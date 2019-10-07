package application.database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DatabaseControlTest {

    @Test
    public void queryReturnsResults() {
        String query = "SELECT * FROM users";
        DatabaseControl db = new DatabaseControl();
        String results;
        results = db.query(query);

        assertNotNull(results);
    }
}
