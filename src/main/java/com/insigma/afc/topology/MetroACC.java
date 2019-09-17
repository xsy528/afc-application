package com.insigma.afc.topology;


import com.insigma.commons.constant.AFCNodeLevel;
import com.insigma.commons.util.NodeIdUtils;

import javax.persistence.*;

/**
 * MetroACC entity.
 * 
 * @author zengziqiang
 */
@Entity
@Table(name = "TMETRO_ACC")
public class MetroACC implements MetroNode {

	private static final long serialVersionUID = 1L;

	public MetroACC() {
	}

	@Id
	@Column(name = "ACC_ID", length = 5)
	private Short accID;

	@Column(name = "ACC_NAME", length = 30)
	private String accName;

	@Column(name = "IP_ADDRESS", length = 32)
	private String ipAddress;

	@Column(name = "STATUS", length = 5)
	private Short status;

	@Column(name = "PIC_NAME")
	private String picName;

	@Column(name = "REMARK", length = 500)
	private String remark;

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

	@Column(name = "EXT_JSON_VALUE", length = 2000)
	private String extJsonValue;

	@Override
	public Long id() {
		if (accID==null){
			return null;
		}
		return NodeIdUtils.nodeIdStrategy.getNodeNo(accID.longValue());
	}

	@Override
	public AFCNodeLevel level() {
		return AFCNodeLevel.ACC;
	}

	@Override
	public String name(){
		return accName;
	}

	public Short getAccID() {
		return accID;
	}

	public void setAccID(Short accID) {
		this.accID = accID;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
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
