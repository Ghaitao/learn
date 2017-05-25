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
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
<body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>



<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
<%@ include file="/jspi/metronic_base_js.jsp"%>
<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>

</body>
</html>