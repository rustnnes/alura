<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tr>
	<td><fmt:formatDate value="${lance.data.time}" pattern="dd/MM/yyyy" /></td>
	<td>${usuario.nome}</td>
	<td>${lance.valor}</td>
</tr>
    