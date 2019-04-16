package com.shabranGuestBook.GuestBook;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class GuestBookController {


    private static final String IS_AJAX_HEADER = "X-Requested-With=XMLHttpRequest";

    private final GuestBookRepository guestbook;


    public GuestBookController(GuestBookRepository guestbook) {

        Assert.notNull(guestbook, "Guestbook must not be null!");
        this.guestbook = guestbook;
    }


    @GetMapping(path = "/") // get
    String index() {
        return "redirect:/guestbook";
    }


    @GetMapping(path = "/guestbook")
    String guestBook(Model model, @ModelAttribute(binding = false) GuestBookForm form) {

        model.addAttribute("entries", guestbook.findAll());
        model.addAttribute("form", form);

        return "guestbook";
    }

    @PostMapping(path = "/guestbook")
    String addEntry(@Valid @ModelAttribute("form") GuestBookForm form, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return guestBook(model, form);
        }

        guestbook.save(form.toNewEntry());

        return "redirect:/guestbook";
    }
    @PostMapping(path = "/guestbook", headers = IS_AJAX_HEADER)
    String addEntry(@Valid GuestBookForm form, Model model) {

        model.addAttribute("entry", guestbook.save(form.toNewEntry()));
        model.addAttribute("index", guestbook.count());

        return "guestbook :: entry";
    }

    @DeleteMapping(path = "/guestbook/{id}")
    String removeEntry(@PathVariable Long id) {

        guestbook.deleteById(id);

        return "redirect:/guestbook";
    }

    @DeleteMapping(path = "/guestbook/{id}", headers = IS_AJAX_HEADER)
    HttpEntity<?> removeEntryJS(@PathVariable Long id) {

        return guestbook.findById(id).map(e -> {

            guestbook.deleteById(e.getId());
            return ResponseEntity.ok().build();

        }).orElse(ResponseEntity.notFound().build());
    }
}
