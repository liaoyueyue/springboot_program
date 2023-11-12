// 留给blog_search.html的变量。后续可能其他页面也需要使用
let searchInfo;

// 导航栏初始化
// window.onload 必须等到页面内包括图片的所有元素加载完毕后才能执行；
// $(document).ready() 是 dom 结构绘制完毕后就执行，不必等到加载完毕。
$(document).ready(function () {
  $(".nav-wrapper").load("/common/navigation.html", function () {
    // 导航栏资源加载完毕后执行
    $.ajax({
      url: "/user/showinfo",
      type: "post",
      data: {},
      success: function (result) {
        if (result != null && result.code == 200) {
          $("#nav-username").text(result.data.username);
          $("#nav-photo").attr("src", result.data.photo);
          $("#nav-logout").show();
        }
      },
    });
    if (searchInfo !== undefined) {
      // 设置搜索框的值
      $("#search-info").val(searchInfo);
    }
  });
});

function searchArticle() {
  let searchInfo = $("#search-info");
  if (searchInfo.val() == "") {
    alert("请输入搜索内容");
    searchInfo.focus();
    return;
  }
  location.href = "blog_search.html?searchInfo=" + searchInfo.val();
}

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
