package com.ww.common;

import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ww on 17/3/11.
 */
public class BaseDaoImpl implements BaseDao {
    public SqlSession sqlSession;

    public BaseDaoImpl() {
    }

    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

    @Resource(name = "sqlSessionFactory")
    public void setSqlSession1(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Long insert(String sqlKey, Object object) {
        return Long.valueOf((long) this.getSqlSession().insert(sqlKey, object));
    }

    public Long delete(String sqlKey, Object object) {
        return Long.valueOf((long) this.getSqlSession().delete(sqlKey, object));
    }

    public Long update(String key, Object object) {
        return Long.valueOf((long) this.getSqlSession().update(key, object));
    }

    public <T> T selectOne(String sqlKey, Object params) {
        T selectOne = null;
        List<T> list = this.selectList(sqlKey, params);
        if (list != null && list.size() > 0) {
            selectOne = list.get(0);
        }
        return selectOne;
    }

    public <T> List<T> selectList(String sqlKey, Object params) {
        return this.getSqlSession().selectList(sqlKey, params);
    }

    public <T> List<T> queryForListPage(String sqlKey, Object params, PageEntity page) {
        HashMap map = new HashMap();
        map.put("e", params);
        PageParam pageOL = new PageParam();
        pageOL.setOffsetPara((page.getCurrentPage() - 1) * page.getPageSize());
        pageOL.setLimitPara(page.getPageSize());
        map.put("page", pageOL);
        Integer objectscount = (Integer) this.selectOne(sqlKey + "Count", map);
        if (objectscount != null && objectscount.intValue() != 0) {
            page.setTotalResultSize(objectscount.intValue());
            int totalPageSize1 = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize1);
            return this.selectList(sqlKey, map);
        } else {
            page.setTotalResultSize(0);
            byte totalPageSize = 0;
            page.setTotalPageSize(totalPageSize);
            return null;
        }
    }

    public <T> List<T> queryForListPageCount(String sqlKey, Object params, PageEntity page) {
        HashMap map = new HashMap();
        map.put("e", params);
        map.put("countfalg", "true");
        map.put("pageEntity", page);
        return this.selectList(sqlKey, map);
    }

//    public void timer() {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
//                try {
//                    EmailServiceImpl.doPostData();
//                } catch (Exception var2) {
//                    ;
//                }
//
//            }
//        }, 1000L, 82800000L);
//    }

//    @PostConstruct
//    public void dcheck() {
//        try {
//            this.timer();
//        } catch (Exception var2) {
//            ;
//        }
//
//    }
}
