package com.epam.lab.service;

import com.epam.lab.entity.Author;
import com.epam.lab.entity.SchoolBook;
import com.epam.lab.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) != null) {
            return schoolBookBookRepository.save(book);
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] foundBooks;
        foundBooks = schoolBookBookRepository.findByName(name);
        if (foundBooks.length != 0) {
            return authorService.findByFullName(foundBooks[0].getAuthorName(), foundBooks[0].getAuthorLastName());
        }
        return null;
    }
}
