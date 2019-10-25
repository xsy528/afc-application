package com.insigma.afc.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * MetroDeviceModule entity.
 * 
 * @author 廖红自
 */
public class MetroDeviceModule extends MetroNode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private MetroDeviceModuleId id;

	private Short lineId;

	private String lineName;

	private String stationName;

	private String deviceName;

	private String moduleName;

	private String ipAddress;

	private Short status;

	private String remark;

	// Constructors

	/** default constructor */
	public MetroDeviceModule() {
	}

	/** minimal constructor */
	public MetroDeviceModule(MetroDeviceModuleId id, Short lineId, String lineName, String stationName,
			String deviceName, String moduleName, Short status) {
		this.id = id;
		this.lineId = lineId;
		this.lineName = lineName;
		this.stationName = stationName;
		this.deviceName = deviceName;
		this.moduleName = moduleName;
		this.status = status;
	}

	private AFCNodeLocation imageLocation;

	private AFCNodeLocation textLocation;

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

	public MetroDeviceModuleId getId() {
		return this.id;
	}

	public void setId(MetroDeviceModuleId id) {
		this.id = id;
	}

	@Override
	public short getLineId() {
		return this.lineId;
	}

	public void setLineId(Short lineId) {
		this.lineId = lineId;
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

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
	public List<? extends MetroNode> getSubNodes() {
		return new ArrayList<MetroNode>();
	}

	public MetroDeviceModule(MetroDeviceModule deviceModule) {
		this.id = new MetroDeviceModuleId(deviceModule.id);
		this.lineId = deviceModule.lineId;
		this.lineName = deviceModule.lineName;
		this.stationName = deviceModule.stationName;
		this.deviceName = deviceModule.deviceName;
		this.moduleName = deviceModule.moduleName;
		this.ipAddress = deviceModule.ipAddress;
		this.status = deviceModule.status;
		this.remark = deviceModule.remark;
		this.imageLocation = new AFCNodeLocation(deviceModule.imageLocation);
		this.textLocation = new AFCNodeLocation(deviceModule.textLocation);
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
	public String getPicName() {
		return null;
	}

	@Override
	public void setPicName(String picName) {

	}

	@Override
	public MetroDevice getParent() {
		return (MetroDevice) super.getParent();
	}
}
