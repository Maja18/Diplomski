package ISA.Team22.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.query.criteria.internal.expression.function.LengthFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ISA.Team22.Domain.DTO.OfferDTO;
import ISA.Team22.Domain.DTO.OfferInfoDTO;
import ISA.Team22.Domain.DTO.PharmacyBasicDTO;
import ISA.Team22.Domain.DTO.PharmacyPromotionDTO;
import ISA.Team22.Domain.DTO.PharmacySubsribedDTO;
import ISA.Team22.Domain.DTO.PromotionDTO;
import ISA.Team22.Domain.DTO.PromotionPeriodDTO;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.Period;
import ISA.Team22.Domain.PharmacyWorkflow.Promotion;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Supplier;
import ISA.Team22.Service.PatientService;
import ISA.Team22.Service.PharmacyService;
import ISA.Team22.Service.PromotionService;

@RestController
@RequestMapping("/api/promotion")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromotionController {

	private final PromotionService promotionService;
	private final PharmacyService pharmacyService;
	
	@Autowired
	public PromotionController(PromotionService promotionService,PharmacyService pharmacyService) {
		this.promotionService = promotionService;
		this.pharmacyService = pharmacyService;
	}
	
	
	@PostMapping("/promotions")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<PromotionDTO>> getPromotions(@RequestBody PharmacyBasicDTO pharmacyBasicDTO)
    {
        List<PromotionDTO> promotionDtos = promotionService.findPromotions(pharmacyBasicDTO);
       
        return promotionDtos == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(promotionDtos);
    }
	
	 @PostMapping("/subscribe")
	 @PreAuthorize("hasRole('PATIENT')")
	 public ResponseEntity<String> subsribe(@RequestBody PharmacyPromotionDTO pharmacyPromotionDTO)
	 {
	     Pharmacy pharmacy;
	       
	     try {
	        pharmacy= pharmacyService.findById(pharmacyPromotionDTO.getPharmacyId());
	     }
	     catch(Exception e) {
	        throw new IllegalArgumentException("This pharmacy doesn't exist!");
	     }
	        
	     return promotionService.subsribeToPharmacy(pharmacy) == false ?
	                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                    ResponseEntity.ok("Patient is now subscribed");
	  }
	 
	 @GetMapping("/subscriptions")
	 @PreAuthorize("hasRole('PATIENT')")
	 public ResponseEntity<List<PharmacySubsribedDTO>> getMySubscriptions()
	 {
		 List<PharmacySubsribedDTO> pharmacySubsribedDTOs = promotionService.findSubscriptionsInPharmacy();

	     return pharmacySubsribedDTOs == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(pharmacySubsribedDTOs);
	  }
	 
	 @PostMapping("/canclePromotion")
	 @PreAuthorize("hasRole('PATIENT')")
	 public ResponseEntity<String> cancleSubscription(@RequestBody PharmacySubsribedDTO pharmacySubsribedDTO){
		 Pharmacy pharmacy;
	       
	     try {
	        pharmacy= pharmacyService.findById(pharmacySubsribedDTO.getPharmacyId());
	     }
	     catch(Exception e) {
	        throw new IllegalArgumentException("This pharmacy doesn't exist!");
	     }
	     
	     return promotionService.unsubsribeToPharmacy(pharmacy) == false ?
                 new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                 ResponseEntity.ok("Patient is now unsubscribed");
		 
	 }
}
