package org.dragon.yunpeng.sandbox.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dragon.yunpeng.sandbox.entities.Form;
import org.dragon.yunpeng.sandbox.entities.FormList;
import org.dragon.yunpeng.sandbox.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "formService")
public class FormServiceImp implements IFormService {

	@Autowired
	private FormRepository formRepository;

	@Override
	public List<String> readCodeFromFile() {

		List<String> codes = new ArrayList<String>();

		String fileName = "code.txt";

		// Get the current working directory of the java process
		String workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);

		// Construct the absolute path to the file
		String filePath = workingDirectory + File.separator + fileName;

		// Read the file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				// Process each line of the file
				System.out.println(line);
				codes.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return codes;
	}

	@Override
	public void exportData() {
		try {

			List<Form> forms = (List<Form>) formRepository.findAll();

			FormList formList = new FormList(forms);
			// Create JAXB context and marshaller
			JAXBContext context = JAXBContext.newInstance(FormList.class);
			Marshaller marshaller = context.createMarshaller();

			String workingDirectory = System.getProperty("user.dir");
			// Convert the list to XML and write to a file
			File outputFile = new File(workingDirectory + "\\target\\forms.xml");

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(formList, outputFile);

			System.out.println("XML file created at: " + outputFile.getAbsolutePath());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportDataToString() {

		StringWriter writer = new StringWriter();

		try {

			List<Form> forms = (List<Form>) formRepository.findAll();

			FormList formList = new FormList(forms);

			// Create JAXB context and marshaller
			JAXBContext context = JAXBContext.newInstance(FormList.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(formList, writer);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return writer.toString();
	}

	@Override
	public List<Form> getAllForms() {
		return (List<Form>) formRepository.findAll();
	}

	@Override
	public Form getForm(Long id) {
		Form form = formRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));

		return form;
	}

	@Override
	public Form saveForm(Form form) {
		return formRepository.save(form);
	}

	@Override
	public void deleteForm(Long id) {
		formRepository.deleteById(id);
	}

	@Override
	public Form unmarshallXML(InputStream xmlInputStream) {
		Form form = null;

		// Unmarshal XML string into a Form object
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Form.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			form = (Form) unmarshaller.unmarshal(xmlInputStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return form;
	}

}
