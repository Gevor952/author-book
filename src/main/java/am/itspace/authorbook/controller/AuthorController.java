package am.itspace.authorbook.controller;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController{

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(value = "/author")
    public String authorPage(ModelMap modelMap){
        List<Author> authors = authorRepository.findAll();
        modelMap.addAttribute("authors", authors);
        return "author";
    }



    @GetMapping(value = "/author/add")
    public String authorAddPage(){
        return "author_add";
    }

    @PostMapping(value = "/author/add")
    public String authorAddPage(@ModelAttribute Author author){
        authorRepository.save(author);
        return "redirect:/author";
    }

}
