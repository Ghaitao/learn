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
        <title>Demo</title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
<body>
<h1>采用multipartFile的方式上传</h1>
<form action="${pageContext.request.contextPath}/file/upload.do" method="POST" enctype="multipart/form-data">  
          文件1: <input type="file" name="multipartFile"/><br/>   
      
    <input type="submit" value="上传"/>   
</form>  
<h1>采用流的方式上传文件</h1>
<form action="${pageContext.request.contextPath}/file/upload2.do" method="POST" enctype="multipart/form-data">  
          文件2: <input type="file" name="file"/><br/>   
      
    <input type="submit" value="上传"/>   
</form> 
<h1>采用multipart提供的file.transfer方法上传文件</h1>
<form action="${pageContext.request.contextPath}/file/upload3.do" method="POST" enctype="multipart/form-data">  
          文件2: <input type="file" name="file"/><br/>   
      
    <input type="submit" value="上传"/>   
</form> 
<h1>使用spring mvc提供的类的方法上传文件</h1>
<form action="${pageContext.request.contextPath}/file/springFileUpload.do" method="POST" enctype="multipart/form-data">  
          文件3: <input type="file" name="file"/><br/>   
      
    <input type="submit" value="上传"/>   
</form> 
<h1>ZXing二维码联系</h1>
	
<button type="button" class="btn btn-primary red margin-left10 loading-state-btn" onclick="share2dImg()" id="query_submit" data-loading-text='显示中'><i class="fa fa-search"></i> 显示二维码</button>
<div class="app-iphone"><img id="showImage" alt="" ></div> 
<%@ include file="/jspi/metronic_base_js.jsp"%>
<script type="text/javascript">
var link="http://www.baidu.com";
function share2dImg(){
	$("#showImage").attr("src","${pageContext.request.contextPath}/file/zxingShow.do?url="+link+"&width=400&height=400");    
}
$(function(){     
	
	
});
</script>
</body>
</html>