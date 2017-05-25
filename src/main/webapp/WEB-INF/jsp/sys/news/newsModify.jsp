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
        <title><spring:message code="com.jinzhiyi.ffs.message.news.title"/></title>
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-summernote/summernote.css" rel="stylesheet" type="text/css" />
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/skins/default.css" rel="stylesheet" type="text/css" />
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
                                        <div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.news.panel.modify.title" /></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form action="${pageContext.request.contextPath}/sys/news/newsModify.do" id="modifyNewsForm" enctype="multipart/form-data" method="post">
                                            <div class="form-body">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.news.panel.info" /></h4>
                                            	<input type="hidden" name="recId" id="recId" value="${news.recId}">
                                            	<input type="hidden" name="content" id="content" value='${news.content}'>
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
                                                    <div class="col-lg-12 col-xs-12 col-md-12">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.news.news_title" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input" name="title" id="title" 
                                                            	value="${news.title}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_title" />">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-12 col-xs-12 col-md-12">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.news.news_summary" /></label>
                                                           <%--  <input type="text" class="form-control disable-ime" name="summary" id="summary" 
                                                            	value="${news.summary}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_summary" />"> --%>
                                                        	<textarea rows="3" cols="8" class="form-control disable-ime" name="summary" id="summary" placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_summary" />" ></textarea>
                                                        </div>
                                                    </div>
                                                </div>
												<div class="row">
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.news.news_startDateTime" /></label>
                                                            <input type="text" class="form-control disable-ime date-picker" name="startDateTime" id="startDateTime" 
                                                            	value="<fmt:formatDate value="${news.startDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_startDateTime" />" autocomplete="off">
                                            			</div>
                                           			</div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.news.news_expiryDateTime" /></label>
                                                        	<input type="text" class="form-control disable-ime date-picker" name="expiryDateTime" id="expiryDateTime" 
                                                        		value="<fmt:formatDate value="${news.expiryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_expiryDateTime" />" autocomplete="off">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-xs-2 col-md-2">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.news.select.grade" /><span class="required">*</span></label>
                                                            <form:select cssClass="form-control required_input" name="grade" items="${gradeListSelect}"
                                                            	itemValue="value" itemLabel="label" path="news.grade">
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-2 col-xs-2 col-md-2">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.news.select.top" /><span class="required">*</span></label>
                                                             <form:select cssClass="form-control required_input" name="top" items="${topListSelect}"
                                                            	itemValue="value" itemLabel="label" path="news.top">
                                                            </form:select>
                                                        </div>
                                                    </div>
												</div>
												<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.news.news_content" /></h4>
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			                                            <div class="form-group last">
		                                                    <div id="summernote_TextModify"> </div>
			                                            </div>
													</div>
												</div>
	                                            <div class="form-actions">
	                                                <div class="row">
	                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
	                                                     	<button type="submit" class="btn red loading-state-btn" id="submit_btn" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />'><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
	                                                     	<a href="javascript:;" class="btn btn-default margin-left10" id="btn_delete"><i class="glyphicon glyphicon-trash"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.delete"/></a>
	                                                     	<%-- <c:if test="${!news.topped}">
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/news/newsTop.do?newsId=${news.recId}"><spring:message code="com.jinzhiyi.ffs.message.news.btn.top"/></a>
	                                                     	</c:if>
	                                                     	<c:if test="${news.topped}">
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/news/newsUnTop.do?newsId=${news.recId}"><spring:message code="com.jinzhiyi.ffs.message.news.btn.unTop"/></a>
	                                                     	</c:if>
	                                                     	<c:if test="${!news.importantGrade}">
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/news/newsImportantGrade.do?newsId=${news.recId}"><spring:message code="com.jinzhiyi.ffs.message.news.btn.important"/></a>
	                                                     	</c:if>
	                                                     	<c:if test="${news.importantGrade }">
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/news/newsNormalGrade.do?newsId=${news.recId}"><spring:message code="com.jinzhiyi.ffs.message.news.btn.normal"/></a>
	                                                     	</c:if> --%>
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/news/newsQueryNoResult.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return" /></a>
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
       	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>
		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-summernote/lang/summernote-zh-CN.min.js" type="text/javascript"></script>
		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/jquery.artDialog.js"></script>
		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/plugins/iframeTools.js"></script>
		<script type="text/javascript">   
			$(function(){    
				
				$("#summary").val("${news.summary}");
				
				$("#btn_delete").click(function() {
					$.dialog.confirm('<spring:message code="com.jinzhiyi.ffs.message.news.prompt_delete"/>', function() {
						window.location.href = '${pageContext.request.contextPath}/sys/news/newsDelete.do?newsId=${news.recId}';
					});
				});
				
				//summernote初始化
				$('#summernote_TextModify').summernote({
					 height: 300,
					 lang: '<ffs:language localeFlag="true" />',
					 minHeight: null,
					 maxHeight: null,
					 toolbar: [
					           ['style', ['style']],
					           ['font', ['bold', 'underline', 'clear']],
					           ['fontname', ['fontname']],
					           ['color', ['color']],
					           ['para', ['ul', 'ol', 'paragraph']],
					           ['table', ['table']],
					           ['insert', ['link']],
					           ['view', ['fullscreen', 'codeview', 'help']]
					         ]
				});
				
				$('#summernote_TextModify').summernote('code',  $('#content').val());
				
				//显示日历
				$('.date-picker').datetimepicker({
					language: '<ffs:language localeFlag="true" />',
		            autoclose: true,
		            isRTL: App.isRTL(),
		            format: "yyyy-mm-dd hh:ii:ss",
		            pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
				});
				
				//验证信息
				var modifyNewsForm = $('#modifyNewsForm');
				var error = $('.alert-danger', modifyNewsForm);
				var success = $('.alert-success', modifyNewsForm);
				
				jQuery.validator.addMethod("noSpace", function(value, element) {
					  return value.indexOf(" ") < 0 && value != "";
				}, "No space please and don't leave it empty");

				modifyNewsForm.validate({
					errorElement : 'span', //default input error message container
					errorClass : 'help-block help-block-error', // default input error message class
					focusInvalid : false, // do not focus the last invalid input
					ignore : "", // validate all fields including form hidden input
					messages : {
						title : {
							required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.news.news_title_required"/>'),
							maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.news.news_title_maxlength"/>'),
							noSpace : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.news.news_title_noSpace"/>')
						},
						summary : {
							maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.news.news_summary_maxlength"/>')
						},
						grade : {
							required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.news.grade_top_required"/>')
						},
						top : {
							required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.news.grade_top_required"/>')
						}
					},
					rules : {
						title : {
							maxlength : 300,
							required : true,
							noSpace : true
						},
						summary : {
							maxlength : 300
						},
						grade : {
							required : true
						},
						top : {
							required : true
						}
					},
					errorPlacement : function(error, element) { // render error placement for each input type
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
					invalidHandler : function(event, validator) { //display error alert on form submit              
						success.hide();
						error.show();
						App.scrollTo(error, -200);
					},
					highlight : function(element) { // hightlight error inputs
						$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
					},
					unhighlight : function(element) { // revert the change done by hightlight
						$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
					},
					success : function(label) {
						label.closest('.form-group').removeClass('has-error'); // set success class to the control group
					},
					submitHandler : function(form) {
						success.show();
						error.hide();
						
						var sHTML = $('#summernote_TextModify').summernote('code');
						$("#content").val(sHTML);
						
						form.submit();
					}
				});
			});
		</script>
</body>
</html>