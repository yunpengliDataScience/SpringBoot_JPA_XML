package org.dragon.yunpeng.sandbox.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XMLFileServiceTest {

	@Autowired
	private XMLFileService xmlFileService;

	@Test
	public void testSaveBook() {
		xmlFileService.unmarshallXML("C:\\Projects\\SpringBoot_JPA_XML\\sampleXMLs\\formList1.xml");

		xmlFileService.unmarshallXML("C:\\Projects\\SpringBoot_JPA_XML\\sampleXMLs\\formList2.xml");
	}
}
