package ISA.Team22.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.DTO.PrescriptionDTO;
import ISA.Team22.Domain.DrugManipulation.Drug;
import ISA.Team22.Domain.DrugManipulation.DrugInfo;
import ISA.Team22.Domain.Examination.Prescription;
import ISA.Team22.Domain.Examination.PrescriptionStatus;
import ISA.Team22.Domain.Pharmacy.Pharmacy;
import ISA.Team22.Domain.Users.Patient;
import ISA.Team22.Repository.PrescriptionRepository;
import ISA.Team22.Service.IService.IPrescriptionService;

@Service
public class PrescriptionService implements IPrescriptionService {

	private final PrescriptionRepository prescriptionRepository;
	private final PatientService patientService;
	private final PharmacyService pharmacyService;
	private final DrugService drugService;
	
	
	@Autowired
	public PrescriptionService(PrescriptionRepository prescriptionRepository, PatientService patientService,
			PharmacyService pharmacyService, DrugService drugService) {
		super();
		this.prescriptionRepository = prescriptionRepository;
		this.patientService = patientService;
		this.pharmacyService = pharmacyService;
		this.drugService = drugService;
	}


	@Override
	public void prescribeDrug(PrescriptionDTO prescriptionDTO) {
		Prescription prescription = new Prescription();
		Drug drug = drugService.findById(prescriptionDTO.getDrugId());
		List<Drug> drugs = new ArrayList<>();
		drugs.add(drug);
		Patient patient = patientService.findById(prescriptionDTO.getPatientId());
		Pharmacy pharmacy = pharmacyService.findById(prescriptionDTO.getPharmacyId());
		
		prescription.setAmountOfDrug(prescriptionDTO.getAmountOfDrug());
		prescription.setDurationOfTherapy(prescriptionDTO.getDurationOfTherapy());
		prescription.setDrugs(drugs);
		prescription.setPatient(patient);
		prescription.setPharmacy(pharmacy);
		prescription.setPrescriptionDate(LocalDate.now());
		prescription.setPrescriptionStatus(PrescriptionStatus.issued);
		
		prescriptionRepository.save(prescription);
		
		List<DrugInfo> drugInfos =  pharmacy.getPharmacyInventory().getDrugInfos();
		
		List<Prescription> patientPrescriptions = patient.getPrescription();
		patientPrescriptions.add(prescription);
		patient.setPrescription(patientPrescriptions);
		
		for(DrugInfo d:drugInfos)
			if(d.getDrug().getId().equals(prescriptionDTO.getDrugId())){
					int drugAmount = d.getDrugAmount();
					d.setDrugAmount(drugAmount--);
			}
	}



}
