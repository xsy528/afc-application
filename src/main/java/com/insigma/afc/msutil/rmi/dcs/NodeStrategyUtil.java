package com.insigma.afc.msutil.rmi.dcs;

import com.insigma.afc.application.AFCNodeLevel;

public class NodeStrategyUtil {

    public static long getNodeId(long id){
        //仅有车站号0421
        if (id > 99 && id < 9999 && id != 1000) {
            return (int) id * 100000 +  1000;
        }
        //仅有线路号04
        else if (id <= 99) {
            return (int) id * 10000000 +  1000;
        }
        //id全
        else if (id > 9999 && id < 999999999) {
            return id;
        }
        //accid
        else if (id > 99 && id < 9999 && id == 1000) {
            return id;
        } else {
            throw new RuntimeException("无效的nodeId" + id);
        }
    }

    public static int getStationId(final long nodeId) {
        if (nodeId > 99 && nodeId < 9999 && nodeId != 1000) {
            return (int) nodeId;
        } else if ((nodeId / 100000) > 0) {
            return (int) (nodeId / 100000);
        } else if (((nodeId / 100000) % 100) == 0) {
            return 0;
        } else {
            throw new RuntimeException("无效的nodeId" + nodeId);
        }
    }

    public static short getStationOnlyId(long nodeId) {
        if (nodeId > 99 && nodeId < 9999 && nodeId != 1000) {
            return (short) (nodeId % 100);
        } else if ((nodeId / 100000 % 100) > 0) {
            return (short) (nodeId / 100000 % 100);
        } else if (((nodeId / 100000) % 100) == 0) {
            return 0;
        } else {
            throw new RuntimeException("无效的nodeId" + nodeId);
        }
    }

    public static AFCNodeLevel getNodeLevel(final long nodeId) {
        short deviceType = getNodeType(nodeId);
        short lineId = getLineId(nodeId);
        int stationId = getStationOnlyId(nodeId);
        if (lineId == 0 && stationId == 0 && deviceType == 1) {
            return AFCNodeLevel.ACC;
        } else if (lineId != 0 && stationId == 0 && deviceType == 1) {
            return AFCNodeLevel.LC;
        } else if (lineId != 0 && stationId != 0 && deviceType == 1) {
            return AFCNodeLevel.SC;
        } else {
            return AFCNodeLevel.SLE;
        }
    }

    public static short getNodeType(final long nodeId) {
        long newNodeID = getNodeId(nodeId);
        short nodeType = (short) ((newNodeID / 1000) % 100);
        return nodeType;
    }

    public static short getLineId(final long nodeId) {
        if (nodeId < 99) {
            return (short) nodeId;
        } else if (nodeId > 99 && nodeId < 9999 && nodeId != 1000) {
            return (short) (nodeId / 100);
        } else {
            return (short) (nodeId / 10000000);
        }
    }
}
