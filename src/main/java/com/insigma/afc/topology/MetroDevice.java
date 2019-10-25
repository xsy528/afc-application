package com.insigma.afc.topology;

import com.insigma.commons.constant.AFCNodeLevel;
import com.insigma.commons.util.NodeIdUtils;

import java.beans.Transient;
import java.util.List;

/**
 * MetroDevice entity.
 * 
 * @author 廖红自
 */
public class MetroDevice extends MetroNode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private MetroDeviceId id;

	private String lineName;

	private String stationName;

	private String deviceName;

	private short deviceType;

	private Short deviceSubType;

	private String ipAddress;

	private AFCNodeLocation imageLocation;

	private AFCNodeLocation textLocation;

	private int commPort;

	private long logicAddress;

	private Short status;

	// 网卡的MAC地址
	private String addrMac;

	private String remark;

	private String picName;


	public MetroDevice() {
	}

	/** minimal constructor */
	public MetroDevice(MetroDeviceId id, String lineName, String stationName, String deviceName, Short status) {
		this.id = id;
		this.lineName = lineName;
		this.stationName = stationName;
		this.deviceName = deviceName;
		this.status = status;
	}

	public MetroDevice(MetroDeviceId id, String lineName, String stationName, String deviceName, String picName,
			short deviceType, short deviceSubType, String ipAddress, AFCNodeLocation imageLocation,
			AFCNodeLocation textLocation, int commPort, long logicAddress, Short status, String remark,
			List<MetroDeviceModule> ss) {
		super();
		this.id = id;
		this.lineName = lineName;
		this.stationName = stationName;
		this.deviceName = deviceName;
		this.picName = picName;
		this.deviceType = deviceType;
		this.deviceSubType = deviceSubType;
		this.ipAddress = ipAddress;
		this.imageLocation = imageLocation;
		this.textLocation = textLocation;
		this.commPort = commPort;
		this.logicAddress = logicAddress;
		this.status = status;
		this.remark = remark;
		this.ss = ss;
	}

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

	public long id() {
		return id.getDeviceId();
	}

	/**
	 * @param textLocation the textLocation to set
	 */
	@Override
	public void setTextLocation(AFCNodeLocation textLocation) {
		this.textLocation = textLocation;
	}

	public MetroDeviceId getId() {
		return this.id;
	}

	public void setId(MetroDeviceId id) {
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

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

	public short getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(short deviceType) {
		this.deviceType = deviceType;
	}

	public Short getDeviceSubType() {
		return deviceSubType;
	}

	public void setDeviceSubType(Short deviceSubType) {
		this.deviceSubType = deviceSubType;
	}


	//	@Column(name = "PIC_NAME")
	@Transient
	@Override
	public String getPicName() {
		return picName;
	}

	@Override
	public void setPicName(String picName) {
		this.picName = picName;
	}

	/**
	 * @param commPort
	 *            the commPort to set
	 */
	public void setCommPort(int commPort) {
		this.commPort = commPort;
	}

	/**
	 * @return the commPort
	 */
	public int getCommPort() {
		return commPort;
	}

	public void setLogicAddress(long logicAddress) {
		this.logicAddress = logicAddress;
	}

	public long getLogicAddress() {
		return logicAddress;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof MetroDevice) {
			return ((MetroDevice) arg0).id.getDeviceId().equals(id.getDeviceId());
		}
		return false;
	}

	public String name() {
		return deviceName;
	}

	@Transient
	public AFCNodeLevel level() {
		return AFCNodeLevel.SLE;
	}

	private List<MetroDeviceModule> ss;

	@Override
	@Transient
	public List<MetroDeviceModule> getSubNodes() {
		return ss;
	}

	public void setModules(List<MetroDeviceModule> ss) {
		this.ss = ss;
		if (ss == null) {
			return;
		}
		for (MetroDeviceModule module : ss) {
			module.setParent(this);
		}
	}

	public MetroDevice(MetroDevice metroDevice) {
		this.id = new MetroDeviceId(metroDevice.id);
		this.lineName = metroDevice.lineName;
		this.stationName = metroDevice.stationName;
		this.deviceName = metroDevice.deviceName;
		this.picName = metroDevice.picName;
		this.deviceType = metroDevice.deviceType;
		this.deviceSubType = metroDevice.deviceSubType;
		this.ipAddress = metroDevice.ipAddress;
		this.imageLocation = new AFCNodeLocation(metroDevice.imageLocation);
		this.textLocation = new AFCNodeLocation(metroDevice.textLocation);
		this.commPort = metroDevice.commPort;
		this.logicAddress = metroDevice.logicAddress;
		this.status = metroDevice.status;
		this.remark = metroDevice.remark;
		this.ss = metroDevice.ss;
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

	@Transient
	public Object getExtValue() {
		return extValue;
	}

	public void setExtValue(Object extValue) {
		this.extValue = extValue;
	}

	@Override
	@Transient
	public long getNodeId() {
		return NodeIdUtils.nodeIdStrategy.getNodeNo(id.getDeviceId());
	}

	@Override
	@Transient
	public MetroStation getParent() {
		return (MetroStation) super.getParent();
	}

	public String getAddrMac() {
		return addrMac;
	}

	public void setAddrMac(String mac) {
		this.addrMac = mac;
	}
}