select * from tab;

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
    writer varchar2(50) not null,
    salary number not null,
    work_year number not null,
    job_position varchar2(30) not null,
    reg_date date default sysdate,
    constraint pk_salary_review_no primary key(no),
    constraint fk_salary_review_uid foreign key("uid") references APPLICANT_MEMBER("uid") on delete set null,
    constraint fk_salary_review_company_no foreign key(company_no) references COMPANY_TABLE(company_no) on delete cascade,
    constraint fk_salary_review_category_number foreign key(category_number) references CATEGORY(category_number) on delete set null
);

create sequence seq_SALARY_REVIEW_no;

