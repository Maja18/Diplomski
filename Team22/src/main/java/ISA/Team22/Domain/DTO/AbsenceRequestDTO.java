package ISA.Team22.Domain.DTO;

import java.time.LocalDate;

import ISA.Team22.Domain.Pharmacy.OfferStatus;
import ISA.Team22.Domain.PharmacyWorkflow.VacationType;

public class AbsenceRequestDTO {

	LocalDate startDate;
	LocalDate endDate;
	VacationType vacationType;
	OfferStatus offerStatus;
	String description;
	
	public AbsenceRequestDTO() {
		super();
	}

	public AbsenceRequestDTO(LocalDate startDate, LocalDate endDate, VacationType vacationType, OfferStatus offerStatus,
			String description) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.vacationType = vacationType;
		this.offerStatus = offerStatus;
		this.description = description;
	}

	public AbsenceRequestDTO(LocalDate startDate, LocalDate endDate, VacationType vacationType) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.vacationType = vacationType;
	}
	
	public AbsenceRequestDTO(VacationType vacationType) {
		super();
		this.vacationType = vacationType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public VacationType getVacationType() {
		return vacationType;
	}

	public void setVacationType(VacationType vacationType) {
		this.vacationType = vacationType;
	}

	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
