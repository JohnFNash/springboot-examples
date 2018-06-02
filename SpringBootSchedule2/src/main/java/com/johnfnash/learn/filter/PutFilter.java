package com.johnfnash.learn.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

@Component
public class PutFilter extends HttpPutFormContentFilter {

}