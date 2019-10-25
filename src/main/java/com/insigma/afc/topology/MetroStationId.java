package com.insigma.afc.topology;

/**
 * TmetroStationId entity.
 * 
 * @author 廖红自
 */
public class MetroStationId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Short lineId;

	private Integer stationId;

	// Constructors

	/** default constructor */
	public MetroStationId() {
	}

	/** full constructor */
	public MetroStationId(Short lineId, Integer stationId) {
		this.lineId = lineId;
		this.stationId = stationId;
	}

	public MetroStationId(MetroStationId id) {
		this.lineId = id.lineId;
		this.stationId = id.stationId;
	}

	// Property accessors

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

}