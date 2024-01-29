package com.water.can.WaterCanal.service.FileService;
import com.water.can.WaterCanal.bean.FileRequest;
import java.io.IOException;

public interface FileService {

    public void saveFile(FileRequest fileRequest) throws IOException;

    public byte[] readImageFromFile(String folderPath, String fileName) throws IOException;
}
