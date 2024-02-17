package com.springboot.osahaneat.payload;

/*

*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    private int status=200;
    private String desc;
    private Object data;
    private boolean isSuccess = true;
}
