package am.itspace.authorbook.controller;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/author")
public class AuthorController{

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    public String authorPage(ModelMap modelMap){
        List<Author> authors = authorRepository.findAll();
        modelMap.addAttribute("authors", authors);
        return "author";
    }



    @GetMapping(value = "/add")
    public String authorAddPage(){
        return "author_add";
    }

    @PostMapping(value = "/add")
    public String authorAddPage(@ModelAttribute Author author){
        authorRepository.save(author);
        return "redirect:/author";
    }

    @GetMapping(value = "/delete")
    public String authorDeletePage(@RequestParam("id") int id){
        authorRepository.deleteById(id);
        return "redirect:/author";
    }

    @GetMapping(value = "/edit")
    public String authorEditPage(@RequestParam("id") int id, ModelMap modelMap){
        Optional<Author> author = authorRepository.findById(id);
        if(author.isPresent()){
            modelMap.addAttribute("author", author.get());
            return "author_edit";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/edit")
    public String authorEditPage(@ModelAttribute Author author){
        System.out.println(author);
        authorRepository.save(author);
        return "redirect:/author";
    }


}
