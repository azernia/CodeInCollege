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
    /// OverdueModel 的摘要说明:关于图书借阅超期信息的模型
    /// </summary>
    public class OverdueModel
    {
        private int bookId;                 /*图书编号*/
        private string bookName;            /*图书名称*/
        private string readerId;            /*读者编号*/
        private string readerName;          /*读者姓名*/
        private DateTime borrowTime;        /*借阅时间*/
        private int isContinue;             /*是否已经续借*/
        private DateTime shouldReturnTime;  /*应当归还时间*/
        private int overdueDays;            /*超期天数*/
        public int BookId
        {
            set { this.bookId = value; }
            get { return this.bookId; }
        }
        public string BookName
        {
            set { this.bookName = value; }
            get { return this.bookName; }
        }
        public string ReaderId
        {
            set { this.readerId = value; }
            get { return this.readerId; }
        }
        public string ReaderName
        {
            set { this.readerName = value; }
            get { return this.readerName; }
        }
        public DateTime BorrowTime
        {
            set { this.borrowTime = value;}
            get { return this.borrowTime; }
        }
        public int IsContinue
        {
            set { this.isContinue = value; }
            get { return this.isContinue; }
        }
        public DateTime ShouldReturnTime
        {
            set { this.shouldReturnTime = value; }
            get { return this.shouldReturnTime; }
        }
        public int OverdueDays
        {
            set { this.overdueDays = value; }
            get { return this.overdueDays; }
        }
        public OverdueModel()
        {
            bookId = 0;
            bookName = "";
            readerId = "";
            readerName = "";
            borrowTime = Convert.ToDateTime("1900-1-1");
            isContinue = 0;
            shouldReturnTime = Convert.ToDateTime("1900-1-1");
            overdueDays = 0;
        }
    }

}
