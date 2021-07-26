$(function() {
	$('.pagenation').paginathing({
		perPage: 15,
		limitPagination: false,
		firstLast: true,
		firstText: '<<',
		lastText: '>>',
		prevText: '前へ',
		nextText: '次へ',
		activeClass: 'active',
	})

	$('.follow-toggle').click(function() {
		$('.follow-toggle-people').slideToggle(50);
	});

	$('.follower-toggle').click(function() {
		$('.follower-toggle-people').slideToggle(50);
	});
});
