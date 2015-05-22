package com.prodyna.pac.security_sandbox;

public enum RegisteredPath {
    GET("/service/get");

    private String path;

    private RegisteredPath(String path) {
        this.path = path;
    }
}
