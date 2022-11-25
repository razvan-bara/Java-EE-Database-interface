<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="modal active">
    	<div class="modal-container basic-radius inverse">
    		<div class="modal-heading">
    	    	
    	    	<c:choose>
				    <c:when test="${researchProject.teacher.id == null && researchProject.student.id == null}">
				        <h3 class="">Adauga un proiect de licenta</h3>
				    </c:when>
				    <c:otherwise>
				        <h3 class="">Editeaza un project de licenta existent</h3>
				    </c:otherwise>
				</c:choose>
    	    	
    		</div>
    	    
		    <form class="modal-body" action="/proiecte_cercetare" method="POST">
			    <c:if test="${researchProject.teacher.id != null && researchProject.student.id != null}">
					<input type="hidden" name="_METHOD" value="PUT">
					<input type="hidden" name="existing_teacher_id" value="${ researchProject.teacher.id }" />
					<input type="hidden" name="existing_student_id" value="${ researchProject.student.id }" />
			    </c:if>
			   	<fieldset class="field-group">
					<input class="normal-color" type="text" name="titlu" placeholder="Titlul proiectului" value="${ researchProject.title }" required/>	
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<label class="select-label">Student</label>
	   				<select name="student">
			    	   <c:choose>
						   <c:when test="${researchProject.teacher.id != null && researchProject.student.id != null}">
					    		<option value="${ researchProject.student.id }">
  									${ researchProject.student.getFullName() }
  								</option>
  								<c:forEach items="${ students }" var="student" varStatus="loop">
									<option value="${ student.id }">${ student.getFullName() }</option>
				        		</c:forEach>
						   </c:when>
						   <c:when test="${ !students.isEmpty() }">
								 <c:forEach items="${ students }" var="student" varStatus="loop">
									<option value="${ student.id }">${ student.getFullName() }</option>
				        		</c:forEach>
						   </c:when>
					   </c:choose>
	   				</select>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<label class="select-label">Profesor</label>
	   				<select name="teacher">
			    	   <c:choose>
						   <c:when test="${researchProject.teacher.id != null && researchProject.student.id != null}">
					    		<option value="${ researchProject.teacher.id }">
  									${ researchProject.teacher.getFullName() }
  								</option>
  								<c:forEach items="${ teachers }" var="teacher" varStatus="loop">
									<option value="${ teacher.id }">${ teacher.getFullName() }</option>
				        		</c:forEach>
						   </c:when>
						   <c:when test="${ !teachers.isEmpty() }">
								 <c:forEach items="${ teachers }" var="teacher" varStatus="loop">
									<option value="${ teacher.id }">${ teacher.getFullName() }</option>
				        		</c:forEach>
						   </c:when>
					   </c:choose>
	   				</select>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<button class="btn submit-btn submit-btn-crud" type="submit">Submit</button>
	    		</fieldset>
	    	</form>
 	    </div>
    </div>
    </jsp:body>
</t:layout>