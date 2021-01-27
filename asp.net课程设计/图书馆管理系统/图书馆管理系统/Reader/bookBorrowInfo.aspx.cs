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

public partial class Reader_bookBorrowInfo : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        /*验证是否登陆了系统*/
        if (Session["readerFlag"] == null)
        {
            Response.Write("<script>top.location.href='../login.aspx';</script>");
            return;
        }
        /*得到该读者的所有借阅图书信息*/
        DataSet ds = LoanLogic.GetBookLoanInfo(Session["readerId"].ToString());
        if (ds.Tables[0].Rows.Count == 0)
        {
            this.Result.Text += "<tr><td colspan=4 align=center><font color=red>你还没有图书借阅信息!</font></td></tr>";
        }
        /*遍历输出该读者的所有借阅信息*/
        for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
        {
            DataRow dr = ds.Tables[0].Rows[i];
            this.Result.Text += "<tr><td><font color=red>" + dr["bookId"] + "</font></td>";
            this.Result.Text += "<td><font color=red>" + BooksLogic.GetBookNameById(Convert.ToInt32(dr["bookId"])) + "</font></td>";
            this.Result.Text += "<td><font color=red>" + Convert.ToDateTime(dr["borrowTime"]).ToShortDateString() + "</font></td>";
            /*如果该图书还没有续借*/
            if(Convert.ToInt32(dr["isContinue"]) == 0)
            {
                /*如果没有续借但已经超过归还时间了*/
                if (LoanLogic.IsOverdue(Convert.ToInt32(dr["loanId"])))
                {
                    this.Result.Text += "<td><font color=red>没续借但超期，请速还!</font></td>";
                }
                /*如果没有续借并且还没有超过归还时间*/
                else
                {
                    this.Result.Text += "<td><font color=red>没续借，可以&nbsp;<a href='bookContinueBorrow.aspx?loanId=" + dr["loanId"] + "'>续借</a></font><td>";
                }
            }
            /*如果该图书已经续借了*/
            else
            {
                if(LoanLogic.IsOverdue(Convert.ToInt32(dr["loanId"])))
                {
                    /*如果已经办理了续借但是超期了*/
                    this.Result.Text += "<td><font color=red>已续借但超期，请速还！</font></td>";
                }
                else
                {
                    /*如果已经办理了续节并且还没有超期*/
                    this.Result.Text += "<td><font color=red>已续节并且还没到期!</font></td>";
                }
            }
        }
    }
}
