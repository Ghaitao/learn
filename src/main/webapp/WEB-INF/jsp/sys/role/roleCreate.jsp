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
        <title><spring:message code="com.jinzhiyi.ffs.message.role.title"/></title>
		<link rel="stylesheet" href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jstree/dist/themes/default/style.min.css" />
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   	</head>
    <body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
     
         <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
                	<div class="container">
						<div class="row">
			                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                    	<div class="portlet light bordered">
	                            	<div class="portlet-title">
	                                	<div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.role.panel.create.title" /></div>
	                                </div>
                                    <div class="portlet-body form">
                                   		<form action="${pageContext.request.contextPath}/sys/role/roleCreate.do" id="createRoleForm" method="post">
                                        	<div class="form-body">
	                                        	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.role.panel.info" /></h4>
	                                          	<input type="hidden" name="menuIds" id="menuIds">
	                                          	<div class="row">
                                              		<div class="col-lg-4 col-xs-4 col-md-4">
	                                              		<div class="form-group">
	                                                        <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.role.role_name_cn" /><span class="required">*</span></label>
	                                                        <input type="text" class="form-control required_input" name="nameCn" id="nameCn" value="${role.nameCn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.role.role_name_cn" />" >
	                                                    </div>
                                                	</div>
	                                                <div class="col-lg-4 col-xs-4 col-md-4">
	                                                    <div class="form-group">
	                                                        <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.role.role_name_en" /></label>
	                                                        <input type="text" class="form-control disable-ime" name="nameEn" id="nameEn" value="${role.nameEn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.role.role_name_en" />" >
	                                                    </div>
	                                                </div>
                                            	</div>
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.role.panel.menu" /></h4>
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
        
		<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
   		<%@ include file="/jspi/metronic_base_js.jsp"%>
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jstree/dist/jstree.min.js" type="text/javascript"></script>
		<script type="text/javascript">   
			$(function(){     
				
				//取得menu树
				$.ajax({  
	                type : "POST",  
	                //dataType : 'json',  
	                url : "${pageContext.request.contextPath}/sys/menu/menuQueryAll.do",  
	                success : function(data) {
	                	var menus = new Array();
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
	                	$('#menuTree').jstree({
	    		            'plugins': ["checkbox", "types"],
	    					'core' : {
	    						 "themes" : {
	    						        "responsive": false
	    						    },    
	    						'data' : menus
	    						},
	    					'types' : {
	    						'default' : {
	    							'icon' : 'fa fa-file-o'
	    						},
	    						'file' : {
	    							'icon' : 'fa fa-file-o'
	    						}
	    					}
	    				});
	                }  
	            });
				
				//验证信息
				var createRoleForm = $('#createRoleForm');
	            var error = $('.alert-danger', createRoleForm);
	            var success = $('.alert-success', createRoleForm);
				
	            createRoleForm.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "",  // validate all fields including form hidden input
	                messages: {
	                	nameCn: {
	                        required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.role.role_name_cn.required"/>'),
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.role.role_name_cn.length"/>')
	                    },
	                    nameEn: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.role.role_name_en.length"/>')
	                    }
	                },
	                rules: {
	                	nameCn: {
	                        maxlength: 300,
	                        required: true
	                    },
		                nameEn: {
	                        maxlength: 300
		                }
	                },

	                errorPlacement: function (error, element) { // render error placement for each input type
	                    if (element.parent(".input-group").size() > 0) {
	                        error.insertAfter(element.parent(".input-group"));
	                    } else if (element.attr("data-error-container")) { 
	                        error.appendTo(element.attr("data-error-container"));
	                    } else if (element.parents('.radio-list').size() > 0) { 
	                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
	                    } else if (element.parents('.radio-inline').size() > 0) { 
	                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
	                    } else if (element.parents('.checkbox-list').size() > 0) {
	                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
	                    } else if (element.parents('.checkbox-inline').size() > 0) { 
	                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
	                    } else {
	                        error.insertAfter(element); // for other inputs, just perform default behavior
	                    }
	                },
	                
	                invalidHandler: function (event, validator) { //display error alert on form submit              
	                    success.hide();
	                    error.show();
	                    App.scrollTo(error, -200);
	                },

	                highlight: function (element) { // hightlight error inputs
	                    $(element)
	                        .closest('.form-group').addClass('has-error'); // set error class to the control group
	                },

	                unhighlight: function (element) { // revert the change done by hightlight
	                    $(element)
	                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
	                },

	                success: function (label) {
	                    label
	                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
	                },

	                submitHandler: function (form) {
	                    success.show();
	                    error.hide();
	                 	// 保存menuIds
						var menuIdsVar = $('#menuTree').jstree(true).get_selected();
						$("#menuTree li").has("i[class*='jstree-undetermined']").each(function(){
							menuIdsVar+=","+$(this).attr("id");
		            	});
						$("#menuIds").val(menuIdsVar);
	                    form.submit();
	                }
	            });
			});
		</script>
    </body>
</html>