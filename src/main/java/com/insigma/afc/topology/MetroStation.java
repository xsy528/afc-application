package com.insigma.afc.topology;

import com.insigma.commons.constant.AFCNodeLevel;
import com.insigma.commons.util.NodeIdUtils;

import javax.persistence.*;

/**
 * TmetroStation entity.
 *
 * @author zengziqiang
 */
@Entity
@Table(name = "TMETRO_STATION")
@IdClass(MetroStationId.class)
public class MetroStation implements MetroNode {

	private static final long serialVersionUID = 1L;

	public MetroStation() {
	}

	@Id
	@Column(name="LINE_ID")
	private Short lineId;

	@Id
	@Column(name="STATION_ID")
	private Integer stationId;

	@Column(name = "LINE_NAME", nullable = false, length = 30)
	private String lineName;

	@Column(name = "STATION_NAME", nullable = false, length = 30)
	private String stationName;

	@Column(name = "IP_ADDRESS", length = 32)
	private String ipAddress;

	@Column(name = "PIC_NAME")
	private String picName;

	@Column(name = "BACK_PIC_NAME")
	private String backPicName;

	@Column(name = "STATUS", nullable = false, precision = 5)
	private Short status;

	@Column(name = "REMARK", length = 500)
	private String remark;

	@Column(name = "EXT_JSON_VALUE", length = 2000)
	private String extJsonValue;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "x", column = @Column(name = "IMAGE_X", nullable = false) ),
			@AttributeOverride(name = "y", column = @Column(name = "IMAGE_Y", nullable = false) ),
			@AttributeOverride(name = "angle", column = @Column(name = "IMAGE_ANGLE", nullable = false) ) })
	private AFCNodeLocation imageLocation;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "x", column = @Column(name = "TEXT_X", nullable = false) ),
			@AttributeOverride(name = "y", column = @Column(name = "TEXT_Y", nullable = false) ),
			@AttributeOverride(name = "angle", column = @Column(name = "TEXT_ANGLE", nullable = false) ) })
	private AFCNodeLocation textLocation;

	@Override
	public Long id() {
		if (stationId==null){
			return null;
		}
		return NodeIdUtils.nodeIdStrategy.getNodeNo(stationId.longValue());
	}

	@Override
	public AFCNodeLevel level() {
		return AFCNodeLevel.SC;
	}

	@Override
	public String name(){
		return stationName;
	}

	public Short getLineId() {
		return lineId;
	}

	public void setLineId(Short lineId) {
		this.lineId = lineId;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getBackPicName() {
		return backPicName;
	}

	public void setBackPicName(String backPicName) {
		this.backPicName = backPicName;
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
