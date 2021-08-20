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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ISA.Team22.Domain.DrugManipulation.SupplierDrugStock;
import ISA.Team22.Domain.Pharmacy.Offer;

@Entity
@DiscriminatorValue("Supplier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier extends Person{
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Offer> offers;
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SupplierDrugStock> drugStocks;

	public Supplier() {
		super();
	}

	public Supplier(Long id, String email, String password, String name, String lastName, String contact,
			Address address, Timestamp lastPasswordResetDate, boolean enabled, List<Authority> authorities,
			Boolean firstLogged, List<Offer> offers, List<SupplierDrugStock> drugStocks) {
		super(id, email, password, name, lastName, contact, address, lastPasswordResetDate, enabled, authorities, firstLogged);
		this.offers = offers;
		this.drugStocks = drugStocks;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public List<SupplierDrugStock> getDrugStocks() {
		return drugStocks;
	}

	public void setDrugStocks(List<SupplierDrugStock> drugStocks) {
		this.drugStocks = drugStocks;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}
	
}
