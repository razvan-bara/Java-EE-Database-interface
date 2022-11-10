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
	    	<h2>Pozitia profesorului in cadrul catedrei</h2>
			<a href="/functii/new"><button>Adauga relatie profesor-catedra</button></a>	
    	</div>
        <table class="table">
        	<thead>
	        	<tr>
	        		<th>#</th>
	        		<th>Nume</th>
	        		<th>Prenume</th>
	        		<th>Denumire Catedra</th>
	        		<th>Pozitie</th>			        		
	        	</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${ teachers_departments }" var="teacher_department" varStatus="loop">
	        		<tr>
	        			<td class="font-weight-semiBold">${ loop.index + 1 }</td>
	        			<td>${ teacher_department.teacher.nume }</td>
	        			<td>${ teacher_department.teacher.prenume }</td>
	        			<td>${ teacher_department.department.denumire }</td>
	        			<td>${ teacher_department.position }</td> 
	        			<td class="action-cell">
		        			<a href="/functii/edit?teacher_id=${ teacher_department.teacher.id }&department_id=${teacher_department.department.id}">
			        			<button class="btn action-btn edit-btn">Edit</button>
		        			</a>
	        			</td>
	        			<td class="action-cell">
		        			<a href="/functii/delete?teacher_id=${ teacher_department.teacher.id }&department_id=${teacher_department.department.id}">
			        			<button class="btn action-btn delete-btn">X</button>
		        			</a>
	        			</td>
	        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    </jsp:body>
</t:layout>