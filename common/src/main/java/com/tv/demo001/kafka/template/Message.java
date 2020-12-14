package com.tv.demo001.kafka.template;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author Allen
 * @Date 2020-09-17 14:37
 **/
@Data
public class Message {
    private Long id;
    private String message;
    private Date sendTime;
}
