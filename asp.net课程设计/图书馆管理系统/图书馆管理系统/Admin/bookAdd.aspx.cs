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

public partial class Admin_bookAdd : System.Web.UI.Page
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
        /*首先构造图书对象的模型，并将界面输入的各个信息保存模型的对应属性中*/
        BooksModel booksModel = new BooksModel();
        booksModel.BookName = this.BookName.Text;
        booksModel.BookTypeId = Int32.Parse(this.BookType.SelectedValue);
        booksModel.Author = this.Author.Text;
        booksModel.Publishing = this.Publishing.Text;
        booksModel.BookMoney = Convert.ToSingle(this.BookMoney.Text);
        booksModel.BookNum = Convert.ToInt32(this.BookNum.Text);
        booksModel.Introduction = this.Introduction.Text;
        /*然后调用业务层将此图书信息加入系统中*/
        BooksLogic booksLogic = new BooksLogic();
        if (booksLogic.AddNewBook(booksModel))
        {
            Response.Write("<script>alert('图书信息添加成功!');location.href='bookAdd.aspx';</script>");
        }
        else
        {
            Response.Write("<script>alert('"+ booksLogic.ErrMessage + "');</script>");
        }

    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        Response.Write("<script>location.href='bookAdd.aspx';</script>");
    }
}
