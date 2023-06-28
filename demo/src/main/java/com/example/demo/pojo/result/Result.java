package com.example.demo.pojo.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Result<T> {

    @JsonSerialize(using = StatusSerializer.class)
    private HttpStatus status;
    private String message;

    private T data;

    public Result(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(boolean flag, String message) {

        this.status = flag? HttpStatus.OK: HttpStatus.BAD_REQUEST;

        this.message = message;
    }

    public Result(boolean flag) {

        this.status = flag? HttpStatus.OK: HttpStatus.BAD_REQUEST;

        this.message = null;
    }

    public Result(){

    }

    public Result(boolean flag, T object){
        this(flag);
        this.data = object;

    }

    public Result(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(HttpStatus status) {
        this.status = status;
    }
}
