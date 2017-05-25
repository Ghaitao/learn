<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${not empty errorMessage}">
	<div class="row">
		<div class="col-lg-12 col-xs-12 col-md-12">
			<div class="alert alert-danger">
				<span> <i class="fa fa-exclamation-triangle"></i> ${errorMessage}</span>
			</div>
		</div>
	</div>
</c:if>