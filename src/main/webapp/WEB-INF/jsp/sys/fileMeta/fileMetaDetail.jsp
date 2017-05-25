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
        <title><spring:message code="com.jinzhiyi.ffs.message.fileMeta.title"/></title>
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
                                        <div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.fileMeta.panel.detail.title"/></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form action="companyQuery.html" class="form-horizontal" id="frm">
                                            <div class="form-body">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.panel.info"/> </h4>
                                            	<div class="row">
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.orig_file_name"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.origFileName}</p>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.file_suffix"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.fileSuffix}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.file_desc"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.fileDesc}</p>
															</div>
                                                        </div>
                                                    </div>
												</div>
                                                <div class="row">
                                                	<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.content_type"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.contentType}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.file_size"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.fileSize}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.storage_medium"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.storageMedium}</p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.upload_dir_path"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.uploadDirPath}</p>
															</div>
                                                        </div>
                                                    </div>
                                                	<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.upload_file_sld_name"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.uploadFileSldName}</p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
													<div class="col-lg-8 col-xs-8 col-md-8">
														<div class="form-group">
															<label class="control-label col-lg-2 col-xs-2 col-md-2"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.file_id"/>：</label>
															<div class="col-lg-10 col-xs-10 col-md-10">
																<p class="form-control-static">${dto.fileId}</p>
															</div>
														</div>
													</div>
												</div>
                                                <div class="row">
                                                	<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.company_code"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.companyCode}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.correlated_customer_code"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.correlatedCustomerCode}</p>
															</div>
                                                        </div>
                                                	</div>
												</div>
												<div class="row">
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.creator"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><ffs:user userId="${dto.creator}" /></p>
															</div>
                                                        </div>
                                                    </div>
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.create_date_time"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><fmt:formatDate value="${dto.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
															</div>
														</div>
													</div>
												</div>
												<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.panel.pic.info"/></h4>
	                                            <div class="row">
	                                            	<div>
		                                            	<div class="col-lg-12 col-xs-12 col-md-12">
		                                            		<img src="<ffs:fileMetaImageUrl fileMetaId="${dto.fileMetaId}" showDefaultImage="false"/>" />
		                                            	</div>
	                                            	</div>
	                                            </div>
                                            </div>
                                            <div class="form-actions">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                    	<!-- <a href="javascript:;" class="btn btn-default margin-left10"><i class="glyphicon glyphicon-trash"></i> 删 除</a> -->
                                                		<a href="${pageContext.request.contextPath}/sys/fileMeta/fileMetaQuery.do" class="btn btn-default margin-left10" ><i class="icon-share-alt"></i>  <spring:message code="com.jinzhiyi.ffs.message.common.btn.return"/></a>
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
         <%@ include file="/jspi/metronic_base_js.jsp"%>
		<script type="text/javascript">
		$(function(){
		});
		</script>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
    </body>
</html>