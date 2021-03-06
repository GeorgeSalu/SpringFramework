<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Funcionarios</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
<script type="text/javascript" src="<c:url value="/js/function.js" />"></script>

</head>
<body>

	<c:import url="menu.jsp"></c:import>

	<fieldset class="master">
		<c:url var="save" value="/funcionario/save"></c:url>
		<form:form modelAttribute="funcionario" action="${save}" method="post" >
			
			<form:errors cssClass="errorblock" path="*" element="div" />
			
			<form:hidden path="idFuncionario"/>
			<fieldset class="grupo">
				<legend>Funcionario</legend>
				<div class="campo">
					<form:label path="nome">Nome</form:label><br/>
					<form:input path="nome" type="text" size="40" id="nome"></form:input>
				</div>
				<div class="campo">
					<form:label path="salario">Salario</form:label><br/>
					<form:input path="salario" type="text" size="20"></form:input>
				</div>
				<div class="campo">
					<form:label path="dataEntrada">Data de Entrada</form:label><br/>
					<form:input path="dataEntrada" type="date"></form:input>
				</div>
				<div class="campo">
					<form:label path="dataSaida">Data de Saida</form:label><br/>
					<form:input path="dataSaida" type="date"></form:input>
				</div>
				<input type="button" value="Localizar" onclick="localizarPorNome();">
				<fieldset class="grupo">
					<legend>Cargo</legend>
					<div class="campo">
						<form:label path="cargo">Cargo</form:label><br/>
						<form:select id="cargo" path="cargo" >
							<form:option value="" label="---- Select ----"></form:option>
							<form:options items="${cargos}" 
											itemValue="idCargo" 
											itemLabel="cargo"/>
						</form:select>
						<input type="button" onclick="localizarPorCargo();" value="Localizar">
					</div>
				</fieldset>
				
				<fieldset>
					<form:hidden path="endereco.idEndereco"/>
					<legend>Endereço</legend>
					<div class="campo">
						<form:label path="endereco.logradouro">Logradouro</form:label>
						<form:input path="endereco.logradouro" type="text" size="30"/>
						<form:errors cssClass="error" path="endereco.logradouro" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.numero">Numero</form:label>
						<form:input path="endereco.numero" type="text" size="30"/>
						<form:errors cssClass="error" path="endereco.numero" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.complemento">Complemento</form:label>
						<form:input path="endereco.complemento" type="text" size="30"/>
						<div></div>
					</div>
					<div class="campo">
						<form:label path="endereco.bairro">Bairro</form:label>
						<form:input path="endereco.bairro" type="text" size="30"/>
						<form:errors cssClass="error" path="endereco.bairro" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.cidade">Cidade</form:label>
						<form:input path="endereco.cidade" type="text" size="30"/>
						<form:errors cssClass="error" path="endereco.cidade" element="div" />
					</div>
					<div class="campo">
						<form:label path="endereco.estado">Estado</form:label>
						<form:input path="endereco.estado" type="text" size="30"/>
						<form:errors cssClass="error" path="endereco.estado" element="div" />
					</div>
				</fieldset>
				
				<br/>
				<div>
					<input value="salvar" type="submit">
					<input value="Limpa" type="reset">
				</div>
			</fieldset>
		</form:form>
	</fieldset>
	
	<fieldset class="master">
		<legend>Funcionarios</legend>
		<table style="width: 960px;">
			<tr>
				<th>Codigo</th>
				<th>Nome</th>
				<th>Salario</th>
				<th>Data de Entrada</th>
				<th>Data de Saida</th>
				<th>Departamento</th>
				<th>Ação</th>
			</tr>
			<c:forEach var="f" items="${funcionarios}" varStatus="i">
			<tr>
				<th>${f.idFuncionario }</th>
				<th>${f.nome }</th>
				<th>
					<fmt:formatNumber value="${f.salario }" currencySymbol="R$" 
							maxFractionDigits="2" type="currency"/>
				</th>
				<th>
					<fmt:parseDate var="dtEntrada" value="${f.dataEntrada }" 
							pattern="yyyy-MM-dd" />
					<fmt:formatDate value="${dtEntrada }" dateStyle="short"/>
				</th>
				<th>
					<fmt:parseDate var="dtSaida" value="${f.dataSaida }" 
							pattern="yyyy-MM-dd" />
					<fmt:formatDate value="${dtSaida }"/>
				</th>
				<th>${f.cargo.cargo }</th>
				<th>
					
					<c:url var="update" value="/funcionario/update/${f.idFuncionario}"></c:url>
					<a href="${update}" title="Ver/Editar">&#9445;</a>
					|
					<c:url var="delete" value="/funcionario/delete/${f.idFuncionario}"></c:url>
					<a href="${delete}" title="Delete">&#9447;</a>
				</th>
			</tr>
			</c:forEach>
		</table>
	</fieldset>
</body>
</html>