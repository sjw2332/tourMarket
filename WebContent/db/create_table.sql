SQL> conn /as sysdba
Connected.
SQL> create user travel identified by 1234;
User created.
SQL> grant connect,resource to travel;
Grant succeeded.
-----------------------------------------------------------------------------------------------------------------------------
CREATE TABLE CUSTOMER  -- 고객테이블
(
         CUSTOMER_ID                      VARCHAR2(30)       PRIMARY KEY                           -- 아이디
       , CUSTOMER_NAME               VARCHAR2(30)       NOT NULL                                 -- 이름
       , CUSTOMER_NICKNAME       VARCHAR2(30)       NOT NULL                                 -- 닉네임
       , PASSWD                                VARCHAR2(50)       NOT NULL                                 -- 비밀번호
       , EMAIL                                      VARCHAR2(100)    NOT NULL                                  -- 이메일
       , TEL                                         VARCHAR2(30)                                                          -- 연락처
       , ADDRESS                              VARCHAR2(100)     NOT NULL                                 -- 주소
);
CREATE TABLE BASKET -- 장바구니테이블
(
        BASKET_ID             NUMBER(5)              PRIMARY KEY                          -- 장바구니번호
       , ITEM_ID                  VARCHAR2(6)                                                               -- 상품코드 FK
       , CUSTOMER_ID     VARCHAR2(30)                                                     -- 회원아이디 FK
       , CNT                        CHAR(6)               NOT NULL                                -- 상품개수
       , REGDATE              DATE                    DEFAULT         SYSDATE       -- 등록날짜
       , FOREIGN KEY(ITEM_ID) REFERENCES ITEM(ITEM_ID) ON DELETE CASCADE
      , FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE
);
CREATE TABLE PURCHASE  -- 구매테이블 
(
         
        PURCHASE_ID       NUMBER(5)              PRIMARY KEY                          -- 장바구니번호
       , ITEM_ID                   VARCHAR2(6)                                                                -- 상품코드 FK
       , CUSTOMER_ID     VARCHAR2(30)                                                     -- 회원아이디 FK
       , CNT                        CHAR(6)               NOT NULL                                -- 상품개수
       , REGDATE              DATE                    DEFAULT         SYSDATE       -- 등록날짜
       , FOREIGN KEY(ITEM_ID) REFERENCES ITEM(ITEM_ID) ON DELETE CASCADE
      , FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE
);
CREATE TABLE ITEM  -- 상품테이블
(
        ITEM_ID                                      VARCHAR2(6)                  PRIMARY KEY                -- 상품코드(사진이름(~jpg))
      , ITEM_NAME                                VARCHAR2(100)    NOT NULL                      -- 상품명
      , ITEM_CONT                               VARCHAR2(1000)                                          -- 상품설명
      , CATEGORY_CODE_ID             VARCHAR2(6)                                                          -- 카테고리번호 FK
      , PRICE                                         NUMBER(20,0)       NOT NULL                      -- 가격   
      , REGDATE                                  DATE                                                              -- 날짜
      , FOREIGN KEY(CATEGORY_CODE_ID) REFERENCES CATEGORY_CODE(CATEGORY_CODE_ID)  ON DELETE CASCADE
);
CREATE TABLE CATEGORY_CODE  -- 카테고리테이블
(
       CATEGORY_CODE_ID           VARCHAR2(6)                 PRIMARY KEY               -- 카테고리번호 
     , CATEGORY_CODE_NAME     VARCHAR2(30)    NOT NULL                      -- 카테고리이름
);