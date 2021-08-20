package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.DTO.CounselingDTO;
import ISA.Team22.Domain.DTO.DataForCalendarDTO;
import ISA.Team22.Domain.DTO.TermAvailabilityCheckDTO;


public interface ICounselingService {
	
	List<Counseling> getAllCounselings();
	Boolean checkPatientCounselingSchedule(TermAvailabilityCheckDTO term);
	String scheduleCounselling(CounselingDTO counselingDTO);
	Boolean checkPharmacistSchedule(Counseling counseling);
	List<Counseling> getAllPharmacistCounselings(Long id);
	Boolean checkPatientExaminationSchedule(TermAvailabilityCheckDTO term);
	List<Counseling> findAll();
	boolean canPharmacistMakeComplaint(Long pharmacistId);
	List<CounselingDTO> getMyScheduledCounsellings(Long id);
	CounselingDTO getCounsellingById(Long id);
	void registerAbsence(Long id);
	void updateCounseling(CounselingDTO counselingDTO);
	List<DataForCalendarDTO> getCounselingsForCalendar();
}
