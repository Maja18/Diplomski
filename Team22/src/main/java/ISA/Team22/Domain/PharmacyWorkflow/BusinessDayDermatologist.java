package ISA.Team22.Domain.PharmacyWorkflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ISA.Team22.Domain.Users.Dermatologist;

@Entity
public class BusinessDayDermatologist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "shift_id", referencedColumnName = "id", nullable = false)
	private Period shift;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "business_day_period",
    joinColumns = @JoinColumn(name = "business_day_dermatologist_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "period_id", referencedColumnName = "id"))
	private List<Period> scheduledPeriods;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Dermatologist dermatologist;
	
	@Column(name = "pharmacyId",  nullable = false)
	private Long pharmacyId;
	
	public BusinessDayDermatologist() {
		super();
	}

	public BusinessDayDermatologist(Long id, Period shift, List<Period> scheduledPeriods, Dermatologist dermatologist,
			Long pharmacyId) {
		super();
		this.id = id;
		this.shift = shift;
		this.scheduledPeriods = scheduledPeriods;
		this.dermatologist = dermatologist;
		this.pharmacyId = pharmacyId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Period getShift() {
		return shift;
	}

	public void setShift(Period shift) {
		this.shift = shift;
	}

	public List<Period> getScheduledPeriods() {
		return scheduledPeriods;
	}

	public void setScheduledPeriods(List<Period> scheduledPeriods) {
		this.scheduledPeriods = scheduledPeriods;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	@Override
	public String toString() {
		return "BusinessDayDermatologist [id=" + id + ", shift=" + shift + ", scheduledPeriods=" + scheduledPeriods
				+ ", dermatologist=" + dermatologist + ", pharmacyId=" + pharmacyId + "]";
	}
	
	
	
}
