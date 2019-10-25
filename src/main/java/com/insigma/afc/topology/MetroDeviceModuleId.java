package com.insigma.afc.topology;

/**
 * metroDeviceModuleId entity.
 * 
 * @author 廖红自
 */
public class MetroDeviceModuleId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer stationId;

	private Long deviceId;

	private Long moduleId;

	// Constructors

	/** default constructor */
	public MetroDeviceModuleId() {
	}

	public MetroDeviceModuleId(MetroDeviceModuleId id) {
		this.stationId = id.stationId;
		this.deviceId = id.deviceId;
		this.moduleId = id.moduleId;
	}

	/** full constructor */
	public MetroDeviceModuleId(Integer stationId, Long deviceId, Long moduleId) {
		this.stationId = stationId;
		this.deviceId = deviceId;
		this.moduleId = moduleId;
	}

	// Property accessors

	public Integer getStationId() {
		return this.stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Long getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

}