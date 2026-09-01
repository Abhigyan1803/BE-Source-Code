package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ACSFP;
import com.example.demo.model.CommunicationInfra;
import com.example.demo.model.OtherSecurityInfra;
import com.example.demo.model.SRESecurity;
import com.example.demo.repository.ACSFPRepo;
import com.example.demo.repository.CommunicationInfraRepo;
import com.example.demo.repository.OtherSecurityInfraRepo;
import com.example.demo.repository.SRERepo;
import com.example.demo.service.SecurityApparatusService;
import com.example.demo.util.FileUploader;

@Service
public class SecurityApparatusServiceImpl implements SecurityApparatusService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	SRERepo sreRepo;

	@Autowired
	ACSFPRepo acsfRepo;

	@Autowired
	OtherSecurityInfraRepo otherSecurityRepo;

	@Autowired
	CommunicationInfraRepo communicationRepo;

//==================SRESecurity ================================

	@Override
	public SRESecurity addSREDetails(SRESecurity record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return sreRepo.save(record);
	}

	@Override
	public SRESecurity updateSREDetails(SRESecurity request, MultipartFile docFile) {
		SRESecurity updated = null;
		SRESecurity records = sreRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = sreRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<SRESecurity> getSREList(int status) {
		List<SRESecurity> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = sreRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = sreRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public SRESecurity viewSREById(Long id) {
		SRESecurity record = sreRepo.findById(id).get();
		return record;
	}

	@Override
	public SRESecurity changeSREStatus(int status, Long id) {
		SRESecurity record = sreRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = sreRepo.save(record);
			return record;
		}
		return null;
	}

//================================ACSFP==============================

	@Override
	public ACSFP addACSFPDetails(ACSFP record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return acsfRepo.save(record);
	}

	@Override
	public ACSFP updateACSFPDetails(ACSFP request, MultipartFile docFile) {
		ACSFP updated = null;
		ACSFP records = acsfRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = acsfRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<ACSFP> getACSFPList(int status) {
		List<ACSFP> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = acsfRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = acsfRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public ACSFP viewACSFPById(Long id) {
		ACSFP record = acsfRepo.findById(id).get();
		return record;
	}

	@Override
	public ACSFP changeACSFPStatus(int status, Long id) {
		ACSFP record = acsfRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = acsfRepo.save(record);
			return record;
		}
		return null;
	}

//================OtherSecurityInfra======================================

	@Override
	public OtherSecurityInfra addOtherInfraDetails(OtherSecurityInfra record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return otherSecurityRepo.save(record);
	}

	@Override
	public OtherSecurityInfra updateOtherInfraDetails(OtherSecurityInfra request, MultipartFile docFile) {
		OtherSecurityInfra updated = null;
		OtherSecurityInfra records = otherSecurityRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = otherSecurityRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<OtherSecurityInfra> getOtherInfraList(int status) {
		List<OtherSecurityInfra> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = otherSecurityRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = otherSecurityRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public OtherSecurityInfra viewOtherInfraById(Long id) {
		OtherSecurityInfra record = otherSecurityRepo.findById(id).get();
		return record;
	}

	@Override
	public OtherSecurityInfra changeOtherInfraStatus(int status, Long id) {
		OtherSecurityInfra record = otherSecurityRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = otherSecurityRepo.save(record);
			return record;
		}
		return null;
	}

//========================CommunicationInfra============================

	@Override
	public CommunicationInfra addCommunicationInfraDetails(CommunicationInfra record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return communicationRepo.save(record);
	}

	@Override
	public CommunicationInfra updateCommunicationInfraDetails(CommunicationInfra request, MultipartFile docFile) {
		CommunicationInfra updated = null;
		CommunicationInfra records = communicationRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = communicationRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<CommunicationInfra> getCommunicationInfraList(int status) {
		List<CommunicationInfra> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = communicationRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = communicationRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public CommunicationInfra viewCommunicationInfraById(Long id) {
		CommunicationInfra record = communicationRepo.findById(id).get();
		return record;
	}

	@Override
	public CommunicationInfra changeCommunicationInfraStatus(int status, Long id) {
		CommunicationInfra record = communicationRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = communicationRepo.save(record);
			return record;
		}
		return null;
	}

}
