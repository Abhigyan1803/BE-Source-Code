package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.ListenerRetry;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.POPAdjutantBranch;
import com.example.demo.model.ReceptionAdjutantBranch;
import com.example.demo.model.SOPAdjutantBranch;
import com.example.demo.model.ScheduleDrillCompetition;
import com.example.demo.repository.POPAdjutantBranchRepo;
import com.example.demo.repository.ReceptionAdjutantBranchRepo;
import com.example.demo.repository.SOPAdjutantBranchRepo;
import com.example.demo.repository.ScheduleDrillCompetitionRepo;
import com.example.demo.service.AdjutantGeneralInstructionService;
import com.example.demo.util.FileUploader;

@Service
public class AdjutantGeneralInstructionServiceImpl implements AdjutantGeneralInstructionService {
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	ReceptionAdjutantBranchRepo receptionAdjutantBranchRepo ;
	
	@Autowired
	POPAdjutantBranchRepo pOPAdjutantBranchRepo;
	
	@Autowired
	SOPAdjutantBranchRepo sOPAdjutantBranchRepo;
	
	@Autowired
	ScheduleDrillCompetitionRepo scheduleDrillCompetitionRepo;

	
	//========================Reception General Instruction =========================
	
	@Override
	public ReceptionAdjutantBranch addReception(ReceptionAdjutantBranch details, MultipartFile file) {
		if (file != null && !file.isEmpty()) {

			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			details.setDocument(url + filename);
		}
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		
		return receptionAdjutantBranchRepo.save(details);
		
	}

	@Override
	public ReceptionAdjutantBranch updateReception(ReceptionAdjutantBranch details, MultipartFile file) {
		ReceptionAdjutantBranch updated=null;
		ReceptionAdjutantBranch existing = receptionAdjutantBranchRepo.findById(details.getId()).get();
		if(existing != null)
		{
			if (file != null && !file.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				details.setDocument(url + filename);
			}
			details.setCreatedAt(existing.getCreatedAt());
			details.setUpdatedAt(new Date());
			
			updated = receptionAdjutantBranchRepo.save(details);
			return updated;
		}
		return updated;
	}

	@Override
	public ReceptionAdjutantBranch changeReceptionStatus(Long id, int status) {
		ReceptionAdjutantBranch existing = receptionAdjutantBranchRepo.findById(id).get();
		if(existing != null)
		{
			existing.setStatus(status);
			existing.setUpdatedAt(new Date());
			
			return receptionAdjutantBranchRepo.save(existing);
		}
		return null;
		
	}

	@Override
	public ReceptionAdjutantBranch viewReceptionById(Long id) {
		ReceptionAdjutantBranch list = receptionAdjutantBranchRepo.findById(id).get();
		return list;
	}

	@Override
	public List<ReceptionAdjutantBranch> getReceptionList(int status) {
		List<ReceptionAdjutantBranch> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = receptionAdjutantBranchRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = receptionAdjutantBranchRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	
	//========================POP General Instruction =========================
	
	@Override
	public POPAdjutantBranch addPOP(POPAdjutantBranch details, MultipartFile file) {
		if (file != null && !file.isEmpty()) {

			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			details.setDocument(url + filename);
		}
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		
		return pOPAdjutantBranchRepo.save(details);
	}

	@Override
	public POPAdjutantBranch updatePOP(POPAdjutantBranch details, MultipartFile file) {
		POPAdjutantBranch updated=null;
		POPAdjutantBranch existing = pOPAdjutantBranchRepo.findById(details.getId()).get();
		if(existing != null)
		{
			if (file != null && !file.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				details.setDocument(url + filename);
			}
			details.setCreatedAt(existing.getCreatedAt());
			details.setUpdatedAt(new Date());
			
			updated = pOPAdjutantBranchRepo.save(details);
			return updated;
		}
		return updated;
	}

	@Override
	public POPAdjutantBranch changePOPStatus(Long id, int status) {
		POPAdjutantBranch existing = pOPAdjutantBranchRepo.findById(id).get();
		if(existing != null)
		{
			existing.setStatus(status);
			existing.setUpdatedAt(new Date());
			
			return pOPAdjutantBranchRepo.save(existing);
		}
		return null;
	}

	@Override
	public POPAdjutantBranch viewPOPById(Long id) {
		POPAdjutantBranch list = pOPAdjutantBranchRepo.findById(id).get();
		return list;
	}

	@Override
	public List<POPAdjutantBranch> getPOPList(int status) {
		List<POPAdjutantBranch> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = pOPAdjutantBranchRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = pOPAdjutantBranchRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	//========================POP General Instruction =========================
	
	@Override
	public SOPAdjutantBranch addSOP(SOPAdjutantBranch details, MultipartFile file) {
		if (file != null && !file.isEmpty()) {

			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			details.setDocument(url + filename);
		}
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		
		return sOPAdjutantBranchRepo.save(details);
	}

	@Override
	public SOPAdjutantBranch updateSOP(SOPAdjutantBranch details, MultipartFile file) {
		SOPAdjutantBranch updated=null;
		SOPAdjutantBranch existing = sOPAdjutantBranchRepo.findById(details.getId()).get();
		if(existing != null)
		{
			if (file != null && !file.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				details.setDocument(url + filename);
			}
			details.setCreatedAt(existing.getCreatedAt());
			details.setUpdatedAt(new Date());
			
			updated = sOPAdjutantBranchRepo.save(details);
			return updated;
		}
		return updated;
	}

	@Override
	public SOPAdjutantBranch changeSOPStatus(Long id, int status) {
		SOPAdjutantBranch existing = sOPAdjutantBranchRepo.findById(id).get();
		if(existing != null)
		{
			existing.setStatus(status);
			existing.setUpdatedAt(new Date());
			
			return sOPAdjutantBranchRepo.save(existing);
		}
		return null;
	}

	@Override
	public SOPAdjutantBranch viewSOPById(Long id) {
		SOPAdjutantBranch list = sOPAdjutantBranchRepo.findById(id).get();
		return list;
	}

	@Override
	public List<SOPAdjutantBranch> getSOPList(int status) {
		List<SOPAdjutantBranch> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = sOPAdjutantBranchRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = sOPAdjutantBranchRepo.findAllByOrderByIdDesc();
		}
		return list;
	}
//==============================Schedule drill competition=================================
	@Override
	public ScheduleDrillCompetition addSchedule(ScheduleDrillCompetition details, MultipartFile file) {
		if (file != null && !file.isEmpty()) {

			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			details.setDocument(url + filename);
		}
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		
		return scheduleDrillCompetitionRepo.save(details);
	}

	@Override
	public ScheduleDrillCompetition updateSchedule(ScheduleDrillCompetition details, MultipartFile file) {
		ScheduleDrillCompetition updated=null;
		ScheduleDrillCompetition existing = scheduleDrillCompetitionRepo.findById(details.getId()).get();
		if(existing != null)
		{
			if (file != null && !file.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				details.setDocument(url + filename);
			}
			details.setCreatedAt(existing.getCreatedAt());
			details.setUpdatedAt(new Date());
			
			updated = scheduleDrillCompetitionRepo.save(details);
			return updated;
		}
		return updated;
	}

	@Override
	public ScheduleDrillCompetition changeScheduleStatus(Long id, int status) {
		ScheduleDrillCompetition existing = scheduleDrillCompetitionRepo.findById(id).get();
		if(existing != null)
		{
			existing.setStatus(status);
			existing.setUpdatedAt(new Date());
			
			return scheduleDrillCompetitionRepo.save(existing);
		}
		return null;
	}

	@Override
	public ScheduleDrillCompetition viewScheduleById(Long id) {
		ScheduleDrillCompetition list =scheduleDrillCompetitionRepo.findById(id).get();
		return list;
	}

	@Override
	public List<ScheduleDrillCompetition> getScheduleList(int status) {
		List<ScheduleDrillCompetition> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = scheduleDrillCompetitionRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = scheduleDrillCompetitionRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public List<ScheduleDrillCompetition> getScheduleByTypeAndStatus(String type, int status) {
		// TODO Auto-generated method stub
		//List<ScheduleDrillCompetition> list = new ArrayList<>();
		List<ScheduleDrillCompetition> list = scheduleDrillCompetitionRepo.findByTypeAndStatusOrderByIdDesc(type,status);
		if(status == 1 || status == 0)
		{
		list = scheduleDrillCompetitionRepo.findByTypeAndStatusOrderByIdDesc(type,status);
		}
		else
	{
			list = scheduleDrillCompetitionRepo.findByTypeOrderByIdDesc(type);
		}
		return list;
	}	
	}


