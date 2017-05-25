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
        <title><spring:message code="com.jinzhiyi.ffs.message.user.title"/></title>
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
                                        <div class="caption"><i class="icon-docs"></i><spring:message code="com.jinzhiyi.ffs.message.user.panel.modify.title"/></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <div class="form-body ">
                                            <form action="${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyModify.do" method="post" id="modiftyForm">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.user.panel.info"/> </h4>
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
                                                	<div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                       		<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.user_code"/></label>
                                                            <div class="form-control no-border">
																<strong>${user.userCode}</strong>
															</div>
														</div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.user_name_cn"/><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input" name="userNameCn"
                                                            	value="${user.userNameCn }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.user_name_cn"/>">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.user_name_en"/></label>
                                                            <input type="text" class="form-control disable-ime" name="userNameEn"
                                                            	value="${user.userNameEn }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.user_name_en"/>">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.mobile_phone"/></label>
                                                            <input type="text" class="form-control" name="mobilePhone"
                                                            	value="${user.mobilePhone }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.mobile_phone"/>">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.telephone"/></label>
                                                            <input type="text" class="form-control" name="telephone"
                                                            	value="${user.telephone }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.telephone"/>">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.email"/></label>
                                                            <input type="text" class="form-control" name="email"
                                                            	value="${user.email }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.email"/>">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.qq"/></label>
                                                            <input type="text" class="form-control" name="qq"
                                                            	value="${user.qq }"  placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.qq"/>">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.company_code"/></label>
                                                            <div class="form-control no-border">
																<strong><ffs:company companyCode="${user.companyCode}" /></strong>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.user.customer_code"/></label>
                                                            <div class="form-control no-border">
																<strong><%-- <ffs:customer customerId="${user.customerId}" /> --%>${user.customerCode}</strong>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <h4 class="form-section">角色信息</h4>
												<div class="row form-horizontal">
													<div class="col-lg-12 col-xs-12 col-md-12">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-1 col-xs-1 col-md-1"><spring:message code="com.jinzhiyi.ffs.message.user.role_choose"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
				                                                <div class="mt-checkbox-inline">	
				                                                	<c:forEach items="${roles}" var="role">
				                                                		<label class="mt-checkbox">
				                                                			<input type="checkbox" id="roleId${role.roleId }" name="roleIds" value="${role.roleId }" data-error-container="#roleIds_error"><ffs:role roleId="${role.roleId }"/>
			                                                            	<span></span>
			                                                            </label>
				                                                	</c:forEach>
			                                                    </div>
			                                                    <div id="roleIds_error"> </div>
		                                                   	</div>
	                                                  	</div>
	                                                </div>
												</div>
                                                <div class="form-actions">
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                        	<button type="submit" class="btn red loading-state-btn" id="btnSave" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving"/>" autocomplete="off">
                                                        		<i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save"/>
                                                        	</button>
                                                    		<a href="${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyQuery.do?companyId=${user.companyId }" class="btn btn-default margin-left10"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return_search"/></a>
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
                <c:forEach items="${userRoles}" var="userRole">
                	$("#roleId" + ${userRole.roleId }).attr("checked", "checked");
                </c:forEach>
                
              	//验证信息
				var modiftyForm = $('#modiftyForm');
	            var error = $('.alert-danger', modiftyForm);
	            var success = $('.alert-success', modiftyForm);

	            modiftyForm.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "",  // validate all fields including form hidden input
	                messages: {
	                	userNameCn: {
	                		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_name_cn.required"/>'),
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_name_cn.maxlength"/>')
	                    },
	                    userNameEn: {
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.user_name_en.maxlength"/>')
	                    },
	                    mobilePhone: {
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.mobile_phone.maxlength"/>')
	                    },
	                    telephone: {
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.telephone.maxlength"/>')
	                    },
	                    email: {
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.email.maxlength"/>')
	                    },
	                    qq: {
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.qq.maxlength"/>')
	                    },
	                    roleIds: {
	                    	required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.user.roleIds.required"/>')
	                    }
	                },
	                rules: {
	                	userNameCn: {
	                        maxlength: 300,
	                        required: true
	                    },
	                    userNameEn: {
	                        maxlength: 300,
	                    },
	                    mobilePhone: {
	                        maxlength: 100
	                    },
	                    telephone: {
	                    	maxlength: 100
	                    },
	                    email: {
	                    	maxlength: 100
	                    },
	                    qq: {
	                    	maxlength: 100
	                    },
	                    roleIds: {
	                        required: true
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