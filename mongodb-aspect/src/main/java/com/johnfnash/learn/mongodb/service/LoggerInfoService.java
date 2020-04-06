package com.johnfnash.learn.mongodb.service;

import com.johnfnash.learn.mongodb.entity.LoggerInfo;
import com.johnfnash.learn.mongodb.mapper.LoggerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoggerInfoService {

    @Autowired
    private LoggerInfoRepository infoRepository;

    @Transactional(readOnly = false)
    public void addLoggerInfo(LoggerInfo loggerInfo) {
        infoRepository.addLoggerInfo(loggerInfo);
    }

    public List<LoggerInfo> getLoggerInfoListByTitle(String title) {
        return infoRepository.getLoggerInfo(title);
    }

    public List<LoggerInfo> getLoggerInfoListByDate(String startDate, String endDate) {
        return infoRepository.getLoggerBetweenTime(startDate, endDate);
    }

}
