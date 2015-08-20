<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<ul>
	<c:url var="depAdd" value="/departamento/add"></c:url>
	<li>
		<a href="${depAdd}" title="Departameto">Departamento</a>
	</li>
	<c:url var="cargoAdd" value="/cargo/add"></c:url>
	<li>
		<a href="${cargoAdd}" title="Cargo">Cargo</a>
	</li>
	<c:url var="funcAdd" value="/funcionario/add"></c:url>
	<li>
		<a href="${funcAdd}" title="Funcionários">Funcionarios</a>
	</li>
</ul>