package ISA.Team22.Domain.DTO;

import java.time.LocalDate;
import java.util.List;

public class OrdersReviewDTO {
	    private Long id;
	    private LocalDate date;
	    private String status;
	    private List<DrugOrderDTO> drugs;
	    private String pharmacyName;
	    private boolean isOfferGiven;
	    
		public OrdersReviewDTO() {
			super();
		}

		public OrdersReviewDTO(Long id, LocalDate date, String status, List<DrugOrderDTO> drugs, String pharmacyName) {
			super();
			this.id = id;
			this.date = date;
			this.status = status;
			this.drugs = drugs;
			this.pharmacyName = pharmacyName;
		}

		public OrdersReviewDTO(Long id, LocalDate date, String status, List<DrugOrderDTO> drugs,
				String pharmacyName,boolean isOfferGiven) {
			super();
			this.id = id;
			this.date = date;
			this.status = status;
			this.drugs = drugs;
			this.pharmacyName = pharmacyName;
			this.isOfferGiven = isOfferGiven;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<DrugOrderDTO> getDrugs() {
			return drugs;
		}

		public void setDrugs(List<DrugOrderDTO> drugs) {
			this.drugs = drugs;
		}

		public String getPharmacyName() {
			return pharmacyName;
		}

		public void setPharmacyName(String pharmacyName) {
			this.pharmacyName = pharmacyName;
		}

		public boolean isOfferGiven() {
			return isOfferGiven;
		}

		public void setOfferGiven(boolean isOfferGiven) {
			this.isOfferGiven = isOfferGiven;
		}
		
	    
}
