package com.insigma.afc.requestbody;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Ticket:  树节点查询条件
 * @author  zengziqiang
 *
 */
@ApiModel(value = "前端传递的左侧树查询条件", description = "acc_clean_center、lines、stations、devices")
public class MetroStationVO extends PageVO{

    @ApiModelProperty(name = "acc_clean_center", value = "acc清分中心节点编号")
    @JsonProperty("acc_clean_center")
    private long accCleanCenter;

    @ApiModelProperty(name = "lines", value = "线路节点编号")
    @JsonProperty("lines")
    private List<Long> lines;

    @ApiModelProperty(name = "lines", value = "车站节点编号")
    @JsonProperty("stations")
    private List<Long> stations;

    @ApiModelProperty(name = "devices", value = "设备节点编号")
    @JsonProperty("devices")
    private List<Long> devices;

    public long getAccCleanCenter() {
        return accCleanCenter;
    }

    public void setAccCleanCenter(long accCleanCenter) {
        this.accCleanCenter = accCleanCenter;
    }

    public List<Long> getLines() {
        return lines;
    }

    public void setLines(List<Long> lines) {
        this.lines = lines;
    }

    public List<Long> getStations() {
        return stations;
    }

    public void setStations(List<Long> stations) {
        this.stations = stations;
    }

    public List<Long> getDevices() {
        return devices;
    }

    public void setDevices(List<Long> devices) {
        this.devices = devices;
    }
}
