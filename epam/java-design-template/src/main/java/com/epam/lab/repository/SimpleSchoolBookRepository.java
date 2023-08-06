package com.epam.lab.repository;

import com.epam.lab.entity.SchoolBook;
import org.apache.commons.lang3.ArrayUtils;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{

    private SchoolBook[] schoolBooks = {};
    //private int countSaved = 0;

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = ArrayUtils.add(schoolBooks, book);
        //countSaved++;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundBooks = {};
        for (SchoolBook book: schoolBooks) {
            if (book.getName().contentEquals(name)) {
                foundBooks = ArrayUtils.add(foundBooks, book);
            }
        }
        return foundBooks;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] foundBooks = findByName(name);
        if (foundBooks.length != 0) {
            schoolBooks = ArrayUtils.removeElements(schoolBooks, foundBooks);
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
