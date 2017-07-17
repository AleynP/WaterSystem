package com.souhou.watersystem.utils;

/**
 * Created by jayli on 2017/5/3 0003.
 */

public class HttpException extends Exception {

    public HttpException() {
        super();
    }

    public HttpException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpException(String detailMessage) {
        super(detailMessage);
    }

    public HttpException(Throwable throwable) {
        super(throwable);
    }
}
