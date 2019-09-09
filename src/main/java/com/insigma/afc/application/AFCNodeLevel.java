package com.insigma.afc.application;

/**
 * 节点类型 Ticket: <br/>
 * 
 * @author 邱昌进(qiuchangjin@zdwxjd.com)
 */
public enum AFCNodeLevel {
    ACC(0), LC(1), SC(2), SLE(3), MODULE(4);

    private final int nodeType;

    AFCNodeLevel(int nodeType) {
        this.nodeType = nodeType;
    }

    public int getStatusCode() {
        return nodeType;
    }
}