package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicCreditForExcellenceResult;
import com.example.demo.model.AcademicLeadershipMatrixResult;
import com.example.demo.model.AcademicOqMatrixResult;
import com.example.demo.model.CampMarksResult;
import com.example.demo.model.DRILLResult;
import com.example.demo.model.EdAssessmentOqFinal;
import com.example.demo.model.EdossierPtResult;
import com.example.demo.model.GSO2ServiceSubjectBMTResult;
import com.example.demo.model.IntellectualSkillsResult;
import com.example.demo.model.IntellectualSkillsSubject;
import com.example.demo.model.IntellectualSkillsSubjectResult;
import com.example.demo.model.OqDrillResult;
import com.example.demo.model.OqEqtnResult;
import com.example.demo.model.OqMarksResult;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.model.ServiceBmt2Result;
import com.example.demo.model.SportsResult;
import com.example.demo.model.TRG_EQTNResult;
import com.example.demo.model.WeaponTrainingResult;
import com.example.demo.payload.EdAssessmentOfGcPayload1;
import com.example.demo.payload.EdAssessmentOfGcPayload2;
import com.example.demo.payload.EdOqMatrixPayload;
import com.example.demo.payload.OqMatrixTermPayload;
import com.example.demo.repository.AcademicLeadershipMatrixResultRepo;
import com.example.demo.repository.AcademicOqMatrixResultRepo;
import com.example.demo.repository.CadetWeaponTrainingResultRepo;
import com.example.demo.repository.CampMarksResultRepo;
import com.example.demo.repository.DRILLResultRepo;
import com.example.demo.repository.EdossierPtResultRepository;
import com.example.demo.repository.GSO2ServiceSubjectBMTResultRepo;
import com.example.demo.repository.RunbackRouteMrRepo;
import com.example.demo.repository.ServiceBmt2ResultRepository;
import com.example.demo.repository.SportsResultRepo;
import com.example.demo.repository.TRG_EQTNResultRepo;
import com.example.demo.service.AcademicCreditForExcellenceResultService;
import com.example.demo.service.AcademicOqMatrixResultsService;
import com.example.demo.service.AdminBattalionService;
import com.example.demo.service.EdAssessmentOfGcService;
import com.example.demo.service.EdAssessmentOqFinalService;
import com.example.demo.service.IntellectualSkillsResultService;
import com.example.demo.service.IntellectualSkillsSubjectService;
import com.example.demo.service.OqMarksResultService;

@Service
public class EdAssessmentOfGcServiceImpl implements EdAssessmentOfGcService {

	@Autowired
	private OqMarksResultService oqMarksResultService;
	@Autowired
	private AcademicOqMatrixResultRepo academicOqMatrixResultRepo;

	@Autowired
	private DRILLResultRepo dRILLResultRepo;

	@Autowired
	private TRG_EQTNResultRepo tRG_EQTNResultRepo;

	@Autowired
	private EdossierPtResultRepository edossierPtResultRepository;

	@Autowired
	private RunbackRouteMrRepo runbackRouteMrRepo;

	@Autowired
	AdminBattalionService adminBattalionService;

	@Autowired
	private SportsResultRepo sportsResultRepo;

	@Autowired
	AcademicOqMatrixResultsService academicOqMatrixResultsService;

	@Autowired
	private CadetWeaponTrainingResultRepo cadetWeaponTrainingResultRepo;

	@Autowired
	private GSO2ServiceSubjectBMTResultRepo gSO2ServiceSubjectBMTResultRepo;

	@Autowired
	private ServiceBmt2ResultRepository serviceBmt2ResultRepository;

	@Autowired
	private CampMarksResultRepo campMarksResultRepo;

	@Autowired
	private IntellectualSkillsResultService intellectualSkillsResultService;

	@Autowired
	IntellectualSkillsSubjectService intellectualSkillsSubjectService;

	@Autowired
	private AcademicLeadershipMatrixResultRepo academicLeadershipMatrixResultRepo;

	@Autowired
	private AcademicCreditForExcellenceResultService academicCreditForExcellenceResultService;

	@Autowired
	private EdAssessmentOqFinalService edAssessmentOqFinalService;

	@Override
	public EdAssessmentOfGcPayload1 getEdAssessmentOfGc(String serviceId) {
		// TODO Auto-generated method stub
		EdAssessmentOfGcPayload1 payload1 = new EdAssessmentOfGcPayload1();
		payload1.setTerm1(new ArrayList<EdAssessmentOfGcPayload2>());
		payload1.setTerm2(new ArrayList<EdAssessmentOfGcPayload2>());
		payload1.setTerm3(new ArrayList<EdAssessmentOfGcPayload2>());
//		List<EdAssessmentOfGcPayload2> term1= payload1.getTerm1();
//		List<EdAssessmentOfGcPayload2> term2= payload1.getTerm2();
//		List<EdAssessmentOfGcPayload2> term3= payload1.getTerm3();

		// -----******** FOR OQ MARKS (Total OQ)******---------
		List<OqMarksResult> OqMarksList = oqMarksResultService.findByServiceIdAndEntryTypeId(serviceId, 2L);
		// term1.add(new EdAssessmentOfGcPayload2());
		if (OqMarksList.size() > 0) {

			for (OqMarksResult oqMarksResult : OqMarksList) {
				// if (oqMarksResult.getTermId() == 1) {
				EdAssessmentOfGcPayload2 oqPayLoad2 = new EdAssessmentOfGcPayload2();
				Integer total = 0;
				Double totalObtained = 0.0;
				oqPayLoad2.setSubjectName("Total OQ");
				oqPayLoad2.setOqBnTotalMarks(oqMarksResult.getTotalMarksBnCdr());
				oqPayLoad2.setOqBnObtainedMarks(Double.parseDouble(oqMarksResult.getObtainedMarksBnCdr().toString()));

				oqPayLoad2.setOqPlTotalMarks(oqMarksResult.getTotalMarksPlCdr());
				oqPayLoad2.setOqPlObtainedMarks(Double.parseDouble(oqMarksResult.getObtainedMarksPlCdr().toString()));

				oqPayLoad2.setOqCoyTotalMarks(oqMarksResult.getTotalMarksCoyCdr());
				oqPayLoad2.setOqCoyObtainedMarks(Double.parseDouble(oqMarksResult.getObtainedMarksCoyCdr().toString()));
				total = oqMarksResult.getTotalMarksBnCdr() + oqMarksResult.getTotalMarksPlCdr()
						+ oqMarksResult.getTotalMarksCoyCdr();
				totalObtained = (oqMarksResult.getObtainedMarksBnCdr() == null ? 0
						: oqMarksResult.getObtainedMarksBnCdr())
						+ (oqMarksResult.getObtainedMarksPlCdr() == null ? 0 : oqMarksResult.getObtainedMarksPlCdr())
						+ (oqMarksResult.getObtainedMarksCoyCdr() == null ? 0 : oqMarksResult.getObtainedMarksCoyCdr());
				oqPayLoad2.setTotalMarks(total);
				oqPayLoad2.setObtainedMarks(totalObtained);

				if (oqMarksResult.getTermId() == 1) {
					payload1.getTerm1().add(oqPayLoad2);
				}
				if (oqMarksResult.getTermId() == 2) {
					payload1.getTerm2().add(oqPayLoad2);
				}
				if (oqMarksResult.getTermId() == 3) {
					payload1.getTerm3().add(oqPayLoad2);
				}
			}
		}

		// -----******** FOR OqMatrixTermPayload (Acad, Drill, Eqtn) ******---------

		// -----******** FOR OQ MATRIX (Acad) ******---------

		List<AcademicOqMatrixResult> oqMatrixList = academicOqMatrixResultRepo.findByServiceIdOrderByTermId(serviceId);
		if (oqMatrixList.size() > 0) {
			for (AcademicOqMatrixResult oqMatrixResult : oqMatrixList) {
				EdAssessmentOfGcPayload2 oqPayLoad2 = new EdAssessmentOfGcPayload2();
				oqPayLoad2.setSubjectName("Acad");
				oqPayLoad2.setObtainedMarks(oqMatrixResult.getObtainedMarks());
				oqPayLoad2.setTotalMarks(oqMatrixResult.getTotalMarks());
				if (oqMatrixResult.getTermId() == 1) {
					payload1.getTerm1().add(oqPayLoad2);
				}
				if (oqMatrixResult.getTermId() == 2) {
					payload1.getTerm2().add(oqPayLoad2);
				}
				if (oqMatrixResult.getTermId() == 3) {
					payload1.getTerm3().add(oqPayLoad2);
				}
			}
		}
		return payload1;
	}

	// *******************************VERSION -2*****************************

	@Override
	public EdAssessmentOfGcPayload1 getEdAssessmentOfGcNew1(String serviceId) {
		EdAssessmentOfGcPayload1 payload1 = new EdAssessmentOfGcPayload1();
		payload1.setTerm1(new ArrayList<EdAssessmentOfGcPayload2>());
		payload1.setTerm2(new ArrayList<EdAssessmentOfGcPayload2>());
		payload1.setTerm3(new ArrayList<EdAssessmentOfGcPayload2>());
		payload1.setTech2(new ArrayList<EdAssessmentOfGcPayload2>());

		// -----******** FOR OQ MARKS (Total OQ)******---------
		List<OqMarksResult> OqMarksList = oqMarksResultService.findByServiceIdAndEntryTypeId(serviceId, 2L);
		EdAssessmentOfGcPayload2 totalOqPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 totalOqPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 totalOqPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 totalOqPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 totalOqPayLoad2 = new EdAssessmentOfGcPayload2();
		totalOqPayLoadTerm1.setCategory("Officer Quotient");
		totalOqPayLoadTerm1.setSubjectName("Total OQ");
		totalOqPayLoadTerm2.setCategory("Officer Quotient");
		totalOqPayLoadTerm2.setSubjectName("Total OQ");
		totalOqPayLoadTerm3.setCategory("Officer Quotient");
		totalOqPayLoadTerm3.setSubjectName("Total OQ");
		totalOqPayLoadTech2.setCategory("Officer Quotient");
		totalOqPayLoadTech2.setSubjectName("Total OQ");
		totalOqPayLoad2.setCategory("Officer Quotient");
		totalOqPayLoad2.setSubjectName("Total OQ");
		if (OqMarksList.size() > 0) {

			for (OqMarksResult oqMarksResult : OqMarksList) {
				Integer total = 0;
				Double totalObtained = 0.0;
				totalOqPayLoad2.setOqBnTotalMarks(oqMarksResult.getTotalMarksBnCdr());
				totalOqPayLoad2
						.setOqBnObtainedMarks(Double.parseDouble(oqMarksResult.getObtainedMarksBnCdr().toString()));

				totalOqPayLoad2.setOqPlTotalMarks(oqMarksResult.getTotalMarksPlCdr());
				totalOqPayLoad2
						.setOqPlObtainedMarks(Double.parseDouble(oqMarksResult.getObtainedMarksPlCdr().toString()));

				totalOqPayLoad2.setOqCoyTotalMarks(oqMarksResult.getTotalMarksCoyCdr());
				totalOqPayLoad2
						.setOqCoyObtainedMarks(Double.parseDouble(oqMarksResult.getObtainedMarksCoyCdr().toString()));
				total = oqMarksResult.getTotalMarksBnCdr() + oqMarksResult.getTotalMarksPlCdr()
						+ oqMarksResult.getTotalMarksCoyCdr();
				totalObtained = (oqMarksResult.getObtainedMarksBnCdr() == null ? 0
						: oqMarksResult.getObtainedMarksBnCdr())
						+ (oqMarksResult.getObtainedMarksPlCdr() == null ? 0 : oqMarksResult.getObtainedMarksPlCdr())
						+ (oqMarksResult.getObtainedMarksCoyCdr() == null ? 0 : oqMarksResult.getObtainedMarksCoyCdr());
				totalOqPayLoad2.setTotalMarks(total);
				totalOqPayLoad2.setObtainedMarks(totalObtained);

				if (oqMarksResult.getTermId() == 1) {
					totalOqPayLoadTerm1 = totalOqPayLoad2;
				}
				if (oqMarksResult.getTermId() == 2) {
					totalOqPayLoadTerm2 = totalOqPayLoad2;
				}
				if (oqMarksResult.getTermId() == 3) {
					totalOqPayLoadTerm3 = totalOqPayLoad2;
				}
				if (oqMarksResult.getTermId() == 7) {
					totalOqPayLoadTech2 = totalOqPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(totalOqPayLoadTerm1);
		payload1.getTerm2().add(totalOqPayLoadTerm2);
		payload1.getTerm3().add(totalOqPayLoadTerm3);
		payload1.getTech2().add(totalOqPayLoadTech2);

		// -----******** FOR OqMatrixTermPayload (Acad, Drill, Eqtn) ******---------

		// List<AcademicOqMatrixResult> acadList =
		// academicOqMatrixResultRepo.findByServiceIdOrderByTermId(serviceId);
		OqMatrixTermPayload oqMatrixTermPayload = academicOqMatrixResultsService.findOqMatrixDrillEqtn(serviceId);
		if (oqMatrixTermPayload != null) {
			EdOqMatrixPayload term1 = oqMatrixTermPayload.getTerm1();
			EdOqMatrixPayload term2 = oqMatrixTermPayload.getTerm2();
			EdOqMatrixPayload term3 = oqMatrixTermPayload.getTerm3();
			EdOqMatrixPayload tech2 = oqMatrixTermPayload.getTech2();
			if (term1 != null) {
				List<AcademicOqMatrixResult> oqMatList = term1.getOqMatrix();
				AcademicOqMatrixResult oqMat = null;
				if (oqMatList.size() == 1) {
					oqMat = oqMatList.get(0);
				}

				List<OqEqtnResult> eqtnList = term1.getOqEqtn();
				OqEqtnResult eqtn = null;
				if (eqtnList.size() == 1) {
					eqtn = eqtnList.get(0);
				}

				List<OqDrillResult> drillList = term1.getOqDrill();
				OqDrillResult drill = null;
				if (drillList.size() == 1) {
					drill = drillList.get(0);
				}
				if (oqMat != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(oqMat.getTotalMarks());
					acadPayLoad2.setObtainedMarks(oqMat.getObtainedMarks());
					payload1.getTerm1().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm1().add(acadPayLoad2);
				}

				if (drill != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(drill.getTotalMarks());
					acadPayLoad2.setObtainedMarks(drill.getObtainedMarks());
					payload1.getTerm1().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm1().add(acadPayLoad2);
				}
				if (eqtn != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(eqtn.getTotalMarks());
					acadPayLoad2.setObtainedMarks(eqtn.getObtainedMarks());
					payload1.getTerm1().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm1().add(acadPayLoad2);
				}
			} else {
				// EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				acadPayLoad2.setCategory("Officer Quotient");
				acadPayLoad2.setSubjectName("Acad");
				acadPayLoad2.setTotalMarks(null);
				acadPayLoad2.setObtainedMarks(null);

				EdAssessmentOfGcPayload2 drillPayLoad2 = new EdAssessmentOfGcPayload2();
				drillPayLoad2.setCategory("Officer Quotient");
				drillPayLoad2.setSubjectName("Drill");
				drillPayLoad2.setTotalMarks(null);
				drillPayLoad2.setObtainedMarks(null);
				EdAssessmentOfGcPayload2 eqtnPayLoad2 = new EdAssessmentOfGcPayload2();
				eqtnPayLoad2.setCategory("Officer Quotient");
				eqtnPayLoad2.setSubjectName("Eqtn");
				eqtnPayLoad2.setTotalMarks(null);
				eqtnPayLoad2.setObtainedMarks(null);
				payload1.getTerm1().add(acadPayLoad2);
				payload1.getTerm1().add(drillPayLoad2);
				payload1.getTerm1().add(eqtnPayLoad2);
			}

			if (term2 != null) {
				List<AcademicOqMatrixResult> oqMatList = term2.getOqMatrix();
				AcademicOqMatrixResult oqMat = null;
				if (oqMatList.size() == 1) {
					oqMat = oqMatList.get(0);
				}

				List<OqEqtnResult> eqtnList = term2.getOqEqtn();
				OqEqtnResult eqtn = null;
				if (eqtnList.size() == 1) {
					eqtn = eqtnList.get(0);
				}

				List<OqDrillResult> drillList = term2.getOqDrill();
				OqDrillResult drill = null;
				if (drillList.size() == 1) {
					drill = drillList.get(0);
				}
				if (oqMat != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(oqMat.getTotalMarks());
					acadPayLoad2.setObtainedMarks(oqMat.getObtainedMarks());
					payload1.getTerm2().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm2().add(acadPayLoad2);
				}

				if (drill != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(drill.getTotalMarks());
					acadPayLoad2.setObtainedMarks(drill.getObtainedMarks());
					payload1.getTerm2().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm2().add(acadPayLoad2);
				}
				if (eqtn != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(eqtn.getTotalMarks());
					acadPayLoad2.setObtainedMarks(eqtn.getObtainedMarks());
					payload1.getTerm2().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm2().add(acadPayLoad2);
				}
			} else {
				// EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				acadPayLoad2.setCategory("Officer Quotient");
				acadPayLoad2.setSubjectName("Acad");
				acadPayLoad2.setTotalMarks(null);
				acadPayLoad2.setObtainedMarks(null);

				EdAssessmentOfGcPayload2 drillPayLoad2 = new EdAssessmentOfGcPayload2();
				drillPayLoad2.setCategory("Officer Quotient");
				drillPayLoad2.setSubjectName("Drill");
				drillPayLoad2.setTotalMarks(null);
				drillPayLoad2.setObtainedMarks(null);
				EdAssessmentOfGcPayload2 eqtnPayLoad2 = new EdAssessmentOfGcPayload2();
				eqtnPayLoad2.setCategory("Officer Quotient");
				eqtnPayLoad2.setSubjectName("Eqtn");
				eqtnPayLoad2.setTotalMarks(null);
				eqtnPayLoad2.setObtainedMarks(null);
				payload1.getTerm2().add(acadPayLoad2);
				payload1.getTerm2().add(drillPayLoad2);
				payload1.getTerm2().add(eqtnPayLoad2);

			}

			if (term3 != null) {
				List<AcademicOqMatrixResult> oqMatList = term3.getOqMatrix();
				AcademicOqMatrixResult oqMat = null;
				if (oqMatList.size() == 1) {
					oqMat = oqMatList.get(0);
				}

				List<OqEqtnResult> eqtnList = term3.getOqEqtn();
				OqEqtnResult eqtn = null;
				if (eqtnList.size() == 1) {
					eqtn = eqtnList.get(0);
				}

				List<OqDrillResult> drillList = term3.getOqDrill();
				OqDrillResult drill = null;
				if (drillList.size() == 1) {
					drill = drillList.get(0);
				}
				if (oqMat != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(oqMat.getTotalMarks());
					acadPayLoad2.setObtainedMarks(oqMat.getObtainedMarks());
					payload1.getTerm3().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm3().add(acadPayLoad2);
				}

				if (drill != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(drill.getTotalMarks());
					acadPayLoad2.setObtainedMarks(drill.getObtainedMarks());
					payload1.getTerm3().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm3().add(acadPayLoad2);
				}
				if (eqtn != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(eqtn.getTotalMarks());
					acadPayLoad2.setObtainedMarks(eqtn.getObtainedMarks());
					payload1.getTerm3().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTerm3().add(acadPayLoad2);
				}
			} else {
				// EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				acadPayLoad2.setCategory("Officer Quotient");
				acadPayLoad2.setSubjectName("Acad");
				acadPayLoad2.setTotalMarks(null);
				acadPayLoad2.setObtainedMarks(null);

				EdAssessmentOfGcPayload2 drillPayLoad2 = new EdAssessmentOfGcPayload2();
				drillPayLoad2.setCategory("Officer Quotient");
				drillPayLoad2.setSubjectName("Drill");
				drillPayLoad2.setTotalMarks(null);
				drillPayLoad2.setObtainedMarks(null);
				EdAssessmentOfGcPayload2 eqtnPayLoad2 = new EdAssessmentOfGcPayload2();
				eqtnPayLoad2.setCategory("Officer Quotient");
				eqtnPayLoad2.setSubjectName("Eqtn");
				eqtnPayLoad2.setTotalMarks(null);
				eqtnPayLoad2.setObtainedMarks(null);
				payload1.getTerm3().add(acadPayLoad2);
				payload1.getTerm3().add(drillPayLoad2);
				payload1.getTerm3().add(eqtnPayLoad2);

			}
			if (tech2 != null) {
				List<AcademicOqMatrixResult> oqMatList = tech2.getOqMatrix();
				AcademicOqMatrixResult oqMat = null;
				if (oqMatList.size() == 1) {
					oqMat = oqMatList.get(0);
				}

				List<OqEqtnResult> eqtnList = tech2.getOqEqtn();
				OqEqtnResult eqtn = null;
				if (eqtnList.size() == 1) {
					eqtn = eqtnList.get(0);
				}

				List<OqDrillResult> drillList = tech2.getOqDrill();
				OqDrillResult drill = null;
				if (drillList.size() == 1) {
					drill = drillList.get(0);
				}
				if (oqMat != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(oqMat.getTotalMarks());
					acadPayLoad2.setObtainedMarks(oqMat.getObtainedMarks());
					payload1.getTech2().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Acad");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTech2().add(acadPayLoad2);
				}

				if (drill != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(drill.getTotalMarks());
					acadPayLoad2.setObtainedMarks(drill.getObtainedMarks());
					payload1.getTech2().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Drill");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTech2().add(acadPayLoad2);
				}
				if (eqtn != null) {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(eqtn.getTotalMarks());
					acadPayLoad2.setObtainedMarks(eqtn.getObtainedMarks());
					payload1.getTech2().add(acadPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
					acadPayLoad2.setCategory("Officer Quotient");
					acadPayLoad2.setSubjectName("Eqtn");
					acadPayLoad2.setTotalMarks(null);
					acadPayLoad2.setObtainedMarks(null);
					payload1.getTech2().add(acadPayLoad2);
				}
			} else {
				// EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
				acadPayLoad2.setCategory("Officer Quotient");
				acadPayLoad2.setSubjectName("Acad");
				acadPayLoad2.setTotalMarks(null);
				acadPayLoad2.setObtainedMarks(null);

				EdAssessmentOfGcPayload2 drillPayLoad2 = new EdAssessmentOfGcPayload2();
				drillPayLoad2.setCategory("Officer Quotient");
				drillPayLoad2.setSubjectName("Drill");
				drillPayLoad2.setTotalMarks(null);
				drillPayLoad2.setObtainedMarks(null);
				EdAssessmentOfGcPayload2 eqtnPayLoad2 = new EdAssessmentOfGcPayload2();
				eqtnPayLoad2.setCategory("Officer Quotient");
				eqtnPayLoad2.setSubjectName("Eqtn");
				eqtnPayLoad2.setTotalMarks(null);
				eqtnPayLoad2.setObtainedMarks(null);
				payload1.getTech2().add(acadPayLoad2);
				payload1.getTech2().add(drillPayLoad2);
				payload1.getTech2().add(eqtnPayLoad2);

			}

		}

		else {
			EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
			acadPayLoad2.setCategory("Officer Quotient");
			acadPayLoad2.setSubjectName("Acad");
			acadPayLoad2.setTotalMarks(null);
			acadPayLoad2.setObtainedMarks(null);
			EdAssessmentOfGcPayload2 drillPayLoad2 = new EdAssessmentOfGcPayload2();
			drillPayLoad2.setCategory("Officer Quotient");
			drillPayLoad2.setSubjectName("Drill");
			drillPayLoad2.setTotalMarks(null);
			drillPayLoad2.setObtainedMarks(null);
			EdAssessmentOfGcPayload2 eqtnPayLoad2 = new EdAssessmentOfGcPayload2();
			eqtnPayLoad2.setCategory("Officer Quotient");
			eqtnPayLoad2.setSubjectName("Eqtn");
			eqtnPayLoad2.setTotalMarks(null);
			eqtnPayLoad2.setObtainedMarks(null);
			payload1.getTerm1().add(acadPayLoad2);
			payload1.getTerm1().add(drillPayLoad2);
			payload1.getTerm1().add(eqtnPayLoad2);

			payload1.getTerm2().add(acadPayLoad2);
			payload1.getTerm2().add(drillPayLoad2);
			payload1.getTerm2().add(eqtnPayLoad2);

			payload1.getTerm3().add(acadPayLoad2);
			payload1.getTerm3().add(drillPayLoad2);
			payload1.getTerm3().add(eqtnPayLoad2);

			payload1.getTech2().add(acadPayLoad2);
			payload1.getTech2().add(drillPayLoad2);
			payload1.getTech2().add(eqtnPayLoad2);
		}

		// -----******** FOR OQ MATRIX (Acad) ******---------

//		List<AcademicOqMatrixResult> oqMatrixList = academicOqMatrixResultRepo.findByServiceIdOrderByTermId(serviceId);
//		EdAssessmentOfGcPayload2 acadPayLoadTerm1 = new EdAssessmentOfGcPayload2();
//		EdAssessmentOfGcPayload2 acadPayLoadTerm2 = new EdAssessmentOfGcPayload2();
//		EdAssessmentOfGcPayload2 acadPayLoadTerm3 = new EdAssessmentOfGcPayload2();
//		EdAssessmentOfGcPayload2 acadPayLoad2 = new EdAssessmentOfGcPayload2();
//		acadPayLoadTerm1.setSubjectName("Acad");
//		acadPayLoadTerm2.setSubjectName("Acad");
//		acadPayLoadTerm3.setSubjectName("Acad");
//		acadPayLoad2.setSubjectName("Acad");
//		if (oqMatrixList.size() > 0) {
//			for (AcademicOqMatrixResult oqMatrixResult : oqMatrixList) {
//				acadPayLoad2.setObtainedMarks(oqMatrixResult.getObtainedMarks());
//				acadPayLoad2.setTotalMarks(oqMatrixResult.getTotalMarks());
//				if (oqMatrixResult.getTermId() == 1) {
//					acadPayLoadTerm1 = acadPayLoad2;
//				}
//				if (oqMatrixResult.getTermId() == 2) {
//					acadPayLoadTerm2 = acadPayLoad2;
//				}
//				if (oqMatrixResult.getTermId() == 3) {
//					acadPayLoadTerm3 = acadPayLoad2;
//				}
//			}
//		}
//		payload1.getTerm1().add(acadPayLoadTerm1);
//		payload1.getTerm2().add(acadPayLoadTerm2);
//		payload1.getTerm3().add(acadPayLoadTerm3);
//

		// -----******** FOR TRG_EQTNResult (OQ Marks Awarded by Comdt)******---------
		List<EdAssessmentOqFinal> oqFinalPayloadList = edAssessmentOqFinalService.findByServiceId(serviceId);
		if (oqFinalPayloadList != null && oqFinalPayloadList.size() > 0) {
			for (EdAssessmentOqFinal oqFinalPayload : oqFinalPayloadList) {
				if (oqFinalPayload.getTermId() == 1) {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					Double dcCiTotalMarks = oqFinalPayload.getDcCiTotalMarks() == null ? 0
							: oqFinalPayload.getDcCiTotalMarks();
					dcCiPayLoad2.setTotalMarks(Integer.parseInt(dcCiTotalMarks.toString()));
					Double dcCiObtainedMarks = oqFinalPayload.getDcCiObtainedMarks() == null ? 0
							: oqFinalPayload.getDcCiObtainedMarks();
					dcCiPayLoad2.setObtainedMarks(dcCiObtainedMarks);
					payload1.getTerm1().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					Double comdtTotalMarks = oqFinalPayload.getComdtTotalMarks() == null ? 0
							: oqFinalPayload.getComdtTotalMarks();
					comdtPayLoad2.setTotalMarks(Integer.parseInt(comdtTotalMarks.toString()));
					Double comdtObtainedMarks = oqFinalPayload.getComdtObtainedMarks() == null ? 0
							: oqFinalPayload.getComdtObtainedMarks();
					comdtPayLoad2.setObtainedMarks(comdtObtainedMarks);
					payload1.getTerm1().add(comdtPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					dcCiPayLoad2.setTotalMarks(null);
					dcCiPayLoad2.setObtainedMarks(null);
					payload1.getTerm1().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					comdtPayLoad2.setTotalMarks(null);
					comdtPayLoad2.setObtainedMarks(null);
					payload1.getTerm1().add(comdtPayLoad2);
				}

				if (oqFinalPayload.getTermId() == 2) {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					Double dcCiTotalMarks = oqFinalPayload.getDcCiTotalMarks() == null ? 0
							: oqFinalPayload.getDcCiTotalMarks();
					dcCiPayLoad2.setTotalMarks(Integer.parseInt(dcCiTotalMarks.toString()));
					Double dcCiObtainedMarks = oqFinalPayload.getDcCiObtainedMarks() == null ? 0
							: oqFinalPayload.getDcCiObtainedMarks();
					dcCiPayLoad2.setObtainedMarks(dcCiObtainedMarks);
					payload1.getTerm2().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					Double comdtTotalMarks = oqFinalPayload.getComdtTotalMarks() == null ? 0
							: oqFinalPayload.getComdtTotalMarks();
					comdtPayLoad2.setTotalMarks(Integer.parseInt(comdtTotalMarks.toString()));
					Double comdtObtainedMarks = oqFinalPayload.getComdtObtainedMarks() == null ? 0
							: oqFinalPayload.getComdtObtainedMarks();
					comdtPayLoad2.setObtainedMarks(comdtObtainedMarks);
					payload1.getTerm2().add(comdtPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					dcCiPayLoad2.setTotalMarks(null);
					dcCiPayLoad2.setObtainedMarks(null);
					payload1.getTerm2().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					comdtPayLoad2.setTotalMarks(null);
					comdtPayLoad2.setObtainedMarks(null);
					payload1.getTerm2().add(comdtPayLoad2);
				}
				if (oqFinalPayload.getTermId() == 3) {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					Double dcCiTotalMarks = oqFinalPayload.getDcCiTotalMarks() == null ? 0
							: oqFinalPayload.getDcCiTotalMarks();
					dcCiPayLoad2.setTotalMarks(Integer.parseInt(dcCiTotalMarks.toString()));
					Double dcCiObtainedMarks = oqFinalPayload.getDcCiObtainedMarks() == null ? 0
							: oqFinalPayload.getDcCiObtainedMarks();
					dcCiPayLoad2.setObtainedMarks(dcCiObtainedMarks);
					payload1.getTerm3().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					Double comdtTotalMarks = oqFinalPayload.getComdtTotalMarks() == null ? 0
							: oqFinalPayload.getComdtTotalMarks();
					comdtPayLoad2.setTotalMarks(Integer.parseInt(comdtTotalMarks.toString()));
					Double comdtObtainedMarks = oqFinalPayload.getComdtObtainedMarks() == null ? 0
							: oqFinalPayload.getComdtObtainedMarks();
					comdtPayLoad2.setObtainedMarks(comdtObtainedMarks);
					payload1.getTerm3().add(comdtPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					dcCiPayLoad2.setTotalMarks(null);
					dcCiPayLoad2.setObtainedMarks(null);
					payload1.getTerm3().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					comdtPayLoad2.setTotalMarks(null);
					comdtPayLoad2.setObtainedMarks(null);
					payload1.getTerm3().add(comdtPayLoad2);
				}
				if (oqFinalPayload.getTermId() == 7) {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					Double dcCiTotalMarks = oqFinalPayload.getDcCiTotalMarks() == null ? 0
							: oqFinalPayload.getDcCiTotalMarks();
					dcCiPayLoad2.setTotalMarks(Integer.parseInt(dcCiTotalMarks.toString()));
					Double dcCiObtainedMarks = oqFinalPayload.getDcCiObtainedMarks() == null ? 0
							: oqFinalPayload.getDcCiObtainedMarks();
					dcCiPayLoad2.setObtainedMarks(dcCiObtainedMarks);
					payload1.getTech2().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					Double comdtTotalMarks = oqFinalPayload.getComdtTotalMarks() == null ? 0
							: oqFinalPayload.getComdtTotalMarks();
					comdtPayLoad2.setTotalMarks(Integer.parseInt(comdtTotalMarks.toString()));
					Double comdtObtainedMarks = oqFinalPayload.getComdtObtainedMarks() == null ? 0
							: oqFinalPayload.getComdtObtainedMarks();
					comdtPayLoad2.setObtainedMarks(comdtObtainedMarks);
					payload1.getTech2().add(comdtPayLoad2);

				} else {
					EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
					dcCiPayLoad2.setCategory("Officer Quotient");
					dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
					dcCiPayLoad2.setTotalMarks(null);
					dcCiPayLoad2.setObtainedMarks(null);
					payload1.getTech2().add(dcCiPayLoad2);

					EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
					comdtPayLoad2.setCategory("Officer Quotient");
					comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
					comdtPayLoad2.setTotalMarks(null);
					comdtPayLoad2.setObtainedMarks(null);
					payload1.getTech2().add(comdtPayLoad2);
				}

			}
		} else {
			EdAssessmentOfGcPayload2 dcCiPayLoad2 = new EdAssessmentOfGcPayload2();
			dcCiPayLoad2.setCategory("Officer Quotient");
			dcCiPayLoad2.setSubjectName("OQ Marks Awarded by Dy Comdt & CI");
			dcCiPayLoad2.setTotalMarks(null);
			dcCiPayLoad2.setObtainedMarks(null);
			payload1.getTerm1().add(dcCiPayLoad2);
			payload1.getTerm2().add(dcCiPayLoad2);
			payload1.getTerm3().add(dcCiPayLoad2);
			payload1.getTech2().add(dcCiPayLoad2);

			EdAssessmentOfGcPayload2 comdtPayLoad2 = new EdAssessmentOfGcPayload2();
			comdtPayLoad2.setCategory("Officer Quotient");
			comdtPayLoad2.setSubjectName("OQ Marks Awarded by Comdt");
			comdtPayLoad2.setTotalMarks(null);
			comdtPayLoad2.setObtainedMarks(null);
			payload1.getTerm1().add(comdtPayLoad2);
			payload1.getTerm2().add(comdtPayLoad2);
			payload1.getTerm3().add(comdtPayLoad2);
			payload1.getTech2().add(comdtPayLoad2);
		}

		// -----******** FOR EdossierPtResult (PT)******---------

		List<EdossierPtResult> ptList = edossierPtResultRepository.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 ptPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 ptPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 ptPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 ptPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 ptPayLoad2 = new EdAssessmentOfGcPayload2();
		ptPayLoadTerm1.setCategory("Physicals");
		ptPayLoadTerm1.setSubjectName("PT");
		ptPayLoadTerm2.setCategory("Physicals");
		ptPayLoadTerm2.setSubjectName("PT");
		ptPayLoadTerm3.setCategory("Physicals");
		ptPayLoadTerm3.setSubjectName("PT");
		ptPayLoadTech2.setCategory("Physicals");
		ptPayLoadTech2.setSubjectName("PT");
		ptPayLoad2.setCategory("Physicals");
		ptPayLoad2.setSubjectName("PT");
		if (ptList.size() > 0) {
			for (EdossierPtResult ptResult : ptList) {
				ptPayLoad2.setObtainedMarks(ptResult.getObtainedMarks());
				ptPayLoad2.setTotalMarks(ptResult.getTotalMarks());
				if (ptResult.getTermId() == 1) {
					ptPayLoadTerm1 = ptPayLoad2;
				}
				if (ptResult.getTermId() == 2) {
					ptPayLoadTerm2 = ptPayLoad2;
				}
				if (ptResult.getTermId() == 3) {
					ptPayLoadTerm3 = ptPayLoad2;
				}
				if (ptResult.getTermId() == 7) {
					ptPayLoadTech2 = ptPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(ptPayLoadTerm1);
		payload1.getTerm2().add(ptPayLoadTerm2);
		payload1.getTerm3().add(ptPayLoadTerm3);
		payload1.getTech2().add(ptPayLoadTech2);
		// -----******** FOR RunbackRouteMr (Speed Marches/Runback)******---------

		EdAssessmentOfGcPayload2 runRoutePayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 runRoutePayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 runRoutePayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 runRoutePayLoadTech2 = new EdAssessmentOfGcPayload2();
		runRoutePayLoadTerm1.setCategory("Physicals");
		runRoutePayLoadTerm1.setSubjectName("Speed Marches/Runback");
		runRoutePayLoadTerm2.setCategory("Physicals");
		runRoutePayLoadTerm2.setSubjectName("Speed Marches/Runback");
		runRoutePayLoadTerm3.setCategory("Physicals");
		runRoutePayLoadTerm3.setSubjectName("Speed Marches/Runback");
		runRoutePayLoadTech2.setCategory("Physicals");
		runRoutePayLoadTech2.setSubjectName("Speed Marches/Runback");
		RunbackRouteMr sumOfRunRouteTerm1 = adminBattalionService.getResultByServiceIdAndTermId(serviceId, 1L);
		runRoutePayLoadTerm1.setTotalMarks(65);
		runRoutePayLoadTerm1.setObtainedMarks(Double.parseDouble(sumOfRunRouteTerm1.getSum().toString()));
		RunbackRouteMr sumOfRunRouteTerm2 = adminBattalionService.getResultByServiceIdAndTermId(serviceId, 2L);
		runRoutePayLoadTerm2.setTotalMarks(65);
		runRoutePayLoadTerm2.setObtainedMarks(Double.parseDouble(sumOfRunRouteTerm2.getSum().toString()));
		RunbackRouteMr sumOfRunRouteTerm3 = adminBattalionService.getResultByServiceIdAndTermId(serviceId, 3L);
		runRoutePayLoadTerm3.setTotalMarks(65);
		runRoutePayLoadTerm3.setObtainedMarks(Double.parseDouble(sumOfRunRouteTerm3.getSum().toString()));
		RunbackRouteMr sumOfRunRouteTech2 = adminBattalionService.getResultByServiceIdAndTermId(serviceId, 7L);
		runRoutePayLoadTech2.setTotalMarks(65);
		runRoutePayLoadTech2.setObtainedMarks(Double.parseDouble(sumOfRunRouteTech2.getSum().toString()));
		payload1.getTerm1().add(runRoutePayLoadTerm1);
		payload1.getTerm2().add(runRoutePayLoadTerm2);
		payload1.getTerm3().add(runRoutePayLoadTerm3);
		payload1.getTech2().add(runRoutePayLoadTech2);

		// -----******** FOR SportsResult (Games)******---------

		List<SportsResult> sportList = sportsResultRepo.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 sportPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 sportPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 sportPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 sportPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 sportPayLoad2 = new EdAssessmentOfGcPayload2();
		sportPayLoadTerm1.setCategory("Physicals");
		sportPayLoadTerm1.setSubjectName("Games");
		sportPayLoadTerm2.setCategory("Physicals");
		sportPayLoadTerm2.setSubjectName("Games");
		sportPayLoadTerm3.setCategory("Physicals");
		sportPayLoadTerm3.setSubjectName("Games");
		sportPayLoadTech2.setCategory("Physicals");
		sportPayLoadTech2.setSubjectName("Games");
		sportPayLoad2.setCategory("Physicals");
		sportPayLoad2.setSubjectName("Games");
		if (sportList.size() > 0) {
			for (SportsResult sportResult : sportList) {
				sportPayLoad2.setObtainedMarks(sportResult.getObtainedMarks());
				sportPayLoad2.setTotalMarks(sportResult.getTotalMarks());
				if (sportResult.getTermId() == 1) {
					sportPayLoadTerm1 = sportPayLoad2;
				}
				if (sportResult.getTermId() == 2) {
					sportPayLoadTerm2 = sportPayLoad2;
				}
				if (sportResult.getTermId() == 3) {
					sportPayLoadTerm3 = sportPayLoad2;
				}
				if (sportResult.getTermId() == 7) {
					sportPayLoadTech2 = sportPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(sportPayLoadTerm1);
		payload1.getTerm2().add(sportPayLoadTerm2);
		payload1.getTerm3().add(sportPayLoadTerm3);
		payload1.getTech2().add(sportPayLoadTech2);

		// -----******** FOR TRG_EQTNResult (Eqtn)******---------

		List<TRG_EQTNResult> eqtnList = tRG_EQTNResultRepo.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 eqtnPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 eqtnPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 eqtnPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 eqtnPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 eqtnPayLoad2 = new EdAssessmentOfGcPayload2();
		eqtnPayLoadTerm1.setCategory("Physicals");
		eqtnPayLoadTerm1.setSubjectName("Eqtn");
		eqtnPayLoadTerm2.setCategory("Physicals");
		eqtnPayLoadTerm2.setSubjectName("Eqtn");
		eqtnPayLoadTerm3.setCategory("Physicals");
		eqtnPayLoadTerm3.setSubjectName("Eqtn");
		eqtnPayLoadTech2.setCategory("Physicals");
		eqtnPayLoadTech2.setSubjectName("Eqtn");
		eqtnPayLoad2.setCategory("Physicals");
		eqtnPayLoad2.setSubjectName("Eqtn");
		if (eqtnList.size() > 0) {
			for (TRG_EQTNResult eqtnResult : eqtnList) {
				eqtnPayLoad2.setObtainedMarks(eqtnResult.getObtainedMarks());
				eqtnPayLoad2.setTotalMarks(eqtnResult.getTotalMarks());
				if (eqtnResult.getTermId() == 1) {
					eqtnPayLoadTerm1 = eqtnPayLoad2;
				}
				if (eqtnResult.getTermId() == 2) {
					eqtnPayLoadTerm2 = eqtnPayLoad2;
				}
				if (eqtnResult.getTermId() == 3) {
					eqtnPayLoadTerm3 = eqtnPayLoad2;
				}
				if (eqtnResult.getTermId() == 7) {
					eqtnPayLoadTech2 = eqtnPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(eqtnPayLoadTerm1);
		payload1.getTerm2().add(eqtnPayLoadTerm2);
		payload1.getTerm3().add(eqtnPayLoadTerm3);
		payload1.getTech2().add(eqtnPayLoadTech2);

		// -----******** FOR DRILL Marks (DRILL)******---------

		List<DRILLResult> drillList = dRILLResultRepo.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 drillPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 drillPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 drillPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 drillPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 drillPayLoad2 = new EdAssessmentOfGcPayload2();
		drillPayLoadTerm1.setCategory("Physicals");
		drillPayLoadTerm1.setSubjectName("Drill");
		drillPayLoadTerm2.setCategory("Physicals");
		drillPayLoadTerm2.setSubjectName("Drill");
		drillPayLoadTerm3.setCategory("Physicals");
		drillPayLoadTerm3.setSubjectName("Drill");
		drillPayLoadTech2.setCategory("Physicals");
		drillPayLoadTech2.setSubjectName("Drill");
		drillPayLoad2.setCategory("Physicals");
		drillPayLoad2.setSubjectName("Drill");
		if (drillList.size() > 0) {
			for (DRILLResult drillResult : drillList) {
				drillPayLoad2.setObtainedMarks(drillResult.getObtainedMarks());
				drillPayLoad2.setTotalMarks(drillResult.getTotalMarks());
				if (drillResult.getTermId() == 1) {
					drillPayLoadTerm1 = drillPayLoad2;
				}
				if (drillResult.getTermId() == 2) {
					drillPayLoadTerm2 = drillPayLoad2;
				}
				if (drillResult.getTermId() == 3) {
					drillPayLoadTerm3 = drillPayLoad2;
				}
				if (drillResult.getTermId() == 7) {
					drillPayLoadTech2 = drillPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(drillPayLoadTerm1);
		payload1.getTerm2().add(drillPayLoadTerm2);
		payload1.getTerm3().add(drillPayLoadTerm3);
		payload1.getTech2().add(drillPayLoadTech2);

		// -----******** FOR WeaponTrainingResult (Weapon Training)******---------

		List<WeaponTrainingResult> wtList = cadetWeaponTrainingResultRepo.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 wtPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 wtPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 wtPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 wtPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 wtPayLoad2 = new EdAssessmentOfGcPayload2();
		wtPayLoadTerm1.setCategory("Service Subjects");
		wtPayLoadTerm1.setSubjectName("Weapon Training");
		wtPayLoadTerm2.setCategory("Service Subjects");
		wtPayLoadTerm2.setSubjectName("Weapon Training");
		wtPayLoadTerm3.setCategory("Service Subjects");
		wtPayLoadTerm3.setSubjectName("Weapon Training");
		wtPayLoadTech2.setCategory("Service Subjects");
		wtPayLoadTech2.setSubjectName("Weapon Training");
		wtPayLoad2.setCategory("Service Subjects");
		wtPayLoad2.setSubjectName("Weapon Training");
		if (wtList.size() > 0) {
			for (WeaponTrainingResult wtResult : wtList) {
				wtPayLoad2.setObtainedMarks(wtResult.getGrandTotal());
				wtPayLoad2.setTotalMarks(wtResult.getMaxGrandTotal());
				if (wtResult.getTermId() == 1) {
					wtPayLoadTerm1 = wtPayLoad2;
				}
				if (wtResult.getTermId() == 2) {
					wtPayLoadTerm2 = wtPayLoad2;
				}
				if (wtResult.getTermId() == 3) {
					wtPayLoadTerm3 = wtPayLoad2;
				}
				if (wtResult.getTermId() == 7) {
					wtPayLoadTech2 = wtPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(wtPayLoadTerm1);
		payload1.getTerm2().add(wtPayLoadTerm2);
		payload1.getTerm3().add(wtPayLoadTerm3);
		payload1.getTech2().add(wtPayLoadTech2);

		// -----******** FOR GSO2ServiceSubjectBMTResult (BMT 1)******---------

		List<GSO2ServiceSubjectBMTResult> bmt1List = gSO2ServiceSubjectBMTResultRepo
				.findByServiceIdOrderByIdDesc(serviceId);
		EdAssessmentOfGcPayload2 bmt1PayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt1PayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt1PayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt1PayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt1PayLoad2 = new EdAssessmentOfGcPayload2();
		bmt1PayLoadTerm1.setCategory("Service Subjects");
		bmt1PayLoadTerm1.setSubjectName("BMT 1");
		bmt1PayLoadTerm2.setCategory("Service Subjects");
		bmt1PayLoadTerm2.setSubjectName("BMT 1");
		bmt1PayLoadTerm3.setCategory("Service Subjects");
		bmt1PayLoadTerm3.setSubjectName("BMT 1");
		bmt1PayLoadTech2.setCategory("Service Subjects");
		bmt1PayLoadTech2.setSubjectName("BMT 1");
		bmt1PayLoad2.setCategory("Service Subjects");
		bmt1PayLoad2.setSubjectName("BMT 1");
		if (wtList.size() > 0) {
			for (GSO2ServiceSubjectBMTResult bmt1Result : bmt1List) {
				bmt1PayLoad2.setObtainedMarks(bmt1Result.getObtainedMarks());
				// bmt1PayLoad2.setTotalMarks(Integer.parseInt(bmt1Result.getTotalMarks().toString()));
				bmt1PayLoad2.setTotalMarks(200);
				if (bmt1Result.getTermId() == 1) {
					bmt1PayLoadTerm1 = bmt1PayLoad2;
				}
				if (bmt1Result.getTermId() == 2) {
					bmt1PayLoadTerm2 = bmt1PayLoad2;
				}
				if (bmt1Result.getTermId() == 3) {
					bmt1PayLoadTerm3 = bmt1PayLoad2;
				}
				if (bmt1Result.getTermId() == 7) {
					bmt1PayLoadTech2 = bmt1PayLoad2;
				}
			}
		}
		payload1.getTerm1().add(bmt1PayLoadTerm1);
		payload1.getTerm2().add(bmt1PayLoadTerm2);
		payload1.getTerm3().add(bmt1PayLoadTerm3);
		payload1.getTech2().add(bmt1PayLoadTech2);

		// -----******** FOR ServiceBmt2Result (BMT 2)******---------

		List<ServiceBmt2Result> bmt2List = serviceBmt2ResultRepository.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 bmt2PayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt2PayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt2PayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt2PayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 bmt2PayLoad2 = new EdAssessmentOfGcPayload2();
		bmt2PayLoadTerm1.setCategory("Service Subjects");
		bmt2PayLoadTerm1.setSubjectName("BMT 2");
		bmt2PayLoadTerm2.setCategory("Service Subjects");
		bmt2PayLoadTerm2.setSubjectName("BMT 2");
		bmt2PayLoadTerm3.setCategory("Service Subjects");
		bmt2PayLoadTerm3.setSubjectName("BMT 2");
		bmt2PayLoadTech2.setCategory("Service Subjects");
		bmt2PayLoadTech2.setSubjectName("BMT 2");
		bmt2PayLoad2.setCategory("Service Subjects");
		bmt2PayLoad2.setSubjectName("BMT 2");
		if (bmt2List.size() > 0) {
			for (ServiceBmt2Result bmt2Result : bmt2List) {
				bmt2PayLoad2.setObtainedMarks(bmt2Result.getObtainedMarks());
				bmt2PayLoad2.setTotalMarks(Integer.parseInt(bmt2Result.getTotalMarks().toString()));
				if (bmt2Result.getTermId() == 1) {
					bmt2PayLoadTerm1 = bmt2PayLoad2;
				}
				if (bmt2Result.getTermId() == 2) {
					bmt2PayLoadTerm2 = bmt2PayLoad2;
				}
				if (bmt2Result.getTermId() == 3) {
					bmt2PayLoadTerm3 = bmt2PayLoad2;
				}
				if (bmt2Result.getTermId() == 7) {
					bmt2PayLoadTech2 = bmt2PayLoad2;
				}
			}
		}
		payload1.getTerm1().add(bmt2PayLoadTerm1);
		payload1.getTerm2().add(bmt2PayLoadTerm2);
		payload1.getTerm3().add(bmt2PayLoadTerm3);
		payload1.getTech2().add(bmt2PayLoadTech2);

		// -----******** FOR RunbackRouteMr (Map (Practical))******---------

		List<RunbackRouteMr> mrPracList = runbackRouteMrRepo.findByServiceIdAndResultTypeOrderByTermId(serviceId,
				"MR Prac");
		EdAssessmentOfGcPayload2 mrPracPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 mrPracPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 mrPracPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 mrPracPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 mrPracPayLoad2 = new EdAssessmentOfGcPayload2();
		mrPracPayLoadTerm1.setCategory("Service Subjects");
		mrPracPayLoadTerm1.setSubjectName("Map (Practical)");
		mrPracPayLoadTerm2.setCategory("Service Subjects");
		mrPracPayLoadTerm2.setSubjectName("Map (Practical)");
		mrPracPayLoadTerm3.setCategory("Service Subjects");
		mrPracPayLoadTerm3.setSubjectName("Map (Practical)");
		mrPracPayLoadTech2.setCategory("Service Subjects");
		mrPracPayLoadTech2.setSubjectName("Map (Practical)");
		mrPracPayLoad2.setCategory("Service Subjects");
		mrPracPayLoad2.setSubjectName("Map (Practical)");
		if (mrPracList.size() > 0) {
			for (RunbackRouteMr mrPracResult : mrPracList) {
				Integer obtainedMarks = mrPracResult.getObtainedMarks() == null ? 0 : mrPracResult.getObtainedMarks();
				mrPracPayLoad2.setObtainedMarks(Double.parseDouble(obtainedMarks.toString()));
				// mrPracPayLoad2.setTotalMarks(Integer.parseInt(mrPracResult.getTotalMarks().toString()));
				mrPracPayLoad2.setTotalMarks(25);
				if (mrPracResult.getTermId() == 1) {
					mrPracPayLoadTerm1 = mrPracPayLoad2;
				}
				if (mrPracResult.getTermId() == 2) {
					mrPracPayLoadTerm2 = mrPracPayLoad2;
				}
				if (mrPracResult.getTermId() == 3) {
					mrPracPayLoadTerm3 = mrPracPayLoad2;
				}
				if (mrPracResult.getTermId() == 7) {
					mrPracPayLoadTech2 = mrPracPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(mrPracPayLoadTerm1);
		payload1.getTerm2().add(mrPracPayLoadTerm2);
		payload1.getTerm3().add(mrPracPayLoadTerm3);
		payload1.getTech2().add(mrPracPayLoadTech2);

		// -----******** FOR CampMarksResult (Camp/Exercise (Incl Tac Trg
		// (Practical))******---------

		List<CampMarksResult> campMarksList = campMarksResultRepo.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 campMarksPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 campMarksPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 campMarksPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 campMarksPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 campMarksPayLoad2 = new EdAssessmentOfGcPayload2();
		campMarksPayLoadTerm1.setCategory("Service Subjects");
		campMarksPayLoadTerm1.setSubjectName("Camp/Exercise (Incl Tac Trg (Practical)");
		campMarksPayLoadTerm2.setCategory("Service Subjects");
		campMarksPayLoadTerm2.setSubjectName("Camp/Exercise (Incl Tac Trg (Practical)");
		campMarksPayLoadTerm3.setCategory("Service Subjects");
		campMarksPayLoadTerm3.setSubjectName("Camp/Exercise (Incl Tac Trg (Practical)");
		campMarksPayLoadTech2.setCategory("Service Subjects");
		campMarksPayLoadTech2.setSubjectName("Camp/Exercise (Incl Tac Trg (Practical)");
		campMarksPayLoad2.setCategory("Service Subjects");
		campMarksPayLoad2.setSubjectName("Camp/Exercise (Incl Tac Trg (Practical)");
		if (campMarksList.size() > 0) {
			for (CampMarksResult campMarksResult : campMarksList) {
				Double obtainedMarks = campMarksResult.getObtainedMarks() == null ? 0
						: campMarksResult.getObtainedMarks();
				campMarksPayLoad2.setObtainedMarks(obtainedMarks);
				campMarksPayLoad2.setTotalMarks(Integer.parseInt(campMarksResult.getTotalMarks().toString()));
				if (campMarksResult.getTermId() == 1) {
					campMarksPayLoadTerm1 = campMarksPayLoad2;
				}
				if (campMarksResult.getTermId() == 2) {
					campMarksPayLoadTerm2 = campMarksPayLoad2;
				}
				if (campMarksResult.getTermId() == 3) {
					campMarksPayLoadTerm3 = campMarksPayLoad2;
				}
				if (campMarksResult.getTermId() == 7) {
					campMarksPayLoadTech2 = campMarksPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(campMarksPayLoadTerm1);
		payload1.getTerm2().add(campMarksPayLoadTerm2);
		payload1.getTerm3().add(campMarksPayLoadTerm3);
		payload1.getTech2().add(campMarksPayLoadTech2);

		// -----******** FOR IntellectualSkillsResult (Academics
		// Subjects)******---------

		IntellectualSkillsResult intellectualSkillsResult = intellectualSkillsResultService
				.findByServiceIdAndTermId(serviceId, 1L);
		if (intellectualSkillsResult != null) {
			List<IntellectualSkillsSubjectResult> subjectResultList = intellectualSkillsResult
					.getIntellectualSkillsSubResult();
			Integer totalMarks = 0;
			Double obtainedMarks = 0.0;
			for (IntellectualSkillsSubjectResult subjectResult : subjectResultList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm1 = new EdAssessmentOfGcPayload2();
				IntellectualSkillsSubject subject = intellectualSkillsSubjectService
						.getSubjectById(subjectResult.getSubjectId());
				intellectualSkillsPayLoadTerm1.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTerm1.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTerm1.setTotalMarks(subjectResult.getFinalTotalMarks());
				intellectualSkillsPayLoadTerm1.setObtainedMarks(subjectResult.getFinalObtainedMarks());
				totalMarks += subjectResult.getFinalTotalMarks() == null ? 0 : subjectResult.getFinalTotalMarks();
				obtainedMarks += subjectResult.getFinalObtainedMarks() == null ? 0
						: subjectResult.getFinalObtainedMarks();
				payload1.getTerm1().add(intellectualSkillsPayLoadTerm1);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm1 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTerm1.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTerm1.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTerm1.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTerm1.setObtainedMarks(obtainedMarks);
			payload1.getTerm1().add(intellectualSkillsPayLoadTerm1);
		} else {
			List<IntellectualSkillsSubject> subjectList = intellectualSkillsSubjectService.getByStatusAndTermId(1, 1L);
			Integer totalMarks = 0;
			for (IntellectualSkillsSubject subject : subjectList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm1 = new EdAssessmentOfGcPayload2();
				intellectualSkillsPayLoadTerm1.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTerm1.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTerm1.setTotalMarks(subject.getFinalTotalMarks());
				totalMarks += subject.getFinalTotalMarks();
				payload1.getTerm1().add(intellectualSkillsPayLoadTerm1);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm1 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTerm1.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTerm1.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTerm1.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTerm1.setObtainedMarks(null);
			payload1.getTerm1().add(intellectualSkillsPayLoadTerm1);
		}

		IntellectualSkillsResult intellectualSkillsResult2 = intellectualSkillsResultService
				.findByServiceIdAndTermId(serviceId, 2L);
		if (intellectualSkillsResult2 != null) {
			List<IntellectualSkillsSubjectResult> subjectResultList = intellectualSkillsResult2
					.getIntellectualSkillsSubResult();
			Integer totalMarks = 0;
			Double obtainedMarks = 0.0;
			for (IntellectualSkillsSubjectResult subjectResult : subjectResultList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm2 = new EdAssessmentOfGcPayload2();
				IntellectualSkillsSubject subject = intellectualSkillsSubjectService
						.getSubjectById(subjectResult.getSubjectId());
				intellectualSkillsPayLoadTerm2.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTerm2.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTerm2.setTotalMarks(subjectResult.getFinalTotalMarks());
				intellectualSkillsPayLoadTerm2.setObtainedMarks(subjectResult.getFinalObtainedMarks());
				totalMarks += subjectResult.getFinalTotalMarks() == null ? 0 : subjectResult.getFinalTotalMarks();
				obtainedMarks += subjectResult.getFinalObtainedMarks() == null ? 0
						: subjectResult.getFinalObtainedMarks();
				payload1.getTerm2().add(intellectualSkillsPayLoadTerm2);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm2 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTerm2.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTerm2.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTerm2.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTerm2.setObtainedMarks(obtainedMarks);
			payload1.getTerm2().add(intellectualSkillsPayLoadTerm2);
		} else {
			List<IntellectualSkillsSubject> subjectList = intellectualSkillsSubjectService.getByStatusAndTermId(1, 2L);
			Integer totalMarks = 0;
			for (IntellectualSkillsSubject subject : subjectList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm2 = new EdAssessmentOfGcPayload2();
				intellectualSkillsPayLoadTerm2.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTerm2.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTerm2.setTotalMarks(subject.getFinalTotalMarks());
				totalMarks += subject.getFinalTotalMarks();
				payload1.getTerm2().add(intellectualSkillsPayLoadTerm2);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm2 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTerm2.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTerm2.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTerm2.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTerm2.setObtainedMarks(null);
			payload1.getTerm2().add(intellectualSkillsPayLoadTerm2);
		}

		IntellectualSkillsResult intellectualSkillsResult3 = intellectualSkillsResultService
				.findByServiceIdAndTermId(serviceId, 3L);
		if (intellectualSkillsResult3 != null) {
			List<IntellectualSkillsSubjectResult> subjectResultList = intellectualSkillsResult3
					.getIntellectualSkillsSubResult();
			Integer totalMarks = 0;
			Double obtainedMarks = 0.0;
			for (IntellectualSkillsSubjectResult subjectResult : subjectResultList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm3 = new EdAssessmentOfGcPayload2();
				IntellectualSkillsSubject subject = intellectualSkillsSubjectService
						.getSubjectById(subjectResult.getSubjectId());
				intellectualSkillsPayLoadTerm3.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTerm3.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTerm3.setTotalMarks(subjectResult.getFinalTotalMarks());
				intellectualSkillsPayLoadTerm3.setObtainedMarks(subjectResult.getFinalObtainedMarks());
				totalMarks += subjectResult.getFinalTotalMarks();
				obtainedMarks += subjectResult.getFinalObtainedMarks() == null ? 0
						: subjectResult.getFinalObtainedMarks();
				payload1.getTerm3().add(intellectualSkillsPayLoadTerm3);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm3 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTerm3.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTerm3.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTerm3.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTerm3.setObtainedMarks(obtainedMarks);
			payload1.getTerm3().add(intellectualSkillsPayLoadTerm3);
		} else {
			List<IntellectualSkillsSubject> subjectList = intellectualSkillsSubjectService.getByStatusAndTermId(1, 3L);
			Integer totalMarks = 0;
			for (IntellectualSkillsSubject subject : subjectList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm3 = new EdAssessmentOfGcPayload2();
				intellectualSkillsPayLoadTerm3.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTerm3.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTerm3.setTotalMarks(subject.getFinalTotalMarks());
				totalMarks += subject.getFinalTotalMarks();
				payload1.getTerm3().add(intellectualSkillsPayLoadTerm3);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTerm3 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTerm3.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTerm3.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTerm3.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTerm3.setObtainedMarks(null);
			payload1.getTerm3().add(intellectualSkillsPayLoadTerm3);
		}
		IntellectualSkillsResult intellectualSkillsResult7 = intellectualSkillsResultService
				.findByServiceIdAndTermId(serviceId, 7L);
		if (intellectualSkillsResult7 != null) {
			List<IntellectualSkillsSubjectResult> subjectResultList = intellectualSkillsResult7
					.getIntellectualSkillsSubResult();
			Integer totalMarks = 0;
			Double obtainedMarks = 0.0;
			for (IntellectualSkillsSubjectResult subjectResult : subjectResultList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTech2 = new EdAssessmentOfGcPayload2();
				IntellectualSkillsSubject subject = intellectualSkillsSubjectService
						.getSubjectById(subjectResult.getSubjectId());
				intellectualSkillsPayLoadTech2.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTech2.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTech2.setTotalMarks(subjectResult.getFinalTotalMarks());
				intellectualSkillsPayLoadTech2.setObtainedMarks(subjectResult.getFinalObtainedMarks());
				totalMarks += subjectResult.getFinalTotalMarks();
				obtainedMarks += subjectResult.getFinalObtainedMarks() == null ? 0
						: subjectResult.getFinalObtainedMarks();
				payload1.getTech2().add(intellectualSkillsPayLoadTech2);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTech2 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTech2.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTech2.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTech2.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTech2.setObtainedMarks(obtainedMarks);
			payload1.getTech2().add(intellectualSkillsPayLoadTech2);
		} else {
			List<IntellectualSkillsSubject> subjectList = intellectualSkillsSubjectService.getByStatusAndTermId(1, 7L);
			Integer totalMarks = 0;
			for (IntellectualSkillsSubject subject : subjectList) {
				EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTech2 = new EdAssessmentOfGcPayload2();
				intellectualSkillsPayLoadTech2.setCategory("Academics Subjects");
				intellectualSkillsPayLoadTech2.setSubjectName(subject.getSubjectName());
				intellectualSkillsPayLoadTech2.setTotalMarks(subject.getFinalTotalMarks());
				totalMarks += subject.getFinalTotalMarks();
				payload1.getTech2().add(intellectualSkillsPayLoadTech2);
			}
			EdAssessmentOfGcPayload2 intellectualSkillsPayLoadTech2 = new EdAssessmentOfGcPayload2();
			intellectualSkillsPayLoadTech2.setCategory("Academics Subjects");
			intellectualSkillsPayLoadTech2.setSubjectName("Total Academics Subjects");
			intellectualSkillsPayLoadTech2.setTotalMarks(totalMarks);
			intellectualSkillsPayLoadTech2.setObtainedMarks(null);
			payload1.getTech2().add(intellectualSkillsPayLoadTech2);
		}

		// -----******** FOR AcademicLeadershipMatrixResult (Leadership
		// Matrix)******---------

		List<AcademicLeadershipMatrixResult> academicLeadershipMatrixList = academicLeadershipMatrixResultRepo
				.findByServiceIdOrderByTermId(serviceId);
		EdAssessmentOfGcPayload2 academicLeadershipMatrixPayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 academicLeadershipMatrixPayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 academicLeadershipMatrixPayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 academicLeadershipMatrixPayLoadTech2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 academicLeadershipMatrixPayLoad2 = new EdAssessmentOfGcPayload2();
		academicLeadershipMatrixPayLoadTerm1.setCategory("Leadership Matrix");
		academicLeadershipMatrixPayLoadTerm1.setSubjectName("Leadership Matrix");
		academicLeadershipMatrixPayLoadTerm2.setCategory("Leadership Matrix");
		academicLeadershipMatrixPayLoadTerm2.setSubjectName("Leadership Matrix");
		academicLeadershipMatrixPayLoadTerm3.setCategory("Leadership Matrix");
		academicLeadershipMatrixPayLoadTerm3.setSubjectName("Leadership Matrix");
		academicLeadershipMatrixPayLoadTech2.setCategory("Leadership Matrix");
		academicLeadershipMatrixPayLoadTech2.setSubjectName("Leadership Matrix");
		academicLeadershipMatrixPayLoad2.setCategory("Leadership Matrix");
		academicLeadershipMatrixPayLoad2.setSubjectName("Leadership Matrix");
		if (academicLeadershipMatrixList.size() > 0) {
			for (AcademicLeadershipMatrixResult academicLeadershipMatrixResult : academicLeadershipMatrixList) {
				Double obtainedMarks = academicLeadershipMatrixResult.getObtainedMarks() == null ? 0
						: academicLeadershipMatrixResult.getObtainedMarks();
				academicLeadershipMatrixPayLoad2.setObtainedMarks(obtainedMarks);
				academicLeadershipMatrixPayLoad2
						.setTotalMarks(Integer.parseInt(academicLeadershipMatrixResult.getTotalMarks().toString()));
				if (academicLeadershipMatrixResult.getTermId() == 1) {
					academicLeadershipMatrixPayLoadTerm1 = academicLeadershipMatrixPayLoad2;
				}
				if (academicLeadershipMatrixResult.getTermId() == 2) {
					academicLeadershipMatrixPayLoadTerm2 = academicLeadershipMatrixPayLoad2;
				}
				if (academicLeadershipMatrixResult.getTermId() == 3) {
					academicLeadershipMatrixPayLoadTerm3 = academicLeadershipMatrixPayLoad2;
				}
				if (academicLeadershipMatrixResult.getTermId() == 7) {
					academicLeadershipMatrixPayLoadTech2 = academicLeadershipMatrixPayLoad2;
				}
			}
		}
		payload1.getTerm1().add(academicLeadershipMatrixPayLoadTerm1);
		payload1.getTerm2().add(academicLeadershipMatrixPayLoadTerm2);
		payload1.getTerm3().add(academicLeadershipMatrixPayLoadTerm3);
		payload1.getTech2().add(academicLeadershipMatrixPayLoadTech2);

		// -----******** FOR AcademicCreditForExcellenceResult (Credit for
		// Excellence)******---------

		EdAssessmentOfGcPayload2 academicCreditForExcellencePayLoadTerm1 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 academicCreditForExcellencePayLoadTerm2 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 academicCreditForExcellencePayLoadTerm3 = new EdAssessmentOfGcPayload2();
		EdAssessmentOfGcPayload2 academicCreditForExcellencePayLoadTech2 = new EdAssessmentOfGcPayload2();
		academicCreditForExcellencePayLoadTerm1.setCategory("Credit for Excellence");
		academicCreditForExcellencePayLoadTerm1.setSubjectName("Credit for Excellence");
		academicCreditForExcellencePayLoadTerm2.setCategory("Credit for Excellence");
		academicCreditForExcellencePayLoadTerm2.setSubjectName("Credit for Excellence");
		academicCreditForExcellencePayLoadTerm3.setCategory("Credit for Excellence");
		academicCreditForExcellencePayLoadTerm3.setSubjectName("Credit for Excellence");
		academicCreditForExcellencePayLoadTech2.setCategory("Credit for Excellence");
		academicCreditForExcellencePayLoadTech2.setSubjectName("Credit for Excellence");
		AcademicCreditForExcellenceResult academicCreditForExcellenceResultTerm1 = academicCreditForExcellenceResultService
				.findByServiceIdAndTermId(serviceId, 1);
		if (academicCreditForExcellenceResultTerm1 != null) {
			academicCreditForExcellencePayLoadTerm1.setTotalMarks(50);
			academicCreditForExcellencePayLoadTerm1
					.setObtainedMarks(academicCreditForExcellenceResultTerm1.getObtainedMarks());
		} else {
			academicCreditForExcellencePayLoadTerm1.setTotalMarks(50);
			academicCreditForExcellencePayLoadTerm1.setObtainedMarks(null);
		}

		AcademicCreditForExcellenceResult academicCreditForExcellenceResultTerm2 = academicCreditForExcellenceResultService
				.findByServiceIdAndTermId(serviceId, 2);
		if (academicCreditForExcellenceResultTerm2 != null) {
			academicCreditForExcellencePayLoadTerm2.setTotalMarks(50);
			academicCreditForExcellencePayLoadTerm2
					.setObtainedMarks(academicCreditForExcellenceResultTerm2.getObtainedMarks());
		} else {
			academicCreditForExcellencePayLoadTerm2.setTotalMarks(50);
			academicCreditForExcellencePayLoadTerm2.setObtainedMarks(null);
		}
		AcademicCreditForExcellenceResult academicCreditForExcellenceResultTerm3 = academicCreditForExcellenceResultService
				.findByServiceIdAndTermId(serviceId, 3);
		if (academicCreditForExcellenceResultTerm3 != null) {
			academicCreditForExcellencePayLoadTerm3.setTotalMarks(50);
			academicCreditForExcellencePayLoadTerm3
					.setObtainedMarks(academicCreditForExcellenceResultTerm3.getObtainedMarks());
		} else {
			academicCreditForExcellencePayLoadTerm3.setTotalMarks(50);
			academicCreditForExcellencePayLoadTerm3.setObtainedMarks(null);
		}
		AcademicCreditForExcellenceResult academicCreditForExcellenceResultTech2 = academicCreditForExcellenceResultService
				.findByServiceIdAndTermId(serviceId, 7);
		if (academicCreditForExcellenceResultTech2 != null) {
			academicCreditForExcellencePayLoadTech2.setTotalMarks(50);
			academicCreditForExcellencePayLoadTech2
					.setObtainedMarks(academicCreditForExcellenceResultTech2.getObtainedMarks());
		} else {
			academicCreditForExcellencePayLoadTech2.setTotalMarks(50);
			academicCreditForExcellencePayLoadTech2.setObtainedMarks(null);
		}
		payload1.getTerm1().add(academicCreditForExcellencePayLoadTerm1);
		payload1.getTerm2().add(academicCreditForExcellencePayLoadTerm2);
		payload1.getTerm3().add(academicCreditForExcellencePayLoadTerm3);
		payload1.getTech2().add(academicCreditForExcellencePayLoadTech2);

		return payload1;

	}
}
