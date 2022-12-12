<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="modal active">
    	<div class="modal-container basic-radius inverse">
    		<div class="modal-heading">
    	    	
    	    	<c:choose>
				    <c:when test="${user.id_utilizator == null}">
				        <h3 class="">Adauga un utilizator nou</h3>
				    </c:when>
				    <c:otherwise>
				        <h3 class="">Editeaza utilizator</h3>
				    </c:otherwise>
				</c:choose>
    	    	
    		</div>
    	    
		    <form class="modal-body" action="/utilizatori" method="POST">
			    <c:if test="${user.id_utilizator != null}">
					<input type="hidden" name="_METHOD" value="PUT">
					<input type="hidden" name="id" value="${ user.id_utilizator }" />
			    </c:if>
	    		<fieldset class="field-group">
	   				<input class="normal-color" type="text" name="full_name" placeholder="Nume complet" value="${ user.nume_complet }" pattern="[A-Za-z ]+" required/>
	    		</fieldset>
	    		<fieldset class="field-group">
	   				<input class="normal-color" type="email" name="email" placeholder="email" value="${ user.email }" required />
	    		</fieldset>
	    		<fieldset class="field-group">
					<label class="select-label">Este admin?
				      <c:choose>				         
				         <c:when test = "${ user.isAdmin() }">
				            <input name="is_admin" type="checkbox" checked="true"/>
				         </c:when>
				         <c:otherwise>
				            <input name="is_admin" type="checkbox" />
				         </c:otherwise>
				      </c:choose>
					</label>
					<label class="select-label">Cont activat?
						<input name="is_active" type="checkbox" />
					</label>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<button class="btn submit-btn submit-btn-crud" type="submit">Submit</button>
	    		</fieldset>
	    	</form>
 	    </div>
    </div>
    </jsp:body>
</t:layout>