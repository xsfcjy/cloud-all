<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="mytag" uri="http://www.sfxie.com/tags" %>
   	<div class="golive-easyui-form-div">
	   	<form id="myform" class="easyui-form" method="post" data-options="novalidate:true" action="#">
	   		<input type="hidden" name="id">
        	<input name="createTime" id="createTime"  type="hidden">
	        <div>
	            <label for="code"><font color="#990000">*</font>报表编码：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="code" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="name">报表名称：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="name"></input>
	        </div>
	        <div>
	            <label for="isMain" ><font color="#990000">*</font>是否有效：</label>
	            <mytag:select  	dataOptions="required:true,valueField:'value',textField:'text'" 
	            				selectId ="isValidList" selectName="isMain"
	            				className="easyui-combobox"  style="width:200px;" 
				            	dataProvider="commonStateValidationDataProvider" displayField="text" valueField="value"
				            	value="N"
	            ></mytag:select>
	        </div>
    	</form>
   	</div>
   	<div style="text-align:center;">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm()">清除</a>
    </div>
