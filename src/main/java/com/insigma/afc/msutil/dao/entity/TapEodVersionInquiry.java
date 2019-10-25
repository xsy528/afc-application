package com.insigma.afc.msutil.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * 版本查询表
 */
@Entity
@Table(name = "TAP_EOD_VERSION_INQUIRY_T")
@SequenceGenerator(name = "SEQ_GEN", sequenceName = "S_TAP_EOD_VERSION_INQUIRY_T")
public class TapEodVersionInquiry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3913771880998651541L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@Column(name = "RECORD_ID")
	private Long recordId;

	/** 线路代码 */
	@Column(name = "LINE_ID")
	private short lineId;

	/** 车站代码 */
	@Column(name = "STATION_ID")
	private Integer stationId;

	/** 节点序号 */
	@Column(name = "NODE_ID")
	private long nodeId;

	/** 节点类型 */
	@Column(name = "ITEM_TYPE")
	private short nodeType;

	/** 读写器号 */
	@Column(name = "VERSION_NUMBER49")
	private Long tpId;

	/** 最后更新时间 */
	@Column(name = "LAST_UPDATE_TIME")
	private Date lastUpdateTime;

	@Column(name = "VERSION_NUMBER0")
	private Long versionNumber0;

	@Column(name = "VERSION_NUMBER1")
	private Long versionNumber1;

	@Column(name = "VERSION_NUMBER2")
	private Long versionNumber2;

	@Column(name = "VERSION_NUMBER3")
	private Long versionNumber3;

	@Column(name = "VERSION_NUMBER4")
	private Long versionNumber4;

	@Column(name = "VERSION_NUMBER5")
	private Long versionNumber5;

	@Column(name = "VERSION_NUMBER6")
	private Long versionNumber6;

	@Column(name = "VERSION_NUMBER7")
	private Long versionNumber7;

	@Column(name = "VERSION_NUMBER8")
	private Long versionNumber8;

	@Column(name = "VERSION_NUMBER9")
	private Long versionNumber9;

	@Column(name = "VERSION_NUMBER10")
	private Long versionNumber10;

	@Column(name = "VERSION_NUMBER11")
	private Long versionNumber11;

	@Column(name = "VERSION_NUMBER12")
	private Long versionNumber12;

	@Column(name = "VERSION_NUMBER13")
	private Long versionNumber13;

	@Column(name = "VERSION_NUMBER14")
	private Long versionNumber14;

	@Column(name = "VERSION_NUMBER15")
	private Long versionNumber15;

	@Column(name = "VERSION_NUMBER16")
	private Long versionNumber16;

	@Column(name = "VERSION_NUMBER17")
	private Long versionNumber17;

	@Column(name = "VERSION_NUMBER18")
	private Long versionNumber18;

	@Column(name = "VERSION_NUMBER19")
	private Long versionNumber19;

	@Column(name = "VERSION_NUMBER20")
	private Long versionNumber20;

	@Column(name = "VERSION_NUMBER21")
	private Long versionNumber21;

	@Column(name = "VERSION_NUMBER22")
	private Long versionNumber22;

	@Column(name = "VERSION_NUMBER23")
	private Long versionNumber23;

	@Column(name = "VERSION_NUMBER24")
	private Long versionNumber24;

	@Column(name = "VERSION_NUMBER25")
	private Long versionNumber25;

	@Column(name = "VERSION_NUMBER26")
	private Long versionNumber26;

	@Column(name = "VERSION_NUMBER27")
	private Long versionNumber27;

	@Column(name = "VERSION_NUMBER28")
	private Long versionNumber28;

	@Column(name = "VERSION_NUMBER29")
	private Long versionNumber29;

	@Column(name = "VERSION_NUMBER30")
	private Long versionNumber30;

	@Column(name = "VERSION_NUMBER31")
	private Long versionNumber31;

	@Column(name = "VERSION_NUMBER32")
	private Long versionNumber32;

	@Column(name = "VERSION_NUMBER33")
	private Long versionNumber33;

	@Column(name = "VERSION_NUMBER34")
	private Long versionNumber34;

	@Column(name = "VERSION_NUMBER35")
	private Long versionNumber35;

	@Column(name = "VERSION_NUMBER36")
	private Long versionNumber36;

	@Column(name = "VERSION_NUMBER37")
	private Long versionNumber37;

	@Column(name = "VERSION_NUMBER38")
	private Long versionNumber38;

	@Column(name = "VERSION_NUMBER39")
	private Long versionNumber39;

	@Column(name = "VERSION_NUMBER40")
	private Long versionNumber40;

	@Column(name = "VERSION_NUMBER41")
	private Long versionNumber41;

	@Column(name = "VERSION_NUMBER42")
	private Long versionNumber42;

	@Column(name = "VERSION_NUMBER43")
	private Long versionNumber43;

	@Column(name = "VERSION_NUMBER44")
	private Long versionNumber44;

	@Column(name = "VERSION_NUMBER45")
	private Long versionNumber45;

	@Column(name = "VERSION_NUMBER46")
	private Long versionNumber46;

	@Column(name = "VERSION_NUMBER47")
	private Long versionNumber47;

	@Column(name = "VERSION_NUMBER48")
	private Long versionNumber48;

	/** full constructor */
	public TapEodVersionInquiry(short lineId, long nodeId, Integer stationId, Date lastUpdateTime, Long versionNumber0,
                                Long versionNumber1, Long versionNumber2, Long versionNumber3, Long versionNumber4, Long versionNumber5,
                                Long versionNumber6, Long versionNumber7, Long versionNumber8, Long versionNumber9, Long versionNumber10,
                                Long versionNumber11, Long versionNumber12, Long versionNumber13, Long versionNumber14,
                                Long versionNumber15, Long versionNumber16, Long versionNumber17, Long versionNumber18,
                                Long versionNumber19, Long versionNumber20, Long versionNumber21, Long versionNumber22,
                                Long versionNumber23, Long versionNumber24, Long versionNumber25, Long versionNumber26,
                                Long versionNumber27, Long versionNumber28, Long versionNumber29, Long versionNumber30,
                                Long versionNumber31, Long versionNumber32, Long versionNumber33, Long versionNumber34,
                                Long versionNumber35, Long versionNumber36, Long versionNumber37, Long versionNumber38,
                                Long versionNumber39, Long versionNumber40, Long versionNumber41, Long versionNumber42,
                                Long versionNumber43, Long versionNumber44, Long versionNumber45, Long versionNumber46,
                                Long versionNumber47, Long versionNumber48) {
		this.lineId = lineId;
		this.nodeId = nodeId;
		this.stationId = stationId;
		this.lastUpdateTime = lastUpdateTime;
		this.versionNumber0 = versionNumber0;
		this.versionNumber1 = versionNumber1;
		this.versionNumber2 = versionNumber2;
		this.versionNumber3 = versionNumber3;
		this.versionNumber4 = versionNumber4;
		this.versionNumber5 = versionNumber5;
		this.versionNumber6 = versionNumber6;
		this.versionNumber7 = versionNumber7;
		this.versionNumber8 = versionNumber8;
		this.versionNumber9 = versionNumber9;
		this.versionNumber10 = versionNumber10;
		this.versionNumber11 = versionNumber11;
		this.versionNumber12 = versionNumber12;
		this.versionNumber13 = versionNumber13;
		this.versionNumber14 = versionNumber14;
		this.versionNumber15 = versionNumber15;
		this.versionNumber16 = versionNumber16;
		this.versionNumber17 = versionNumber17;
		this.versionNumber18 = versionNumber18;
		this.versionNumber19 = versionNumber19;
		this.versionNumber20 = versionNumber20;
		this.versionNumber21 = versionNumber21;
		this.versionNumber22 = versionNumber22;
		this.versionNumber23 = versionNumber23;
		this.versionNumber24 = versionNumber24;
		this.versionNumber25 = versionNumber25;
		this.versionNumber26 = versionNumber26;
		this.versionNumber27 = versionNumber27;
		this.versionNumber28 = versionNumber28;
		this.versionNumber29 = versionNumber29;
		this.versionNumber30 = versionNumber30;
		this.versionNumber31 = versionNumber31;
		this.versionNumber32 = versionNumber32;
		this.versionNumber33 = versionNumber33;
		this.versionNumber34 = versionNumber34;
		this.versionNumber35 = versionNumber35;
		this.versionNumber36 = versionNumber36;
		this.versionNumber37 = versionNumber37;
		this.versionNumber38 = versionNumber38;
		this.versionNumber39 = versionNumber39;
		this.versionNumber40 = versionNumber40;
		this.versionNumber41 = versionNumber41;
		this.versionNumber42 = versionNumber42;
		this.versionNumber43 = versionNumber43;
		this.versionNumber44 = versionNumber44;
		this.versionNumber45 = versionNumber45;
		this.versionNumber46 = versionNumber46;
		this.versionNumber47 = versionNumber47;
		this.versionNumber48 = versionNumber48;
	}

	/** default constructor */
	public TapEodVersionInquiry() {
	}

	/** minimal constructor */
	public TapEodVersionInquiry(short lineId, long nodeId, Integer stationId, Date lastUpdateTime) {
		this.lineId = lineId;
		this.nodeId = nodeId;
		this.stationId = stationId;
		this.lastUpdateTime = lastUpdateTime;
	}

	public short getNodeType() {
		return nodeType;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public void setNodeType(short nodeType) {
		this.nodeType = nodeType;
	}

	public short getLineId() {
		return this.lineId;
	}

	public void setLineId(short lineId) {
		this.lineId = lineId;
	}

	public long getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}

	public int getStationId() {
		return this.stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getVersionNumber0() {
		return versionNumber0;
	}

	public void setVersionNumber0(Long versionNumber0) {
		this.versionNumber0 = versionNumber0;
	}

	public Long getVersionNumber1() {
		return versionNumber1;
	}

	public void setVersionNumber1(Long versionNumber1) {
		this.versionNumber1 = versionNumber1;
	}

	public Long getVersionNumber2() {
		return versionNumber2;
	}

	public void setVersionNumber2(Long versionNumber2) {
		this.versionNumber2 = versionNumber2;
	}

	public Long getVersionNumber3() {
		return versionNumber3;
	}

	public void setVersionNumber3(Long versionNumber3) {
		this.versionNumber3 = versionNumber3;
	}

	public Long getVersionNumber4() {
		return versionNumber4;
	}

	public void setVersionNumber4(Long versionNumber4) {
		this.versionNumber4 = versionNumber4;
	}

	public Long getVersionNumber5() {
		return versionNumber5;
	}

	public void setVersionNumber5(Long versionNumber5) {
		this.versionNumber5 = versionNumber5;
	}

	public Long getVersionNumber6() {
		return versionNumber6;
	}

	public void setVersionNumber6(Long versionNumber6) {
		this.versionNumber6 = versionNumber6;
	}

	public Long getVersionNumber7() {
		return versionNumber7;
	}

	public void setVersionNumber7(Long versionNumber7) {
		this.versionNumber7 = versionNumber7;
	}

	public Long getVersionNumber8() {
		return versionNumber8;
	}

	public void setVersionNumber8(Long versionNumber8) {
		this.versionNumber8 = versionNumber8;
	}

	public Long getVersionNumber9() {
		return versionNumber9;
	}

	public void setVersionNumber9(Long versionNumber9) {
		this.versionNumber9 = versionNumber9;
	}

	public Long getVersionNumber10() {
		return versionNumber10;
	}

	public void setVersionNumber10(Long versionNumber10) {
		this.versionNumber10 = versionNumber10;
	}

	public Long getVersionNumber11() {
		return versionNumber11;
	}

	public void setVersionNumber11(Long versionNumber11) {
		this.versionNumber11 = versionNumber11;
	}

	public Long getVersionNumber12() {
		return versionNumber12;
	}

	public void setVersionNumber12(Long versionNumber12) {
		this.versionNumber12 = versionNumber12;
	}

	public Long getVersionNumber13() {
		return versionNumber13;
	}

	public void setVersionNumber13(Long versionNumber13) {
		this.versionNumber13 = versionNumber13;
	}

	public Long getVersionNumber14() {
		return versionNumber14;
	}

	public void setVersionNumber14(Long versionNumber14) {
		this.versionNumber14 = versionNumber14;
	}

	public Long getVersionNumber15() {
		return versionNumber15;
	}

	public void setVersionNumber15(Long versionNumber15) {
		this.versionNumber15 = versionNumber15;
	}

	public Long getVersionNumber16() {
		return versionNumber16;
	}

	public void setVersionNumber16(Long versionNumber16) {
		this.versionNumber16 = versionNumber16;
	}

	public Long getVersionNumber17() {
		return versionNumber17;
	}

	public void setVersionNumber17(Long versionNumber17) {
		this.versionNumber17 = versionNumber17;
	}

	public Long getVersionNumber18() {
		return versionNumber18;
	}

	public void setVersionNumber18(Long versionNumber18) {
		this.versionNumber18 = versionNumber18;
	}

	public Long getVersionNumber19() {
		return versionNumber19;
	}

	public void setVersionNumber19(Long versionNumber19) {
		this.versionNumber19 = versionNumber19;
	}

	public Long getVersionNumber20() {
		return versionNumber20;
	}

	public void setVersionNumber20(Long versionNumber20) {
		this.versionNumber20 = versionNumber20;
	}

	public Long getVersionNumber21() {
		return versionNumber21;
	}

	public void setVersionNumber21(Long versionNumber21) {
		this.versionNumber21 = versionNumber21;
	}

	public Long getVersionNumber22() {
		return versionNumber22;
	}

	public void setVersionNumber22(Long versionNumber22) {
		this.versionNumber22 = versionNumber22;
	}

	public Long getVersionNumber23() {
		return versionNumber23;
	}

	public void setVersionNumber23(Long versionNumber23) {
		this.versionNumber23 = versionNumber23;
	}

	public Long getVersionNumber24() {
		return versionNumber24;
	}

	public void setVersionNumber24(Long versionNumber24) {
		this.versionNumber24 = versionNumber24;
	}

	public Long getVersionNumber25() {
		return versionNumber25;
	}

	public void setVersionNumber25(Long versionNumber25) {
		this.versionNumber25 = versionNumber25;
	}

	public Long getVersionNumber26() {
		return versionNumber26;
	}

	public void setVersionNumber26(Long versionNumber26) {
		this.versionNumber26 = versionNumber26;
	}

	public Long getVersionNumber27() {
		return versionNumber27;
	}

	public void setVersionNumber27(Long versionNumber27) {
		this.versionNumber27 = versionNumber27;
	}

	public Long getVersionNumber28() {
		return versionNumber28;
	}

	public void setVersionNumber28(Long versionNumber28) {
		this.versionNumber28 = versionNumber28;
	}

	public Long getVersionNumber29() {
		return versionNumber29;
	}

	public void setVersionNumber29(Long versionNumber29) {
		this.versionNumber29 = versionNumber29;
	}

	public Long getVersionNumber30() {
		return versionNumber30;
	}

	public void setVersionNumber30(Long versionNumber30) {
		this.versionNumber30 = versionNumber30;
	}

	public Long getVersionNumber31() {
		return versionNumber31;
	}

	public void setVersionNumber31(Long versionNumber31) {
		this.versionNumber31 = versionNumber31;
	}

	public Long getVersionNumber32() {
		return versionNumber32;
	}

	public void setVersionNumber32(Long versionNumber32) {
		this.versionNumber32 = versionNumber32;
	}

	public Long getVersionNumber33() {
		return versionNumber33;
	}

	public void setVersionNumber33(Long versionNumber33) {
		this.versionNumber33 = versionNumber33;
	}

	public Long getVersionNumber34() {
		return versionNumber34;
	}

	public void setVersionNumber34(Long versionNumber34) {
		this.versionNumber34 = versionNumber34;
	}

	public Long getVersionNumber35() {
		return versionNumber35;
	}

	public void setVersionNumber35(Long versionNumber35) {
		this.versionNumber35 = versionNumber35;
	}

	public Long getVersionNumber36() {
		return versionNumber36;
	}

	public void setVersionNumber36(Long versionNumber36) {
		this.versionNumber36 = versionNumber36;
	}

	public Long getVersionNumber37() {
		return versionNumber37;
	}

	public void setVersionNumber37(Long versionNumber37) {
		this.versionNumber37 = versionNumber37;
	}

	public Long getVersionNumber38() {
		return versionNumber38;
	}

	public void setVersionNumber38(Long versionNumber38) {
		this.versionNumber38 = versionNumber38;
	}

	public Long getVersionNumber39() {
		return versionNumber39;
	}

	public void setVersionNumber39(Long versionNumber39) {
		this.versionNumber39 = versionNumber39;
	}

	public Long getVersionNumber40() {
		return versionNumber40;
	}

	public void setVersionNumber40(Long versionNumber40) {
		this.versionNumber40 = versionNumber40;
	}

	public Long getVersionNumber41() {
		return versionNumber41;
	}

	public void setVersionNumber41(Long versionNumber41) {
		this.versionNumber41 = versionNumber41;
	}

	public Long getVersionNumber42() {
		return versionNumber42;
	}

	public void setVersionNumber42(Long versionNumber42) {
		this.versionNumber42 = versionNumber42;
	}

	public Long getVersionNumber43() {
		return versionNumber43;
	}

	public void setVersionNumber43(Long versionNumber43) {
		this.versionNumber43 = versionNumber43;
	}

	public Long getVersionNumber44() {
		return versionNumber44;
	}

	public void setVersionNumber44(Long versionNumber44) {
		this.versionNumber44 = versionNumber44;
	}

	public Long getVersionNumber45() {
		return versionNumber45;
	}

	public void setVersionNumber45(Long versionNumber45) {
		this.versionNumber45 = versionNumber45;
	}

	public Long getVersionNumber46() {
		return versionNumber46;
	}

	public void setVersionNumber46(Long versionNumber46) {
		this.versionNumber46 = versionNumber46;
	}

	public Long getVersionNumber47() {
		return versionNumber47;
	}

	public void setVersionNumber47(Long versionNumber47) {
		this.versionNumber47 = versionNumber47;
	}

	public Long getVersionNumber48() {
		return versionNumber48;
	}

	public void setVersionNumber48(Long versionNumber48) {
		this.versionNumber48 = versionNumber48;
	}

	public Long getTpId() {
		return this.tpId;
	}

	public void setTpId(Long versionNumber49) {
		this.tpId = versionNumber49;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return new ToStringBuilder(this).append("lineId", getLineId()).append("nodeId", getNodeId())
				.append("stationId", getStationId()).toString();
	}

	//	public boolean equals(Object other) {
	//		if (!(other instanceof TapEodVersionInquiry))
	//			return false;
	//		TapEodVersionInquiry castOther = (TapEodVersionInquiry) other;
	//		return new EqualsBuilder().append(this.getLineId(), castOther.getLineId())
	//				.append(this.getNodeId(), castOther.getNodeId()).append(this.getStationId(), castOther.getStationId())
	//				.isEquals();
	//	}

	//	public int hashCode() {
	//		return new HashCodeBuilder().append(getLineId()).append(getNodeId()).append(getStationId()).toHashCode();
	//	}

}
