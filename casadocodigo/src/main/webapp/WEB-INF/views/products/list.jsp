<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	${sucesso}
	<table>
		<tr>
			<td>Titulo</td>
			<td>Descricao</td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>
					<a href="${spring:mvcUrl('PC#show').arg(0,product.id).build()}">
						${product.title}
					</a>
				</td>
				<!-- <td>${product.description}</td>  -->
				<td>
					<c:forEach items="${product.prices}" var="price">
						[${price.value} - ${price.bookType}]
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>