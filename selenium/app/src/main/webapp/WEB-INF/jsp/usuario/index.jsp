<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
	<title>Caelum</title>
</head>
<body>
	<h1>Todos os Usuários</h1>

	<table>
		<tr>
			<th>Nome</th>
			<th>E-mail</th>
			<th></th>
		</tr>

		<c:forEach items="${usuarioList}" var="usuario">
			<tr>
				<td>${usuario.nome}</td>
				<td>${usuario.email}</td>
				<td>
				<a href="${pageContext.request.contextPath}/usuarios/${usuario.id}">exibir</a> | 
				<a href="${pageContext.request.contextPath}/usuarios/${usuario.id}/edit">editar</a>
					<form action="${pageContext.request.contextPath}/usuarios/${usuario.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Tem certeza?')">excluir</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/usuarios/new">Novo Usuário</a> 
</body>