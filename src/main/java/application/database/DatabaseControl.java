package application.database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class DatabaseControl {
    public static String query(String query) {
        JSONArray JSONReturn = new JSONArray();

        try {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost/SPRING_PRACTICE?useSSL=false&serverTimezone=UTC","root","");

            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                int count = rs.getMetaData().getColumnCount();
                JSONObject JSONObject = new JSONObject();
                for(int i = 1; i < count+1; i++) {
                    JSONObject.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
                JSONReturn.add(JSONObject);
            }
            con.close();
            return JSONReturn.toJSONString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return JSONReturn.toJSONString();
    }
}
