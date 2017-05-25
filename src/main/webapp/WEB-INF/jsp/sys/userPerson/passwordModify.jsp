<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!--<![endif]-->
    <head>
        <%@ include file="/jspi/meta.jsp"%>
        <title><spring:message code="com.jinzhiyi.ffs.message.userPerson.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
	</head>

    <body class="page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
        <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
						<div class="row">
			                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="portlet light bordered">
                                    <div class="portlet-title">
                                        <div class="caption"><i class="icon-docs"></i><spring:message code="com.jinzhiyi.ffs.message.user.panel.modify.password.title"/></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <div class="form-body ">
                                            <form action="${pageContext.request.contextPath}/sys/userPerson/passwordModify.do" method="post" id="modiftyForm">
                                            	<%-- <h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.user.panel.info"/> </h4> --%>
                                            	<input type="hidden" value="${user.recId}" name="recId">
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
                                                	<div class="col-lg-3 col-xs-3 col-md-3 col-md-offset-4">
                                                        <div class="form-group">
                                                       		<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.original_user_password"/></label>
                                                            <input type="password" class="form-control required_input text-lowercase" name="userPassword" id="userPassword" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.original_user_password"/>">
														</div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-3 col-xs-3 col-md-3 col-md-offset-4">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.new_user_password"/></label>
                                                            <input type="password" class="form-control required_input text-lowercase" name="newUserPassword" id="newUserPassword" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.new_user_password"/>">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-3 col-xs-3 col-md-3 col-md-offset-4">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.repeat_user_password"/></label>
                                                            <input type="password" class="form-control required_input text-lowercase" name="repeatUserPassword" id="repeatUserPassword" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.repeat_user_password"/>">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-actions">
                                                    <div class="row">
                                                        <div class="col-md-offset-4 col-md-3 text-center">
                                                        	<button type="submit" class="btn red loading-state-btn" id="btnSave" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving"/>">
                                                        		<i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save"/>
                                                        	</button>
                                                    		<a href="${pageContext.request.contextPath}/sys/userPerson/userPersonDetail.do?recId=${user.recId}" class="btn btn-default margin-left10"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return"/></a>
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
        <%@ include file="/jspi/metronic_base_js.jsp"%>
   		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
               
              	//验证信息
				var modiftyForm = $('#modiftyForm');
	            var error = $('.alert-danger', modiftyForm);
	            var success = $('.alert-success', modiftyForm);

	            jQuery.validator.addMethod("numletter", function(value, element) {
	        		return this.optional( element ) || /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$/.test( value );
	        	}, $.validator.format("Please enter only letters and numbers."));
	            
	            modiftyForm.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "",  // validate all fields including form hidden input
	                messages: {
	                	userPassword: {
	     	        		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_password.required"/>'),
	     	                minlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_password.minlength"/>'),
	     	                maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_password.maxlength"/>'),
	     	                numletter: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_password.numletter"/>')
	     	            },
	                	newUserPassword: {
	     	        		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.new_user_password.required"/>'),
	     	                minlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.new_user_password.minlength"/>'),
	     	                maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.new_user_password.maxlength"/>'),
	     	                numletter: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.new_user_password.numletter"/>')
	     	            },
	     	            repeatUserPassword: {
	     	        		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.repeat_user_password.required"/>'),
	     	                minlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.repeat_user_password.minlength"/>'),
	     	                maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.repeat_user_password.maxlength"/>'),
	     	                numletter: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_password.numletter"/>'),
	     	                equalTo: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.repeat_user_password.equalTo"/>')
	     	            }
	                },
	                rules: {
	                	userPassword: {
	                         required: true,
	                         minlength: 8,
	                         numletter: true,
	                         maxlength: 100
	                     },
	                	newUserPassword: {
	                         required: true,
	                         minlength: 8,
	                         numletter: true,
	                         maxlength: 100
	                     },
	                     repeatUserPassword: {
	                         required: true,
	                         minlength: 8,
	                         maxlength: 100,
	                         numletter: true,
	                         equalTo: "#newUserPassword"
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
	                    form.submit();
	                }
	            });
			});
		</script>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
    </body>
</html>