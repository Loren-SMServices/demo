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
	<h2>Pagina principal</h2>
	
	<table class="table table-bordered table-striped table-hover" style="text-align: center; margin: 2%; width:96%">
  		<thead>
  			<tr>
  				<th scope="col">#</th>
  				<th scope="col">Nombre</th>
  				<th scope="col">Apellido</th>
  				<th scope="col">Imagen</th>
  			</tr>
  		</thead>
		<tbody>
        <c:forEach var="usuario" items="${lista}">
            <tr>
                <td>${usuario.id}</td>
                <td>${usuario.firstName}</td>
                <td>${usuario.lastName}</td>
                <td><img src="${usuario.image}" style="width: 3%"></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

	</table>
	
<!-- 	<div style="padding: 2% 0 0 2%"> -->
<!-- 	    Muestra los datos obtenidos de la API -->
<%-- 	    <p><c:out value="${apiData}"/></p> --%>
<!-- 	</div> -->
	<form action="/logout" method="post">
	    <button type="submit" class="btn btn-danger">Cerrar sesión</button>
	</form>
</body>
</html>

	
    
</body>
</html>