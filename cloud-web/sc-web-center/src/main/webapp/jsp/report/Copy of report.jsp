<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>

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
	  	<mytag:report queryFormId="queryForm" autoQuery="false" reportDealer="DemoReportDealer" northStyle="style=\"height:100px;\""></mytag:report>
  </body>
</html>
