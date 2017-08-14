(function($){
	if(!window.Component){
		Component = new Object();
	}
	Component.menuTree = new menuTree ();
	function menuTree (){
		var self = this;
		self.callback = {};
		self.setting = {
			async: {
				enable: true,
				url:function(){
					return self.callback['getUrl']();
				},
				autoParam:["id","level"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: self.callback.filter
			},
			view: {
				addHoverDom: function(treeId, treeNode){
					self.callback.addHoverDom(treeId, treeNode);
				},
				removeHoverDom: function(treeId, treeNode){
					self.callback.removeHoverDom(treeId, treeNode);
				},
				selectedMulti: false
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			callback: {
				beforeDrag: function(treeId, treeNodes){
					self.callback.beforeDrag(treeId, treeNodes);
				},
				beforeEditName: function(treeId, treeNode){
					self.callback.beforeEditName(treeId, treeNode);
				},
				beforeRemove: function(treeId, treeNode){
					self.callback.beforeRemove(treeId, treeNode);
				},
				beforeRename: function(treeId, treeNode, newName, isCancel){
					self.callback.beforeRename(treeId, treeNode, newName, isCancel);
				},
				onRemove: function(event, treeId, treeNode){
					self.callback.onRemove(event, treeId, treeNode);
				},
				onRename: function(e, treeId, treeNode, isCancel){
					self.callback.onRename(e, treeId, treeNode, isCancel);
				},
				beforeAsync: function(treeId, treeNode){
					self.callback['myBeforeCallBack'](treeId, treeNode);
				},
				onClick: function(event, treeId, treeNode){
					self.callback.zTreeOnClick(event, treeId, treeNode);
				}
			}
		};	
	}
})($);