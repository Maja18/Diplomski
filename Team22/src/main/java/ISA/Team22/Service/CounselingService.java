package ISA.Team22.Service;


import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.Examination.ExaminationStatus;
import ISA.Team22.Domain.DTO.CounselingDTO;
import ISA.Team22.Domain.DTO.DataForCalendarDTO;
import ISA.Team22.Domain.DTO.TermAvailabilityCheckDTO;
import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayPharmacist;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Repository.CounselingRepository;
import ISA.Team22.Service.IService.ICounselingService;

@Service
public class CounselingService implements ICounselingService {

	
	private final CounselingRepository counselingRepository;
	private final PharmacistService pharmacistService; 
	private final PatientService patientService;
	private final BusinessDayPharmacistService businessDayPharmacistService;
	private final EmailService emailServices;
	
	@Autowired
	public CounselingService(CounselingRepository counselingRepository, PharmacistService pharmacistService,
			PatientService patientService, BusinessDayPharmacistService businessDayPharmacistService, EmailService emailServices) {
		super();
		this.counselingRepository = counselingRepository;
		this.pharmacistService = pharmacistService;
		this.patientService = patientService;
		this.businessDayPharmacistService = businessDayPharmacistService;
		this.emailServices = emailServices;
	}

	@Override
	public List<Counseling> getAllCounselings() {
		return counselingRepository.findAll();
	}
	
	@Override
	public List<Counseling> findAll() {
		return counselingRepository.findAll();
	}
	
	@Override
	public Boolean checkPatientCounselingSchedule(TermAvailabilityCheckDTO term) {
		List<Counseling> counselings = patientService.getAllMyCounselings(term.getPatientID());
		if (counselings.isEmpty())
			return true;
		
		for(Counseling c:counselings)
			if(term.getStartDate().equals(c.getStartDate())) {
				if(term.getStartTime().isAfter(c.getStartTime())  && term.getStartTime().isBefore(c.getEndTime()))
					return false;
				else if(term.getEndTime().isAfter(c.getStartTime())  && term.getEndTime().isBefore(c.getEndTime())) 
					return false;
				else if(c.getStartTime().isAfter(term.getStartTime())  && c.getEndTime().isBefore(term.getEndTime()))
					return false;
				else if(term.getStartTime().equals(c.getStartTime()))
					return false;
				else return true;
			}else continue;
		return true;
	}
	
	@Override
	public Boolean checkPatientExaminationSchedule(TermAvailabilityCheckDTO term) {
		List<Examination> examinations = patientService.getAllMyExaminations(term.getPatientID());
		if (examinations == null)
			return true;
		for(Examination e:examinations) {
			if(term.getStartDate().equals(e.getStartDate())) {
				if(term.getStartTime().isAfter(e.getStartTime())  && term.getStartTime().isBefore(e.getEndTime()))
					return false;
				else if(term.getEndTime().isAfter(e.getStartTime())  && term.getEndTime().isBefore(e.getEndTime())) 
					return false;
				else if(e.getStartTime().isAfter(term.getStartTime())  && e.getEndTime().isBefore(term.getEndTime()))
					return false;
				else if(term.getStartTime().equals(e.getStartTime()))
						return false;
				else return true;
			}else continue;
		}
		return true;
	}

	@Override
	public String scheduleCounselling(CounselingDTO counselingDTO) {
		if(counselingDTO.getStartTime().isAfter(counselingDTO.getEndTime()))
			return "Please choose end time to be after start time!";
		if(counselingDTO.getStartTime().equals(counselingDTO.getEndTime()))
			return "Please choose end time to be after start time!";
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Pharmacist pharmacist = pharmacistService.getById(person.getId());
		LocalDate startDate = counselingDTO.getStartDate(); 
		LocalTime startTime = counselingDTO.getStartTime();
		LocalTime endTime = counselingDTO.getEndTime();
		String[] tokens = counselingDTO.getPatientInfo().split("\\s");
		Patient patient = patientService.findByEmail(tokens[3]);
		Long duration = startTime.until(endTime, MINUTES);
		
		Counseling counseling = new Counseling(startDate, startTime, endTime, 0.0, duration, "", pharmacist, patient, ExaminationStatus.scheduled);
		TermAvailabilityCheckDTO term = new TermAvailabilityCheckDTO(patient.getId(), startDate, startTime, endTime);
		Boolean checkCounseling = checkPatientCounselingSchedule(term);
		
		if(checkCounseling)
			if(checkPharmacistSchedule(counseling)) {
				counselingRepository.save(counseling);
				this.emailServices.sendNotificationAsync(patient.getEmail(), "Scheduled examination INFO", ""
						+ "Your examination is scheduled for date " + counseling.getStartDate().toString() + " " + counseling.getStartTime() +".");
				return ("Counseling is scheduled! ");
			}else return ("Pharmacist is  not free, choose another date or time. ");
		else return ("Patient has another counselling, choose another date or time. ");
				
	}
	
	
	@Override
	public Boolean checkPharmacistSchedule(Counseling counseling) {
		//first we found does dermatologist work that day we need
		BusinessDayPharmacist businessDayPharmacist = businessDayPharmacistService.getPharmacisttBusinessDay(counseling.getPharmacist().getId(), counseling.getStartDate());
		if(businessDayPharmacist.getId() == null)
			return false;
		Boolean shift = checkShift(counseling, businessDayPharmacist);
		if(!shift)
			return false;
		//second we found does dermatologist have any other scheduled examination that day we need
		List<Counseling> counselings = getAllPharmacistCounselings(counseling.getPharmacist().getId());
		for(Counseling c:counselings) {
			if(counseling.getStartDate().equals(c.getStartDate())) {
				if(counseling.getStartTime().isAfter(c.getStartTime())  && counseling.getStartTime().isBefore(c.getEndTime()))
					return false;
				else if(counseling.getEndTime().isAfter(c.getStartTime())  && counseling.getEndTime().isBefore(c.getEndTime())) 
					return false;
				else if(c.getStartTime().isAfter(counseling.getStartTime())  && c.getEndTime().isBefore(counseling.getEndTime()))
					return false;
				else if(counseling.getStartTime().equals(c.getStartTime()))
					return false;
				else return true;
			}else continue;
		}
		return shift;
	}
	
	private Boolean checkShift(Counseling counseling, BusinessDayPharmacist businessDayPharmacist) {
		if(counseling.getStartTime().isBefore(businessDayPharmacist.getShift().getStartTime())) 
			return false;
		else if(counseling.getStartTime().isAfter(businessDayPharmacist.getShift().getEndTime()))
			return false;
		else if(counseling.getEndTime().isBefore(businessDayPharmacist.getShift().getStartTime()))
			return false;
		else if(counseling.getEndTime().isAfter(businessDayPharmacist.getShift().getEndTime()))
			return false;
		else
			return true;
	}
	
	@Override
	public List<Counseling> getAllPharmacistCounselings(Long id) {
		List<Counseling> allCounselings = counselingRepository.findAll();
		List<Counseling> counselings = new ArrayList<Counseling>();
		for(Counseling e:allCounselings)
			if(e.getPharmacist().getId().equals(id))
				counselings.add(e);
		
		return counselings;
	}
	
	@Override
	public boolean canPharmacistMakeComplaint(Long pharmacistId) {
        Boolean isAble = false; 
        List<Counseling> counselings = findAll();
        for (Counseling counseling : counselings) {
			if(counseling.getPharmacist().getId().equals(pharmacistId) && counseling.getCounselingStatus() == ExaminationStatus.held) {
					isAble = true;
			}
		}
       
        return isAble;
	}

	@Override
	public CounselingDTO getCounsellingById(Long id) {
		Counseling counseling = counselingRepository.findById(id).get();
		CounselingDTO counselingDTO = new CounselingDTO(counseling.getPharmacist().getPharmacy().getId(), counseling.getId(), counseling.getPatient().getId().toString());
		
		return counselingDTO;
	}

	@Override
	public List<CounselingDTO> getMyScheduledCounsellings(Long id) {
		List<Counseling> counselings = getAllPharmacistCounselings(id);
		List<CounselingDTO> counselingsDTO = new ArrayList<>();
		
		for(Counseling c:counselings)
			counselingsDTO.add(new CounselingDTO(c.getPharmacist().getPharmacy().getId(), c.getId(), c.getStartDate(), c.getStartTime().toString(),
					c.getPatient().getName()+ " " + c.getPatient().getLastName()));
		
		return counselingsDTO;
	}

	@Override
	public void registerAbsence(Long id) {
		Counseling counseling  = counselingRepository.findById(id).get();
		counseling.setCounselingStatus(ExaminationStatus.didNotCome); 
		Patient patient = patientService.findByEmail(counseling.getPatient().getEmail());
		Integer penals = patient.getPenalty() + 1;
		patient.setPenalty(penals);
		counselingRepository.save(counseling);
		
	}

	@Override
	public void updateCounseling(CounselingDTO counselingDTO) {
		Counseling counseling =  counselingRepository.findById(counselingDTO.getCounselingId()).get();
		counseling.setDiagnosis(counselingDTO.getPatientInfo());
		counseling.setCounselingStatus(ExaminationStatus.held);
		Patient patient = counseling.getPatient();
		List<Counseling> patientCounselings = patient.getCounseling();
		patientCounselings.add(counseling);
		patient.setCounseling(patientCounselings);
		patientService.saveChanges(patient);
		counselingRepository.save(counseling);
		
	}

	@Override
	public List<DataForCalendarDTO> getCounselingsForCalendar() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Pharmacist pharmacist = pharmacistService.getById(person.getId());
		List<Counseling> counselings = getAllPharmacistCounselings(person.getId());
		List<DataForCalendarDTO> counselingsForCalendar = new ArrayList<DataForCalendarDTO>();
		Date date = new Date();
		for (Counseling e : counselings) {
			Instant startTimeI = e.getStartTime().atDate(LocalDate.of(1111, 11, 11)).atZone(ZoneId.systemDefault()).toInstant();
			Date startTime = Date.from(startTimeI);
			Instant endTimeI = e.getEndTime().atDate(LocalDate.of(1111, 11, 11)).atZone(ZoneId.systemDefault()).toInstant();
			Date endTime = Date.from(endTimeI);
			date = java.sql.Date.valueOf(e.getStartDate());
			
			counselingsForCalendar.add(new DataForCalendarDTO(e.getId(), e.getPatient().getId(), date, startTime,
					endTime, e.getDuration(), pharmacist.getPharmacy().getName(),e.getPatient().getName() + " " + e.getPatient().getLastName()));
		}
		return counselingsForCalendar;
	}
	
}
