package com.ssk.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.ssk.exception.ServiceException;

import java.beans.Transient;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分页数据封装类
 */
public class CommonPage<T> extends Page<T> {
    private static final long serialVersionUID = 1L;  // 序列化
    private List<T> records;  // 查询内容
    private Map<String, Object> condition; // 映射
    private Date time;  // 时间 当current 为1 的时候，才会传输时间，前端需要判断并且存储

    public CommonPage() {
        this.records = Collections.emptyList();
    }

    /**
     * 无条件分页查询
     *
     * @param current 页数
     * @param size    每页显示数量
     */
    public CommonPage(int current, int size) throws ServiceException {
        super(current, size);
        if (current <= 0 || size < 0) {
            throw new ServiceException("请传输有效页码参数");
        }
        if (current == 1) {
            this.time = new Date();
        }
        this.records = Collections.emptyList();
    }

    /**
     * 将现有的list进行分页
     *
     * @param current 页数
     * @param size    页大小
     * @param list    集合
     */
    public CommonPage(int current, int size, List<T> list) {
        super(current, size);
        if (current == 1 || current == 0) {
            this.time = new Date();
        }
        //去掉list中的null值
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) == null) {
                list.remove(i);
            }
        }
        this.records = list;
    }

    /**
     * 条件分页查询
     *
     * @param current      页数
     * @param size         每页显示数量
     * @param orderByField 条件
     */
    public CommonPage(int current, int size, String orderByField) {
        super(current, size);
        if (current == 1) {
            this.time = new Date();
        }
        this.records = Collections.emptyList();
        this.setOrderByField(orderByField);
    }

    /**
     * 条件分页查询
     *
     * @param current      页数
     * @param size         每页显示数量
     * @param orderByField 条件
     * @param isAsc        正序 倒序
     */
    public CommonPage(int current, int size, String orderByField, boolean isAsc) {
        this(current, size, orderByField);
        if (current == 1) {
            this.time = new Date();
        }
        this.setAsc(isAsc);
    }

    public List<T> getRecords() {
        return this.records;
    }

    public CommonPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Transient
    public Map<String, Object> getCondition() {
        return this.condition;
    }

    public CommonPage<T> setCondition(Map<String, Object> condition) {
        this.condition = condition;
        return this;
    }

    public String toString() {
        StringBuilder pg = new StringBuilder();
        pg.append(" Page:{ [").append(super.toString()).append("], ");
        if (this.records != null) {
            pg.append("records-size:").append(this.records.size());
        } else {
            pg.append("records is null");
        }
        return pg.append(" }").toString();
    }


}
