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
	
	<h1>Enviar datos al controlador</h1>
    
    <!-- Formulario para enviar datos -->
    <form action="/procesarFormulario" method="post">
        <label for="dato">Introduce un dato:</label>
        <input type="text" id="dato" name="dato" required />
        <button type="submit">Enviar</button>
    </form>
    
    <!-- Mostrar el dato enviado (si está disponible en el modelo) -->
    <h2>Dato enviado:</h2>
    <p><c:out value="${dato}" /></p> <!-- Mostrar el valor con JSTL -->
	
</body>
</html>