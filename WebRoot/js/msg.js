$(function() {
			$(".reply").each().bind("click", function() {
						alert("ok")
					});
		});

function reply(item) {
	alert($(item).val());
}