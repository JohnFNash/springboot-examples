package com.johnfnash.learn.shiro.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * 数据库对象抽象类
 */
@Data
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 5952973214101216907L;

	@TableId(type = IdType.UUID)
    private String id;
    
    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
    
}
