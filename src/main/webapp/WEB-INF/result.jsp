<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>

<html>
<head>
</head>
<body>
<table>
    <thead align="center" >
    <tr>

        <th> id </th>
        <th> name </th>

    </tr>
    </thead>
    <tbody align="center">
    <c:forEach items="${adsArr}" var="ads">
        <tr>

            <td> <c:out value="${ads.id}"/> </td>
            <td> <c:out value="${ads.managerName}"/> </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
