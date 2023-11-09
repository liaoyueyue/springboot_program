// 导航栏初始化
$(document).ready(function () {
  $(".nav-wrapper").load("/common/navigation.html");
});

function logout() {
  if (confirm("确认注销？")) {
    $.ajax({
      url: "/user/logout",
      type: "post",
      data: {},
      success: function (result) {
        if (result != null && result.code == 200 && result.data == 1) {
          location.href = "/login.html";
        } else {
          alert("注销失败");
        }
      },
    });
  }
}
