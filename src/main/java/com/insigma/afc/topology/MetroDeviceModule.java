package com.insigma.afc.topology;


import com.insigma.commons.constant.AFCNodeLevel;

import javax.persistence.*;

/**
 * MetroDeviceModule entity.
 * 
 * @author zengziqiang
 */
@Entity
@Table(name = "TMETRO_DEVICE_MODULE")
public class MetroDeviceModule implements MetroNode {
	private static final long serialVersionUID = 1L;

	public MetroDeviceModule() {
	}

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

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "stationId", column = @Column(name = "STATION_ID", nullable = false,
					precision = 11)),
			@AttributeOverride(name = "deviceId", column = @Column(name = "DEVICE_ID", nullable = false)),
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", nullable = false))})
	private MetroDeviceModuleId id;

	@Column(name = "LINE_ID", nullable = false, precision = 5)
	private Short lineId;

	@Column(name = "LINE_NAME", nullable = false, length = 30)
	private String lineName;

	@Column(name = "STATION_NAME", nullable = false, length = 30)
	private String stationName;

	@Column(name = "DEVICE_NAME", nullable = false, length = 30)
	private String deviceName;

	@Column(name = "MODULE_NAME", nullable = false, length = 30)
	private String moduleName;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "STATUS", nullable = false, precision = 5)
	private Short status;

	@Column(name = "REMARK", length = 500)
	private String remark;

	@Column(name = "EXT_JSON_VALUE", length = 2000)
	private String extJsonValue;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "x", column = @Column(name = "IMAGE_X", nullable = false)),
			@AttributeOverride(name = "y", column = @Column(name = "IMAGE_Y", nullable = false)),
			@AttributeOverride(name = "angle", column = @Column(name = "IMAGE_ANGLE", nullable = false))})
	private AFCNodeLocation imageLocation;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "x", column = @Column(name = "TEXT_X", nullable = false)),
			@AttributeOverride(name = "y", column = @Column(name = "TEXT_Y", nullable = false)),
			@AttributeOverride(name = "angle", column = @Column(name = "TEXT_ANGLE", nullable = false))})
	private AFCNodeLocation textLocation;

	@Override
	public Long id() {
		return id.getModuleId();
	}

	@Override
	public AFCNodeLevel level() {
		return null;
	}

	@Override
	public String name(){
		return moduleName;
	}

	public MetroDeviceModuleId getId() {
		return id;
	}

	public void setId(MetroDeviceModuleId id) {
		this.id = id;
	}

	public Short getLineId() {
		return lineId;
	}

	public void setLineId(Short lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExtJsonValue() {
		return extJsonValue;
	}

	public void setExtJsonValue(String extJsonValue) {
		this.extJsonValue = extJsonValue;
	}

	public AFCNodeLocation getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(AFCNodeLocation imageLocation) {
		this.imageLocation = imageLocation;
	}

	public AFCNodeLocation getTextLocation() {
		return textLocation;
	}

	public void setTextLocation(AFCNodeLocation textLocation) {
		this.textLocation = textLocation;
	}

}
