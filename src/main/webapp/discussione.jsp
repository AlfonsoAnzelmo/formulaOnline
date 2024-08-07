<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="unisa.it.formulaonline.model.entity.Lettore" %>
<%@ page import="unisa.it.formulaonline.model.entity.Discussione" %>
<%@ page import="unisa.it.formulaonline.model.entity.Commento" %>
<%@ page import="unisa.it.formulaonline.model.entity.Categoria" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.js"></script>
    <link rel="stylesheet" href="resources/css/formulaonline.css">
    <title>${requestScope.discussione.titolo} - FormulaOnline</title>
</head>

<body>
<jsp:include page="WEB-INF/header.jsp"/>
<div class="container main h-100 pt-4 pb-4">
    <div class="row d-flex justify-content-center h-100">
        <div class="col-11">
            <div class="d-flex justify-content-between mb-4">
                <p class="h1">${requestScope.discussione.titolo}</p>
                <c:if test="${sessionScope.lettore.moderatore}">

                    <a href="modificaDiscussione?idDiscussione=${requestScope.discussione.idDiscussione}" class="btn btn-secondary"> Modifica discussione</a>

                </c:if>
                <c:if test="${sessionScope.lettore.moderatore}">
                    <a href="eliminaDiscussione?idDiscussione=${requestScope.discussione.idDiscussione}" class="btn btn-secondary"> Elimina discussione</a>

                </c:if>
            </div>





            <ol class="list-group">
                <c:forEach items="${requestScope.commenti}" var="comm">
                    <li class="list-group-item">
                        <div class="container-fluid row" id="${comm.idCommento}">
                            <div class="row">
                                Di: ${comm.autore.nickname}
                                <p>
                                        ${comm.corpo}
                                </p>
                                <small>
                                    Data: ${comm.dataCommento}
                                </small>
                            </div>
                            <div class="align-self-end my-1">
                                <c:choose>
                                    <c:when test="${sessionScope.lettore!=null &&
                                            (sessionScope.lettore.moderatore ||
                                            sessionScope.lettore.idLettore == comm.autore.idLettore)}">
                                        <form class="col" method="post" action="eliminaCommento">
                                            <input type="hidden" name="idCommento" value="${comm.idCommento}">
                                            <button class="btn btn-danger float-start" type="submit">
                                                Elimina commento
                                            </button>
                                        </form>
                                    </c:when>
                                </c:choose>
                                <button type="button" class="btn btn-danger col my-1 float-end" data-bs-toggle="modal"
                                        data-bs-target="#segnalaModal">
                                    Segnala
                                </button>
                                <div class="modal fade" id="segnalaModal" tabindex="1"
                                     aria-labelledby="segnalaModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="segnalaModalLabel">Motivo
                                                    della segnalazione
                                                </h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Annulla"></button>
                                            </div>
                                            <c:if test="${sessionScope.lettore!=null}">
                                                <form action="segnalaCommento" method="post">
                                                    <input type="hidden" value="${requestScope.Discussione.idDiscussione}">
                                                    <div class="modal-body">
                                                        <textarea aria-label="Motivazione" class="form-control"
                                                                  id="motivazione" name="motivazione" required></textarea>
                                                        <input type="hidden" name="idCommento" value="${comm.idCommento}">
                                                        <input type="hidden" name="idDiscussione" value="${requestScope.discussione.idDiscussione}">
                                                    </div>

                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">Annulla
                                                        </button>
                                                        <button type="submit" class="btn btn-danger">
                                                            Segnala
                                                        </button>
                                                    </div>
                                                </form>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ol>
            <div class="d-flex flex-column commenta py-2 px-3 commenta">
                <c:choose>
                    <c:when test="${sessionScope.lettore!=null}">
                        <p class="h6 p-1">Commenta:</p>
                        <form action="commenta" method="post">
                            <input type="hidden" id="idDiscussione" name="idDiscussione"
                                   value="${requestScope.discussione.idDiscussione}">
                            <textarea class="form-control p-2" maxlength="500" placeholder="Scrivi il tuo commento..."
                                      name="corpo" required></textarea>
                            <button class="btn btn-primary float-end mt-2">Commenta</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p class="p-3">
                            <a class="link-primary text-decoration-none" href="login.jsp">Accedi</a>
                            per commentare
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>