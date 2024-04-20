(function($) 
{
	"use strict";
	var Parallax=function(object,option)
	{
		var $this=$(object);
		var $optionDefault=
		{
			speed		:	30,
			startPosition: 0
		};
		var $option=$.extend($optionDefault,option);

		this.bind=function()
		{		
			var object=this;
			$(window).on('scroll',function() { object.update(); }).resize(function() { object.update(); });	
			this.update();
		};
		
		this.update=function() 
		{
			var top=$this.offset().top;
			var position=$(window).scrollTop();
			
			var height=$this.innerHeight();
			var windowHeight=$(window).height();
			
			if((top+height<position) || (top>position+windowHeight)) return;

			$this.css('backgroundPosition','50%'+' '+((option.startPosition+position-top)*($option.speed/100))+'px');
		};	
	}
	$.fn.parallax=function(option) 
	{
		var parallax=new Parallax(this,option);
		if(this.length)
			parallax.bind();
	};
})(jQuery);