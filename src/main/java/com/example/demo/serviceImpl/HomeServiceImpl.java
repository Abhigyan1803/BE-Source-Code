package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AboutImages;
import com.example.demo.model.HomeAboutEntries;
import com.example.demo.model.HomeAboutEntriesDetails;
import com.example.demo.model.HomeAboutUs;
import com.example.demo.repository.HomeAboutEntriesDetailsRepository;
import com.example.demo.repository.HomeAboutEntriesRepository;
import com.example.demo.repository.HomeAboutUsRepository;
import com.example.demo.service.HomeService;
import com.example.demo.util.FileUploader;

@Service
public class HomeServiceImpl implements HomeService {
	
//	@Value("${spring.url}")
//	private String url;
//
//	@Value("${spring.dir}")
//	private String UploadDir;

	@Autowired
	HomeAboutUsRepository homeAboutRepo;
	
	@Autowired
	HomeAboutEntriesRepository aboutEntryRepo;
	
	@Autowired
	HomeAboutEntriesDetailsRepository aboutEntryDetailsRepo;
	@Override
	public HomeAboutUs addAbout(HomeAboutUs homeAboutUs) {
//		HomeAboutUs homeAboutUsNew=new HomeAboutUs();
//		List<AboutImages>  aboutImagesList=homeAboutUs.getImages();
//		
//		
//		int i=0;
//		
//		for(MultipartFile image:images) {
//			//for(AboutImages imageUrl:homeAboutUs.getImages()) {
//		//	List<AboutImages> doc;
//			AboutImages aboutImage = new AboutImages();
//				String doc="";
//			doc = FileUploader.uploadProfileImage(image, uploadDir);
//			//String imageUrl.setUrl();
//			//aboutImage.setUrl(url + doc);
//			aboutImagesList.get(i).setUrl(url + doc);
//			
//			
//			
//			//aboutImages.add(imageUrl);
//			i++;
//		//	homeAboutUsNew.setImages(doc);
//		//}
//			}
//		homeAboutUs.setImages(aboutImagesList);
		HomeAboutUs result=	homeAboutRepo.save(homeAboutUs);
		return result;
	}
	
	@Override
		public List<HomeAboutUs> getHomeAboutListByType(String type) {
			// TODO Auto-generated method stub
		if(type!=null) {
			List<HomeAboutUs> list=homeAboutRepo.findAllByType(type);
			if(list!=null) {
				return list;
			}
			return null;
		
		}
		List<HomeAboutUs> listAll=homeAboutRepo.findAll();
		if(listAll!=null) {
			return listAll;
		}
		return null;
		
		}
	
	
	@Override
		public HomeAboutUs updateHomeAbout(HomeAboutUs homeAboutUs) {
		HomeAboutUs result = null;
		if (homeAboutUs != null && homeAboutUs.getId() != null && homeAboutUs.getId() != 0) {

			Optional<HomeAboutUs> at = homeAboutRepo.findById(homeAboutUs.getId());
			if (at.isPresent()) {
				homeAboutUs.setUpdatedAt(new Date());
				result = homeAboutRepo.save(homeAboutUs);
			}
		}
		return result;
		}
	
	//-----------------------------About Entries-----------------------------------------------------
	
	@Override
		public HomeAboutEntries addAboutEntry(HomeAboutEntries entries) {
			// TODO Auto-generated method stub
		HomeAboutEntries result=aboutEntryRepo.save(entries);
			return result;
		}
	
	@Override
		public HomeAboutEntries getAboutEntryById(Long id) {
			// TODO Auto-generated method stub
		if(id!=null && id!=0) {
			Optional<HomeAboutEntries> result=aboutEntryRepo.findById(id);
			if(result.isPresent())
			{
				return result.get();
			}
		}
		
			return null;
		}
	
	@Override
	public List<HomeAboutEntries> getAboutEntryList() {
		// TODO Auto-generated method stub
		List<HomeAboutEntries> result=aboutEntryRepo.findAll();
		return result;
	}
	
	@Override
		public HomeAboutEntries updateAboutEntry(HomeAboutEntries entries) {
			// TODO Auto-generated method stub
		HomeAboutEntries result = null;
		if (entries != null && entries.getId() != null && entries.getId() != 0) {

			Optional<HomeAboutEntries> at = aboutEntryRepo.findById(entries.getId());
			if (at.isPresent()) {
				entries.setUpdatedAt(new Date());
				result = aboutEntryRepo.save(entries);
			}
		}
		return result;
		}
	//-----------------------------About Entries Description-----------------------------------------------------
	
	@Override
		public HomeAboutEntriesDetails addAboutEntryDetails(HomeAboutEntriesDetails entriesDetails) {
			// TODO Auto-generated method stub
		HomeAboutEntriesDetails result=aboutEntryDetailsRepo.save(entriesDetails);
			return result;
		}
	
	@Override
		public HomeAboutEntriesDetails getAboutEntryDetailsById(Long id) {
			// TODO Auto-generated method stub
		if(id!=null && id!=0) {
		Optional<HomeAboutEntriesDetails> result=aboutEntryDetailsRepo.findById(id);
		if(result.isPresent())
		{
			return result.get();
		}
		}
			return null;
		}
	
	@Override
		public List<HomeAboutEntriesDetails> getAboutEntryDetailsList() {
			// TODO Auto-generated method stub
		List<HomeAboutEntriesDetails> result=aboutEntryDetailsRepo.findAll();
			return result;
		}
	
	@Override
		public HomeAboutEntriesDetails updateAboutEntryDetails(HomeAboutEntriesDetails entriesDetails) {
			// TODO Auto-generated method stub
		HomeAboutEntriesDetails result = null;
		if (entriesDetails != null && entriesDetails.getId() != null && entriesDetails.getId() != 0) {

			Optional<HomeAboutEntriesDetails> at = aboutEntryDetailsRepo.findById(entriesDetails.getId());
			if (at.isPresent()) {
				entriesDetails.setUpdatedAt(new Date());
				result = aboutEntryDetailsRepo.save(entriesDetails);
			}
		}
		return result;

		}
}
