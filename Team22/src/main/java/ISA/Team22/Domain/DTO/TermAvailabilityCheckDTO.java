package ISA.Team22.Domain.DTO;

import java.time.LocalDate;
import java.time.LocalTime;


public class TermAvailabilityCheckDTO {
	
	private Long patientID;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public TermAvailabilityCheckDTO(Long patientID, LocalDate startDate, LocalTime startTime, LocalTime endTime) {

		this.patientID = patientID;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getPatientID() {
		return patientID;
	}

	public void setPatientID(Long patientID) {
		this.patientID = patientID;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	
}
