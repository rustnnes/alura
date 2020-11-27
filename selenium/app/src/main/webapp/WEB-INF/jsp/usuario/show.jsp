<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
	<title>Caelum</title>
</head>
<body>
	<p>
		<b>Nome:</b>
		${usuario.nome}
	</p>
	<p>
		<b>E-mail:</b>
		${usuario.email}
	</p>

	<a href="${pageContext.request.contextPath}/usuarios/${usuario.id}/edit">Editar</a>
	<a href="${pageContext.request.contextPath}/usuarios">Voltar</a>
</body>