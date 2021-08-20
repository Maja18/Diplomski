package ISA.Team22.Domain.Users;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugReservation;
import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Examination.EPrescription;
import ISA.Team22.Domain.Examination.Examination;
import ISA.Team22.Domain.Examination.Prescription;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestDermatologist;
import ISA.Team22.Domain.PharmacyWorkflow.Complaint;
import ISA.Team22.Domain.PharmacyWorkflow.LoyaltyProgram;

@Entity
@DiscriminatorValue("Patient")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient extends Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "penalty")
	private Integer penalty;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loyaltyProgram_id", referencedColumnName = "id")
	private LoyaltyProgram loyaltyProgram;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinTable(name = "patient_allergies",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id"))
	private List<Drug> drugs;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Counseling> counseling;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Prescription> prescription;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DrugReservation> drugReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Examination> examinations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Complaint> complaints;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EPrescription> ePrescriptions;
	
	@ManyToMany
    @JsonIgnore
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinTable(name = "patients_subscriptions", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
	private List<Pharmacy> subscribedToPharmacies;
	
	public Patient() {
		super();
	}

	public Patient(Long id, String email, String password, String name, String lastName, String contact,
			Address address, Timestamp lastPasswordResetDate, boolean enabled, List<Authority> authorities,
			Boolean firstLogged, Integer penalty, LoyaltyProgram loyaltyProgram, List<Drug> drugs,
			List<Counseling> counseling, List<Prescription> prescription, List<DrugReservation> drugReservations,
			List<Examination> examinations, List<Complaint> complaints, List<EPrescription> ePrescriptions) {
		super(id, email, password, name, lastName, contact, address, lastPasswordResetDate, enabled, authorities, firstLogged);
		this.penalty = penalty;
		this.loyaltyProgram = loyaltyProgram;
		this.drugs = drugs;
		this.counseling = counseling;
		this.prescription = prescription;
		this.drugReservations = drugReservations;
		this.examinations = examinations;
		this.complaints = complaints;
		this.ePrescriptions = ePrescriptions;
	}
	
	

	public Patient(Integer penalty, LoyaltyProgram loyaltyProgram, List<Drug> drugs, List<Counseling> counseling,
			List<Prescription> prescription, List<DrugReservation> drugReservations, List<Examination> examinations,
			List<Complaint> complaints, List<EPrescription> ePrescriptions, List<Pharmacy> subscribedToPharmacies) {
		super();
		this.penalty = penalty;
		this.loyaltyProgram = loyaltyProgram;
		this.drugs = drugs;
		this.counseling = counseling;
		this.prescription = prescription;
		this.drugReservations = drugReservations;
		this.examinations = examinations;
		this.complaints = complaints;
		this.ePrescriptions = ePrescriptions;
		this.subscribedToPharmacies = subscribedToPharmacies;
	}

	public Integer getPenalty() {
		return penalty;
	}

	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}

	public LoyaltyProgram getLoyaltyProgram() {
		return loyaltyProgram;
	}

	public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public List<Counseling> getCounseling() {
		return counseling;
	}

	public void setCounseling(List<Counseling> counseling) {
		this.counseling = counseling;
	}

	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}

	public List<DrugReservation> getDrugReservations() {
		return drugReservations;
	}

	public void setDrugReservations(List<DrugReservation> drugReservations) {
		this.drugReservations = drugReservations;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public List<EPrescription> getePrescriptions() {
		return ePrescriptions;
	}

	public void setePrescriptions(List<EPrescription> ePrescriptions) {
		this.ePrescriptions = ePrescriptions;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	public List<Pharmacy> getSubscribedToPharmacies() {
		return subscribedToPharmacies;
	}

	public void setSubscribedToPharmacies(List<Pharmacy> subscribedToPharmacies) {
		this.subscribedToPharmacies = subscribedToPharmacies;
	}
	
	
	
}
