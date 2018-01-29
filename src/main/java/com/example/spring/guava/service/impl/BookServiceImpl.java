package com.example.spring.guava.service.impl;

import com.example.spring.guava.domain.Book;
import com.example.spring.guava.service.BookService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author sunboyu
 * @date 2018/1/29
 */
@Service
public class BookServiceImpl implements BookService {

    @Cacheable("books")
    @Override
    public Book getBook() {
        Book book = new Book();
        book.setId(System.currentTimeMillis());
        book.setName("水浒传");
        book.setType(1);
        return book;
    }
}
