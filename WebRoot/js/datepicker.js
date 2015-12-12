$(function() {
			$("#birthday").css("readonly", "readonly").click(function() {
						new Calendar().show(this);
					});
		});