package com.epam.lab;

import com.epam.lab.entity.Author;
import com.epam.lab.entity.Book;
import com.epam.lab.entity.SchoolBook;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.BookRepository;
import com.epam.lab.repository.SimpleAuthorRepository;
import com.epam.lab.repository.SimpleSchoolBookRepository;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.BookService;
import com.epam.lab.service.SimpleAuthorService;
import com.epam.lab.service.SimpleSchoolBookService;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

//        AuthorRepository authorRepository = new SimpleAuthorRepository();
//        AuthorService authorService = new SimpleAuthorService(authorRepository);
////
//        Author a = new Author("Aleksandr", "Pushkin", LocalDate.of(2020, Month.JANUARY, 8), "russia");
////        Author b = new Author("Pushkin", "Aleksandr", LocalDate.of(2020, Month.JANUARY, 8), "russia");
////        Author c = new Author("", "", LocalDate.of(2020, Month.JANUARY, 8), "russia");
//        System.out.println(authorService.count());
//        System.out.println(authorService.save(a));
//        System.out.println(authorService.count());
//        System.out.println(authorService.save(b));
//        System.out.println(authorService.save(c));
//        System.out.println(authorService.remove(a));
//        System.out.println(authorService.remove(b));
//        System.out.println(authorService.count());

//        BookRepository bookRepository = new SimpleSchoolBookRepository();
//        BookService bookService = new SimpleSchoolBookService(bookRepository,authorService);
//
//        Book book = new SchoolBook(10, "Rusalochka", "Aleksandr", "Pushkin", LocalDate.of(2020, Month.JANUARY, 8));
//
//        System.out.println(bookService.save(book));
//        System.out.println(bookService.save(book));
//        System.out.println(bookService.save(book));
//        System.out.println(bookService.count());


//        String a = "aaaa";
//        String b = "aaaa";
//
//        System.out.println(a.contentEquals(b));

    }

}
