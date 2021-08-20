package ISA.Team22.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.EPrescriptionFrontDTO;
import ISA.Team22.Domain.DTO.PharmacyDrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.QRCodeDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugInfo;
import ISA.Team22.Domain.Examination.EPrescription;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Repository.EPrescriptionRepository;
import ISA.Team22.Service.IService.IEPrescriptionService;
import javassist.NotFoundException;

@Service
public class EPrescriptionService implements IEPrescriptionService  {
	
	
	private  final EPrescriptionRepository ePrescriptionRepository;
	private final PharmacyService pharmacyService;
	private final DrugService drugService;
	
	
	@Autowired
	public EPrescriptionService(EPrescriptionRepository ePrescriptionRepository,PharmacyService pharmacyService,DrugService drugService) {
		this.ePrescriptionRepository = ePrescriptionRepository;
		this.pharmacyService = pharmacyService;
		this.drugService = drugService;
	}

	@Override
	public EPrescription findByCode(String code) {
		return ePrescriptionRepository.findByCode(code);
	}
	
	@Override
	public String getEReceiptCode(String decodedText) {
        String []code = decodedText.split("!");
        return code[0];
    }
	
	public static String decodeQRCode(File qrCodeimage) throws IOException, NotFoundException {
	        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
	        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
	        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

	        try {
	            Result result = new MultiFormatReader().decode(bitmap);
	            return result.getText();
	        } catch ( com.google.zxing.NotFoundException n) {
	            System.out.println("There is no QR code in the image");
	            return null;
	        }
	    }
	 
	@Override
	 public List<QRCodeDTO> getDrugsInQRcode(String decodedText) {
	        List<QRCodeDTO> qrCodeDTOs = new ArrayList<>();
	        if(decodedText.contains(";")) {
	            String []code = decodedText.split("!");
	            String []drugs = code[1].split(";");
	            for (String drug: drugs) {
	                String []drugParts = drug.split("_");
	               
	                qrCodeDTOs.add(new QRCodeDTO(drugParts[0],drugParts[1],Integer.parseInt(drugParts[2])));
	            }
	        }
	        else {
	            String []code = decodedText.split("!");
	            String []drugParts = code[1].split("_");
	            qrCodeDTOs.add(new QRCodeDTO(drugParts[0],drugParts[1],Integer.parseInt(drugParts[2])));
	        }
	        return qrCodeDTOs;
	    }
	
	
	 @Override
	 public List<PharmacyDrugAvailabilityDTO> getAvailabilityInPharmacies(List<QRCodeDTO> qrCodeDrugs) {
	        List<PharmacyDrugAvailabilityDTO> pharmacyList = new ArrayList<>();
	        List<Pharmacy> pharmacies = pharmacyService.findAll();
	        
	        for(Pharmacy pharmacy : pharmacies) {
	        	int i = 0;
	        	List<DrugInfo> drugInfos = pharmacy.getPharmacyInventory().getDrugInfos();
	        	Double sumPrice = 0.0;
	        	for (QRCodeDTO qrCodeDrug : qrCodeDrugs) {	
	        		for (DrugInfo drugInfo : drugInfos){
						if(drugInfo.getDrug().getName().equals(qrCodeDrug.getDrugName()) && drugInfo.getDrug().getCode().equals(qrCodeDrug.getDrugCode()) && drugInfo.getDrugAmount() >= qrCodeDrug.getAmount()) {
							Double price = drugInfo.getPrice();
							sumPrice += price;
							System.out.println(sumPrice);
							i++;
						}else {
							continue;
						}
	        		}
				}
	        	
	        	if(qrCodeDrugs.size() == i) {
	        	AddressDTO address = new AddressDTO(pharmacy.getAddress().getCity().getName(), pharmacy.getAddress().getStreetName(),
	        			pharmacy.getAddress().getStreetNumber(), pharmacy.getAddress().getCity().getCountry().getName());
				
	            pharmacyList.add(new PharmacyDrugAvailabilityDTO(pharmacy.getId(), sumPrice, pharmacy.getAllGrades(), address, pharmacy.getName()));
	        	}
	        }
	        return pharmacyList;
	    }
	 
	 @Override
	 public EPrescription saveEPrescription(Patient patient,EPrescriptionFrontDTO ePrescriptionFrontDTO,Pharmacy pharmacy) {
		    List<QRCodeDTO> drugs = ePrescriptionFrontDTO.getQrCodeDrugs();
			List<DrugInfo> pharmacyDrugs = pharmacy.getPharmacyInventory().getDrugInfos();
			EPrescription ePrescription = new EPrescription();
			List<Drug> drugsForEPrescription = new ArrayList<Drug>();
			for (QRCodeDTO qrDug : drugs) {
				for (DrugInfo drug : pharmacyDrugs) {
					if(qrDug.getDrugCode().equals(drug.getDrug().getCode())) {
						int drugAmount = drug.getDrugAmount() - qrDug.getAmount();
						drug.setDrugAmount(drugAmount);
					    Drug drug1 = drugService.findByCode(qrDug.getDrugCode());
			            ePrescription.setPatient(patient);
			            ePrescription.setCode(ePrescriptionFrontDTO.getCode());
			            ePrescription.setPrescriptionDate(LocalDate.now());
			            ePrescription.setPharmacyId(pharmacy.getId());
			            drugsForEPrescription.add(drug1);
			            ePrescription.setDrugs(drugsForEPrescription);
					}
				}
					
			}
			
			return ePrescriptionRepository.save(ePrescription);
		}
			

}
