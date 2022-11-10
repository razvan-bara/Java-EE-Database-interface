<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="modal active">
    	<div class="modal-container">
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
    	    
		    <form class="modal-body" action="/catedre" method="POST">
			    <c:if test="${teacher_department.teacher.id == null && teacher_department.department.id == null}">
					<input type="hidden" name="_METHOD" value="PUT">
					<input type="hidden" name="teacher_id" value="${ teacher_department.teacher.id }" />
					<input type="hidden" name="department_id" value="${ teacher_department.department.id }" />
			    </c:if>
	    		<fieldset class="field-group">
	    			<label class="select-label">Nume complet</label>
	   				<select>
	   					<c:if test="${teacher_department.teacher.id != null && teacher_department.department.id != null}">
	   						<option value="${ teacher_department.teacher.id }">
	   							${ teacher_department.teacher.getFullName() }
	   						</option>
	   					</c:if>
	   				</select>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<label class="select-label">Catedra</label>
	   				<select>
	   					<c:if test="${teacher_department.teacher.id != null && teacher_department.department.id != null}">
	   						<option value="${ teacher_department.department.id }">
	   							${ teacher_department.department.denumire }
	   						</option>
	   					</c:if>
	   				</select>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<label class="select-label">Pozitie</label>
	   				<select>
	   					<c:if test="${teacher_department.teacher.id != null && teacher_department.department.id != null}">
	   						<option value="">
	   							${ teacher_department.position }
	   						</option>
	   					</c:if>
	   					<option value="2">2</option>
	   					<option value="3">3</option>
	   					<option value="4">4</option>
	   				</select>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<input type="submit" value="Submit"/>
	    		</fieldset>
	    	</form>
 	    </div>
    </div>
    </jsp:body>
</t:layout>