package com.pretz.dyntreeserver.service;

public class JsonApiParser {

    public String parse(String jsonString) {
        return "{\"data\":" +
                jsonString +
                "}";
    }
}
