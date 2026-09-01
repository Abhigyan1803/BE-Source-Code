package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.AuthTable;
import com.example.demo.model.Blog;
import com.example.demo.payload.BlogPayload;
import com.example.demo.repository.BlogRepo;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.BlogService;
import com.example.demo.util.ConstantVar;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogRepo blogRepo;

	@Autowired
	LoginRepository authRepo;

	@Override
	public Blog addBlog(Blog blog, ServletRequest request) {
		AuthTable auth = getUser(request);
		blog.setAuthor(auth.getName());
		if (auth.getHasRole().equals("3")) {
			blog.setIsCadet(1);
		} else {
			blog.setIsCadet(0);
		}
		int minutesOfReading = blog.getDescription().length() / 200;
		blog.setMinutesOfReading(minutesOfReading + " min");

		return blogRepo.save(blog);
	}

	@Override
	public BlogPayload getBlogList(Integer battalionId, Integer status, Pageable pageable) {
		Integer[] deletedStatus = { 2 };
		BlogPayload blogPayLoad = new BlogPayload();
		Integer totalRecords = 0;
		List<Blog> list = null;
		if (status < 2) {
			if(battalionId!=null && battalionId!=0) {  //13-03-2023
				list = blogRepo.findByBattalionIdAndStatusAndStatusNotInOrderByIdDesc(battalionId, status, deletedStatus, pageable);
				totalRecords = blogRepo.findByBattalionIdAndStatusAndStatusNotInOrderByIdDesc(battalionId, status, deletedStatus).size();
			}  //----13-03-2023
			else {
			list = blogRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus, pageable);
			totalRecords = blogRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus).size();
			}
			// return list;
		} else {
			if(battalionId!=null && battalionId!=0) {
				list = blogRepo.findAllByBattalionIdAndStatusNotInOrderByIdDesc(battalionId,deletedStatus, pageable);
				totalRecords = blogRepo.findAllByBattalionIdAndStatusNotInOrderByIdDesc(battalionId,deletedStatus).size();
			}
			else {
			list = blogRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus, pageable);
			totalRecords = blogRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus).size();
			}
			// return list;
		}
		blogPayLoad.setTotalRecords(totalRecords);
		blogPayLoad.setBlogList(list);
		return blogPayLoad;
	}

	@Override
	public Blog getBlogById(Long id) {
		Optional<Blog> list = blogRepo.findById(id);
		return list.get();
	}

	@Override
	public Blog updateBlog(Blog blog, ServletRequest request) {
		Blog blg = null;
		Optional<Blog> b = blogRepo.findById(blog.getId());
		if (b.isPresent()) {

			blg = b.get();

			if (StringUtils.isNotBlank(blog.getBlogImage())) {
				blg.setBlogImage(blog.getBlogImage());
			}

			if (StringUtils.isNotBlank(blog.getDocs())) {
				blg.setDocs(blog.getDocs());
			}

			if (blog.getTitle() != null) {

				blg.setTitle(blog.getTitle());
			}

			if (blog.getCategory() != null) {

				blg.setCategory(blog.getCategory());
			}

			if (blog.getAuthor() != null) {

				blg.setAuthor(blog.getAuthor());
			}

			if (blog.getApprovedBy() != null) {

				blg.setApprovedBy(blog.getApprovedBy());
			}

			if (blog.getApprovedDate() != null) {

				blg.setApprovedDate(blog.getApprovedDate());
			}

			if (blog.getDescription() != null) {

				blg.setDescription(blog.getDescription());
			}

			if (blog.getMinutesOfReading() != null) {

				blg.setMinutesOfReading(blog.getMinutesOfReading());
			}

			if (blog.getStatus() != null) {

				blg.setStatus(blog.getStatus());
			}

			blg.setUpdatedAt(new Date());

		}
		Blog list = blogRepo.save(blg);
		return list;
	}

	@Override
	public List<Blog> getBlogListByCategory(String cat) {
		List<Blog> list = blogRepo.findByCategoryAndStatusOrderByIdDesc(cat, 1);
		return list;
	}

	public AuthTable getUser(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader(ConstantVar.HEADER_STRING);
		String username = null;
		String authToken = null;
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		if (header != null && header.startsWith(ConstantVar.TOKEN_PREFIX)) {
			authToken = header.replace(ConstantVar.TOKEN_PREFIX, "");
			username = jwtTokenUtil.getUsernameFromToken(authToken);
		}
		AuthTable authUser = authRepo.findByUsername(username);
		return authUser;
	}

}
