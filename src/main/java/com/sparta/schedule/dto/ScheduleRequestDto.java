package com.sparta.schedule.dto;

import lombok.Getter;

import java.sql.Date;

@Getter
public class ScheduleRequestDto {
    private String contents;
    private String manager;
    private String password;
    private Date myDate;
}