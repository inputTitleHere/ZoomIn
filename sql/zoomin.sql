--alter session set "_oracle_script" = true;
-- admin용 코드
create user zoomin identified by Khjava123456 default tablespace users;

alter user zoomin quota unlimited on users;
grant connect, resource, create view to zoomin;

-- zoomin용 코드
select * from tab;

create table COMPANY_TABLE(
    company_no char(10) not null,
    company_name varchar2(50) not null,
    company_logo varchar2(255),
    company_info varchar2(4000),
    CONSTRAINT pk_company_no primary key(company_no)
);

create table CATEGORY(
    category_number number not null,
    domain varchar2(50) not null,
    constraint pk_category_number primary key(category_number)
);

create table RECRUIT_MEMBER(
    uid number,
    company_no char(10),
    "name" varchar2(50),
    "id" varchar2(50),
    "password" varchar2(300),
    email varchar2(100),
    supervisor char(1) default 'N',
    reg_date date default sysdate,
    constraint pk_uid primary key(uid),
    constraint fk_company_no foreign key(company_no) references COMPANY_TABLE(company_no) on delete cascade
);

