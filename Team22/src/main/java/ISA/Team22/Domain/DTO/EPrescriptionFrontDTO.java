package ISA.Team22.Domain.DTO;

import java.util.List;

public class EPrescriptionFrontDTO {
	private Long pharmacyId;
	private List<QRCodeDTO> qrCodeDrugs;
	private String code;
	
	public EPrescriptionFrontDTO() {
		super();
	}
	
	public EPrescriptionFrontDTO(Long pharmacyId, List<QRCodeDTO> qrCodeDrugs) {
		super();
		this.pharmacyId = pharmacyId;
		this.qrCodeDrugs = qrCodeDrugs;
	}
	
	public EPrescriptionFrontDTO(Long pharmacyId, List<QRCodeDTO> qrCodeDrugs, String code) {
		super();
		this.pharmacyId = pharmacyId;
		this.qrCodeDrugs = qrCodeDrugs;
		this.code = code;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}
	
	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
	public List<QRCodeDTO> getQrCodeDrugs() {
		return qrCodeDrugs;
	}
	
	public void setQrCodeDrugs(List<QRCodeDTO> qrCodeDrugs) {
		this.qrCodeDrugs = qrCodeDrugs;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
