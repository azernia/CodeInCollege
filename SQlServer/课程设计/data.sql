--Add User
insert into buser values('yh001','��һƽ','��','432152015106094647','15211171305','����ʡ����ذ�����');
insert into buser values('yh002','������','��','431162014101398177','15211171305','����ʡ�����������');
insert into buser values('yh003','�ƾ���','Ů','439482014101601852','13211172205','����ʡ������');
insert into buser values('yh004','���˳','Ů','434792013100618160','13237341551','����ʡ������');
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
insert into business values('yw001','����','�޹̶����ڣ�����ʱ��ȡ');
insert into business values('yw002','��������','���Ȳ�Լ�����ڣ�һ���Դ��룬һ����֧ȡ');
insert into business values('yw003','����֪ͨ','ÿ�ζ��˻��Ĳ����������ж���֪ͨ���۳���Ӧ����');
--Add Bank Card Transaction Details
--Add Credit Card
insert into creditcard values('436742920179002657','yh001',123456,2000,2000,0);
insert into creditcard values('621723191235471180','yh002',123456,3500,3000,10);
insert into creditcard values('621700294325350898','yh003',123456,5500,2000,100);
insert into creditcard values('621799560331901708','yh004',123456,2500,2000,15);

insert into bankcardtransactiondetails values(201912201010,'436742920141062657','����ת��','��������',100,'621723191200001180',to_date('2019-12-20 13:55:32','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201912202352,'436742920141062657','���½���','����Խ�',20,'Alipay',to_date('2019-12-20 15:30:32','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201912201268,'436742920141062657','���½���','����Խ�',35,'Alipay',to_date('2019-12-20 20:35:32','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201912209858,'621723191200001180','����ת��','��������',35,'436742920141062657',to_date('2019-12-20 14:00:00','yyyy-mm-dd hh24:mi:ss'));
insert into bankcardtransactiondetails values(201911118596,'621723191200001180','���½���','����Խ�',80,'WeChat',to_date('2019-12-20 14:00:00','yyyy-mm-dd hh24:mi:ss'));

insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('436742920141062657',201912201010);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('436742920141062657',201912202352);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('436742920141062657',201912201268);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('621723191200001180',201912209858);
insert into btrelationship(bankcardnum,bankcardtransactionserialnum) values('621723191200001180',201911118596);

insert into loan values('dk2016121301','436742920141062657',100000,to_date('2016-12-13 15:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2017-12-13 23:59:59','yyyy-mm-dd hh24:mi:ss'));
insert into loan values('dk2019111111','621723191200001180',100000,to_date('2019-11-11 15:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2020-11-11 23:59:59','yyyy-mm-dd hh24:mi:ss'));
insert into loan values('dk2017101101','621700294010150898',100000,to_date('2017-10-11 14:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2018-10-11 23:59:59','yyyy-mm-dd hh24:mi:ss'));
insert into loan values('dk2018121601','621799555001901708',100000,to_date('2018-12-16 8:30:32','yyyy-mm-dd hh24:mi:ss'),to_date('2019-12-16 23:59:59','yyyy-mm-dd hh24:mi:ss'));


insert into creditcardtransactiondetails values(201811110023,'���½���',15,to_date('2018-11-19','yyyy-mm-dd'),15,'��Ǯ��');
insert into creditcardtransactiondetails values(201811111225,'���½���',15,to_date('2018-11-19','yyyy-mm-dd'),15,'WeChat');
insert into creditcardtransactiondetails values(201811161123,'����',120,to_date('2018-11-19','yyyy-mm-dd'),120,'����Խ�');
insert into creditcardtransactiondetails values(201811181600,'���½���',8,to_date('2018-11-19','yyyy-mm-dd'),8,'��Ǯ��');
insert into creditcardtransactiondetails values(201811191515,'���½���',32,to_date('2018-11-19','yyyy-mm-dd'),32,'��Ǯ��');
insert into creditcardtransactiondetails values(201811191615,'���½���',32,to_date('2018-11-19','yyyy-mm-dd'),32,'��Ǯ��');

insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('436742920179002657',201811110023);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('436742920179002657',201811191515);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('436742920179002657',201811191615);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('621723191235471180',201811111225);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('621700294325350898',201811161123);
insert into ctrelationship(creditcardnum,creditcardtransactionserialnum) values('621799560331901708',201811181600);



