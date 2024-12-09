package am.itspace.authorbook.controller;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.entity.Book;
import am.itspace.authorbook.repository.AuthorRepository;
import am.itspace.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(value = "/book")
    public String bookPage(ModelMap modelMap) {
        List<Book> books = bookRepository.findAll();
        modelMap.addAttribute("books", books);
        return "book";
    }

    @GetMapping(value = "/book/add")
    public String addBook(ModelMap modelMap) {
        List<Author> authors = authorRepository.findAll();
        modelMap.addAttribute("authors1", authors);
        return "add_book";
    }


    @PostMapping(value = "/book/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/book";
    }

}
