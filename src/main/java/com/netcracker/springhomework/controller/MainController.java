package com.netcracker.springhomework.controller;

import com.netcracker.springhomework.dto.User;
import com.netcracker.springhomework.dto.UserWithMeta;
import com.netcracker.springhomework.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    private final FileService fileService;
    final private JavaMailSender emailSender;

    @Autowired
    public MainController(FileService fileService, JavaMailSender emailSender) {
        this.fileService = fileService;
        this.emailSender = emailSender;
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
                return new ResponseEntity<>(new UserWithMeta(user, agent, headers.getDate()), HttpStatus.OK);
            }
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {

        try {
            String result = new BufferedReader(new InputStreamReader(file.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            FileWriter writer = new FileWriter("user.txt", true);
            writer.write(result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam String to, @RequestParam String subject, @RequestParam String text){

        this.sendSimpleMessage(to, subject, text);
        return ResponseEntity.ok().build();
    }

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}
