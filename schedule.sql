# schedule 데이터베이스 생성
CREATE DATABASE schedule;

# 각 테이블 생성
# on update를 통해 update될 시 현재시간으로 자동으로 update됨
CREATE TABLE schedule(
     schedule_id int auto_increment primary key,
     manager varchar(20) not null,
     myDate DATETIME not null,
     contents varchar(100) not null,
     password varchar(20) not null,
     created_at DATETIME not null default CURRENT_TIMESTAMP,
     updated_at DATETIME not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

# 모든 일정 조회, 수정일 기준으로 내림차순
SELECT * FROM schedule ORDER BY updated_at DESC;

# 선택한 schedule_id 조회, 수정일 기준으로 내림차순
SELECT * FROM schedule WHERE schedule_id = ? ORDER BY updated_at DESC;

# 선택한 manager 일정 모두 조회, 수정일 기준으로 내림차순
SELECT * FROM schedule WHERE manager = ? ORDER BY updated_at DESC;

# 선택한 수정일에 대한 일정 모두 조회, 수정일 기준으로 내림차순
# 기존 updated_at는 날짜와 시간 모두 나오지만 DATE로 감싸줌으로 날짜만 나오게 됨
SELECT * FROM schedule WHERE DATE(updated_at) = ? ORDER BY updated_at DESC;

# 선택한 일정 수정
UPDATE schedule SET manager = ?, contents = ? WHERE schedule_id = ?;

# 선택한 일정 삭제
DELETE FROM schedule WHERE schedule_id = ?;