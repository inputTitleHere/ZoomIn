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

select * from company_table;
select * from COMPANY_REVIEW where company_no=4923047853;
select * from company_review order by company_no;
select * from enroll_table;
select * from category order by category_number;

select * from RESUME where "uid" in (select "uid" from ENROLL_TABLE where recruit_board_no=4);

select * from recruit_board where "uid"=1 order by closure_date-sysdate asc;
select * from category order by category_number;

--delete from recruit_board where no in (49,50,51);

commit;