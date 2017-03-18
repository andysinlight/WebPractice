package dao;

import bean.Rule;
import bean.User;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public class RuleDao extends BaseDao {
    public static boolean addRule(Rule rule) {
        int update = 0;
        try {
            update = mRunner.update("insert into rule(name,des) values(?,?)", rule.getName(), rule.getDes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update>0;
    }

    public static Rule getRule(long id) {
        try {
            return mRunner.query("select id,name,des from rule where id = ?",
                    new ResultSetHandler<Rule>() {
                        public Rule handle(ResultSet resultSet) throws SQLException {
                            if(resultSet.next()){
                                return new Rule( resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3)
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

    public List<Rule> getUserRules(long id) {
        try {
            return mRunner.query("select r.* from rule r ,user_rule ur where r.id = ur.rid and ur.uid = ?",
                    new ResultSetHandler<List<Rule>>() {
                        public List<Rule> handle(ResultSet resultSet) throws SQLException {
                            ArrayList<Rule> rules = new ArrayList<Rule>();
                            while (resultSet.next()){
                                Rule rule = new Rule(resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3));
                                rules.add(rule);

                            }

                            return rules;
                        }
                    }, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
