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
    /// LoanModel 的摘要说明：图书借阅信息的模型，对应数据库中的借阅信息表loan表
    /// </summary>
    public class LoanModel
    {
        /*图书借用信息表
        CREATE TABLE [dbo].[loan_130610] (
	        [loanId] [int] IDENTITY (1, 1) NOT NULL ,			        //借用编号
	        [readerId] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,  //读者编号
	        [bookId] [int] NULL ,								        //图书编号
	        [borrowTime] [datetime] NULL ,							    //借阅时间
	        [returnTime] [datetime] NULL ,							    //归还时间
	        [isReturn] [int] NULL ,								        //是否已经归还
	        [continueBorrowTime] [datetime] NULL ,						//续借时间
	        [isContinue] [int] NULL 							        //是否续借
        ) ON [PRIMARY]*/
        private int loanId;
        private string readerId;
        private int bookId;
        private DateTime borrowTime;
        private DateTime returnTime;
        private int isReturn;
        private DateTime continueBorrowTime;
        private int isContinue;
        public int LoanId
        {
            set { this.loanId = value; }
            get { return this.loanId; }
        }
        public string ReaderId
        {
            set { this.readerId = value; }
            get { return this.readerId; }
        }
        public int BookId
        {
            set { this.bookId = value; }
            get { return this.bookId; }
        }
        public DateTime BorrowTime
        {
            set { this.borrowTime = value; }
            get { return this.borrowTime; }
        }
        public DateTime ReturnTime
        {
            set { this.returnTime = value; }
            get { return this.returnTime; }
        }
        public int IsReturn
        {
            set { this.isReturn = value; }
            get { return this.isReturn; }
        }
        public DateTime ContinueBorrowTime
        {
            set { this.continueBorrowTime = value; }
            get { return this.continueBorrowTime; }
        }
        public int IsContinue
        {
            set { this.isContinue = value; }
            get { return this.isContinue; }
        }
        public LoanModel()
        {
            loanId = 0;
            readerId = "";
            bookId = 0;
            borrowTime = Convert.ToDateTime("1900-1-1");
            returnTime = Convert.ToDateTime("1900-1-1");
            isReturn = 0;
            continueBorrowTime = Convert.ToDateTime("1900-1-1");
            isContinue = 0;
        }
    }

}
