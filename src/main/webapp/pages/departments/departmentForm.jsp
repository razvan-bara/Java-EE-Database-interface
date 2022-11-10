<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="modal active">
    	<div class="modal-container">
    		<div class="modal-heading">
    	    	
    	    	<c:choose>
				    <c:when test="${department.id == null}">
				        <h3 class="">Adauga o catdra noua</h3>
				    </c:when>
				    <c:otherwise>
				        <h3 class="">Editeaza catedra</h3>
				    </c:otherwise>
				</c:choose>
    	    	
    		</div>
    	    
		    <form class="modal-body" action="/catedre" method="POST">
			    <c:if test="${student.id != null}">
					<input type="hidden" name="_METHOD" value="PUT">
					<input type="hidden" name="id" value="${ department.id }" />
			    </c:if>
	    		<fieldset class="field-group">
	   				<input type="text" name="denumire" placeholder="Denumire" value="${ department.denumire }" pattern="[A-Za-z]+" required/>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<input type="submit" value="Submit"/>
	    		</fieldset>
	    	</form>
 	    </div>
    </div>
    </jsp:body>
</t:layout>