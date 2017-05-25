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
        <title><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.title"/></title>
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
                                        <div class="caption"><i class="icon-docs"></i> <spring:message code="com.jinzhiyi.ffs.message.dictionaryData.panel.create.title" /></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <div class="form-body ">
                                            <form action="${pageContext.request.contextPath}/basic/dictionaryData/dictionaryDataCreate.do" id="createDictionaryDataForm" method="post">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.panel.info" /></h4>
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
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_code" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input" id="code" name="code" 
                                                            	value="${dictionaryData.code}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_code" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                    	<div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_parentCode" /></label>
                                                            <input type="text" class="form-control" id="parentCode" name="parentCode" 
                                                            	value="${dictionaryData.parentCode}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_parentCode" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_cn" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input" id="nameCn" name="nameCn" 
                                                            	value="${dictionaryData.nameCn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_cn" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_en" /></label>
                                                            <input type="text" class="form-control disable-ime" id="nameEn" name="nameEn" 
                                                            	value="${dictionaryData.nameEn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_en" />">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_sorter" /></label>
                                                            <input type="text" class="form-control" id="sorter" name="sorter" 
                                                            	value="${dictionaryData.sorter}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_sorter" />">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-actions">
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                        	<button type="submit" class="btn red loading-state-btn" id="submit_btn" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />'><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/basic/dictionaryData/dictionaryDataQuery.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return" /></a>
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
		<script type="text/javascript">   
			$(function(){     
				
				//验证信息
				var createDictionaryDataForm = $('#createDictionaryDataForm');
	            var error = $('.alert-danger', createDictionaryDataForm);
	            var success = $('.alert-success', createDictionaryDataForm);
				
	            createDictionaryDataForm.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "",  // validate all fields including form hidden input
	                messages: {
	                	nameCn: {
	                        required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_cn.required"/>'),
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_cn.maxlength"/>')
	                    },
	                    nameEn: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_en.maxlength"/>')
	                    },
	                    code: {
	                    	required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_code.required"/>'),
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_code.maxlength"/>')
	                    },
	                    parentCode: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_parentCode.maxlength"/>')
	                    },
	                    sorter: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_sorter.maxlength"/>')
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
	                	code: {
	                		maxlength: 300,
	                		required: true
	                    },
	                    parentCode: {
	                    	maxlength: 300
	                    },
	                    sorter: {
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