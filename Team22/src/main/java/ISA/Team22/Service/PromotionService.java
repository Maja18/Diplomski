package ISA.Team22.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.PharmacyBasicDTO;
import ISA.Team22.Domain.DTO.PharmacySubsribedDTO;
import ISA.Team22.Domain.DTO.PromotionDTO;
import ISA.Team22.Domain.DTO.PromotionPeriodDTO;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.PharmacyWorkflow.Period;
import ISA.Team22.Domain.PharmacyWorkflow.Promotion;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Domain.Users.Person;
import ISA.Team22.Repository.PatientRepository;
import ISA.Team22.Repository.PromotionRepository;
import ISA.Team22.Service.IService.IPromotionService;


@Service
public class PromotionService implements IPromotionService {

	private final PromotionRepository promotionRepository;
	private final PatientRepository patientRepository;
	private final EmailService emailServices;
	
    public PromotionService(PromotionRepository promotionRepository,PatientRepository patientRepository,EmailService emailServices) {
		this.promotionRepository = promotionRepository;
		this.patientRepository = patientRepository;
		this.emailServices = emailServices;
	}

	@Override
	public List<Promotion> findAllPromotions() {
		return promotionRepository.findAll();
	}
	
	@Override
	public List<PharmacySubsribedDTO> findSubscriptionsInPharmacy() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		 Person person = (Person)currentUser.getPrincipal();
		 Patient patient = patientRepository.findById(person.getId()).get();
		 List<Pharmacy> pharmacies = patient.getSubscribedToPharmacies();
		 List<PharmacySubsribedDTO> pharmacySubsribedDTOs = new ArrayList<PharmacySubsribedDTO>();
		 PharmacySubsribedDTO dto;
		 for (Pharmacy p : pharmacies) {
			 dto = new PharmacySubsribedDTO(p.getId(), p.getName());
			 pharmacySubsribedDTOs.add(dto);
			}
		return pharmacySubsribedDTOs;
	}
	
	@Override
	public List<PromotionDTO> findPromotions(PharmacyBasicDTO pharmacyBasicDTO) {
		List<Promotion> promotions = findAllPromotions();
        List<PromotionDTO> promotionDtos = new ArrayList<PromotionDTO>();
        for (Promotion promotion : promotions) {
        	
            	if(promotion.getPharmacy().getId().equals(pharmacyBasicDTO.getId())) {
            		Period p = promotion.getPeriod();
            		PromotionPeriodDTO periodDTO = new PromotionPeriodDTO();
            		periodDTO.setStartDate(p.getStartDate());
            		periodDTO.setEndDate(p.getEndDate());
            		PromotionDTO dto = new PromotionDTO(promotion.getId(), promotion.getDescription(),promotion.getPharmacy().getId(),periodDTO);
            		promotionDtos.add(dto);
            	}
			
			
		}
		return promotionDtos;
	}
	
	@Override
	 public boolean subsribeToPharmacy(Pharmacy pharmacy) {
		 Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
	     Person person = (Person)currentUser.getPrincipal();
         Patient patient = patientRepository.findById(person.getId()).get();
         List<Pharmacy> pharmacies = patient.getSubscribedToPharmacies();
         for (Pharmacy p : pharmacies) {
			if(p.getId().equals(pharmacy.getId())) {
				System.out.println("You are already subsrcibed to this pharmacy!");
				throw new IllegalArgumentException("You are already subsrcibed to this pharmacy!");
				 
			}
		}
         pharmacies.add(pharmacy);
         patientRepository.save(patient); 
         this.emailServices.sendNotificationAsync(patient.getEmail(), "Promotion INFO", ""
					+ "You are now subsribed to " + pharmacy.getName().toString() +  ".");
         
         return true;
	     
	 }
	 
	 @Override
	 public boolean unsubsribeToPharmacy(Pharmacy pharmacy) {
		 Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
	     Person person = (Person)currentUser.getPrincipal();
         Patient patient = patientRepository.findById(person.getId()).get();
         List<Pharmacy> pharmacies = new ArrayList<>(patient.getSubscribedToPharmacies());
         boolean deleted = false;
        
         for (Iterator<Pharmacy> itr = patient.getSubscribedToPharmacies().iterator(); itr.hasNext();) {
        	 Pharmacy p =  (Pharmacy) itr.next();
			if(p.getId().equals(pharmacy.getId())) {
				itr.remove();
				deleted = true;  
			}
         }
         
         patientRepository.save(patient); 
         this.emailServices.sendNotificationAsync(patient.getEmail(), "Promotion INFO", ""
					+ "You will no longer receive promotions from pharmacy  " + pharmacy.getName().toString() +  ".");
         
         return true;
	 }

}
