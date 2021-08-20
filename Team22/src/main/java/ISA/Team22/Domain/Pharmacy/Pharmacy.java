package ISA.Team22.Domain.Pharmacy;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ISA.Team22.Domain.PharmacyWorkflow.Promotion;

import ISA.Team22.Domain.Users.Address;
import ISA.Team22.Domain.Users.Dermatologist;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Pharmacist;
import ISA.Team22.Domain.Users.PharmacyAdministrator;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pharmacy  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "allGrades")
	private Integer allGrades;
	
	@Column(name = "numberOfGrades")
	private Integer numberOfGrades;
	
	@ManyToMany(mappedBy = "subscribedToPharmacies")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Patient> subscribedUsersIDs;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY)
	private List<Pharmacist> pharmacist;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "pharmacy_dermatologists",
    joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"))
	private List<Dermatologist> dermatologist;
	
	@OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacyInventory_id",  referencedColumnName = "id")
	private PharmacyInventory pharmacyInventory;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PharmacyAdministrator> pharmacyAdministrator;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Promotion> promotions;
	
	public Pharmacy() {
		super();
	}

	public Pharmacy(Long id, String name, String description, Integer allGrades, Integer numberOfGrades, List<Patient> subscribedUsersIDs,
			List<Pharmacist> pharmacist, List<Dermatologist> dermatologist, Address address,
			PharmacyInventory pharmacyInventory, List<PharmacyAdministrator> pharmacyAdministrator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.allGrades = allGrades;
		this.numberOfGrades = numberOfGrades;
		this.subscribedUsersIDs = subscribedUsersIDs;
		this.pharmacist = pharmacist;
		this.dermatologist = dermatologist;
		this.address = address;
		this.pharmacyInventory = pharmacyInventory;
		this.pharmacyAdministrator = pharmacyAdministrator;
	}

	public Pharmacy(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Patient> getSubscribedUsersIDs() {
		return subscribedUsersIDs;
	}

	public void setSubscribedUsersIDs(List<Patient> subscribedUsersIDs) {
		this.subscribedUsersIDs = subscribedUsersIDs;
	}

	public List<Pharmacist> getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(List<Pharmacist> pharmacist) {
		this.pharmacist = pharmacist;
	}

	public List<Dermatologist> getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(List<Dermatologist> dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PharmacyInventory getPharmacyInventory() {
		return pharmacyInventory;
	}

	public void setPharmacyInventory(PharmacyInventory pharmacyInventory) {
		this.pharmacyInventory = pharmacyInventory;
	}

	public List<PharmacyAdministrator> getPharmacyAdministrator() {
		return pharmacyAdministrator;
	}

	public void setPharmacyAdministrator(List<PharmacyAdministrator> pharmacyAdministrator) {
		this.pharmacyAdministrator = pharmacyAdministrator;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	
	
	
}
