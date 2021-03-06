<%-- 
    Document   : lista-registros
    Created on : 20/03/2017, 00:12:24
    Author     : Bruna Alves
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista registros</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Idade</th>
                <th>Data de entrada</th>
                <th>Data de saída</th>
            </tr>
            <c:forEach var="visitante" items="${visitantes}">
                <tr>
                    <td><a href="edita.html?id=${visitante.id}">${visitante.id}</a></td>
                    <td>${visitante.nome}</td>
                    <td>${visitante.idade}</td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${visitante.entrada}" /></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${visitante.saida}" /></td>
                    <td><a href="exclui.html?id=${visitante.id}">X</a></td>
                    <td><a href="saida.html?id=${visitante.id}">Saída</a></td>
                    <td><a href="editaform.html?id=${visitante.id}">E</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
