package com.springboot.osahaneat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MenuDto {
    private String title;
    private String image;
    private boolean free_shipping;
    private String description;
    private double price;
}
