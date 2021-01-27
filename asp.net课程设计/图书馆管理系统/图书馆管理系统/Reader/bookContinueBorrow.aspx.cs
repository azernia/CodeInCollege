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

using Library.Logic;

public partial class Reader_bookContinueBorrow : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        /*验证是否登陆了系统*/
        if (Session["readerFlag"] == null)
        {
            Response.Write("<script>top.location.href='../login.aspx';</script>");
            return;
        }
        int loanId = Int32.Parse(Request.QueryString["loanId"]);
        LoanLogic loanLogic = new LoanLogic();
        if (loanLogic.ContinueBorrowBook(loanId))
        {
            Response.Write("<script>alert('图书续借成功!');location.href='bookBorrowInfo.aspx';</script>");
        }
        else
        {
            Response.Write("<script>alert('" + loanLogic.ErrMessage + "');location.href='bookBorrowInfo.aspx';</script>");
        }


    }
}
