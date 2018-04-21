--------------------------------------------------------
--  File created - Sunday-April-22-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type EXCELDATAOBJECT
--------------------------------------------------------

  CREATE OR REPLACE TYPE "SHAY"."EXCELDATAOBJECT" AS OBJECT
(
  bil varchar2(80),
  namestud varchar2(80),
  matricNumber varchar(80),
  course varchar2(80),
  cohort varchar2(80),
  muet varchar2(80),
  yearsem varchar2(80),
  status varchar2(80),
  gpa varchar2(80),
  academicAdvisor varchar2(80),
  phoneNumber varchar(80),
  sessions varchar2(20),
  createBy varchar2(20)
);

/
--------------------------------------------------------
--  DDL for Type EXCELDATAOBJECTARRAY
--------------------------------------------------------

  CREATE OR REPLACE TYPE "SHAY"."EXCELDATAOBJECTARRAY" AS TABLE OF EXCELDATAOBJECT;

/
--------------------------------------------------------
--  DDL for Type MESSAGEDATA
--------------------------------------------------------

  CREATE OR REPLACE TYPE "SHAY"."MESSAGEDATA" AS OBJECT
(
  tajuk varchar2(80),
  mesej varchar2(4000),
  penerima varchar(80),
  sender varchar2(80)
);

/
--------------------------------------------------------
--  DDL for Type MESSAGEDATAARRAY
--------------------------------------------------------

  CREATE OR REPLACE TYPE "SHAY"."MESSAGEDATAARRAY" AS TABLE OF MESSAGEDATA;

/
--------------------------------------------------------
--  DDL for Sequence SQ_AU_RES
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_AU_RES"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 827 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_AU_STAFF
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_AU_STAFF"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 827 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_AU_STUD
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_AU_STUD"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 827 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_COURSE
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_COURSE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 19 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_MESSAGE
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_MESSAGE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_MOBILE
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_MOBILE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 491 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_RESULTS
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_RESULTS"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1171 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_SEMESTER
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_SEMESTER"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 19 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_STUD
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_STUD"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 547 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SQ_SUMMARY
--------------------------------------------------------

   CREATE SEQUENCE  "SHAY"."SQ_SUMMARY"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table COURSE
--------------------------------------------------------

  CREATE TABLE "SHAY"."COURSE" 
   (	"COURSE_ID" VARCHAR2(20 BYTE), 
	"COURSE_NAME" VARCHAR2(20 BYTE), 
	"COURSE_CODE" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MESSAGENOTI
--------------------------------------------------------

  CREATE TABLE "SHAY"."MESSAGENOTI" 
   (	"MESSAGE_ID" VARCHAR2(20 BYTE), 
	"MESSAGE_TITLE" VARCHAR2(80 BYTE), 
	"MESSAGE_BODY" VARCHAR2(4000 BYTE), 
	"RECEIVER" VARCHAR2(20 BYTE), 
	"SENDER" VARCHAR2(20 BYTE), 
	"DATETIME" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MOBILE_APP
--------------------------------------------------------

  CREATE TABLE "SHAY"."MOBILE_APP" 
   (	"MOBILE_ID" VARCHAR2(20 BYTE), 
	"MATRIC_NO" VARCHAR2(20 BYTE), 
	"DEVICE_ID" VARCHAR2(255 BYTE), 
	"DATE_ADDED" TIMESTAMP (6), 
	"STATUS" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table RESULTS
--------------------------------------------------------

  CREATE TABLE "SHAY"."RESULTS" 
   (	"ID" VARCHAR2(20 BYTE), 
	"MATRIC_NO" VARCHAR2(20 BYTE), 
	"SEMESTER_ID" VARCHAR2(20 BYTE), 
	"GPA" NUMBER(3,2), 
	"CREATEBY" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SEMESTER
--------------------------------------------------------

  CREATE TABLE "SHAY"."SEMESTER" 
   (	"SEMESTER_ID" VARCHAR2(20 BYTE), 
	"YEARS" VARCHAR2(20 BYTE), 
	"SESSIONS" VARCHAR2(20 BYTE), 
	"SEMESTER" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table STAFF
--------------------------------------------------------

  CREATE TABLE "SHAY"."STAFF" 
   (	"STAFF_NO" VARCHAR2(20 BYTE), 
	"FULL_NAME" VARCHAR2(80 BYTE), 
	"ADDRESS" VARCHAR2(255 BYTE), 
	"EMAIL" VARCHAR2(20 BYTE), 
	"PHONE_NUMBER" VARCHAR2(15 BYTE), 
	"PASSWORD" VARCHAR2(255 BYTE), 
	"CREATEBY" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table STUDENT
--------------------------------------------------------

  CREATE TABLE "SHAY"."STUDENT" 
   (	"ID" VARCHAR2(20 BYTE), 
	"MATRIC_NO" VARCHAR2(20 BYTE), 
	"FULL_NAME" VARCHAR2(80 BYTE), 
	"COURSE_ID" VARCHAR2(20 BYTE), 
	"COHORT" VARCHAR2(20 BYTE), 
	"STATUS" VARCHAR2(20 BYTE), 
	"ACADEMIC_ADVISOR" VARCHAR2(90 BYTE), 
	"PHONE_NUMBER" VARCHAR2(15 BYTE), 
	"MUET" VARCHAR2(20 BYTE), 
	"CREATEBY" VARCHAR2(20 BYTE), 
	"AUTH" VARCHAR2(255 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SUMMARY
--------------------------------------------------------

  CREATE TABLE "SHAY"."SUMMARY" 
   (	"SUMMARY_ID" VARCHAR2(20 BYTE), 
	"TOTAL_NORMAL" NUMBER, 
	"TOTAL_DEANLIST" NUMBER, 
	"TOTAL_CONDITIONAL_POS" NUMBER, 
	"SEMESTER_ID" VARCHAR2(20 BYTE), 
	"COURSE_ID" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TBL_AUDIT_RESULTS
--------------------------------------------------------

  CREATE TABLE "SHAY"."TBL_AUDIT_RESULTS" 
   (	"AUDIT_ID" VARCHAR2(20 BYTE), 
	"OLD_VALUES" VARCHAR2(255 BYTE), 
	"NEW_VALUES" VARCHAR2(255 BYTE), 
	"TRANSACTIONS" VARCHAR2(20 BYTE), 
	"USERS" VARCHAR2(20 BYTE), 
	"TIME_DATES" TIMESTAMP (6)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TBL_AUDIT_STAFF
--------------------------------------------------------

  CREATE TABLE "SHAY"."TBL_AUDIT_STAFF" 
   (	"AUDIT_ID" VARCHAR2(20 BYTE), 
	"OLD_VALUES" VARCHAR2(255 BYTE), 
	"NEW_VALUES" VARCHAR2(255 BYTE), 
	"TRANSACTIONS" VARCHAR2(20 BYTE), 
	"USERS" VARCHAR2(20 BYTE), 
	"TIME_DATES" TIMESTAMP (6)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TBL_AUDIT_STUDENT
--------------------------------------------------------

  CREATE TABLE "SHAY"."TBL_AUDIT_STUDENT" 
   (	"AUDIT_ID" VARCHAR2(20 BYTE), 
	"OLD_VALUES" VARCHAR2(255 BYTE), 
	"NEW_VALUES" VARCHAR2(255 BYTE), 
	"TRANSACTIONS" VARCHAR2(20 BYTE), 
	"USERS" VARCHAR2(20 BYTE), 
	"TIME_DATES" TIMESTAMP (6)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SHAY.COURSE
SET DEFINE OFF;
Insert into SHAY.COURSE (COURSE_ID,COURSE_NAME,COURSE_CODE) values ('C13',null,'BITC');
Insert into SHAY.COURSE (COURSE_ID,COURSE_NAME,COURSE_CODE) values ('C17',null,'BITD');
REM INSERTING into SHAY.MESSAGENOTI
SET DEFINE OFF;
Insert into SHAY.MESSAGENOTI (MESSAGE_ID,MESSAGE_TITLE,MESSAGE_BODY,RECEIVER,SENDER,DATETIME) values ('MES1','GPA FOR SEMESTER/SESSION 2-2017/2018','Your GPA is 3','B031610008','SSUSER',to_timestamp('22-APR-18 02.29.57.873000000','DD-MON-RR HH24.MI.SSXFF'));
Insert into SHAY.MESSAGENOTI (MESSAGE_ID,MESSAGE_TITLE,MESSAGE_BODY,RECEIVER,SENDER,DATETIME) values ('MES2','GPA FOR SEMESTER/SESSION 2-2017/2018','Your GPA is 4','B031520027','SSUSER',to_timestamp('22-APR-18 02.29.57.873000000','DD-MON-RR HH24.MI.SSXFF'));
REM INSERTING into SHAY.MOBILE_APP
SET DEFINE OFF;
Insert into SHAY.MOBILE_APP (MOBILE_ID,MATRIC_NO,DEVICE_ID,DATE_ADDED,STATUS) values ('M489','B031610008','chpnKUc2BaE:APA91bHpD18szMrBdb5WCyre2X1UcL57a1X5Nu74CjW31YLNlrFBch8XaXw8ypI-bFUOWRSBIqGTWpZ0SGppfbtjy-cYymXYIOqzt1OJ9xmqykyVA9Ei70rf_LChMKII7s6HEL0G410L',to_timestamp('22-APR-18 01.37.04.395000000','DD-MON-RR HH24.MI.SSXFF'),'1');
Insert into SHAY.MOBILE_APP (MOBILE_ID,MATRIC_NO,DEVICE_ID,DATE_ADDED,STATUS) values ('M490','B031520027','eU5R2ok8Kd8:APA91bFpNAEQB387PpDsZEdGGi2FNN9iJz0FKhd3l2k72U9DvZXlIb0jutezS9zMHswrw9MiEMKSsGeMGVCepK6A0HnpZ6Rf1ukpxNBWwRSJQPO5cKstYkUZzuccSiTguyslhV3unoTr',to_timestamp('22-APR-18 02.28.05.283000000','DD-MON-RR HH24.MI.SSXFF'),'1');
REM INSERTING into SHAY.RESULTS
SET DEFINE OFF;
Insert into SHAY.RESULTS (ID,MATRIC_NO,SEMESTER_ID,GPA,CREATEBY) values ('STUD1165','B031520025','SEM13',3.12,'SSUSER');
Insert into SHAY.RESULTS (ID,MATRIC_NO,SEMESTER_ID,GPA,CREATEBY) values ('STUD1166','B031610008','SEM13',3.1,'SSUSER');
Insert into SHAY.RESULTS (ID,MATRIC_NO,SEMESTER_ID,GPA,CREATEBY) values ('STUD1167','B031610003','SEM13',3.12,'SSUSER');
Insert into SHAY.RESULTS (ID,MATRIC_NO,SEMESTER_ID,GPA,CREATEBY) values ('STUD1168','B031610002','SEM13',3.18,'SSUSER');
Insert into SHAY.RESULTS (ID,MATRIC_NO,SEMESTER_ID,GPA,CREATEBY) values ('STUD1169','B031520027','SEM13',4,'SSUSER');
Insert into SHAY.RESULTS (ID,MATRIC_NO,SEMESTER_ID,GPA,CREATEBY) values ('STUD1170','B031610009','SEM13',4,'SSUSER');
REM INSERTING into SHAY.SEMESTER
SET DEFINE OFF;
Insert into SHAY.SEMESTER (SEMESTER_ID,YEARS,SESSIONS,SEMESTER) values ('SEM13','3','2-2017/2018','1');
REM INSERTING into SHAY.STAFF
SET DEFINE OFF;
Insert into SHAY.STAFF (STAFF_NO,FULL_NAME,ADDRESS,EMAIL,PHONE_NUMBER,PASSWORD,CREATEBY) values ('SSUSER','SUPERUSER','-','-','-','abc123','SUPERUSER');
Insert into SHAY.STAFF (STAFF_NO,FULL_NAME,ADDRESS,EMAIL,PHONE_NUMBER,PASSWORD,CREATEBY) values ('STAFF001','DUMMY','N/A','DUMMY@DUM.COM','00000','abc123','SSUSER');
REM INSERTING into SHAY.STUDENT
SET DEFINE OFF;
Insert into SHAY.STUDENT (ID,MATRIC_NO,FULL_NAME,COURSE_ID,COHORT,STATUS,ACADEMIC_ADVISOR,PHONE_NUMBER,MUET,CREATEBY,AUTH) values ('STUD541','B031520025','AMIR HAZIM SHAH','C13','2015/2016','AKTIF','SYAHIRAN','999','3','SSUSER','B031520025');
Insert into SHAY.STUDENT (ID,MATRIC_NO,FULL_NAME,COURSE_ID,COHORT,STATUS,ACADEMIC_ADVISOR,PHONE_NUMBER,MUET,CREATEBY,AUTH) values ('STUD542','B031610008','SALMAN LUI','C13','2015/2016','AKTIF','SYAHIRAN','999','3','SSUSER','B031610008');
Insert into SHAY.STUDENT (ID,MATRIC_NO,FULL_NAME,COURSE_ID,COHORT,STATUS,ACADEMIC_ADVISOR,PHONE_NUMBER,MUET,CREATEBY,AUTH) values ('STUD543','B031610003','FAREZ FEEBLE','C13','2015/2016','AKTIF','SYAHIRAN','999','4','SSUSER','B031610003');
Insert into SHAY.STUDENT (ID,MATRIC_NO,FULL_NAME,COURSE_ID,COHORT,STATUS,ACADEMIC_ADVISOR,PHONE_NUMBER,MUET,CREATEBY,AUTH) values ('STUD544','B031610002','ILHAM IFTIKHAR','C13','2015/2016','AKTIF','SYAHIRAN','999','3','SSUSER','B031610002');
Insert into SHAY.STUDENT (ID,MATRIC_NO,FULL_NAME,COURSE_ID,COHORT,STATUS,ACADEMIC_ADVISOR,PHONE_NUMBER,MUET,CREATEBY,AUTH) values ('STUD545','B031520027','SYAHIRAN SHAHRIN','C17','2015/2016','AKTIF','SYAHIRAN','999','3','SSUSER','B031520027');
Insert into SHAY.STUDENT (ID,MATRIC_NO,FULL_NAME,COURSE_ID,COHORT,STATUS,ACADEMIC_ADVISOR,PHONE_NUMBER,MUET,CREATEBY,AUTH) values ('STUD546','B031610009','AFIQ RAZAK','C17','2015/2016','AKTIF','SYAHIRAN','999','3','SSUSER','B031610009');
REM INSERTING into SHAY.SUMMARY
SET DEFINE OFF;
REM INSERTING into SHAY.TBL_AUDIT_RESULTS
SET DEFINE OFF;
REM INSERTING into SHAY.TBL_AUDIT_STAFF
SET DEFINE OFF;
REM INSERTING into SHAY.TBL_AUDIT_STUDENT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SUMMARY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."SUMMARY_PK" ON "SHAY"."SUMMARY" ("SUMMARY_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index STUDENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."STUDENT_PK" ON "SHAY"."STUDENT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index COURSE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."COURSE_PK" ON "SHAY"."COURSE" ("COURSE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SEMESTER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."SEMESTER_PK" ON "SHAY"."SEMESTER" ("SEMESTER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index RESULTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."RESULTS_PK" ON "SHAY"."RESULTS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index MOBILE_APP_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."MOBILE_APP_PK" ON "SHAY"."MOBILE_APP" ("MOBILE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index MESSAGENOTI_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."MESSAGENOTI_PK" ON "SHAY"."MESSAGENOTI" ("MESSAGE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index TBL_AUDIT_STUDENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."TBL_AUDIT_STUDENT_PK" ON "SHAY"."TBL_AUDIT_STUDENT" ("AUDIT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index STAFF_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SHAY"."STAFF_PK" ON "SHAY"."STAFF" ("STAFF_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger CHECK_COURSE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."CHECK_COURSE" 
BEFORE INSERT ON STUDENT
FOR EACH ROW 
DECLARE
v_count NUMBER;
v_seq NUMBER := SQ_COURSE.NEXTVAL;
v_id VARCHAR2(255);
BEGIN

SELCOUNTCOURSE(:NEW.COURSE_ID,v_count);

  IF (v_count < 1) THEN
    v_id := 'C'||v_seq;
    INSCOURSE(v_id,'',:NEW.COURSE_ID);
    :NEW.COURSE_ID := v_id;
  ELSE SELCOURCE(:NEW.COURSE_ID,v_id);
    :NEW.COURSE_ID := v_id;
  end if;
END;
/
ALTER TRIGGER "SHAY"."CHECK_COURSE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CHECK_RES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."CHECK_RES" 
BEFORE INSERT ON results
FOR EACH ROW 
DECLARE
v_count NUMBER;
BEGIN

SELECT COUNT(*)
  INTO v_count
  FROM results
  WHERE semester = :NEW.semester and years = :new.years and sessions = :new.sessions and MATRIC_NO = :new.matric_no;

  IF (v_count = 1) THEN
  Raise_Application_Error (-20010, 'ALREADY');
  END IF;

END;
/
ALTER TRIGGER "SHAY"."CHECK_RES" DISABLE;
--------------------------------------------------------
--  DDL for Trigger CHECK_STAFF
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."CHECK_STAFF" 
BEFORE INSERT ON STAFF
FOR EACH ROW 
DECLARE
v_count NUMBER;
BEGIN

SELECT COUNT(*)
  INTO v_count
  FROM staff
  WHERE staff_no = :NEW.staff_no;

  IF (v_count = 1) THEN
  Raise_Application_Error (-20010, 'ALREADY');
  END IF;

END;
/
ALTER TRIGGER "SHAY"."CHECK_STAFF" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CHECK_STUD
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."CHECK_STUD" 
BEFORE INSERT ON STUDENT
FOR EACH ROW 
DECLARE
v_count NUMBER;
BEGIN

SELECT COUNT(*)
  INTO v_count
  FROM student
  WHERE MATRIC_NO = :NEW.MATRIC_NO;

  IF (v_count = 1) THEN
  Raise_Application_Error (-20010, 'ALREADY');
  END IF;

END;
/
ALTER TRIGGER "SHAY"."CHECK_STUD" DISABLE;
--------------------------------------------------------
--  DDL for Trigger T_MESSAGE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."T_MESSAGE" 
BEFORE INSERT ON MESSAGENOTI
FOR EACH ROW 
DECLARE
BEGIN
if (:NEW.message_id is null) then
:NEW.message_id := 'MES'||SQ_MESSAGE.nextval;
end if;
END;
/
ALTER TRIGGER "SHAY"."T_MESSAGE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger T_MOBILE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."T_MOBILE" 
BEFORE INSERT ON mobile_app
FOR EACH ROW 
DECLARE
BEGIN
if (:NEW.mobile_id is null) then
:NEW.mobile_id := 'M'||sq_mobile.nextval;
end if;

if(:NEW.date_added is null) then
:NEW.date_added := CURRENT_TIMESTAMP;
end if;
END;
/
ALTER TRIGGER "SHAY"."T_MOBILE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger T_RES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."T_RES" 
BEFORE INSERT ON results
FOR EACH ROW 
DECLARE
BEGIN
if (:NEW.ID is null) then
:NEW.ID := 'STUD'||sq_results.nextval;
end if;
END;
/
ALTER TRIGGER "SHAY"."T_RES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger T_STAFF
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."T_STAFF" 
BEFORE INSERT ON staff
FOR EACH ROW 
DECLARE
BEGIN
if (:NEW.password is null) then
:NEW.password := 'abc123';
end if;
END;
/
ALTER TRIGGER "SHAY"."T_STAFF" ENABLE;
--------------------------------------------------------
--  DDL for Trigger T_STUD
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."T_STUD" 
BEFORE INSERT ON student
FOR EACH ROW 
DECLARE
BEGIN
if (:NEW.ID is null) then
:NEW.ID := 'STUD'||sq_stud.nextval;
end if;

if(:NEW.auth is null) then
:NEW.auth := :NEW.matric_no;
end if;
END;
/
ALTER TRIGGER "SHAY"."T_STUD" ENABLE;
--------------------------------------------------------
--  DDL for Trigger T_SUMMARY
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SHAY"."T_SUMMARY" 
BEFORE INSERT ON SUMMARY
FOR EACH ROW
BEGIN
IF(:NEW.SUMMARY_ID IS NULL) THEN
:NEW.SUMMARY_ID := SQ_SUMMARY.NEXTVAL;
END IF;
END;
/
ALTER TRIGGER "SHAY"."T_SUMMARY" ENABLE;
--------------------------------------------------------
--  DDL for Procedure GETBYMATRIC
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."GETBYMATRIC" (p_matric IN varchar2,p_name OUT varchar2,p_course OUT varchar2,p_cohort OUT varchar2,p_status out varchar2,p_pa out varchar2,p_phone out varchar2,p_record out sys_refcursor)
as
v_count number;
begin
SELCOUNTSTUDENT(p_matric,v_count);
if(v_count > 0) then 
    select s.full_name,c.COURSE_CODE,s.cohort,s.status,s.academic_advisor,s.phone_number into p_name,p_course,p_cohort,p_status,p_pa,p_phone from student s inner join course c on c.COURSE_ID = s.COURSE_ID where s.matric_no = p_matric;
    open p_record for
    select r.gpa,se.sessions from results r 
    inner join semester se on r.SEMESTER_ID = se.SEMESTER_ID
    where r.matric_no = p_matric;
else Raise_Application_Error (-20010, 'NO STUDENT');
    end if;
end;

/
--------------------------------------------------------
--  DDL for Procedure GETBYSESSION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."GETBYSESSION" (p_sesi in varchar2,p_kos in varchar2,p_record OUT sys_refcursor)
as
begin
open p_record for
select  count(r.gpa),se.years from results r 
inner join STUDENT s on s.MATRIC_NO = r.MATRIC_NO 
inner join course c on c.COURSE_ID = s.COURSE_ID and c.COURSE_CODE = p_kos 
inner join semester se on se.SEMESTER_ID = r.SEMESTER_ID and se.SESSIONS = p_sesi
where  r.gpa > 3.0
group by se.years;
end;

/
--------------------------------------------------------
--  DDL for Procedure GETCOURSE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."GETCOURSE" (p_sesi in varchar2,p_cursor out SYS_REFCURSOR)
AS
BEGIN
open p_cursor for
select c.COURSE_CODE from student s 
inner join course c on c.COURSE_ID = s.COURSE_ID
inner join RESULTS r on r.MATRIC_NO = s.MATRIC_NO
inner join SEMESTER SE on SE.SEMESTER_ID = r.SEMESTER_ID and se.SESSIONS= p_sesi 
group by c.COURSE_CODE;
end;

/
--------------------------------------------------------
--  DDL for Procedure GETLISTSTAFF
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."GETLISTSTAFF" (p_cursor out SYS_REFCURSOR)
AS
begin
open p_cursor for
select staff_no,full_name,address,email,phone_number from staff;
end;

/
--------------------------------------------------------
--  DDL for Procedure GETSESI
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."GETSESI" (p_cursor out SYS_REFCURSOR)
AS
BEGIN
open p_cursor for
select sessions from semester group by sessions;
end;

/
--------------------------------------------------------
--  DDL for Procedure GETYEARS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."GETYEARS" (p_course in varchar2,p_sesi in varchar2,p_cursor out SYS_REFCURSOR)
AS
BEGIN
open p_cursor for 
select se.years from results r 
inner join course c on c.COURSE_CODE = p_course
inner join student s on s.MATRIC_NO = r.MATRIC_NO and s.COURSE_id = c.COURSE_ID
inner join semester se on se.SESSIONS = p_sesi
group by se.years;
end;

/
--------------------------------------------------------
--  DDL for Procedure INSCOURSE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."INSCOURSE" (p_id in varchar2,p_name in varchar2,p_code in varchar2)
as
begin
insert into course(course_id,course_name,course_code)values(p_id,p_name,p_code);
end;

/
--------------------------------------------------------
--  DDL for Procedure INSEXCELDATA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."INSEXCELDATA" (
   p_array in EXCELDATAOBJECTARRAY
) AS
   excelobject EXCELDATAOBJECT;
   v_count number;
   v_count2 number;
BEGIN
  for i in 1 .. p_array.count loop
    excelobject := p_array(i);
    select count(*) into v_count from student where matric_no = excelobject.matricNumber;
    if(v_count < 1) then
    INSSTUDENT(excelobject.matricNumber,excelobject.namestud,excelobject.course,excelobject.cohort,excelobject.status,excelobject.academicAdvisor,excelobject.phoneNumber,excelobject.muet,excelobject.createBy);
    end if; 
    
    SELCOUNTRESULT(excelobject.matricNumber,excelobject.sessions,SUBSTR(excelobject.yearsem,1,1),SUBSTR(excelobject.yearsem,-1,1),v_count2);
    if v_count2 < 1 then
    ins_results(excelobject.matricNumber,SUBSTR(excelobject.yearsem,-1,1),SUBSTR(excelobject.yearsem,1,1),excelobject.sessions,to_number(SUBSTR(excelobject.gpa,2,4)),excelobject.createBy);
    end if;
  end loop; 
END;

/
--------------------------------------------------------
--  DDL for Procedure INSMESAGE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."INSMESAGE" (p_tajuk in varchar2,p_msj in varchar2,p_matric in varchar2,p_sender in varchar2)
as
begin
insert into MESSAGENOTI(MESSAGE_TITLE,MESSAGE_BODY,receiver,sender,DATETIME)values(p_tajuk,p_msj,p_matric,p_sender,CURRENT_TIMESTAMP);
end;

/
--------------------------------------------------------
--  DDL for Procedure INSNULKMESSAGE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."INSNULKMESSAGE" (
   p_array in MESSAGEDATAARRAY
) AS
   mm MESSAGEDATA;
BEGIN
  for i in 1 .. p_array.count loop
    mm := p_array(i);
    INSMESAGE(mm.tajuk,mm.mesej,mm.penerima,mm.sender);
  end loop; 
END;

/
--------------------------------------------------------
--  DDL for Procedure INSSTUDENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."INSSTUDENT" (p_matric in varchar,p_name in varchar,p_course in  varchar2,p_cohort in varchar2,p_status in varchar2,p_pa in varchar2,p_phone in varchar2,p_muet in varchar2,p_create in varchar2)
as
begin
insert into student(matric_no,full_name,course_id,cohort,status,academic_advisor,phone_number,muet,CREATEBY)values(p_matric,p_name,p_course,p_cohort,p_status,p_pa,p_phone,p_muet,p_create);
end;

/
--------------------------------------------------------
--  DDL for Procedure INS_RESULTS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."INS_RESULTS" (p_id in varchar2,p_sem in varchar2,p_year in varchar2,p_sesi in  varchar2,p_gpa in number,p_create in varchar2)
as
v_seq number := SQ_SEMESTER.nextval;
v_id varchar2(255);
v_count number;

begin
SELCOUNTSEMESTER(p_year,p_sesi,p_sem,v_count);
if(v_count < 1) then
v_id := 'SEM'||v_seq;
insert INTO SEMESTER(semester_id,years,sessions,semester)values(v_id,p_year,p_sesi,p_sem); 
else SELSEMESTER(p_year,p_sesi,p_sem,v_id);
end if;

insert into results(matric_no,semester_id,gpa,CREATEBY) values(p_id,v_id,p_gpa,p_create);
end;

/
--------------------------------------------------------
--  DDL for Procedure INS_STAFF
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."INS_STAFF" (p_id in varchar2,p_name in varchar2,p_address in varchar2,p_email in varchar2,p_phone in varchar2,p_pass in varchar2,p_created in varchar2)
as
begin
     insert into staff(staff_no,full_name,address,email,phone_number,password,createby)values(p_id,p_name,p_address,p_email,p_phone,p_pass,p_created);
end;

/
--------------------------------------------------------
--  DDL for Procedure LOGIN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."LOGIN" (p_id IN varchar2,p_pass IN varchar2,p_staff_id OUT varchar2,p_name OUT varchar2,p_address OUT VARCHAR2,p_email OUT varchar2,p_phone_number out varchar2,p_password out varchar2)
as
begin
select staff_no,full_name,address,email,phone_number,password into p_staff_id,p_name,p_address,p_email,p_phone_number,p_password FROM staff where staff_no = p_id and password = p_pass;
end;

/
--------------------------------------------------------
--  DDL for Procedure LOGINMOBILE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."LOGINMOBILE" (p_id IN varchar2,p_pass IN varchar2,p_matric OUT varchar2,p_name OUT varchar2,p_phone_number out varchar2,p_token IN varchar2)
as
v_vount number;
v_key number;
begin
    select count(*) into v_vount FROM student WHERE matric_no = p_id and auth = p_pass;
    if (v_vount > 0) then
     SELECT matric_no,full_name,phone_number into p_matric,p_name,p_phone_number FROM student WHERE matric_no = p_id and auth = p_pass;
     select count(*) into v_key from mobile_app where matric_no = p_matric;
         if(v_key < 1) then
         Insert into mobile_app(matric_no,device_id,status) values (p_matric,p_token,'1');
         else 
         update mobile_app set device_id = p_token where matric_no = p_matric;
         end if;
     end if; 
end;

/
--------------------------------------------------------
--  DDL for Procedure LOGOUTMOBILE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."LOGOUTMOBILE" (p_matric in varchar2,p_res out varchar2)
as
v_count number;
begin
DELETE FROM MOBILE_APP WHERE MATRIC_NO = p_matric;
p_res := 'OK';

commit;
end;

/
--------------------------------------------------------
--  DDL for Procedure SELCOUNTCOURSE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."SELCOUNTCOURSE" (p_course in varchar2,p_res out number)
as
begin
select count(*) into p_res from course where course_code =  p_course;
end;

/
--------------------------------------------------------
--  DDL for Procedure SELCOUNTRESULT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."SELCOUNTRESULT" (p_matric in varchar,p_sesi in varchar,p_year in varchar,p_sem in varchar,p_res out number)
as 
begin
select count(*) into p_res from results r inner join semester s on r.semester_id = s.semester_id and s.SESSIONS = p_sesi and s.SEMESTER = p_sem and s.YEARS = p_year
where r.MATRIC_NO = p_matric;
end;

/
--------------------------------------------------------
--  DDL for Procedure SELCOUNTSEMESTER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."SELCOUNTSEMESTER" (p_year in varchar2,p_sessions in varchar2,p_sem in varchar2,p_res out number)
as
begin
select count(*) into p_res from semester where years = p_year and SEMESTER = p_sem and SESSIONS = p_sessions;
end
;

/
--------------------------------------------------------
--  DDL for Procedure SELCOUNTSTUDENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."SELCOUNTSTUDENT" (p_matric in varchar2,p_res out number)
as
begin
select count(*) into p_res from student where MATRIC_NO = p_matric;
end
;

/
--------------------------------------------------------
--  DDL for Procedure SELCOURCE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."SELCOURCE" (p_code in varchar2,p_res out varchar2)
as
begin
select COURSE_ID into p_res from COURSE where COURSE_CODE = p_code;
end
;

/
--------------------------------------------------------
--  DDL for Procedure SELSEMESTER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."SELSEMESTER" (p_year in varchar2,p_sessions in varchar2,p_sem in varchar2,p_res out varchar2)
as
begin
select SEMESTER_ID into p_res from semester where years = p_year and SEMESTER = p_sem and SESSIONS = p_sessions;
end
;

/
--------------------------------------------------------
--  DDL for Procedure SENTNOTIMULTI
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SHAY"."SENTNOTIMULTI" (p_sessions in varchar2,p_record out SYS_REFCURSOR)
as
begin
open p_record for 
select s.matric_no,m.device_id,r.gpa from results r 
inner join student s on s.matric_no = r.matric_no
inner join mobile_app m on m.matric_no = s.matric_no
inner join semester se on r.SEMESTER_ID = se.SEMESTER_ID and se.SESSIONS = p_sessions;
end;

/
--------------------------------------------------------
--  Constraints for Table TBL_AUDIT_STUDENT
--------------------------------------------------------

  ALTER TABLE "SHAY"."TBL_AUDIT_STUDENT" MODIFY ("AUDIT_ID" NOT NULL ENABLE);
 
  ALTER TABLE "SHAY"."TBL_AUDIT_STUDENT" ADD CONSTRAINT "TBL_AUDIT_STUDENT_PK" PRIMARY KEY ("AUDIT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SEMESTER
--------------------------------------------------------

  ALTER TABLE "SHAY"."SEMESTER" ADD CONSTRAINT "SEMESTER_PK" PRIMARY KEY ("SEMESTER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."SEMESTER" MODIFY ("SEMESTER_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RESULTS
--------------------------------------------------------

  ALTER TABLE "SHAY"."RESULTS" ADD CONSTRAINT "RESULTS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."RESULTS" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "SHAY"."RESULTS" MODIFY ("CREATEBY" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOBILE_APP
--------------------------------------------------------

  ALTER TABLE "SHAY"."MOBILE_APP" ADD CONSTRAINT "MOBILE_APP_PK" PRIMARY KEY ("MOBILE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."MOBILE_APP" MODIFY ("MOBILE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SUMMARY
--------------------------------------------------------

  ALTER TABLE "SHAY"."SUMMARY" ADD CONSTRAINT "SUMMARY_PK" PRIMARY KEY ("SUMMARY_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."SUMMARY" MODIFY ("SUMMARY_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TBL_AUDIT_STAFF
--------------------------------------------------------

  ALTER TABLE "SHAY"."TBL_AUDIT_STAFF" MODIFY ("AUDIT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table STAFF
--------------------------------------------------------

  ALTER TABLE "SHAY"."STAFF" ADD CONSTRAINT "STAFF_PK" PRIMARY KEY ("STAFF_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."STAFF" MODIFY ("STAFF_NO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table STUDENT
--------------------------------------------------------

  ALTER TABLE "SHAY"."STUDENT" ADD CONSTRAINT "STUDENT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."STUDENT" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table COURSE
--------------------------------------------------------

  ALTER TABLE "SHAY"."COURSE" ADD CONSTRAINT "COURSE_PK" PRIMARY KEY ("COURSE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."COURSE" MODIFY ("COURSE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESSAGENOTI
--------------------------------------------------------

  ALTER TABLE "SHAY"."MESSAGENOTI" ADD CONSTRAINT "MESSAGENOTI_PK" PRIMARY KEY ("MESSAGE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SHAY"."MESSAGENOTI" MODIFY ("MESSAGE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TBL_AUDIT_RESULTS
--------------------------------------------------------

  ALTER TABLE "SHAY"."TBL_AUDIT_RESULTS" MODIFY ("AUDIT_ID" NOT NULL ENABLE);
