package org.dragon.yunpeng.sandbox.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.dragon.yunpeng.sandbox.entities.Library;
import org.dragon.yunpeng.sandbox.pojos.RootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service(value = "xmlFileService")
public class XMLFileService {

	Logger logger = LoggerFactory.getLogger(XMLFileService.class);

	public RootElement unmarshallXMLToRootElement(String filePath) {
		RootElement root = null;

		// Unmarshal XML string into a Form object
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RootElement.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			unmarshaller.setEventHandler(new XmlParseValidationEventHandler());
			root = (RootElement) unmarshaller.unmarshal(new File(filePath));

			System.out.println(root);
		} catch (JAXBException e) {

			throw new RuntimeException(e);
		}

		return root;
	}

	public void marshallBooksToXML(String filePath, int numOfRecords) {

		RootElement root = new RootElement();

		List<Book> bookList = new ArrayList<Book>();

		for (long i = 1; i <= numOfRecords; i++) {
			Book book = new Book();
			book.setId(i);
			book.setAuthor("Cliff Lee");
			book.setIsbn("ABCD" + i);
			book.setTitle("Title " + i);
			book.setLibraryID(1l);

			bookList.add(book);
		}

		root.setBookList(bookList);

		try {
			JAXBContext context = JAXBContext.newInstance(RootElement.class);

			// Create Marshaller
			Marshaller marshaller = context.createMarshaller();

			// To make the XML output pretty
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Marshal the object to the console
			// marshaller.marshal(root, System.out);

			// Marshal the Library object to a file
			marshaller.marshal(root, new File(filePath));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void marshallLibrarysToXML(String filePath, int numOfRecords) {

		RootElement root = new RootElement();

		List<Library> libraryList = new ArrayList<Library>();

		for (long i = 1; i <= numOfRecords; i++) {
			Library library = new Library();
			library.setId(i);
			library.setName("My Library " + i);

			libraryList.add(library);
		}

		root.setLibraryList(libraryList);

		try {
			JAXBContext context = JAXBContext.newInstance(RootElement.class);

			// Create Marshaller
			Marshaller marshaller = context.createMarshaller();

			// To make the XML output pretty
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Marshal the object to the console
			// marshaller.marshal(root, System.out);

			// Marshal the Library object to a file
			marshaller.marshal(root, new File(filePath));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public RootElement unmarshallXMLToRootElement(InputStream xmlInputStream) {
		RootElement root = null;

		// Unmarshal XML string into a Form object
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RootElement.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			root = (RootElement) unmarshaller.unmarshal(xmlInputStream);
		} catch (JAXBException e) {

			e.printStackTrace();
		}

		return root;
	}

	public boolean isValidXml(MultipartFile file) {

		boolean isValidXML = false;
		// Read the contents of the MultipartFile
		String xmlContent;
		try {
			xmlContent = new String(file.getBytes());
			// Parse the contents as XML
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(null); // Disable error handler to prevent exceptions from being thrown
			InputSource inputSource = new InputSource(new StringReader(xmlContent));
			builder.parse(inputSource);

			// If parsing is successful, return true
			isValidXML = true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return isValidXML;
	}
}
