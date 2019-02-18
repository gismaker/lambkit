$(function() {
	// Waves初始化
	Waves.displayEffect();
	// 输入框获取焦点后出现下划线
	$('.form-control').focus(function() {
		$(this).parent().addClass('fg-toggled');
	}).blur(function() {
		$(this).parent().removeClass('fg-toggled');
	});
});
Checkbix.init();
$(function() {
	// 回车事件
	$('#username, #password, #respass, #captcha').keypress(function (event) {
		if (13 == event.keyCode) {
			regist();
		}
	});
	$('#regitst-bt').click(function() {
		regist();
	});
});

function regist() {
	$.ajax({
		url: BASE_PATH + '/regist',
		type: 'POST',
		data: {
			username: $('#username').val(),
			password: $('#password').val(),
			repswd:$("#respass").val(),
			captcha: $('#captcha').val(),
			//rememberMe: $('#rememberMe').is(':checked'),
			backurl: BACK_URL
		},
		beforeSend: function() {

		},
		success: function(json){
			if (json.code == 1) {
				location.href = json.data;
			} else {
				alert(json.data);
				if (10101 == json.code) {
					$('#username').focus();
				}
				if (10102 == json.code) {
					$('#password').focus();
				}
				if (10106 == json.code) {
					$('#captcha').focus();
				}
				if (10108 == json.code) {
					$('#respass').focus();
				}
			}
		},
		error: function(error){
			console.log(error);
		}
	});
}