package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Schedule {
    private int schedule_id;
    private String manager;
    private Date myDate;
    private String contents;
    private String password;
    private Date created_at;
    private Date updated_at;

    public Schedule(ScheduleRequestDto requestDto){
        this.manager = requestDto.getManager();
        this.myDate = requestDto.getMyDate();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
}
