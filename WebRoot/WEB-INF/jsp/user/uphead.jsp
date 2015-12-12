<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>图片上传</title>
<!-- add styles -->
<link href="${pageContext.request.contextPath}/css/photo_css/main.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/css/photo_css/jquery.Jcrop.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />

<!-- add scripts -->
<script
	src="${pageContext.request.contextPath}/js/photo_js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/photo_js/jquery.Jcrop.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/photo_js/notescript.js"></script>
</head>
<body>
	<div id="container">
		<div class="demo" style=" margin-top:60px;">
			<div class="bheader">
				<h2>编辑头像</h2>
			</div>
			<div class="bbody">

				<!-- upload form -->
				<form id="upload_form" enctype="multipart/form-data" method="post"
					action="${pageContext.request.contextPath }/servlet/upHeadServlet"
					onsubmit="return checkForm()">
					<!-- hidden crop params -->
					<input type="hidden" id="x1" name="x1" /> <input type="hidden"
						id="y1" name="y1" /> <input type="hidden" id="x2" name="x2" /> <input
						type="hidden" id="y2" name="y2" />
					<h2>第一步:请选择图像文件</h2>
					<div>
						<input type="file" name="image_file" id="image_file"
							onchange="fileSelectHandler()" />
					</div>

					<div class="error"></div>

					<div class="step2">
						<h2>请选择需要截图的部位,然后按上传</h2>
						<img id="preview" />

						<div class="info">
							<label>文件大小</label> <input type="text" id="filesize"
								name="filesize" /> <label>类型</label> <input type="text"
								id="filetype" name="filetype" /> <label>图像尺寸</label> <input
								type="text" id="filedim" name="filedim" /> <label>宽度</label> <input
								type="text" id="w" name="width" /> <label>高度</label> <input
								type="text" id="h" name="heigth" />
						</div>

						<input type="submit" value="上传" />
					</div>
				</form>
			</div>
		</div>
		<div>
			<c:url value="../../public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot }"></c:import>
		</div>
	</div>
</body>
</html>