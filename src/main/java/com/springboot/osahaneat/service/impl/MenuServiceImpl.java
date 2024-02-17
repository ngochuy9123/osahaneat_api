package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.entity.Category;
import com.springboot.osahaneat.entity.Food;
import com.springboot.osahaneat.repository.FoodRepository;
import com.springboot.osahaneat.service.FileService;
import com.springboot.osahaneat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FileService fileService;

    @Override
    public boolean createMenu(MultipartFile file, String title, boolean freeship,
                              String timeship, double price, int cate_id) {
        boolean isInsertSuccess = false;
        boolean isSaveFileSuccess = fileService.saveFile(file);
        if (isSaveFileSuccess){
            Food food = new Food();
            food.setTitle(title);
            food.setFree_shipping(freeship);
            food.setImage(file.getOriginalFilename());
            food.setPrice(price);
            food.setTime_ship(timeship);

            Category category = new Category();
            category.setId(cate_id);
            food.setCategory(category);

            foodRepository.save(food);
            isInsertSuccess = true;
        }
        return isInsertSuccess;
    }
}
