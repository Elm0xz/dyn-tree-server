package com.pretz.dyntreeserver.controller;

public class JsonApiParser {

    public String parse(String jsonString) {
        return "{\"data\":" +
                jsonString +
                "}";
    }
}
