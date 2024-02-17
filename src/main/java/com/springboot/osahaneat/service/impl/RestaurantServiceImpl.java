package com.springboot.osahaneat.service.impl;

import com.springboot.osahaneat.dto.CategoryDto;
import com.springboot.osahaneat.dto.MenuDto;
import com.springboot.osahaneat.dto.RestaurantDto;
import com.springboot.osahaneat.entity.*;
import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.repository.RestaurantRepository;
import com.springboot.osahaneat.service.FileService;
import com.springboot.osahaneat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    FileService fileService;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle,
                                    String description, boolean freeship,
                                    String address, String open_date){
        boolean isInsertSuccess = false;
        try{
            boolean isSaveFileSuccess = fileService.saveFile(file);
            if (isSaveFileSuccess){
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setAddress(address);
                restaurant.setFree_shipping(freeship);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                Date openDate = simpleDateFormat.parse(open_date);
                restaurant.setOpen_date(openDate);
                restaurantRepository.save(restaurant);
                isInsertSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error with insert restaurant: "+e.getMessage());
        }

        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDto> getHomePageRestaurant() {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);

        for(Restaurant data : listData){
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setId(data.getId());
            restaurantDto.setTitle(data.getTitle());
            restaurantDto.setImage(data.getImage());
            restaurantDto.setSubtitle(data.getSubtitle());

            restaurantDto.setFreeShip(data.isFree_shipping());
            restaurantDto.setRating(calculateRating(data.getRatingRestaurantSet()));
            System.out.println(data);

            restaurantDtos.add(restaurantDto);
        }

        return restaurantDtos;
    }

    @Override
    public RestaurantDto getDetailRestaurant(int restaurant_id) {
        Optional<Restaurant> data = restaurantRepository.findById(restaurant_id);
        RestaurantDto restaurantDto = new RestaurantDto();
        if (data.isPresent()){
            Restaurant restaurant = data.get();

            restaurantDto.setTitle(restaurant.getTitle());
            restaurantDto.setImage(restaurant.getImage());
            restaurantDto.setFreeShip(restaurant.isFree_shipping());
            restaurantDto.setSubtitle(restaurant.getSubtitle());
            restaurantDto.setOpen_date(restaurant.getOpen_date());
            restaurantDto.setRating(calculateRating(restaurant.getRatingRestaurantSet()));
            restaurantDto.setDescription(restaurant.getDescription());
            List<CategoryDto> categoryDtos = new ArrayList<>();
            for (MenuRestaurant menu:restaurant.getMenuRestaurantSet()) {
                CategoryDto categoryDto = getCategoryDto(menu);
                categoryDtos.add(categoryDto);
            }
            restaurantDto.setCategoryDtos(categoryDtos);

        }

        return restaurantDto;
    }

    private static CategoryDto getCategoryDto(MenuRestaurant menu) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(menu.getCategory().getName());
        List<MenuDto> menuDtos = new ArrayList<>();
        for (Food food: menu.getCategory().getFoodSet()) {
            MenuDto menuDto = new MenuDto();
            menuDto.setImage(food.getImage());
            menuDto.setTitle(food.getTitle());
            menuDto.setFree_shipping(food.isFree_shipping());
            menuDto.setPrice(food.getPrice());
            menuDto.setDescription(food.getDescription());
            menuDtos.add(menuDto);
        }
        categoryDto.setMenuDtos(menuDtos);
        return categoryDto;
    }

    public double calculateRating(Set<RatingRestaurant> ratingRestaurantSet){
        double totalPoint=0;
        for (RatingRestaurant data:ratingRestaurantSet) {
            totalPoint+=data.getRate_point();
        }
        return totalPoint / ratingRestaurantSet.size();
    }
}
