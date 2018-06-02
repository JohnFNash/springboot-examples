INSERT INTO `quartz_job_bean` 
(`job_id`,`cron_expression`,`description`,`is_concurrent`,`job_class`,`job_group`,`job_name`,`job_status`,`method_name`,`next_time`,`previous_time`,`spring_id`,`start_time`) 
VALUES 
 ('1','*/20 * * * * ?','触发器task_1','1','com.johnfnash.learn.jobs.JobTest','group_1','task_1','1','run','2018-06-02 09:45:40','2018-06-02 09:45:20',NULL,'2018-05-29 09:02:20')
,('18','*/5 * * * * ?','task 5\t','1','com.johnfnash.learn.jobs.JobTest','group_1','task_5','0','run','2018-05-29 14:47:00',NULL,NULL,'2018-05-29 14:46:56')
,('2','0 * 14-19 * * ?','触发器task_2','1','com.johnfnash.learn.jobs.JobTest','group_1','task_2','0','run','2018-05-28 14:00:00','2018-05-28 14:00:00',NULL,'2018-05-28 10:25:48')
,('3','0 0/1 * * * ?','触发器task_3','1','com.johnfnash.learn.jobs.JobTest','group_1','task_3','0','run','2018-05-23 00:00:00','2018-05-22 00:00:00',NULL,'2018-05-23 00:00:00')
,('4','0 0/1 * * * ?','触发器task_4','1','com.johnfnash.learn.jobs.JobViewTest','group_1','task_4','0','run','2018-05-24 21:55:00','2018-05-24 21:54:00',NULL,'2018-05-24 21:54:00');
