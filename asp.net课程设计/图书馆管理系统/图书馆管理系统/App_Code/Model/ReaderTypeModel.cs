using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

namespace Library.Model
{
    /// <summary>
    /// ReaderTypeModel 的摘要说明:关于读者类型的数据模型类，对应数据中的读者类型信息表reaerType表
    /// </summary>
    public class ReaderTypeModel
    {
        /*读者类型信息表
        CREATE TABLE [dbo].[readerType_130610] (
	        [readerTypeId] [int] IDENTITY (1, 1) NOT NULL ,					    //类型编号
	        [readerTypeName] [nvarchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,   //类型名称
          	[loanNum] [int] NULL ,								                //可借图书数目
	        [loanDays] [int] NULL ,								                //可借书天数
	        [continueDays] [int] NULL 							                //可续借书天数
        ) ON [PRIMARY]*/
        private int readerTypeId;
        private string readerTypeName;
        private int loadNum;
        private int loanDays;
        private int continueDays;
        public int ReaderTypeId
        {
            set { this.readerTypeId = value; }
            get { return this.readerTypeId; }
        }
        public string ReaderTypeName
        {
            set { this.readerTypeName = value; }
            get { return this.readerTypeName; }
        }
        public int LoanNum
        {
            set { this.loadNum = value; }
            get { return this.loadNum; }
        }
        public int LoanDays
        {
            set { this.loanDays = value; }
            get { return this.loanDays; }
        }
        public int ContinueDays
        {
            set { this.continueDays = value; }
            get { return this.continueDays; }
        }
        public ReaderTypeModel()
        {
            this.readerTypeId = 0;
            this.readerTypeName = "";
            this.loanDays = 0;
            this.continueDays = 0;
        }
    }

}
