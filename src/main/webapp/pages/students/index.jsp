<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="container flex-column">
    	<div class="table-heading">
	    	<h2>Studenti</h2>
			<a href="/studenti/new"><button class="btn submit-btn submit-btn-new">Adauga student</button></a>	
    	</div>
        <table class="table shadow-depth">
        	<thead>
	        	<tr>
	        		<th>#</th>
	        		<th>Nume</th>
	        		<th>Prenume</th>
	        		<th>Adresa</th>
	        	</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${ students }" var="student" varStatus="loop">
	        		<tr>
	        			<td class="font-weight-semiBold">${ loop.index + 1 }</td>
	        			<td>${ student.nume }</td>
	        			<td>${ student.prenume }</td>
	        			<td>${ student.adresa }</td>
	        			<td class="action-cell"><a href="/studenti/edit?id=${ student.id }"><button class="btn action-btn edit-btn">Edit</button></a></td>
    					<c:if test='${ auth_user.isAdmin() }'>
		        			<td class="action-cell"><a href="/studenti/delete?id=${ student.id }"><button class="btn action-btn delete-btn">X</button></a></td>
      					</c:if>
	        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    </jsp:body>
</t:layout>