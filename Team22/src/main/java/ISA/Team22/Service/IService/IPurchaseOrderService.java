package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.DrugOrderDTO;
import ISA.Team22.Domain.DTO.OrdersReviewDTO;
import ISA.Team22.Domain.Pharmacy.PurchaseOrder;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;

public interface IPurchaseOrderService {
	List<PurchaseOrder> findAllOrders();
	List<OrdersReviewDTO> findOrders(List<PurchaseOrder> purchaseOrders, List<OrdersReviewDTO> ordersReviewDTOs);
	List<DrugOrderDTO> findDrugs(List<PurchaseOrderDrug> drugOrderDTOs);
}
