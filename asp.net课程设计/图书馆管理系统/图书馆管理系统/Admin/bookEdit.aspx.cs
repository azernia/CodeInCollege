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

public partial class Admin_bookEdit : System.Web.UI.Page
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
            /*初始化图书类别下拉框的信息*/
            DataSet ds = (new BookTypeLogic()).GetBookTypeInfo();
            for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
            {
                DataRow dr = ds.Tables[0].Rows[i];
                ListItem li = new ListItem(dr["bookTypeName"].ToString(), dr["bookTypeId"].ToString());
                this.BookType.Items.Add(li);
            }
            /*根据图书编号初始化该图书的各个属性显示*/
            InitView();
        }
    }
    protected void Btn_Update_Click(object sender, EventArgs e)
    {
        /*建立图书模型，并将图书对象的各个属性保存起来*/
        BooksModel booksModel = new BooksModel();
        booksModel.BookId = Int32.Parse(Request.QueryString["bookId"]);
        booksModel.BookName = this.BookName.Text;
        booksModel.BookTypeId = Int32.Parse(this.BookType.SelectedValue);
        booksModel.Author = this.Author.Text;
        booksModel.Publishing = this.Publishing.Text;
        booksModel.BookMoney = Convert.ToSingle(this.BookMoney.Text);
        booksModel.BookNum = Convert.ToInt32(this.BookNum.Text);
        booksModel.Introduction = this.Introduction.Text;
        /*调用业务层执行图书信息的更新操作*/
        BooksLogic booksLogic = new BooksLogic();
        if (booksLogic.UpdateOneBook(booksModel))
        {
            Response.Write("<script>alert('图书信息更新成功!');location.href='bookEdit.aspx?bookId=" + Request.QueryString["bookId"] + "';</script>");
        }
        else
        {
            Response.Write("<script>alert('" + booksLogic.ErrMessage + "');</script>");
        }
    }

    private void InitView()
    {
        /*根据图书编号得到该图书对象的详细信息*/
        int bookId = Int32.Parse(Request.QueryString["bookId"]);
        BooksLogic booksLogic = new BooksLogic();
        BooksModel booksModel = booksLogic.GetBookInfo(bookId);
        /*将该图书对象的详细信息显示在界面上*/
        this.BookType.SelectedValue = booksModel.BookTypeId.ToString();
        this.BookName.Text = booksModel.BookName;
        this.Author.Text = booksModel.Author;
        this.Publishing.Text = booksModel.Publishing;
        this.BookMoney.Text = booksModel.BookMoney.ToString();
        this.BookNum.Text = booksModel.BookNum.ToString();
        this.Introduction.Text = booksModel.Introduction;
    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        Response.Write("<script>location.href='booksManage.aspx';</script>");
    }
}
