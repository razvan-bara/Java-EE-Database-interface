<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="notification error">
    	${ error }
    </div>
    <div class="notification error">
    	<c:out value="${sessionScope.error}" />
		<c:remove var="error" scope="session" />
    </div>
    <div class="notification success">
    	<c:out value="${sessionScope.success}" />
		<c:remove var="success" scope="session" />
    </div>
    <div class="container flex-column">
    	<div class="table-heading">
	    	<h2>Proiecte cercetare</h2>
			<a href="/proiecte_cercetare/new"><button class="btn submit-btn submit-btn-new" >Adauga proiect de cercetare</button></a>	
    	</div>
        <table class="table">
        	<thead>
	        	<tr>
	        		<th>#</th>
	        		<th>Titlu proiect</th>
	        		<th>Student</th>
	        		<th>Profesor</th>			        		
	        	</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${ researchProjects }" var="researchProject" varStatus="loop">
	        		<tr>
	        			<td class="font-weight-semiBold">${ loop.index + 1 }</td>
	        			<td>${ researchProject.title }</td>
	        			<td>${ researchProject.student.getFullName() }</td>
	        			<td>${ researchProject.teacher.getFullName() }</td>
	        			<td class="action-cell">
		        			<a href="/proiecte_cercetare/edit?teacher_id=${ researchProject.teacher.id }&student_id=${researchProject.student.id}">
			        			<button class="btn action-btn edit-btn">Edit</button>
		        			</a>
	        			</td>
	        			<td class="action-cell">
		        			<a href="/proiecte_cercetare/delete?teacher_id=${ researchProject.teacher.id }&student_id=${researchProject.student.id}">
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