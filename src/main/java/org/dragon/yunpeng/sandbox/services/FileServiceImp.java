package org.dragon.yunpeng.sandbox.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service(value = "fileService")
public class FileServiceImp implements IFileService {

	@Override
	public byte[] saveFile(MultipartFile file, String fileName) throws IOException {
		String UPLOAD_FOLDER = getFileUploadDirectory();

		// Create Path object
		Path path = Paths.get(UPLOAD_FOLDER + fileName);

		byte[] fileBytes = file.getBytes();
		// Save the valid XML
		Files.write(path, fileBytes);
		return fileBytes;
	}

	@Override
	public String getFileUploadDirectory() {
		String workingDirectory = System.getProperty("user.dir");
		String UPLOAD_FOLDER = workingDirectory + File.separator + "uploaded" + File.separator;

		File directory = new File(UPLOAD_FOLDER);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		return UPLOAD_FOLDER;
	}

	
}
