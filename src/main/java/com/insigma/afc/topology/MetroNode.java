package com.insigma.afc.topology;

public abstract class MetroNode extends AFCNode implements Cloneable {

	private static final long serialVersionUID = 8369790461122870407L;

	public abstract String getPicName();

	public abstract void setPicName(String picName);

	public abstract void setImageLocation(AFCNodeLocation imageLocation);

	public abstract void setTextLocation(AFCNodeLocation TextLocation);

	protected volatile MetroNode parent;

	public void setParent(MetroNode parent) {
		this.parent = parent;
	}

	public MetroNode getParent() {
		return parent;
	}

}
