<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
	<title>Caelum</title>
</head>
<body>
	<h1>Todos leilões</h1>

	<table>
		<tr>
			<th>Nome</th>
			<th>Data de abertura</th>
			<th>Valor inicial</th>
			<th>Usuario</th>
			<th>Usado?</th>
			<th></th>
		</tr>

		<c:forEach items="${leilaoList}" var="leilao">
			<tr>
				<td>${leilao.nome}</td>
				<td><fmt:formatDate value="${leilao.dataAbertura.time}" pattern="dd/MM/yyyy" /></td>
				<td>${leilao.valorInicial}</td>
				<td>${leilao.usuario.nome}</td>
				<td>${leilao.usado?'Sim':'Não'}</td>
				<td><a href="${pageContext.request.contextPath}/leiloes/${leilao.id}">exibir</a></td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/leiloes/new">Novo Leilão</a> 
</body>