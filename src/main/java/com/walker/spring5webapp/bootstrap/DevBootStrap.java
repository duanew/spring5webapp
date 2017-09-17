package com.walker.spring5webapp.bootstrap;

import com.walker.spring5webapp.model.Author;
import com.walker.spring5webapp.model.Book;
import com.walker.spring5webapp.model.Publisher;
import com.walker.spring5webapp.repositories.AuthorRepository;
import com.walker.spring5webapp.repositories.BookRepository;
import com.walker.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Publisher publisher = new Publisher("Harper Collins", "Address");
        publisherRepository.save(publisher);

        Publisher publisher2 = new Publisher("Worx", "Address");
        publisherRepository.save(publisher2);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driver Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher2);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
