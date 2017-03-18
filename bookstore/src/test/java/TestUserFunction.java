import bean.UserFunction;
import dao.FunctionDao;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
public class TestUserFunction {
    FunctionDao mDao = new FunctionDao();

    @Test
    public void testUserFunction(){
        UserFunction function = new UserFunction("删除分类","deleteCategory","delete_category");
//        UserFunction function2 = new UserFunction("添加图书","appendBook","add_Book");
        assert mDao.addFunction(function);
//        assert mDao.addFunction(function2);
    }

    @Test
    public void binRuleAction(){
        mDao.bindRuleAction(1,3);
//        mDao.bindRuleAction(2,2);
    }



    @Test
    public void getUserFunction(){
        List<UserFunction> ruleFunction = mDao.getRuleFunction(1);
        assert ruleFunction.size()>0;
    }
}
