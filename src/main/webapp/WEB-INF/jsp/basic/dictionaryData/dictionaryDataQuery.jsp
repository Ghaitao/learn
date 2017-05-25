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
	                	<div class="portlet light bordered">
	                        <div class="portlet-title">
	                            <div class="caption"><i class="icon-user"></i><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.panel.query.title" /></div>
	                            <div class="actions">
									<a href="${pageContext.request.contextPath}/basic/dictionaryData/preDictionaryDataCreate.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.dictionaryData.panel.create.title" /></a>
								</div>
	                        </div>
	                        <div class="portlet-body">
								<div class="row">
									<form role="form" class="form-inline" action="${pageContext.request.contextPath}/basic/dictionaryData/dictionaryDataQuery.do" id="queryDictionaryDataForm" method="post">
										<div class="col-lg-12">
											<div class="form-group">
												<input type="text" class="form-control input-medium disable-ime" name="code" id="code" value="${dictionaryData.code}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_code" />">
												<%-- <input type="text" class="form-control input-medium disable-ime" name="parentCode" id="parentCode" value="${dictionaryData.parentCode}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_parentCode" />"> --%>
												<input type="text" class="form-control input-medium disable-ime" name="name" id="name" value="${dictionaryData.name}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name" />">
												<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" id="query_submit" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching" />'><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" /></button>
											</div>
										</div>
									</form>
								</div>
								<div class="row margin-top20">
									<div class="col-lg-12">
										<c:if test="${dictionaryDatas != null && (not empty dictionaryDatas)}">
		                                <table class="table table-bordered table-striped table-hover">
		                                    <thead>
										        <tr>
										        	<th style="width: 50px;"></th>
										        	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_code" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_cn" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_en" /></th>
										          	<%-- <th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_parentCode" /></th> --%>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_sorter" /></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.common.btn.operate" /></th>
										        </tr>
										    </thead>
										    <tbody>
										    	<c:forEach items="${dictionaryDatas}" var="dictionaryData">
										    	<tr>
										    		<td style="text-align: center;">
										        		<i id="${dictionaryData.code}" class="fa fa-plus" style="cursor: pointer;"></i>
										    		</td>
										    		<td><a href="${pageContext.request.contextPath}/basic/dictionaryData/preDictionaryDataModify.do?code=${dictionaryData.code}&parentCode=${dictionaryData.parentCode}">${dictionaryData.code}</a></td>
										          	<td>${dictionaryData.nameCn}</td>
										          	<td>${dictionaryData.nameEn}</td>
										          	<%-- <td>${dictionaryData.parentCode}</td> --%>
										          	<td>${dictionaryData.sorter}</td>
										          	<td>
										          		<a class="btn btn-xs green margin-left10" href="${pageContext.request.contextPath}/basic/dictionaryData/preDictionaryDataCreate.do?parentCode=${dictionaryData.code}"><i class="fa fa-plus"></i> <spring:message code="com.jinzhiyi.ffs.message.dictionaryData.btn.create_sub_dict" /></a>
										          	</td>
										        </tr>
										        <tr id="subTr${dictionaryData.code}" style="display: none;">
										        	<td></td>
										        	<td colspan="5">
											        	<table class="table table-bordered table-striped table-hover">
						                                    <thead>
														        <tr>
														        	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_code" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_cn" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_name_en" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_parentCode" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.dictionaryData.dictionaryData_sorter" /></th>
														        </tr>
														    </thead>
														    <tbody>
														    	<c:forEach items="${dictionaryData.subDicts}" var="subDict">
														    	<tr>
														    		<td><a href="${pageContext.request.contextPath}/basic/dictionaryData/preDictionaryDataModify.do?code=${subDict.code}&parentCode=${subDict.parentCode}">${subDict.code}</a></td>
														          	<td>${subDict.nameCn}</td>
														          	<td>${subDict.nameEn}</td>
														          	<td>${subDict.parentCode}</td>
														          	<td>${subDict.sorter}</td>
														        </tr>
														    	</c:forEach>
														    </tbody>
			                                			</table>
		                                			</td>
										        </tr>
										    	</c:forEach>
										    </tbody>
		                                </table>
                                        <div class="clearfix text-right" style="margin-top:10px;">
											<framework:pagination />
										</div>
		                                </c:if>
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
		<script type="text/javascript">   
			$(function(){     
				//点击图片属性 显示或隐藏子表
				$('td i').click(function(){
					//显示方法
					if ($(this).attr("class") == 'fa fa-plus') {
						$(this).attr("class", "fa fa-minus");
						$("#subTr" + $(this).attr("id")).show();
					} else {
						$(this).attr("class", "fa fa-plus");
						$("#subTr" + $(this).attr("id")).hide();
					}
				});
			});
		</script>
    </body>
</html>