package ISA.Team22.Domain.PharmacyWorkflow;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Pharmacist;

@Entity
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "description",  nullable = false)
	private String description;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "complaintDate")
	private LocalDate complaintDate;
	
	@OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "dermatologist_id", referencedColumnName = "id", nullable = true, unique = false)
    private Dermatologist dermatologist;
	
	@OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacist pharmacist;
	
	@JsonBackReference
	@OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
	private Patient patient;
	
	@Column(name = "isAnswered",  nullable = false)
	private Boolean isAnswered;
	
	@Column(name = "role",  nullable = false)
	private String role;
	
	
	public Complaint() {
		super();
	}

	
	

	public Complaint(Long id, String description, String answer, LocalDate complaintDate, Dermatologist dermatologist,
			Pharmacist pharmacist, Pharmacy pharmacy, Patient patient, Boolean isAnswered, String role) {
		super();
		this.id = id;
		this.description = description;
		this.answer = answer;
		this.complaintDate = complaintDate;
		this.dermatologist = dermatologist;
		this.pharmacist = pharmacist;
		this.pharmacy = pharmacy;
		this.patient = patient;
		this.isAnswered = isAnswered;
		this.role = role;
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LocalDate getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(LocalDate complaintDate) {
		this.complaintDate = complaintDate;
	}

	

	public Dermatologist getDermatologist() {
		return dermatologist;
	}


	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}


	public Pharmacist getPharmacist() {
		return pharmacist;
	}


	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}


	public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}



	public Boolean getIsAnswered() {
		return isAnswered;
	}



	public void setIsAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}




	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}

	
	
	   
}
