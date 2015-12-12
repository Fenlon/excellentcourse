function gotopage(currentpage, url) {
	if (currentpage < 1 || currentpage != parseInt(currentpage)) {
		alert("请输入合法值!");
		document.getElementById("pagenum").value = '';
	} else {
		var pagesize = document.getElementById("pagesize").value;
		window.location.href = url + 'currentpage=' + currentpage
				+ '&pagesize=' + pagesize;
	}
}

function changePageSize(pagesize, oldPageSize, url) {
	if (pagesize < 1 || pagesize != parseInt(pagesize)) {
		alert("请输入合法值!")
		document.getElementById("pagesize").value = oldPageSize;
	} else
		window.location.href = url + 'pagesize=' + pagesize;

}

function del(id, currentpage, url) {
	if (window.confirm("您确定要删除吗？")) {
		window.location.href = url + 'currentpage=' + currentpage + '&id=' + id;
	}
}