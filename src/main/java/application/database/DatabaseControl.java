package application.database;

import application.App;
import application.tools.ConfigFileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;

public class DatabaseControl implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static String configFile = "config.properties";
    private Connection connection;

    public DatabaseControl() throws IOException, SQLException {
        String database = getConfigProperty(configFile, "database");
        String user = getConfigProperty(configFile, "user");
        String password = getConfigProperty(configFile, "password");
        String url = "jdbc:mysql://localhost/"+database+"?useSSL=false&serverTimezone=UTC";

        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    private String getConfigProperty(String configFile, String property) throws IOException {
        ConfigFileReader cfr = new ConfigFileReader();
        return cfr.getConfigProperty(configFile, property);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public String query(String query) throws SQLException {
        JSONArray JSONReturn = new JSONArray();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            int count = rs.getMetaData().getColumnCount();
            JSONObject JSONObject = new JSONObject();
            for (int i = 1; i < count + 1; i++) {
                JSONObject.put(rs.getMetaData().getColumnName(i), rs.getString(i));
            }
            JSONReturn.add(JSONObject);
        }
        return JSONReturn.toJSONString();
    }
}
