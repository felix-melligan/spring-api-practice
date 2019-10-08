package application.controller;

import application.App;
import application.database.DatabaseControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @RequestMapping("/")
    public String home() {
        return "Spring is working!";
    }

    @RequestMapping("/users")
    public String users(HttpServletRequest request) {
        String result = "";
        String query = "SELECT * FROM users";

        try(DatabaseControl dbc = new DatabaseControl()) {
            logger.info("Request from: "+request.getRemoteAddr()+"\tQuery: "+query);
            result = String.valueOf(dbc.query(query));
        } catch (SQLException sqle) {
            logger.error(sqle.getMessage());
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
        }
        return result;
    }
}
