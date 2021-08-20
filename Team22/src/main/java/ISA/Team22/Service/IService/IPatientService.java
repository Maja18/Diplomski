package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.PatientFrontDTO;
import ISA.Team22.Domain.DTO.PersonRequestDTO;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Examination.Examination;

public interface IPatientService {
	Patient findByEmail(String email);
	Patient save(PersonRequestDTO userRequest);
	Patient findById(Long id);
	List<PatientFrontDTO> getAll();
	List<Examination> getAllMyExaminations(Long id);
	List<Counseling> getAllMyCounselings(Long id);
}
