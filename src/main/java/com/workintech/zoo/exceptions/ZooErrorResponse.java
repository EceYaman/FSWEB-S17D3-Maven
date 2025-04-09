package com.workintech.zoo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZooErrorResponse {
    private String message;
    private int status;
    private long timestamp;

    public ZooErrorResponse(String message, int status, long timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
