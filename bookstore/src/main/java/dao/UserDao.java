package dao;

import bean.Rule;
import bean.User;
import com.sun.xml.internal.bind.v2.model.runtime.RuntimeLeafInfo;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public class UserDao extends BaseDao {

    public boolean addUser(User user) {
        int update = 0;
        try {
            update = mRunner.update("insert into user(name,phone,address,email,active) value(?,?,?,?,?)",
                    user.getName(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getEmail(),
                    user.isActive());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update > 0;
    }


    public User getUser(long id) {
        try {
            return mRunner.query("select id,name,phone,address,email,active from user where id = ?",
                    new ResultSetHandler<User>() {
                        public User handle(ResultSet resultSet) throws SQLException {
                            if(resultSet.next()){
                                return new User( resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getString(5),
                                        resultSet.getBoolean(6)
                                        );

                            }
                            return null;
                        }
                    },id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addRule(long uid,long rid){
        int update = 0;
        try {
            update = mRunner.update("insert into user_rule(uid,rid) value(?,?)", uid, rid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update>0;
    }




}
