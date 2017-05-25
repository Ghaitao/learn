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
        <title><spring:message code="com.jinzhiyi.ffs.message.menu.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/skins/default.css" rel="stylesheet" type="text/css" />
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
                                        <div class="caption"><i class="icon-docs"></i> <spring:message code="com.jinzhiyi.ffs.message.menu.panel.modify.title" /></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <div class="form-body ">
                                            <form action="${pageContext.request.contextPath}/sys/menu/menuModify.do" id="modifyMenuForm" method="post">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.menu.panel.info" /></h4>
                                            	<input type="hidden" id="recId" name="recId" value="${menu.recId}">
                                            	<c:if test="${not empty errorMessage}">
                                            	<div class="row">
                                                	<div class="col-lg-12 col-xs-12 col-md-12">
										                <div class="alert alert-danger">
										                    <span> <i class="fa fa-exclamation-triangle"></i> ${errorMessage} </span>
										                </div>
									                </div>
									            </div>
								                </c:if>
                                                <div class="row">
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_cn" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input" id="nameCn" name="nameCn" value="${menu.nameCn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_cn" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_en" /></label>
                                                            <input type="text" class="form-control disable-ime" id="nameEn" name="nameEn" value="${menu.nameEn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_en" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_icon" /></label>
                                                            <input type="text" class="form-control" id="icon" name="icon" value="${menu.icon}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.menu.menu_icon" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                    	<div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_remark" /></label>
                                                            <input type="text" class="form-control" id="remark" name="remark" value="${menu.remark}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.menu.menu_remark" />">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_parentId" /></label>
                                                            <input type="text" class="form-control" id="parentId" name="parentId" value="${menu.parentId}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.menu.menu_parentId" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_sort" /></label>
                                                            <input type="text" class="form-control" id="sort" name="sort" value="${menu.sort}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.menu.menu_sort" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-xs-6 col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_url" /></label>
                                                            <input type="text" class="form-control" id="url" name="url" value="${menu.url}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.menu.menu_url" />">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-actions">
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                        	<button type="submit" class="btn red loading-state-btn" id="submit_btn" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />'><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
	                                                     	<a href="javascript:;" id="btn_delete" class="btn btn-default margin-left10"><i class="glyphicon glyphicon-trash"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.delete"/></a>
	                                                     	<c:if test="${menu.valid}">
	                                                    	<a href="${pageContext.request.contextPath}/sys/menu/menuInValid.do?menuId=${menu.recId}" class="btn btn-default margin-left10" id="btn_unlock"><i class="fa fa-unlock"></i> <spring:message code="com.jinzhiyi.ffs.message.menu.menu_invalid" /></a>
	                                                    	</c:if>
	                                                    	<c:if test="${!menu.valid}">
	                                                    	<a href="${pageContext.request.contextPath}/sys/menu/menuValid.do?menuId=${menu.recId}" class="btn btn-default margin-left10" id="btn_lock"><i class="icon-lock"></i> <spring:message code="com.jinzhiyi.ffs.message.menu.menu_valid" /></a>
	                                                    	</c:if>
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/menu/menuQuery.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return" /></a>
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
        </div>
        
		<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
   		<%@ include file="/jspi/metronic_base_js.jsp"%>
   		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/jquery.artDialog.js"></script>
		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/plugins/iframeTools.js"></script>
		<script type="text/javascript">   
			$(function(){     
				
				$("#btn_delete").click(function() {
					$.dialog.confirm('<spring:message code="com.jinzhiyi.ffs.message.menu.prompt_delete"/>', function() {
						window.location.href = '${pageContext.request.contextPath}/sys/menu/menuDelete.do?menuId=${menu.recId}';
					});
				});
				
				//验证信息
				var modifyMenuForm = $('#modifyMenuForm');
	            var error = $('.alert-danger', modifyMenuForm);
	            var success = $('.alert-success', modifyMenuForm);
				
	            modifyMenuForm.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "",  // validate all fields including form hidden input
	                messages: {
	                	nameCn: {
	                        required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_cn.required"/>'),
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_cn.maxlength"/>')
	                    },
	                    nameEn: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_en.maxlength"/>')
	                    },
	                    icon: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_icon.maxlength"/>')
	                    },
	                    parentId: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_parentId.maxlength"/>')
	                    },
	                    sort: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_sort.maxlength"/>')
	                    },
	                    url: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_url.maxlength"/>')
	                    },
	                    remark: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.menu.menu_remark.maxlength"/>')
	                    }
	                },
	                rules: {
	                	nameCn: {
	                        maxlength: 300,
	                        required: true
	                    },
		                nameEn: {
	                        maxlength: 300
	                    },
	                	icon: {
	                		maxlength: 300
	                    },
	                    parentId: {
	                    	maxlength: 300
	                    },
	                    sort: {
	                    	maxlength: 300
	                    },
	                    url: {
	                    	maxlength: 300
	                    },
	                    remark: {
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
	                    form[0].submit();
	                }
	            });
			});
		</script>
    </body>
</html>