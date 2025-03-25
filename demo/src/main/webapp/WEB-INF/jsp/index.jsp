<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %> <!-- Importar JSTL es usado actualmente -->
    
<!-- <%//String texto = (String) request.getAttribute("texto");%>  Forma antigua habia que recoger los datos
																   desde request y almacenarlo en una variable -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><c:out value="${texto}" /></h2> <!-- Etiqueta JSTL recogemos directamente los datos del Model -->
</body>
</html>