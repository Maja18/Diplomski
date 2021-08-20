package ISA.Team22.Domain.Examination;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestDermatologist;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Pharmacist;

@Entity
public class Prescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "prescriptionDate",  nullable = false)
	private LocalDate prescriptionDate;
	
	@Column(name = "amountOfDrug",  nullable = false)
	private Integer amountOfDrug;
	
	@Column(name = "durationOfTherapy",  nullable = false)
    private Double durationOfTherapy;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JoinColumn(name = "pharmacy", referencedColumnName = "id", nullable = false)
	private Pharmacy pharmacy;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Patient patient;
	
	@ManyToMany
    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinTable(name = "prescription_drugs", joinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id"))
	private List<Drug> drugs;
	
	@Enumerated(EnumType.ORDINAL)
	private PrescriptionStatus prescriptionStatus;
    
	public Prescription() {
		super();
	}
	

	public Prescription(Long id, LocalDate prescriptionDate, Integer amountOfDrug, Double durationOfTherapy,
			Pharmacist pharmacist, Patient patient, List<Drug> drugs, PrescriptionStatus prescriptionStatus) {
		super();
		this.id = id;
		this.prescriptionDate = prescriptionDate;
		this.amountOfDrug = amountOfDrug;
		this.durationOfTherapy = durationOfTherapy;
		this.patient = patient;
		this.drugs = drugs;
		this.prescriptionStatus = prescriptionStatus;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(LocalDate prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public Integer getAmountOfDrug() {
		return amountOfDrug;
	}
	public void setAmountOfDrug(Integer amountOfDrug) {
		this.amountOfDrug = amountOfDrug;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<Drug> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}
	public Double getDurationOfTherapy() {
		return durationOfTherapy;
	}
	public void setDurationOfTherapy(Double durationOfTherapy) {
		this.durationOfTherapy = durationOfTherapy;
	}


	public PrescriptionStatus getPrescriptionStatus() {
		return prescriptionStatus;
	}


	public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}


	public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	 
	
}
