<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cargos</title>
</head>
<body>

	<c:import url="menu.jsp"></c:import>

	<c:url var="save" value="/cargo/save"></c:url>
	<form:form modelAttribute="cargo" action="${save}" method="post" >
		<form:hidden path="idDepartamento"/>
		<fieldset style="width: 400px;margin: 0 auto;">
			<legend>Cargo</legend>
			<div>
				<form:label path="cargo">Cargo</form:label><br/>
				<form:input path="cargo" type="text"></form:input>
			</div>
			<br/>
			<div>
				<form:label path="cargo">Cargo</form:label><br/>
				<form:select path="departamento" required="true">
					<form:option value="" label="---- Select ----"></form:option>
					<form:options items="${departamentos}" 
									itemValue="idDepartamento" 
									itemLabel="departamento"/>
				</form:select>
			</div>
			<br/>
			<div>
				<input value="salvar" type="submit">
				<input value="Limpa" type="reset">
			</div>
		</fieldset>
	</form:form>
	
	<fieldset style="width: 400px;margin: 0 auto;">
		<legend>Cargos</legend>
		<table style="width: 400px;">
			<tr>
				<th>Codigo</th>
				<th>Descrição</th>
				<th>Departamento</th>
				<th>Ação</th>
			</tr>
			<c:forEach var="c" items="${cargos}" varStatus="i">
			<tr>
				<th>${c.idCargo }</th>
				<th>${c.cargo }</th>
				<th>${c.departamento.departamento }</th>
				<th>
					
					<c:url var="update" value="/cargo/update/${c.idCargo}"></c:url>
					<a href="${update}" title="Ver/Editar">&#9445;</a>
					|
					<c:url var="delete" value="/cargo/delete/${c.idcargo}"></c:url>
					<a href="${delete}" title="Delete">&#9447;</a>
				</th>
			</tr>
			</c:forEach>
		</table>
	</fieldset>
</body>
</html>