<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="unisa.it.formulaonline.model.entity.Categoria" %>
<%@ page import="unisa.it.formulaonline.model.entity.Discussione" %>
<%@ page import="unisa.it.formulaonline.model.entity.Lettore" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.css">
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="resources/css/formulaonline.css">
    <title>Formula Online</title>
</head>

<body>
<%@include file="/WEB-INF/header.jsp" %>
<%
    Lettore l = (Lettore) session.getAttribute("lettore");
    if(l==null || !l.getModeratore()){
        String redirectURL = ".";
        response.sendRedirect(redirectURL);
    }
%>
<div class="container main h-100 pt-4 pb-4">
    <div class="row d-flex justify-content-center h-100 px-5">
        <div class="container bg-white py-4">
            <p class="h3 text-center">
                Crea Categoria
            </p>
            <form action="salvaCategoria" method="post" class="container w-100 m-2">
                <input class="form-control w-50 mb-3" maxlength="50"
                    placeholder="Nome categoria" id="nome" name="nome"
                    required>
                <textarea class="form-control mb-3" placeholder="descrizione"
                    maxlength="300" name="descrizione"></textarea>
                    <label class="ms-3">Categoria</label>
                <select class="form-select mb-3" id="categoriaInput" name="categoria">
                    <option value="0" selected>Nessuna categoria</option>
                    <c:forEach items="${requestScope.categorie}" var="categoria">
                        <option value="${categoria.idCategoria}">${categoria.nome}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-primary">Crea</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>