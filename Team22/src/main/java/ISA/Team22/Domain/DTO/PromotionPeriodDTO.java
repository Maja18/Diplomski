package ISA.Team22.Domain.DTO;

import java.time.LocalDate;

public class PromotionPeriodDTO {
	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public PromotionPeriodDTO() {
		super();
	}

	public PromotionPeriodDTO(Long id, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
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
	
}
