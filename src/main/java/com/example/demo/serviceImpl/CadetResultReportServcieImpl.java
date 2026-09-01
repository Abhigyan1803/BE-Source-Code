package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.CampMarksResult;
import com.example.demo.model.GSO2ServiceSubjectBMTResult;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.model.ServiceBmt2Result;
import com.example.demo.model.ServiceBmt2SubjectResult;
import com.example.demo.model.WeaponTrainingResult;
import com.example.demo.payload.CadetResultReportPayload1;
import com.example.demo.payload.CadetResultReportPayload2;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.CadetWeaponTrainingResultRepo;
import com.example.demo.repository.CampMarksResultRepo;
import com.example.demo.repository.GSO2ServiceSubjectBMTResultRepo;
import com.example.demo.repository.RunbackRouteMrRepo;
import com.example.demo.repository.ServiceBmt2ResultRepository;
import com.example.demo.service.CadetResultReportService;

@Service
public class CadetResultReportServcieImpl implements CadetResultReportService {

	@Autowired
	private CadetWeaponTrainingResultRepo cadetWeaponTrainingResultRepo;

	@Autowired
	private GSO2ServiceSubjectBMTResultRepo gSO2ServiceSubjectBMTResultRepo;

	@Autowired
	private ServiceBmt2ResultRepository serviceBmt2ResultRepository;

	@Autowired
	private CampMarksResultRepo campMarksResultRepo;

	@Autowired
	private RunbackRouteMrRepo runbackRouteMrRepo;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public CadetResultReportPayload1 getCadetResultReport() {
		// TODO Auto-generated method stub

		CadetResultReportPayload1 payload1 = new CadetResultReportPayload1();
		payload1.setTerm1(new ArrayList<CadetResultReportPayload2>());
		payload1.setTerm2(new ArrayList<CadetResultReportPayload2>());
		payload1.setTerm3(new ArrayList<CadetResultReportPayload2>());
		payload1.setTech2(new ArrayList<CadetResultReportPayload2>());

		List<Cadet> cadetList = null;
		cadetList = cadetRepo.findAllByStatus(1);

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				List<WeaponTrainingResult> wtList = cadetWeaponTrainingResultRepo
						.findByServiceIdOrderByTermId(cad.getServiceId());
				CadetResultReportPayload2 wtPayLoadTerm1 = new CadetResultReportPayload2();
				CadetResultReportPayload2 wtPayLoadTerm2 = new CadetResultReportPayload2();
				CadetResultReportPayload2 wtPayLoadTerm3 = new CadetResultReportPayload2();
				CadetResultReportPayload2 wtPayLoadTech2 = new CadetResultReportPayload2();
				CadetResultReportPayload2 wtPayLoad2 = new CadetResultReportPayload2();
				wtPayLoad2.setName(cad.getName());
				wtPayLoad2.setServiceId(cad.getServiceId());
				wtPayLoad2.setCourse(cad.getCourse());
				wtPayLoad2.setFgcCountry(cad.getNationality());
				wtPayLoad2.setCoy(cad.getCompany());
				wtPayLoad2.setBn(cad.getBattalian());
				CadetResultReportPayload2 demoWtPayLoad2 = new CadetResultReportPayload2();
				demoWtPayLoad2.setName(cad.getName());
				demoWtPayLoad2.setServiceId(cad.getServiceId());

				if (wtList.size() > 0) {
					for (WeaponTrainingResult wtResult : wtList) {

						if (wtResult.getTermId() == 1) {
							wtPayLoad2.setWtObtainedMarks(wtResult.getGrandTotal());
							wtPayLoad2.setWtTotalMarks(150);
							wtPayLoad2.setWtRemarks(wtResult.getRemark());   //////////remarks
							wtPayLoadTerm1 = wtPayLoad2;
						} else {
							wtPayLoadTerm1 = demoWtPayLoad2;
						}
						if (wtResult.getTermId() == 2) {
							wtPayLoad2.setWtObtainedMarks(wtResult.getGrandTotal());
							wtPayLoad2.setWtTotalMarks(150);
							wtPayLoad2.setWtRemarks(wtResult.getRemark());  /////remarks
							wtPayLoadTerm2 = wtPayLoad2;
						} else {
							wtPayLoadTerm2 = demoWtPayLoad2;
						}
						if (wtResult.getTermId() == 3) {
							wtPayLoad2.setWtObtainedMarks(wtResult.getGrandTotal());
							wtPayLoad2.setWtTotalMarks(150);
							wtPayLoad2.setWtRemarks(wtResult.getRemark());  /////remarks
							wtPayLoadTerm3 = wtPayLoad2;
						} else {
							wtPayLoadTerm3 = demoWtPayLoad2;
						}
						if (wtResult.getTermId() == 7) {
							wtPayLoad2.setWtObtainedMarks(wtResult.getGrandTotal());
							wtPayLoad2.setWtTotalMarks(150);
							wtPayLoad2.setWtRemarks(wtResult.getRemark());  /////remarks
							wtPayLoadTech2 = wtPayLoad2;
						} else {
							wtPayLoadTech2 = demoWtPayLoad2;
						}
					}
				} else {
					wtPayLoadTerm1 = wtPayLoad2;
					wtPayLoadTerm2 = wtPayLoad2;
					wtPayLoadTerm3 = wtPayLoad2;
					wtPayLoadTech2 = wtPayLoad2;
				}

				// -----******** FOR GSO2ServiceSubjectBMTResult (BMT 1)******---------

				List<GSO2ServiceSubjectBMTResult> bmt1List = gSO2ServiceSubjectBMTResultRepo
						.findByServiceIdOrderByIdDesc(cad.getServiceId());
				if (bmt1List.size() > 0) {
					for (GSO2ServiceSubjectBMTResult bmt1Result : bmt1List) {
						if (bmt1Result.getTermId() == 1) {
							wtPayLoadTerm1.setBmt1ObtainedMarks(bmt1Result.getObtainedMarks());
							wtPayLoadTerm1.setBmt1TotalMarks(100);
							wtPayLoadTerm1.setBmt1Remarks(bmt1Result.getRemarks());   ////////Remarks
							
						}
						if (bmt1Result.getTermId() == 2) {
							wtPayLoadTerm2.setBmt1ObtainedMarks(bmt1Result.getObtainedMarks());
							wtPayLoadTerm2.setBmt1TotalMarks(100);
							wtPayLoadTerm2.setBmt1Remarks(bmt1Result.getRemarks());   ////////Remarks
						}
						if (bmt1Result.getTermId() == 3) {
							wtPayLoadTerm3.setBmt1ObtainedMarks(bmt1Result.getObtainedMarks());
							wtPayLoadTerm3.setBmt1TotalMarks(200);
							wtPayLoadTerm3.setBmt1Remarks(bmt1Result.getRemarks());   ////////Remarks
						}
						if (bmt1Result.getTermId() == 7) {
							wtPayLoadTech2.setBmt1ObtainedMarks(bmt1Result.getObtainedMarks());
							wtPayLoadTech2.setBmt1TotalMarks(100);
							wtPayLoadTech2.setBmt1Remarks(bmt1Result.getRemarks());   ////////Remarks
						}
					}
				}

				// -----******** FOR RunbackRouteMr (Map (Practical))******---------

				List<RunbackRouteMr> mrPracList = runbackRouteMrRepo
						.findByServiceIdAndResultTypeOrderByTermId(cad.getServiceId(), "MR Prac");
				if (mrPracList.size() > 0) {
					for (RunbackRouteMr mrPracResult : mrPracList) {

						if (mrPracResult.getTermId() == 1) {
							Integer obtainedMarks = mrPracResult.getObtainedMarks() == null ? 0
									: mrPracResult.getObtainedMarks();
							wtPayLoadTerm1
									.setMapReadingPacticalObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							wtPayLoadTerm1.setMapReadingPacticalTotalMarks(25);
							wtPayLoadTerm1.setMapReadingPacticalRemarks(mrPracResult.getRemark());     ////////Remarks
						}
						if (mrPracResult.getTermId() == 2) {
							Integer obtainedMarks = mrPracResult.getObtainedMarks() == null ? 0
									: mrPracResult.getObtainedMarks();
							wtPayLoadTerm2
									.setMapReadingPacticalObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							wtPayLoadTerm2.setMapReadingPacticalTotalMarks(25);
							wtPayLoadTerm2.setMapReadingPacticalRemarks(mrPracResult.getRemark());     ////////Remarks
						}
						if (mrPracResult.getTermId() == 3) {
							Integer obtainedMarks = mrPracResult.getObtainedMarks() == null ? 0
									: mrPracResult.getObtainedMarks();
							wtPayLoadTerm3
									.setMapReadingPacticalObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							wtPayLoadTerm3.setMapReadingPacticalTotalMarks(25);
							wtPayLoadTerm3.setMapReadingPacticalRemarks(mrPracResult.getRemark());     ////////Remarks
						}
						if (mrPracResult.getTermId() == 7) {
							Integer obtainedMarks = mrPracResult.getObtainedMarks() == null ? 0
									: mrPracResult.getObtainedMarks();
							wtPayLoadTech2
									.setMapReadingPacticalObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							wtPayLoadTech2.setMapReadingPacticalTotalMarks(25);
							wtPayLoadTech2.setMapReadingPacticalRemarks(mrPracResult.getRemark());     ////////Remarks
						}
					}
				}

				// -----******** FOR ServiceBmt2Result (BMT 2)******---------

				List<ServiceBmt2Result> bmt2List = serviceBmt2ResultRepository
						.findByServiceIdOrderByTermId(cad.getServiceId());
				if (bmt2List.size() > 0) {
					for (ServiceBmt2Result bmt2Result : bmt2List) {
						List<ServiceBmt2SubjectResult> subjectResultList = bmt2Result.getServiceBmt2SubjectResult();
						if (bmt2Result.getTermId() == 1) {
							for (ServiceBmt2SubjectResult bmt2SubResult : subjectResultList) {
								if (bmt2SubResult.getSubjectId() == 1) {
									wtPayLoadTerm1.setMapReadingWrittenObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm1.setMapReadingWrittenTotalMarks(bmt2SubResult.getTotalMarks());
									wtPayLoadTerm1.setMapReadingWrittenRemarks(bmt2SubResult.getRemarks());   /////Remarks
								}
								if (bmt2SubResult.getSubjectId() == 2) {
									wtPayLoadTerm1.setRadioTelephonyObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm1.setRadioTelephonyTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 3) {
									wtPayLoadTerm1.setFieldEngineeringObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm1.setFieldEngineeringTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 4) {
									wtPayLoadTerm1.setOrgAdmAndIsObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm1.setOrgAdmAndIsTotalMarks(bmt2SubResult.getTotalMarks());
								}

								if (bmt2SubResult.getSubjectId() == 5) {
									wtPayLoadTerm1.setLdrshipTrgObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm1.setLdrshipTrgTotalMarks(bmt2SubResult.getTotalMarks());
								}
							}
							// bmt2PayLoadTerm1 = bmt2PayLoad2;
						}
						if (bmt2Result.getTermId() == 2) {
							for (ServiceBmt2SubjectResult bmt2SubResult : subjectResultList) {
								if (bmt2SubResult.getSubjectId() == 6) {
									wtPayLoadTerm2.setMapReadingWrittenObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm2.setMapReadingWrittenTotalMarks(bmt2SubResult.getTotalMarks());
									wtPayLoadTerm2.setMapReadingWrittenRemarks(bmt2SubResult.getRemarks()); /////Remarks
								}
								if (bmt2SubResult.getSubjectId() == 7) {
									wtPayLoadTerm2.setRadioTelephonyObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm2.setRadioTelephonyTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 8) {
									wtPayLoadTerm2.setFieldEngineeringObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm2.setFieldEngineeringTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 9) {
									wtPayLoadTerm2.setOrgAdmAndIsObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm2.setOrgAdmAndIsTotalMarks(bmt2SubResult.getTotalMarks());
								}

								if (bmt2SubResult.getSubjectId() == 10) {
									wtPayLoadTerm2.setLdrshipTrgObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm2.setLdrshipTrgTotalMarks(bmt2SubResult.getTotalMarks());
								}
							}

							// bmt2PayLoadTerm2 = bmt2PayLoad2;
						}
						if (bmt2Result.getTermId() == 3) {
							for (ServiceBmt2SubjectResult bmt2SubResult : subjectResultList) {
								if (bmt2SubResult.getSubjectId() == 11) {
									wtPayLoadTerm3.setMapReadingWrittenObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm3.setMapReadingWrittenTotalMarks(bmt2SubResult.getTotalMarks());
									wtPayLoadTerm3.setMapReadingWrittenRemarks(bmt2SubResult.getRemarks()); /////Remarks
								}
								if (bmt2SubResult.getSubjectId() == 12) {
									wtPayLoadTerm3.setFieldEngineeringObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm3.setFieldEngineeringTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 13) {
									wtPayLoadTerm3.setOrgAdmAndIsObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm3.setOrgAdmAndIsTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 14) {
									wtPayLoadTerm3.setFinTimeMgtObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm3.setFinTimeMgtTotalMarks(bmt2SubResult.getTotalMarks());
								}

								if (bmt2SubResult.getSubjectId() == 15) {
									wtPayLoadTerm3.setLdrshipTrgObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTerm3.setLdrshipTrgTotalMarks(bmt2SubResult.getTotalMarks());
								}
							}

							// bmt2PayLoadTerm3 = bmt2PayLoad2;
						}
						if (bmt2Result.getTermId() == 7) {
							for (ServiceBmt2SubjectResult bmt2SubResult : subjectResultList) {
								if (bmt2SubResult.getSubjectId() == 16) {
									wtPayLoadTech2.setMapReadingWrittenObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTech2.setMapReadingWrittenTotalMarks(bmt2SubResult.getTotalMarks());
									wtPayLoadTech2.setMapReadingWrittenRemarks(bmt2SubResult.getRemarks()); /////Remarks
								}
								if (bmt2SubResult.getSubjectId() == 17) {
									wtPayLoadTech2.setRadioTelephonyObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTech2.setRadioTelephonyTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 18) {
									wtPayLoadTech2.setFieldEngineeringObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTech2.setFieldEngineeringTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 19) {
									wtPayLoadTech2.setOrgAdmAndIsObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTech2.setOrgAdmAndIsTotalMarks(bmt2SubResult.getTotalMarks());
								}
								if (bmt2SubResult.getSubjectId() == 20) {
									wtPayLoadTech2.setLdrshipTrgObtainedMarks(bmt2SubResult.getObtainedMarks());
									wtPayLoadTech2.setLdrshipTrgTotalMarks(bmt2SubResult.getTotalMarks());
								}
							}
							// bmt2PayLoadTech2 = bmt2PayLoad2;
						}
					}
				}

				// -----******** FOR CampMarksResult (Camp/Exercise (Incl Tac Trg
				// (Practical))******---------

				List<CampMarksResult> campMarksList = campMarksResultRepo
						.findByServiceIdOrderByTermId(cad.getServiceId());
				if (campMarksList.size() > 0) {
					for (CampMarksResult campMarksResult : campMarksList) {
						Double obtainedMarks = campMarksResult.getObtainedMarks() == null ? 0
								: campMarksResult.getObtainedMarks();

						if (campMarksResult.getTermId() == 1) {
							if (campMarksResult.getExerciseTypeId() == 1) {
								wtPayLoadTerm1.setExPervatRakshaObtainedMarks(obtainedMarks);
								wtPayLoadTerm1.setExPervatRakshaTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx1Term1 = campMarksPayLoad2;
								wtPayLoadTerm1.setExPervatRakshaRemarks(campMarksResult.getRemarks());       //////////Remarks
							}
							if (campMarksResult.getExerciseTypeId() == 2) {
								wtPayLoadTerm1.setExChinditObtainedMarks(obtainedMarks);
								wtPayLoadTerm1.setExChinditTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx2Term1 = campMarksPayLoad2;
								wtPayLoadTerm1.setExChinditRemarks(campMarksResult.getRemarks());       //////////Remarks
							}

						}
						if (campMarksResult.getTermId() == 2) {
							if (campMarksResult.getExerciseTypeId() == 1) {
								wtPayLoadTerm2.setExPervatRakshaObtainedMarks(obtainedMarks);
								wtPayLoadTerm2.setExPervatRakshaTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx1Term2 = campMarksPayLoad2;
								wtPayLoadTerm2.setExPervatRakshaRemarks(campMarksResult.getRemarks());       //////////Remarks
							}
							if (campMarksResult.getExerciseTypeId() == 2) {
								wtPayLoadTerm2.setExChinditObtainedMarks(obtainedMarks);
								wtPayLoadTerm2.setExChinditTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx2Term2 = campMarksPayLoad2;
								wtPayLoadTerm2.setExChinditRemarks(campMarksResult.getRemarks());       //////////Remarks
							}
						}
						if (campMarksResult.getTermId() == 3) {
							if (campMarksResult.getExerciseTypeId() == 1) {
								wtPayLoadTerm3.setExPervatRakshaObtainedMarks(obtainedMarks);
								wtPayLoadTerm3.setExPervatRakshaTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx1Term3 = campMarksPayLoad2;
								wtPayLoadTerm3.setExPervatRakshaRemarks(campMarksResult.getRemarks());       //////////Remarks
							}
							if (campMarksResult.getExerciseTypeId() == 2) {
								wtPayLoadTerm3.setExChinditObtainedMarks(obtainedMarks);
								wtPayLoadTerm3.setExChinditTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx2Term3 = campMarksPayLoad2;
								wtPayLoadTerm3.setExChinditRemarks(campMarksResult.getRemarks());       //////////Remarks
							}
						}
						if (campMarksResult.getTermId() == 7) {
							if (campMarksResult.getExerciseTypeId() == 1) {
								wtPayLoadTech2.setExPervatRakshaObtainedMarks(obtainedMarks);
								wtPayLoadTech2.setExPervatRakshaTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx1Tech2 = campMarksPayLoad2;
								wtPayLoadTech2.setExPervatRakshaRemarks(campMarksResult.getRemarks());       //////////Remarks
							}
							if (campMarksResult.getExerciseTypeId() == 2) {
								wtPayLoadTech2.setExChinditObtainedMarks(obtainedMarks);
								wtPayLoadTech2.setExChinditTotalMarks(
										Integer.parseInt(campMarksResult.getTotalMarks().toString()));
								// campMarksPayLoadEx2Tech2 = campMarksPayLoad2;
								wtPayLoadTech2.setExChinditRemarks(campMarksResult.getRemarks());       //////////Remarks
							}
						}
					}
				}

				// -----******** FOR RouteMarch (SpeedMarch)******---------

				List<RunbackRouteMr> speedMarchList = runbackRouteMrRepo
						.findByServiceIdAndResultTypeOrderByTermId(cad.getServiceId(), "Route March");

				if (mrPracList.size() > 0) {
					for (RunbackRouteMr speedMarchResult : speedMarchList) {
						if (speedMarchResult.getTermId() == 1) {
							Integer obtainedMarks = speedMarchResult.getObtainedMarks() == null ? 0
									: speedMarchResult.getObtainedMarks();
							wtPayLoadTerm1.setSpeedMarchObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTerm1.setSpeedMarchTotalMarks(15);
							wtPayLoadTerm1.setSpeedMarchRemarks(speedMarchResult.getRemark());     /////////Remarks
						}
						if (speedMarchResult.getTermId() == 2) {
							Integer obtainedMarks = speedMarchResult.getObtainedMarks() == null ? 0
									: speedMarchResult.getObtainedMarks();
							wtPayLoadTerm2.setSpeedMarchObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTerm2.setSpeedMarchTotalMarks(15);
							wtPayLoadTerm2.setSpeedMarchRemarks(speedMarchResult.getRemark());     /////////Remarks
						}
						if (speedMarchResult.getTermId() == 3) {
							Integer obtainedMarks = speedMarchResult.getObtainedMarks() == null ? 0
									: speedMarchResult.getObtainedMarks();
							wtPayLoadTerm3.setSpeedMarchObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTerm3.setSpeedMarchTotalMarks(15);
							wtPayLoadTerm3.setSpeedMarchRemarks(speedMarchResult.getRemark());     /////////Remarks
						}
						if (speedMarchResult.getTermId() == 7) {
							Integer obtainedMarks = speedMarchResult.getObtainedMarks() == null ? 0
									: speedMarchResult.getObtainedMarks();
							wtPayLoadTech2.setSpeedMarchObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTech2.setSpeedMarchTotalMarks(15);
							wtPayLoadTech2.setSpeedMarchRemarks(speedMarchResult.getRemark());     /////////Remarks
						}
					}
				}

				// -----******** FOR Runback (RunBack)******---------

				List<RunbackRouteMr> runbackList = runbackRouteMrRepo
						.findByServiceIdAndResultTypeOrderByTermId(cad.getServiceId(), "Runback");
				if (runbackList.size() > 0) {
					for (RunbackRouteMr runbackResult : runbackList) {

						if (runbackResult.getTermId() == 1) {
							Integer obtainedMarks = runbackResult.getObtainedMarks() == null ? 0
									: runbackResult.getObtainedMarks();
							wtPayLoadTerm1.setRunbackObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTerm1.setRunbackTotalMarks(15);
							wtPayLoadTerm1.setRunbackRemarks(runbackResult.getRemark());     /////////Remarks
						}
						if (runbackResult.getTermId() == 2) {
							Integer obtainedMarks = runbackResult.getObtainedMarks() == null ? 0
									: runbackResult.getObtainedMarks();
							wtPayLoadTerm2.setRunbackObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTerm2.setRunbackTotalMarks(15);
							wtPayLoadTerm2.setRunbackRemarks(runbackResult.getRemark());     /////////Remarks
						}
						if (runbackResult.getTermId() == 3) {
							Integer obtainedMarks = runbackResult.getObtainedMarks() == null ? 0
									: runbackResult.getObtainedMarks();
							wtPayLoadTerm3.setRunbackObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTerm3.setRunbackTotalMarks(15);
							wtPayLoadTerm3.setRunbackRemarks(runbackResult.getRemark());     /////////Remarks
						}
						if (runbackResult.getTermId() == 7) {
							Integer obtainedMarks = runbackResult.getObtainedMarks() == null ? 0
									: runbackResult.getObtainedMarks();
							wtPayLoadTech2.setRunbackObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
							// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
							wtPayLoadTech2.setRunbackTotalMarks(15);
							wtPayLoadTech2.setRunbackRemarks(runbackResult.getRemark());     /////////Remarks
						}
					}
				}

				if (cad.getTerm() == 1) {
					payload1.getTerm1().add(wtPayLoadTerm1);
				}
				if (cad.getTerm() == 2) {
					payload1.getTerm2().add(wtPayLoadTerm2);
				}
				if (cad.getTerm() == 3) {
					payload1.getTerm3().add(wtPayLoadTerm3);
				}
				if (cad.getTerm() == 7) {
					payload1.getTech2().add(wtPayLoadTech2);
				}
			}
		}

		return payload1;
	}
}
