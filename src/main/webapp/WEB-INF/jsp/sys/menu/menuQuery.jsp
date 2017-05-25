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
   </head>
    <body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
     
        <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
	                	<div class="portlet light bordered">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-user"></i><spring:message code="com.jinzhiyi.ffs.message.menu.panel.query.title" /></div>
                                <div class="actions">
									<a href="${pageContext.request.contextPath}/sys/menu/preMenuCreate.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.menu.panel.create.title" /></a>
								</div>
                            </div>
                            <div class="portlet-body">
								<div class="row">
									<form role="form" class="form-inline" action="${pageContext.request.contextPath}/sys/menu/menuQuery.do" id="queryMenuForm" method="post">
										<div class="col-lg-12">
											<div class="form-group">
												<input type="text" class="form-control input-medium disable-ime" id="name" name="name" value="${menu.name}" placeholder='<spring:message code="com.jinzhiyi.ffs.message.menu.menu_name" />'>
												<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" id="btnSubmit" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching" />'><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" /></button>
											</div>
										</div>
									</form>
								</div>
								<div class="row margin-top20">
									<div class="col-lg-12">
										<c:if test="${listMenus != null && (not empty listMenus)}">
                                        <table class="table table-bordered table-striped table-hover">
                                            <thead>
										        <tr>
										        	<th style="width: 50px;"></th>
										        	<th width="50"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_num" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_cn" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_en" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_remark" /></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_icon" /></th>
										          	<th width="60"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_parentId" /></th>
										          	<th width="50"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_sort" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_url" /></th>
										          	<th width="60"><spring:message code="com.jinzhiyi.ffs.message.common.status" /></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.common.btn.operate" /></th>
										        </tr>
										    </thead>
										    <tbody>
										    	<c:forEach items="${listMenus}" var="menu">
										        <tr>
										        	<td style="text-align: center;">
										        		<c:if test="${menu.parent}">
										        			<i id="${menu.recId}" class="fa fa-plus" style="cursor: pointer;"></i>
										        		</c:if>
										    		</td>
										        	<td>${menu.recId}</td>
										          	<td><a href="${pageContext.request.contextPath}/sys/menu/preMenuModify.do?menuId=${menu.recId}">${menu.nameCn}</a></td>
										          	<td>${menu.nameEn}</td>
										          	<td>${menu.remark}</td>
										          	<td><i class="${menu.icon}"></i></td>
										          	<td>${menu.parentId}</td>
										          	<td>${menu.sort}</td>
										          	<td>${menu.url}</td>
										          	<td>
											          	<c:if test="${menu.valid}">
											            <span class="label label-success"><ffs:enum declaringClassName="${menu.validity.declaringClass.name}" name="${menu.validity}" /></span>
											          	</c:if>
											          	<c:if test="${!menu.valid}">
											            <span class="label label-default"><ffs:enum declaringClassName="${menu.validity.declaringClass.name}" name="${menu.validity}" /></span>
											          	</c:if>
										          	</td>
										          	<td>
										        		<c:if test="${menu.parent}">
										          			<a class="btn btn-xs green margin-left10" href="${pageContext.request.contextPath}/sys/menu/preMenuCreate.do?parentId=${menu.recId}"><i class="fa fa-plus"></i> <spring:message code="com.jinzhiyi.ffs.message.menu.btn.create_sub_menu" /></a>
										       			</c:if>
										       		</td>
										        </tr>
										        <c:if test="${menu.parent}">
										        <tr id="subTr${menu.recId}" style="display: none;">
										        	<td></td>
										        	<td colspan="10">
										        		<table class="table table-bordered table-striped table-hover">
				                                            <thead>
														        <tr>
														        	<th width="50"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_num" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_cn" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_name_en" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_remark" /></th>
														          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_icon" /></th>
														          	<th width="60"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_parentId" /></th>
														          	<th width="50"><spring:message code="com.jinzhiyi.ffs.message.menu.menu_sort" /></th>
														          	<th><spring:message code="com.jinzhiyi.ffs.message.menu.menu_url" /></th>
														          	<th width="60"><spring:message code="com.jinzhiyi.ffs.message.common.status" /></th>
														        </tr>
														    </thead>
														    <tbody>
														        <c:forEach items="${menu.children}" var="child">
														        <tr>
														        	<td>${child.recId}</td>
														          	<td><a href="${pageContext.request.contextPath}/sys/menu/preMenuModify.do?menuId=${child.recId}">${child.nameCn}</a></td>
														          	<td>${child.nameEn}</td>
														          	<td>${child.remark}</td>
														          	<td><i class="${child.icon}"></i></td>
														          	<td>${child.parentId}</td>
														          	<td>${child.sort}</td>
														          	<td>${child.url}</td>
														          	<td>
															          	<c:if test="${child.valid}">
															            <span class="label label-success"><ffs:enum declaringClassName="${child.validity.declaringClass.name}" name="${child.validity}" /></span>
															          	</c:if>
															          	<c:if test="${!child.valid}">
															            <span class="label label-default"><ffs:enum declaringClassName="${child.validity.declaringClass.name}" name="${child.validity}" /></span>
															          	</c:if>
														          	</td>
														        </tr>
														        </c:forEach>
														    </tbody>
			                                			</table>
		                                			</td>
		                                		</tr>
		                                		</c:if>
										        </c:forEach>
										    </tbody>
                                        </table>
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