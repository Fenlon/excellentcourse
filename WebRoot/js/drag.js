$(function() {
			$(".answertitle").mousedown(function(e) {
						iDiffX = e.pageX - $(this).offset().left;
						iDiffY = e.pageY - $(this).offset().top;
						$(document).mousemove(function(e) {
									$("#answer").css({
												"left" : (e.pageX - iDiffX),
												"top" : (e.pageY - iDiffY)
											});
								});
					});
			$(".answertitle").mouseup(function() {
						$(document).unbind("mousemove");
					});
		});