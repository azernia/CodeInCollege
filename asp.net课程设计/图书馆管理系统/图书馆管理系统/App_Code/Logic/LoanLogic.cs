using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

using Library.Model;
using Library.Utility;
using System.Collections;

namespace Library.Logic
{
    /// <summary>
    /// LoanLogic 的摘要说明：关于图书借阅的业务处理
    /// </summary>
    public class LoanLogic
    {
        private string errMessage;
        public string ErrMessage
        {
            set { this.errMessage = value; }
            get { return this.errMessage; }
        }
        public LoanLogic()
        {
            this.errMessage = "";
        }
        /*根据图书编号和读者编号加入新的借阅信息*/
        public bool AddNewLoanInfo(int bookId,string readerId)
        {
            /*查询是否存在该图书的信息*/
            if (!BooksLogic.IsExistBook(bookId))
            {
                this.errMessage = "对不起，该图书不存在!";
                return false;
            }
            /*查询该图书的库存是否大于0*/
            if (BooksLogic.GetBookNumById(bookId) <= 0)
            {
                this.errMessage = "对不起，该图书已借完!";
                return false;
            }
            /*查询该读者的信息是否存在*/
            if (!ReaderLogic.IsExistReaderInfo(readerId))
            {
                this.errMessage = "对不起，该读者信息不存在!";
                return false;
            }
            /*判断是否可以继续借阅图书*/
            if (!this.CanLoanBook(readerId))
            {
                this.errMessage = "对不起，你借阅书的数量满了!";
                return false;
            }
            /*判断该读者是否已经借阅了该书*/
            if (this.IsReaderBorrowBook(bookId, readerId))
            {
                this.errMessage = "对不起，该读者已经借阅了该书";
                return false;
            }
            /*将读者的借阅信息加入到借阅信息表*/
            LoanModel loanModel = new LoanModel();
            loanModel.ReaderId = readerId;
            loanModel.BookId = bookId;
            loanModel.BorrowTime = System.DateTime.Now;
            string sqlString = "insert into [loan_130610](readerId,bookId,borrowTime,returnTime,isReturn,continueBorrowTime,isContinue) values('";
            sqlString += loanModel.ReaderId + "',";
            sqlString += loanModel.BookId + ",'";
            sqlString += loanModel.BorrowTime + "','";
            sqlString += loanModel.ReturnTime + "',";
            sqlString += loanModel.IsReturn + ",'";
            sqlString += loanModel.ContinueBorrowTime + "',";
            sqlString += loanModel.IsContinue + ")";
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "图书借阅时发生了错误!";
                return false;
            }
            return true;
        }

        /*判断某个读者是否可以继续借阅图书*/
        public bool CanLoanBook(string readerId)
        {
            /*查询该读者可以借多少本书*/
            string queryString = "select loanNum from [readerType_130610],[reader_130610] where [readerType_130610].readerTypeId=[reader_130610].readerTypeId and [reader_130610].readerId='" + readerId + "'";
            int loanNum = Convert.ToInt32(DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,queryString,null));
            /*查询该读者已经借阅了多少本书了*/
            queryString = "select count(*) as haveLoanNum from [loan_130610] where isReturn=0 and readerId='" + readerId + "'";
            int haveLoanNum = Convert.ToInt32(DBOperation.ExecuteScalar(DBOperation.CONN_STRING_NON_DTC,CommandType.Text,queryString,null));
            return (loanNum > haveLoanNum);
        }

        /*根据图书编号，读者编号实现图书的归还*/
        public bool ReturnBook(int bookId,string readerId)
        {
            /*查询是否存在该图书的信息*/
            if (!BooksLogic.IsExistBook(bookId))
            {
                this.errMessage = "对不起，该图书不存在!";
                return false;
            }
            /*查询该读者的信息是否存在*/
            if (!ReaderLogic.IsExistReaderInfo(readerId))
            {
                this.errMessage = "对不起，该读者信息不存在!";
                return false;
            }
            /*判断该读者是否已经借阅了该书*/
            if (!this.IsReaderBorrowBook(bookId, readerId))
            {
                this.errMessage = "对不起，该读者并没有借该书!";
                return false;
            }
            /*更新图书借阅信息表实现图书的归还操作*/
            string sqlString = "update [loan_130610] set isReturn=1,returnTime='" + System.DateTime.Now + "' where bookId=" + bookId + " and readerId='" + readerId + "'";
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "图书归还时发生了数据错误!";
                return false;
            }
            return true;
        }

        /*判断某个读者是否已经借阅了某本书*/
        public bool IsReaderBorrowBook(int bookId, string readerId)
        {
            string queryString = "select * from [loan_130610] where isReturn=0 and bookId=" + bookId + " and readerId='" + readerId + "'";
            return DBOperation.ExecuteReader(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null).Read();
        }

        /*得到借阅超期信息*/
        public ArrayList GetOverdueInfo()
        {
            /*
            对不起,此功能被屏蔽,有需要的朋友可以联系作者   
            姓名: 汪建林		生日:1985年1月7日(农历)
            星座: 双鱼座(故名双鱼林) 血型: 0
            毕业学校: 2007年于成都理工大学
            家乡地址: 四川渠县望溪乡包山村5组
            联系QQ: 287307421  联系电话: 13558690869
            联系Email: wangjianlin1985@126.com
            双鱼林电脑工作室淘宝店: http://shop34864101.taobao.com
            双鱼林电脑工作室拍拍店: http://287307421.paipai.com
            如果朋友们觉得我的设计还看得,可以联系我设计,我熟悉ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver提供技术指导!
             */
            return null;
        }

        /*根据读者编号得到该读者所有的图书借阅信息*/
        public static DataSet GetBookLoanInfo(string readerId)
        {
            string queryString = "select * from [loan_130610] where isReturn=0 and readerId='" + readerId + "'";
            return DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null);
        }

        /*根据借阅编号判断某个图书借阅是否已经超期*/
        public static bool IsOverdue(int loanId)
        {
            bool overDueFlag = false;
            string queryString = "select * from [loan_130610] where loanId=" + loanId;
            DataSet ds = new DataSet();
            ds = DBOperation.GetDataSet(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, queryString, null);
            DataRow dr = ds.Tables[0].Rows[0];
            int isContinue = Convert.ToInt32(dr["isContinue"]);
            /*如果没有续借*/
            if (isContinue == 0)
            {
                /*得到借书时间*/
                DateTime borrowTime = Convert.ToDateTime(dr["borrowTime"]).Date;
                /*得到该读者可以借书的天数*/
                int loanDays = ReaderLogic.GetLoanDays(dr["readerId"].ToString());
                /*计算得到应当归还图书的时间*/
                DateTime shouldReturnTime = borrowTime.AddDays(loanDays);
                /*如果今天超过了应当归还的日期*/
                if (DateTime.Now.Date > shouldReturnTime) overDueFlag = true;
            }
            else
            {
                /*得到续借图书时间*/
                DateTime continueBorrowTime = Convert.ToDateTime(dr["continueBorrowTime"]).Date;
                /*得到该读者可以续借的天数*/
                int continueDays = ReaderLogic.GetContinueDays(dr["readerId"].ToString());
                /*计算应当归还图书的时间*/
                DateTime shouldReturnTime = continueBorrowTime.AddDays(continueDays);
                /*如果今天超过了应当归还的日期*/
                if (DateTime.Now.Date > shouldReturnTime) overDueFlag = true;
            }
            return overDueFlag;
        }

        /*根据借阅编号办理续借操作*/
        public bool ContinueBorrowBook(int loanId)
        {
            string sqlString = "update [loan_130610] set isContinue=1 and continueBorrowTime='" + System.DateTime.Now.Date + "' where loanId=" + loanId;
            if (DBOperation.ExecuteNonQuery(DBOperation.CONN_STRING_NON_DTC, CommandType.Text, sqlString, null) < 0)
            {
                this.errMessage = "办理图书续借时发生了错误!";
                return false;
            }
            return true;
        }
    }
}
