<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %> <!-- Importar JSTL es usado actualmente -->
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
    
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
	<div style="padding: 2% 0 0 2%">
		<form class="row g-3" action="/anonimo/formularioLogin" method="post">
		  <div class="col-md-5">
		    <label for="user" class="form-label">Email</label>
		    <input type="email" class="form-control w-100" id="user" name="username" required>
		  </div>
		  <div class="col-md-12">
		    <label for="password" class="form-label">Password</label>
		    <input type="password" class="form-control w-100" id="password" name="password" required>
		  </div>
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary">Login</button>
		  </div>
		</form>
		
		<div class="mt-3">
	       	<p><a href="/anonimo/loginRegistro?source=login">Registrate</a></p>
	    </div>
    </div>
    <p style="background-color: orange"><c:out value="${mensaje}" /></p>
</body>
</html>