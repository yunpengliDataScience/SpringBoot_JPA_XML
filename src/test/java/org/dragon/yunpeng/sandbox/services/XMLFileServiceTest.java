package org.dragon.yunpeng.sandbox.services;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XMLFileServiceTest {

	@Autowired
	private XMLFileService xmlFileService;

	@Test
	public void tesMarshallToXML() {

		String workingDirectory = System.getProperty("user.dir");
		System.out.println("workingDirectory=" + workingDirectory);

		String fileDirectory = workingDirectory + File.separator + "sampleXMLs" + File.separator + "large"
				+ File.separator;

		xmlFileService.marshallBooksToXML(fileDirectory + "BookList.xml", 20000);

		xmlFileService.marshallLibrarysToXML(fileDirectory + "LibraryList.xml", 20000);
	}

	// @Test
	public void testUnmarshallXML() {

		String workingDirectory = System.getProperty("user.dir");
		System.out.println("workingDirectory=" + workingDirectory);

		String fileDirectory = workingDirectory + File.separator + "sampleXMLs" + File.separator;

		xmlFileService.unmarshallXMLToRootElement(fileDirectory + "LibraryList.xml");

		xmlFileService.unmarshallXMLToRootElement(fileDirectory + "BookList.xml");
	}
}
