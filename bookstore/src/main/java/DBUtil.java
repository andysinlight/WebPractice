import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/16.
 */
public class DBUtil {
    static DataSource dataSource;

    static {
        try {
            Properties pro = new Properties();
            InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db_connect.properties");
            pro.load(input);
            dataSource = BasicDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }


}
