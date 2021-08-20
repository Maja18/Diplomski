package ISA.Team22.Domain.Users;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ISA.Team22.Domain.PharmacyWorkflow.AbsenceRequestPharmacist;
import ISA.Team22.Domain.PharmacyWorkflow.Complaint;

@Entity
@DiscriminatorValue("SystemAdministrator")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SystemAdministrator extends Person{
	
	@ManyToMany(targetEntity = Complaint.class,  cascade = CascadeType.ALL)
	private List<Complaint> complaints;
	
	private Boolean mainAdmin;

	public SystemAdministrator() {
		super();
	}
	
	public SystemAdministrator(Long id, String email, String password, String name, String lastName, String contact,
			Address address, Timestamp lastPasswordResetDate, boolean enabled, List<Authority> authorities,
			Boolean firstLogged, List<Complaint> complaints, Boolean mainAdmin) {
		super(id, email, password, name, lastName, contact, address, lastPasswordResetDate, enabled, authorities, firstLogged);
		this.complaints = complaints;
		this.mainAdmin = mainAdmin;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Boolean getMainAdmin() {
		return mainAdmin;
	}

	public void setMainAdmin(Boolean mainAdmin) {
		this.mainAdmin = mainAdmin;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}
	
	 
}
