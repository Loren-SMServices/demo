<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %> <!-- Importar JSTL es usado actualmente -->
    
<!-- <%//String texto = (String) request.getAttribute("texto");%>  Forma antigua habia que recoger los datos
																   desde request y almacenarlo en una variable -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<style>
  .form-control {
    width: 100%; /* Forzar ancho completo */
    max-width: 300px; /* Opcional: límite de ancho */
  }
</style>
<title>Insert title here</title>
</head>
<body>
	
	<h2><c:out value="${texto}" /></h2> <!-- Etiqueta JSTL recogemos directamente los datos del Model -->
	
	<h1>Enviar datos al controlador</h1>
	
	<form class="row g-3" action="/procesarFormulario" method="post">
	  <div class="col-md-5">
	    <label for="user" class="form-label">Email</label>
	    <input type="email" class="form-control w-100" id="user" name="user" required>
	  </div>
	  <div class="col-md-12">
	    <label for="password" class="form-label">Password</label>
	    <input type="password" class="form-control w-100" id="password" name="password" required>
	  </div>
	  <div class="col-12">
	    <button type="submit" class="btn btn-primary">Sign in</button>
	  </div>
	</form>
    
    <!-- Formulario para enviar datos -->
<!--     <form action="/procesarFormulario" method="post"> -->
<!--         <label for="dato">Introduce un dato:</label> -->
<!--         <input type="text" id="dato" name="dato" required /> -->
<!--         <button type="submit">Enviar</button> -->
<!--     </form> -->
    
    <!-- Mostrar el dato enviado (si está disponible en el modelo) -->
    <h2>Usuario enviado:</h2>
    <p><c:out value="${user}" /></p> <!-- Mostrar el valor con JSTL -->
    <p><c:out value="${password}" /></p>
	
</body>
</html>