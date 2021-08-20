package ISA.Team22.Domain.Users;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ISA.Team22.Domain.Examination.Counseling;
import ISA.Team22.Domain.Examination.Prescription;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestDermatologist;
import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestPharmacist;
import ISA.Team22.Domain.PharmacyWorkflow.BusinessDayPharmacist;

@Entity
@DiscriminatorValue("Pharmacist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pharmacist extends Person{

	@Column(name = "allGrades")
	private Integer allGrades;
	
	@Column(name = "numberOfGrades")
	private Integer numberOfGrades;
	
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BusinessDayPharmacist> businessDays;
	
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Counseling> counselings;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Pharmacy pharmacy;
	
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AbsenceRequestPharmacist> absenceRequests;
	
	public Pharmacist() {
		super();
	}

	public Pharmacist(Long id, String email, String password, String name, String lastName, String contact,
			Address address, Timestamp lastPasswordResetDate, boolean enabled, List<Authority> authorities,
			Boolean firstLogged,Integer allGrades, Integer numberOfGrades,
			List<BusinessDayPharmacist> businessDays, List<Counseling> counselings,
			Pharmacy pharmacy, List<AbsenceRequestPharmacist> absenceRequests) {
		super(id, email, password, name, lastName, contact, address, lastPasswordResetDate, enabled, authorities, firstLogged);
		this.allGrades = allGrades;
		this.numberOfGrades = numberOfGrades;
		this.businessDays = businessDays;
		this.counselings = counselings;
		this.pharmacy = pharmacy;
		this.absenceRequests = absenceRequests;
	}

	public Integer getAllGrades() {
		return allGrades;
	}

	public void setAllGrades(Integer allGrades) {
		this.allGrades = allGrades;
	}

	public Integer getNumberOfGrades() {
		return numberOfGrades;
	}

	public void setNumberOfGrades(Integer numberOfGrades) {
		this.numberOfGrades = numberOfGrades;
	}

	public List<BusinessDayPharmacist> getBusinessDays() {
		return businessDays;
	}

	public void setBusinessDays(List<BusinessDayPharmacist> businessDays) {
		this.businessDays = businessDays;
	}

	public List<Counseling> getCounselings() {
		return counselings;
	}

	public void setCounselings(List<Counseling> counselings) {
		this.counselings = counselings;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public List<AbsenceRequestPharmacist> getAbsenceRequests() {
		return absenceRequests;
	}

	public void setAbsenceRequests(List<AbsenceRequestPharmacist> absenceRequests) {
		this.absenceRequests = absenceRequests;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	
}
