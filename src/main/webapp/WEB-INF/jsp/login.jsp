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
        <title>会员登录</title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/pages/css/login.min.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
        /* 	body {
        		background-image: url('${ffsStaticUrl}/img/forwarder/logo/lch/login_bg.png');
        		background-repeat: repeat;
        	} */
        </style>
    </head>
    <body class="login" style="background-image: url('${ffsStaticUrl}/img/forwarder/logo/lch/login_bg.png');">
    	<div class="text-right padding10">
				<a title="中文版" href=""><i class="flag-icon flag-icon-cn"></i> 简体中文</a>
				<a title="English" href="" class="margin-left10 margin-right10"><i class="flag-icon flag-icon-us"></i> English</a>
		</div>
        <div class="logo"><img src="${ffsStaticUrl}/img/forwarder/logo/lch/text_logo_big_cn.png" alt="上海立驰航国际贸易有限公司" title="上海立驰航国际贸易有限公司"/></div>
        <div class="content">
            <form class="login-form" action="${pageContext.request.contextPath}/login.do" method="post">
                <h3 class="form-title"><spring:message code="com.jinzhiyi.ffs.message.login.title"/></h3>
                <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">
                    <span> <i class="fa fa-exclamation-triangle"></i> ${errorMessage} </span>
                </div>
                </c:if>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9"><spring:message code="com.jinzhiyi.ffs.message.login.usercode"/></label>
					<div class="input-icon input-icon-lg">
						<i class="fa fa-user"></i>
						<input class="form-control input-lg disable-ime" type="text" autocomplete="off" placeholder="<spring:message code="com.jinzhiyi.ffs.message.login.usercode"/>" name="userCode" id="userCode" value="${userCode}"/>
					</div>
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9"><spring:message code="com.jinzhiyi.ffs.message.login.password"/></label>
                    <div class="input-icon input-icon-lg">
						<i class="fa fa-lock"></i>
						<input class="form-control input-lg disable-ime" type="password" autocomplete="off" placeholder="<spring:message code="com.jinzhiyi.ffs.message.login.password"/>" name="userPassword" id="userPassword" />
					</div>
                </div>
                <!-- <div class="form-group">
                    <label class="rememberme check mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="remember" value="1" /><spring:message code="com.jinzhiyi.ffs.message.login.rememberme"/>
                        <span></span>
                    </label>
                </div> -->
                <div class="form-group">
                    <button type="submit" class="btn btn-lg red btn-block input-lg loading-state-btn" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.login.logining"/>" autocomplete="off"><spring:message code="com.jinzhiyi.ffs.message.login.login"/></button>
                </div>
                <div class="form-group text-right">
                	<a href="javascript:;" id="forget-password" class="forget-password"><spring:message code="com.jinzhiyi.ffs.message.login.forget_password"/></a>
                	<%-- <c:if test="${not ffsCompany.adminCompany}"> --%>
						<a href="${pageContext.request.contextPath}/customer/apply.do" id="register-btn" class="forget-password"><spring:message code="com.jinzhiyi.ffs.message.login.apply"/></a>                	
                	<%-- </c:if> --%>
                </div>
            </form>
        </div>
        <%@ include file="/jspi/metronic_base_js.jsp"%>
<script type="text/javascript">
$(function() {
	try {
		if ($('#userCode').val() == '') {
			$('#userCode').focus();
		} else {
			$('#userPassword').focus();
		}
	} catch (e) {}
});
</script>
    </body>
</html>