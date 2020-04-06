package com.johnfnash.learn.mongodb.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "LoggerInfo")
@CompoundIndexes(
        @CompoundIndex(name = "title", def = "{ title : 1 }")
)
public class LoggerInfo {

    private static final long serialVersionUID = 4495390935587105239L;

    private String title;

    private Date createDate;

    private String createDateStr;

    private Object[] arguments;

    private String targetName;

    private String methodName;

    private Object result;

    private String ip;

    public LoggerInfo() {
    }

    public LoggerInfo(String title, Date createDate, Object[] arguments, Object result, String targetName, String methodName, String ip) {
        this.title = title;
        this.createDate = createDate;
        this.arguments = arguments;
        this.targetName = targetName;
        this.methodName = methodName;
        this.result = result;
        this.ip = ip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return new Date();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateStr() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(this.createDate);
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
