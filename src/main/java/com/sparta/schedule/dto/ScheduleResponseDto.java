package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private int schedule_id;
    private String manager;
    private Date myDate;
    private String contents;

    public ScheduleResponseDto(Schedule schedule){
        this.schedule_id = schedule.getSchedule_id();
        this.manager = schedule.getManager();
        this.myDate = schedule.getMyDate();
        this.contents = schedule.getContents();
    }

    public ScheduleResponseDto(int schedule_id, String manager, Date myDate, String contents){
        this.schedule_id = schedule_id;
        this.manager = manager;
        this.myDate = myDate;
        this.contents = contents;
    }
}