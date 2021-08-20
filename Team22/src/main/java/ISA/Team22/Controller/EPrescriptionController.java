package ISA.Team22.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import ISA.Team22.Domain.DTO.AddressDTO;
import ISA.Team22.Domain.DTO.EPrescriptionDTO;
import ISA.Team22.Domain.DTO.PharmacyDrugAvailabilityDTO;
import ISA.Team22.Domain.DTO.QRCodeDTO;
import ISA.Team22.Domain.DrugManipulation.DrugInfo;
import ISA.Team22.Domain.Examination.EPrescription;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.Users.City;
import ISA.Team22.Domain.Users.Country;
import ISA.Team22.Service.EPrescriptionService;
import ISA.Team22.Service.PharmacyService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/erecipes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EPrescriptionController {

	private final EPrescriptionService ePrescriptionService;
	
	
	@Autowired
	public EPrescriptionController(EPrescriptionService ePrescriptionService) {
		this.ePrescriptionService = ePrescriptionService;
	}
	
	@PostMapping("/file")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<EPrescriptionDTO> searchDrugsBasedOnQRCode(@RequestParam("file") MultipartFile file) throws IOException, NotFoundException {

        if (!file.isEmpty()) {
            try {
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File("src/main/resources/qrCodes/" + file.getOriginalFilename());
                ImageIO.write(src, "png", destination);
                String decodedText = ePrescriptionService.decodeQRCode(new File("src/main/resources/qrCodes/" + file.getOriginalFilename()));
  
                if (decodedText == null) {
                    throw new IllegalArgumentException("Please upload valid QR code!");
                } else {
                    String code = ePrescriptionService.getEReceiptCode(decodedText);
                    EPrescription ePrescription = ePrescriptionService.findByCode(code);
                    if(ePrescription!=null) {
                        throw new IllegalArgumentException("This ePrescription is already used!");
                    }
                    
                    List<QRCodeDTO> qrCodeDrugs = ePrescriptionService.getDrugsInQRcode(decodedText);
                    if(qrCodeDrugs==null) {
                        throw new IllegalArgumentException("Please try later, there are no drugs!");
                    }
                    
                    List<PharmacyDrugAvailabilityDTO> pharmacyDrugAvailabilityDTOs = ePrescriptionService.getAvailabilityInPharmacies(qrCodeDrugs);
                    EPrescriptionDTO ePrescriptionDTO = new EPrescriptionDTO(qrCodeDrugs,code,pharmacyDrugAvailabilityDTOs);
                    return pharmacyDrugAvailabilityDTOs == null ?
                            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                            ResponseEntity.ok(ePrescriptionDTO);

                }
            } catch (IOException | NotFoundException e) {
                throw new IllegalArgumentException("Please upload valid QR code!");}
        }
        throw new IllegalArgumentException("Please upload valid QR code!");
    }
	
	
}
