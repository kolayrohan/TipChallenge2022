package com.example.springbootdocker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    private Map<String, String> urlMap = new HashMap<>();

    @PostConstruct
    public void populateMap () {
        urlMap.put ("abc", "abcb12390e8038");
        urlMap.put ("xyz", "zyjafjds;");

    }


    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }

    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createShortUrl (@RequestBody  Map <String, String> map ) {
        String url =map.get("url");
        String shortenurl = map.get("shorturl");
        urlMap.put (shortenurl, url);
        return new ResponseEntity("Added", HttpStatus.OK);
    }

    @RequestMapping (value = "/getLongUrl", method = RequestMethod.GET)
    public ResponseEntity<String> getLongUrl (@RequestParam String shorturl) {
        System.out.println("in long url");
        return new ResponseEntity<>(urlMap.get(shorturl), HttpStatus.OK);
    }
}
