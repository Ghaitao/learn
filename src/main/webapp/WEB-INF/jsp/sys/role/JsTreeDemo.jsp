<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <head>
        <%@ include file="/jspi/meta.jsp"%>
        <title>JSTRee练习</title>
		<link rel="stylesheet" href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jstree/dist/themes/default/style.min.css" />
       	<link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/skins/default.css" rel="stylesheet" type="text/css" />
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   	</head>
<body>

 <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
                	<div class="container">
						<div class="row">
			                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                    	<div class="portlet light bordered">
	                            	<div class="portlet-title">
	                                	<div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.role.panel.modify.title" /></div>
	                                </div>
                                    <div class="portlet-body form">
                                   		<form action="${pageContext.request.contextPath}/sys/role/roleModify1.do" id="modifyRoleForm" method="post">
                                        	<div class="form-body">
	                                        	<input type="hidden" name="recId" id="recId" value="${role.recId}">
	                                        	<input type="hidden" name="menuIds" id="menuIds">
                                            	<h4 class="form-section">菜单分配</h4>
												<div class="row">
													<div class="col-lg-12 col-xs-12 col-md-12">
														<div class="form-group">
		                             						 <div id="menuTree" class="jstree"></div>
		                                             	</div>
	                                            	</div>
	                                            </div>
	                                            <div class="form-actions">
	                                                <div class="row">
	                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
	                                                    	<button type="submit" class="btn red loading-state-btn" id="submit_btn" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />'><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
	                                                    	<a href="javascript:;" id="btn_delete" class="btn btn-default margin-left10"><i class="glyphicon glyphicon-trash"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.delete"/></a>
	                                                    	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/role/roleQuery.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return" /></a>
	                                                    </div>
	                                                </div>
	                                            </div>
                                           	</div>
                                        </form>
                                	</div>
                      			</div>
               				</div>
          				</div>
             		</div>
             	</div>
        	</div>
        </div>





	<%@ include file="/jspi/metronic_base_js.jsp"%>
   	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
    <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jstree/dist/jstree.min.js" type="text/javascript"></script>
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/jquery.artDialog.js"></script>
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/plugins/iframeTools.js"></script>
	
	<script type="text/javascript">
	$(function(){
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/sys/menu/menuQueryAll.do",
			success:function(data){
				var menus = new Array();
				alert(data);
				/* $.each(data,function(i,obj){
					alert(obj.recId+"==="+obj.parentId+"=="+obj.i18nName);
				}); */
				$.each(data, function(i, obj) {
					var node = {};
					node.id = obj.recId;
					node.parent = obj.parentId == null ? '#' : obj.parentId;
					node.text = obj.i18nName;
					if (obj.remark||'' != '') {
						node.text += "(" + obj.remark + ")";
					}
					node.icon = obj.icon;
					node.state = {'opened': true },
					menus[i] = node;
				});
				console.log(menus);
				$('#menuTree').jstree({
		            'plugins': ["checkbox", "types","contextmenu","dnd"],//可以自定义contextMenu
					'core' : {
						 "themes" : {
						        	"responsive": false
						    },    
						 'data' : menus,
						 'check_callback':function(operation, node, parent, position, more){//下面有参数说明
							 /* if(operation=='rename_node'){
								 console.log('rename测试'+node.text+"=="+parent.text+"("+parent.id+")");
								 //这里可以执行相关 操作
							 }
							 if(operation=='create_node'){
								 console.log('create_node测试'+node.text+"=="+parent.text+"("+parent.id+")");
							 }
							 if(operation=='delete_node'){
								 console.log('delete_node测试'+node.text+"=="+parent.text+"("+parent.id+")");
							 } */
						 }
					},
					'types' : {
						'default' : {
							'icon' : 'fa fa-file-o'
						},
						'file' : {
							'icon' : 'fa fa-file-o'
						}
					},
					'contextmenu' : {
						'items' : {
							"create" : null,
							"rename" : null,
							"remove" : null,
							"ccp" : null,
							"add" : {
								"label" : "Add",
								"action" : function(obj){
									var inst = jQuery.jstree.reference(obj.reference);    
			                        var clickedNode = inst.get_node(obj.reference);   
			                        alert("add operation--clickedNode's id is:" + clickedNode.id); 
								}
							 },
							 "delete" : {
								 "label": "delete",    
				                 "action": function (obj) {  
				                        var inst = jQuery.jstree.reference(obj.reference);    
				                        var clickedNode = inst.get_node(obj.reference);   
				                        alert("delete operation--clickedNode's id is:" + clickedNode.id);  
				                  }   
							 }
						}
					}
				}).on("ready.jstree", function () {
					<c:forEach items="${menus}" var="menu">
						if (!$('#menuTree').jstree(true).is_parent(${menu.menuId})) {
							$('#menuTree').jstree(true).check_node(${menu.menuId});
						}
                    </c:forEach>
				});
			}
			
		});
		
		
	});
	
	</script>
	<!-- 
	具体参考:::::https://viky-zhang.gitbooks.io/jstree-doc/plugin.html
	plugins:{
		checkbox:此插件在每个节点前添加一个勾选框，使得多选节点更方便。
				此插件支持三种状态：全部子节点选中、全部未选中、部分选中undetermined。部分选中状态会向上转播状态
				你还可通过设置传播方式的选项进行微调。
				需注意的是，即使一个节点处于禁用状态，勾选框也会受传播的影响。
				半选中状态undetermined是插件自动计算出来的，但当你使用 AJAX 来按需加载节点， 且想将这些未加载节点的状态设置为半选中状态，那你可传入这个参数："undetermined" : true。
	    Contextmenu(右键菜单插件):
	    		此插件用于在树中点击右键时弹出一个菜单。 此菜单可通过具体的配置进行自定义。
	    dnd:	
	    		此插件允许拖动和释放节点来调整树结构。
		类型插件（Types plugin）:
				此插件用于为节点设置类型，即按类型形成不同的节点组，使得更容易对嵌套规则、图标等进行操作。
				可用set_type或直接设置节点的type属性值来设置节点的类型。
	}
	
	operation,node,parent,position,more。

	operation：这个是看我们右键选择了什么功能。是删除，添加，移动，修改等。
	node：表示当前对象的值，里面保存有这个节点里面的一些属性。
	parent：表示父类节点对象。
	position：表示当前节点的text值。比如我们上图的123就是在后台打印了position的原因。
	more：这个现在打印不出来，也不清楚干什么用的。
	
	
	
	
	
	
	
	
	
	
	
	 -->
</body>
</html>