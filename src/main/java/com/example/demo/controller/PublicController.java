package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/")
    public String index() {
        return "index";  // maps to /templates/index.html
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    @GetMapping("/blog/{slug}")
    public String blogPost(@PathVariable String slug) {
        // slug can be used later to fetch content
        return "blog-post"; // must have blog-post.html
    }

    @GetMapping("/notes/{slug}")
    public String publicNote(@PathVariable String slug) {
        return "public-note"; // must have public-note.html
    }
}
