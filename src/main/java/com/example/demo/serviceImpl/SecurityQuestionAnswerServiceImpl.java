package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.model.AuthTable;
import com.example.demo.model.Cadet;
import com.example.demo.model.ForgetPasswordAnswers;
import com.example.demo.model.User;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.SecurityQuestionAnswerRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SecurityQuestionAnswerService;
import com.example.demo.util.ConstantVar;

@Service
public class SecurityQuestionAnswerServiceImpl implements SecurityQuestionAnswerService {

	@Autowired
	SecurityQuestionAnswerRepo answerRepo;

	@Autowired
	LoginRepository loginRepo;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	AdminRepository adminRepo;

	@Override
	public List<ForgetPasswordAnswers> createAnswer(List<ForgetPasswordAnswers> answer) {
		return answerRepo.saveAll(answer);
	}

	@Override
	public ForgetPasswordAnswers updateAnswer(ForgetPasswordAnswers answer) {
		ForgetPasswordAnswers br = null;
		Optional<ForgetPasswordAnswers> b = answerRepo.findById(answer.getId());
		if (b.isPresent()) {
			br = b.get();

			if (answer.getQuestion() != null) {

				br.setQuestion(answer.getQuestion());
			}

			if (answer.getAnswer() != null) {

				br.setAnswer(answer.getAnswer());
			}

			if (answer.getStatus() != null) {

				br.setStatus(answer.getStatus());
			}
			br.setUpdatedAt(new Date());
		}
		ForgetPasswordAnswers list = answerRepo.save(br);
		return list;
	}

	@Override
	public AuthTable updatePassword(AuthTable password) {
		AuthTable user = null;
		try {
			user = loginRepo.findByUsername(password.getUsername());
			String pwd = new BCryptPasswordEncoder().encode(password.getPassword());
			user.setPassword(pwd);

			if (user.getHasRole().equals("0")) {  //formultipleroles
				Admin adminUser = adminRepo.findByUsername(password.getUsername());
				adminUser.setPassword(pwd);
				adminRepo.save(adminUser);
			} else if (user.getHasRole().equals("1")) {   //formultipleroles
				User officerUser = userRepo.findByUsername(password.getUsername());
				officerUser.setPassword(pwd);
				userRepo.save(officerUser);
			} else if (user.getHasRole().equals("3")) {  //formultipleroles
				Cadet cadetUser = cadetRepo.findByUsernameAndStatus(password.getUsername(), ConstantVar.ONE);
				cadetUser.setPassword(pwd);
				cadetRepo.save(cadetUser);
			}
			loginRepo.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	@Override
	public ForgetPasswordAnswers checkAnswer(ForgetPasswordAnswers securityAnswer) {
		ForgetPasswordAnswers list = answerRepo.findByServiceIdAndQuestionIdAndAnswerLike(securityAnswer.getServiceId(),
				securityAnswer.getQuestion().getId(), securityAnswer.getAnswer());
		return list;

	}

}
