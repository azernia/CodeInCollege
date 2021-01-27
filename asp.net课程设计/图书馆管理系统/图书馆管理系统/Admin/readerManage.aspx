<%@ Page Language="C#" AutoEventWireup="true" CodeFile="readerManage.aspx.cs" Inherits="Admin_readerManage" %>

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
	  <td style="height: 14px; width: 637px;" align="center">
          <img src="../images/list.gif" width=14px height=14px>读者信息管理--&gt;读者信息</td>
        </tr>
          <tr style="color:blue;font-size:14px;">
	  <td style="height: 14px; width: 637px;">
          读者编号:<asp:TextBox ID="ReaderId" runat="server" Width="87px"></asp:TextBox>
          读者姓名:
          <asp:TextBox ID="ReaderName" runat="server" Width="95px"></asp:TextBox>
          读者类别:<asp:DropDownList ID="ReaderType" runat="server" Width="86px">
          </asp:DropDownList>
          <asp:Button ID="Btn_Query" runat="server" Text="查询" OnClick="Btn_Query_Click" /></td>
        </tr>
        <tr>
          <td style="height: 37px; width: 637px;" align="center">
              <asp:GridView ID="GridView1" runat="server" AllowPaging="True" AutoGenerateColumns="False"
                  BackColor="#FFC0C0" BorderColor="#C0FFC0" BorderStyle="Solid" BorderWidth="3px"
                  CellPadding="4" CellSpacing="2" DataKeyNames="readerId" DataSourceID="ReaderDataSource"
                  ForeColor="ForestGreen" Width="635px" OnRowDataBound="GridView1_RowDataBound" OnPageIndexChanging="GridView1_PageIndexChanging" PageSize="8">
                  <FooterStyle BackColor="#CCCCCC" />
                  <Columns>
                      <asp:BoundField DataField="readerId" HeaderText="读者编号" ReadOnly="True" SortExpression="readerId" />
                      <asp:BoundField DataField="readerName" HeaderText="读者姓名" SortExpression="readerName" />
                      <asp:BoundField DataField="readerSex" HeaderText="读者性别" SortExpression="readerSex" />
                      <asp:BoundField DataField="readerTypeName" HeaderText="读者类别" SortExpression="readerTypeName" />
                      <asp:BoundField DataField="readerBirthday" HeaderText="读者生日"
                          SortExpression="readerBirthday" />
                          <asp:TemplateField HeaderText="操作">
                          <ItemTemplate>
                            <a href='readerEdit.aspx?readerId=<%# Eval("readerId") %>'><img src="../images/edit.gif" width=12px height=12px border=0px>详细</a>
                            &nbsp;&nbsp;
                            <a href='readerDel.aspx?readerId=<%# Eval("readerId") %>'><img src="../images/delete.gif" width=12px height=12px border=0px />删除</a>
                          </ItemTemplate>
                      </asp:TemplateField>
                  </Columns>
                  <RowStyle BackColor="White" />
                  <SelectedRowStyle BackColor="#000099" Font-Bold="True" ForeColor="White" />
                  <PagerStyle BackColor="#CCCCCC" ForeColor="Black" HorizontalAlign="Left" />
                  <HeaderStyle BackColor="Black" Font-Bold="True" ForeColor="White" />
              </asp:GridView>
              <asp:SqlDataSource ID="ReaderDataSource" runat="server" ConnectionString="<%$ ConnectionStrings:LibraryConnectionString %>"
                  SelectCommand="SELECT [readerId], [readerName], [readerSex], [readerTypeName], [readerBirthday] FROM [readerView]">
              </asp:SqlDataSource>
              &nbsp;&nbsp;
              
              
        </td>
      </tr>
    </table>
  </form>
</body>
</html>