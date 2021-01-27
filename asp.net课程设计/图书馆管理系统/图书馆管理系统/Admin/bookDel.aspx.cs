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

public partial class Admin_bookDel : System.Web.UI.Page
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
            /*取得传入的待删除的图书对象的编号*/
            int bookId = Int32.Parse(Request.QueryString["bookId"]);
            BooksLogic booksLogic = new BooksLogic();
            /*调用业务层执行图书信息删除操作*/
            if (booksLogic.DeleteBooksInfo(bookId))
            {
                Response.Write("<script>alert('图书信息删除成功!');location.href='booksManage.aspx';</script>");
            }
            else
            {
                Response.Write("<script>alert('" + booksLogic.ErrMessage + "');location.href='booksManage.aspx';</script>");
            }
        }
    }
}
