<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de produtos</title>
</head>
<body>
    <!-- <form method="post" action="/casadocodigo/produtos"> -->
      <form:form action="/casadocodigo/produtos" method="post" commandName="products" enctype="multipart/form-data">
        <div>
            <label for="title">Titulo</label>
            <form:input path="title"/>
            <form:errors path="title"></form:errors>
        </div>
        
        <div>
            <label for="description">Descricao</label>
			<form:textarea path="description" rows="10" cols="20"/>
            <form:errors path="description"></form:errors>
        </div>
        
        <div>
        	<label for="releaseDate">Data de Lancamento</label>
			<form:input path="releaseDate" type="date"/>
        	<form:errors path="releaseDate"></form:errors>
        </div>
        
        <div>
        	<label for="summary">Sumario do livro</label>
        	<input type="file" name="summary">
        	<form:errors path="summaryPath"></form:errors>
        </div>
        
        <c:forEach items="${types}" var="bookType" varStatus="status">
        	<div>
        		<label for="price_${bookType}">${bookType}</label>
        		<input type="text" name="prices[${status.index}].value" id="price_${bookType}">
        		<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}">
        	</div>
        </c:forEach>
        
        <div>
            <input type="submit" value="Enviar" />
        </div>
      </form:form>
    <!-- </form> -->
</body>
</html>