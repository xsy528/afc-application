package com.insigma.afc.topology;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * metroDeviceModuleId entity.
 * 
 * @author zengziqiang
 */
@Embeddable
public class MetroDeviceModuleId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public MetroDeviceModuleId(){

	}

	public MetroDeviceModuleId(Integer stationId, Long deviceId, Long moduleId) {
		this.stationId = stationId;
		this.deviceId = deviceId;
		this.moduleId = moduleId;
	}

	@Column(name = "STATION_ID", nullable = false, precision = 11)
	private Integer stationId;

	@Column(name = "DEVICE_ID", nullable = false)
	private Long deviceId;

	@Column(name = "MODULE_ID", nullable = false)
	private Long moduleId;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MetroDeviceModuleId that = (MetroDeviceModuleId) o;
		return Objects.equals(stationId, that.stationId) &&
				Objects.equals(deviceId, that.deviceId) &&
				Objects.equals(moduleId, that.moduleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(stationId, deviceId, moduleId);
	}
}