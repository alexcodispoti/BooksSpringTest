package it.almaviva.bootstrap;

import it.almaviva.model.Author;
import it.almaviva.model.Book;
import it.almaviva.model.Publisher;
import it.almaviva.repository.AuthorRepository;
import it.almaviva.repository.BookRepository;
import it.almaviva.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private  final AuthorRepository authorRepository;

    private  final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Creo le instanze delle classi persistenti
        Publisher publisher1=new Publisher();
        publisher1.setName("Mondadori");
        publisher1.setCity("Rome");
        publisher1.setAddressLine1("Via Magna Grecia n.45");
        publisherRepository.save(publisher1);
        System.out.println("Publisher Count: "+publisherRepository.count());


        Author eric=new Author("Eric","Evans");
        Book book1=new Book("Domain Driven Design","123123");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        book1.setPublisher(publisher1);

        publisher1.getBooks().add(book1);


        Author rod=new Author("Rod","Johnson");
        Book book2=new Book("J2EE Development","393450");

        book2.setPublisher(publisher1);
        Book book3=new Book("The language of OOP","234234");
        book3.setPublisher(publisher1);





        eric.getBooks().add(book2);
        eric.getBooks().add(book3);

        book1.getAuthors().add(rod);

        authorRepository.save(eric);
        authorRepository.save(rod);
        bookRepository.save(book1);
        bookRepository.save(book3);
        bookRepository.save(book2);



        System.out.println("Started in bootstrap : ");
        System.out.println("Number of Books : " + bookRepository.count());
        System.out.println("Number of Book with Publisher1 : " + publisher1.getBooks().size());




    }
}
