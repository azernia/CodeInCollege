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

public partial class Admin_readerEdit : System.Web.UI.Page
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
            /*初始化读者类别下拉框*/
            this.ReaderType.DataSource = (new ReaderTypeLogic()).GetReaderTypeInfo();
            this.ReaderType.DataTextField = "readerTypeName";
            this.ReaderType.DataValueField = "readerTypeId";
            this.ReaderType.DataBind();
            /*根据读者编号得到读者各个信息并显示在界面上*/
            ReaderModel readerModel = (new ReaderLogic()).GetReaderInfo(Request.QueryString["readerId"]);
            this.ReaderId.Text = readerModel.ReaderId;
            this.ReaderPassword.Text = readerModel.ReaderPassword;
            this.ReaderName.Text = readerModel.ReaderName;
            this.ReaderSex.Text = readerModel.ReaderSex;
            this.ReaderBirthday.Text = readerModel.ReaderBirthday.ToShortDateString();
            this.ReaderType.Text = (new ReaderTypeLogic()).GetReaderTypeNameById(readerModel.ReaderTypeId);
            this.ReaderPhone.Text = readerModel.ReaderPhone;
            this.ReaderEmail.Text = readerModel.ReaderEmail;
            this.ReaderAddress.Text = readerModel.ReaderAddress;
            if (readerModel.ReaderPhotoUrl != "")
            {
                this.ReaderPhotoUrl.Text = readerModel.ReaderPhotoUrl;
                this.ReaderPhoto.ImageUrl = readerModel.ReaderPhotoUrl;
            }
            else
                this.ReaderPhoto.ImageUrl = "ReaderPhoto\\NoImage.jpg";

            this.ReaderMemo.Text = readerModel.ReaderMemo;
        }
    }
    protected void Btn_Update_Click(object sender, EventArgs e)
    {
        ReaderModel readerModel = new ReaderModel();
        readerModel.ReaderId = this.ReaderId.Text;
        readerModel.ReaderPassword = this.ReaderPassword.Text;
        readerModel.ReaderName = this.ReaderName.Text;
        readerModel.ReaderSex = this.ReaderSex.Text;
        readerModel.ReaderBirthday = Convert.ToDateTime(this.ReaderBirthday.Text);
        readerModel.ReaderTypeId = Int32.Parse(this.ReaderType.SelectedValue);
        readerModel.ReaderPhone = this.ReaderPhone.Text;
        readerModel.ReaderEmail = this.ReaderEmail.Text;
        readerModel.ReaderAddress = this.ReaderAddress.Text;
        readerModel.ReaderPhotoUrl = this.ReaderPhotoUrl.Text;
        readerModel.ReaderMemo = this.ReaderMemo.Text;
        /*调用业务层执行读者信息的更新操作*/
        ReaderLogic readerLogic = new ReaderLogic();
        if (readerLogic.UpdateReaderInfo(readerModel,Server.MapPath("")))
            Response.Write("<script>alert('读者信息更新成功!');loaction.href='readerEdit.aspx?readerId=" + readerModel.ReaderId + "';</script>");
        else
            Response.Write("<script>alert('" + readerLogic.ErrMessage + "');</script>");
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

    protected void Btn_Cancle_Click(object sender, EventArgs e)
    {
        Response.Redirect("readerManage.aspx");
    }
    protected void Btn_Upload_Click(object sender, EventArgs e)
    {
        /*如果用户上传了新的图片文件*/
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
}
