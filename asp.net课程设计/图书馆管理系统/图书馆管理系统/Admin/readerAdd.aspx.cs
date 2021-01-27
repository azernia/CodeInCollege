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

public partial class Admin_readerAdd : System.Web.UI.Page
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
        this.ReaderPhoto.ImageUrl = "ReaderPhoto\\NoImage.jpg";
    }
    /*如果需要上传读者照片*/
    protected void Btn_Upload_Click(object sender, EventArgs e)
    {
        /*如果上传了文件*/
        if (this.ReaderPhotoUpload.PostedFile.ContentLength > 0)
        {
            /*验证上传的文件格式，只能为gif和jpeg格式*/
            string mimeType = this.ReaderPhotoUpload.PostedFile.ContentType;
            if (String.Compare(mimeType, "image/gif", true) == 0 || String.Compare(mimeType, "image/pjpeg", true) == 0)
            {
                //this.DeviceImagePath.Text = "上传文件中....";
                string extFileString = System.IO.Path.GetExtension(this.ReaderPhotoUpload.PostedFile.FileName); /*获取文件扩展名*/
                string saveFileName = this.MakeFileName(extFileString); /*根据扩展名生成文件名*/
                string imagePath = "ReaderPhoto\\" + saveFileName;/*图片路径*/
                this.ReaderPhotoUpload.PostedFile.SaveAs(Server.MapPath(imagePath));
                this.ReaderPhoto.ImageUrl = imagePath;
                this.ReaderPhotoUrl.Text = imagePath;
            }
            else
            {
                Response.Write("<script>alert('上传文件格式不正确!');</script>");
            }
        }
    }

    /*根据当前系统时间生成一个文件名*/
    private string MakeFileName(string exeFileString)
    {
        System.DateTime now = System.DateTime.Now;
        int year = now.Year;
        int month = now.Month;
        int day = now.Day;
        int hour = now.Hour;
        int minute = now.Minute;
        int second = now.Second;

        string yearString = year.ToString();
        string monthString = month < 10 ? ("0" + month) : month.ToString();
        string dayString = day < 10 ? ("0" + day) : day.ToString();
        string hourString = hour < 10 ? ("0" + hour) : hour.ToString();
        string minuteString = minute < 10 ? ("0" + minute) : minute.ToString();
        string secondString = second < 10 ? ("0" + second) : second.ToString();

        /*根据当前时间的年月日时分秒生成文件名*/
        string fileName = yearString + monthString + dayString + hourString + minuteString + secondString + exeFileString;
        return fileName;
    }
    protected void Btn_Add_Click(object sender, EventArgs e)
    {
        /*建立读者对象模型，将读者的各个信息传入模型的对应属性中*/
        ReaderModel readerModel = new ReaderModel();
        readerModel.ReaderId = this.ReaderId.Text;
        readerModel.ReaderPassword = this.ReaderPassword.Text;
        readerModel.ReaderName = this.ReaderName.Text;
        readerModel.ReaderSex = this.ReaderSex.SelectedValue;
        if (this.ReaderBirthday.Text != "")
            readerModel.ReaderBirthday = Convert.ToDateTime(this.ReaderBirthday.Text);
        readerModel.ReaderTypeId = Int32.Parse(this.ReaderType.SelectedValue);
        readerModel.ReaderPhone = this.ReaderPhone.Text;
        readerModel.ReaderEmail = this.ReaderEmail.Text;
        readerModel.ReaderAddress = this.ReaderAddress.Text;
        readerModel.ReaderPhotoUrl = this.ReaderPhotoUrl.Text;
        readerModel.ReaderMemo = this.ReaderMemo.Text;
        /*调用业务层执行新读者信息的添加*/
        ReaderLogic readerLogic = new ReaderLogic();
        if (readerLogic.AddNewReaderInfo(readerModel))
        {
            Response.Write("<script>alert('读者信息添加成功!');location.href='readerAdd.aspx';</script>");
        }
        else
            Response.Write("<script>alert('" + readerLogic.ErrMessage + "');</script>");
    }
    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        Response.Write("<script>location.href='readerAdd.aspx';</script>");
    }
}
