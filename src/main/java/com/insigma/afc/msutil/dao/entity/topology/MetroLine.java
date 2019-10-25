package com.insigma.afc.msutil.dao.entity.topology;


import com.insigma.commons.constant.AFCNodeLevel;
import com.insigma.commons.util.NodeIdUtils;

import javax.persistence.*;

/**
 * MetroLine entity.
 * 
 * @author 廖红自
 */
@Entity
@Table(name = "TMETRO_LINE")
public class MetroLine implements MetroNode {

	private static final long serialVersionUID = 1L;

	public MetroLine() {
	}

	@Id
	@Column(name = "LINE_ID", length = 5)
	private Short lineID;

	@Column(name = "LINE_NAME", length = 30)
	private String lineName;

	@Column(name = "IP_ADDRESS", length = 32)
	private String ipAddress;

	@Column(name = "PIC_NAME")
	private String picName;

	@Column(name = "REMARK", length = 500)
	private String remark;

	@Column(name = "STATUS", length = 5)
	private Short status;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "x", column = @Column(name = "IMAGE_X", nullable = false)),
			@AttributeOverride(name = "y", column = @Column(name = "IMAGE_Y", nullable = false)),
			@AttributeOverride(name = "angle", column = @Column(name = "IMAGE_ANGLE", nullable = false)) })
	private AFCNodeLocation imageLocation;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "x", column = @Column(name = "TEXT_X", nullable = false)),
			@AttributeOverride(name = "y", column = @Column(name = "TEXT_Y", nullable = false)),
			@AttributeOverride(name = "angle", column = @Column(name = "TEXT_ANGLE", nullable = false)) })
	private AFCNodeLocation textLocation;

	@Column(name = "EXT_JSON_VALUE", length = 2000)
	private String extJsonValue;

	@Override
	public Long id() {
		if (lineID==null){
			return null;
		}
		return NodeIdUtils.nodeIdStrategy.getNodeNo(lineID.longValue());
	}

	@Override
	public AFCNodeLevel level() {
		return AFCNodeLevel.LC;
	}

	@Override
	public String name(){
		return lineName;
	}

	public Short getLineID() {
		return lineID;
	}

	public void setLineID(Short lineID) {
		this.lineID = lineID;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public String getExtJsonValue() {
		return extJsonValue;
	}

	public void setExtJsonValue(String extJsonValue) {
		this.extJsonValue = extJsonValue;
	}
}
