<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="mytag" uri="http://www.sfxie.com/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
	  	<mytag:report debug="true" templatePath="/WEB-INF/template" easyUIRootPath="/static/js/ui/jquery-easyui-1.4.2" queryFormId="queryForm" autoQuery="false" url="/ui/report" exportUrl="/ui/report/export" northStyle="style=\"height:100px;\""
	  					i18n="easyui.i18n"
	  	></mytag:report>
  </body>
</html>
