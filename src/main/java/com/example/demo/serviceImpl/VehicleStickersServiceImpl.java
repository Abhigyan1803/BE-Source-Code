package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.VehicleStickers;
import com.example.demo.repository.VehicleStickersRepo;
import com.example.demo.service.VehicleStickerService;
import com.example.demo.util.FileUploader;

@Service
public class VehicleStickersServiceImpl implements VehicleStickerService {
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	VehicleStickersRepo vehicleStickersRepo;

	@Override
	public VehicleStickers addDetails(VehicleStickers record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return vehicleStickersRepo.save(record);
	}

	@Override
	public VehicleStickers updateDetails(VehicleStickers request, MultipartFile docFile) {
		VehicleStickers updated = null;
		VehicleStickers records = vehicleStickersRepo.findById(request.getId()).get();
			if(records != null)
			{
				if(docFile != null && !docFile.isEmpty())
				{
					String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
					records.setFile(url + filename);
				}
				records.setUpdatedAt(new Date());
				records.setDescription(request.getDescription());
				records.setName(request.getName());
				records.setStatus(request.getStatus());
				updated = vehicleStickersRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<VehicleStickers> getList(int status) {
		List<VehicleStickers> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = vehicleStickersRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = vehicleStickersRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public VehicleStickers viewById(Long id) {
		VehicleStickers record = vehicleStickersRepo.findById(id).get();
		return record;
	}

	@Override
	public VehicleStickers changeStatus(int status, Long id) {
		VehicleStickers record =  vehicleStickersRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = vehicleStickersRepo.save(record);
			return record ;
		}
		return null;
	}

}
