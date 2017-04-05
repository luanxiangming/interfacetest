package com.vipabc.interfacetest.utils;

import javax.ws.rs.core.Response;

public class RestClientException extends RuntimeException {

    private static final long serialVersionUID = -4899178204551749396L;
    private String code;

    public RestClientException(String message, String code) {
        super(message);
        this.code = code;
    }

    public RestClientException(String url, Exception e, Response.Status status) {
        super(url + "\n" + e.getMessage(), e);
        this.code = status.name();
    }

    public RestClientException(String message, Response.Status status) {
        this(message, status.name());
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
}
}
