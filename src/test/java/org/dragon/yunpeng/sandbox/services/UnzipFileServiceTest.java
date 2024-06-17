package org.dragon.yunpeng.sandbox.services;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UnzipFileServiceTest {

	@Autowired
	private UnzipFileService unzipFileService;

	@Test
	public void testUnzip() {

		String workingDirectory = System.getProperty("user.dir");
		System.out.println("workingDirectory=" + workingDirectory);

		String tempFileDirectory = workingDirectory + File.separator + "tempFileFolder" + File.separator;

		String zipFilePath = tempFileDirectory + File.separator + "sampleXMLs.zip";
		String destDirectory = tempFileDirectory + File.separator;

		try {
			unzipFileService.unzip(zipFilePath, destDirectory);
			System.out.println("Unzip completed successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
