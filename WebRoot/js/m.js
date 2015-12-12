$(function() {
			var color;
			$(".div2").hide();
			$(".div1").mouseover(function() {
						$(".div2").show();
					}).mouseout(function() {
						$(".div2").hide();
					});
			$("ul li").mouseover(function() {
						var clazz = $(this).attr("class");
						$("dl").each(function() {
									if ($(this).hasClass(clazz)) {
										$(this).css("background-color",
												"#6699FF");
									} else {
										$(this).css("background-color",
												"#CCCCFF");
									}
								});
					}).mouseout(function() {

					});
			$("dl").mouseover(function() {
						$(this).css("background-color", "#6699FF")
					}).mouseout(function() {
						$(this).css("background-color", "#CCCCFF")
					});
			$("dd").mouseover(function() {
						color = $(this).css("background-color");
						$(this).css("background-color", "white")
					}).mouseout(function() {
						$(this).css("background-color", color)
					});
		});