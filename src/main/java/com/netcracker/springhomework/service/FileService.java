package com.netcracker.springhomework.service;

import com.netcracker.springhomework.dto.User;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {

    public void saveUser(User user) throws IOException {
        FileWriter writer = new FileWriter("user.txt");

        String res = user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymic() + " "
                + user.getAge() + " " + user.getSalary() + " " + user.getMail() + " " + user.getWorkplace();
        writer.write(res);
        writer.flush();
    }
}
