#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define SHULIANG 11
struct MARKET
{
	char flag;
	int num;
	char spm[12];
	int danjia;
	int shuliang;	
	int sum;
//	int PaiXu[11];
}spA[SHULIANG],z[SHULIANG];
void InitData()  //文件的录入
{
	int i=0;
	int num;
	char spm[12];
	int danjia;
	int shuliang;	
	FILE *fp = NULL;
	fp=fopen("超市01.txt","wb");  //打开文件
	if(fp==NULL)  //判断文件能否打开
	{
		printf("文件无法打开!!\n");
	}
	while(i<11)
	{		
			printf("**********输入商品***********\n");
			printf("请输入商品的编号:\n");
			scanf("%d",&num); 
			printf("请输入商品的名称:\n");
			scanf("%s",&spm);
			printf("请输入商品的单价:\n");
			scanf("%d",&danjia);
			printf("请输入商品的数量:\n");
			scanf("%d",&shuliang); 
			spA[i].flag='!';
			spA[i].num=num;
			strcpy(spA[i].spm,spm);
			spA[i].danjia=danjia;
			spA[i].shuliang=shuliang;
			spA[i].sum=0;
			printf("是否继续添加?(y/n)\n");
			if(getch()=='n')
			{
				break;
			}
			i++;
	}
	fwrite(&spA,sizeof(struct MARKET),i+1,fp);
	fclose(fp);
}
void Display(struct MARKET *sp)
{
	printf("商品的编号:%d\n",sp->num);
	printf("商品的名称:%s\n",sp->spm);
	printf("商品的单价:%d\n",sp->danjia);
	printf("商品的数量:%d\n",sp->shuliang);
	printf("商品的总价:%d\n",sp->sum);
}
void Delete()  //执行删除商品信息的模块
{
	struct MARKET sp;
	FILE *fp=NULL;
	int num=0;
	short temp=1,temp1;
	fp=fopen("超市01.txt","rb+");  //打开文件
	if(fp==NULL)  //判断文件能否打开
	{
		printf("文件无法打开!!\n");
	}
	while(temp)
	{
		printf("**********删除商品信息!\n***********");
		printf("请输入删除商品的编号:\n");
		scanf("%d",&num);  //输入商品编号
		rewind(fp);  //文件内指针跳至开头
		temp1=0;
		while(!feof(fp))
		{
			fread(&sp,sizeof(struct MARKET),1,fp);
			if(sp.flag=='!'&&sp.num==num)
			{
				Display(&sp);  //输出删除商品的信息
				temp1=1;
				printf("请问是否删除?(y/n)\n");
				if(getch()=='y')  //判断是否删除
				{
					fseek(fp,-(long)(sizeof(struct MARKET)),SEEK_CUR);
					fputc(42,fp);
				}
				break;
			}
		}	
		if(temp1==0)
		{
			printf("删除商品不存在!\n");
		}
		printf("是否继续删除?(y/n)\n");		
		if(getch()!='y')
		{
			fclose(fp);
			temp=0;
		}
	}
	
}
void Search()  //执行查找商品信息的模块
{
	struct MARKET sp;
	FILE *fp=NULL;
	int num;
	short temp=1,temp1;
	fp=fopen("超市01.txt","rb");
	if(fp==NULL)
	{
		printf("文件无法打开!!\n");
	}
	while(temp)
	{
		rewind(fp);  //文件内指针跳至开头
		temp1=0;
		printf("***********查找商品!**********\n");
	    printf("请输入查询商品的编号:\n");
	    scanf("%d",&num);		
		while(!feof(fp))
		{
			fread(&sp,sizeof(struct MARKET),1,fp);
			if(sp.flag==33&&sp.num==num)
			{     
				printf("查找信息如下:\n");
				Display(&sp);  //输出查找信息
				temp1=1;
				break;  //跳出循环
			}
		}
		if(temp1==0)
		{
			printf("查询商品不存在!\n");
		}
		printf("是否继续查询?(y/n)\n");		
		if(getch()!='y')
		{
			fclose(fp);
			temp=0;
		}
	}
}
void Sum()
{
	int i;
	int temp=1;
	struct MARKET sp[3];
	FILE *fp=NULL;
	fp=fopen("超市01.txt","rb+");
	if(fp==NULL)
	{
		printf("文件无法打开!!\n");
	}
	rewind(fp);
	fread(&sp,sizeof(struct MARKET),3,fp);
	for(i=0;i<3;i++)	
	{
		sp[i].sum=sp[i].danjia*sp[i].shuliang;  //计算商品总价
		printf("总价求和结果如下:%d\n",sp[i].sum);//输出计算结果
		//Display(&sp[i]);
	}
    fp=fopen("超市01.txt","rb+");
	fwrite(&sp,sizeof(struct MARKET),3,fp); //将结果写入文件
	fclose(fp);
}
void PaiXu()  //商品总价的排序模块
{
	struct MARKET temp;
	struct MARKET z[3];
	int n=11,i,j;
	FILE *fp=NULL;
	FILE *fp1=NULL;
	fp=fopen("超市01.txt","rb");
	if(fp==NULL)
	{
		printf("文件无法打开!!\n");
	}
		rewind(fp);
		fread(&z,sizeof(struct MARKET),3,fp);
		printf("**********商品总价的排序**********\n");
		for(i=0;i<3-1;i++)
		{
			for(j=i+1;j<3;j++)
			{
			if(z[j].sum>z[i].sum)
			{
				temp=z[j];
				z[j]=z[i];
				z[i]=temp;												
			}						
			}
		}		
		fp1=fopen("超市02.txt","wb");  //打开新的文件
		printf("排序结果如下:\n");
        for(i=0;i<3;i++)
		{
			printf("%d\t%s\t%d\t%d\t%d",z[i].num,z[i].spm,z[i].danjia,z[i].shuliang,z[i].sum);
            printf("\n");
			fwrite(&z,sizeof(struct MARKET),1,fp1);  //写入新的文件
		}
	fclose(fp);
	fclose(fp1);
}
main()
{
    short temp=1;
	int a;
	long password;
	printf("请输入密码:\n");
	scanf("%ld",&password);  //密码的输入
	if(password==696969)  //判断密码是否正确
	{
		printf("**********登录成功**********");
		while(temp)
		{
			printf("请选择执行的操作:\n");
			printf("1 商品信息录入\n2 商品信息的删除\n3 商品信息的查找\n4 计算商品总价\n5 商品商品总价的排序\n0 结束操作\n");
			scanf("%d",&a); //选择操作
			switch(a)
			{
			case 1:InitData();
				break;
			case 2:Delete();  //跳转至删除模块
				break;
			case 3:Search();  //跳转至查找模块
				break;
			case 4:Sum();  //跳转至总价模块
				break;
			case 5:PaiXu();  //跳转至排序模块
				break;
			case 0:exit(0);  //结束程序
			} 

			printf("是否继续?(y/n)\n");		
			if(getch()!='y')
			{
				temp=0;
			}
		}
	}
	else
	{
		printf("PASSWORD WRONG!!\n");
	} 
}