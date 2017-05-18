<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="mytag" uri="http://www.sfxie.com/tags" %>
   	<div class="golive-easyui-form-div">
	   	<form id="myform" class="easyui-form" method="post" data-options="novalidate:true" action="${ctx}/systemAjax/system-userModule.action">
	   		<input type="hidden" name="id">
	   		<input name="createUser" id="createUser" type="hidden">
        	<input name="createTime" id="createTime"  type="hidden">
	        <div>
	            <label for="companyCode"><font color="#990000">*</font>公司编码：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="companyCode" data-options="required:true"></input>
	        </div>
	        <div>
	            <label for="companyNameCn">公司中文名：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="companyNameCn"></input>
	        </div>
	        <div>
	            <label for="isValid" ><font color="#990000">*</font>是否有效：</label>
	            <mytag:select  	dataOptions="required:true,valueField:'value',textField:'text'" 
	            				selectId ="isValidList" selectName="isValid"
	            				className="easyui-combobox"  style="width:200px;" 
				            	dataProvider="commonStateValidationDataProvider" displayField="text" valueField="value"
				            	value="N"
	            ></mytag:select>
	        </div>
	        <div>
	            <label for="remark">描述：</label>
	            <input class="easyui-textbox" style="width:200px;" type="text" name="remark"></input>
	        </div>
    	</form>
   	</div>
   	<div style="text-align:center;padding:5px;height: 10%;">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm()">清除</a>
    </div>
