package am.itspace.authorbook.controller;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.entity.Book;
import am.itspace.authorbook.repository.AuthorRepository;
import am.itspace.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public String bookPage(ModelMap modelMap) {
        List<Book> books = bookRepository.findAll();
        modelMap.addAttribute("books", books);
        return "book";
    }

    @GetMapping(value = "/add")
    public String addBook(ModelMap modelMap) {
        List<Author> authors = authorRepository.findAll();
        modelMap.addAttribute("authors1", authors);
        return "add_book";
    }


    @PostMapping(value = "/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping(value = "/delete")
    public String authorDeletePage(@RequestParam("id") int id){
        bookRepository.deleteById(id);
        return "redirect:/book";
    }

    @GetMapping(value = "/edit")
    public String editBookPage(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            List<Author> authors = authorRepository.findAll();
            modelMap.addAttribute("book", book.get());
            modelMap.addAttribute("authors", authors);
            return "edit_book";
        }
        return "redirect:/book";
    }

    @PostMapping(value = "/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/book";
    }
}