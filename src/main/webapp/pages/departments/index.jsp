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
	    	<h2>Catedre</h2>
			<a href="/catedre/new"><button>Adauga catedra</button></a>	
    	</div>
        <table class="table shadow-depth">
        	<thead>
	        	<tr>
	        		<th>#</th>
	        		<th>Denumire</th>
	        	</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${ departments }" var="department" varStatus="loop">
	        		<tr>
	        			<td class="font-weight-semiBold">${ loop.index + 1 }</td>
	        			<td>${ department.denumire }</td>
	        			<td class="action-cell"><a href="/catedre/edit?id=${ department.id }"><button class="btn action-btn edit-btn">Edit</button></a></td>
	        			<td class="action-cell"><a href="/catedre/delete?id=${ department.id }"><button class="btn action-btn delete-btn">X</button></a></td>
	        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    </jsp:body>
</t:layout>