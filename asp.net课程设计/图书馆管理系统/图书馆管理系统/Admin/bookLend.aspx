<%@ Page Language="C#" AutoEventWireup="true" CodeFile="bookLend.aspx.cs" Inherits="Admin_bookLend" %>

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
	  <td style="height: 15px" align="center">
          <img src="../images/ADD.gif" width=14px height=14px>图书借阅管理--&gt;图书借阅登记</td>
        </tr>
         <tr>
	    <td style="height: 26px">
            输入图书编号：<asp:TextBox ID="BookId" runat="server" Width="159px"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="BookId"
                ErrorMessage="请输入图书编号!"></asp:RequiredFieldValidator></td>
        </tr>
        <tr>
	    <td style="height: 26px">
            输入读者编号：<asp:TextBox ID="ReaderId" runat="server" Width="159px"></asp:TextBox>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="ReaderId"
                ErrorMessage="请输入读者编号!"></asp:RequiredFieldValidator></td>
        </tr>
        <tr>
	    <td style="height: 26px" align="center">
            <asp:Label ID="Result" runat="server" Width="545px" ForeColor="Red"></asp:Label></td>
        </tr>
          <tr>
              <td style="height: 26px" align="center">
                  <asp:Button ID="Btn_Add" runat="server" OnClick="Btn_Add_Click" Text="借阅" />&nbsp;
                  <asp:Button ID="Btn_Cancle" runat="server" OnClick="Btn_Cancle_Click" Text="取消" /></td>
          </tr>
      </table>
    </form>
</body>
</html>