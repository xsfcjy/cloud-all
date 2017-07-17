<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/meta.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>组织架构共享云平台</title>
<script type="text/javascript">
	//禁止后退键 作用于Firefox、Opera
	document.onkeypress=banBackSpace;
	//禁止后退键  作用于IE、Chrome
	document.onkeydown=banBackSpace;
	function openMenu(self){
		var href = $(self).attr('href');
		var title = $(self).attr('menuTitle');
		var tab = $('#content').tabs('getTab',title);
		if(!tab){
			var content ='<iframe scrolling="yes" frameborder="0"  src="'+'${ctx}'+href+'" style="width:100%;height:99%;"></iframe>'; 
			$('#content').tabs('add',{
			    title:title,
			    content:content,
			    closable:true,
			    fit:true,
			    tools:[{
			        iconCls:'golive-icon-refresh',
			        handler:function(){
			        	var refresh_tab = $('#content').tabs('getSelected');  // get selected panel
			        	if(refresh_tab && refresh_tab.find('iframe').length > 0){  
			        	    var _refresh_ifram = refresh_tab.find('iframe')[0];  
			        	    var refresh_url = _refresh_ifram.src;  
			        	    _refresh_ifram.contentWindow.location.href=refresh_url;  
		        	    }  
			        }
			    }]
			});
		}else{
			$('#content').tabs('select',title);
		}
	}
	$(document).ready(function(){
// 	    $('.golive-menu-level-2').mouseover({
// 	    	fit : true
// 	    });
// 	    golive-menu-level-2-active
		/* $(".golive-menu-level-2-active").hoverDelay({hoverEvent:function(self){
			$(self).addClass('golive-menu-level-2-active');
			
		}}); */
		$(".golive-menu-level-title").mouseover(function(event){
			$(event.target).addClass('golive-menu-level-title-active');
		}).mouseout(function(event){
			$(event.target).removeClass('golive-menu-level-title-active');
		});
	});
	function exitSystem(){
		alert('退出功能等待实现...');
// 		window.location.href = "${ctx}/loginout-logout.action";
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;" data-options="fit:true">
		<div class="north" data-options="region:'north'" style="height: 65px;">
<%-- 			<img src="${ctx}/static/css/images/logo.jpg"> --%>
		</div>
		<div class="copyright" data-options="region:'south'" style="height: 25px;text-align: center;">
			<spring:message code="page.index.copyright" />
		</div>
		<div data-options="region:'west',split:true,border:false" title="<spring:message code="page.index.navibar" />" style="width: 15%;text-align: center;">
			<div id="accordion" class="easyui-accordion" data-options="fit:true" style="width:100%;height:100%;">
				<mytag:menu></mytag:menu>
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			    <div id="content" class="easyui-tabs" data-options="tools:'#tab-tools',fit:true">
			        <div title="<spring:message code="page.index.welcome" />" data-options="iconCls:'icon-help',closable:true,fit:true" style="padding:10px">
<%-- 			            <iframe height="100%" width="100%" frameborder="no" border="0" framespacing="0" src="${ctx}/cms/common/report.jsp"></iframe>		 --%>
			            		
			        </div>
			    </div>
			    <div id="tab-tools">
			        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'golive-icon-admin',plain:true">
<%-- 			        	${S_login_userInfo.username} --%>
			        </a>
			        <a href="javascript:void(0)" class="easyui-linkbutton"  onclick="exitSystem()" style="color:red;"><spring:message code="page.index.logout" /></a>
			    </div>		
		</div>
	</div>

</body>
</html>

