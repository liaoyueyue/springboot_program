<%@page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css" />
    <style>
      .demo-login-container {
        width: 320px;
        margin: 21px auto 0;
      }

      .demo-login-other .layui-icon {
        position: relative;
        display: inline-block;
        margin: 0 2px;
        top: 2px;
        font-size: 26px;
      }
    </style>
  </head>
  <body>
    <form class="layui-form">
      <div class="demo-login-container">
        <div class="layui-form-item">
          <div class="layui-input-wrap">
            <div class="layui-input-prefix">
              <i class="layui-icon layui-icon-username"></i>
            </div>
            <input
              type="text"
              name="username"
              value=""
              lay-verify="required"
              placeholder="用户名"
              lay-reqtext="请填写用户名"
              autocomplete="off"
              class="layui-input"
              lay-affix="clear"
            />
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-input-wrap">
            <div class="layui-input-prefix">
              <i class="layui-icon layui-icon-password"></i>
            </div>
            <input
              type="password"
              name="password"
              value=""
              lay-verify="required"
              placeholder="密   码"
              lay-reqtext="请填写密码"
              autocomplete="off"
              class="layui-input"
              lay-affix="eye"
            />
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-row">
            <div class="layui-col-xs7">
              <div class="layui-input-wrap">
                <div class="layui-input-prefix">
                  <i class="layui-icon layui-icon-vercode"></i>
                </div>
                <input
                  type="text"
                  name="captcha"
                  value=""
                  lay-verify="required"
                  placeholder="验证码"
                  lay-reqtext="请填写验证码"
                  autocomplete="off"
                  class="layui-input"
                  lay-affix="clear"
                />
              </div>
            </div>
            <div class="layui-col-xs5">
              <div style="margin-left: 10px">
                <img
                  src="https://www.oschina.net/action/user/captcha"
                  onclick="this.src='https://www.oschina.net/action/user/captcha?t='+ new Date().getTime();"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="layui-form-item">
          <input
            type="checkbox"
            name="remember"
            lay-skin="primary"
            title="记住密码"
          />
          <a href="#forget" style="float: right; margin-top: 7px">忘记密码？</a>
        </div>
        <div class="layui-form-item">
          <button
            class="layui-btn layui-btn-fluid"
            lay-submit
            lay-filter="demo-login"
          >
            登录
          </button>
        </div>
        <div class="layui-form-item demo-login-other">
          <label>社交账号登录</label>
          <span style="padding: 0 21px 0 6px">
            <a href="javascript:;"
              ><i
                class="layui-icon layui-icon-login-qq"
                style="color: #3492ed"
              ></i
            ></a>
            <a href="javascript:;"
              ><i
                class="layui-icon layui-icon-login-wechat"
                style="color: #4daf29"
              ></i
            ></a>
            <a href="javascript:;"
              ><i
                class="layui-icon layui-icon-login-weibo"
                style="color: #cf1900"
              ></i
            ></a>
          </span>
          或 <a href="#reg">注册帐号</a>
        </div>
      </div>
    </form>
    <script src="/static/layui/layui.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
      layui.use(function () {
        // 弹出层组件 layer 是 Layui 最古老的组件，也是使用覆盖面最广泛的代表性组件。
        var layer = layui.layer;
        // layer.msg('Hello World', {icon: 6});
        // 表单组件 form 是包含输入框、选择框、复选框、开关、单选框等表单项组件的集合，主要用于对表单域进行各类动态化渲染和相关的交互操作。
        var form = layui.form;
        // 提交事件
        /**
         * 1. 登录按钮上需要有 lay-submit
         * 2. demo-login 是登入按钮上 lay-filter 属性
         */
        form.on("submit(demo-login)", function (data) {
          var field = data.field; // 获取表单字段值
          // 此处可执行 Ajax 等操作
          $.ajax({
            url: "/login",
            type: "post",
            data: field,
            success: function (result) {
              layer.alert(result);
            },
          });
          return false; // 阻止默认 form 跳转
        });
      });
    </script>
  </body>
</html>
