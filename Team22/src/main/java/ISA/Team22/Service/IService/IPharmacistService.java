package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.PatientSearchDTO;
import ISA.Team22.Domain.DTO.PharmacistDTO;
import ISA.Team22.Domain.Users.Pharmacist;

public interface IPharmacistService {
	
	Pharmacist getById(Long id);
	Pharmacist findByEmail(String email);
	Pharmacist save(PharmacistDTO userRequest);
	void update(PharmacistDTO pharmacistDTO);
	String updatePassword(PharmacistDTO pharmacistDTO);
	List<Pharmacist> findAll();
	List<PatientSearchDTO> searchMyPatient(PatientSearchDTO patientSearchDTO);
	List<PatientSearchDTO> getMyPatients();
	List<PatientSearchDTO> sortMyPatient(PatientSearchDTO sortingKey);
	List<PatientSearchDTO> sortMyPatientDescending(PatientSearchDTO sortingKey);
}
