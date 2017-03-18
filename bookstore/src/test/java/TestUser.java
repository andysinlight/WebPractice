import bean.Book;
import bean.Rule;
import bean.User;
import bean.UserFunction;
import dao.FunctionDao;
import dao.RuleDao;
import dao.UserDao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public class TestUser {
    UserDao userDao = new UserDao();

    @Test
    public void addUser() {
//        Book book = mBookDao.getBookByID("1");
//        assert book != null;
        User user = new User("andy", "13872766110", "shenzhen", "www.andyuan@gmail.com", false);
        assert userDao.addUser(user);
    }

    @Test
    public void getUser() {
        assert userDao.getUser(1l) != null;
    }


    @Test
    public void addRule() {
        Rule rule = new Rule("管理员","系统内部人员");
//        Rule rule = new Rule("顾客", "外部人员");
        assert RuleDao.addRule(rule);
    }

    @Test
    public void getRule() {
        assert RuleDao.getRule(1l) != null;
    }

    @Test
    public void addUserRule() {
        assert userDao.addRule(1, 1);
        assert userDao.addRule(1, 2);
    }

    @Test
    public void getUserRule() {
        RuleDao ruleDao = new RuleDao();
        FunctionDao functionDao = new FunctionDao();
        ArrayList<UserFunction> functions = new ArrayList<UserFunction>();
        List<Rule> userRules = ruleDao.getUserRules(1);
        for(Rule rule: userRules){
            List<UserFunction> ruleFunction = functionDao.getRuleFunction(rule.getId());
            functions.addAll(ruleFunction);
        }
        assert functions.size() > 0;
    }
}
