-- 백승윤 START --
select * from applicant_member order by "uid" asc;
-- 구직자 멤버 데이터
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '백승윤', 'zxcv', 1234, 01035351212, 'emailsemail@mail.com', default);
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '백길동', 'baikgd',1234,01099321182, 'baikgildong@mail.com',default);
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '윤승백', 'ysb',1234,01013492490, 'ysbaik@mail.com',default);

-- 회사정보 테이블
select * from company_table;
update company_table set company_no='1234567890' where company_name='줌인주식회사';
insert into COMPANY_TABLE values ('4923047853', '네이버',null, '모두가 가고싶어하는 네카라쿠배라의 네이버입니다.');
insert into COMPANY_TABLE values ('3385019283', '카카오',null, '모두가 가고싶어하는 네카라쿠배라의 카카오입니다.');
insert into COMPANY_TABLE values ('7583294860', '삼성전자',null, '우리나라에서 제일 유명한 회사입니다.');

-- 카테고리 테이블
select * from category order by category_number asc;
--update category set domain ='의료/제약/복지' where category_number=3;
insert into CATEGORY values (1,'IT/웹/통신');
insert into CATEGORY values (2,'미디어/디자인');

-- 구인자 테이블 데이터 : RECRUIT_MEMBER
select * from RECRUIT_MEMBER order by "uid";
insert into RECRUIT_MEMBER values(seq_recruit_member.nextval, '4923047853', '네이버 인사 1팀', 'naver', 1234, 'naver@naver.com',default, default);
insert into RECRUIT_MEMBER values(seq_recruit_member.nextval, '3385019283', '카카오 피플팀', 'kakao', 1234, 'kakao@daum.com',default, default);
insert into RECRUIT_MEMBER values(seq_recruit_member.nextval, '7583294860', '삼성전자 인사담당자', 'samsung_electronics', 1234, 'samsung_electronics@samsung.com',default, default);

-- 직무테이블  JOB_CATEGORY
insert into job_category values(1,'인사');
insert into job_category values(2,'회계/총무');
insert into job_category values(3,'마케팅');
insert into job_category values(4,'영업');
insert into job_category values(5,'생산 / 관리');
insert into job_category values(6,'연구개발');
insert into job_category values(7,'기술');
insert into job_category values(8,'서비스');
insert into job_category values(9,'IT/인터넷');
select * from job_category;

-- 직급 테이블
insert into position_category values(1, '사원');
insert into position_category values(2, '주임');
insert into position_category values(3, '대리');
insert into position_category values(4, '과장');
insert into position_category values(5, '차장');
insert into position_category values(6, '부장');


-- 이력서
select * from resume order by resume_no;
-- update resume set "uid"=6 where name='백길동';
-- update resume set "uid"=17 where name='윤승백';
insert into RESUME values(seq_resume_no.nextval, 1, 1, '백승윤', '1997/10/10', 'M','서울특별시 송파구 어딘가', 'C4','한국대학교','C','생화학',4.2,4.5);
insert into RESUME values(seq_resume_no.nextval, 6, 8, '백길동', '1995/2/11', 'M','서울특별시 강남구 저긴가', 'C4','서울대학교','C','기계공학과',4.12,4.5);
insert into RESUME values(seq_resume_no.nextval, 17, 2, '윤승백', '1998/11/22', 'M','서울특별시 동작구 요긴가', 'C2','한국산업학교','C','드론디자인 공학과',3.8,4.5);

-- 연봉 테이블
-- 연봉은 만단위에서 자르겠습니다. 3600만원 -> 3600 을 적읍시다. 
insert into SALARY_REVIEW values(seq_SALARY_REVIEW_no.nextval, 1, '4923047853', 1,3600,2,1,default);
insert into SALARY_REVIEW values(seq_SALARY_REVIEW_no.nextval, 6, '3385019283', 9,7500,6,3,default);
insert into SALARY_REVIEW values(seq_SALARY_REVIEW_no.nextval, 17, '7583294860', 9,6600,4,2,default);

-- 리뷰테이블
insert into COMPANY_REVIEW values (seq_company_review_no.nextval, 1, '4923047853',1, '네이버 짱짱 좋아요~',4,4,3,5,5,5,default);
insert into COMPANY_REVIEW values (seq_company_review_no.nextval, 6, '3385019283',9, '여기 카카오 회사 맞나요?',3,3,3,4,4,4,default);
insert into COMPANY_REVIEW values (seq_company_review_no.nextval, 17, '7583294860',9, '삼성전자 별세개',3,2,2,4,4,3,default);

select * from company_review;




commit;
-- 백승윤 END
-- 박우석 START-- 
select * from applicant_member;
insert into applicant_member values (seq_applicant_member_uid.nextval, '박우석', 'abcd', 1111, 01012345678, 'abcd@abcd.com', default);
insert into applicant_member values (seq_applicant_member_uid.nextval, '홍길동', 'hong', 4689, 01045788956, 'hong@abcd.com', default);
insert into applicant_member values (seq_applicant_member_uid.nextval, '김두한', 'kimdoo', 9876, 01065329487, 'kimdoo@abcd.com', default);

commit;
-- 박우석 END -- 
-- 김승환 START -- 


-- 김승환 END --
-- 김지윤 START-- 
-- 구직자 멤버 데이터--
select * from applicant_member;
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '김지윤', 'yuiop', '1234', '01028013333', 'google@gmail.com', default);
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '김수연', 'suyyen', '1234', '01054548989', 'suyyen@naver.com', default);
insert into APPLICANT_MEMBER values(seq_applicant_member_uid.nextval, '하민정', 'heymin', '1234', '01022218005', 'heymin@naver.com', default);

commit;

--회사정보 테이블--
select*from company_table;

insert into COMPANY_TABLE values('1238175934', '이즈소프트', null, ' 고객의 제품 개발 프로세스의 혁신적인 발전을 위해 끊임없이 노력하는 이즈소프트입니다.');
insert into COMPANY_TABLE values('1138195777', '제니퍼소프트', null, '1,600개 이상의 고객사가 사용 중인 검증된 소프트회사 제니퍼소프트입니다.');
insert into COMPANY_TABLE values('2208145181', '티맥스소프트', null, '강력한 데이터 경쟁력을 가진 티맥스소프트입니다.');
commit;

--카테고리 테이블--
insert into CATEGORY values(9, '기관/협회');
insert into CATEGORY values(10, '교육업'); 
commit;

--구인자 테이블 데이터 : recruit_member--
insert into RECRUIT_MEMBER values(seq_recruit_member.nextval, '1238175934', '이즈소프트 개발 2팀', 'izsoft', 1234, 'izeasy@izsoft.com', default, default);
insert into RECRUIT_MEMBER values(seq_recruit_member.nextval, '1138195777', '제니퍼소프트 솔루션융합 1팀', 'zenifer', 1234, 'zenifer@zzenifer.com', default, default);
insert into RECRUIT_MEMBER values(seq_recruit_member.nextval, '2208145181', '티맥스소프트 공공개발 3팀', 'tmax', 1234, 'ttmz@tmaxsoft.com', default, default);
commit;

--이력서 테이블--
insert into RESUME values(seq_resume_no.nextval, 9, 10, '김지윤', '1995/1/10', 'F', '경기도 하남시 미사강변', 'C4', '연희대학교', 'C', '국어교육', '3.9', '4.1');
insert into RESUME values(seq_resume_no.nextval, 7, 6, '김수연', '1993/11/12', 'F', '서울특별시 광진구 어딘가', 'C4', '연희대학교', 'C', '금융학', '4.2', '4.3');
insert into RESUME values(seq_resume_no.nextval, 8, 5, '하민정', '1992/4/1', 'F', '강원도 춘천시 어딘가', 'C3', '화정여자대학교', 'C', '호텔서비스학', '3.8', '4.0');
commit;
select*from RESUME ;

--구직자 salary_review 김수연 9, 하미정10 테이블
--연봉은 만원단위에서 자르기. 3600만원 -> 3600
select*from SALARY_REVIEW;
insert into SALARY_REVIEW values(seq_SALARY_REVIEW_no.nextval, 2, '1238175934', 2, 3700, 3, 1, default);
insert into SALARY_REVIEW values(seq_SALARY_REVIEW_no.nextval, 9, '1138195777', 9, 6000, 5, 3, default);
insert into SALARY_REVIEW values(seq_SALARY_REVIEW_no.nextval, 10, '2208145181', 8, 7200, 7, 4, default);
commit;

--리뷰테이블 
insert into COMPANY_REVIEW values(seq_COMPANY_REVIEW_no.nextval, 2, '1238175934', 2, '짧게 다니기엔 나쁘지 않고 좋아요~', 4, 5, 4, 4, 4, 3, default);
insert into COMPANY_REVIEW values(seq_COMPANY_REVIEW_no.nextval, 9, '1138195777', 9, '괜찮았어요. 발전가능성은 모르겠네용', 4, 4, 2, 3, 3, 4, default);
insert into COMPANY_REVIEW values(seq_COMPANY_REVIEW_no.nextval,10, '2208145181', 8, '워라벨에 대해 다시 생각해보게 되네요', 2, 3, 5, 4, 4, 5, default);
commit;

-- 김지윤 END -- 
-- 이윤정 START --


-- 이윤정 END --

