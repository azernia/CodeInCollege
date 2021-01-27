/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2019/12/18 21:32:29                          */
/*==============================================================*/



-- Type package declaration
create or replace package PDTypes  
as
    TYPE ref_cursor IS REF CURSOR;
end;

-- Integrity package declaration
create or replace package IntegrityPackage AS
 procedure InitNestLevel;
 function GetNestLevel return number;
 procedure NextNestLevel;
 procedure PreviousNestLevel;
 end IntegrityPackage;
/

-- Integrity package definition
create or replace package body IntegrityPackage AS
 NestLevel number;

-- Procedure to initialize the trigger nest level
 procedure InitNestLevel is
 begin
 NestLevel := 0;
 end;


-- Function to return the trigger nest level
 function GetNestLevel return number is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 return(NestLevel);
 end;

-- Procedure to increase the trigger nest level
 procedure NextNestLevel is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 NestLevel := NestLevel + 1;
 end;

-- Procedure to decrease the trigger nest level
 procedure PreviousNestLevel is
 begin
 NestLevel := NestLevel - 1;
 end;

 end IntegrityPackage;
/


drop trigger "CompoundDeleteTrigger_btrelati"
/

drop trigger "CompoundInsertTrigger_btrelati"
/

drop trigger "CompoundUpdateTrigger_btrelati"
/

drop trigger "tib_btrelationship"
/

drop trigger "CompoundDeleteTrigger_ctrelati"
/

drop trigger "CompoundInsertTrigger_ctrelati"
/

drop trigger "CompoundUpdateTrigger_ctrelati"
/

drop trigger "tib_ctrelationship"
/

drop trigger "CompoundDeleteTrigger_staff"
/

drop trigger "CompoundInsertTrigger_staff"
/

drop trigger "CompoundUpdateTrigger_staff"
/

alter table "BTRelationship"
   drop constraint FK_BTRELATI_REFERENCE_BANKCAR2
/

alter table "BTRelationship"
   drop constraint FK_BTRELATI_REFERENCE_BANKCARD
/

alter table "BankCard"
   drop constraint FK_BANKCARD_REFERENCE_BUSER
/

alter table "BankCard"
   drop constraint FK_BANKCARD_REFERENCE_BUSINESS
/

alter table "BankCard"
   drop constraint FK_BANKCARD_REFERENCE_LOAN
/

alter table "CTRelationship"
   drop constraint FK_CTRELATI_REFERENCE_CREDITC2
/

alter table "CTRelationship"
   drop constraint FK_CTRELATI_REFERENCE_CREDITCA
/

alter table "CreditCard"
   drop constraint FK_CREDITCA_REFERENCE_BUSER
/

drop table "BTRelationship" cascade constraints
/

drop table "BUser" cascade constraints
/

drop table "BankCard" cascade constraints
/

drop table "BankCardTransactionDetails" cascade constraints
/

drop table "Business" cascade constraints
/

drop table "CTRelationship" cascade constraints
/

drop table "CreditCard" cascade constraints
/

drop table "CreditCardTransactionDetails" cascade constraints
/

drop table "Loan" cascade constraints
/

drop table "Staff" cascade constraints
/

drop sequence "Sequence_2"
/

drop sequence "Sequence_3"
/

create sequence "Sequence_2"
increment by 1
start with 1
/

create sequence "Sequence_3"
increment by 1
start with 1
/

/*==============================================================*/
/* Table: "BTRelationship"                                      */
/*==============================================================*/
create table "BTRelationship" 
(
   "btRelationshipID"   NUMBER(6)            not null,
   "bankCardNum"        VARCHAR2(20),
   "bankCardTransactionSerialNum" NUMBER(12),
   constraint PK_BTRELATIONSHIP primary key ("btRelationshipID")
)
/

/*==============================================================*/
/* Table: "BUser"                                               */
/*==============================================================*/
create table "BUser" 
(
   "bUserID"            VARCHAR2(10)         not null,
   "name"               VARCHAR(10),
   "sex"                VARCHAR(4),
   "iDCard"             VARCHAR(18),
   "phoneNum"           NUMBER,
   "address"            VARCHAR2(30),
   constraint PK_BUSER primary key ("bUserID")
)
/

/*==============================================================*/
/* Table: "BankCard"                                            */
/*==============================================================*/
create table "BankCard" 
(
   "bankCardNum"        VARCHAR2(20)         not null,
   "bUserID"            VARCHAR2(10),
   "bankCardPwd"        NUMBER               not null,
   "asset"              NUMBER,
   "businessID"         VARCHAR2(10),
   "loanID"             VARCHAR2(13),
   constraint PK_BANKCARD primary key ("bankCardNum")
)
/

/*==============================================================*/
/* Table: "BankCardTransactionDetails"                          */
/*==============================================================*/
create table "BankCardTransactionDetails" 
(
   "bankCardTransactionSerialNum" NUMBER(12)           not null,
   "transactionAccount" VARCHAR2(20),
   "transactionType"    VARCHAR2(10),
   "tradingChannel"     VARCHAR2(10),
   "transactionAmount"  NUMBER,
   "counterpartyAccount" NUMBER,
   "transactionDate"    DATE,
   constraint PK_BANKCARDTRANSACTIONDETAILS primary key ("bankCardTransactionSerialNum")
)
/

/*==============================================================*/
/* Table: "Business"                                            */
/*==============================================================*/
create table "Business" 
(
   "businessID"         VARCHAR2(10)         not null,
   "businessName"       VARCHAR2(20),
   "description"        VARCHAR2(80),
   constraint PK_BUSINESS primary key ("businessID")
)
/

/*==============================================================*/
/* Table: "CTRelationship"                                      */
/*==============================================================*/
create table "CTRelationship" 
(
   "ctRelationshipID"   NUMBER(6)            not null,
   "creditCardNum"      VARCHAR2(20),
   "creditCardTransactionSerialNum" NUMBER(12),
   constraint PK_CTRELATIONSHIP primary key ("ctRelationshipID")
)
/

/*==============================================================*/
/* Table: "CreditCard"                                          */
/*==============================================================*/
create table "CreditCard" 
(
   "creditCardNum"      VARCHAR2(20)         not null,
   "bUserID"            VARCHAR2(10),
   "creditCardPwd"      NUMBER               not null,
   "totalDegree"        NUMBER,
   "balance"            NUMBER,
   "creditScore"        NUMBER,
   constraint PK_CREDITCARD primary key ("creditCardNum")
)
/

/*==============================================================*/
/* Table: "CreditCardTransactionDetails"                        */
/*==============================================================*/
create table "CreditCardTransactionDetails" 
(
   "creditCardTransactionSerialNum" NUMBER(12)           not null,
   "transactionType"    VARCHAR2(10),
   "transactionAmount"  NUMBER,
   "repaymentTime"      DATE,
   "repaymentAmount"    NUMBER,
   constraint PK_CREDITCARDTRANSACTIONDETAIL primary key ("creditCardTransactionSerialNum")
)
/

/*==============================================================*/
/* Table: "Loan"                                                */
/*==============================================================*/
create table "Loan" 
(
   "loanID"             VARCHAR2(13)         not null,
   "bankCardNum"        VARCHAR2(20),
   "loanAmount"         NUMBER,
   "loanDate"           DATE,
   "repaymentDate"      DATE,
   constraint PK_LOAN primary key ("loanID")
)
/

/*==============================================================*/
/* Table: "Staff"                                               */
/*==============================================================*/
create table "Staff" 
(
   "staffID"            NUMBER(10)           not null,
   "staffPwd"           VARCHAR2(8),
   "rank"               NUMBER,
   constraint PK_STAFF primary key ("staffID")
)
/

alter table "BTRelationship"
   add constraint FK_BTRELATI_REFERENCE_BANKCAR2 foreign key ("bankCardNum")
      references "BankCard" ("bankCardNum")
/

alter table "BTRelationship"
   add constraint FK_BTRELATI_REFERENCE_BANKCARD foreign key ("bankCardTransactionSerialNum")
      references "BankCardTransactionDetails" ("bankCardTransactionSerialNum")
/

alter table "BankCard"
   add constraint FK_BANKCARD_REFERENCE_BUSER foreign key ("bUserID")
      references "BUser" ("bUserID")
/

alter table "BankCard"
   add constraint FK_BANKCARD_REFERENCE_BUSINESS foreign key ("businessID")
      references "Business" ("businessID")
/

alter table "BankCard"
   add constraint FK_BANKCARD_REFERENCE_LOAN foreign key ("loanID")
      references "Loan" ("loanID")
/

alter table "CTRelationship"
   add constraint FK_CTRELATI_REFERENCE_CREDITC2 foreign key ("creditCardNum")
      references "CreditCard" ("creditCardNum")
/

alter table "CTRelationship"
   add constraint FK_CTRELATI_REFERENCE_CREDITCA foreign key ("creditCardTransactionSerialNum")
      references "CreditCardTransactionDetails" ("creditCardTransactionSerialNum")
/

alter table "CreditCard"
   add constraint FK_CREDITCA_REFERENCE_BUSER foreign key ("bUserID")
      references "BUser" ("bUserID")
/


create or replace trigger "CompoundDeleteTrigger_btrelati"
for delete on "BTRelationship" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create or replace trigger "CompoundInsertTrigger_btrelati"
for insert on "BTRelationship" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create or replace trigger "CompoundUpdateTrigger_btrelati"
for update on "BTRelationship" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create trigger "tib_btrelationship" before insert
on "BTRelationship" for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column ""btRelationshipID"" uses sequence Sequence_2
    select Sequence_2.NEXTVAL INTO :new."btRelationshipID" from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create or replace trigger "CompoundDeleteTrigger_ctrelati"
for delete on "CTRelationship" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create or replace trigger "CompoundInsertTrigger_ctrelati"
for insert on "CTRelationship" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create or replace trigger "CompoundUpdateTrigger_ctrelati"
for update on "CTRelationship" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create trigger "tib_ctrelationship" before insert
on "CTRelationship" for each row
declare
    integrity_error  exception;
    errno            integer;
    errmsg           char(200);
    dummy            integer;
    found            boolean;

begin
    --  Column ""ctRelationshipID"" uses sequence Sequence_3
    select Sequence_3.NEXTVAL INTO :new."ctRelationshipID" from dual;

--  Errors handling
exception
    when integrity_error then
       raise_application_error(errno, errmsg);
end;
/


create or replace trigger "CompoundDeleteTrigger_staff"
for delete on "Staff" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create or replace trigger "CompoundInsertTrigger_staff"
for insert on "Staff" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/


create or replace trigger "CompoundUpdateTrigger_staff"
for update on "Staff" compound trigger
// Declaration
// Body
  before statement is
  begin
     NULL;
  end before statement;

  before each row is
  begin
     NULL;
  end before each row;

  after each row is
  begin
     NULL;
  end after each row;

  after statement is
  begin
     NULL;
  end after statement;

END
/

