var UITree = function () {

	//角色菜单  树结构（详细见 metronic_v4.5.6/theme/assets/pages/scripts/ui-tree.js）
    var roleMenu = function () {
        $('#tree_2').jstree({
            'plugins': ["wholerow", "checkbox", "types"],
            'core': {
                "themes" : {
                    "responsive": false
                },    
                'data': [
            		{
                        "text": "客户",
                        "children": [{
                            "text": "客户审核（客服）",
                            "icon": "fa fa-sticky-note-o icon-state-danger",
                            "state": {
                                "selected": true
                            }
                        }, {
                            "text": "客户审核（财务）",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                        }, {
                            "text": "客户维护",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                        }]
                	},
                	{
                        "text": "订单",
                        "children": [{
                            "text": "审核订单（航线经理）",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                        }, {
                            "text": "审核订单（航线）",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                        }, {
                            "text": "订单操作（客服）",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                        }, {
                            "text": "创建订单（客户）",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                    	}]
                	},
                	{
                        "text": "进仓",
                        "children": [{
                            "text": "进仓扫描",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                        }, {
                            "text": "进仓记录",
                            "icon": "fa fa-sticky-note-o icon-state-danger"
                        }]
                	}
                ]
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            }
        });
    }

    return {
        init: function () {
            roleMenu();
        }
    };
}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {    
       UITree.init();
    });
}