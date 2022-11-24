<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="modal active">
    	<div class="modal-container basic-radius inverse">
    		<div class="modal-heading">
    	    	
    	    	<c:choose>
				    <c:when test="${teacher_department.teacher.id == null && teacher_department.department.id == null}">
				        <h3 class="">Adauga un profesor la o cadera</h3>
				    </c:when>
				    <c:otherwise>
				        <h3 class="">Editeaza relatia profesor-catedra</h3>
				    </c:otherwise>
				</c:choose>
    	    	
    		</div>
    	    
		    <form class="modal-body" action="/functii" method="POST">
			    <c:if test="${teacher_department.teacher.id != null && teacher_department.department.id != null}">
					<input type="hidden" name="_METHOD" value="PUT">
					<input type="hidden" name="teacher_id" value="${ teacher_department.teacher.id }" />
					<input type="hidden" name="department_id" value="${ teacher_department.department.id }" />
			    </c:if>
	    		<fieldset class="field-group">
	    			<label class="select-label">Nume complet</label>
	   				<select name="teacher">
			    	   <c:choose>
						   <c:when test="${teacher_department.teacher.id != null && teacher_department.department.id != null}">
					    		<option value="${ teacher_department.teacher.id }">
  									${ teacher_department.teacher.getFullName() }
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
	    			<label class="select-label">Catedra</label>
	   				<select name="department">
			    	   <c:choose>
						   <c:when test="${teacher_department.teacher.id != null && teacher_department.department.id != null}">
					    		<option value="${ teacher_department.department.id }">
  									${ teacher_department.department.denumire }
  								</option>
  								<c:forEach items="${ departments }" var="department" varStatus="loop">
									<option value="${ department.id }">${ department.denumire }</option>
				        		</c:forEach>
						   </c:when>
						   <c:when test="${ !departments.isEmpty() }">
								 <c:forEach items="${ departments }" var="department" varStatus="loop">
									<option value="${ department.id }">${ department.denumire }</option>
				        		</c:forEach>
						   </c:when>
					   </c:choose>
	   				</select>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<label class="select-label">Pozitie</label>
	   				<select name="position">
	   					<c:choose>
						   <c:when test="${teacher_department.teacher.id != null && teacher_department.department.id != null}">
					    		<option value="${ teacher_department.position }">
  									${ teacher_department.position }
  								</option>
  								<c:forEach items="${ positions }" var="position" varStatus="loop">
									<option value="${ position }">${ position }</option>
				        		</c:forEach>
						   </c:when>
						   <c:when test="${ !positions.isEmpty() }">
								 <c:forEach items="${ positions }" var="position" varStatus="loop">
									<option value="${ position }">${ position }</option>
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