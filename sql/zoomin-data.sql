-- 백승윤 START --
select * from applicant_member;
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '백승윤', 'zxcv', 1234, 01035351212, 'emailsemail@mail.com', default);

-- 백승윤 END
-- 박우석 START-- 
select * from applicant_member;
insert into applicant_member values (seq_applicant_member_uid.nextval, '박우석', 'abcd', 1111, 01012345678, 'abcd@abcd.com', default);
insert into applicant_member values (seq_applicant_member_uid.nextval, '홍길동', 'hong', 4689, 01045788956, 'hong@abcd.com', default);
insert into applicant_member values (seq_applicant_member_uid.nextval, '김두한', 'kimdoo', 9876, 01065329487, 'kimdoo@abcd.com', default);

commit;
-- 박우석 END -- 
-- 김승환 START -- 
--구직자 멤버
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '김승환', 'kshw', 1234, 01089284867, 'kshwan1379@naver.com',default);
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '김남길', 'kngs', 1234, 01098523346, 'kngs@naver.com',default);
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '김창렬', 'kcl', 1234, 01066248753, 'kcl@naver.com',default);
select * from applicant_member;

--회사 정보
insert into company_table values('6875341675','농협',null,'제 1 금융권 모든 국민과 함께 하는 그날까지!');
insert into company_table values('3578951486','국민은행',null,'자산관리의 베스트 국민은행');
insert into company_table values('6877345778','신한은행',null,'돈불려 드립니다 신한은행');
select * from company_table;

--업무 카테고리 테이블 
insert into category values(5,'서비스업');
insert into category values(6,'은행/금융업');
commit;
select * from category order by category_number; 

-- 구인자 테이블 데이터
insert into recruit_member values(seq_recruit_member.nextval,'6875341675','농협 민원 접수팀','nonghyeob',1234,'nong@naver.com',default,default);
insert into recruit_member values(seq_recruit_member.nextval,'3578951486','국민은행 경비 1팀','kukmin',1234,'kuk@naver.com',default,default);
insert into recruit_member values(seq_recruit_member.nextval,'6877345778','신한은행 창구2팀','sinhan',1234,'sin@naver.com',default,default);
select * from  recruit_member;
commit;

--이력서
select * from resume;

insert into  resume values(seq_resume_no.nextval, 7, 3, '김승환', '1996/11/11', 'M','경기도 하남시 미사강변대로', 'C2', '춘천대학교','C', '의료기기정보과', '4.0', '4.1');
insert into  resume values(seq_resume_no.nextval, 15, 5, '김남길', '1966/05/05', 'F','서울시 강남구 봉은사로', 'C4', '서울대학교','C', '토목과', '2.8', '3.0');
insert into  resume values(seq_resume_no.nextval, 16, 7, '김창렬', '1973/2/18', 'M','강원도 춘천시 기계공고', 'C3', '가천대학교','C', '기계설비학과', '3.5', '4.2');
--구직자  연봉테이블
insert into salary_review values(seq_SALARY_REVIEW_no.nextval,7,'6875341675',3,2600,3,3,default);
insert into salary_review values(seq_SALARY_REVIEW_no.nextval,15,'3578951486',6,3500,4,2,default);
insert into salary_review values(seq_SALARY_REVIEW_no.nextval,16,'6877345778',6,4200,5,3,default);
select * from salary_review;

select * from company_review;
insert into company_review values (seq_company_review_no.nextval, 7,'6875341675',3,'끔찍합니다 정말',1,2,2,3,2,2,default);
insert into company_review values (seq_company_review_no.nextval, 15,'6875341675',6,'그냥 그래요',3,3,3,3,3,3,default);
insert into company_review values (seq_company_review_no.nextval, 16,'6875341675',6,'죽지못해 다닙니다',2,3,4,3,2,1,default);
commit;
-- 김승환 END --
-- 김지윤 START -- 


-- 김지윤 END -- 
-- 이윤정 START --


-- 이윤정 END --

