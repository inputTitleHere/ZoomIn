select * from (select row_number() over(order by closure_date asc) rnum, r.* from recruit_board r where closure_date-sysdate>0) b where rnum between 1 and 5;
select * from recruit_board;
select count(*) from recruit_board where closure_date-sysdate>0;
select closure_date-sysdate from recruit_board;

select * from resume order by "uid" asc;

select * from applicant_member order by "uid";
select * from recruit_member order by "uid";
select * from recruit_board order by company_no;

select * from favourite;
delete from favourite where "uid"=1;
commit;

select * from (select row_number() over(order by reg_date desc) rnum, s.*,c.company_name from salary_review s join company_table c on s.company_no=c.company_no) a where rnum between 1 and 5;


select * from career;
select * from job_category;
select * from company_table;
select count(*) from company_table where company_no=1122334455;
select * from COMPANY_REVIEW where company_no=4923047853;
select * from company_review order by company_no;
select * from enroll_table;
select * from category order by category_number;

select * from RESUME where "uid" in (select "uid" from ENROLL_TABLE where recruit_board_no=4);

select * from recruit_board where "uid"=1 order by closure_date-sysdate asc;
select * from category order by category_number;

select * from position_category;
select * from salary_review;

--delete from recruit_board where no in (49,50,51);

commit;

----------------------------
drop table price_table;
create table test(
    col1 number,
    col2 number
);

insert into test values(1,1);
insert into test values(2,2);
insert into test values(null,3);
insert into test values(4,null);
commit;

select count(*)from test where col2 in (1,2,3) or col1 in (4);
select count(col2) from test where col2 in (1,2,3) or col1 in (4);

