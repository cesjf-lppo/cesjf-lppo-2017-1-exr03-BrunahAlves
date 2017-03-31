<%-- 
    Document   : editaform-registro
    Created on : 24/03/2017, 21:52:40
    Author     : aluno
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <body>
        <h1>Edita Reclamações</h1>
        <form method="post">
            <div><input type="hidden" name="id" value="${visitante.id}"/>id: ${visitante.id}</div>
            <div><label>Nome: <input type="text" name="nome" value="${visitante.nome}"/></label></div>
            <div><label>Idade: <input type="text" name="idade" value="${visitante.idade}"/></label></div>
            <div><label>Entrada: <input type="datetime" name="entrada" value="${visitante.entrada}"/></label></div>
       
            <div><label>Saida: <input type="datetime" name="saida" value="${visitante.saida}"/></label></div>
            <div><input type="submit"></div>
        </form>
    </body>
    </body>
</html>

