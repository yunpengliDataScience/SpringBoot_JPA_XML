package org.dragon.yunpeng.sandbox.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

	byte[] saveFile(MultipartFile file, String fileName) throws IOException;

	String getFileUploadDirectory();

}
