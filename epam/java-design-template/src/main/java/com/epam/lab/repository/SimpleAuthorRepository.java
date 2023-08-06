package com.epam.lab.repository;

import com.epam.lab.entity.Author;
import org.apache.commons.lang3.ArrayUtils;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};
    private int countSaved = 0;

    @Override
    public boolean save(Author author) {
        if (! author.getName().isEmpty() && ! author.getLastName().isEmpty()) {
            if (findByFullName(author.getName(), author.getLastName()) == null) {
                authors = ArrayUtils.add(authors, author);
                countSaved++;
                return true;
            }
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().contentEquals(name) && author.getLastName().contentEquals(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            authors = ArrayUtils.removeElement(authors, author);
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return countSaved;
    }
}
