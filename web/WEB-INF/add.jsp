<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/7
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form>
    <fieldset>
        <legend>增加员工</legend>
        <label>编号</label><input type="text" id="id1" value=""/><br/>
        <label>姓名</label><input type="text" id="name" value=""/><br/>
        <label>年龄</label><input type="text" id="age" value=""/><br/>
        <label>工作</label><input type="text" id="job" value=""/><br/>
        <label>工资</label><input type="text" id="salary" value=""/><br/>
        <input type="button" value="保存" id="save">
    </fieldset>
</form>

<script type="text/javascript" src="../plugins/jquery-1.7.2.js"></script>
<script type="text/javascript">
    $("#save").click(function () {
        $.ajax({
            type: "post",
            data: {
                id: $("#id1").val(),
                name: $("#name").val(),
                age: $("#age").val(),
                job: $("#job").val(),
                salary: $("#salary").val()
            },
            url: "save.do",
            success:function (data) {
                $("#page-wrap").html(data);
            }
        });
    });

</script>