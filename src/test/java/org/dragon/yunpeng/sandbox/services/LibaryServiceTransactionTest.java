package org.dragon.yunpeng.sandbox.services;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibaryServiceTransactionTest {

	@Autowired
	private LibraryService libraryService;

	private String workingDirectory;

	@BeforeEach
	public void setUp() {
		workingDirectory = System.getProperty("user.dir");
		System.out.println("workingDirectory=" + workingDirectory);
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	public void testSaveEntitiesFromXmlsUsingOrphanData() {
		String fileDirectory = workingDirectory + File.separator + "sampleXMLs" + File.separator + "missing_data"
				+ File.separator;
		try {
			libraryService.disableConstraints();

			libraryService.saveEntitiesFromXmls(fileDirectory);
		} finally {
			libraryService.enableConstraints();
		}
	}
	
	@Test
	public void testSaveEntitiesFromXmlsUsingGoodData() {
		String fileDirectory = workingDirectory + File.separator + "sampleXMLs" + File.separator + "good"
				+ File.separator;
		try {
			libraryService.disableConstraints();

			libraryService.saveEntitiesFromXmls(fileDirectory);
		} finally {
			libraryService.enableConstraints();
		}
	}
	
	@Test
	public void testSaveEntitiesFromLargeXMLs() {

		String fileDirectory = workingDirectory + File.separator + "sampleXMLs" + File.separator + "large"
				+ File.separator;

		try {
			libraryService.disableConstraints();

			libraryService.saveEntitiesFromXmls(fileDirectory);
		} finally {
			libraryService.enableConstraints();
		}
	}
}
