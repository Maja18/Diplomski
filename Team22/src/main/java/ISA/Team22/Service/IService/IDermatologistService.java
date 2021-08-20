package ISA.Team22.Service.IService;

import java.util.List;
import java.util.Set;

import ISA.Team22.Domain.DTO.DermatologistDTO;
import ISA.Team22.Domain.DTO.PatientDTO;
import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.Users.Dermatologist;

public interface IDermatologistService {
	
	Dermatologist getById(Long id);
	Dermatologist findByEmail(String email);
	Dermatologist save(DermatologistDTO userRequest);
	void update(DermatologistDTO dto);
	String updatePassword(DermatologistDTO dto);
	List<Dermatologist> findAll();
	List<PatientSearchDTO> getMyPatients();
	List<PatientSearchDTO> searchMyPatient(PatientSearchDTO patientSearchDTO);
	List<PatientSearchDTO> sortMyPatient(PatientSearchDTO sortingKey);
	List<PatientSearchDTO> sortMyPatientDescending(PatientSearchDTO sortingKey);
	
}
