package com.ww.common;

import java.util.List;

/**
 * Created by ww on 17/3/11.
 */
public interface BaseDao {
    Long insert(String var1, Object var2);

    Long delete(String var1, Object var2);

    Long update(String var1, Object var2);

    <T> T selectOne(String var1, Object var2);

    <T> List<T> selectList(String var1, Object var2);

    <T> List<T> queryForListPage(String var1, Object var2, PageEntity var3);
}
