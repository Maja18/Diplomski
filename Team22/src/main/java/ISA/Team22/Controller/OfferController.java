package ISA.Team22.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ISA.Team22.Domain.DTO.DrugOrderDTO;
import ISA.Team22.Domain.DTO.OfferDTO;
import ISA.Team22.Domain.DTO.OfferInfoDTO;
import ISA.Team22.Domain.Pharmacy.Offer;
import ISA.Team22.Domain.Pharmacy.PurchaseOrder;
import ISA.Team22.Domain.Pharmacy.PurchaseOrderStatus;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Supplier;
import ISA.Team22.Repository.OfferRepository;
import ISA.Team22.Repository.SupplierRepository;
import ISA.Team22.Service.OfferService;
import ISA.Team22.Service.SupplierService;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferController {

	private final OfferService offerService;
	
	private final SupplierService supplierService;
	
	private final SupplierRepository supplierRepository;
	
	@Autowired
	public OfferController(OfferService offerService, SupplierService supplierService,SupplierRepository supplierRepository) {
		this.offerService = offerService;
		this.supplierService = supplierService;
		this.supplierRepository = supplierRepository;
	}
	
	@GetMapping("/myOffers")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<List<OfferInfoDTO>> getMyActiveOffers()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(person.getId());
        List<OfferInfoDTO> offersDto = offerService.getSupplierOffersInfoDTOS(supplier);

        return (ResponseEntity<List<OfferInfoDTO>>) (offersDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offersDto));
    }
	
	
	
	@GetMapping("/myOffers/{offerStatus}")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<List<OfferInfoDTO>> getMyActiveOffersByStatus(@PathVariable String offerStatus)
    {	
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(person.getId());
        List<OfferInfoDTO> offersDto = offerService.getOffersInfoDTOSByStatus(supplier,offerStatus);

        return offersDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offersDto);
    }

   
    
    @PostMapping("/addOffer")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> addOffer(@RequestBody OfferDTO offerDTO)
    {
        Offer offer = offerService.sendOffer(offerDTO);
       
        return offer == null  ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offer);
    }
    
    @PostMapping("/changeOffer")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> changeOffer(@RequestBody OfferDTO offerDTO)
    {
    	Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person)currentUser.getPrincipal();
        Supplier supplier = supplierRepository.findById(person.getId()).get();
        offerDTO.setSupplierId(supplier.getId());
   
        if(offerService.isOfferPossible(offerDTO, supplier)) {
        	Offer offer = offerService.changeOffer(offerDTO);
            return offer == null  ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(offer);
        }
        return null;
        
    }
}
