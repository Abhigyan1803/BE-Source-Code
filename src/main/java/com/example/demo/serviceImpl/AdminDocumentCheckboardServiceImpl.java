package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AviationList;
import com.example.demo.model.CavList;
import com.example.demo.model.PCList;
import com.example.demo.model.ParaList;
import com.example.demo.model.PendingCVRCases;
import com.example.demo.model.PendingConfirmationLineDirectorate;
import com.example.demo.model.PendingEducationDocs;
import com.example.demo.repository.AviationListRepo;
import com.example.demo.repository.CavListRepo;
import com.example.demo.repository.PCListRepo;
import com.example.demo.repository.ParaListRepo;
import com.example.demo.repository.PendingCVRCasesRepo;
import com.example.demo.repository.PendingConfirmationLineDirectorateRepo;
import com.example.demo.repository.PendingEducationDocsRepo;
import com.example.demo.service.AdminDocumentCheckboardService;
import com.example.demo.util.FileUploader;

@Service
public class AdminDocumentCheckboardServiceImpl implements AdminDocumentCheckboardService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	PendingCVRCasesRepo cvrCasesRepo;

	@Autowired
	PendingEducationDocsRepo educationDocsRepo;

	@Autowired
	PendingConfirmationLineDirectorateRepo lineDirectorateRepo;

	@Autowired
	PCListRepo pcListRepo;

	@Autowired
	AviationListRepo aviationListRepo;

	@Autowired
	ParaListRepo paraListRepo;

	@Autowired
	CavListRepo cavListRepo;

	@Override
	public PendingCVRCases addCVR(PendingCVRCases record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return cvrCasesRepo.save(record);

	}

	@Override
	public PendingCVRCases updateCVR(PendingCVRCases request, MultipartFile docFile) {
		PendingCVRCases updated = null;
		PendingCVRCases records = cvrCasesRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = cvrCasesRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public PendingCVRCases viewCVR(Long id) {
		PendingCVRCases record = cvrCasesRepo.findById(id).get();
		return record;
	}

	@Override
	public List<PendingCVRCases> getCVRList(int status) {
		List<PendingCVRCases> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = cvrCasesRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = cvrCasesRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public PendingCVRCases changeCVRStatus(Long id, int status) {
		PendingCVRCases record = cvrCasesRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = cvrCasesRepo.save(record);
			return record;
		}
		return null;
	}

//===============================Pending Education Doc's Api=================================

	@Override
	public PendingEducationDocs addEducationDoc(PendingEducationDocs record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return educationDocsRepo.save(record);
	}

	@Override
	public PendingEducationDocs updateEducationDoc(PendingEducationDocs request, MultipartFile docFile) {
		PendingEducationDocs updated = null;
		PendingEducationDocs records = educationDocsRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = educationDocsRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public PendingEducationDocs viewEducationDoc(Long id) {
		PendingEducationDocs record = educationDocsRepo.findById(id).get();
		return record;
	}

	@Override
	public List<PendingEducationDocs> getEducationDocList(int status) {
		List<PendingEducationDocs> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = educationDocsRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = educationDocsRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public PendingEducationDocs changeEducationDocStatus(Long id, int status) {
		PendingEducationDocs record = educationDocsRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = educationDocsRepo.save(record);
			return record;
		}
		return null;
	}
//====================================Pending Confirmation Line Directorate====================

	@Override
	public PendingConfirmationLineDirectorate addLineDirectorate(PendingConfirmationLineDirectorate record,
			MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return lineDirectorateRepo.save(record);
	}

	@Override
	public PendingConfirmationLineDirectorate updateLineDirectorate(PendingConfirmationLineDirectorate request,
			MultipartFile docFile) {
		PendingConfirmationLineDirectorate updated = null;
		PendingConfirmationLineDirectorate records = lineDirectorateRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = lineDirectorateRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public PendingConfirmationLineDirectorate viewLineDirectorate(Long id) {
		PendingConfirmationLineDirectorate record = lineDirectorateRepo.findById(id).get();
		return record;
	}

	@Override
	public List<PendingConfirmationLineDirectorate> getLineDirectorateList(int status) {
		List<PendingConfirmationLineDirectorate> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = lineDirectorateRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = lineDirectorateRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public PendingConfirmationLineDirectorate changeLineDirectorateStatus(Long id, int status) {
		PendingConfirmationLineDirectorate record = lineDirectorateRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = lineDirectorateRepo.save(record);
			return record;
		}
		return null;
	}

//==========================================PC List==========================
	@Override
	public PCList addPCList(PCList record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return pcListRepo.save(record);
	}

	@Override
	public PCList updatePCList(PCList request, MultipartFile docFile) {
		PCList updated = null;
		PCList records = pcListRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = pcListRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public PCList viewPCList(Long id) {
		PCList list = pcListRepo.findById(id).get();
		return list;
	}

	@Override
	public List<PCList> getPCList(int status) {
		List<PCList> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = pcListRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = pcListRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public PCList changePCListStatus(Long id, int status) {
		PCList record = pcListRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = pcListRepo.save(record);
			return record;
		}
		return null;
	}

//===================================Aviation List================================
	@Override
	public AviationList addAviationList(AviationList record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return aviationListRepo.save(record);
	}

	@Override
	public AviationList updateAviationList(AviationList request, MultipartFile docFile) {
		AviationList updated = null;
		AviationList records = aviationListRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = aviationListRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public AviationList viewAviationList(Long id) {
		AviationList list = aviationListRepo.findById(id).get();
		return list;
	}

	@Override
	public List<AviationList> getAviationList(int status) {
		List<AviationList> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = aviationListRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = aviationListRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public AviationList changeAviationListStatus(Long id, int status) {
		AviationList record = aviationListRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = aviationListRepo.save(record);
			return record;
		}
		return null;
	}

//=================================Para List======================================
	@Override
	public ParaList addParaList(ParaList record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return paraListRepo.save(record);
	}

	@Override
	public ParaList updateParaList(ParaList request, MultipartFile docFile) {
		ParaList updated = null;
		ParaList records = paraListRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = paraListRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public ParaList viewParaList(Long id) {
		ParaList list = paraListRepo.findById(id).get();
		return list;
	}

	@Override
	public List<ParaList> getParaList(int status) {
		List<ParaList> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = paraListRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = paraListRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public ParaList changeParaListStatus(Long id, int status) {
		ParaList record = paraListRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = paraListRepo.save(record);
			return record;
		}
		return null;
	}

//============================Cav List=====================================
	@Override
	public CavList addCavList(CavList record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return cavListRepo.save(record);
	}

	@Override
	public CavList updateCavList(CavList request, MultipartFile docFile) {
		CavList updated = null;
		CavList records = cavListRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = cavListRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public CavList viewCavList(Long id) {
		CavList list = cavListRepo.findById(id).get();
		return list;
	}

	@Override
	public List<CavList> getCavList(int status) {
		List<CavList> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = cavListRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = cavListRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public CavList changeCavListStatus(Long id, int status) {
		CavList record = cavListRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = cavListRepo.save(record);
			return record;
		}
		return null;
	}

}
