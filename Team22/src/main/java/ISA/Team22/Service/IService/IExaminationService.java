package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.Examination.Examination;

import ISA.Team22.Domain.DTO.ExaminationDTO;
import ISA.Team22.Domain.DTO.DataForCalendarDTO;
import ISA.Team22.Domain.DTO.ExaminationUpdateDTO;
import ISA.Team22.Domain.DTO.TermAvailabilityCheckDTO;


public interface IExaminationService {
	
	String scheduleNewExamination(ExaminationDTO examinationDTO);
	List<ExaminationDTO> getFreeScheduledExaminations(Long id);
	List<ExaminationDTO> getMyScheduledExaminations(Long id);
	String updateScheduledExamination(ExaminationDTO examinationDTO);
	ExaminationDTO getExaminationByID(Long id);
	void registerAbsence(Long id);
	void updateExamination(ExaminationUpdateDTO examinationUpdateDTO);
	List<DataForCalendarDTO> getExaminationsForCalendar();
	boolean canDermatologistMakeComplaint(Long dermatologistId);
	
	
}
