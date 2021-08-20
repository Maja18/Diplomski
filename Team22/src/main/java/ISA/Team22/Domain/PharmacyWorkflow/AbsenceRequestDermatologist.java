package ISA.Team22.Domain.PharmacyWorkflow;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ISA.Team22.Domain.Pharmacy.OfferStatus;
import ISA.Team22.Domain.Users.Dermatologist;

@Entity
@Table(name="absence_request_dermatologist")
public class AbsenceRequestDermatologist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "startDate",  nullable = false)
	private LocalDate startDate;
	
	@Column(name = "endDate",  nullable = false)
	private LocalDate endDate;
	
	@Enumerated(EnumType.ORDINAL)
	private VacationType vacationType;
	
	@Column(name = "description")
	private String description;
	
	@Enumerated(EnumType.ORDINAL)
	private OfferStatus status;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Dermatologist dermatologist;
	
	public AbsenceRequestDermatologist() {
		super();
	}

	public AbsenceRequestDermatologist(Long id, LocalDate startDate, LocalDate endDate, VacationType vacationType,
			String description, OfferStatus status, Dermatologist dermatologist) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.vacationType = vacationType;
		this.description = description;
		this.status = status;
		this.dermatologist = dermatologist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	
}
