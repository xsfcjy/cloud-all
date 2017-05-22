<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="mytag" uri="http://www.sfxie.com/tags" %>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
  		<form action="#" id="queryForm">
  			<table height="100%">
  				<tbody>
  					<tr><td>名称：</td><td><input name="name" type="text"></td></tr>
  					<tr><td>编码：</td><td><input name="code" type="text"></td></tr>
  				</tbody>
  			</table>
   		</form>
	  	<mytag:report templatePath="/WEB-INF/template" easyUIRootPath="/static/js/ui/jquery-easyui-1.5.2" queryFormId="queryForm" autoQuery="false" url="/ui/report" northStyle="style=\"height:100px;\""></mytag:report>
  </body>
</html>
