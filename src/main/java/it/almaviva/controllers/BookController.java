package it.almaviva.controllers;

import it.almaviva.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private  final BookRepository bookRepository;

    //Costruttore importante perche bookRepository gli arriva NULL
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //Mappare la richiesta ,per visualizzare una lista di libri, su uno specifico URL
    @RequestMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("books",bookRepository.findAll());
        return "books/list";
    }
}
