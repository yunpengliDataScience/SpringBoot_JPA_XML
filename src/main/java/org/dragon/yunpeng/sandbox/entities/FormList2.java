package org.dragon.yunpeng.sandbox.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formList")
public class FormList2 implements Serializable {

	private List<Form2> forms;

	public FormList2() {
		forms = new ArrayList<Form2>();
	}

	public FormList2(List<Form2> forms) {
		this.forms = forms;
	}

	@XmlElement(name = "form2")
	public List<Form2> getForms() {
		return forms;
	}

	public void setForms(List<Form2> forms) {
		this.forms = forms;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder=new StringBuilder();
		for(Form2 form: forms) {
			stringBuilder.append(form.toString()+"\n");
		}
		
		return stringBuilder.toString();
	}
}
