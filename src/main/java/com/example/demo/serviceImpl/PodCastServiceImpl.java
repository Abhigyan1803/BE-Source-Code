package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.MediaCategory;
import com.example.demo.model.PodCastDetails;
import com.example.demo.repository.MediaCategoryRepo;
import com.example.demo.repository.PodCastRepo;
import com.example.demo.service.PodCastService;
import com.example.demo.util.FileUploader;

@Service
public class PodCastServiceImpl implements PodCastService {

	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	PodCastRepo podCastRepo;
	
	@Autowired
	MediaCategoryRepo mediaCategoryRepo;
	
	
	@Override
	public PodCastDetails addPodcast(MultipartFile file, PodCastDetails request) {
      PodCastDetails podCastDetailsNew=null; 
		try {
			    if(file != null && !file.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(file,UploadDir);
			  request.setMediaUrl(url + filename);
			}
		   MediaCategory mediaCategory=mediaCategoryRepo.findById(request.getMediaCategory().getId()).get();
                 request.setMediaCategory(mediaCategory);
		   podCastDetailsNew=podCastRepo.save(request);     	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return podCastDetailsNew;
	}

	@Override
	public List<PodCastDetails> getAllPodCast() {
	    return podCastRepo.findAllByOrderByIdDesc();
	}
	
	
	@Override
	 public PodCastDetails activeDeactivePodCast(Long id,int status)
	 { 
		PodCastDetails podCastDetailsNew=null;
		 try {
			 PodCastDetails podCastDetails=podCastRepo.findById(id).get();
			   if(podCastDetails!=null) 
			   {
				   podCastDetails.setStatus(status);
				   podCastDetailsNew=podCastRepo.save(podCastDetails);
			   } 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return podCastDetailsNew;  
	 }
	
	@Override
	public PodCastDetails getPodCastById(Long id) {
		return podCastRepo.findById(id).get();
	}


	@Override
	 public PodCastDetails updatePodcastRecord(MultipartFile file,PodCastDetails request)
	 { 
		PodCastDetails podCastDetailsNew=null;
		 try {
			 PodCastDetails podCastDetails=podCastRepo.findById(request.getId()).get();
			   if(podCastDetails!=null) 
			   {
				   if(file != null && !file.isEmpty()) {
						String filename = FileUploader.uploadProfileImage(file,UploadDir);
						request.setMediaUrl(url+filename);
					}   
			  MediaCategory mediaCategory=mediaCategoryRepo.findById(request.getMediaCategory().getId()).get();
	                 request.setMediaCategory(mediaCategory);
				
	                 request.setCreatedAt(podCastDetails.getCreatedAt());
	                 request.setUpdatedOn(new Date());
	                 podCastDetailsNew=podCastRepo.save(request);
			   } 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return podCastDetailsNew;
	 }
	
	
}
