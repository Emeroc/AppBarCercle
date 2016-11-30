;(function($){ // secure $ jQuery alias

$.fn.azscroller = function(target, idField, nameField,priceField) {
	/*var lineHeight = 25; // line-height li
	var padding = 6; // padding class initial
	var originalSize = 12; // font-size az
	
	var azPos = {};*/
	var originalSize = 12; // font-size az
	  	
	$(this).attr('unselectable','on').css('MozUserSelect','none');
	/*
    var leftButtonDown = false;
    $(document).mousedown(function(e) { if(e.which === 1) leftButtonDown = true; });
    $(document).mouseup(function(e) { if(e.which === 1) leftButtonDown = false; });
	*/
	var lastInitial = 0;
	target.find('li').each(function(index) {
		var name = $(this).find('a').attr('data-username');
		
		if (name.substring(0,1).toUpperCase() != lastInitial) {
			/*var scrollPos = lineHeight * $(this).index() + padding * Object.keys(azPos).length;
			if (typeof azPos[lastInitial] != 'undefined') {
				azPos[lastInitial].end = scrollPos;
			}*/
			lastInitial = name.substring(0,1).toUpperCase();
			target.find('li:eq(' + $(this).index() + ')').before($('<li class="initial" id="'+lastInitial+'">' + lastInitial + '</li>'));
			/*azPos[lastInitial] = {start: scrollPos, end: target.height()};*/
		}
		$(this).click(function() {

			if(($(this).find('a').attr('data-price')))
				priceField.val($(this).find('a').attr('data-price'));
			idField.val($(this).find('a').attr('data-userid')).trigger('change');
			nameField.val(name);
		});
	});	

	var alphabet = $(document.createElement('div'));
	var ul = $(document.createElement('ul'));
		
	for(var i=0; i<26; i++) {
		(function (i) {
			var li = $(document.createElement('li'))
				.attr('id', 'letter' + String.fromCharCode(65 + i))
				.html(String.fromCharCode(65 + i))
				.click(function(e) {
					$('#clients-list').animate({
						
		                   scrollTop:$('#clients-list').scrollTop()+$('#'+String.fromCharCode(65 + i)).position().top 
		               }, 'slow');
					$(this).css('font-size', originalSize + 3*originalSize/2 + 'px');
					var letter = $(this);
					setTimeout(function () { letter.css('font-size', originalSize + 'px'); }, 600);
					
					return false;
				});
			/*	.mousedown(function(e) {
					var relY = e.pageY - $(this).offset().top;
					if (typeof azPos[$(this).html()] != 'undefined') {
						target.scrollTop((azPos[$(this).html()].end - azPos[$(this).html()].start)*relY/$(this).height() + azPos[$(this).html()].start);
					}
				})
				.mousemove(function(e) {
					var relY = e.pageY - $(this).offset().top;

					if (i > 0) $('#letter' + String.fromCharCode(65 + (i - 1))).css('font-size', originalSize + originalSize/2 + 'px');
					$(this).css('font-size', originalSize + 3*originalSize/2 + 'px');
					if (i < 26) $('#letter' + String.fromCharCode(65 + (i + 1))).css('font-size', originalSize + originalSize/2 + 'px');
					
					if (leftButtonDown && typeof azPos[$(this).html()] != 'undefined') {
						target.scrollTop((azPos[$(this).html()].end - azPos[$(this).html()].start)*relY/$(this).height() + azPos[$(this).html()].start);
					}
				})
				.mouseleave(function(e) {
					if (i > 0) $('#letter' + String.fromCharCode(65 + (i - 1))).css('font-size', originalSize + 'px');
					$(this).css('font-size', originalSize + 'px');
					if (i < 26) $('#letter' + String.fromCharCode(65 + (i + 1))).css('font-size', originalSize + 'px');
				});	*/	
			alphabet.append(li);
		})(i);
  	}
	
	$(this).append(alphabet
		.append(ul)
	);
}; //end plugin azselector

})(jQuery); // confine scope