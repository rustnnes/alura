<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/usuarios" method="post">
  
	<c:if test="${not empty usuario.id}">
		<input type="hidden" name="usuario.id" value="${usuario.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Nome:<br />
		<input type="text" name="usuario.nome" value="${usuario.nome}"/>
	</div>

	<div class="field">
		E-mail:<br />
		<input type="text" name="usuario.email" value="${usuario.email}"/>
	</div>


<div class="actions">
	<button type="submit" id="btnSalvar">Salvar!</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/usuarios">Voltar</a>
