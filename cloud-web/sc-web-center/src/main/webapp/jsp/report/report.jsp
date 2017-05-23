<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="mytag" uri="http://www.sfxie.com/tags" %>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
	  	<mytag:report templatePath="/WEB-INF/template" easyUIRootPath="/static/js/ui/jquery-easyui-1.4.2" queryFormId="queryForm" autoQuery="false" url="/ui/report" northStyle="style=\"height:100px;\""></mytag:report>
  </body>
</html>
