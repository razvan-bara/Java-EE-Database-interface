<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="container flex-column">
    	<div class="table-heading">
	    	<h2>Utilizatori</h2>
    	</div>
        <table class="table shadow-depth">
        	<thead>
	        	<tr>
	        		<th>#</th>
	        		<th>Nume complet</th>
	        		<th>Email</th>
	        		<th>Rol</th>
	        	</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${ users }" var="user" varStatus="loop">
	        		<tr>
	        			<td class="font-weight-semiBold">${ loop.index + 1 }</td>
	        			<td>${ user.nume_complet }</td>
	        			<td>${ user.email }</td>
	        			<td>${ user.rol }</td>
	        			<td class="action-cell"><a href="/utilizatori/edit?id=${ user.id_utilizator }"><button class="btn action-btn edit-btn">Edit</button></a></td>
    					<c:if test='${ auth_user.rol == "admin" }'>
		        			<td class="action-cell"><a href="/utilizatori/delete?id=${ user.id_utilizator }"><button class="btn action-btn delete-btn">X</button></a></td>
      					</c:if>
	        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    </jsp:body>
</t:layout>