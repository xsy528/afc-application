package com.insigma.afc.msutil.model;

import com.insigma.afc.topology.AFCNode;
import com.insigma.commons.util.DateTimeUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 命令日志实体
 * 
 * @author CaiChunye
 */
@Entity
@Table(name = "TMO_CMD_RESULT")
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "S_TMO_CMD_RESULT", allocationSize = 1, initialValue = 1)
//@TableView(colorConvertor = CmdResultRowColorConvertor.class)
public class TmoCmdResult extends AFCNode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long logId;

	private Short cmdType;

	//@ColumnView(name = "命令名称")
	private String cmdName;

	//@ColumnView(name = "操作员名称/编号", convertor = UserNameConvertor.class)
	private String operatorId;

	//@ColumnView(name = "发送时间", convertor = DateTimeConvertor.class)
	private Date occurTime;

	private String tagName;

	private String tagValue;

	//@ColumnView(name = "命令结果/应答码", convertor = AFCMackcodeConvertor.class)
	private Short cmdResult;

	//	//@ColumnView(name = "结果说明")
	private String remark;

	private Short uploadStatus;

	public TmoCmdResult(Long logId, Short lineId, Integer stationId, Long nodeId, String operatorId, String cmdName,
			Short cmdResult, Date occurTime, Short uploadStatus, Short cmdType) {
		this.logId = logId;
		this.lineId = lineId;
		this.stationId = stationId;
		this.nodeId = nodeId;
		this.operatorId = operatorId;
		this.cmdName = cmdName;
		this.cmdResult = cmdResult;
		this.occurTime = occurTime;
		this.uploadStatus = uploadStatus;
		this.cmdType = cmdType;
	}

	public TmoCmdResult() {
	}

	public TmoCmdResult(Long logId) {
		this.logId = logId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@Column(name = "LOG_ID", nullable = false, precision = 20, scale = 0)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "LINE_ID", nullable = false, precision = 5, scale = 0)
	public short getLineId() {
		return this.lineId;
	}

	@Column(name = "STATION_ID", nullable = false, precision = 11, scale = 0)
	public int getStationId() {
		return this.stationId;
	}

	@Column(name = "NODE_ID", nullable = false, scale = 0)
	public long getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "ITEM_TYPE")
	public short getNodeType() {
		return nodeType;
	}

	public void setNodeType(short nodeType) {
		this.nodeType = nodeType;
	}

	@Column(name = "OPERATOR_ID", nullable = false, length = 32)
	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "CMD_NAME", nullable = false, length = 64)
	public String getCmdName() {
		return this.cmdName;
	}

	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	@Column(name = "TAG_NAME", length = 32)
	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Column(name = "TAG_VALUE", length = 100)
	public String getTagValue() {
		return this.tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	@Column(name = "CMD_RESULT", nullable = false, precision = 5, scale = 0)
	public Short getCmdResult() {
		return this.cmdResult;
	}

	public void setCmdResult(Short cmdResult) {
		this.cmdResult = cmdResult;
	}

	@Column(name = "OCCUR_TIME", nullable = false, length = 11)
	public Date getOccurTime() {
		return this.occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "UPLOAD_STATUS", nullable = false, precision = 5, scale = 0)
	public Short getUploadStatus() {
		return this.uploadStatus;
	}

	public void setUploadStatus(Short uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	@Column(name = "CMD_TYPE", nullable = false, precision = 5, scale = 0)
	public Short getCmdType() {
		return this.cmdType;
	}

	public void setCmdType(Short cmdType) {
		this.cmdType = cmdType;
	}

	@Override
	public String toString() {
		return "LineId:" + lineId + "  StationId:" + stationId + "  NodeId:" + nodeId + "  "
				+ DateTimeUtil.formatDate(occurTime, "yyyy-MM-dd HH:mm:ss") + " " + tagName + "_" + tagValue
				+ "  CmdType:" + cmdType;
	}

}
