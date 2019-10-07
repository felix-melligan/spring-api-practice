package application.controller;

import application.database.DatabaseControl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class Controller {

    @RequestMapping("/")
    public String home() {
        return "Spring is working!";
    }

    @RequestMapping("/users")
    public String users() {
        DatabaseControl db = new DatabaseControl();
        return String.valueOf(db.query("Select * FROM users"));
    }
}
