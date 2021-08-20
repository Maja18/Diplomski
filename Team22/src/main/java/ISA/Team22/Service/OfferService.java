package ISA.Team22.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.DrugOrderDTO;
import ISA.Team22.Domain.DTO.OfferDTO;
import ISA.Team22.Domain.DTO.OfferInfoDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.SupplierDrugStock;
import ISA.Team22.Domain.Pharmacy.Offer;
import ISA.Team22.Domain.Pharmacy.OfferStatus;
import ISA.Team22.Domain.Pharmacy.PurchaseOrder;
import ISA.Team22.Domain.Pharmacy.PurchaseOrderStatus;
import ISA.Team22.Domain.PharmacyWorkflow.PurchaseOrderDrug;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Domain.Users.Supplier;
import ISA.Team22.Repository.OfferRepository;
import ISA.Team22.Repository.PurchaseOrderRepository;
import ISA.Team22.Repository.SupplierRepository;
import ISA.Team22.Service.IService.IOfferService;

@Service
public class OfferService implements IOfferService {

	private  final OfferRepository offerRepository;
	
	private final SupplierDrugStockService supplierDrugStockService;
	
	private final SupplierRepository supplierRepository;
	
	private final PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	public OfferService(OfferRepository offerRepository,SupplierDrugStockService supplierDrugStockService,
			SupplierRepository supplierRepository,PurchaseOrderRepository purchaseOrderRepository) {
		this.offerRepository = offerRepository;
		this.supplierDrugStockService = supplierDrugStockService;
		this.supplierRepository = supplierRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
	}
	
	@Override
	public Offer sendOffer(OfferDTO offerDTO) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person)currentUser.getPrincipal();
        Supplier supplier = supplierRepository.findById(person.getId()).get();
        if(offerDTO.getDeliveryDate().isAfter(offerDTO.getDueDate()))
    		throw new IllegalArgumentException("Date is incorrect!");
        
        offerDTO.setSupplierId(supplier.getId());
        Boolean canMakeOffer = isOfferPossible(offerDTO,supplier);
        
        if(canMakeOffer)  {
            Boolean amountUpdated = supplierDrugStockService.updateAmount(offerDTO);
            Offer offer = save(offerDTO);
            if(offer == null || !amountUpdated || !canMakeOffer) {
            	return null;
            	}
            
            return offer;
        }
        return null;

    }
	
	@Override
	public Boolean isOfferPossible(OfferDTO offerDTO, Supplier supplier) {
		if(offerDTO.getPrice() <= 0 ) {
			throw new IllegalArgumentException("Price must be positive!");
		}
		if(offerDTO.isOfferGiven() == true) {
			throw new IllegalArgumentException("You have already made an offer for this order!");
		}
		
		if(offerDTO.getDeliveryDate().isAfter(offerDTO.getDueDate()) || offerDTO.getDeliveryDate() == null ) {
        	throw new IllegalArgumentException("Delivery date is passed!");
        }
		
        PurchaseOrder purchaseOrder ;
        try {
        	purchaseOrder = purchaseOrderRepository.findById(offerDTO.getOrderId()).get();
		} catch (Exception e) {
			throw new IllegalArgumentException("There is no order with that id!");
		}
       
        List<SupplierDrugStock> supplierDrugStocks = supplier.getDrugStocks();  
        if(!areDrugsAvailable(purchaseOrder, supplierDrugStocks)) {
        	throw new IllegalArgumentException("You don't have enough drugs in stock");
        }
        
        if(!offerDTO.getDeliveryDate().isAfter(LocalDate.now()) || purchaseOrder.getPurchaseOrderStatus().equals(PurchaseOrderStatus.closed)) {
            throw new IllegalArgumentException("Creating offer is not possible. Order is closed.");
        }
       
        return true;
	}

	@Override
	public Boolean areDrugsAvailable(PurchaseOrder purchaseOrder, List<SupplierDrugStock> supplierDrugStocks) {
		for (PurchaseOrderDrug drug : purchaseOrder.getPurchaseOrderDrugs()) {
			for (SupplierDrugStock supplierDrugStock : supplierDrugStocks) {
				if(drug.getDrug().getName().equals(supplierDrugStock.getName()) && (drug.getAmount() <= supplierDrugStock.getQuantity())){
					return true;
				}
			}
		}
		 return false;
	}

	@Override
	public Offer save(OfferDTO offerDTO) {
		Offer offer = new Offer();
		OfferStatus status = null ;
		offer.setSupplier(supplierRepository.findById(offerDTO.getSupplierId()).get());
		offer.setDeliveryTime(offerDTO.getDeliveryDate());
		offer.setPurchaseOrder(purchaseOrderRepository.findById(offerDTO.getOrderId()).get());
        offer.setTotalPrice(offerDTO.getPrice());
        offer.setOfferStatus(status.created);
        offerDTO.setOfferGiven(true);
        
        return offerRepository.save(offer);
        
	}
	
	
	@Override
	public Offer changeOffer(OfferDTO offerDTO) {
		try {
			Offer offer = findById(offerDTO.getOfferId());
			offer.setTotalPrice(offerDTO.getPrice());
			offer.setDeliveryTime(offerDTO.getDeliveryDate());
			
			return offerRepository.save(offer);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return null;	
	}

	@Override
	public Offer findById(Long id) {
		return  offerRepository.findById(id).get();
	}
	
	@Override
	public List<DrugOrderDTO> getDrugsOrder(List<PurchaseOrderDrug> purchaseOrderDrugs) {
        List<DrugOrderDTO> drugs = new ArrayList<>();
        for (PurchaseOrderDrug drug: purchaseOrderDrugs) {
        	drugs.add(new DrugOrderDTO(drug.getId(), drug.getDrug().getName(),
        			drug.getDrug().getCode(),drug.getDrug().getDrugForm(),
        			drug.getDrug().getDrugType(),drug.getAmount()));
        }
        return drugs;
    }
	
	@Override
	public List<OfferInfoDTO> getSupplierOffersInfoDTOS(Supplier supplier) {
        List<OfferInfoDTO> supplierOffersDto = new ArrayList<>();
        List<Offer> offers =  supplier.getOffers();
        for (Offer o: offers) {
            PurchaseOrder order = o.getPurchaseOrder();
            if(order.getDueDate().isAfter(LocalDate.now()) && o.getDeliveryTime().isAfter(LocalDate.now()) && order.getPurchaseOrderStatus().equals(PurchaseOrderStatus.processed))
            {
                supplierOffersDto.add(new OfferInfoDTO(o.getId(), order.getId(), o.getDeliveryTime(), o.getTotalPrice(),
                        order.getDueDate(), getDrugsOrder(order.getPurchaseOrderDrugs()), order.getPharmacyAdministrator().getPharmacy().getName(),true, o.getOfferStatus()));
            }
            else {
                supplierOffersDto.add(new OfferInfoDTO(o.getId(), order.getId(), o.getDeliveryTime(), o.getTotalPrice(),
                        order.getDueDate(),getDrugsOrder(order.getPurchaseOrderDrugs()), order.getPharmacyAdministrator().getPharmacy().getName(),false,o.getOfferStatus()));
            }

        }

        return supplierOffersDto;
    }
	
	@Override
	 public List<OfferInfoDTO> getOffersInfoDTOSByStatus(Supplier supplier, String offerStatus) {
	        List<OfferInfoDTO> supplierOffersDto = new ArrayList<>();
	        List<Offer> offers =  supplier.getOffers();
	        for (Offer o: offers) {
	            if(o.getOfferStatus().toString().equals(offerStatus)) {
	                PurchaseOrder order = o.getPurchaseOrder();
	                if(order.getDueDate().isAfter(LocalDate.now()) && !order.getPurchaseOrderStatus().equals(PurchaseOrderStatus.closed))
	                {
	                	 supplierOffersDto.add(new OfferInfoDTO(o.getId(), order.getId(), o.getDeliveryTime(), o.getTotalPrice(),
	                             order.getDueDate(), getDrugsOrder(order.getPurchaseOrderDrugs()), order.getPharmacyAdministrator().getPharmacy().getName(),true, o.getOfferStatus()));
	                 }
	                else {
	                	supplierOffersDto.add(new OfferInfoDTO(o.getId(), order.getId(), o.getDeliveryTime(), o.getTotalPrice(),
	                            order.getDueDate(), getDrugsOrder(order.getPurchaseOrderDrugs()), order.getPharmacyAdministrator().getPharmacy().getName(),false, o.getOfferStatus()));
	                }
	            }

	        }
	       
	        return supplierOffersDto;
	    }

	
}
