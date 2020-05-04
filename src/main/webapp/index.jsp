<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<input id="reflex" value="" placeholder="请输入code">
<input id="reflexNext" value="" placeholder="请输入执行方法code">
<a href="#" onclick="reflexClick()">反射方法测试</a>

<script>
    function reflexClick() {
        var value = document.getElementById("reflex").value;
        var code = document.getElementById("reflexNext").value;
        window.location.href = "/index/?code=" + value + "&nextCode=" + code
    }
</script>
</body>
</html>
