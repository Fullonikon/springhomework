package com.netcracker.springhomework.controller;

import com.netcracker.springhomework.dto.User;
import com.netcracker.springhomework.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {

    private FileService fileService;


    @Autowired
    public MainController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping ("/form")
    public void form(@RequestBody User user) throws IOException {
        fileService.saveUser(user);
    }


}
