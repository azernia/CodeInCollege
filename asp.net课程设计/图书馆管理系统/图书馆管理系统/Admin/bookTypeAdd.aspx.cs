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

public partial class Admin_bookTypeAdd : System.Web.UI.Page
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
        BookTypeModel bookTypeModel = new BookTypeModel();
        bookTypeModel.BookTypeName = this.BookTypeName.Text;
        BookTypeLogic bookTypeLogic = new BookTypeLogic();
        if (bookTypeLogic.InsertBookTypeInfo(bookTypeModel))
        {
            Response.Write("<script>alert('图书类型信息添加成功!');location.href='bookTypeAdd.aspx';</script>");
        }
        else
        {
            Response.Write("<script>alert('" + bookTypeLogic.ErrMessage + "');</script>");
        }
    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        this.BookTypeName.Text = "";
    }
}
