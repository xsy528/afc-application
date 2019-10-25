package com.insigma.afc.msutil.model;


public class CommandResult  {

	private String logId;

	//@ColumnView(name = "节点名称", width = 200)
	private String id;

	private String operatorId;

	//@ColumnView(name = "命令名称")
	private String cmdName;

	//	//@ColumnView(name = "命令参数")
	private String arg;

	private short result;

	//@ColumnView(name = "命令结果", convertor = CmdResultConvertor.class)
	private String cmdResult;

	//@ColumnView(name = "发送时间")
	private String occurTime;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getCmdName() {
		return cmdName;
	}

	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	public String getCmdResult() {
		return cmdResult;
	}

	public void setCmdResult(String cmdResult) {
		this.cmdResult = cmdResult;
	}

	public String getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

//	public Color getBackgound() {
//		return null;
//	}

//	public Color getForeground() {
//		if (result != 0) {
//			return ColorConstants.COLOR_ERROR;
//		}
//		return ColorConstants.COLOR_NORMAL;
//	}

	public short getResult() {
		return result;
	}

	public void setResult(short result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "CommandResult{" +
				"logId='" + logId + '\'' +
				", id='" + id + '\'' +
				", operatorId='" + operatorId + '\'' +
				", cmdName='" + cmdName + '\'' +
				", arg='" + arg + '\'' +
				", result=" + result +
				", cmdResult='" + cmdResult + '\'' +
				", occurTime='" + occurTime + '\'' +
				'}';
	}
}
