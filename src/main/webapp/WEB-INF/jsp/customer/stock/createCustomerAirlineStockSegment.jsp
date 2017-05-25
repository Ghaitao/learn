<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<head>
	<%@ include file="/jspi/meta.jsp"%>
    <title><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.page.create.title"/></title>
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
                                            <div class="caption"><i class="fa fa-edit"></i><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.page.create.panel.title"/> </div>
                                        </div>
                                        <div class="portlet-body form">
                                            <form:form action="${pageContext.request.contextPath}/customer/doCreateCustomerAirlineStockSegment.do" modelAttribute="customerAirlineStockSegment" id="frmCreateCustomerAirlineStockSegment" cssClass="horizontal-form">
                                                <div class="form-body">
                                                	<%@ include file="/WEB-INF/jsp/common/errorMessageTip.jsp"%>
													<div class="row">
                                                    	<div class="col-lg-3 col-xs-3 col-md-3">
                                                            <div class="form-group">
                                                                <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.customerCode"/><span class="required">*</span>：</label>
                                                                <form:select path="customerCode" items="${airlines}" itemLabel="label" itemValue="value" cssClass="form-control required_input" />
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-xs-2 col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.stkPrefix"/><span class="required">*</span>：</label>
                                                                <form:input path="stkPrefix" cssClass="form-control required_input"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-xs-2 col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.startNo"/><span class="required">*</span>：</label>
                                                                <form:input id="startNo" path="startNo" cssClass="form-control required_input"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-xs-2 col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.count"/><span class="required">*</span>：</label>
                                                                <form:input id="count" path="count" cssClass="form-control required_input"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-xs-2 col-md-2">
                                                            <div class="form-group">
                                                                <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.endNo"/><span class="required">*</span>：</label>
                                                                <form:input id="endNo" path="endNo" cssClass="form-control required_input"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-actions">
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                        	<button type="submit" class="btn red loading-state-btn" id="btnSubmit" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.page.create.button.generating" />"><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.page.create.button.generate" /></button>
                                                        	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/customer/queryCustomerAirlineStockSegments.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.button.back_seg_query"/></a>
                                                            <a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/customer/queryCustomerAirlineStockDetails.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.button.back_detail_query"/></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form:form>
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
    <script type="text/javascript">
    	$(function(){
    		$('#count').blur(function () {
    			var startNo = $('#startNo').val();
        		var count = $(this).val();
        		var endNo = null;
        		try {
        			endNo = parseInt(startNo) + parseInt(count) - 1;
        		} catch (e) {}
        		if (endNo) {
        			$('#endNo').val(endNo);
        		}
    		});
    		
			$('#endNo').blur(function () {
				var startNo = $('#startNo').val();
        		var endNo = $(this).val();
        		var count = null;
        		try {
        			count = parseInt(endNo) - parseInt(startNo) + 1;
        		} catch (e) {}
        		if (count) {
        			$('#count').val(count);
        		}
    		});
			
    		$('#startNo').blur(function () {
    			var startNo = $(this).val();
        		var count = $('#count').val();
        		var endNo = $('#endNo').val();
        		
        		if (startNo != '') {
        			if (count != '') {
        				$('#count').blur();
        			} else if (endNo != '') {
        				$('#endNo').blur();
        			}
        		}
    		});
    		
            $('#frmCreateCustomerAirlineStockSegment').validate({
                messages: {
                	'customerCode' : '<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.customerCode"/>',
                	'stkPrefix' : '<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.stkPrefix"/>',
                	'startNo' : '<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.startNo"/>',
                	'count' : '<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.count"/>',
                	'endNo' : '<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.endNo"/>'
                },
                rules: {
                	'customerCode' : {
                		required: true
                	},
                	'stkPrefix' : {
                		required:true,
                		maxlength : 3,
                		minlength : 3
                	},
                	'startNo' : {
                		required:true,
                		digits : true,
                		maxlength : 7,
                		minlength : 7
                	},
                	'count' : {
                		required:true,
                		digits : true,
                		maxlength : 5
                	},
                	'endNo' : {
                		required:true,
                		digits : true,
                		maxlength : 7,
                		minlength : 7
                	}
                }
            });
    	});
    </script>	
</body>
</html>