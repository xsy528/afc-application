package com.insigma.afc.msutil.dao.entity.topology;

import javax.persistence.Column;

/**
 * TmetroStationId entity.
 * 
 * @author 廖红自
 */
public class MetroStationId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public MetroStationId(){

	}

	public MetroStationId(Short lineId, Integer stationId) {
		this.lineId = lineId;
		this.stationId = stationId;
	}

	private Short lineId;

	private Integer stationId;

	@Column(name = "LINE_ID", nullable = false, precision = 5)
	public Short getLineId() {
		return this.lineId;
	}

	public void setLineId(Short lineId) {
		this.lineId = lineId;
	}

	@Column(name = "STATION_ID", nullable = false, precision = 11)
	public Integer getStationId() {
		return this.stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof MetroStationId)) {
			return false;
		}
		MetroStationId castOther = (MetroStationId) other;

		return ((this.getLineId().equals(castOther.getLineId())) || (this.getLineId() != null
				&& castOther.getLineId() != null && this.getLineId().equals(castOther.getLineId())))
				&& ((this.getStationId().equals(castOther.getStationId())) || (this.getStationId() != null
						&& castOther.getStationId() != null && this.getStationId().equals(castOther.getStationId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getLineId() == null ? 0 : this.getLineId().hashCode());
		result = 37 * result + (getStationId() == null ? 0 : this.getStationId().hashCode());
		return result;
	}

}