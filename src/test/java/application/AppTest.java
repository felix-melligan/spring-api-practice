package application;

import org.junit.Test;

public class AppTest {
    String[] args = {"Hello", "World"};

    @Test
    public void shouldRunTest() {
        args = new String[0];
        App app = new App();

        app.main(args);
    }
}
