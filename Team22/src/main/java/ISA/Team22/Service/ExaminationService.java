package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.Examination.ExaminationStatus;

import ISA.Team22.Domain.DTO.ExaminationDTO;
import ISA.Team22.Domain.DTO.DataForCalendarDTO;
import ISA.Team22.Domain.DTO.ExaminationUpdateDTO;
import ISA.Team22.Domain.DTO.TermAvailabilityCheckDTO;
import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayDermatologist;
import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayPharmacist;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Repository.ExaminationRepository;
import ISA.Team22.Service.IService.IExaminationService;

@Service
public class ExaminationService implements IExaminationService {
	
	private final DermatologistService dermatologistService;
	private final PharmacyService pharmacyService;
	private final PatientService patientService;
	private final BusinessDayDermatologistService businessDayDermatologistService;
	private final EmailService emailServices;
	private final ExaminationRepository examinationRepository;
	
	@Autowired
	public ExaminationService(ExaminationRepository examinationRepository, DermatologistService dermatologistService,
			PharmacyService pharmacyService, PatientService patientService, 
			BusinessDayDermatologistService businessDayDermatologistService, EmailService emailServices) {
		super();
		this.examinationRepository = examinationRepository;
		this.dermatologistService = dermatologistService;
		this.pharmacyService = pharmacyService;
		this.patientService = patientService;
		this.businessDayDermatologistService = businessDayDermatologistService;
		this.emailServices = emailServices;
	}
	
	public List<Examination> findAll() {
		return examinationRepository.findAll();
	}

	@Override
	public String scheduleNewExamination(ExaminationDTO examinationDTO) {
		if(examinationDTO.getStartTime().isAfter(examinationDTO.getEndTime()))
			return "Please choose end time to be after start time!";
		if(examinationDTO.getStartTime().equals(examinationDTO.getEndTime()))
			return "Please choose end time to be after start time!";
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		Dermatologist dermatologist = dermatologistService.getById(person.getId());
		LocalDate startDate = examinationDTO.getStartDate(); 
		LocalTime startTime = examinationDTO.getStartTime();
		LocalTime endTime = examinationDTO.getEndTime();
		Pharmacy pharmacy = pharmacyService.getById(examinationDTO.getPharmacyID());
		String[] tokens = examinationDTO.getPatientInfo().split("\\s");
		Patient patient = patientService.findByEmail(tokens[3]);
		Long duration = startTime.until(endTime, MINUTES);

		Examination examination = new Examination( startDate, startTime, endTime, duration, 2500.50, ExaminationStatus.scheduled,"no diagnos", dermatologist, patient, pharmacy, null);
		TermAvailabilityCheckDTO term = new TermAvailabilityCheckDTO(patient.getId(), startDate, startTime, endTime);
		Boolean checkPatient = checkPatientExaminationSchedule(term);
		Boolean checkCounseling = checkPatientCounselingSchedule(term);
		if(checkPatient) {
			if(checkCounseling)
				if(checkDermatologistSchedule(examination)) {
					examinationRepository.save(examination);
					this.emailServices.sendNotificationAsync(patient.getEmail(), "Scheduled examination INFO", ""
							+ "Your examination is scheduled for date " + examination.getStartDate().toString() + " " + examination.getStartTime() +".");
					return("Examination is scheduled!");
				}else return("Dermatologist is not free, please choose another date, time or pharmacy!");
			else return("Patient has counceling in that time, please choose another date or time!");
		}else return("Patient has another examination in that time, please choose another date or time!");
	
	}
	@Override
	public String updateScheduledExamination(ExaminationDTO examinationDTO) {
		Examination examination = examinationRepository.findById(examinationDTO.getExaminationID()).get();
		String[] tokens = examinationDTO.getPatientInfo().split("\\s");
		System.out.println(tokens[3]);
		Patient patient = patientService.findByEmail(tokens[3]);
		TermAvailabilityCheckDTO term = new TermAvailabilityCheckDTO(patient.getId(), examinationDTO.getStartDate(), examinationDTO.getStartTime(), examination.getEndTime());
		Boolean checkPatient = checkPatientExaminationSchedule(term);
		Boolean checkCounseling = checkPatientCounselingSchedule(term);
		if(checkPatient) {
			if(checkCounseling) {
				examination.setPatient(patient);
				examinationRepository.save(examination);
				this.emailServices.sendNotificationAsync(patient.getEmail(), "Scheduled examination INFO", ""
						+ "Your examination is scheduled for date " + examination.getStartDate().toString() + " " + examination.getStartTime() +".");
				return("Examination is scheduled!");
			}else return("Patient has counceling in that time, please choose another date or time!");
		}else return("Patient has another examination in that time, please choose another date or time!");
		
	}
	@Override
	public List<ExaminationDTO> getFreeScheduledExaminations(Long id) {
		List<Examination> examinations = examinationRepository.findAll();
		List<ExaminationDTO> examinationsDTO = new ArrayList<ExaminationDTO>();
		for(Examination e:examinations) 
			if(e.getPatient() == null && e.getDermatologist().getId() == id) 
				examinationsDTO.add(new ExaminationDTO(e.getPharmacy().getId(), e.getStartDate(), e.getStartTime(), e.getEndTime(), e.getStartTime().toString(), e.getEndTime().toString(), e.getDuration(), e.getId(), e.getPharmacy().getName()));
			
		return examinationsDTO;
	}

	public List<Examination> getAllPatientExaminations(Long id) {
		List<Examination> allExaminations = examinationRepository.findAll();
		List<Examination> examinations = new ArrayList<Examination>();
		for(Examination e:allExaminations) {	
			if(e.getPatient() == null ) 
				continue;
			else if(e.getPatient().getId().equals(id)) 
				examinations.add(e);
		}
			
		return examinations;
	}

	public List<Examination> getAllDermatologistExamination(Long id) {
		List<Examination> allExaminations = examinationRepository.findAll();
		List<Examination> examinations = new ArrayList<Examination>();
		for(Examination e:allExaminations)
			if(e.getDermatologist().getId() == id)
				examinations.add(e);
		return examinations;
	}

	public Boolean checkPatientExaminationSchedule(TermAvailabilityCheckDTO term) {

		List<Examination> examinations = getAllPatientExaminations(term.getPatientID());
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
	public boolean canDermatologistMakeComplaint(Long dermatologistId) {

        Boolean isAble = false; 
        List<Examination> examinations = findAll();
        
        for (Examination examination : examinations) {
			if(examination.getDermatologist().getId().equals(dermatologistId) && examination.getExaminationStatus() == ExaminationStatus.held) {
					isAble = true;
			}
		}
       
        return isAble;
	}

	public Boolean checkDermatologistSchedule(Examination examination) {
		//first we found does dermatologist work that day we need
		BusinessDayDermatologist businessDayDermatologist = businessDayDermatologistService.getDermatolgistBusinessDay(examination.getDermatologist().getId(), examination.getStartDate(), examination.getPharmacy().getId());
		if(businessDayDermatologist.getId() == null)
			return false;
		Boolean shift = checkShift(examination, businessDayDermatologist);
		if(!shift)
			return false;
		//second we found does dermatologist have any other scheduled examination that day we need
		List<Examination> examinations = getAllDermatologistExamination(examination.getDermatologist().getId());
		for(Examination e:examinations)
			if(examination.getStartDate().equals(e.getStartDate())) {
			System.out.println("Datum pregleda koji treba da zakazem" + examination.getStartDate()+"Datum zakazanog" + e.getStartDate());
				if(examination.getStartTime().isAfter(e.getStartTime())  && examination.getStartTime().isBefore(e.getEndTime()))
					return false;
				else if(examination.getEndTime().isAfter(e.getStartTime())  && examination.getEndTime().isBefore(e.getEndTime())) 
					return false;
				else if(e.getStartTime().isAfter(examination.getStartTime())  && e.getEndTime().isBefore(examination.getEndTime()))
					return false;
				else if(examination.getStartTime().equals(e.getStartTime()))
					return false;
				else return true;
			}else continue;
		return shift;
	}

	@Override
	public ExaminationDTO getExaminationByID(Long id) {
		Examination e = examinationRepository.findById(id).get();
		ExaminationDTO examinationDTO = new ExaminationDTO(e.getPharmacy().getId(), e.getId(), e.getPatient().getId().toString());
		
		return examinationDTO;
	}

	@Override
	public void registerAbsence(Long id) {
		Examination examination  = examinationRepository.findById(id).get();
		examination.setExaminationStatus(ExaminationStatus.didNotCome);
		Patient patient = patientService.findByEmail(examination.getPatient().getEmail());
		Integer penals = patient.getPenalty() + 1;
		patient.setPenalty(penals);
		examinationRepository.save(examination);
		
	}

	private Boolean checkShift(Examination examination, BusinessDayDermatologist businessDayDermatologist) {
		if(examination.getStartTime().isBefore(businessDayDermatologist.getShift().getStartTime())) 
			return false;
		else if(examination.getStartTime().isAfter(businessDayDermatologist.getShift().getEndTime()))
			return false;
		else if(examination.getEndTime().isBefore(businessDayDermatologist.getShift().getStartTime()))
			return false;
		else if(examination.getEndTime().isAfter(businessDayDermatologist.getShift().getEndTime()))
			return false;
		else
			return true;
	}
	
	@Override
	public void updateExamination(ExaminationUpdateDTO examinationUpdateDTO) {
		Examination examination = examinationRepository.findById(examinationUpdateDTO.getExaminationId()).get();
		examination.setDiagnosis(examinationUpdateDTO.getExaminationInfo());
		examination.setExaminationStatus(ExaminationStatus.held);
		Patient patient = examination.getPatient();
		List<Examination> patientExaminations = patient.getExaminations();
		patientExaminations.add(examination);
		patient.setExaminations(patientExaminations);
		patientService.saveChanges(patient);
		examinationRepository.save(examination);
	}

	@Override
	public List<ExaminationDTO> getMyScheduledExaminations(Long id) {
		List<Examination> examinations = getAllDermatologistExamination(id);
		List<ExaminationDTO> examinationsDTO = new ArrayList<ExaminationDTO>();
		
		for(Examination e:examinations)
			examinationsDTO.add(new ExaminationDTO(e.getPharmacy().getId(), e.getStartDate(),e.getStartTime(),
					e.getEndTime(), e.getStartTime().toString(), e.getEndTime().toString(), e.getDuration(), e.getId(), e.getPharmacy().getName(), e.getPatient().getName()+ " " + e.getPatient().getLastName()));
		
		return examinationsDTO;
	}

	@Override
	public List<DataForCalendarDTO> getExaminationsForCalendar() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		Person person = (Person) currentUser.getPrincipal();
		List<Examination> examinations = getAllDermatologistExamination(person.getId());
		List<DataForCalendarDTO> examinationsForCalendar = new ArrayList<DataForCalendarDTO>();
		Date date = new Date();
		for (Examination e : examinations) {
			Instant startTimeI = e.getStartTime().atDate(LocalDate.of(1111, 11, 11)).atZone(ZoneId.systemDefault()).toInstant();
			Date startTime = Date.from(startTimeI);
			Instant endTimeI = e.getEndTime().atDate(LocalDate.of(1111, 11, 11)).atZone(ZoneId.systemDefault()).toInstant();
			Date endTime = Date.from(endTimeI);
			date = java.sql.Date.valueOf(e.getStartDate());
			if(e.getPatient() == null) {
				examinationsForCalendar.add(new DataForCalendarDTO(e.getId(), date, startTime,
						endTime, e.getDuration(), e.getPharmacy().getName()));
			}else {
				examinationsForCalendar.add(new DataForCalendarDTO(e.getId(), e.getPatient().getId(), date, startTime,
						endTime, e.getDuration(), e.getPharmacy().getName(),e.getPatient().getName() + " " + e.getPatient().getLastName()));
			}
			
		}
		return examinationsForCalendar;
	}
	
}
