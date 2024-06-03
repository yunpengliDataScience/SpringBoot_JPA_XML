package org.dragon.yunpeng.sandbox.services;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dragon.yunpeng.sandbox.entities.FormList;
import org.dragon.yunpeng.sandbox.pojos.RootElement;
import org.springframework.stereotype.Service;

@Service(value = "xmlFileService")
public class XMLFileService {

	public FormList unmarshallXML(String filePath) {
		FormList formList = null;

		// Unmarshal XML string into a Form object
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FormList.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			formList = (FormList) unmarshaller.unmarshal(new File(filePath));

			System.out.println(formList);
		} catch (JAXBException e) {

			e.printStackTrace();
		}

		return formList;
	}

	public RootElement unmarshallXMLToRootElement(String filePath) {
		RootElement root = null;

		// Unmarshal XML string into a Form object
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RootElement.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			root = (RootElement) unmarshaller.unmarshal(new File(filePath));

			System.out.println(root);
		} catch (JAXBException e) {

			e.printStackTrace();
		}

		return root;
	}
}
