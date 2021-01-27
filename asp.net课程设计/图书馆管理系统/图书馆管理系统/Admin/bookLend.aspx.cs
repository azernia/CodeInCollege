using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

using Library.Model;
using Library.Logic;

public partial class Admin_bookLend : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            /*验证是否登陆了系统*/
            if (Session["adminFlag"] == null)
            {
                Response.Write("<script>top.location.href='../login.aspx';</script>");
                return;
            }
        }
    }
    protected void Btn_Add_Click(object sender, EventArgs e)
    {
        string readerId = this.ReaderId.Text;
        int bookId = Int32.Parse(this.BookId.Text);
        LoanLogic loanLogic = new LoanLogic();
        if (loanLogic.AddNewLoanInfo(bookId, readerId))
            this.Result.Text = "读者:" + ReaderLogic.GetReaderNameById(readerId) + "借阅图书:" + BooksLogic.GetBookNameById(bookId) + " 成功!";
        else
            this.Result.Text = loanLogic.ErrMessage;
        this.ReaderId.Text = "";
        this.BookId.Text = "";
    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        this.BookId.Text = "";
        this.ReaderId.Text = "";
        this.Result.Text = "";
    }
}
