
$.bootboxDialog = function createBootboxDialog(message, title) {
	bootbox.dialog({
        message: message,
        title: title,
        size: 'small',
        buttons: {
          	confirm: {
	            label: "确定",
	            className: "green",
	            callback: function() {

	            }
          	},
          	cancel: {
	            label: "取消",
	            className: "red",
	            callback: function() {
	            
	            }
	        }
        }
   	});
}

$.bootboxConfirm = function createBootboxConfirm(msg) {
	bootbox.confirm({  
        buttons: {  
            confirm: {  
                label: '<i class="fa fa-check"></i> 确认',  
                className: 'green'  
            },  
            cancel: {  
                label: '<i class="fa fa-times"></i> 取消',  
                className: 'red'  
            }  
        },  
        message: '您确定要删除该角色吗?',  
        callback: function(result) {  
             
        },  
        title: "消息",  
    });  
}