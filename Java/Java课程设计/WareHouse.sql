--�����û�
create user WareHouse identified by 666;
grant connect,resource to WareHouse;
--��������Ա��
create table manager( 
       managerID varchar2(20) primary key,
       managerName varchar2(20),
       mpassword varchar2(20),
       loginTime date
);
--��������
create table goodsClass(
       className varchar2(20) primary key
);
--���������
create table area(
       areaID varchar2(4) primary key,
       areaMsg varchar2(10)
);
--���������
create table goods(
       goodsID varchar2(20) primary key,
       goodsName varchar2(20),
       className varchar2(20) references goodsClass(className),
       stockPrice float,
       stockTime date,
       shipmentTime date,
       areaID varchar2(4) references area(areaID)
);
