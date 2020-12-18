<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style type="text/css">
        body {             background-color: lightblue;        }
    </style>
</head>
<body>
<div id="login">

    <form method="post" action="/updateDeveloper">

        <fieldset class="clearfix">
            <h1>Hi ${developer.name}! You are login. Hear you can change your data!</h1>

            <p><input type="text"  name="login" value="${developer.account.login}" required></p>
            <p><input type="text"  name="password" value="${developer.account.password}" required></p>

            <p><input type="text"  name="name" value="${developer.name}" required></p>


            <p>Your Skills: &#8195;
                <c:forEach items="${developer.skills}" var="skillss">
                    <i> ${skillss.name} &#8195;</i>
                </c:forEach>
            </p>
            <div id="skills">
                <p>Select a skill from the list or add a missing one</p>
                    <c:forEach items="${skills}" var="skills">
                        <input type="checkbox" name="skills" value="${skills.name}"/>${skills.name}
                    </c:forEach>
                &#8195;
                <input type="text" name="newSkill" placeholder="Enter new Skill">
            </div>

            <p><input formaction="/updateDeveloper" formmethod="post" type="submit" value="Enter"></p>
        </fieldset>
        <p><input formaction="/deleteDeveloper" formmethod="post" type="submit" value="Delete Your Account"> </p>
    </form>

</div>
</body>
</html>