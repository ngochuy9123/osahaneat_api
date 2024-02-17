package com.springboot.osahaneat.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private int id;
    private String image;
    private String title;
    private String subtitle;
    private double rating;
    private boolean freeShip;
    private Date open_date;
    private String description;
    private List<CategoryDto> categoryDtos;
}
