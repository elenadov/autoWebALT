package testDB;

import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDB {
    private Database database;
    private Logger logger  =Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        database = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        database.quit();
    }

    @Test
    public void testDB() throws SQLException {
        List<Map<String, String>> dataRowSeleniumTable = database.selectTableAsMap("select * from CLIENT");
        logger.info(dataRowSeleniumTable);
    }

    @Test
    public void testDBPass() throws SQLException {
        List<Map<String,String>> passwordForLogin = database.selectTableAsMap("select CAST(AES_DECRYPT(AUTH2_SECRET, 'emict') AS CHAR) as sms_code from AUTH2_CLIENT order by AUTH2_DATETIME_CREATED desc limit 1");
        logger.info(passwordForLogin);
    }

    @Test
    public void testDBPass2() throws SQLException {
        String pass = database.selectValue("select CAST(AES_DECRYPT(AUTH2_SECRET, 'emict') AS CHAR) as sms_code from AUTH2_CLIENT order by AUTH2_DATETIME_CREATED desc limit 1");
        logger.info(pass);
    }

}
