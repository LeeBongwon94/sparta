package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto){
        // Repository -> Entity
        Schedule schedule = new Schedule(requestDto);

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.create(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }

    // ID로 일정 조회
    public ScheduleResponseDto getSchedule(int schedule_id){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return new ScheduleResponseDto(scheduleRepository.selectOne(schedule_id));
    }
}