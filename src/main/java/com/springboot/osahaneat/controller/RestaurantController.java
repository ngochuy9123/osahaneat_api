package com.springboot.osahaneat.controller;

import com.springboot.osahaneat.payload.ResponseData;
import com.springboot.osahaneat.service.FileService;
import com.springboot.osahaneat.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private FileService fileService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam String subtitle,
                                              @RequestParam String description,
                                              @RequestParam boolean freeship,
                                              @RequestParam String address,
                                              @RequestParam String open_date){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = false;
        try {
            isSuccess = restaurantService.insertRestaurant(
                    file,title,subtitle,description,freeship,address,open_date);

        } catch (ParseException e) {
            System.out.println("Error when parse open_date: "+e.getMessage());
        }
        responseData.setSuccess(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getRestaurant(){
        ResponseData responseData = new ResponseData();

        responseData.setData(restaurantService.getHomePageRestaurant());

        return ResponseEntity.ok(responseData);
    }


    @GetMapping("/file/{filename:.*}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename){
        Resource resource = fileService.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION
                        , "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int restaurant_id){
        ResponseData responseData = new ResponseData();

        responseData.setData(restaurantService.getDetailRestaurant(restaurant_id));

        return ResponseEntity.ok(responseData);
    }
}
