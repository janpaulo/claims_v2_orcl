--------------------------------------------------------
--  File created - Monday-November-04-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence ACCOUNT_ROLE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CLAIMS"."ACCOUNT_ROLE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence CLAIMS_TBL_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CLAIMS"."CLAIMS_TBL_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence USER_PROFILE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CLAIMS"."USER_PROFILE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table ACCOUNT_ROLE
--------------------------------------------------------

  CREATE TABLE "CLAIMS"."ACCOUNT_ROLE" 
   (	"ROLE_ID" VARCHAR2(20 BYTE), 
	"ROLE_NAME" VARCHAR2(100 BYTE), 
	"ROLE_DESC" VARCHAR2(200 BYTE), 
	"STATUS" VARCHAR2(20 BYTE), 
	"IS_ACTIVE" VARCHAR2(20 BYTE), 
	"DATE_CREATED" DATE DEFAULT sysdate
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);
--------------------------------------------------------
--  DDL for Table CLAIMS_TBL
--------------------------------------------------------

  CREATE TABLE "CLAIMS"."CLAIMS_TBL" 
   (	"CLAIM_ID" VARCHAR2(20 BYTE), 
	"CLAIM_QID" VARCHAR2(20 BYTE), 
	"ACCRE_NO" VARCHAR2(20 BYTE), 
	"DATE_CREATED" DATE DEFAULT SYSDATE, 
	"JSON" CLOB, 
	"SERIES_NO" VARCHAR2(50 BYTE), 
	"MEM_PIN" VARCHAR2(20 BYTE), 
	"DATE_ADMITED" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
 LOB ("JSON") STORE AS SECUREFILE (ENABLE STORAGE IN ROW 4000 CHUNK 8192
  NOCACHE LOGGING  NOCOMPRESS  KEEP_DUPLICATES ) ;
--------------------------------------------------------
--  DDL for Table ESOA_TBL
--------------------------------------------------------

  CREATE TABLE "CLAIMS"."ESOA_TBL" 
   (	"ESOA_ID" VARCHAR2(20 BYTE), 
	"ESOA_QID" VARCHAR2(20 BYTE), 
	"ACCRE_NO" VARCHAR2(20 BYTE), 
	"PROF_FEE" VARCHAR2(20 BYTE), 
	"TOTAL_AMOUNT" VARCHAR2(20 BYTE), 
	"SUM_PHILHEALTH_AMOUNT" VARCHAR2(20 BYTE), 
	"PROF_PHILHEALTH_AMOUNT" VARCHAR2(20 BYTE), 
	"JSON" LONG, 
	"DATE_CREATED" DATE DEFAULT sysdate
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING;
--------------------------------------------------------
--  DDL for Table USERS_ACCOUNT
--------------------------------------------------------

  CREATE TABLE "CLAIMS"."USERS_ACCOUNT" 
   (	"USER_ID" VARCHAR2(20 BYTE), 
	"USERNAME" VARCHAR2(200 BYTE), 
	"PASSWORD" VARCHAR2(200 BYTE), 
	"EMAIL" VARCHAR2(200 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);
--------------------------------------------------------
--  DDL for Table USER_PROFILE
--------------------------------------------------------

  CREATE TABLE "CLAIMS"."USER_PROFILE" 
   (	"USER_ID" VARCHAR2(20 BYTE), 
	"ACCRE_NUM" VARCHAR2(20 BYTE), 
	"HCI_NAME" VARCHAR2(100 BYTE), 
	"HCI_DESC" VARCHAR2(200 BYTE), 
	"DATE_CREATED" DATE, 
	"HCI_CYPERKEY" VARCHAR2(200 BYTE), 
	"ROLE_ID" VARCHAR2(20 BYTE), 
	"QID" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING;
REM INSERTING into CLAIMS.ACCOUNT_ROLE
SET DEFINE OFF;
Insert into CLAIMS.ACCOUNT_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESC,STATUS,IS_ACTIVE,DATE_CREATED) values ('1','admin','admin role','active','1',to_date('24-OCT-24','DD-MON-RR'));
REM INSERTING into CLAIMS.CLAIMS_TBL
SET DEFINE OFF;
REM INSERTING into CLAIMS.ESOA_TBL
SET DEFINE OFF;
REM INSERTING into CLAIMS.USERS_ACCOUNT
SET DEFINE OFF;
Insert into CLAIMS.USERS_ACCOUNT (USER_ID,USERNAME,PASSWORD,EMAIL) values ('12345678','test','BCBAC47C6B7C244ACEA485E5791FB23DAF096220E42D1BF68AEDBAE3794B4A63',null);
REM INSERTING into CLAIMS.USER_PROFILE
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index ACCOUNT_ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CLAIMS"."ACCOUNT_ROLE_PK" ON "CLAIMS"."ACCOUNT_ROLE" ("ROLE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);
--------------------------------------------------------
--  DDL for Index USERS_ACCOUNT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CLAIMS"."USERS_ACCOUNT_PK" ON "CLAIMS"."USERS_ACCOUNT" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);
--------------------------------------------------------
--  DDL for Index USER_PROFILE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CLAIMS"."USER_PROFILE_PK" ON "CLAIMS"."USER_PROFILE" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS ;
--------------------------------------------------------
--  DDL for Trigger ACCOUNT_ROLE_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "CLAIMS"."ACCOUNT_ROLE_TRG" 
BEFORE INSERT ON ACCOUNT_ROLE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ROLE_ID IS NULL THEN
      SELECT ACCOUNT_ROLE_SEQ.NEXTVAL INTO :NEW.ROLE_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "CLAIMS"."ACCOUNT_ROLE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CLAIMS_TBL_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "CLAIMS"."CLAIMS_TBL_TRG" 
BEFORE INSERT ON CLAIMS_TBL 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.CLAIM_ID IS NULL THEN
      SELECT CLAIMS_TBL_SEQ.NEXTVAL INTO :NEW.CLAIM_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "CLAIMS"."CLAIMS_TBL_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger USER_PROFILE_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "CLAIMS"."USER_PROFILE_TRG" 
BEFORE INSERT ON USER_PROFILE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.QID IS NULL THEN
      SELECT USER_PROFILE_SEQ.NEXTVAL INTO :NEW.QID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "CLAIMS"."USER_PROFILE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Package AUTH_PKG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE "CLAIMS"."AUTH_PKG" AS
    -- Define a record type for user details
    TYPE user_details_record IS RECORD (
        user_id   USERS_ACCOUNT.USER_ID%TYPE,
        username  USERS_ACCOUNT.USERNAME%TYPE,
        email     USERS_ACCOUNT.EMAIL%TYPE,
        auth_success BOOLEAN
    );

    -- Function to authenticate a user and return user details if successful
FUNCTION AUTHENTICATE_USER (
    p_username IN VARCHAR2,
    p_password IN VARCHAR2
) RETURN SYS_REFCURSOR;

    -- Procedure to register a new user
    PROCEDURE REGISTER_USER (
        p_userid IN VARCHAR2,
        p_username IN VARCHAR2,
        p_password IN VARCHAR2,
        p_email    IN VARCHAR2
    );

    -- Procedure to update user password
    PROCEDURE UPDATE_PASSWORD (
        p_username IN VARCHAR2,
        p_new_password IN VARCHAR2
    );
END AUTH_PKG;

/
--------------------------------------------------------
--  DDL for Package CLAIMS_PKG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE "CLAIMS"."CLAIMS_PKG" IS

  -- USER_PROFILE table
  -- INSERT
  PROCEDURE insert_user_profile (
    p_user_id        IN VARCHAR2,
    p_accre_num      IN VARCHAR2,
    p_hci_name       IN VARCHAR2,
    p_hci_desc       IN VARCHAR2,
    p_date_created   IN DATE,
    p_hci_cyperkey   IN VARCHAR2,
    p_role_id        IN VARCHAR2,
    p_qid            IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );

  -- SELECT
   FUNCTION get_user_profile(p_user_id IN VARCHAR2) RETURN SYS_REFCURSOR;

  -- UPDATE
  PROCEDURE update_user_profile (
    p_user_id        IN VARCHAR2,
    p_accre_num      IN VARCHAR2,
    p_hci_name       IN VARCHAR2,
    p_hci_desc       IN VARCHAR2,
    p_date_created   IN DATE,
    p_hci_cyperkey   IN VARCHAR2,
    p_role_id        IN VARCHAR2,
    p_qid            IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );

  -- DELETE
   PROCEDURE delete_user_profile (
    p_user_id        IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );


  -- USERS_ACCOUNT table
  -- INSERT
  PROCEDURE insert_users_account (
    p_user_id        IN VARCHAR2,
    p_username       IN VARCHAR2,
    p_password       IN VARCHAR2,
    p_email          IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) ;

  -- SELECT
FUNCTION get_users_account(p_user_id IN VARCHAR2) RETURN SYS_REFCURSOR ;

  -- UPDATE
 PROCEDURE update_users_account (
    p_user_id        IN VARCHAR2,
    p_username       IN VARCHAR2,
    p_password       IN VARCHAR2,
    p_email          IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );

  -- DELETE
  PROCEDURE delete_users_account (
    p_user_id        IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );


  -- ACCOUNT_ROLE table
  -- INSERT
  PROCEDURE insert_account_role (
    p_role_id        IN VARCHAR2,
    p_role_name      IN VARCHAR2,
    p_role_desc      IN VARCHAR2,
    p_status         IN VARCHAR2,
    p_is_active      IN VARCHAR2,
    p_date_created   IN DATE,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );

  -- SELECT
 FUNCTION get_account_roles(p_role_id IN VARCHAR2) RETURN SYS_REFCURSOR;
  -- UPDATE
  PROCEDURE update_account_role (
    p_role_id        IN VARCHAR2,
    p_role_name      IN VARCHAR2,
    p_role_desc      IN VARCHAR2,
    p_status         IN VARCHAR2,
    p_is_active      IN VARCHAR2,
    p_date_created   IN DATE,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );

  -- DELETE
  PROCEDURE delete_account_role (
    p_role_id        IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  );
  
---- CLAIMS --------------------------------------------------
  
  FUNCTION get_claims(p_claim_id IN VARCHAR2) RETURN SYS_REFCURSOR;
  
  PROCEDURE insert_claim(
    p_claim_qid   IN VARCHAR2,
    p_accre_no    IN VARCHAR2,
    p_json         IN CLOB,
    p_series_no    IN VARCHAR2,
    p_mem_pin      IN VARCHAR2,
    p_date_admited IN DATE,
    p_code         OUT VARCHAR2,
    p_message      OUT VARCHAR2
  );
  
  PROCEDURE update_claim(
    p_claim_id     IN VARCHAR2,
    p_claim_qid    IN VARCHAR2,
    p_accre_no     IN VARCHAR2,
    p_json          IN CLOB,
    p_series_no     IN VARCHAR2,
    p_mem_pin       IN VARCHAR2,
    p_date_admited  IN DATE,
    p_code          OUT VARCHAR2,
    p_message       OUT VARCHAR2
  );
  

END claims_pkg;

/
--------------------------------------------------------
--  DDL for Package ESOA_PKG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE "CLAIMS"."ESOA_PKG" AS
  -- Select procedure to retrieve records
  FUNCTION get_esoa(p_esoa_id IN VARCHAR2) RETURN SYS_REFCURSOR;

  -- Insert procedure to add records
  PROCEDURE insert_esoa(
    p_esoa_qid                IN VARCHAR2,
    p_accre_no                IN VARCHAR2,
    p_prof_fee                IN NUMBER,
    p_total_amount            IN NUMBER,
    p_sum_philhealth_amount   IN NUMBER,
    p_prof_philhealth_amount   IN NUMBER,
    p_json                    IN CLOB,
    p_code                    OUT VARCHAR2,
    p_message                 OUT VARCHAR2
  );

  -- Update procedure to modify records
  PROCEDURE update_esoa(
    p_esoa_id                 IN VARCHAR2,
    p_esoa_qid                IN VARCHAR2,
    p_accre_no                IN VARCHAR2,
    p_prof_fee                IN NUMBER,
    p_total_amount            IN NUMBER,
    p_sum_philhealth_amount   IN NUMBER,
    p_prof_philhealth_amount   IN NUMBER,
    p_json                    IN CLOB,
    p_code                    OUT VARCHAR2,
    p_message                 OUT VARCHAR2
  );
END esoa_pkg;

/
--------------------------------------------------------
--  DDL for Package Body AUTH_PKG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY "CLAIMS"."AUTH_PKG" AS

    -- Helper function to hash passwords
    FUNCTION HASH_PASSWORD(p_password IN VARCHAR2) RETURN VARCHAR2 IS
    BEGIN
        RETURN RAWTOHEX(DBMS_CRYPTO.HASH(UTL_I18N.STRING_TO_RAW(p_password, 'AL32UTF8'), DBMS_CRYPTO.HASH_SH256));
    END;

    -- Procedure to register a new user
    PROCEDURE REGISTER_USER (
        p_userid IN VARCHAR2,
        p_username IN VARCHAR2,
        p_password IN VARCHAR2,
        p_email    IN VARCHAR2
    ) IS
        v_hashed_password VARCHAR2(64);
    BEGIN
        -- Hash the password
        v_hashed_password := HASH_PASSWORD(p_password);

        -- Insert the new user
        INSERT INTO USERS_ACCOUNT (USER_ID, USERNAME, PASSWORD, EMAIL)
        VALUES (p_userid, p_username, v_hashed_password, p_email);

        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE_APPLICATION_ERROR(-20001, 'Username or Email already exists');
    END REGISTER_USER;

    -- Function to authenticate a user and return user details if successful
    -- Function to authenticate a user
    FUNCTION AUTHENTICATE_USER (
        p_username IN VARCHAR2,
        p_password IN VARCHAR2
    ) RETURN SYS_REFCURSOR IS
        v_hashed_password VARCHAR2(64);
        v_result SYS_REFCURSOR;
    BEGIN
        -- Hash the input password for comparison
        v_hashed_password := HASH_PASSWORD(p_password);

        -- Open a cursor for fetching user details
        OPEN v_result FOR
            SELECT USER_ID, USERNAME, EMAIL
            FROM USERS_ACCOUNT
            WHERE UPPER(USERNAME) = UPPER(p_username)
              AND PASSWORD = v_hashed_password;

        -- Return the result cursor
        RETURN v_result;
    END AUTHENTICATE_USER;



    -- Procedure to update user password
    PROCEDURE UPDATE_PASSWORD (
        p_username IN VARCHAR2,
        p_new_password IN VARCHAR2
    ) IS
        v_hashed_password VARCHAR2(64);
    BEGIN
        -- Hash the new password
        v_hashed_password := HASH_PASSWORD(p_new_password);

        -- Update the user's password
        UPDATE USERS_ACCOUNT
        SET PASSWORD = v_hashed_password
        WHERE USERNAME = p_username;

        COMMIT;
    END UPDATE_PASSWORD;

END AUTH_PKG;

/
--------------------------------------------------------
--  DDL for Package Body CLAIMS_PKG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY "CLAIMS"."CLAIMS_PKG" IS

  -- USER_PROFILE table procedures

  PROCEDURE insert_user_profile (
    p_user_id        IN VARCHAR2,
    p_accre_num      IN VARCHAR2,
    p_hci_name       IN VARCHAR2,
    p_hci_desc       IN VARCHAR2,
    p_date_created   IN DATE,
    p_hci_cyperkey   IN VARCHAR2,
    p_role_id        IN VARCHAR2,
    p_qid            IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    INSERT INTO claims.user_profile (
      user_id, accre_num, hci_name, hci_desc, date_created,
      hci_cyperkey, role_id, qid
    ) VALUES (
      p_user_id, p_accre_num, p_hci_name, p_hci_desc, p_date_created,
      p_hci_cyperkey, p_role_id, p_qid
    );

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_1_0_00000';
      p_message := 'User profile has been successfully saved.';
    END IF;
    
  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_1_0_00001';
      p_message := 'An error was encountered while saving user profile - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END insert_user_profile;

  FUNCTION get_user_profile(p_user_id IN VARCHAR2) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT accre_num, hci_name, hci_desc, date_created, hci_cyperkey, role_id, qid
      FROM claims.user_profile
      WHERE user_id = p_user_id;
    
    RETURN v_result;
  END get_user_profile;

  PROCEDURE update_user_profile (
    p_user_id        IN VARCHAR2,
    p_accre_num      IN VARCHAR2,
    p_hci_name       IN VARCHAR2,
    p_hci_desc       IN VARCHAR2,
    p_date_created   IN DATE,
    p_hci_cyperkey   IN VARCHAR2,
    p_role_id        IN VARCHAR2,
    p_qid            IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    UPDATE claims.user_profile
    SET accre_num = p_accre_num,
        hci_name = p_hci_name,
        hci_desc = p_hci_desc,
        date_created = p_date_created,
        hci_cyperkey = p_hci_cyperkey,
        role_id = p_role_id,
        qid = p_qid
    WHERE user_id = p_user_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_1_0_00002';
      p_message := 'User profile has been successfully updated.';
    ELSE
      p_code := 'RM01_1_0_00003';
      p_message := 'No changes made; user profile not found.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_1_0_00004';
      p_message := 'An error was encountered while updating user profile - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END update_user_profile;

  PROCEDURE delete_user_profile (
    p_user_id        IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    DELETE FROM claims.user_profile
    WHERE user_id = p_user_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_1_0_00005';
      p_message := 'User profile has been successfully deleted.';
    ELSE
      p_code := 'RM01_1_0_00006';
      p_message := 'No user profile found to delete.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_1_0_00007';
      p_message := 'An error was encountered while deleting user profile - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END delete_user_profile;


  -- USERS_ACCOUNT table procedures

  PROCEDURE insert_users_account (
    p_user_id        IN VARCHAR2,
    p_username       IN VARCHAR2,
    p_password       IN VARCHAR2,
    p_email          IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    INSERT INTO claims.users_account (
      user_id, username, password, email
    ) VALUES (
      p_user_id, p_username, p_password, p_email
    );

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_2_0_00000';
      p_message := 'User account has been successfully saved.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_2_0_00001';
      p_message := 'An error was encountered while saving user account - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END insert_users_account;

  FUNCTION get_users_account(p_user_id IN VARCHAR2) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT username, password, email
      FROM claims.users_account
      WHERE (p_user_id IS NULL OR user_id = p_user_id);
    
    RETURN v_result;
  END get_users_account;

  PROCEDURE update_users_account (
    p_user_id        IN VARCHAR2,
    p_username       IN VARCHAR2,
    p_password       IN VARCHAR2,
    p_email          IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    UPDATE claims.users_account
    SET username = p_username,
        password = p_password,
        email = p_email
    WHERE user_id = p_user_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_2_0_00002';
      p_message := 'User account has been successfully updated.';
    ELSE
      p_code := 'RM01_2_0_00003';
      p_message := 'No changes made; user account not found.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_2_0_00004';
      p_message := 'An error was encountered while updating user account - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END update_users_account;

  PROCEDURE delete_users_account (
    p_user_id        IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    DELETE FROM claims.users_account
    WHERE user_id = p_user_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_2_0_00005';
      p_message := 'User account has been successfully deleted.';
    ELSE
      p_code := 'RM01_2_0_00006';
      p_message := 'No user account found to delete.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_2_0_00007';
      p_message := 'An error was encountered while deleting user account - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END delete_users_account;


  -- ACCOUNT_ROLE table procedures

  PROCEDURE insert_account_role (
    p_role_id        IN VARCHAR2,
    p_role_name      IN VARCHAR2,
    p_role_desc      IN VARCHAR2,
    p_status         IN VARCHAR2,
    p_is_active      IN VARCHAR2,
    p_date_created   IN DATE,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    INSERT INTO claims.account_role (
      role_id, role_name, role_desc, status, is_active, date_created
    ) VALUES (
      p_role_id, p_role_name, p_role_desc, p_status, p_is_active, p_date_created
    );

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_3_0_00000';
      p_message := 'Account role has been successfully saved.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_3_0_00001';
      p_message := 'An error was encountered while saving account role - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END insert_account_role;

  FUNCTION get_account_roles(p_role_id IN VARCHAR2) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT ROLE_ID, ROLE_NAME, ROLE_DESC, STATUS, IS_ACTIVE, DATE_CREATED
      FROM claims.account_role
      WHERE (p_role_id IS NULL OR role_id = p_role_id)
      ORDER BY ROLE_ID ASC;
    
    RETURN v_result;
  END get_account_roles;

  PROCEDURE update_account_role (
    p_role_id        IN VARCHAR2,
    p_role_name      IN VARCHAR2,
    p_role_desc      IN VARCHAR2,
    p_status         IN VARCHAR2,
    p_is_active      IN VARCHAR2,
    p_date_created   IN DATE,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    UPDATE claims.account_role
    SET role_name = p_role_name,
        role_desc = p_role_desc,
        status = p_status,
        is_active = p_is_active,
        date_created = p_date_created
    WHERE role_id = p_role_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_3_0_00002';
      p_message := 'Account role has been successfully updated.';
    ELSE
      p_code := 'RM01_3_0_00003';
      p_message := 'No changes made; account role not found.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_3_0_00004';
      p_message := 'An error was encountered while updating account role - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END update_account_role;

  PROCEDURE delete_account_role (
    p_role_id        IN VARCHAR2,
    p_code           OUT VARCHAR2,
    p_message        OUT VARCHAR2
  ) IS
  BEGIN
    DELETE FROM claims.account_role
    WHERE role_id = p_role_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_3_0_00005';
      p_message := 'Account role has been successfully deleted.';
    ELSE
      p_code := 'RM01_3_0_00006';
      p_message := 'No account role found to delete.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_3_0_00007';
      p_message := 'An error was encountered while deleting account role - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END delete_account_role;

---- CLAIMS --------------------------------------------------

  FUNCTION get_claims(p_claim_id IN VARCHAR2) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT claim_id, claim_qid, accre_no, date_created, json, series_no, mem_pin, date_admited
      FROM claims.CLAIMS_TBL
      WHERE (p_claim_id IS NULL OR claim_id = p_claim_id);
    
    RETURN v_result;

  END get_claims;
  
  PROCEDURE insert_claim(
    p_claim_qid   IN VARCHAR2,
    p_accre_no    IN VARCHAR2,
    p_json         IN CLOB,
    p_series_no    IN VARCHAR2,
    p_mem_pin      IN VARCHAR2,
    p_date_admited IN DATE,
    p_code         OUT VARCHAR2,
    p_message      OUT VARCHAR2
  ) IS
  BEGIN
    INSERT INTO claims.claims_tbl (
      claim_qid,
      accre_no,
      date_created,
      json,
      series_no,
      mem_pin, 
      date_admited
    ) VALUES (
      p_claim_qid,
      p_accre_no,
      sysdate,
      p_json,
      p_series_no,
      p_mem_pin,
      p_date_admited
    );

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_2_0_00000';
      p_message := 'Claim has been successfully saved.';
    ELSE
      p_code := 'RM01_2_0_00002';
      p_message := 'No rows inserted.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_2_0_00001';
      p_message := 'An error was encountered while saving the claim - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END insert_claim;
  
   PROCEDURE update_claim(
    p_claim_id     IN VARCHAR2,
    p_claim_qid    IN VARCHAR2,
    p_accre_no     IN VARCHAR2,
    p_json          IN CLOB,
    p_series_no     IN VARCHAR2,
    p_mem_pin       IN VARCHAR2,
    p_date_admited  IN DATE,
    p_code          OUT VARCHAR2,
    p_message       OUT VARCHAR2
  ) IS
  BEGIN
    UPDATE claims.claims_tbl
    SET claim_qid = p_claim_qid,
        accre_no = p_accre_no,
        date_created = sysdate,
        json = p_json,
        series_no = p_series_no,
        mem_pin = p_mem_pin,
        date_admited = p_date_admited
    WHERE claim_id = p_claim_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_3_0_00000';
      p_message := 'Claim has been successfully updated.';
    ELSE
      p_code := 'RM01_3_0_00002';
      p_message := 'No rows updated. Claim ID may not exist.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_3_0_00001';
      p_message := 'An error was encountered while updating the claim - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END update_claim;

END claims_pkg;

/
--------------------------------------------------------
--  DDL for Package Body ESOA_PKG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY "CLAIMS"."ESOA_PKG" AS

  -- Function to select records
  FUNCTION get_esoa(p_esoa_id IN VARCHAR2) RETURN SYS_REFCURSOR IS
    v_cursor SYS_REFCURSOR;
  BEGIN
    OPEN v_cursor FOR
      SELECT ESOA_ID, ESOA_QID, accre_no, prof_fee, total_amount,
             sum_philhealth_amount, prof_philhealth_amount, json, date_created
      FROM claims.ESOA_TBL
      WHERE  (p_esoa_id IS NULL OR ESOA_ID = p_esoa_id);
    RETURN v_cursor;
  END get_esoa;

  -- Procedure to insert records
  PROCEDURE insert_esoa(
    p_esoa_qid                IN VARCHAR2,
    p_accre_no                IN VARCHAR2,
    p_prof_fee                IN NUMBER,
    p_total_amount            IN NUMBER,
    p_sum_philhealth_amount   IN NUMBER,
    p_prof_philhealth_amount   IN NUMBER,
    p_json                    IN CLOB,
    p_code                    OUT VARCHAR2,
    p_message                 OUT VARCHAR2
  ) IS
  BEGIN
    INSERT INTO ESOA_TBL (
      ESOA_QID, accre_no, prof_fee, total_amount,
      sum_philhealth_amount, prof_philhealth_amount, json, date_created
    ) VALUES (
      p_esoa_qid, p_accre_no, p_prof_fee, p_total_amount,
      p_sum_philhealth_amount, p_prof_philhealth_amount, p_json, SYSDATE
    );

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_4_0_00000';
      p_message := 'ESO record has been successfully saved.';
    ELSE
      p_code := 'RM01_4_0_00002';
      p_message := 'No rows inserted.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_4_0_00001';
      p_message := 'An error was encountered while saving ESO record - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END insert_esoa;

  -- Procedure to update records
  PROCEDURE update_esoa(
    p_esoa_id                 IN VARCHAR2,
    p_esoa_qid                IN VARCHAR2,
    p_accre_no                IN VARCHAR2,
    p_prof_fee                IN NUMBER,
    p_total_amount            IN NUMBER,
    p_sum_philhealth_amount   IN NUMBER,
    p_prof_philhealth_amount   IN NUMBER,
    p_json                    IN CLOB,
    p_code                    OUT VARCHAR2,
    p_message                 OUT VARCHAR2
  ) IS
  BEGIN
    UPDATE claims.ESOA_TBL
    SET ESOA_QID = p_esoa_qid,
        accre_no = p_accre_no,
        prof_fee = p_prof_fee,
        total_amount = p_total_amount,
        sum_philhealth_amount = p_sum_philhealth_amount,
        prof_philhealth_amount = p_prof_philhealth_amount,
        json = p_json
    WHERE ESOA_ID = p_esoa_id;

    IF (SQL%ROWCOUNT > 0) THEN
      p_code := 'RM01_4_0_00000';
      p_message := 'ESO record has been successfully updated.';
    ELSE
      p_code := 'RM01_4_0_00002';
      p_message := 'No rows updated. ESO ID may not exist.';
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      p_code := 'RM01_4_0_00001';
      p_message := 'An error was encountered while updating ESO record - ' || SQLCODE || ' - ERROR - ' || SQLERRM;
  END update_esoa;

END esoa_pkg;

/
--------------------------------------------------------
--  Constraints for Table ACCOUNT_ROLE
--------------------------------------------------------

  ALTER TABLE "CLAIMS"."ACCOUNT_ROLE" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "CLAIMS"."ACCOUNT_ROLE" ADD CONSTRAINT "ACCOUNT_ROLE_PK" PRIMARY KEY ("ROLE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS_ACCOUNT
--------------------------------------------------------

  ALTER TABLE "CLAIMS"."USERS_ACCOUNT" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "CLAIMS"."USERS_ACCOUNT" ADD CONSTRAINT "USERS_ACCOUNT_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) ENABLE;
--------------------------------------------------------
--  Constraints for Table USER_PROFILE
--------------------------------------------------------

  ALTER TABLE "CLAIMS"."USER_PROFILE" MODIFY ("ACCRE_NUM" NOT NULL ENABLE);
  ALTER TABLE "CLAIMS"."USER_PROFILE" MODIFY ("HCI_NAME" NOT NULL ENABLE);
  ALTER TABLE "CLAIMS"."USER_PROFILE" MODIFY ("HCI_CYPERKEY" NOT NULL ENABLE);
  ALTER TABLE "CLAIMS"."USER_PROFILE" MODIFY ("ROLE_ID" NOT NULL ENABLE);
  ALTER TABLE "CLAIMS"."USER_PROFILE" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "CLAIMS"."USER_PROFILE" ADD CONSTRAINT "USER_PROFILE_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USER_PROFILE
--------------------------------------------------------

  ALTER TABLE "CLAIMS"."USER_PROFILE" ADD CONSTRAINT "USER_PROFILE_FK1" FOREIGN KEY ("USER_ID")
	  REFERENCES "CLAIMS"."USERS_ACCOUNT" ("USER_ID") ENABLE;
  ALTER TABLE "CLAIMS"."USER_PROFILE" ADD CONSTRAINT "USER_PROFILE_FK2" FOREIGN KEY ("ROLE_ID")
	  REFERENCES "CLAIMS"."ACCOUNT_ROLE" ("ROLE_ID") ENABLE;
