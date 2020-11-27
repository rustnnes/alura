<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
	<title>Caelum</title>
</head>
<body>
	<p>
		<b>Nome:</b>
		${leilao.nome}
	</p>
	<p>
		<b>Valor inicial:</b>
		${leilao.valorInicial}
	</p>
	<p>
		<b>Usuário:</b>
		${leilao.usuario.nome}
	</p>
	<p>
		<b>Data de abertura:</b>
		<fmt:formatDate value="${leilao.dataAbertura.time}" pattern="dd/MM/yyyy" />
	</p>
	<p>
		<b>Usado?</b>
		${leilao.usado ? 'Sim' : 'Não'}
	</p>
	

	<div>
		<table id="lancesDados">
		
			<tr bgcolor="#efefef">
				<td>Data</td>
				<td>Usuário</td>
				<td>Valor</td>
			</tr>
			
			<c:forEach items="${leilao.lances}" var="lance">
				<tr>
					<td><fmt:formatDate value="${lance.data.time}" pattern="dd/MM/yyyy" /></td>
					<td>${lance.usuario.nome}</td>
					<td>${lance.valor}</td>
				</tr>
			</c:forEach>
		</table>
	</div>	
	
	<div>
		<form id="frmLance">
		Novo Lance:
		
			<select name="lance.usuario.id">
				<c:forEach items="${usuarios}" var="usuario">
					<option value="${usuario.id}">${usuario.nome}</option>
				</c:forEach>
			</select>
			
			<input type="text" name="lance.valor" />
			<input type="hidden" name="lance.leilao.id" value="${leilao.id}" />
			
			<input type="button" id="btnDarLance" value="Dar lance!" />
		</form>
	</div>
	
	<a href="${pageContext.request.contextPath}/leiloes">Voltar</a>
	
<script type="text/javascript">

	$('#btnDarLance').click(function() {
		$.post( '${pageContext.request.contextPath}/_lances', $('#frmLance').serialize(), function(data) {
			//alert("Lance adicionado com sucesso!");
			$('#lancesDados').append(data);
		});
		
	});

</script>
</body>