$(function() {
	$("#email").change(function() {
		$("#img-email").hide();
		$("#btn-email").show();

	});
	$("#btn-email").click(function() {
		var email = $("#email").val();
		if (email == "") {
			return;
		}

		$.ajax({
			url : "/mysite03/api/user/checkemail?email=" + email,
			type : "get",
			data : "",
			dataType : "json",
			success : function(response) {
				if (response.result == "fail") {
					console.error(response.message);
					return;
				}
				// 이메일이 존재하는 경우
				if (response.data == true) {
					alert("이메일이 존재 합니다.다른 이메일을 입력해 주세요");
					$("#email").val("");
					$("#email").focus();
					return;
				}

				$("#img-email").show();
				$("#btn-email").hide();
			},
			error : function(XHR, status, e) {
				 console.error(status+":"+e);
			}
		});
	});
});
