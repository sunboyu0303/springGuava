package com.example.spring.guava.controller;

import com.example.spring.guava.domain.Book;
import com.example.spring.guava.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunboyu
 * @date 2018/1/29
 */
@RestController
@RequestMapping("/guava")
public class GuavaController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/cache")
    public Book getCache() {
        return bookService.getBook();
    }
}