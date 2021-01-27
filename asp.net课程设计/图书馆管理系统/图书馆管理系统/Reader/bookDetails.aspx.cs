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
//该源码首发自www.51aspx.com(５１aｓｐｘ．ｃｏｍ)

using Library.Model;
using Library.Logic;

public partial class Reader_bookDetails : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        /*验证是否登陆了系统*/
        if (Session["readerFlag"] == null)
        {
            Response.Write("<script>top.location.href='../login.aspx';</script>");
            return;
        }
        /*根据图书编号初始化该图书的各个属性显示*/
        InitView();
    }
    private void InitView()
    {
        /*根据图书编号得到该图书对象的详细信息*/
        int bookId = Int32.Parse(Request.QueryString["bookId"]);
        BooksLogic booksLogic = new BooksLogic();
        BooksModel booksModel = booksLogic.GetBookInfo(bookId);
        /*将该图书对象的详细信息显示在界面上*/
        this.BookType.Text = BookTypeLogic.GetBookTypeNameById(booksModel.BookTypeId);
        this.BookName.Text = booksModel.BookName;
        this.Author.Text = booksModel.Author;
        this.Publishing.Text = booksModel.Publishing;
        this.BookMoney.Text = booksModel.BookMoney.ToString();
        this.BookNum.Text = booksModel.BookNum.ToString();
        this.Introduction.Text = booksModel.Introduction;
    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        Response.Write("<script>location.href='bookQuery.aspx';</script>");
    }
}
