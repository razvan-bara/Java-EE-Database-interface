<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="notification error">
    	${ error }
    </div>
    <div class="notification success">
    	<c:out value="${sessionScope.success}" />
		<c:remove var="success" scope="session" />
    </div>
    <div class="container flex-column">
    	<div class="table-heading">
	    	<h2>Profesori</h2>
			<a href="/profesori/new"><button>Adauga profesor</button></a>	
    	</div>
        <table class="table">
        	<thead>
	        	<tr>
	        		<th>#</th>
	        		<th>Nume</th>
	        		<th>Prenume</th>
	        		<th>Adresa</th>
	        	</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${ teachers }" var="teacher" varStatus="loop">
	        		<tr>
	        			<td class="font-weight-semiBold">${ loop.index + 1 }</td>
	        			<td>${ teacher.nume }</td>
	        			<td>${ teacher.prenume }</td>
	        			<td>${ teacher.adresa }</td>
	        			<td class="action-cell"><a href="/profesori/edit?id=${ teacher.id }"><button class="btn action-btn edit-btn">Edit</button></a></td>
	        			<td class="action-cell"><a href="/profesori/delete?id=${ teacher.id }"><button class="btn action-btn delete-btn">X</button></a></td>
	        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    </jsp:body>
</t:layout>