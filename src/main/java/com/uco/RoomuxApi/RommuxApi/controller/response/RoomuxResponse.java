package com.uco.RoomuxApi.RommuxApi.controller.response;

import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilDefaultObject;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class RoomuxResponse <T>{
    private T data;
    private String message;





}
