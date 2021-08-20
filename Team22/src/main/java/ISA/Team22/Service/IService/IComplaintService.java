package ISA.Team22.Service.IService;

import java.util.List;

import ISA.Team22.Domain.DTO.ComplaintDTO;
import ISA.Team22.Domain.DTO.ComplaintReviewDTO;
import ISA.Team22.Domain.PharmacyWorkflow.Complaint;

public interface IComplaintService {
	Complaint save(ComplaintDTO complaintDTO);
	List<Complaint> findAll();
	Complaint updateComplaint(ComplaintReviewDTO complaintReviewDTO);
	void findComplaints(List<Complaint> complaints, List<ComplaintReviewDTO> complaintDTOs);

}
