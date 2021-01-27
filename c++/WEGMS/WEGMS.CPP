#include "iostream"
#include"string"
#include"fstream"
#include"iomanip"
using namespace std;
int count1=0,count11=0; //学生总数和交钱的人数
int count2=0,count22=0; //教工总数和交钱人数

class Student
{
	public:
		Student(double xwater=0,double xpower=0,double xgas=0,string xnum="a",string xna="a");//学生类构造函数
		void operator =(Student S); //赋值运算符重载
		friend ostream &operator <<(ostream & a,Student &S) //插入运算符重载
		{ 
			a<<setw(8)<<left<<S.number;
			a<<setw(8)<<left<<S.name;
			a<<setw(8)<<left<<S.water;
			a<<setw(8)<<left<<S.power;
			a<<setw(8)<<left<<S.gas;
			a<<setw(8)<<left<<S.SGetW();
			a<<setw(8)<<left<<S.SGetC();
			a<<setw(8)<<left<<S.SGetG();
			a<<setw(8)<<left<<S.Stotal();
			if(S.flag)
				a<<"已交";
			else 
				a<<"未交";
			cout<<endl;
			return a;
		}
		double SGetW(); //计算水费
		double SGetC(); //计算电费
		double SGetG(); //计算煤气费
		double Stotal(); //计算总费用
		double water,power,gas,water_rate,circuit,gas_rate,total;
		int flag;
		string number,name; 
};

Student::Student(double xwater,double xpower,double xgas,string xnum,string xna) //学生类构造函数
{
	water=xwater;
	power=xpower;
	gas=xgas;
	number=xnum;
	name=xna;
	flag=0;
}

void Student::operator =(Student S) //赋值运算符重载
{
	name=S.name;
	number=S.number;
	water=S.water;
	power=S.power;
	gas=S.gas;
}

double Student::SGetW( ) //计算水费 
{
	if(water<10)
		water_rate=0; 
	else
		water_rate=4.2*(water-10);
	return water_rate; 
}

double Student::SGetC() //计算电费 
{
	if(power<10)
		circuit=0;
	else
		circuit=0.6*(power-10);
	return circuit;
}

double Student::SGetG() //计算煤气费 
{
	if(gas<10)
		gas_rate=0;
	else
		gas_rate=2.3*(gas-10);
	return gas_rate;
}

double Student::Stotal() //计算总的钱
{
	total=SGetG()+SGetW()+SGetC();
	return total;
}

class MStudent //学生类计算
{
	public:
		MStudent(int sz=1) 
		{
			size=sz;
			student=new Student[size];
		} //构造函数
		~MStudent() 
		{
			delete [] student; 
		}
		void Menu(); //学生用户主菜单
		void Pay(); //学生交费菜单
		void Amend( ); //修改用户菜单
		void Add( ); //增加用户
		void Delet(); // 删除用户菜单
		void DName(); //姓名删除
		void DNumber(); //学号删除
		void Check( ); //查询菜单
		void CName(); //姓名查询
		void CNumber(); //学号查询
		void PName(); //输入姓名交费
		void PNumber(); //输入学号交费
		void Display(); //查看交费情况
		void Save();
	private:
		int size;
		Student *student; 
};


void MStudent::PName( ) //输入姓名交费
{ 
	char ch;
	double money,money2;
	cout<<"\t\t\t欢迎使用姓名交费功能"<<endl;
	string na;
	cout<<"请输入学生姓名:"<<endl;
	cin>>na;
	int fl=0;
	for(int i=0;i<count1;i++)
		if(na==student[i].name)
		{
			fl=1;
			if(student[i].flag)
				cout<<"该学生已缴纳过费用"<<endl;
			else
			{
				cout<<"你应该交"<<student[i].Stotal()<<"钱"<<endl;
				cout<<"确定是否真的现在交费 (Y/N)"<<endl;
				cin>>ch;
				if(ch=='Y'||ch=='y')
				{ 
					student[i].flag=1;
					cout<<"你实际交的钱是:"<<endl;
					cin>>money;
					if(money>student[i].Stotal())
						cout<<"找零"<<money-student[i].Stotal()<<"钱"<<endl;
					if(student[i].Stotal()>money)
					{ 
						cout<<"交的钱不够,还应交"<<student[i].Stotal()-money<<"钱"<<endl;
						cout<<"补交:";
						cin>>money2;
					}
					cout<<"交费成功"<<endl;
					count11++;
					Menu();
				}
				else Menu();
			}
		}
		if(!fl)
			cout<<"没有这个学生"<<endl;
}

void MStudent::PNumber( ) //输入学号交费
{ 
	cout<<"\t\t\t欢迎使用学号交费功能"<<endl;
	string num;
	double money,money2;
	char ch;
	cout<<"请输入学生学号:"<<endl;
	cin>>num;
	int fl=0;
	for(int i=0;i<count1;i++)
		if(num==student[i].number)
		{
			if(student[i].flag)
			{
				fl=1; 
				cout<<"该学生已缴纳过费用"<<endl;
			}
			else
			{ 
				cout<<"你应该交"<<student[i].Stotal()<<"钱"<<endl;
				cout<<"确定是否真的现在交费 (Y/N)"<<endl;
				cin>>ch;
				if(ch=='Y'||ch=='y')
				{
					student[i].flag=1;
					cout<<"你实际交的钱是:"<<endl;
					cin>>money;
					if(money>student[i].Stotal())
						cout<<"找零"<<money-student[i].Stotal()<<"钱"<<endl;
					if(student[i].Stotal()>money)
					{
						cout<<"交的钱不够,还应交"<<student[i].Stotal()-money<<"钱"<<endl;
						cout<<"补交:"; cin>>money2;
					}
					cout<<"交费成功"<<endl;
					count11++;
					Menu();
				}
				else Menu();
			}
		}
		if(!fl)
			cout<<"没有这个学生"<<endl;
}

void MStudent::Add() //增加学生
{
	cout<<"\t\t\t欢迎使用增加学生功能"<<endl;
	cout<<"请输入学生的姓名:"<<endl;
	cin>>student[count1].name;
	cout<<"请输入学生的学号:"<<endl;
	cin>>student[count1].number;
	cout<<"请输入学生的用水量:"<<endl;
	cin>>student[count1].water;
	cout<<"请输入学生的用电量:"<<endl;
	cin>>student[count1].power;
	cout<<"请输入学生的用煤气量:"<<endl;
	cin>>student[count1].gas; count1++;
	cout<<"\t\t\t学生信息已被成功的增加!"<<endl;
 }

void MStudent::DName( ) //按姓名删除
{
	cout<<"\t\t\t欢迎使用姓名删除学生功能"<<endl;
	int fl=0;
	int i,j;
	string na;
	cout<<"请输入要删除学生的姓名"<<endl;
	cin>>na;
	for(i=0;i<count1;i++)
		if(na==student[i].name) {fl=1;break;}
		if(fl)
		{
			for(j=i;j<count1-1;j++)
				student[j]=student[j+1];
			count1--;
			cout<<"你已成功删除学生信息"<<endl; }
		else
			cout<<"没有这个学生"<<endl;
}

void MStudent::Display() 
{ 
	int i;
	if(count11==0)
		cout<<"暂时还无学生交费"<<endl;
	else
	{
		cout<<"已交费人员信息"<<endl;
		cout<<"学号 "<<"姓名 "<<" 用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费 "<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
		for( i=0;i<count1;i++)
			if(student[i].flag)
				cout<<student[i];
	}
	if(count11==count1) cout<<"所有学生均以交费"<<endl;
	else
	{
		cout<<"未交费人员信息"<<endl;
		cout<<"学号 "<<"姓名 "<<" 用水量 "<<"用电量 "<<"用煤气量 "<<"水费"<<"电费 "<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
		for(i=0;i<count1;i++)
			if(!student[i].flag)
				cout<<student[i];
	}
}

void MStudent::DNumber( ) //按学号删除
{
	cout<<"\t\t\t欢迎使用学号删除学生功能"<<endl; int fl=0;
	int i,j;
	string num;
	cout<<"请输入要删除学生的学号"<<endl; cin>>num;
	for(i=0;i<count1;i++)
		if(num==student[i].number) 
		{
			fl=1;
			break;
		}
		if(fl)
		{
			for(j=i;j<count1-1;j++)
				student[j]=student[j+1];
			count1--;
			cout<<"你已成功删除学生信息"<<endl;
		}
		else
			cout<<"没有这个学生"<<endl;
}

void MStudent::CName( ) //姓名查询 
{ 
	cout<<"\t\t\t欢迎使用姓名查询学生功能"<<endl;
	int i,fl=0;
	string na;
	cout<<"请输入要查找的姓名"<<endl;
	cin>>na;
	for(i=0;i<count1;i++)
		if(na==student[i].name)
		{
			fl=1;
			break;
		}
		if(fl)
		{
			cout<<"学号 "<<"姓名 "<<" 用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费 "<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
			cout<<student[i]<<endl;
		} 
		else
			cout<<"没有这个学生"<<endl;
}

void MStudent::CNumber() //学号查询 
{ 
	cout<<"\t\t\t欢迎使用学号查询学生功能"<<endl;
	int i,fl=0;
	string num;
	cout<<"请输入要查找的学号"<<endl;
	cin>>num;
	for(i=0;i<count1;i++)
		if(num==student[i].number)
		{ 
			fl=1;
			break;
		}
		if(fl)
		{
			cout<<"学号 "<<"姓名 "<<" 用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费"<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
			cout<<student[i]<<endl;
		}
		else
			cout<<"没有这个学生"<<endl;
}

void MStudent::Save() 
{ 
	ofstream f("Stu2.txt");
	f<<"学号 "<<"姓名 "<<" 用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费"<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
	for(int i=0;i<count1;i++)
		f<<student[i]<<endl;
	f.close();
	cout<<"恭喜你,文件已成功保存"<<endl; 
}

void MStudent::Delet() //删除学生菜单 
{
	int choice;
	cout<<"\t\t\t\t**************"<<endl;
	cout<<"\t\t\t\t 删除信息"<<endl;
	cout<<"\t\t\t\t 1,按学号删除"<<endl;
	cout<<"\t\t\t\t 2,按姓名删除"<<endl;
	cout<<"\t\t\t\t 3,返回主菜单"<<endl;
	cout<<"\t\t\t\t 0,返回上一层"<<endl;
	cout<<"\t\t\t\t**************"<<endl;
	while(1)
	{
		cout<<"请输入你的选择"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1: DNumber(); 
				break; //学号删除
			case 2: DName();
				break; //姓名删除
			case 3: Menu();
				break; //返回学生管理主菜单
			case 0: Amend();
				break; //返回上一层
		}
		if(choice==0)
			break;
	}
}

void MStudent::Pay() //学生缴费菜单
{
	int choice;
	cout<<"\t\t\t\t****************"<<endl;
	cout<<"\t\t\t\t*---学生缴费---*"<<endl;
	cout<<"\t\t\t\t*1,输入学号交费*"<<endl;
	cout<<"\t\t\t\t*2,输入姓名交费*"<<endl;
	cout<<"\t\t\t\t*0, 返回上一层 *"<<endl;
	cout<<"\t\t\t\t****************"<<endl;
	while(1)
	{
		cout<<"请输入你的选择"<<endl;
		cin>>choice;
		switch(choice)
		{ 
			case 1: PNumber(); 
				break; //输入学号计费
			case 2: PName();
				break; //输入姓名计费
			case 0: Menu();
				break; //返回上一层
		}
		if(choice==0)
			break;
	}
}

void MStudent::Menu() //学生用户主菜单
{
	int choice;
	cout<<"\t\t\t\t欢迎使用学生用户管理系统"<<endl<<endl;
	cout<<"********************************"<<endl;
	cout<<"\t\t\t\t*------1,注册并修改用户------*"<<endl;
	cout<<"\t\t\t\t*------2,缴纳费用------*"<<endl;
	cout<<"\t\t\t\t*------3,查询费用------*"<<endl;
	cout<<"\t\t\t\t*------4,查看交费情况--*"<<endl;
	cout<<"\t\t\t\t*------5,保存信息到文件--*"<<endl;
	cout<<"\t\t\t\t*------0,返回上一层----*"<<endl;
	cout<<"********************************"<<endl;
	cout<<"友情提示:第一次使用时,要进行注册!"<<endl<<endl;
	for(;;)
	{
		cout<<"请输入您的选择"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1: Amend();
				break; //注册并修改
			case 2: Pay();
				break; //缴纳费用
			case 3: Check();
				break;
			case 4: Display();
				break;
			case 5: Save();
				break;
			case 0: break;
		}
		if(choice==0) break;
	}
}

void MStudent::Amend() //修改用户菜单
{
	int choice;
	cout<<"\t\t\t\t修改学生信息"<<endl<<endl;
	cout<<"\t\t\t\t1,增加学生 "<<endl;
	cout<<"\t\t\t\t2,删除学生 "<<endl;
	cout<<"\t\t\t\t0,回上一层 "<<endl;
	while(1)
	{
		cout<<"请输入你的选择"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1: Add();
				break;
			case 2: Delet();
				break;
			case 0: Menu();
				break;
		}
		if(choice==0)
			break;
	}
}

void MStudent::Check() //查询菜单
{
	int choice;
	cout<<"\t\t 查询学生信息"<<endl<<endl;
	cout<<" 1,按姓名查找 "<<endl;
	cout<<" 2,按学号查找 "<<endl;
	cout<<" 0,返回上一层 "<<endl;
	cout<<"============================================="<<endl;
	while(1)
	{
		cout<<"请输入您的选择"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1: CName();
				break; //输入姓名查找
			case 2: CNumber();
				break; //输入学号查找
			case 0: Menu();
				break;
		}
		if(choice==0)break;
	}
}

class Teacher //教师
{
	public:
		Teacher( double xwater=0,double xpower=0,double xgas=0,string xname="a")
		{
			water=xwater;
			power=xpower;
			gas=xgas;
			name=xname;
			flag=0;
		}
		void operator =(Teacher S); //赋值运算符重载
		friend ostream &operator <<(ostream & a,Teacher &S) //插入运算符重载
		{
			a<<setw(6)<<left<<S.name;
			a<<setw(6)<<left<<S.water;
			a<<setw(10)<<S.power;
			a<<setw(10)<<S.gas;
			a<<setw(10)<<S.TGetW();
			a<<setw(10)<<S.TGetC();
			a<<setw(10)<<S.TGetG();
			a<<setw(10)<<S.Ttotal();
			if(S.flag)
				a<<"已交";
			else 
				a<<"未交";
			cout<<endl;
			return a;
		}
		double TGetW(); //水费计算
		double TGetC(); //电费计算
		double TGetG(); //煤气费计算
		double Ttotal(); //计算总的钱
		string name;
		double water,power,gas,water_rate,circuit,gas_rate,total;
		int flag;
};

void Teacher::operator =(Teacher S) //赋值运算符重载 
{
	name=S.name;
	water=S.water;
	power=S.power;
	gas=S.gas;
}

double Teacher::TGetW() //计算水费
{
	water_rate=3.7*water;
	return water_rate;
}

double Teacher::TGetC() //计算电费
{
	circuit=0.45*power;
	return circuit;
}

double Teacher::TGetG() //计算煤气费 
{
	gas_rate=1.9*gas;
	return gas_rate;
}

double Teacher::Ttotal() //计算应该付的的钱 
{
	total=TGetW()+TGetC()+TGetG();
	return total;
}

class MTeacher //老师类数组计算 
{
	public:
		MTeacher( int sz=1)
		{
			size=sz;
			teacher=new Teacher[size];
		}
		~MTeacher() {delete [] teacher ; }
		void Menu(); //教工主菜单
		void Amend( ); //修改用户菜单
		void Add(); //增加用户
		void Delet(); // 删除用户
		void Check(); //查询是否交费
		void Pay(); //交水电煤气费
		// void display(); //展示所有的用户
		void Display(); //查看交费情况
		void Save();
	private:
		int size;
		Teacher *teacher;
};


void MTeacher::Pay() //用户交费
{
	char ch;
	string na;
	cout<<"\t\t\t欢迎使用交费功能"<<endl;
	cout<<"请输入姓名:"<<endl;
	cin>>na;
	int fl=0;
	double money,money2;
	for(int i=0;i<count2;i++)
		if(na==teacher[i].name)
		{
			fl=1;
			if(teacher[i].flag)
			{
				cout<<"该教工已缴纳过费用"<<endl; break;
			}
			else
			{
				cout<<"你应该交"<<teacher[i].Ttotal()<<"钱"<<endl;
				cout<<"确定是否真的现在交费 (Y/N)"<<endl;
				cin>>ch;
				if(ch=='Y'||ch=='y')
				{ 
					teacher[i].flag=1;
					cout<<"你实际交的钱是:"<<endl;
					cin>>money;
					if(money>teacher[i].Ttotal())
						cout<<"找零"<<money-teacher[i].Ttotal()<<"钱"<<endl;
					if(teacher[i].Ttotal()>money)
					{ 
						cout<<"交的钱不够,还应交"<<teacher[i].Ttotal()-money<<"钱"<<endl;
						cout<<"补交:"; cin>>money2;
					}
					cout<<"交费成功"<<endl;count22++;
					Menu();
				}
				else 
					Menu();
			}
		}
		if(!fl)
			cout<<"没有这个教工"<<endl;
 }

void MTeacher::Add() //增加教工
{
	cout<<"\t\t\t欢迎使用增加教工功能"<<endl;
	cout<<"请输入教工的姓名"<<endl;
	cin>>teacher[count2].name;
	cout<<"请输入教工的用水量"<<endl;
	cin>>teacher[count2].water;
	cout<<"请输入教工的用电量"<<endl;
	cin>>teacher[count2].power;
	cout<<"请输入教工的用煤气量"<<endl;
	cin>>teacher[count2].gas; count2++;
	cout<<"\t\t\t教工已被成功增加!"<<endl;
}

void MTeacher::Delet( ) //删除教工 
{
	cout<<"\t\t\t欢迎使用删除教工功能"<<endl;
	int fl=0;
	int i,j;
	string name;
	cout<<"请输入要删除教工的姓名"<<endl;
	cin>>name;
	for(i=0;i<count2;i++) if(name==teacher[i].name) 
	{
		fl=1;
		break;
	}
	if(fl)
	{
		for(j=i;j<count2-1;j++) //不能写成
			teacher[j]=teacher[j+1];
		count2--;
		cout<<"你已成功删除教工信息"<<endl;
	}
	else
		cout<<"没有这个教工"<<endl;
}

void MTeacher::Check( ) //查找交费情况 
{ 
	cout<<"\t\t\t欢迎使用查费功能"<<endl;
	string na;
	int i=0,fl=0;
	cout<<"请你输入姓名"<<endl;
	cin>>na;
	for(i=0;i<count2;i++)
		if(na==teacher[i].name)
		{
			fl=1;
			break;
		}
		if(fl)
		{
			cout<<"姓名 "<<"用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费 "<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
			cout<<teacher[i]<<endl;
		}
		else
			cout<<"没有这个教工"<<endl;
}

void MTeacher::Display() //查看交费情况
{
	int i;
	if(count22==0)
		cout<<"暂时还无教工交钱"<<endl;
	else
	{
		cout<<"已交费人员信息"<<endl;
		cout<<"姓名 "<<"用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费"<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
		for( i=0;i<count2;i++)
			if(teacher[i].flag)
				cout<<teacher[i];
	}//cout<<count22;
	if(count22==count2) 
		cout<<"所有的教工均以交钱"<<endl;
	else
	{ 
		cout<<"未交费人员信息"<<endl;
		cout<<"姓名 "<<"用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费"<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
		for(i=0;i<count2;i++)
			if(!teacher[i].flag)
				cout<<teacher[i];
	}
}

void MTeacher::Save()
{ 
	ofstream f("Teacher2.txt");
	f<<"学号 "<<"姓名 "<<" 用水量 "<<"用电量 "<<"用煤气量 "<<"水费 "<<"电费 "<<"煤气费 "<<" 总额 "<<" 是否交费"<<endl;
	for(int i=0;i<count2;i++)
		f<<teacher[i]<<endl;
	f.close();
	cout<<"\t\t教工信息以成功保存到磁盘文件中"<<endl;
}

void MTeacher::Amend() //修改菜单
{
	int choice;
	cout<<"\t\t 修改教工信息"<<endl<<endl;
	cout<<" 1,增加教工"<<endl;
	cout<<" 2,删除教工"<<endl;
	cout<<" 0,返回上一层"<<endl;
	cout<<"*******************************************"<<endl;
	while(1)
	{
		cout<<"请输入你的选择"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1: Add();
				break;
			case 2: Delet();
				break;
			case 0:	Menu();
				break;
		}
		if(choice==0)
			break;
	}
}

void MTeacher::Menu() //教工主菜单
{
	int choice;
	cout<<"\t\t 欢迎使用教工用户电煤气管理系统"<<endl<<endl;
	cout<<" 1,注册并修改用户"<<endl;
	cout<<" 2,缴纳费用"<<endl;
	cout<<" 3,查询费用"<<endl;
	cout<<" 4,显示交费情况"<<endl;
	cout<<" 5,保存信息到文件"<<endl;
	cout<<" 0,返回上一层"<<endl;
	cout<<"温馨提示:第一次登陆时,要进行注册!!"<<endl<<endl;
	cout<<"================================================"<<endl;
	for(;;)
	{
		cout<<"请输入您的选择"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1: Amend();
				break; //注册并修改教工
			case 2: Pay();
				break; //缴纳费用
			case 3: Check();
				break; //查询交费情况
			case 4: Display();
				break; //查看交费情况
			case 5: Save();
				break;
			case 0: break; //很有问题
		}
		if(choice==0) break;
	}
}

void main() //主函数

{
	MStudent student(200);
	MTeacher teacher(200);
	cout<<"友情提示:本程序的菜单中,,输入每个功能前相应的数字可实现相应的功能!"<<endl;
	int choice;
	for(;;)
	{
		cout<<"\t\t 欢迎使用高校电煤气管理系统\a"<<endl<<endl ;
		cout<<" 1,教工用户管理系统"<<endl<<endl;
		cout<<" 2,学生用户管理系统"<<endl<<endl;
		cout<<" 0,退出系统"<<endl<<endl;
		cout<<"********************************************************\n";
		cout<<"请输入你的选择"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1:teacher.Menu( );
				break;
			case 2:student.Menu( );
				break;
			case 0:break;
		}
		if(choice==0)
			break;
	}
}
