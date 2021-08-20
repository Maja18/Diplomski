package ISA.Team22.Service.IService;

import ISA.Team22.Domain.DTO.DrugReservationDTO;

public interface IDrugReservationService {

	DrugReservationDTO getDrugReservation(Long id);
	void updateReservation(Long id);
}
