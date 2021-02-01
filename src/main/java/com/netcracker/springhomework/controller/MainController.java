package com.netcracker.springhomework.controller;

import com.netcracker.springhomework.dto.User;
import com.netcracker.springhomework.dto.UserWithMeta;
import com.netcracker.springhomework.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MainController {

    private FileService fileService;


    @Autowired
    public MainController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping ("/form")
    public ResponseEntity<?> form(@RequestBody User user) throws IOException {
        if(!fileService.validateUser(user)){
            return ResponseEntity.badRequest().build();
        }
        fileService.saveUser(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping ("/find")
    public ResponseEntity<UserWithMeta> find(@RequestParam String lastName, @RequestParam String firstName,
                                             @RequestHeader("user-agent") String agent, @RequestHeader HttpHeaders headers) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("./user.txt"));

        for (String line: lines){
            String[] data = line.split(" ");
            User user = new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
            if (data[0].equals(lastName) && data[1].equals(firstName)){
                new ResponseEntity<>(new UserWithMeta(user, agent, headers.getDate()), HttpStatus.OK);
            }
        }


        return ResponseEntity.ok().build();
    }


}
