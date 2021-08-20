package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.OfferDTO;
import ISA.Team22.Domain.DTO.SupplierDrugUpdateDTO;
import ISA.Team22.Domain.DTO.SupplierDrugsDTO;
import ISA.Team22.Domain.DrugManipulation.SupplierDrugStock;
import ISA.Team22.Domain.Users.Supplier;

public interface ISupplierDrugStockService {
	SupplierDrugStock update(SupplierDrugUpdateDTO supplierDrugUpdateDTO);
	Boolean updateAmount(OfferDTO offerDTO);
	List<SupplierDrugsDTO> findSupplierDrugs(Supplier supplier);
}
