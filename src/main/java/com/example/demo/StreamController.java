package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
public class StreamController {

    @Autowired
    BooksRepository booksRepository;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    private String extracted() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/data.txt"));

        lines.stream().parallel().forEach(accNumber ->
        {
            System.out.println("Thread : " + Thread.currentThread().getName() + " , value " + accNumber);
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:9090/api/book/";
            ResponseEntity<BookEntity> call = restTemplate.getForEntity(url + accNumber, BookEntity.class);
            booksRepository.save(call.getBody());

        });
        return "200";
    }
}
