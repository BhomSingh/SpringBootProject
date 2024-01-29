package com.water.can.WaterCanal.service.FileService;

import com.water.can.WaterCanal.Common.CommonUtil;
import com.water.can.WaterCanal.bean.FileRequest;
import com.water.can.WaterCanal.dao.FileRepository;
import com.water.can.WaterCanal.model.FileEntity;
import com.water.can.WaterCanal.service.FileService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    private static final String FOLDER_PATH = "/Users/haseebarittek005/Desktop/Save_files_database";

    @Override
    public void saveFile(FileRequest fileRequest) throws IOException {

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(fileRequest.getFilename());
        fileEntity.setFiletype(fileRequest.getFiletype());

        FileEntity fileEntityUpdateName = fileRepository.save(fileEntity);

        fileEntityUpdateName.setFilename(fileEntityUpdateName.getFilename() + "_" +
                fileEntityUpdateName.getId() + "." +
                fileEntityUpdateName.getFiletype());

        FileEntity fileRecord = fileRepository.save(fileEntityUpdateName);

        // Specify the full path for saving the file
        String filePath = FOLDER_PATH + "/" + fileRecord.getFilename();

        // Write the decoded bytes to the file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            FileCopyUtils.copy(CommonUtil.convertBase64ToBytes(fileRequest.getBase64()), fos);
        }
    }

    @Override
    public byte[] readImageFromFile(String folderPath, String fileName) throws IOException {
        Path path = Paths.get(FOLDER_PATH, fileName);
        return Files.readAllBytes(path);
    }

}