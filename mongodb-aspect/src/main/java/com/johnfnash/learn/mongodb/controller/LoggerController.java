package com.johnfnash.learn.mongodb.controller;

import com.johnfnash.learn.mongodb.entity.LoggerInfo;
import com.johnfnash.learn.mongodb.service.LoggerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private LoggerInfoService loggerInfoService;

    @GetMapping("/listByTitle")
    public List<LoggerInfo> getLoggerInfo(@RequestParam("title") String title){
        List<LoggerInfo> loggerInfos = loggerInfoService.getLoggerInfoListByTitle(title);
        return loggerInfos;
    }

    @GetMapping("/listByDate")
    public List<LoggerInfo> getInfoByDate(@RequestParam("startDate") String startDate , @RequestParam("endDate") String endDate){
        List<LoggerInfo> loggerInfos = loggerInfoService.getLoggerInfoListByDate(startDate, endDate);
        return loggerInfos;
    }

}
