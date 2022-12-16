<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="container flex-column">
    	<div class="table-heading">
	    	<h2>Pozitia profesorului in cadrul catedrei</h2>
			<a href="/functii/new"><button class="btn submit-btn submit-btn-new" >Adauga relatie profesor-catedra</button></a>	
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
	        			<c:if test='${ auth_user.isAdmin() }'>
	        			<td class="action-cell">
		        			<a href="/functii/delete?teacher_id=${ teacher_department.teacher.id }&department_id=${teacher_department.department.id}">
			        			<button class="btn action-btn delete-btn">X</button>
		        			</a>
	        			</td>
	        			</c:if>
	        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    </jsp:body>
</t:layout>