package me.decentos.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookPagesController {

    @GetMapping("/")
    public String listPage(Model model) {
        model.addAttribute("keywords", "list of books");
        return "list";
    }

//    @GetMapping("/edit")
//    public String editPage(@RequestParam("id") long id, Model model) {
//        model.addAttribute("book", "book by id");
//        return "edit";
//    }
//
//    @RequestMapping("/create")
//    public String createPage(Model model) {
//        model.addAttribute("book", new Book("", new Author("", ""), new Genre("")));
//        return "create";
//    }
}
