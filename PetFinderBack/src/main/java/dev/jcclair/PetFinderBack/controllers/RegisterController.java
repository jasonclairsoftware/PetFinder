package dev.jcclair.PetFinderBack.controllers;

import dev.jcclair.PetFinderBack.models.UserViewModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This is the main controller for handling REST requests for registering a new User
 *
 * @author Jason Clair
 */
@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
public class RegisterController {

    @PostMapping
    public String registerUser(@ModelAttribute UserViewModel userViewModel, Model model) {

        return "";
    }

}
