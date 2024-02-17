package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    @Value("${fileupload.path}")
    private String uploadPath;

    private Path root;


    private void init(){
        try{
            root = Paths.get(uploadPath);
            if (Files.notExists(root)){
                Files.createDirectories(root);
            }
        }catch (Exception e){
            System.out.println("Error with directory"+e.getMessage());
        }
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        try {
            init();
            if (!file.isEmpty()){
                Files.copy(file.getInputStream(),root.resolve(
                        Objects.requireNonNull(file.getOriginalFilename()))
                        ,StandardCopyOption.REPLACE_EXISTING);
                return true;
            }
            else{
                System.out.println("Failed to store empty file");
            }
        }catch (Exception e){
            System.out.println("Error with save file"+e.getMessage());
        }
        return false;
    }

    @Override
    public Resource loadFile(String filename) {
        try{
            init();
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
        }catch (Exception e){
            System.out.println("Error load file"+e.getMessage());
        }
        return null;
    }
}
