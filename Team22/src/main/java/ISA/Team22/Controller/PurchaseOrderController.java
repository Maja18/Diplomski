package ISA.Team22.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ISA.Team22.Domain.DTO.DrugOrderDTO;
import ISA.Team22.Domain.DTO.OrdersReviewDTO;
import ISA.Team22.Domain.Pharmacy.PurchaseOrder;
import ISA.Team22.Domain.Pharmacy.PurchaseOrderStatus;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;
import ISA.Team22.Service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchaseOrder")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseOrderController {
	
	private final PurchaseOrderService purchaseOrderService;
	
	@Autowired
	public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}
	
	@GetMapping("activeOrders")
    @PreAuthorize("hasRole('SUPPLIER')")
	ResponseEntity<List<OrdersReviewDTO>> getActiveOrders(){
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findAllOrders();
		List<OrdersReviewDTO> ordersReviewDTOs = new ArrayList<OrdersReviewDTO>();
		ordersReviewDTOs = purchaseOrderService.findOrders(purchaseOrders, ordersReviewDTOs);
		
		 return ordersReviewDTOs == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(ordersReviewDTOs);
	}

	
}
