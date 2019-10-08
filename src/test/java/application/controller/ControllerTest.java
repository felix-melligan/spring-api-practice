package application.controller;

import application.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    private String result;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void homeTest() {
        result = "Spring is working!";
        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class).contains(result));
    }

    @Test
    public void usersTest() {
        assertNotNull(this.restTemplate.getForObject("http://localhost:" + port + "/users",
                String.class));
    }
}
