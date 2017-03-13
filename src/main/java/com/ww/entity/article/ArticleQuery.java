package com.ww.entity.article;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ww on 17/3/13.
 */
@Data
public class ArticleQuery {
    private static final long serialVersionUID = -3888944756009060820L;
    private String queryKey;
    private int type;
    private int orderby; //排序条件 0时间倒序 1浏览量倒序
    private int count;//查询数据量 0不限制	大于0限制

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginCreateTime;//查询 开始添加时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;//查询 结束添加时间
}
