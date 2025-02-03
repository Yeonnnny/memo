-- 문제 1

-- 수강생을 관리하는 MANAGER 테이블을 만들어보세요.
-- - 컬럼은 총 id, name, student_code 입니다.
-- - id는 bigint 타입이며 PK입니다.
-- - name은 최소 2자 이상, varchar 타입, not null 입니다.
-- - student_code는 STUDENT 테이블을 참조하는 FK이며 not null 입니다.
-- - FK는 CONSTRAINT 이름을 ‘manager_fk_student_code’ 로 지정해야합니다.

create table MANAGER(
    id bigint PRIMARY KEY,
    name varchar(255) not null check(CHAR_LENGTH(name) >=2),
    student_code varchar(100) not null,
    constraint manager_fk_student_code foreign key (student_code) references STUDENT(student_code)
);

-- 문제 2
-- ALTER, MODIFY를 이용하여 MANAGER 테이블의 id 컬럼에 AUTO_INCREMENT 기능을 부여하세요.
ALTER TABLE MANAGER MODIFY COLUMN id BIGINT AUTO_INCREMENT;

-- 문제 3
-- INSERT를 이용하여 수강생 s1, s2, s3, s4, s5를 관리하는 managerA와 s6, s7, s8, s9를 관리하는 managerB를 추가하세요.
-- - AUTO_INCREMENT 기능을 활용하세요
INSERT INTO MANAGER(name, student_code) VALUES ("managerA","s1");
INSERT INTO MANAGER(name, student_code) VALUES ("managerA","s2");
INSERT INTO MANAGER(name, student_code) VALUES ("managerA","s3");
INSERT INTO MANAGER(name, student_code) VALUES ("managerA","s4");
INSERT INTO MANAGER(name, student_code) VALUES ("managerA","s5");

INSERT INTO MANAGER(name, student_code) VALUES ("managerB","s6");
INSERT INTO MANAGER(name, student_code) VALUES ("managerB","s7");
INSERT INTO MANAGER(name, student_code) VALUES ("managerB","s8");
INSERT INTO MANAGER(name, student_code) VALUES ("managerB","s9");

-- 문제 4
-- JOIN을 사용하여 managerA가 관리하는 수강생들의 이름과 시험 주차 별 성적을 가져오세요.
select s.name, e.exam_seq, e.score
from student s join exam e
on s.student_code = e.student_code
where s.student_code in (select student_code
                        from manager 
                        where name = 'managerA');

-- 문제 5
-- STUDENT 테이블에서 s1 수강생을 삭제했을 때 EXAM에 있는 s1수강생의 시험성적과 MANAGER의 managerA가 관리하는 수강생 목록에 자동으로 삭제될 수 있도록 하세요.
-- - ALTER, DROP, MODIFY, CASCADE 를 사용하여 EXAM, MANAGER 테이블을 수정합니다.

-- 1) 기존 외래키 제약 조건 삭제
alter table exam drop foreign key exam_fk_student_code;
alter table manager drop foreign key manager_fk_student_code;

-- 2) cascade 옵션 추가해 외래키 추가
alter table exam 
add constraint exam_fk_student_code
foreign key (student_code) references student(student_code)
on delete cascade;

alter table manager
add constraint manager_fk_student_code
foreign key (student_code) references student(student_code)
on delete cascade;

-- 3) 확인 테스트
INSERT INTO STUDENT (student_code, name, gender, MAJOR_code) VALUES ('s0', '김도연', 'F', 'm1');
INSERT INTO EXAM (student_code,exam_seq,score,result) VALUES ('s0',1,95,'P');
INSERT INTO MANAGER (name, student_code) VALUES ('managerA', 's0');

delete from student where student_code = 's0';

select * from exam;
select * from manager;
select * from exam where student_code = 's0';
select * from manager where student_code = 's0';