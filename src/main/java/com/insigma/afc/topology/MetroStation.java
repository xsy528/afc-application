package com.insigma.afc.topology;

import com.insigma.commons.util.NodeIdUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TmetroStation entity.
 * 
 * @author 廖红自
 */
public class MetroStation extends MetroNode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private MetroStationId id = new MetroStationId();

	private String lineName;

	private String stationName;

	private String ipAddress;

	private AFCNodeLocation imageLocation;

	private AFCNodeLocation textLocation;

	private String picName;

	private String backPicName;

	private Short status = 0;

	private String remark;

	/** default constructor */
	public MetroStation() {
	}

	/** minimal constructor */
	public MetroStation(MetroStationId id, String lineName, String stationName, Short status) {
		this.id = id;
		this.lineName = lineName;
		this.stationName = stationName;
		this.status = status;
	}

	public MetroStation(MetroStationId id, String lineName, String stationName, String ipAddress,
			AFCNodeLocation imageLocation, AFCNodeLocation textLocation, String picName, String backPicName,
			Short status, String remark, Map<Long, MetroDevice> deviceMap, List<MetroDevice> devices) {
		super();
		this.id = id;
		this.lineName = lineName;
		this.stationName = stationName;
		this.ipAddress = ipAddress;
		this.imageLocation = imageLocation;
		this.textLocation = textLocation;
		this.picName = picName;
		this.backPicName = backPicName;
		this.status = status;
		this.remark = remark;
		this.deviceMap = deviceMap;
		this.devices = devices;
	}

	public MetroStationId getId() {
		return this.id;
	}

	public void setId(MetroStationId id) {
		this.id = id;
	}

	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * @return the imageLocation
	 */
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

	/**
	 * @return the textLocation
	 */
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

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
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

	public String getBackPicName() {
		if (backPicName == null) {
			backPicName = picName;
		}
		return backPicName;
	}

	public void setBackPicName(String backPicName) {
		this.backPicName = backPicName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[车站编号:");
		sb.append(id.getStationId());
		sb.append("车站名称:");
		sb.append(stationName);
		sb.append("]");
		return sb.toString();
	}

	public long id() {
		return id.getStationId();
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof MetroStation) {
			return ((MetroStation) arg0).id() == id();
		}
		return false;
	}

	private Map<Long, MetroDevice> deviceMap = new HashMap<Long, MetroDevice>();

	private List<MetroDevice> devices = new ArrayList<MetroDevice>();

	/**
	 * @return the devices
	 */
	public List<MetroDevice> getDevices() {
		return devices;
	}

	@Override
	public List<MetroDevice> getSubNodes() {
		return getDevices();
	}


	public MetroStation(MetroStation metroStation) {

		this.id = new MetroStationId(metroStation.id);
		this.lineName = metroStation.lineName;
		this.stationName = metroStation.stationName;
		this.ipAddress = metroStation.ipAddress;
		this.imageLocation = new AFCNodeLocation(metroStation.imageLocation);
		this.textLocation = new AFCNodeLocation(metroStation.textLocation);
		this.picName = metroStation.picName;
		this.backPicName = metroStation.backPicName;
		this.status = metroStation.status;
		this.remark = metroStation.remark;
		//this.deviceMap = deviceMap;
		//this.devices = metroStation.devices;
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
		return NodeIdUtils.nodeIdStrategy.getNodeNo(id.getStationId().longValue());
	}

}
