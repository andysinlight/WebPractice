package dao;

import bean.Rule;
import bean.UserFunction;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public class FunctionDao extends BaseDao {


    public boolean addFunction(UserFunction function){
        int update = 0;

        try {
            update = mRunner.update("insert into function(name,des,action) values(?,?,?)",
                    function.getName(), function.getDes(), function.getAction());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update>0;
    }

    public List<UserFunction> getRuleFunction(long rid) {
        try {
            return mRunner.query("select f.* from function f ,rule_function rf where f.id = rf.fid and rf.rid = ?",
                    new ResultSetHandler<List<UserFunction>>() {
                        public List<UserFunction> handle(ResultSet resultSet) throws SQLException {
                            ArrayList<UserFunction> functions = new ArrayList<UserFunction>();
                            while (resultSet.next()){
                                UserFunction function = new UserFunction(resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4));
                                functions.add(function);

                            }
                            return functions;
                        }

                    }, rid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean bindRuleAction(long rid,long aid) {
        int update = 0;
        try {
            update = mRunner.update("insert into rule_function(rid,fid) values(?,?)",
                   rid,aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update>0;
    }
}
