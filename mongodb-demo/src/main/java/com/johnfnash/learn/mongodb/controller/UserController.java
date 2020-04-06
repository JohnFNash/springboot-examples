package com.johnfnash.learn.mongodb.controller;

import com.johnfnash.learn.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    // 集合名
    private static final String COLLECTION_NAME = "userx";

    @PostMapping("/add")
    public String insert(@RequestBody User user) {
        this.mongoTemplate.insert(user, COLLECTION_NAME);
        return "ok";
    }

    @GetMapping("/list")
    public List<User> query(@RequestParam(value = "dataStatus") Integer dataStatus) {
        Query query = Query.query(Criteria.where("dataStatus").is(dataStatus));
        //List<User> users = this.mongoTemplate.find(query, User.class, COLLECTION_NAME);
        List<User> users = this.mongoTemplate.find(query, User.class);
        return users;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "userId") String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        this.mongoTemplate.remove(query, COLLECTION_NAME);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody User user) {
        Query query = Query.query(Criteria.where("userId").is(user.getUserId()));
        Update update = new Update();
        update.set("age", user.getAge());
        update.set("name", user.getName());
        update.set("email", user.getEmail());
        this.mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
        return "ok";
    }

}
