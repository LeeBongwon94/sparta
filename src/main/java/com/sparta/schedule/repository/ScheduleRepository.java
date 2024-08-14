package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
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

    // 일정 수정
    public ScheduleResponseDto update(
            @PathVariable int schedule_id,
            @RequestBody ScheduleRequestDto requestDto)
    {
        if(findById(schedule_id)) {
            if(requestDto.getPassword().equals(selectOne(schedule_id).getPassword())) {
                String sql = "UPDATE schedule SET manager = ?, contents = ? WHERE schedule_id = ?";

                jdbcTemplate.update(sql, requestDto.getManager(), requestDto.getContents(), schedule_id);
                // 반환값이 객체인데, 일정을 수정할 때 일정(날짜)은 데이터가 안넘어와서 기존 데이터를 불러오기위해 selectOne(schedule_id).getMyDate() 사용.
                return new ScheduleResponseDto(schedule_id, requestDto.getManager(), selectOne(schedule_id).getMyDate(), requestDto.getContents());
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                return null;
            }
        } else {
            throw new IllegalArgumentException("선택한 일정이 존재하지 않습니다.");
        }
    }

    public boolean findById(int schedule_id){
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next())    return true;
            else                    return false;
        }, schedule_id);
    }
}