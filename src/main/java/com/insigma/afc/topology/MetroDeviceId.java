package com.insigma.afc.topology;
/**
 * TmetroDeviceId entity.
 * 
 * @author 廖红自
 */
public class MetroDeviceId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Short lineId;

	private Integer stationId;

	private Long deviceId;

	public MetroDeviceId() {
	}

	public MetroDeviceId(Short lineId, Integer stationId, Long deviceId) {
		this.lineId = lineId;
		this.stationId = stationId;
		this.deviceId = deviceId;
	}

	/** full constructor */
	public MetroDeviceId(MetroStationId sid, Long deviceId) {
		this(sid.getLineId(), sid.getStationId(), deviceId);
	}

	public MetroDeviceId(MetroDeviceId id) {
		this.lineId = id.lineId;
		this.stationId = id.stationId;
		this.deviceId = id.deviceId;
	}

	public Short getLineId() {
		return this.lineId;
	}

	public void setLineId(Short lineId) {
		this.lineId = lineId;
	}

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

	@Override
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof MetroDeviceId)) {
			return false;
		}
		MetroDeviceId castOther = (MetroDeviceId) other;

		return ((this.getLineId() == castOther.getLineId()) || (this.getLineId() != null
				&& castOther.getLineId() != null && this.getLineId().equals(castOther.getLineId())))
				&& ((this.getStationId() == castOther.getStationId()) || (this.getStationId() != null
						&& castOther.getStationId() != null && this.getStationId().equals(castOther.getStationId())))
				&& ((this.getDeviceId() == castOther.getDeviceId()) || (this.getDeviceId() != null
						&& castOther.getDeviceId() != null && this.getDeviceId().equals(castOther.getDeviceId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getLineId() == null ? 0 : this.getLineId().hashCode());
		result = 37 * result + (getStationId() == null ? 0 : this.getStationId().hashCode());
		result = 37 * result + (getDeviceId() == null ? 0 : this.getDeviceId().hashCode());
		return result;
	}

}