(function($){
	/**
	 * 自定义jquery的拓展函数: myfocus
	 * 图片浏览器
	 * @param options
	  {
	  	id: 'boxID1',//焦点图盒子ID
	  	ajaxUrl:'xxxx/url',  // 获取图片数据的链接
	  	imgObject:{
	  		width:111,
	  		height:90
	  	},
	  	hasRightClick:false,
	  	deleteAction:function(){}
	  }
	 */
	//如果页面没有引入myfocus插件,则抛出异常
if(!myFocus){
	throw "The myfocus plugin is not find! Please import myfocus plugin!";
}
$.fn.myfocus = function(options) {
	var self = this;
	var selfId = this.selector.replace('#','')
	var defaults = {
			id:selfId,
			hasRightClick:false,
		    pattern: 'mF_tbhuabao',//焦点图风格的名称
		    time: 3,//切换时间间隔(秒)
		    trigger: 'mouseover',//触发切换模式:'click'(点击)/'mouseover'(悬停)
		    delay: 200,//'mouseover'模式下的切换延迟(毫秒)
		    txtHeight: 'default'//标题高度设置(像素),'default'为默认CSS高度，0为隐藏
		};
	var imgObject = options.imgObject || {
		width:160,
		height:120
	};
	
	var opts = $.extend(defaults, options);
	_ajaxMyfocus(opts,self,imgObject);
};

function _ajaxMyfocus(opts,self,imgObject){
	//请求数据
	$.ajax({
	  url: opts.ajaxUrl,
	  async:true,
	  dataType:"json",
	  success: function(response){
		  /**
		   * 
		   response：[{
		   		src：'图像的地址',
		   		alt:'图像不存在时显示的文字',
		   		id:'图像所在数据库的唯一标志'
		   }]
		   */
		  var data = response;
		  if(data.length>0){
			  $(self).append('<div class="loading"><img src="../images/demo/loading.gif" alt="请稍候..." /></div>');
			  var imageDiv = '<div class="pic"><ul>';
			  for(var i=0;i<data.length;i++){
				  imageDiv += '<li><a href="#"><img style="z-index:99999999;" id="'+data[i]["id"]+'" width="'+imgObject["width"]+'" height="'+imgObject["height"]+'" src="'+data[i]["src"]+'" alt="'+data[i]["alt"]+'" /></a></li>';
			  }
			  imageDiv += '</ul></div>';
//			  console.log(data);
			  $(self).append($(imageDiv));
			  $(self).css({
				  width:imgObject["width"]+"px",
				  height:imgObject["height"]+"px"
			  });
//			  self.myFocus = myFocus.set(opts);
			  $('#'+opts.id).myFocus(opts,function(settings){
				  opts.myFocus =  settings.myFocus;
//				  console.log('opts2');
//				   console.log(opts);
				  if(opts.hasRightClick){
					  //添加右击事件
					  $(imageDiv).find('img').each(function (index, domEle) {
						  $('#'+$(domEle).attr('id')).WinContextMenu({
							  myFocus:opts.myFocus,
							  cancel:'.cancel',   
							  items:[{   
								  id:'_deleteImage',   
								  text:'删除图片',   
								  icon:'../skins/default/contextmenu/icons/Calendar.png',   
								  action:function(e){
									  var imgId = e.target.id;
									  if(opts.deleteAction){
										  var resultObj = opts.deleteAction();
										  if(resultObj.success){
											  $('#'+opts.id).empty();
											  _ajaxMyfocus(opts,self,imgObject);
										  }else{
											  alert(resultObj.errorText);
										  }
									  }else{
										  ;
									  }
								  }//按照项添加   
							  }],   
							  action:function(e){alert(e.id);}//自由设计项事件回调   
						  }); 
					  });
				  }
			  }); 
		  }else{
			  $(self).append('<div class="loading"><img src="../images/demo/loading.gif" alt="无图片数据" /></div>');
		  }
	  }
	});
}

})(jQuery);
