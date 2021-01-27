<%@ Page Language="C#" AutoEventWireup="true" CodeFile="bookEdit.aspx.cs" Inherits="Admin_bookEdit" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1" runat="server">
    <title>无标题页</title>
    <LINK href="../css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
   <form method="post" id="frmAnnounce" runat="server">
      <table width=600 border=0 cellpadding=0 cellspacing=0 align="center">
        <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px" align="center">
          <img src="../images/ADD.gif" width=14px height=14px>图书信息管理--&gt;图书信息更新</td>
        </tr>
         <tr>
	    <td style="height: 26px">
            图书类别：
            <asp:DropDownList ID="BookType" runat="server">
            </asp:DropDownList>
        </td>
        </tr>
        <tr>
	    <td style="height: 26px">
            图书名称：<asp:TextBox ID="BookName" runat="server" Width="191px"></asp:TextBox></td>
        </tr>
        <tr>
	    <td style="height: 24px">
          作者姓名：<asp:TextBox ID="Author" runat="server" Width="143px"></asp:TextBox>
        </td>
        </tr>
        <tr>
	    <td style="height: 26px">
          出版社：<asp:TextBox ID="Publishing" runat="server" Width="237px"></asp:TextBox>
        </td>
        </tr>
        <tr>
	    <td style="height: 26px">
          定价：<asp:TextBox ID="BookMoney" runat="server" Width="57px"></asp:TextBox>
            <asp:Label ID="Label1" runat="server" Text="元"></asp:Label>&nbsp;
          图书数量：<asp:TextBox ID="BookNum" runat="server" Width="52px"></asp:TextBox>
            <asp:Label ID="Label2" runat="server" Text="本"></asp:Label><br />
            &nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="BookMoney"
                ErrorMessage="请输入定价!"></asp:RequiredFieldValidator>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="BookNum"
                ErrorMessage="请输入图书数量!"></asp:RequiredFieldValidator>
            <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToValidate="BookMoney"
                ErrorMessage="请输入正确的定价!" Operator="DataTypeCheck" Type="Currency"></asp:CompareValidator>
            <asp:CompareValidator ID="CompareValidator2" runat="server" ControlToValidate="BookNum"
                ErrorMessage="请输入正确的图书数量!" Operator="DataTypeCheck" Type="Integer"></asp:CompareValidator></td>
        </tr>
        <tr>
	    <td style="height: 26px" valign="top">
          图书简介：<br />
            &nbsp; &nbsp;&nbsp;
            <asp:TextBox ID="Introduction" runat="server" Height="64px" TextMode="MultiLine" Width="254px"></asp:TextBox></td>
        </tr>
          <tr>
              <td style="height: 26px" align="center">
                  <asp:Button ID="Btn_Update" runat="server" OnClick="Btn_Update_Click" Text="更新" />&nbsp;
                  <asp:Button ID="Btn_Back" runat="server" OnClick="Btn_Cancle_Click" Text="返回" /></td>
          </tr>
         
      </table>
    </form>
</body>
</html>