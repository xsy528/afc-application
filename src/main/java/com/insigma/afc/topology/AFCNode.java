/**
 * 
 */
package com.insigma.afc.topology;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qcj(qiuchangjin@zdwxgd.com)
 *
 */
public class AFCNode implements Serializable {
	private static final long serialVersionUID = -8239744355715269518L;

	protected AFCNode parent;

	protected short lineId;

	protected int stationId;

	/**
	 * 节点编号：例如：由线路+车站+设备类型+设备ID 组成，8 位数字字符。
	 * @return
	 */
	//	@ColumnView(name = "节点名称/节点编码", convertor = MetroNodeConvertor.class)
	protected long nodeId;

	/**
	 * 类型的值
	 * @return
	 */
	protected short nodeType;

	public AFCNode() {
		super();
	}

	public AFCNode(short lineId, int stationId, long nodeId, short nodeType) {
		super();
		this.lineId = lineId;
		this.stationId = stationId;
		this.nodeId = nodeId;
		this.nodeType = nodeType;
	}

	/**
	 * 编号
	 * @return
	 */
	@Transient
	public long id() {
		return nodeId;
	}

	/**
	 * 名称
	 * @return
	 */
	//	@Transient
	//	public String name() {
	//		AFCNodeLevel level = level();
	//		if (level == AFCNodeLevel.ACC) {
	//			return AFCApplication.getNodeName(getNodeId());
	//		} else if (level == AFCNodeLevel.LC) {
	//			return AFCApplication.getNodeName(getLineId());
	//		} else if (level == AFCNodeLevel.SC) {
	//			return AFCApplication.getNodeName(getStationId());
	//		} else {
	//			return AFCApplication.getNodeName(nodeId);
	//		}
	//	}

	public short getLineId() {
		return lineId;
	}

	public void setLineId(short lineId) {
		this.lineId = lineId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	/**
	 * 节点编号：例如：由线路+车站+设备类型+设备ID 组成，8 位数字字符。
	 * @return
	 */
	public long getNodeId() {
		return nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	public short getNodeType() {
		return nodeType;
	}

	public void setNodeType(short nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * 级别
	 * @return
	 */
	//	@Transient
	//	public AFCNodeLevel level() {
	//		return AFCApplication.getNodeLevel(nodeId);
	//	}

	@Transient
	public List<? extends AFCNode> getSubNodes() {
		return null;
	}

	@Transient
	public AFCNode getParent() {
		return parent;
	}

	@Transient
	public <T extends AFCNode> T getAFCNodeInfo(Class<? extends T> afcNodeInfoType) {
		if (!nodeInfos.containsKey(afcNodeInfoType)) {
		}
		return (T) nodeInfos.get(afcNodeInfoType);
	}

	Map<Class, AFCNode> nodeInfos = new HashMap<Class, AFCNode>();

	public synchronized void putInfo(AFCNode info) {
		this.nodeInfos.put(info.getClass(), info);
	}

	/**
	 * @return the isVirtual
	 */
	@Transient
	public boolean isVirtual() {
		return false;
	}

}
