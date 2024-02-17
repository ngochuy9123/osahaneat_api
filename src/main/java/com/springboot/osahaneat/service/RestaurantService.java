package com.springboot.osahaneat.service;

import com.springboot.osahaneat.dto.RestaurantDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface RestaurantService {
    boolean insertRestaurant(MultipartFile file,
                              String title,
                              String subtitle,
                              String description,
                              boolean freeship,
                              String address,
                              String open_date) throws ParseException;

    List<RestaurantDto> getHomePageRestaurant();

    RestaurantDto getDetailRestaurant(int restaurant_id);
}
