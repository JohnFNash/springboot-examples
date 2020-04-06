package com.johnfnash.learn.mongodb.mapper;

import com.johnfnash.learn.mongodb.entity.LoggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class LoggerInfoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<LoggerInfo> getLoggerInfo(String title) {
        Query query = new Query();
        Criteria criteria = Criteria.where("title").is(title);
        query.addCriteria(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createDate"));
        return mongoTemplate.find(query, LoggerInfo.class);
    }

    public List<LoggerInfo> getLoggerBetweenTime(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sdate = null, edate = null;
        try {
            sdate = sdf.parse(startDate);
            edate = sdf.parse(endDate);
        } catch (ParseException e) {
            System.out.println("时间转换错误======");
            e.printStackTrace();
        }

        Query query = new Query();
        Criteria criteria = new Criteria();
        if (sdate != null && edate != null) {
            criteria = Criteria.where("createDate").gte(sdate).lte(edate);
        }
        query.addCriteria(criteria);
        return mongoTemplate.find(query, LoggerInfo.class);
    }

    public void addLoggerInfo(LoggerInfo loggerInfo) {
        mongoTemplate.insert(loggerInfo);
    }

}