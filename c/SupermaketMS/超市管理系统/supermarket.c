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
void InitData()  //�ļ���¼��
{
	int i=0;
	int num;
	char spm[12];
	int danjia;
	int shuliang;	
	FILE *fp = NULL;
	fp=fopen("����01.txt","wb");  //���ļ�
	if(fp==NULL)  //�ж��ļ��ܷ��
	{
		printf("�ļ��޷���!!\n");
	}
	while(i<11)
	{		
			printf("**********������Ʒ***********\n");
			printf("��������Ʒ�ı��:\n");
			scanf("%d",&num); 
			printf("��������Ʒ������:\n");
			scanf("%s",&spm);
			printf("��������Ʒ�ĵ���:\n");
			scanf("%d",&danjia);
			printf("��������Ʒ������:\n");
			scanf("%d",&shuliang); 
			spA[i].flag='!';
			spA[i].num=num;
			strcpy(spA[i].spm,spm);
			spA[i].danjia=danjia;
			spA[i].shuliang=shuliang;
			spA[i].sum=0;
			printf("�Ƿ�������?(y/n)\n");
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
	printf("��Ʒ�ı��:%d\n",sp->num);
	printf("��Ʒ������:%s\n",sp->spm);
	printf("��Ʒ�ĵ���:%d\n",sp->danjia);
	printf("��Ʒ������:%d\n",sp->shuliang);
	printf("��Ʒ���ܼ�:%d\n",sp->sum);
}
void Delete()  //ִ��ɾ����Ʒ��Ϣ��ģ��
{
	struct MARKET sp;
	FILE *fp=NULL;
	int num=0;
	short temp=1,temp1;
	fp=fopen("����01.txt","rb+");  //���ļ�
	if(fp==NULL)  //�ж��ļ��ܷ��
	{
		printf("�ļ��޷���!!\n");
	}
	while(temp)
	{
		printf("**********ɾ����Ʒ��Ϣ!\n***********");
		printf("������ɾ����Ʒ�ı��:\n");
		scanf("%d",&num);  //������Ʒ���
		rewind(fp);  //�ļ���ָ��������ͷ
		temp1=0;
		while(!feof(fp))
		{
			fread(&sp,sizeof(struct MARKET),1,fp);
			if(sp.flag=='!'&&sp.num==num)
			{
				Display(&sp);  //���ɾ����Ʒ����Ϣ
				temp1=1;
				printf("�����Ƿ�ɾ��?(y/n)\n");
				if(getch()=='y')  //�ж��Ƿ�ɾ��
				{
					fseek(fp,-(long)(sizeof(struct MARKET)),SEEK_CUR);
					fputc(42,fp);
				}
				break;
			}
		}	
		if(temp1==0)
		{
			printf("ɾ����Ʒ������!\n");
		}
		printf("�Ƿ����ɾ��?(y/n)\n");		
		if(getch()!='y')
		{
			fclose(fp);
			temp=0;
		}
	}
	
}
void Search()  //ִ�в�����Ʒ��Ϣ��ģ��
{
	struct MARKET sp;
	FILE *fp=NULL;
	int num;
	short temp=1,temp1;
	fp=fopen("����01.txt","rb");
	if(fp==NULL)
	{
		printf("�ļ��޷���!!\n");
	}
	while(temp)
	{
		rewind(fp);  //�ļ���ָ��������ͷ
		temp1=0;
		printf("***********������Ʒ!**********\n");
	    printf("�������ѯ��Ʒ�ı��:\n");
	    scanf("%d",&num);		
		while(!feof(fp))
		{
			fread(&sp,sizeof(struct MARKET),1,fp);
			if(sp.flag==33&&sp.num==num)
			{     
				printf("������Ϣ����:\n");
				Display(&sp);  //���������Ϣ
				temp1=1;
				break;  //����ѭ��
			}
		}
		if(temp1==0)
		{
			printf("��ѯ��Ʒ������!\n");
		}
		printf("�Ƿ������ѯ?(y/n)\n");		
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
	fp=fopen("����01.txt","rb+");
	if(fp==NULL)
	{
		printf("�ļ��޷���!!\n");
	}
	rewind(fp);
	fread(&sp,sizeof(struct MARKET),3,fp);
	for(i=0;i<3;i++)	
	{
		sp[i].sum=sp[i].danjia*sp[i].shuliang;  //������Ʒ�ܼ�
		printf("�ܼ���ͽ������:%d\n",sp[i].sum);//���������
		//Display(&sp[i]);
	}
    fp=fopen("����01.txt","rb+");
	fwrite(&sp,sizeof(struct MARKET),3,fp); //�����д���ļ�
	fclose(fp);
}
void PaiXu()  //��Ʒ�ܼ۵�����ģ��
{
	struct MARKET temp;
	struct MARKET z[3];
	int n=11,i,j;
	FILE *fp=NULL;
	FILE *fp1=NULL;
	fp=fopen("����01.txt","rb");
	if(fp==NULL)
	{
		printf("�ļ��޷���!!\n");
	}
		rewind(fp);
		fread(&z,sizeof(struct MARKET),3,fp);
		printf("**********��Ʒ�ܼ۵�����**********\n");
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
		fp1=fopen("����02.txt","wb");  //���µ��ļ�
		printf("����������:\n");
        for(i=0;i<3;i++)
		{
			printf("%d\t%s\t%d\t%d\t%d",z[i].num,z[i].spm,z[i].danjia,z[i].shuliang,z[i].sum);
            printf("\n");
			fwrite(&z,sizeof(struct MARKET),1,fp1);  //д���µ��ļ�
		}
	fclose(fp);
	fclose(fp1);
}
main()
{
    short temp=1;
	int a;
	long password;
	printf("����������:\n");
	scanf("%ld",&password);  //���������
	if(password==696969)  //�ж������Ƿ���ȷ
	{
		printf("**********��¼�ɹ�**********");
		while(temp)
		{
			printf("��ѡ��ִ�еĲ���:\n");
			printf("1 ��Ʒ��Ϣ¼��\n2 ��Ʒ��Ϣ��ɾ��\n3 ��Ʒ��Ϣ�Ĳ���\n4 ������Ʒ�ܼ�\n5 ��Ʒ��Ʒ�ܼ۵�����\n0 ��������\n");
			scanf("%d",&a); //ѡ�����
			switch(a)
			{
			case 1:InitData();
				break;
			case 2:Delete();  //��ת��ɾ��ģ��
				break;
			case 3:Search();  //��ת������ģ��
				break;
			case 4:Sum();  //��ת���ܼ�ģ��
				break;
			case 5:PaiXu();  //��ת������ģ��
				break;
			case 0:exit(0);  //��������
			} 

			printf("�Ƿ����?(y/n)\n");		
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