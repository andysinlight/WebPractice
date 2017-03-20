package dao;

import org.apache.commons.dbutils.QueryRunner;
import utils.DBUtil;

/**
 * Created by Administrator on 2017/3/18.
 */
class BaseDao {
    static QueryRunner mRunner = new QueryRunner(DBUtil.getDataSource());
}
