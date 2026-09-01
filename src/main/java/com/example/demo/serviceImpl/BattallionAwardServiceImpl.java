package com.example.demo.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionAward;
import com.example.demo.payload.AddBatalionAwardPayLoad;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.payload.PaginationPayLoad;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.BattalionAwardRepo;
import com.example.demo.repository.BattalionCompanyRepo;
import com.example.demo.service.BattallionAwardService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;

@Service
public class BattallionAwardServiceImpl implements BattallionAwardService {

	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	BattalionAwardRepo battalionAwardRepo;
	
	@Autowired
	AdminBattalionRepo adminBattalionRepo;
	
	@Autowired
	BattalionCompanyRepo companyRepo;
	
	@Override
	public Map<Object, Object> addBatallioAward(AddBatalionAwardPayLoad request,MultipartFile img,ServletRequest servletRequest) {
		
		long time_show = System.currentTimeMillis();
		HashMap<Object, Object> map = new HashMap<>();
		BattalionAward battalionAward=new BattalionAward();
		try
		{
			if(img != null && !img.isEmpty()) {
			
				byte[] bytes = img.getBytes();
	            Path path = Paths.get(UploadDir +time_show+img.getOriginalFilename().replaceAll("\\s+", "_"));
	            Files.write(path,bytes);
	            battalionAward.setImage(url +time_show+img.getOriginalFilename());
			}
			battalionAward.setAward(request.getAward());
			battalionAward.setBattalionId(request.getBattalionId());
			battalionAward.setName(request.getName());
			battalionAward.setRank(request.getRank());
			battalionAward.setStatus(request.getStatus());
			
			battalionAward.setCompany(companyRepo.findById(request.getCompanyId()).get());

			
			battalionAward = battalionAwardRepo.save(battalionAward);
			
			if(battalionAward != null)
			{
				FileWritting.createLog((HttpServletRequest) servletRequest,battalionAward.getId() + ",added," + "addBattallionAward," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.OBJECT_DETAILS, battalionAward);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		
		return map;
	}
	

	@Override
	public Map<Object, Object> getAllAwards(int battalionId,int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			List<BattalionAward> responseList = new ArrayList<>();
			 //Pageable pagedData=PageRequest.of(request.getpNumber(),request.getPageSize());
				//Page<BattalionAward> awardList = battalionAwardRepo.findAllByOrderByIdDesc(pagedData);
			
			List<BattalionAward> awardList = new ArrayList<>();
			if(battalionId == 0 && status == 2) {
				 awardList = battalionAwardRepo.findAllByOrderByIdDesc();
			}
			else if(battalionId > 0 && battalionId < 5 && status == 2)
			{ 
				 awardList = battalionAwardRepo.findByBattalionIdOrderByIdDesc(battalionId);
			}
			else if(battalionId > 0 && battalionId < 5 && status < 2)
			{
				awardList = battalionAwardRepo.findByBattalionIdAndStatusOrderByIdDesc(battalionId,status);
			}
			else if(battalionId == 0 && status < 2)
			{
				awardList = battalionAwardRepo.findAllByStatusOrderByIdDesc(status);
			}
			else
			{
				awardList = battalionAwardRepo.findAllByOrderByIdDesc();
			}
				for(BattalionAward awards : awardList)
				{
					Battalion battalion = adminBattalionRepo.findById(awards.getBattalionId()).get();
					awards.setBattalionName(battalion.getShortName());
					
					responseList.add(awards);
					
				}
				if(!awardList.isEmpty())
				{
					map.put(ConstantMessage.LIST,responseList);
					//map.put(ConstantMessage.LIST_SIZE,awardList.getTotalElements());
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
					return map;
				}
				else
				{
					map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
					return map;
				}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
			
		}
		return map;
	}
	
	@Override
	public Map<Object, Object> updateAwards(BattalionAward request,MultipartFile img,ServletRequest servletRequest) {
	
			long time_show = System.currentTimeMillis();
			HashMap<Object, Object> map = new HashMap<>();
			try
			{
			   BattalionAward battalionAward=battalionAwardRepo.findById(request.getId()).get();
				if(battalionAward!=null) 
				{
				
					if(img != null && !img.isEmpty()) {
						
						byte[] bytes = img.getBytes();
			            Path path = Paths.get(UploadDir +time_show+img.getOriginalFilename().replaceAll("\\s+", "_"));
			            Files.write(path,bytes);
			            battalionAward.setImage(url +time_show+img.getOriginalFilename().replaceAll("\\s+", "_"));
					}
					battalionAward.setAward(request.getAward());
				    battalionAward.setName(request.getName());
				    battalionAward.setRank(request.getRank());
				    battalionAward.setStatus(request.getStatus());
				    battalionAward.setUpdatedDate(new Date());
				    battalionAward.setCompany(companyRepo.findById(request.getCompany().getId()).get());

				    battalionAward =battalionAwardRepo.save(battalionAward);
					
					if(battalionAward != null)
					{
						FileWritting.createLog((HttpServletRequest) servletRequest,battalionAward.getId() + ",update," + "updateAwards," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
						map.put(ConstantMessage.OBJECT_DETAILS,battalionAward);
						map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
						map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
						return map;
					}
					else
					{
						map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
						map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
					}
				}else 
				{
					map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
				}
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return map;
		}

	@Override
	public Map<Object, Object> ActiveDeactiveAward(Long id, int status ,ServletRequest servletRequest) {
	
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			BattalionAward battalionAward = battalionAwardRepo.findById(id).get();
			if(battalionAward!= null)
			{
				battalionAward.setStatus(status);
				battalionAward = battalionAwardRepo.save(battalionAward);
				
				FileWritting.createLog((HttpServletRequest) servletRequest,battalionAward.getId() + ",status-update," + "activeDeActiveAwards," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
				
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
				return map;
			}
			else
			{
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
			
		}
		return map;
	}

	@Override	
	public  Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request){
		HashMap<Object, Object> map = new HashMap<>();
		try
		{
			BattalionAward battalionAward=battalionAwardRepo.findById(request.getId()).get();

			if(battalionAward!=null) 
			{
				map.put(ConstantMessage.OBJECT_DETAILS,battalionAward);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			}else 
			{
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE,ConstantMessage.INVALID_ID);
			}	
		}
	catch(Exception ex)
	{
	map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
	map.put(ConstantMessage.MESSAGE,ConstantMessage.TECHNICAL_ISSUE);
	}
return map;
}
	

}
