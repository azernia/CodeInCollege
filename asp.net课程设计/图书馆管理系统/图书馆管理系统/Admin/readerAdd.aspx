<%@ Page Language="C#" AutoEventWireup="true" CodeFile="readerAdd.aspx.cs" Inherits="Admin_readerAdd" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1" runat="server">
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
    <script language=javascript>
     function seltime(inputName)
     {
	window.open('seltime.aspx?InputName='+inputName+'','','width=250,height=220,left=360,top=250,scrollbars=yes');  
     }
  
   </script>
</head>
<body>
   <form method="post" id="frmAnnounce" runat="server">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px" align="center">
          <img src="../images/ADD.gif" width=14px height=14px>读者信息管理--&gt;读者信息添加</td>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者编号：<asp:TextBox ID="ReaderId" runat="server" Width="159px"></asp:TextBox>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者密码：<asp:TextBox ID="ReaderPassword" runat="server" Width="157px"></asp:TextBox>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者姓名：<asp:TextBox ID="ReaderName" runat="server" Width="157px"></asp:TextBox>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者性别：<asp:DropDownList ID="ReaderSex" runat="server">
              <asp:ListItem>男</asp:ListItem>
              <asp:ListItem>女</asp:ListItem>
          </asp:DropDownList>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者生日：<asp:TextBox ID="ReaderBirthday" runat="server" Width="77px"></asp:TextBox><input class="submit" name="Button" onclick="seltime('ReaderBirthday')" style="width: 30px"
                  type="button" value="选择" />
        </tr>
        <tr>
	  <td style="height: 26px">
          读者类型：<asp:DropDownList ID="ReaderType" runat="server" DataSourceID="ReaderTypeDataSource"
              DataTextField="readerTypeName" DataValueField="readerTypeId">
          </asp:DropDownList>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者电话：<asp:TextBox ID="ReaderPhone" runat="server" Width="158px"></asp:TextBox>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者Email：<asp:TextBox ID="ReaderEmail" runat="server" Width="158px"></asp:TextBox>
        </tr>
        <tr>
	  <td style="height: 26px">
          读者地址：<asp:TextBox ID="ReaderAddress" runat="server" Width="301px"></asp:TextBox>
        </tr>
        <tr>
         <td style="width: 502px">
             读者照片路径：<asp:TextBox ID="ReaderPhotoUrl" runat="server" ReadOnly="True" Width="228px" Enabled="False"></asp:TextBox>
          &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<br />
             <br />
             上传读者照片：<asp:FileUpload ID="ReaderPhotoUpload" runat="server" Width="231px" />&nbsp;
             <asp:Button ID="Btn_Upload" runat="server" Text="上传" OnClick="Btn_Upload_Click" Width="36px" /></td><td width=100px>
    <asp:Image ID="ReaderPhoto" runat="server" Height="90px" Width="99px" /></td>
        </tr>
        <tr>
	  <td style="height: 20px">
          附加信息：<asp:TextBox ID="ReaderMemo" runat="server" Width="250px" Height="49px" TextMode="MultiLine"></asp:TextBox>
        </tr>
          <tr>
              <td style="height: 27px" align="center">
                  <asp:Button ID="Btn_Add" runat="server" OnClick="Btn_Add_Click" Text="添加" />&nbsp;
                  <asp:Button ID="Btn_Cancle" runat="server" OnClick="Btn_Cancle_Click" Text="取消" /></td>
          </tr>
          
      </table>
       <asp:SqlDataSource ID="ReaderTypeDataSource" runat="server" ConnectionString="<%$ ConnectionStrings:LibraryConnectionString %>"
           SelectCommand="SELECT [readerTypeId], [readerTypeName] FROM [readerType_130610]"></asp:SqlDataSource>
    </form>
</body>
</html>
