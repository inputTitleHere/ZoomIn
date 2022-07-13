-- 백승윤 START --
select * from applicant_member;
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '백승윤', 'zxcv', 1234, 01035351212, 'emailsemail@mail.com', default);

-- 백승윤 END
-- 박우석 START-- 
select * from applicant_member;
insert into applicant_member values (seq_applicant_member_uid.nextval, '박우석', 'abcd', 1111, 01012345678, 'abcd@abcd.com', default);
insert into applicant_member values (seq_applicant_member_uid.nextval, '홍길동', 'hong', 4689, 01045788956, 'hong@abcd.com', default);
insert into applicant_member values (seq_applicant_member_uid.nextval, '김두한', 'kimdoo', 9876, 01065329487, 'kimdoo@abcd.com', default);

--update applicant_member set password = 1234 where id = 'kimdoo';
-- 회사정보 테이블
select * from COMPANY_TABLE;
insert into COMPANY_TABLE values ('1948653785', '현대', null, '전자쪽에 발을 뻗치는 현대입니다.');
insert into COMPANY_TABLE values ('8153268498', '벤츠코리아', null, '최고의 전기차를 만들기 위해 노력하는 벤츠입니다.');
insert into COMPANY_TABLE values ('4988653629', 'KH전자', null, '최상위 레벨의 인재들이 모인 기업 KH전자입니다.');

-- 업무 카테고리 테이블
select * from CATEGORY order by category_number asc;
insert into CATEGORY values (3, '의료/제약/복지/');
insert into CATEGORY values (4, '건설업');

-- 취업담당자 한명씩 만들기
select * from recruit_member;
insert into RECRUIT_MEMBER values (seq_recruit_member.nextval, '1948653785', '현대 인사 1팀', 'hyundai', 1234, 'hyundai@mail.com', default, default);
insert into RECRUIT_MEMBER values (seq_recruit_member.nextval, '8153268498', '벤츠코리아 인사팀', 'benzko', 1234, 'benzko@mail.com', default, default);
insert into RECRUIT_MEMBER values (seq_recruit_member.nextval, '4988653629', 'KH전자 인사과장', 'khjava', 1234, 'khjava@mail.com', default, default);

-- 이력서 만들기
select * from resume;
insert into RESUME values (seq_resume_no.nextval, 5, 1, '박우석', '1998/11/09', 'M', '경기도 광명시', 'C4', '서울대학교', 'C', '컴퓨터', 4.0, 4.5);
insert into RESUME values (seq_resume_no.nextval, 13, 7, '홍길동', '1967/08/24', 'M', '강원도 춘천시', 'C3', '춘천고등학교', 'C', '기계공학', 3.4 , 4.5);
insert into RESUME values (seq_resume_no.nextval, 14, 3, '김두한', '1983/02/17', 'M', '제주도 서귀포시', 'C2', '조선대학교', 'C', '지리학', 2.5, 4.5);

-- 연봉테이블
select * from salary_review;
insert into salary_review values (SEQ_SALARY_REVIEW_NO.nextval, 5, '1948653785', 9, 8000, 4, 3, default);
insert into salary_review values (SEQ_SALARY_REVIEW_NO.nextval, 13, '8153268498', 5, 4000, 2, 2, default);
insert into salary_review values (SEQ_SALARY_REVIEW_NO.nextval, 14, '4988653629', 3, 2000, 1, 1, default);

-- 회사리뷰테이블
select * from company_review;
insert into company_review values (SEQ_COMPANY_REVIEW_NO.nextval, 5, '1948653785', 9, '자유로운 회사에요.', 4, 4, 5, 4, 5, 5, default);
insert into company_review values (SEQ_COMPANY_REVIEW_NO.nextval, 13, '8153268498', 5, '이것도 회사인가..?', 3, 2, 2, 1, 3, 2, default);
insert into company_review values (SEQ_COMPANY_REVIEW_NO.nextval, 14, '4988653629', 3, '다닐만 해요.', 3, 4, 5, 4, 3, 5, default);

commit;
-- 박우석 END -- 
-- 김승환 START -- 


-- 김승환 END --
-- 김지윤 START -- 


-- 김지윤 END -- 
-- 이윤정 START --


-- 이윤정 END --

