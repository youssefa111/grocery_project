package com.grocery_project.auth.user.controller;

public interface UserRequest {

     String registerRequest = "{\n" +
            "    \"firstName\":\"rafeek\",\n" +
            "    \"lastName\":\"eldarb\",\n" +
            "    \"dateOfBirth\":\"1999-03-01\",\n" +
            "    \"joinDate\":\"2023-03-23\",\n" +
            "    \"address\":\"Nasr City\",\n" +
            "    \"phone1\":\"01123167225\",\n" +
            "    \"username\":\"rafeek_eldarb2\",\n" +
            "    \"email\":\"r2.n@gmail.com\",\n" +
            "    \"password\":\"123456r\",\n" +
            "    \"role\":{\n" +
            "        \"id\":3,\n" +
            "        \"roleType\":\"STUDENT\"\n" +
            "    }\n" +
            "    ,\n" +
            "    \"student\":{\n" +
            "        \"parentName\":\"nehad\",\n" +
            "        \"parentPhone\":\"07775000\"\n" +
            "    }\n" +
            "\n" +
            "}";
}
