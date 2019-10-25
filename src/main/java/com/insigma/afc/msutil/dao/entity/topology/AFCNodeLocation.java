/*
 * 
 */
package com.insigma.afc.msutil.dao.entity.topology;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author changjin_qiu
 *
 */
@Embeddable
public class AFCNodeLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	public AFCNodeLocation(){

	}

	public AFCNodeLocation(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	private int x;

	private int y;

	private int angle;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
}
