<%@ Page Language="C#" AutoEventWireup="true" CodeFile="bookDetails.aspx.cs" Inherits="Reader_bookDetails" %>

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
            图书类别：&nbsp;
            <asp:Literal ID="BookType" runat="server"></asp:Literal></td>
        </tr>
        <tr>
	    <td style="height: 26px">
            图书名称：<asp:Literal ID="BookName" runat="server"></asp:Literal></td>
        </tr>
        <tr>
	    <td style="height: 24px">
          作者姓名：
            <asp:Literal ID="Author" runat="server"></asp:Literal>
        </td>
        </tr>
        <tr>
	    <td style="height: 26px">
          出版社：
            <asp:Literal ID="Publishing" runat="server"></asp:Literal></td>
        </tr>
        <tr>
	    <td style="height: 26px">
          定价：
            <asp:Literal ID="BookMoney" runat="server"></asp:Literal>
            <asp:Label ID="Label1" runat="server" Text="元"></asp:Label>&nbsp;
          图书数量：<asp:Literal
                ID="BookNum" runat="server"></asp:Literal>
            <asp:Label ID="Label2" runat="server" Text="本"></asp:Label></td>
        </tr>
        <tr>
	    <td style="height: 26px" valign="top">
          图书简介：<br />
            &nbsp; &nbsp;&nbsp;&nbsp;
            <asp:Literal ID="Introduction" runat="server"></asp:Literal></td>
        </tr>
          <tr>
              <td style="height: 26px" align="center">
                  &nbsp;
                  <asp:Button ID="Btn_Back" runat="server" OnClick="Btn_Cancle_Click" Text="返回" /></td>
          </tr>
         
      </table>
    </form>
</body>
</html>