package com.dano.kjm.domain.item.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Slf4j
@Service
public class FileService {

    public String uploadImg(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String saveFileName = uuid.toString() + extension;

        String property = System.getProperty("user.home");
        String fileUploadFullUrl = property + uploadPath + "/" + saveFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();
        return saveFileName;
    }

    public void deleteImg(String filePath) throws Exception {
        File file = new File(filePath);

        if(file.exists()) {
            file.delete();
            log.info("파일 삭제");
        } else {
            log.info("존재하지 않는 파일입니다.");
        }
    }
}
