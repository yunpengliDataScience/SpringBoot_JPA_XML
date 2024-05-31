package org.dragon.yunpeng.sandbox.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formList")
public class FormList implements Serializable {

	private List<Form> forms;
	
	private List<Form2> forms2;

	public FormList() {
		forms = new ArrayList<Form>();
		forms2 = new ArrayList<Form2>();
	}

	public FormList(List<Form> forms) {
		this.forms = forms;
	}

	@XmlElement(name = "form")
	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}
	
	@XmlElement(name = "form2")
	public List<Form2> getForms2() {
		return forms2;
	}

	public void setForms2(List<Form2> forms2) {
		this.forms2 = forms2;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder=new StringBuilder();
		
		if(!forms.isEmpty()) {
			for(Form form: forms) {
				stringBuilder.append(form.toString()+"\n");
			}
		}
		
		if(!forms2.isEmpty()) {
			for(Form2 form2: forms2) {
				stringBuilder.append(form2.toString()+"\n");
			}
		}
		
		return stringBuilder.toString();
	}
}
