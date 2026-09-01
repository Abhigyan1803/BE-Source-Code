package com.example.demo.payload;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.AcademicOqMatrixResult;
import com.example.demo.model.OqDrillResult;
import com.example.demo.model.OqEqtnResult;

public class EdOqMatrixPayload implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
//	private List<AcademicOqMatrixResult> oqMatrix = new ArrayList<AcademicOqMatrixResult>();
//	private List<OqDrillResult> oqDrill = new ArrayList<OqDrillResult>();
//	private List<OqEqtnResult> oqEqtn = new ArrayList<OqEqtnResult>();
	private List<AcademicOqMatrixResult> oqMatrix = null;
	private List<OqDrillResult> oqDrill = null;
	private List<OqEqtnResult> oqEqtn = null;

	public List<AcademicOqMatrixResult> getOqMatrix() {
		return oqMatrix;
	}

	public void setOqMatrix(List<AcademicOqMatrixResult> oqMatrix) {
		this.oqMatrix = oqMatrix;
	}

	public List<OqDrillResult> getOqDrill() {
		return oqDrill;
	}

	public void setOqDrill(List<OqDrillResult> oqDrill) {
		this.oqDrill = oqDrill;
	}

	public List<OqEqtnResult> getOqEqtn() {
		return oqEqtn;
	}

	public void setOqEqtn(List<OqEqtnResult> oqEqtn) {
		this.oqEqtn = oqEqtn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
