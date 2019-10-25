package com.insigma.afc.topology;

import com.insigma.commons.util.NodeIdUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MetroLine entity.
 * 
 * @author 廖红自
 */
public class MetroLine extends MetroNode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private short lineID;

	private String lineName;

	private String ipAddress;

	private AFCNodeLocation imageLocation;

	private AFCNodeLocation textLocation;

	private String picName;

	private String remark;

	private Short status;

	public MetroLine(short lineID, String lineName, short status) {
		this.lineID = lineID;
		this.lineName = lineName;
		this.status = status;
	}

	public MetroLine() {

	}

	public AFCNodeLocation getImageLocation() {
		if (imageLocation == null) {
			imageLocation = new AFCNodeLocation();
		}
		return imageLocation;
	}

	public long id() {
		return lineID;
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

	public short getLineID() {
		return lineID;
	}

	public void setLineID(short lineID) {
		this.lineID = lineID;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(short status) {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[线路号:");
		sb.append(lineID);
		sb.append("线路名称:");
		sb.append(lineName);
		sb.append("]");
		return sb.toString();
	}

	private Map<Long, MetroStation> ssMap = new HashMap<Long, MetroStation>();

	private List<MetroStation> ss = new ArrayList<MetroStation>();

	public Map<Long, MetroStation> getStationMap() {
		return ssMap;
	}

	public List<MetroStation> getStationList() {
		return ss;
	}

	@Override
	public List<MetroStation> getSubNodes() {
		return getStationList();
	}

	private String extJsonValue;

	private Object extValue;

	public String getExtJsonValue() {
		return extJsonValue;
	}

	public void setExtJsonValue(String extJsonValue) {
		this.extJsonValue = extJsonValue;
		if (extJsonValue == null || "".equals(extJsonValue)) {
			return;
		}
	}

	public Object getExtValue() {
		return extValue;
	}

	public void setExtValue(Object extValue) {
		this.extValue = extValue;
	}

	@Override
	public long getNodeId() {
		return NodeIdUtils.nodeIdStrategy.getNodeNo((long)lineID);
	}

}
