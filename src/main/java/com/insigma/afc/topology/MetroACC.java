package com.insigma.afc.topology;

import com.insigma.commons.util.NodeIdUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MetroACC entity.
 * 
 * @author 廖红自
 */
public class MetroACC extends MetroNode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private short accID;

	private String accName;

	private String ipAddress;

	private AFCNodeLocation imageLocation;

	private AFCNodeLocation textLocation;

	private String picName;

	private String remark;

	private int status;

	public MetroACC(short accID, String accName, int status) {
		this.accID = accID;
		this.accName = accName;
		this.status = status;
	}

	public MetroACC() {

	}

	public short getAccID() {
		return accID;
	}

	public void setAccID(short accID) {
		this.accID = accID;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getPicName() {
		return picName;
	}

	@Override
	public void setPicName(String picName) {
		this.picName = picName;
	}

	private Map<Long, MetroLine> lineMap = new HashMap<Long, MetroLine>();

	private List<MetroLine> lines;

	public AFCNodeLocation getImageLocation() {
		if (imageLocation == null) {
			imageLocation = new AFCNodeLocation();
		}
		return imageLocation;
	}

	/**
	 * @param imageLocation the imageLocation to set
	 */
	@Override
	public void setImageLocation(AFCNodeLocation imageLocation) {
		this.imageLocation = imageLocation;
	}

	public AFCNodeLocation getTextLocation() {
		if (textLocation == null) {
			textLocation = new AFCNodeLocation();
		}
		return textLocation;
	}

	/**
	 * @param textLocation the textLocation to set
	 */
	@Override
	public void setTextLocation(AFCNodeLocation textLocation) {
		this.textLocation = textLocation;
	}

	private String extJsonValue;
	private Object extValue;

	public String getExtJsonValue() {
		return extJsonValue;
	}

	public void setExtJsonValue(String extJsonValue) {
		this.extJsonValue = extJsonValue;
	}

	public Object getExtValue() {
		return extValue;
	}

	public void setExtValue(Object extValue) {
		this.extValue = extValue;
	}

	@Override
	public long getNodeId() {
		return NodeIdUtils.nodeIdStrategy.getNodeNo((long)accID);
	}

}
