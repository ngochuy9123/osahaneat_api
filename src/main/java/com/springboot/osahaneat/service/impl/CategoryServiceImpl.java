package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.dto.CategoryDto;
import com.springboot.osahaneat.dto.MenuDto;
import com.springboot.osahaneat.entity.Category;
import com.springboot.osahaneat.entity.Food;
import com.springboot.osahaneat.repository.CategoryRepository;
import com.springboot.osahaneat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategoryHomePage() {

        PageRequest pageRequest = PageRequest.of(0,2);
        Page<Category> categories = categoryRepository.findAll(pageRequest);
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category data:categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(data.getName());

            List<MenuDto> menuDtos = new ArrayList<>();
            for (Food food :data.getFoodSet()) {
                MenuDto menuDto = new MenuDto();
                menuDto.setTitle(food.getTitle());
                menuDto.setImage(food.getImage());
                menuDto.setFree_shipping(food.isFree_shipping());

                menuDtos.add(menuDto);
            }
            categoryDto.setMenuDtos(menuDtos);
            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }
}
