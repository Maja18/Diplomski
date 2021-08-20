package ISA.Team22.Domain.DTO;

import java.util.Date;

public class DataForCalendarDTO {
	
	private Long id;
	private Long patientId;
	private Date start;
	private Date startTime;
	private Date endTime;
	private Long duration;
	private String pharmacyName;
	private String patientInfo;
	
	public DataForCalendarDTO() {
		super();
	}


	public DataForCalendarDTO(Long id, Long patientId, Date start, Date startTime, Date endTime, Long duration,
			String pharmacyName, String patientInfo) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.start = start;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.pharmacyName = pharmacyName;
		this.patientInfo = patientInfo;
	}
	public DataForCalendarDTO(Long id,Date start, Date startTime, Date endTime, Long duration,String pharmacyName) {
		super();
		this.id = id;
		this.start = start;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.pharmacyName = pharmacyName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}

	

	
}
