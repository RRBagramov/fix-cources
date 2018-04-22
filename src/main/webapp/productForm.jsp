<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products Store Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Products management</h1>
    <h2>
        <a href="/new">Add new product</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/products">List all products</a>

    </h2>
</div>
<div align="center">
    <c:if test="${product != null}">
    <form action="updateProduct" method="post">
        </c:if>
        <c:if test="${product == null}">
        <form action="add" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${product == null}">
                            Add new product
                        </c:if>
                        <c:if test="${product != null}">
                            Edit product
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${product != null}">
                    <input type="hidden" name="id" value="<c:out value='${product.id}' />"/>
                </c:if>
                <tr>
                    <th>Name:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${product.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Type:</th>
                    <td>
                        <input type="text" name="type" size="45"
                               value="<c:out value='${product.type}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Price:</th>
                    <td>
                        <input type="text" name="price" size="5"
                               value="<c:out value='${product.price}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>