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
using Library.Model;

public partial class Admin_bookTypeDel : System.Web.UI.Page
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
        int bookTypeId = Int32.Parse(Request.QueryString["bookTypeId"]);
        BookTypeLogic bookTypeLogic = new BookTypeLogic();
        if (bookTypeLogic.DeleteBookType(bookTypeId))
        {
            Response.Write("<script>alert('图书类别信息删除成功!');location.href='bookTypeManage.aspx';</script>");
        }
        else
        {
            Response.Write("<script>alert('" + bookTypeLogic.ErrMessage + "');location.href='bookTypeManage.aspx';</script>");
        }

    }
}
