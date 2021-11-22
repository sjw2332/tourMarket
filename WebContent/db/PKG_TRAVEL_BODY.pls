create or replace PACKAGE BODY PKG_TRAVEL AS

  PROCEDURE PROC_CATEGORY_LIST( O_CUR  OUT SYS_REFCURSOR ) AS
  BEGIN
    OPEN  O_CUR  FOR  
      SELECT      CATEGORY_CODE_ID, CATEGORY_CODE_NAME
        FROM      CATEGORY_CODE
        ORDER BY  CATEGORY_CODE_ID  ASC;
  END PROC_CATEGORY_LIST;

  PROCEDURE PROC_ITEM_LIST( 
         IN_CATEGORY_CODE_ID    IN   VARCHAR2,   
         
         IN_NOWPAGE    IN   NUMBER,                             -- 현재 페이지
         IN_PAGECOUNT  IN   NUMBER,                           -- 페이지당 자료수
         
         OUT_TOTALCOUNT OUT NUMBER,                   -- 전체 자료수
          
         O_CUR         OUT  SYS_REFCURSOR
    ) AS
      V_STARTNUM   NUMBER(8);
      V_ENDNUM       NUMBER(8);
  BEGIN
     -- 페이징을 위한 전체자료수
      SELECT    COUNT(ITEM_ID)
       INTO     OUT_TOTALCOUNT
       FROM     ITEM
       WHERE  CATEGORY_CODE_ID   =  IN_CATEGORY_CODE_ID ; 
       
    -- 가져올 자료의 시작번호, 끝번호
      V_STARTNUM  :=  (IN_NOWPAGE-1) * IN_PAGECOUNT + 1;
      V_ENDNUM    :=  IN_NOWPAGE * IN_PAGECOUNT; 
     
       IF( V_ENDNUM >= OUT_TOTALCOUNT  )  THEN
           V_ENDNUM := OUT_TOTALCOUNT; 
       END IF;
       
       -- DATA 가져오기
      OPEN O_CUR FOR
        SELECT  * FROM
        (
           SELECT
                ROW_NUMBER() OVER( ORDER BY ITEM_ID ) AS RN,
                ITEM_ID, CATEGORY_CODE_ID,
                ITEM_NAME, ITEM_CONT, 
                TO_CHAR(REGDATE,'YYYY-MM-DD HH24:MI:SS') REGDATE, 
                PRICE,
                BUYCOUNT 
            FROM
               ITEM
            WHERE 
               CATEGORY_CODE_ID   =  IN_CATEGORY_CODE_ID         
        ) T
        WHERE T.RN   BETWEEN   V_STARTNUM  AND   V_ENDNUM;
    
  END PROC_ITEM_LIST;

  PROCEDURE PROC_CATEGORY_VIEW( IN_CATEGORY_ID IN VARCHAR2, O_CATEGORY_ID OUT VARCHAR2, O_CATEGORY_NAME OUT VARCHAR2) AS
  BEGIN
    SELECT  CATEGORY_CODE_ID,   CATEGORY_CODE_NAME
      INTO   O_CATEGORY_ID, O_CATEGORY_NAME
      FROM   CATEGORY_CODE
      WHERE  CATEGORY_CODE_ID   =  IN_CATEGORY_ID;         
     
  END PROC_CATEGORY_VIEW; 

   PROCEDURE   PROC_CUSTOMER_JOIN(
    IN_CUSTOMER_ID  IN  VARCHAR2, 
    IN_CUSTOMER_NAME  IN  VARCHAR2, 
    IN_CUSTOMER_NICKNAME  IN  VARCHAR2, 
    IN_PASSWD  IN  VARCHAR2, 
    IN_EMAIL  IN  VARCHAR2, 
    IN_TEL  IN  VARCHAR2, 
    IN_ADDRESS  IN  VARCHAR2

) AS
  BEGIN
     INSERT INTO CUSTOMER VALUES (
        IN_CUSTOMER_ID, IN_CUSTOMER_NAME, IN_CUSTOMER_NICKNAME, IN_PASSWD,
        IN_EMAIL, IN_TEL, IN_ADDRESS
    );

  END PROC_CUSTOMER_JOIN;

  PROCEDURE   PROC_CUSTOMER_LOGIN
(
    IN_CUSTOMER_ID in VARCHAR2             --id     in
    ,  IN_PASSWD   in varchar2             --pwd    in
    ,  O_LOGINCHECK  out number            -- 1,2,0    out
) AS
        V_CHECK number(1) ;                --  0    1(아이디 존재여부)
        V_PASSWD VARCHAR2(50);

  BEGIN
    select count(*) into V_CHECK
   from CUSTOMER
   where CUSTOMER_ID = IN_CUSTOMER_ID;
   if  V_CHECK = 0 then
        O_LOGINCHECK := 1;          -- 입력한 아이디가 없는 경우
   else
      select PASSWD into V_PASSWD
      from CUSTOMER
      where  CUSTOMER_ID = IN_CUSTOMER_ID;      
      if IN_PASSWD = V_PASSWD then
         O_LOGINCHECK := 0;        -- 아이디 비번 모두 일치
      else
         O_LOGINCHECK := 2;        -- 비밀번호 mismatch
      end if;      
   end if;

  END PROC_CUSTOMER_LOGIN;
  
  PROCEDURE   PROC_CUSTOMER_UPDATE(
    IN_CUSTOMER_ID      IN  VARCHAR2,
    IN_CUSTOMER_NICKNAME  IN  VARCHAR2, 
    IN_PASSWD  IN  VARCHAR2, 
    IN_EMAIL  IN  VARCHAR2, 
    IN_TEL  IN  VARCHAR2, 
    IN_ADDRESS  IN  VARCHAR2

) AS
  BEGIN
   UPDATE CUSTOMER SET
   CUSTOMER_NICKNAME    =   IN_CUSTOMER_NICKNAME,
   PASSWD               =   IN_PASSWD,
   EMAIL                =   IN_EMAIL, 
   TEL                  =   IN_TEL,
   ADDRESS              =   IN_ADDRESS
   WHERE    CUSTOMER_ID =   IN_CUSTOMER_ID;
   
  END PROC_CUSTOMER_UPDATE;

  PROCEDURE PROC_ITEM_VIEW(IN_ITEM_ID IN NUMBER, O_CUR OUT SYS_REFCURSOR) AS
  BEGIN
  --ID로 조회 
    OPEN O_CUR FOR
    SELECT
        ITEM_ID,
        ITEM_NAME,
        ITEM_CONT,
        CATEGORY_CODE_ID,
        PRICE,
        REGDATE,
        BUYCOUNT
    FROM ITEM
    WHERE ITEM_ID = IN_ITEM_ID;
  END PROC_ITEM_VIEW;

  PROCEDURE PROC_BASKET_ADD(IN_ITEM_ID IN VARCHAR2, IN_CNT  IN VARCHAR2, IN_CUSTOMER_ID IN VARCHAR2) AS
  BEGIN
  INSERT INTO BASKET(BASKET_ID, ITEM_ID, CNT, CUSTOMER_ID)
  VALUES((SELECT NVL(MAX(BASKET_ID), 0) + 1 FROM BASKET), IN_ITEM_ID, IN_CNT, IN_CUSTOMER_ID);
  COMMIT;
  END PROC_BASKET_ADD;

  PROCEDURE PROC_BASKET_LIST(IN_CUSTOMER_ID IN VARCHAR2, O_CUR OUT SYS_REFCURSOR) AS
  BEGIN
    OPEN O_CUR FOR
  SELECT
       B.BASKET_ID,
       I. ITEM_NAME,
        I.PRICE,
        B.CNT,
        (I.PRICE * B.CNT) SUBTOTAL,
        B.REGDATE
    FROM ITEM I, BASKET B
    WHERE I.ITEM_ID = B.ITEM_ID AND B.CUSTOMER_ID = IN_CUSTOMER_ID;
  END PROC_BASKET_LIST;

  PROCEDURE PROC_BASKET_DELETE(IN_BASKET_ID IN NUMBER, IN_CUSTOMER_ID IN VARCHAR2) AS
  BEGIN
    DELETE FROM BASKET 
    WHERE BASKET_ID = IN_BASKET_ID AND CUSTOMER_ID = IN_CUSTOMER_ID;
    COMMIT;
    
  END PROC_BASKET_DELETE;



  PROCEDURE PROC_BASKET_DELETEALL AS
  BEGIN
    DELETE FROM BASKET;
    COMMIT;
  END PROC_BASKET_DELETEALL;

  PROCEDURE PROC_PURCHASE_LIST(IN_CUSTOMER_ID IN VARCHAR2, O_CUR OUT SYS_REFCURSOR) AS
  BEGIN
     OPEN O_CUR FOR
  SELECT
       P.PURCHASE_ID,
       I. ITEM_NAME,
        I.PRICE,
        P.CNT,
        (I.PRICE * P.CNT) SUBTOTAL,
        P.REGDATE
    FROM ITEM I, PURCHASE P
    WHERE I.ITEM_ID = P.ITEM_ID AND P.CUSTOMER_ID = IN_CUSTOMER_ID ORDER BY P.REGDATE ASC;;
  END PROC_PURCHASE_LIST;
  
  PROCEDURE PROC_PURCHASE_ADD(IN_CUSTOMER_ID IN VARCHAR2) AS
  I BINARY_INTEGER:=0; 
  TYPE DEPT_TABLE_TYPE IS TABLE OF BASKET%ROWTYPE
  INDEX BY BINARY_INTEGER;
  DEPT_TABLE DEPT_TABLE_TYPE;
  BEGIN
  FOR DEPT_LIST IN(SELECT * FROM BASKET WHERE CUSTOMER_ID = IN_CUSTOMER_ID)LOOP
  I:=I+1;
  DEPT_TABLE(I).ITEM_ID:=DEPT_LIST.ITEM_ID;
  DEPT_TABLE(I).CUSTOMER_ID:=DEPT_LIST.CUSTOMER_ID;
  DEPT_TABLE(I).CNT:=DEPT_LIST.CNT;
 INSERT INTO PURCHASE(PURCHASE_ID, ITEM_ID, CUSTOMER_ID, CNT)
  VALUES((SELECT NVL(MAX(PURCHASE_ID), 0) + 1 FROM PURCHASE), DEPT_TABLE(I).ITEM_ID, DEPT_TABLE(I).CUSTOMER_ID, DEPT_TABLE(I).CNT);
 UPDATE ITEM SET BUYCOUNT = BUYCOUNT + 1 WHERE ITEM_ID = DEPT_LIST.ITEM_ID;
  END LOOP;

  END PROC_PURCHASE_ADD;

PROCEDURE PROC_MAIN_VIEW (O_CUR  OUT SYS_REFCURSOR) AS  
  
  BEGIN
    OPEN  O_CUR  FOR
    
    SELECT ITEM_ID, ITEM_NAME, PRICE, BUYCOUNT, CATEGORY_CODE_ID
    FROM 
    (SELECT  ITEM_ID, ITEM_NAME, PRICE, BUYCOUNT, CATEGORY_CODE_ID
    FROM  ITEM
    ORDER BY BUYCOUNT DESC)
     
    WHERE ROWNUM <= 8;
     
  END PROC_MAIN_VIEW;
  
  PROCEDURE PROC_MYPAGE_VIEW (
        IN_CUSTOMER_ID IN VARCHAR2,
        O_CUSTOMER_NICKNAME OUT VARCHAR2,
        O_EMAIL     OUT VARCHAR2,
        O_TEL   OUT VARCHAR2
     ) AS
  BEGIN
   SELECT  CUSTOMER_NICKNAME, EMAIL, TEL
      INTO   O_CUSTOMER_NICKNAME, O_EMAIL, O_TEL
      FROM   CUSTOMER
      WHERE  CUSTOMER_ID   =  IN_CUSTOMER_ID;
  END PROC_MYPAGE_VIEW;

END PKG_TRAVEL;