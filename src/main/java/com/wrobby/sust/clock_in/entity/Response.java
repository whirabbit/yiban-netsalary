package com.wrobby.sust.clock_in.entity;

import lombok.Data;

@Data
public class Response {
    Integer response;
    String message;
    Object data;
}
