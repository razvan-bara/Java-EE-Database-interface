<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="modal active">
    	<div class="modal-container basic-radius inverse">
    		<div class="modal-heading">
    	    	
    	    	<c:choose>
				    <c:when test="${teacher.id == null}">
				        <h3 class="">Adauga un profesor nou</h3>
				    </c:when>
				    <c:otherwise>
				        <h3 class="">Editeaza profesor</h3>
				    </c:otherwise>
				</c:choose>
    	    	
    		</div>
    	    
		    <form class="modal-body" action="/profesori" method="POST">
			    <c:if test="${teacher.id != null}">
					<input type="hidden" name="_METHOD" value="PUT">
					<input type="hidden" name="id" value="${ teacher.id }" />
			    </c:if>
	    		<fieldset class="field-group">
	   				<input class="normal-color" type="text" name="nume" placeholder="Nume" value="${ teacher.nume }" pattern="[A-Za-z]+" required/>
	    		</fieldset>
	    		<fieldset class="field-group">
	   				<input  class="normal-color" type="text" name="prenume" placeholder="Prenume" value="${ teacher.prenume }" pattern="[A-Za-z]+" required />
	    		</fieldset>
	 			<fieldset class="field-group">
	   				<input class="normal-color" type="text" name="adresa" placeholder="Adresa" value="${ teacher.adresa }" pattern="[A-Za-z]+" required />
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<button class="btn submit-btn submit-btn-crud" type="submit">Submit</button>
	    		</fieldset>
	    	</form>
 	    </div>
    </div>
    </jsp:body>
</t:layout>