package com.sample.play.core;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
public class CommonResponse <T> {
    private long code;
    private String errCode;
    private String[] messages;
    private List errItems;
    private T result;

    protected CommonResponse(long code, String message, T data){
        this.code = code;
        if(!StringUtils.isEmpty(message)){
            messages = new String[]{message};
        }
        this.result = data;
    }

    protected CommonResponse(String errcode, long code, String message, T data){
        this.code = code;
        this.errCode = errcode;
        if(StringUtils.isEmpty(message)){
            messages = new String[]{message};
        }
        this.result = data;
    }

    protected CommonResponse(long code, String messages[], T data){
        this.code = code;
        this.messages = messages;
        this.result = data;
    }


    public static <T> CommonResponse<T> success(T data){
        return new CommonResponse<T>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> CommonResponse<T> success(T data, String message){
        return new CommonResponse<T>(HttpStatus.OK.value(), message, data);
    }

    public static <T> CommonResponse<T> success(String message){
        return new CommonResponse<T>(HttpStatus.OK.value(), message, null);
    }

    public static <T> CommonResponse<T> failed(HttpStatus errCode){
        return new CommonResponse<T>(errCode.value(), errCode.getReasonPhrase(), null);
    }

    public static <T> CommonResponse<T> failed(HttpStatus errCode, String message){
        return new CommonResponse<T>(errCode.value(), message, null);
    }

}
