package application.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import static org.junit.Assert.*;

public class DatabaseControlTest {
    @InjectMocks private DatabaseControl databaseControl;
    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private ResultSet mockResultSet;
    @Mock private ResultSetMetaData mockResultSetMetaData;

    @Test
    public void constructorCreatesConnection() {
        try (DatabaseControl dbc = new DatabaseControl()) {
            assertNotNull(dbc.getConnection());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void canCloseConnection() {
        try (DatabaseControl dbc = new DatabaseControl()) {
            assertNotNull(dbc);
            dbc.close();
            assertTrue(dbc.getConnection().isClosed());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testMockDBConnection() throws Exception {
        String query = "SELECT * FROM table";
        int columnCount = 1;
        String columnName = "column_name";
        String columnEntry = "column_entry";
        String expectedResult = "[{\"column_name\":\"column_entry\"}]";

        MockitoAnnotations.initMocks(this);

        Mockito.when(mockConnection.prepareStatement(query)).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(mockResultSet.getMetaData()).thenReturn(mockResultSetMetaData);
        Mockito.when(mockResultSetMetaData.getColumnCount()).thenReturn(columnCount);
        Mockito.when(mockResultSetMetaData.getColumnName(columnCount)).thenReturn(columnName);
        Mockito.when(mockResultSet.getString(columnCount)).thenReturn(columnEntry);
        String result = databaseControl.query(query);
        assertEquals(expectedResult, result);
        Mockito.verify(mockStatement.executeQuery(), Mockito.times(1));
    }
}
