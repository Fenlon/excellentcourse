<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发布公告界面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
</head>

<body>
	<div id="container">
		<h2>添加公告</h2>

		<form method="post"
			action="${pageContext.request.contextPath}/servlet/NoticeServlet?method=add">
			<table>
				<tr>
					<td>标题</td>
					<td><input type="text" name="title"><span>不能超过255字符</span></td>
				</tr>
				<tr>
					<td>内容</td>
					<td><textarea rows="10" cols="100" id="content" name="content"></textarea>
						<script type="text/javascript">
							var option = {
								emotionLocalization : true,
								initialContent : '编写文章内容...',//初始化编辑器的内容 
								initialFrameWidth : 900,
								initialFrameHeight : 165,
								autoHeightEnabled : false,
								toolbars : [ [ "fullscreen", "source", "|",
										"undo", "redo", "|", "bold", "italic",
										"underline", "fontborder",
										"strikethrough", "superscript",
										"subscript", "removeformat",
										"formatmatch", "autotypeset",
										"blockquote", "pasteplain", "|",
										"forecolor", "backcolor",
										"insertorderedlist",
										"insertunorderedlist", "selectall",
										"cleardoc", "|", "rowspacingtop",
										"rowspacingbottom", "lineheight", "|",
										"customstyle", "paragraph",
										"fontfamily", "fontsize", "|",
										"directionalityltr",
										"directionalityrtl", "indent", "|",
										"justifyleft", "justifycenter",
										"justifyright", "justifyjustify", "|",
										"touppercase", "tolowercase", "|",
										"link", "unlink", "anchor", "|",
										"imagenone", "imageleft", "imageright",
										"imagecenter", "|", "insertimage",
										"emotion", "scrawl", "insertvideo",
										"music", "attachment", "insertframe",
										"insertcode", "webapp", "pagebreak",
										"template", "background", "|",
										"horizontal", "date", "time",
										"spechars", "snapscreen", "wordimage",
										"|", "inserttable", "deletetable",
										"insertparagraphbeforetable",
										"insertrow", "deleterow", "insertcol",
										"deletecol", "mergecells",
										"mergeright", "mergedown",
										"splittocells", "splittorows",
										"splittocols", "|", "print", "preview",
										"searchreplace", "help" ] ]
							//设置宽度 
							}
							var editor = new UE.ui.Editor(option);
							editor.render("content");
							//1.2.4以后可以使用一下代码实例化编辑器
							//UE.getEditor('myEditor')
						</script></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="reset" val="重置"> <input type="submit"
						value="发布"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
