package ISA.Team22.Controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.SupplierDrugUpdateDTO;
import ISA.Team22.Domain.DTO.SupplierDrugsDTO;
import ISA.Team22.Domain.DrugManipulation.SupplierDrugStock;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Supplier;
import ISA.Team22.Service.SupplierDrugStockService;
import ISA.Team22.Service.SupplierService;

@RestController
@RequestMapping("/api/supplierDrugs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupplierDrugStockController {
	
	
	private final SupplierDrugStockService supplierDrugStockService;
	
	private final SupplierService supplierService;
	
	@Autowired
	public SupplierDrugStockController(SupplierDrugStockService supplierDrugStockService, SupplierService supplierService) {
		this.supplierDrugStockService = supplierDrugStockService;
		this.supplierService = supplierService;
	}
	
	@GetMapping("")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<List<SupplierDrugsDTO>> getAllDrugs()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(person.getId());
        List<SupplierDrugsDTO> supplierDrugsDTOS = supplierDrugStockService.findSupplierDrugs(supplier);
        
        return supplierDrugsDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(supplierDrugsDTOS);
    }
	
	@PostMapping("/updateDrug")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<String> updateDrug(@RequestBody SupplierDrugUpdateDTO supplierDrugUpdateDTO) {
        if(supplierDrugUpdateDTO.getNewQuantity() < 0) {
        	throw new IllegalArgumentException("Quantity must be positive!");
        }
        SupplierDrugStock drugStock= supplierDrugStockService.update(supplierDrugUpdateDTO);
        
        return drugStock == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Drug is successfully added!", HttpStatus.CREATED);
    }
}
