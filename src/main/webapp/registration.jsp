<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style type="text/css">
        body {            background-color: tomato;        }
    </style>
</head>
<body>
<div id="login">

    <form method="post" action="/saveDeveloper">

        <fieldset class="clearfix">
            <h1>Register your data information. All fields must be not empty!</h1>

            <p><input type="login"  name="login" placeholder="Your Login" required></p>
            <p><input type="password"  name="password" placeholder="Your Password" required></p>
            <p><input type="text"  name="name" placeholder="Your Name" required></p>


            <p>Your Skills: &#8195;
                <c:forEach items="${requestScope.developer.skills}" var="skillss">
                    <i> ${skillss.name} &#8195;</i>
                </c:forEach>
            </p>
            <div id="skills">
                <p>Select a skill from the list or add a missing one</p>
                <c:forEach items="${skills}" var="skills">
                    <input type="checkbox" name="skills" value="${skills.name}"/>${skills.name}
                </c:forEach>
                &#8195;
                <input type="text" name="newSkill" placeholder="New Skill">
            </div>

            <p><input formaction="/saveDeveloper" formmethod="post" type="submit" value="Enter"></p>
        </fieldset>
    </form>

</div>


</body>
</html>