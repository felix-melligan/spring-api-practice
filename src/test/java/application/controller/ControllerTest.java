package application.controller;

import application.App;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    @Test
    public void homeTest() {
        App app = new App();
        Controller controller = new Controller();
        String[] args = {};
        String expected = "Spring is working!";

        app.main(args);
        assertEquals(expected, controller.home());
    }

    @Test
    public void usersTest() {
        App app = new App();
        Controller controller = new Controller();
        String[] args = {"/users"};

        app.main(args);
        System.out.println(controller.home());
    }
}
