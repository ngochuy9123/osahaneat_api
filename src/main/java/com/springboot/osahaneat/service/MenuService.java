package com.springboot.osahaneat.service;

import org.springframework.web.multipart.MultipartFile;

public interface MenuService {
    boolean createMenu(MultipartFile file,String title,boolean freeship,
                       String timeship,double price,int cate_id);

}
