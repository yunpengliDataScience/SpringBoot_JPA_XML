package org.dragon.yunpeng.sandbox.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "form2")
public class Form2 implements Serializable{

	
	String x;
	
	String y;
	
	@XmlElement
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	
	@XmlElement
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Form2 [x=" + x + ", y=" + y + "]";
	}
}
