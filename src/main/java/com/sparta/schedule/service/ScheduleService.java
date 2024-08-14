package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

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

    // 모든 일정 조회
    public List<ScheduleResponseDto> getSchedule(){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.selectAll();
    }

    // manager로 일정 조회
    public List<ScheduleResponseDto> getSchedule(String manager){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.selectAll(manager);
    }

    // 수정일로 일정 조회
    public List<ScheduleResponseDto> getSchedule(Date updated_at){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.selectAll(updated_at);
    }

    // 선택한 일정 수정
    public ScheduleResponseDto updateSchedule(int schdule_id, ScheduleRequestDto requestDto){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.update(schdule_id, requestDto);
    }

    // 선택한 일정 삭제
    public void deleteSchedule(int schedule_id, ScheduleRequestDto requestDto){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        scheduleRepository.delete(schedule_id, requestDto);
    }
}