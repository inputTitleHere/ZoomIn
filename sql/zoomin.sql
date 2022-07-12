--alter session set "_oracle_script" = true;
-- admin용 코드
create user zoomin identified by Khjava123456 default tablespace users;

alter user zoomin quota unlimited on users;
grant connect, resource, create view to zoomin;

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

create table CATEGORY(
    category_number number not null,
    domain varchar2(50) not null,
    constraint pk_category_number primary key(category_number)
);

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
alter table recruit_board drop constraint fk_recruit_board_uid;
alter table recruit_board add constraint fk_recruit_board_uid foreign key("uid") references RECRUIT_MEMBER("uid") on delete cascade;
alter table recruit_board drop constraint fk_recruit_board_category_number;
alter table recruit_board add constraint fk_recruit_board_category_number foreign key(category_number) references CATEGORY(category_number) on delete set null;
alter table recruit_board drop constraint fk_recruit_board_company_no;
alter table recruit_board add constraint fk_recruit_board_company_no foreign key(company_no) references COMPANY_TABLE(company_no) on delete cascade;


select * from user_constraints where table_name = 'RECRUIT_BOARD';

