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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<!--  EJEMPLO COMO IMPORTAR CSS <link href="/css/style.css" rel="stylesheet" type="text/css">  -->
<!--  EJEMPLO COMO IMPORTAR UN JS <script src="/js/script.js"></script>  -->

<!-- // 		IMPORTANTE, ESTO SOLO ES UN EJEMPLO ESTILOS Y JS SIEMPRE IRAN EN RESOURCE STATIC POR CONVENCION -->

<!-- 		// 		src/main/resources/static/ -->
<!-- 		// 	├── css/ -->
<!-- 		// 	│   └── style.css -->
<!-- 		// 	├── js/ -->
<!-- 		// 	│   └── script.js -->
<!-- 		// 	└── images/ -->
<!-- 		// 	    └── logo.png -->

<!-- //		PERO ESTO SE PUEDE ALTERAR EN APPLICATION.PROPERTIES AÑADIENDO spring.web.resources.static-locations=classpath:/custom-path/ -->

			<!-- JAVASCRIPT -->
<script>

       // Manejo del clic en las filas para llenar la modal
       $(document).on("click", ".clickable-row", function () {
           const id = $(this).data("id");
           const firstName = $(this).data("firstname");
           const lastName = $(this).data("lastname");
           const image = $(this).data("image");

           // Rellenar el formulario dentro del modal
           $("#modal-id").val(id);
           $("#modal-firstname").val(firstName);
           $("#modal-lastname").val(lastName);
           $("#modal-image").val(image);
       });

       // Enviar los datos editados al controlador vía AJAX
      // Manejar clic en el botón "Guardar Cambios"
		$(document).on("click", "#saveChanges", function () {
		    enviarFormulario();
		});

	// Manejar tecla Enter en los campos del formulario
	$(document).on("keydown", "#editForm input", function (event) {
	    if (event.key === "Enter") {
	        event.preventDefault(); // Evitar envío tradicional del formulario
	        enviarFormulario();
	    }
	});

	// Función común para enviar el formulario
	function enviarFormulario() {
	    console.log("Enviando formulario...");
	    const formData = $("#editForm").serialize();
	
	    $.ajax({
	        url: "/usuario/updateUsuario",
	        type: "POST",
	        data: formData,
	        success: function (response) {
	            alert("Usuario actualizado correctamente.");
	            location.reload();
	        },
	        error: function (error) {
	            alert("Error al actualizar el usuario.");
	            console.error(error);
	        }
	    });

    $("#detalleModal").modal("hide");
}
       
    // Filtrar la tabla en vivo
    document.addEventListener("DOMContentLoaded", function () {
	    document.getElementById("searchInput").addEventListener("keyup", function () {
	        const filter = this.value.toLowerCase(); // Convierte el texto a minúsculas para comparación
	        const rows = document.querySelectorAll("#tableBody tr"); // Obtiene todas las filas de la tabla
	
	        rows.forEach(row => {
	            const cells = row.getElementsByTagName("td");
	            let match = false;
	
	            // Verifica si alguna celda de la fila contiene el texto buscado
	            for (const cell of cells) {
	                if (cell.textContent.toLowerCase().includes(filter)) {
	                    match = true;
	                    break;
	                }
	            }
	
	            // Muestra u oculta la fila según si hay coincidencias
	            row.style.display = match ? "" : "none";
	        });
	    });
    });
    
</script>
<!-- ESTILOS -->
    <style>
	 	tbody tr:nth-child(odd) {
   			background-color: rgba(0, 0, 0, 0.05);
    		transition: background-color 0.3s ease;
  		}
  		tbody tr:hover {
    		background-color: rgba(0, 123, 255, 0.2); /* Resalta con azul claro */
  		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<h2>Pagina principal</h2>
	
	<div class="container mt-3">
   		<input type="text" id="searchInput" class="form-control" placeholder="Buscar en la tabla...">
		</div>
	
	<table class="table table-bordered table-striped table-hover" style="text-align: center; margin: 2%; width:96%">
  		<thead>
  			<tr>
  				<th scope="col">#</th>
  				<th scope="col">Nombre</th>
  				<th scope="col">Apellido</th>
  				<th scope="col">Imagen</th>
  			</tr>
  		</thead>
		<tbody id="tableBody">
        <c:forEach var="usuario" items="${lista}">
            <tr class="clickable-row" data-bs-toggle="modal" data-bs-target="#detalleModal"
                        data-id="${usuario.id}"
                        data-firstname="${usuario.firstName}"
                        data-lastname="${usuario.lastName}"
                        data-image="${usuario.image}">
                <td>${usuario.id}</td>
                <td>${usuario.firstName}</td>
                <td>${usuario.lastName}</td>
                <td><img src="${usuario.image}" style="width: 4%"></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Modal editable  -->
    <div class="modal fade" id="detalleModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Editar Fila</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="editForm">
                        <input type="hidden" id="modal-id" name="id">
                        <div class="mb-3">
                            <label for="modal-firstname" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="modal-firstname" name="firstName">
                        </div>
                        <div class="mb-3">
                            <label for="modal-lastname" class="form-label">Apellido</label>
                            <input type="text" class="form-control" id="modal-lastname" name="lastName">
                        </div>
                        <div class="mb-3">
                            <label for="modal-image" class="form-label">URL Imagen</label>
                            <input type="text" class="form-control" id="modal-image" name="image">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="button" id="saveChanges" class="btn btn-primary">Guardar Cambios</button>
                </div>
            </div>
        </div>
    </div>

	<form action="/logout" method="post">
	    <button type="submit" class="btn btn-danger">Cerrar sesión</button>
	</form>
</body>
</html>
