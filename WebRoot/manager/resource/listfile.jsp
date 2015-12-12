<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<%@taglib uri="/dph" prefix="dph"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>列出所有文件</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/pagequery.js"></script>
<link href="${pageContext.request.contextPath }/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/pagequery.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.pubfile {
	width: 760px;
	text-align: center;
	margin: 0 auto;
	border: 1px solid green;
}

.pubfile td {
	border-bottom: 1px dotted gray;
}
</style>
</head>
<body style="text-align: center;">
	<div id="">
		<table class="pubfile">
			<tr>
				<td><dph:permission value="上传文件">
						<a href="${pageContext.request.contextPath}/servlet/UpfileServlet">添加资源</a>
					</dph:permission></td>
			</tr>
		</table>
		<table class="pubfile">

			<tr>

				<td><a
					href="${pageContext.request.contextPath }/servlet/ListFileServlet?type=all">全部资源</a></td>
				<td><a
					href="${pageContext.request.contextPath }/servlet/ListFileServlet?type=image">图片</a></td>
				<td><a
					href="${pageContext.request.contextPath }/servlet/ListFileServlet?type=movie">视频</a></td>
				<td><a
					href="${pageContext.request.contextPath }/servlet/ListFileServlet?type=music">音乐</a></td>
				<td><a
					href="${pageContext.request.contextPath }/servlet/ListFileServlet?type=doc">文档</a></td>
				<td><a
					href="${pageContext.request.contextPath }/servlet/ListFileServlet?type=package">压缩包</a></td>
				<td><a
					href="${pageContext.request.contextPath }/servlet/ListFileServlet?type=others">其他</a></td>
			</tr>

		</table>

		<table class="pubfile" frame="border">
			<tr>
				<td>文件名称</td>
				<td>上传时间</td>
				<td>文件描述</td>
				<td>上传人</td>
				<td>操作</td>
			</tr>
			<c:forEach var="upfile" items="${requestScope.pagebean.list}">
				<tr>
					<td>${upfile.filename }</td>
					<td>${fn:substringBefore(upfile.uptime,".0") }</td>
					<td>${upfile.description }</td>
					<td>${upfile.username }</td>
					<td>
						<div>
							<a
								href="${pageContext.request.contextPath }/servlet/DownLoadServlet?method=download&id=${upfile.id }">下载</a>
						</div> <dph:permission value="删除文件">
							<div>
								<a
									href="${pageContext.request.contextPath }/servlet/DownLoadServlet?method=delete&id=${upfile.id }&currentpage=${pagebean.currentpage}&pagesize=${pagebean.pagesize}">删除</a>
							</div>
						</dph:permission>

					</td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<td><span>每页</span><input id="pagesize" type="text"
					style="width: 30px;" maxlength="2"
					value="${requestScope.pagebean.pagesize }"
					onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${pageContext.request.contextPath }/servlet/ListFileServlet?type=${type }&')">条
				</td>
				<td><span> <c:choose>
							<c:when test="${requestScope.pagebean.currentpage==1}">
								<c:out value="第一页     上一页"></c:out>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)"
									onclick="gotopage(1,'${pageContext.request.contextPath}/servlet/ListFileServlet?type=${type }&')">第一页</a>
								<a href="javascript:void(0)"
									onclick="gotopage(${requestScope.pagebean.previouspage},'${pageContext.request.contextPath}/servlet/ListFileServlet?type=${type }&')">上一页</a>
							</c:otherwise>
						</c:choose>


				</span> <c:forEach var="pagenum" items="${requestScope.pagebean.pagebar }">
						<c:if test="${pagenum==pagebean.currentpage}">
							<font color="red">${pagenum }</font>
						</c:if>
						<c:if test="${pagenum!=pagebean.currentpage}">
							<a href="javascript:void(0)"
								onclick="gotopage(${pagenum },'${pageContext.request.contextPath}/servlet/ListFileServlet?type=${type }&')">${pagenum}</a>
						</c:if>
					</c:forEach> <span> <c:choose>
							<c:when
								test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
								<c:out value="下一页     最后页"></c:out>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)"
									onclick="gotopage(${requestScope.pagebean.nextpage},'${pageContext.request.contextPath}/servlet/ListFileServlet?type=${type }&')">下一页</a>
								<a href="javascript:void(0)"
									onclick="gotopage(${requestScope.pagebean.totalpage},'${pageContext.request.contextPath}/servlet/ListFileServlet?type=${type }&')">最后页</a>
							</c:otherwise>
						</c:choose>

				</span></td>
				<td>GO<input name="currentpage" id="pagenum" type="text"
					style="width: 50px;" /> <input type="image"
					style="margin-bottom: -3px;"
					onclick="gotopage(document.getElementById('pagenum').value,'${pageContext.request.contextPath}/servlet/ListFileServlet?type=${type }&')"
					src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
			</tr>
		</table>
	</div>
</body>
</html>
