-- 공통 START --
--alter session set "_oracle_script" = true;
-- admin용 코드
create user zoomin identified by Khjava123456 default tablespace users;

alter user zoomin quota unlimited on users;
grant connect, resource, create view to zoomin;
-- 공통 END --
-- 백승윤 START --
-- zoomin용 코드
select * from tab;
--show recyclebin;
--purge recyclebin;
--drop table company_table;
create table COMPANY_TABLE(
    company_no char(10) not null,
    company_name varchar2(50) not null,
    company_logo varchar2(255),
    company_info varchar2(4000),
    CONSTRAINT pk_company_table_company_no primary key(company_no)
);

insert into COMPANY_TABLE values(0123456789, '줌인주식회사', null,'줌인 웹사이트를 운영하는 주식회사입니다.');
commit;
select * from company_Table;

create table CATEGORY(
    category_number number not null,
    domain varchar2(50) not null,
    constraint pk_category_number primary key(category_number)
);

create table JOB_CATEGORY(
    category_number number not null,
    job_name varchar2(50) not null,
    constraint pk_job_category_category_number primary key(category_number)
);

create table POSITION_CATEGORY(
    category_number number not null,
    position_name varchar2(50) not null,
    constraint pk_postion_category_category_number primary key(category_number)
);
--drop table position_category;
select * from position_category;

commit;

--drop table recruit_member;
create table RECRUIT_MEMBER(
   "uid" number,
    company_no char(10),
    name varchar2(50),
    id varchar2(50),
    password varchar2(300),
    email varchar2(100),
    supervisor char(1) default 'N',
    reg_date date default sysdate,
    constraint pk_uid primary key("uid"),
    constraint fk_company_no foreign key(company_no) references COMPANY_TABLE(company_no) on delete cascade,
    constraint ck_supervisor check (supervisor in ('Y','N'))
);
create sequence seq_recruit_member;
--alter table recruit_member add constraint ck_supervisor check (supervisor in ('Y','N'));


select * from user_constraints where table_name = 'RECRUIT_MEMBER';


create table RECRUIT_BOARD(
    no number not null,
    "uid" number not null,
    category_number number not null,
    company_no char(10) not null,
    title varchar2(300) not null,
    career_years_req varchar2(50) default '경력무관',
    school_status varchar2(50) default '학력무과',
    work_type varchar2(50) default '미정',
    office_location varchar2(50) default '미정',
    salary varchar2(50) default '회사 내규',
    content varchar2(4000),
    closure_date date not null,
    reg_date date default sysdate,
    constraint pk_recruit_board_no primary key(no),
    constraint fk_recruit_board_uid foreign key("uid") references RECRUIT_MEMBER("uid"),
    constraint fk_recruit_board_category_number foreign key(category_number) references CATEGORY(category_number),
    constraint fk_recruit_board_company_no foreign key(company_no) references COMPANY_TABLE(company_no)
);
create sequence seq_recruit_board;
alter table recruit_board drop constraint fk_recruit_board_uid;
alter table recruit_board add constraint fk_recruit_board_uid foreign key("uid") references RECRUIT_MEMBER("uid") on delete cascade;
alter table recruit_board drop constraint fk_recruit_board_category_number;
alter table recruit_board add constraint fk_recruit_board_category_number foreign key(category_number) references CATEGORY(category_number) on delete set null;
alter table recruit_board drop constraint fk_recruit_board_company_no;
alter table recruit_board add constraint fk_recruit_board_company_no foreign key(company_no) references COMPANY_TABLE(company_no) on delete cascade;


select * from user_constraints where table_name = 'RECRUIT_BOARD';

create table recruit_job_bridge(
    recruit_board_no number not null,
    job_category_number number not null,
    constraint fk_recruit_job_bridge_recruit_board_no foreign key(recruit_board_no) references recruit_board(no) on delete cascade,
    constraint fk_recruit_job_bridge_job_category_number foreign key(job_category_number) references job_category(category_number) on delete set null
);

create table FAVOURITE(
    "uid" number,
    recruit_board_no number,
    constraint fk_favourite_uid foreign key("uid") references applicant_member,
    constraint fk_favourite_recruit_board_no foreign key(recruit_board_no) references recruit_board
);


create table ENROLL_TABLE(
    "uid" number,
    recruit_board_no number,
    constraint fk_enroll_table_uid foreign key("uid") references applicant_member,
    constraint fk_enroll_table_board_no foreign key(recruit_board_no) references recruit_board
);

-- 백승윤 END --
-- 박우석 START --

-- 회사 리뷰 테이블
create table COMPANY_REVIEW(
    no number not null,
    "uid" number not null,
    company_no char(10) not null,
    category_number number not null,
    writer varchar2(50) not null,
    content varchar2(4000) not null,
    stars number,
    work_life_balance number,
    level_up number,
    work_intensity number,
    potential number,
    salary_satisfaction number,
    reg_date date default sysdate,
    constraint pk_company_review_no primary key(no) ,
    constraint fk_company_review_uid foreign key("uid") references APPLICANT_MEMBER("uid") on delete set null,
    constraint fk_company_review_company_no foreign key(company_no) references COMPANY_TABLE(company_no) on delete cascade,
    constraint fk_company_review_category_number foreign key(category_number) references CATEGORY(category_number) on delete set null,
    constraint ck_stars check(stars in (1, 2, 3, 4, 5)),
    constraint ck_work_life_balance check(work_life_balance in (1, 2, 3, 4, 5)),
    constraint ck_level_up check(level_up in (1, 2, 3, 4, 5)),
    constraint ck_work_intensity check(work_intensity in (1, 2, 3, 4, 5)),
    constraint ck_potential check(potential in (1, 2, 3, 4, 5)),
    constraint ck_salary_satisfaction check(salary_satisfaction in (1, 2, 3, 4, 5))
);

alter table company_review drop constraint fk_company_review_category_number;
alter table company_review add constraint fk_company_review_category_number foreign key(category_number) references job_category(category_number) on delete set null;

--alter table company_Review drop column writer;
select * from company_review;
--alter table COMPANY_REVIEW add constraint ck_stars check(stars in (1, 2, 3, 4, 5));
--alter table COMPANY_REVIEW add constraint ck_work_life_balance check(work_life_balance in (1, 2, 3, 4, 5));
--alter table COMPANY_REVIEW add constraint ck_level_up check(level_up in (1, 2, 3, 4, 5));
--alter table COMPANY_REVIEW add constraint ck_work_intensity check(work_intensity in (1, 2, 3, 4, 5));
--alter table COMPANY_REVIEW add constraint ck_potential check(potential in (1, 2, 3, 4, 5));
--alter table COMPANY_REVIEW add constraint ck_salary_satisfaction check(salary_satisfaction in (1, 2, 3, 4, 5));

create sequence seq_COMPANY_REVIEW_no;

select * from user_constraints where table_name = 'COMPANY_REVIEW';

-- 연봉 리뷰 테이블
create table SALARY_REVIEW(
    no number not null,
    "uid" number not null,
    company_no char(10) not null,
    category_number number not null,
    salary number not null,
    work_year number not null,
    job_position varchar2(30) not null,
    reg_date date default sysdate,
    constraint pk_salary_review_no primary key(no),
    constraint fk_salary_review_uid foreign key("uid") references APPLICANT_MEMBER("uid") on delete set null,
    constraint fk_salary_review_company_no foreign key(company_no) references COMPANY_TABLE(company_no) on delete cascade,
    constraint fk_salary_review_category_number foreign key(category_number) references job_category(category_number) on delete set null
);
alter table salary_review drop column reg_date;
alter table salary_review add reg_date date default sysdate;
select * from salary_review;
alter table salary_review drop constraint fk_salary_review_category_number;
alter table salary_review add constraint fk_salary_review_category_number foreign key(category_number) references job_category(category_number) on delete set null;
alter table salary_review add constraint fk_salary_review_job_position foreign key(job_position) references POSITION_CATEGORY(category_number) on delete set null;

create sequence seq_SALARY_REVIEW_no;
-- 박우석 END -- 
-- 김지윤 START --
--구직자 멤버 테이블
create table APPLICANT_MEMBER(
"uid" number not null,
name varchar2(30),
id varchar2(50),
password varchar2(300),
phone char(11),
email varchar(100),
reg_date date default sysdate,
constraint pk_applicant_member_uid primary key ("uid"), 
constraint uq_applicant_member_id unique(id)
);

--시퀀스 구직자 멤버 uid 생성 
create sequence seq_applicant_member_uid;

SELECT*FROM RECRUIT_BOARD;
--drop table RECRUIT_TABLE;

--채용글-지원자 연결 테이블
create table RECRUIT_TABLE(
no number not null,
"uid" number not null,
reg_date date default sysdate,
constraint fk_recruit_board_no foreign key(no) references RECRUIT_BOARD(no) , --set null
constraint fk_recruit_uid foreign key("uid") references APPLICANT_MEMBER("uid") --cascade 
);

alter table RECRUIT_TABLE drop constraint fk_recruit_board_no;
alter table RECRUIT_TABLE add constraint fk_recruit_board_no foreign key(no) references RECRUIT_BOARD(no) on delete set null;

select*from user_constraints where table_name = 'RECRUIT_TABLE';

alter table RECRUIT_TABLE drop constraint fk_recruit_uid;
alter table RECRUIT_TABLE add constraint fk_recruit_uid foreign key("uid") references APPLICANT_MEMBER("uid") on delete cascade;
-- 김지윤 END --
-- 이윤정 START --
create table statistic(
    day date not null,
    recruit_count number,
    review_count number,
    constraint pk_day primary key (day)
);

create table career(
    resume_no number not null,
    company_name varchar2(50),
    enter_date date,
    exit_date date,
    is_working char(1),
    constraint fk_resume_no foreign key(resume_no) references RESUME(resume_no) on delete cascade
);

alter table career add constraint ck_is_working check (is_working in ('A', 'B', 'C'));
create table resume(
    resume_no number not null,
    "uid" number not null,
    category_number number not null,
    name varchar2(30),
    birthday date,
    gender char(1),
    address varchar2(200),
    school_type char(2),
    school_name varchar2(60),
    school_status char(1),
    major_name varchar2(60),
    grade number,
    total_point number,
    constraint pk_resume_no primary key (resume_no),
    constraint fk_uid foreign key("uid") references APPLICANT_MEMBER ("uid") on delete cascade,
    constraint fk_category_number foreign key (category_number) references CATEGORY(category_number)
);
create sequence seq_resume_no;
--select * from all_sequences where sequence_name='SEQ_RESUME_NO';

alter table resume drop constraint fk_category_number;
alter table resume add constraint fk_category_number foreign key (category_number) references CATEGORY(category_number) on delete set null;

--ck추가
alter table resume add constraint ck_gender check (gender in ('M', 'F'));
alter table resume add constraint ck_school_type check (school_type in ('H1', 'C2', 'C3', 'C4'));
alter table resume add constraint ck_school_status check (school_status in ('A', 'B', 'C'));

--방문자용 통계 테이블
--drop table visit
create table visit (
	v_date date not null
);
select * from visit;
select count(*) from visit where to_date(v_date, 'yyyy-mm-dd') = to_date(sysdate, 'yyyy-mm-dd');
insert into visit values (to_date(sysdate, 'yyyy-mm-dd'));
insert into visit values (to_date('2022-07-11', 'yyyy-mm-dd'));
insert into visit values (to_date('2022-07-12', 'yyyy-mm-dd'));
insert into visit values (to_date('2022-07-13', 'yyyy-mm-dd'));
insert into visit values (to_date('2022-07-14', 'yyyy-mm-dd'));
insert into visit values (to_date('2022-07-15', 'yyyy-mm-dd'));

--방문자 수 증가
insert into visit values (sysdate);

--오늘 방문자 수
select count(*) from visit where to_date(v_date, 'yyyy-mm-dd') = to_date(sysdate, 'yyyy-mm-dd');
select sysdate from dual;
--총 방문자수 
select * from visit;

--날짜별 방문자 수 
select * from visit where v_date between to_date('2022-07-14', 'yyyy-mm-dd') and to_date('2022-07-14', 'yyyy-mm-dd') + 0.99999;

commit;

select count(*) from company_review where to_date(reg_date, 'yyyy-mm-dd') = to_date('2022-07-18', 'yyyy-mm-dd');
select count(*) from salary_review where to_date(reg_date, 'yyyy-mm-dd') = to_date(sysdate, 'yyyy-mm-dd');

--today확인용 게시글 insert
select * from salary_review;
insert into salary_review values(SEQ_SALARY_REVIEW_NO.nextval, 3, '1472583694', 3, 3000, 1, 1, sysdate);
select * from company_review;

--게시판 전체 수 (union all)
select 
    sum(Cnt)
from (
    select count(*) as Cnt from salary_review
    union all
    select count(*) as Cnt from company_review
);
--select sum(Cnt) from (select count(*) as Cnt from salary_review union all select count(*) as Cnt from company_review)

--게시글 날짜별 조회하기
--select count(*) from(select "uid", reg_date from salary_review  union all  select "uid", reg_date from company_review) where reg_date between to_date(? , 'yyyy-mm-dd') and to_date(? , 'yyyy-mm-dd') + 0.99999
select
    *
from(
    select "uid", reg_date from salary_review
    union all
    select "uid", reg_date from company_review
)
where reg_date between to_date('2022-07-18' , 'yyyy-mm-dd') and to_date('2022-07-18' , 'yyyy-mm-dd') + 0.99999;

--최근 일주일 데이터 가져오기(방문자)
-- select trunc(v_date) as "date", count(*) cnt from  visit where  v_date >= to_char((sysdate-7), 'yyyymmdd') group by  trunc(v_date)
select 
   (v_date) as "date",
    count(*) cnt
from 
    visit 
where
     v_date >= to_char((sysdate-7), 'yyyymmdd') 
group by 
    (v_date)
order by
    1;

---최근 일주일 데이터 가져오기(게시판)
--select trunc(reg_date) as "date", count(*) cnt from(select "uid", reg_date from salary_review union all  select "uid", reg_date from company_review) where reg_date >= to_char((sysdate-7), 'yyyymmdd') group by trunc(reg_date)
select
    trunc(reg_date) as "date",
    count(*) cnt
from(
    select "uid", reg_date from salary_review
    union all
    select "uid", reg_date from company_review
)
where 
    reg_date >= to_char((sysdate-7), 'yyyymmdd')
group by 
    trunc(reg_date);
--게시판관리
select * from salary_review;
select * from category;
select * from position_category;
--연봉게시판 전체조회 
--select no, id, company_no, domain, salary, work_year, position_name, s.reg_date from salary_review s join applicant_member a on s."uid" = a."uid" join category c on s.category_number = c.category_number join position_category p on p.category_number = c.category_number order by reg_date desc;
select * from(select row_number () over (order by s.reg_date desc) rnum,  no, id, company_no, domain, salary, work_year, position_name, s.reg_date from salary_review s join applicant_member a on s."uid" = a."uid" join category c on s.category_number = c.category_number join position_category p on p.category_number = c.category_number)where  rnum between 11 and 20;
        

--회사리뷰게시판 전체조회
select * from company_review where no = '22';
select * from(select row_number () over (order by c.reg_date desc) rnum, no, id, content, c.reg_date from company_review c join applicant_member a on c."uid" = a."uid")where  rnum between 11 and 20; 

insert into salary_review values(SEQ_SALARY_REVIEW_NO.nextval, 3, '1472583694', 3, 3000, 1, 1, default); 
-- 이윤정 END --

--김승환 테스트용
select * from resume;
delete from resume where resume_no=41;
select  * from applicant_member;
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '김테스트', 'ktest', 1234, 01055559999, 'ktest@mail.com', default);
commit;