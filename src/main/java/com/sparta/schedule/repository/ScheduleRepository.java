package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule create(@RequestBody Schedule schedule){
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (manager, contents, myDate, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getManager());
                    preparedStatement.setString(2, schedule.getContents());
                    preparedStatement.setDate(3, schedule.getMyDate());
                    preparedStatement.setString(4, schedule.getPassword());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        int id = keyHolder.getKey().intValue();
        schedule.setSchedule_id(id);

        return schedule;
    }

    // schedule_id로 조회
    public Schedule selectOne(@PathVariable int schedule_id){
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()){
                Schedule schedule = new Schedule();
                schedule.setSchedule_id(schedule_id);
                schedule.setManager(resultSet.getString("manager"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setMyDate(resultSet.getDate("myDate"));
                schedule.setPassword(resultSet.getString("password"));

                return schedule;
            } else {
                return null;
            }
        }, schedule_id);
    }

    // 모든 일정 조회
    public List<ScheduleResponseDto> selectAll(){
        String sql = "SELECT * FROM schedule ORDER BY updated_at desc";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                int id = rs.getInt("schedule_id");
                String manager = rs.getString("manager");
                Date myDate = rs.getDate("myDate");
                String contents = rs.getString("contents");
                return new ScheduleResponseDto(id, manager, myDate, contents);
            }
        });
    }

    // 입력한 manager가 포함된 일정 모두 조회
    public List<ScheduleResponseDto> selectAll(@PathVariable String manager){
        String sql = "SELECT * FROM schedule WHERE manager = ? ORDER BY updated_at desc";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
            int id = rs.getInt("schedule_id");
            String manager1 = rs.getString("manager");
            Date myDate = rs.getDate("myDate");
            String contents = rs.getString("contents");
            return new ScheduleResponseDto(id, manager1, myDate, contents);
        }, manager);
    }

    // 입력한 수정일이 포함된 일정 모두 조회
    public List<ScheduleResponseDto> selectAll(@PathVariable Date updated_at){
        String sql = "SELECT * FROM schedule WHERE DATE(updated_at) = ? ORDER BY updated_at desc";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                int id = rs.getInt("schedule_id");
                String manager = rs.getString("manager");
                Date myDate = rs.getDate("myDate");
                String contents = rs.getString("contents");
                return new ScheduleResponseDto(id, manager, myDate, contents);
            }
        }, updated_at);
    }
}