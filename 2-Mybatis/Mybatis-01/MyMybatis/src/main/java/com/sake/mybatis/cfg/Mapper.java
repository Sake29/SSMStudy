package com.sake.mybatis.cfg;

/**
 * 用于封装执行的sql语句和结果类型的全限定类名
 */
public class Mapper {

    //sql
    private String queryString;
    //实体类的全限定类名
    private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
