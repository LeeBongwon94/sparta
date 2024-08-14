# API 명세서
기능|Method|             URL             | request |response
:---:|:---:|:---------------------------:|:--------:|:---:
일정 작성|POST|        /api/schedule        | 요청 body  |작성 정보
일정 조회|GET| /api/schedule/{schedule_id} | 요청 param |단건 일정 정보
모든 일정 조회|GET|       /api/schedule/        | 요청 param |모든 일정 정보
일정 수정|PUT| /api/schedule/{schedule_id} | 요청 body |수정 정보
일정 삭제|DELETE| /api/schedule/{schedule_id} |요청 param|


# ERD

