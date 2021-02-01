package com.netcracker.springhomework.service;

import com.netcracker.springhomework.dto.User;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileService {

    public void saveUser(User user) throws IOException {
        FileWriter writer = new FileWriter("user.txt", true);

        String res = user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymic() + " "
                + user.getAge() + " " + user.getSalary() + " " + user.getMail() + " " + user.getWorkplace() + "\r\n";
        writer.write(res);
        writer.flush();
    }

    public boolean validateUser(User user) {
        Pattern pattern = Pattern.compile("^[А-ЯЁ][а-яё]*([-][А-ЯЁ][а-яё]*)?\\s[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*$");
        Matcher matcher = pattern.matcher(user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymic());
        return matcher.matches();
    }
}
