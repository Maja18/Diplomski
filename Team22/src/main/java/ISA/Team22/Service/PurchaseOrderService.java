package ISA.Team22.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.DrugOrderDTO;
import ISA.Team22.Domain.DTO.OrdersReviewDTO;
import ISA.Team22.Domain.Pharmacy.PurchaseOrder;
import ISA.Team22.Domain.Pharmacy.PurchaseOrderStatus;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;
import ISA.Team22.Repository.PurchaseOrderRepository;
import ISA.Team22.Service.IService.IPurchaseOrderService;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

	private final PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
		this.purchaseOrderRepository = purchaseOrderRepository;
	}

	@Override
	public List<PurchaseOrder> findAllOrders() {
		return purchaseOrderRepository.findAll();
	}
	
	@Override
	public List<OrdersReviewDTO> findOrders(List<PurchaseOrder> purchaseOrders, List<OrdersReviewDTO> ordersReviewDTOs) {
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			if(purchaseOrder.getDueDate().isAfter(LocalDate.now()) && !purchaseOrder.getPurchaseOrderStatus().equals(PurchaseOrderStatus.closed)){
				OrdersReviewDTO dto = new OrdersReviewDTO(purchaseOrder.getId(), purchaseOrder.getDueDate(), purchaseOrder.getPurchaseOrderStatus().toString(), findDrugs(purchaseOrder.getPurchaseOrderDrugs()),
						purchaseOrder.getPharmacyAdministrator().getPharmacy().getName());
				ordersReviewDTOs.add(dto);
			}
		}
		
		return ordersReviewDTOs;
	}

	@Override
	public List<DrugOrderDTO> findDrugs(List<PurchaseOrderDrug> drugOrderDTOs) {
		List<DrugOrderDTO> drugs = new ArrayList<>();
		for (PurchaseOrderDrug d : drugOrderDTOs) {
			DrugOrderDTO orderDTO = new DrugOrderDTO(d.getId(), d.getDrug().getName(), d.getDrug().getCode(), 
					d.getDrug().getDrugForm(), d.getDrug().getDrugType(), d.getAmount());
			drugs.add(orderDTO);
		}
		
		return drugs;
	}
}
