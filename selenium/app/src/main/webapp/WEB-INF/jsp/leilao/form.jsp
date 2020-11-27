<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/leiloes" method="post">
  
	<div class="field">
		Nome:<br />
		<input type="text" name="leilao.nome" />
	</div>
	<div class="field">
		Valor inicial:<br />
		<input type="text" name="leilao.valorInicial" />
	</div>
	<div class="field">
		Usuário:<br />
		<select name="leilao.usuario.id">
			<c:forEach items="${usuarios}" var="u">
			<option value="${u.id}">${u.nome}</option>
			</c:forEach>
		</select>
	</div>
	<div class="field">
		É usado?
		<input type="checkbox" name="leilao.usado" />
	</div>
	
<div class="actions">
	<button type="submit">Salvar!</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/leiloes">Voltar</a>
