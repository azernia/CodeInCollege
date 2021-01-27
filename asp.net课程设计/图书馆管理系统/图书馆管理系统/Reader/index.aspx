<%@ Page Language="C#" AutoEventWireup="true" CodeFile="index.aspx.cs" Inherits="Reader_index" %>

<%@ Register Src="../UserControl/WebFootControl.ascx" TagName="WebFootControl" TagPrefix="uc2" %>
<%@ Register TagPrefix="uc1" TagName="ReaderHeadmodule" Src="../UserControl/ReaderHeadmodule.ascx" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>图书管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<LINK href="../css/style.css" type="text/css" rel="stylesheet">
		<script src="../script/App.js"></script>
	
	</HEAD>
	<body onmousemove="hiddenMenu();">
		<form id="Form1" method="post" runat="server">
			<table align="center" cellSpacing="0" cellPadding="0" width="760" border="0" ID="Table5">
				<TBODY>
					<tr>
						<td>
							<!--   /菜单层   -->
							<table class="tbl" id="Table2" cellSpacing="0" cellPadding="0" width="760" border="0">
								<tr>
									<td>
										<!--   导航超链接   -->
											<uc1:ReaderHeadmodule id="ReaderHeadmodule" runat="server"></uc1:ReaderHeadmodule>
										<!--   /导航主菜单   --></td>
								</tr>
								</table>
							<!-- END PAGE HEADER MODULE -->
							<!--   内容层   -->
							<table class="lrb" align="center" cellSpacing="0" cellPadding="0" width="760" border="0"
								ID="Table3">
								<tr>
									<td bgcolor="#d6ebff" style="height: 400px">
									<iframe style="height: 400px; width: 760px;" frameborder="0" name="ContentFrame" scrolling="no" src="../desk.aspx" width="760"></IFRAME>
									</td>
								</tr>
							</table>
							<!--   /内容层   -->
						</td>
					</tr>
					<tr><td><uc2:WebFootControl ID="WebFootControl1" runat="server" /></td></tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>
