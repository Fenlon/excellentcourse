$(function() {
			var left;
			var top;
			var ismove = false;

			var interval = setInterval(function() {
						if (ismove) {
							$("#a").css({
										position : "absolute",
										'top' : top,
										'left' : left
									});
						}
					}, 5);

			$("#b").mouseover(function() {
						$(this).css("cursor", "pointer");
					});

			$("#b").mousedown(function() {
						ismove = true;
						$(this).css("cursor", "pointer");
					});
			$("#b").mouseup(function() {
						ismove = false;
						/*
						 * left = event.clientX; top = event.clientY; alert(left +
						 * "--" + top);
						 */
						clearInterval(interval);
					});

			$("#b").mousemove(function() {
						left = event.clientX;
						top = event.clientY;
					});
		});

function move() {

}