package com.insigma.afc.topology;


import com.insigma.commons.constant.AFCNodeLevel;

import javax.persistence.*;

/**
 * MetroDevice entity.
 *
 * @author zengziqiang
 */
@Entity
@Table(name = "TMETRO_DEVICE")
@IdClass(MetroDeviceId.class)
public class MetroDevice implements MetroNode {

    private static final long serialVersionUID = 1L;

    public MetroDevice() {
    }

    @Id
    @Column(name="LINE_ID")
    private Short lineId;

    @Id
    @Column(name="STATION_ID")
    private Integer stationId;

    @Id
    @Column(name="DEVICE_ID")
    private Long deviceId;

    @Column(name = "LINE_NAME", nullable = false, length = 30)
    private String lineName;

    @Column(name = "STATION_NAME", nullable = false, length = 30)
    private String stationName;

    @Column(name = "DEVICE_NAME", nullable = false, length = 30)
    private String deviceName;

    @Column(name = "DEVICE_TYPE", nullable = false, precision = 5)
    private Short deviceType;

    @Column(name = "DEVICE_SUB_TYPE", precision = 5)
    private Short deviceSubType;

    @Column(name = "IP_ADDRESS", length = 32)
    private String ipAddress;

    @Column(name = "COMM_PORT")
    private Integer commPort;

    @Column(name = "LOGIC_ADDRESS")
    private Long logicAddress;

    @Column(name = "STATUS", nullable = false, precision = 5)
    private Short status;

    // 网卡的MAC地址
    @Column(name = "ADDR_MAC")
    private String addrMac;

    @Column(name = "REMARK", length = 500)
    private String remark;

    @Column(name = "PIC_NAME")
    private String picName;

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

    public String getPicName() {
        return picName;
    }

    @Override
    public Long id() {
        return deviceId;
    }

    @Override
    public AFCNodeLevel level() {
        return AFCNodeLevel.SLE;
    }

    @Override
    public String name(){
        return deviceName;
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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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

    public Short getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Short deviceType) {
        this.deviceType = deviceType;
    }

    public Short getDeviceSubType() {
        return deviceSubType;
    }

    public void setDeviceSubType(Short deviceSubType) {
        this.deviceSubType = deviceSubType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getCommPort() {
        return commPort;
    }

    public void setCommPort(Integer commPort) {
        this.commPort = commPort;
    }

    public Long getLogicAddress() {
        return logicAddress;
    }

    public void setLogicAddress(Long logicAddress) {
        this.logicAddress = logicAddress;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getAddrMac() {
        return addrMac;
    }

    public void setAddrMac(String addrMac) {
        this.addrMac = addrMac;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPicName(String picName) {
        this.picName = picName;
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