package com.johnfnash.learn.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JobTest {
	
	public void run() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(df.format(new Date()) + " job executing...");
	}
	
}
