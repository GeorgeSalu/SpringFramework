<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Departamento</title>
</head>
<body>

	<c:import url="menu.jsp"></c:import>

	<c:url var="save" value="/departamento/save"></c:url>
	<form:form modelAttribute="departamento" action="${save}" method="post" >
		<form:hidden path="idDepartamento"/>
		<fieldset style="width: 300px;margin: 0 auto;">
			<legend>Departamento</legend>
			<div>
				<form:label path="departamento">Departamento</form:label><br/>
				<form:input path="departamento" type="text"></form:input>
			</div>
			<br/>
			<div>
				<input value="salvar" type="submit">
				<input value="Limpa" type="reset">
			</div>
		</fieldset>
	</form:form>
	
	<fieldset style="width: 300px;margin: 0 auto;">
		<legend>Departamentos</legend>
		<table style="width: 300px;">
			<tr>
				<th>Codigo</th>
				<th>Descrição</th>
				<th>Ação</th>
			</tr>
			<c:forEach var="d" items="${departamentos}" varStatus="i">
			<tr>
				<th>${d.idDepartamento }</th>
				<th>${d.departamento }</th>
				<th>
					
					<c:url var="update" value="/departamento/update/${d.idDepartamento}"></c:url>
					<a href="${update}" title="Ver/Editar">&#9445;</a>
					|
					<c:url var="delete" value="/departamento/delete/${d.idDepartamento}"></c:url>
					<a href="${delete}" title="Delete">&#9447;</a>
				</th>
			</tr>
			</c:forEach>
		</table>
	</fieldset>
</body>
</html>