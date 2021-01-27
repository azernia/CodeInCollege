--Add User
insert into buser values('yh001','陈一平','男','432152015106094647','15211171305','湖南省临澧县安福镇');
insert into buser values('yh002','王利民','男','431162014101398177','15211171305','河南省焦作市马村区');
insert into buser values('yh003','黄晶灿','女','439482014101601852','13211172205','湖南省东安县');
insert into buser values('yh004','李光顺','女','434792013100618160','13237341551','湖南省耒阳市');
--Add Bank Card
insert into bankcard(bankCardNum,buserid,bankcardpwd,asset) values('436742920141062657','yh001',123456,2000);
insert into bankcard(bankCardNum,buserid,bankcardpwd,asset) values('621723191200001180','yh002',123456,4000);
insert into bankcard(bankCardNum,buserid,bankcardpwd,asset) values('621700294010150898','yh003',123456,8000);
insert into bankcard(bankCardNum,buserid,bankcardpwd,asset) values('621799555001901708','yh004',123456,9000);
--Add Staff
insert into staff values(1001,'yg1001',1);
insert into staff values(1002,'yg1002',2);
insert into staff values(1003,'yg1003',3);
--Add Business
insert into business values('yw001','活期','无固定存期，可随时存取');
insert into business values('yw002','定活两便','事先不约定存期，一次性存入，一次性支取');
insert into business values('yw003','短信通知','每次对账户的操作都将进行短信通知并扣除相应费用');
--Add Bank Card Transaction Details
--Add Credit Card
insert into creditcard values('436742920179002657','yh001',123456,2000,2000,0);
insert into creditcard values('621723191235471180','yh002',123456,3500,3000,10);
insert into creditcard values('621700294325350898','yh003',123456,5500,2000,100);
insert into creditcard values('621799560331901708','yh004',123456,2500,2000,15);

insert into bankcardtransactiondetails values(201912201010,'436742920141062657','跨行转账','网上银行',100,'621723191200001180',to_date('2019-12-20 13:55:32','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201912202352,'436742920141062657','线下交易','银企对接',20,'Alipay',to_date('2019-12-20 15:30:32','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201912201268,'436742920141062657','线下交易','银企对接',35,'Alipay',to_date('2019-12-20 20:35:32','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201912209858,'621723191200001180','银行转入','网上银行',35,'436742920141062657',to_date('2019-12-20 14:00:00','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201911118596,'621723191200001180','线下交易','银企对接',80,'WeChat',to_date('2019-12-20 14:00:00','yyyy-mm-dd hh24:mi:ss'));

insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('436742920141062657',201912201010);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('436742920141062657',201912202352);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('436742920141062657',201912201268);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('621723191200001180',201912209858);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('621723191200001180',201911118596);

insert into loan values('dk2016121301','436742920141062657',100000,to_date('2016-12-13 15:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2017-12-13 23:59:59','yyyy-mm-dd hh24:mi:ss'));
insert into loan values('dk2019111111','621723191200001180',100000,to_date('2019-11-11 15:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2020-11-11 23:59:59','yyyy-mm-dd hh24:mi:ss'));
insert into loan values('dk2017101101','621700294010150898',100000,to_date('2017-10-11 14:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2018-10-11 23:59:59','yyyy-mm-dd hh24:mi:ss'));
insert into loan values('dk2018121601','621799555001901708',100000,to_date('2018-12-16 8:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2019-12-16 23:59:59','yyyy-mm-dd hh24:mi:ss'));


insert into creditcardtransactiondetails values(201811110023,'线下交易',15,to_date('2018-11-19','yyyy-mm-dd'),15,'收钱吧');
insert into creditcardtransactiondetails values(201811111225,'线下交易',15,to_date('2018-11-19','yyyy-mm-dd'),15,'WeChat');
insert into creditcardtransactiondetails values(201811161123,'电商',120,to_date('2018-11-19','yyyy-mm-dd'),120,'银企对接');
insert into creditcardtransactiondetails values(201811181600,'线下交易',8,to_date('2018-11-19','yyyy-mm-dd'),8,'收钱吧');
insert into creditcardtransactiondetails values(201811191515,'线下交易',32,to_date('2018-11-19','yyyy-mm-dd'),32,'收钱吧');
insert into creditcardtransactiondetails values(201811191615,'线下交易',32,to_date('2018-11-19','yyyy-mm-dd'),32,'收钱吧');

insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('436742920179002657',201811110023);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('436742920179002657',201811191515);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('436742920179002657',201811191615);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('621723191235471180',201811111225);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('621700294325350898',201811161123);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('621799560331901708',201811181600);



