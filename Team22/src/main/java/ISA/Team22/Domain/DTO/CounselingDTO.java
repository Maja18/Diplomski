package ISA.Team22.Domain.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class CounselingDTO {
	private Long pharmacyID;
	
	private Long counselingId;
	
	private LocalDate startDate;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private String startTimeText;
	
	private String endTimeText;
	
	private String patientInfo;

	
	public CounselingDTO() {
		super();
	}

	
	public CounselingDTO(Long pharmacyID, Long counselingId, LocalDate startDate,String startTimeText, String patientInfo) {
		super();
		this.pharmacyID = pharmacyID;
		this.counselingId = counselingId;
		this.startDate = startDate;
		this.startTimeText = startTimeText;
		this.patientInfo = patientInfo;
	}


	public CounselingDTO(Long counselingId, LocalDate startDate, LocalTime startTime, LocalTime endTime, String patientInfo) {
		super();
		this.counselingId = counselingId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.patientInfo = patientInfo;
	}



	public CounselingDTO(LocalDate startDate, LocalTime startTime, LocalTime endTime, String patientInfo) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.patientInfo = patientInfo;
	}

	public Long getCounselingId() {
		return counselingId;
	}


	public CounselingDTO(Long pharmacyID, Long counselingId, String patientInfo) {
		super();
		this.pharmacyID = pharmacyID;
		this.counselingId = counselingId;
		this.patientInfo = patientInfo;
	}


	public Long getPharmacyID() {
		return pharmacyID;
	}


	public void setPharmacyID(Long pharmacyID) {
		this.pharmacyID = pharmacyID;
	}


	public void setCounselingId(Long counselingId) {
		this.counselingId = counselingId;
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

	public String getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}


	public String getStartTimeText() {
		return startTimeText;
	}


	public void setStartTimeText(String startTimeText) {
		this.startTimeText = startTimeText;
	}


	public String getEndTimeText() {
		return endTimeText;
	}


	public void setEndTimeText(String endTimeText) {
		this.endTimeText = endTimeText;
	}
	
	
}
