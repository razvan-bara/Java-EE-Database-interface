<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
    <div class="container flex-column">
    	<div class="table-heading">
	    	<h2>Studenti</h2>
			<button onClick="openModal()">Add student</button>    	
    	</div>
        <table class="table">
        	<thead>
	        	<tr>
	        		<th>#</th>
	        		<th>Nume</th>
	        		<th>Prenume</th>
	        		<th>Adresa</th>
	        	</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${ students }" var="student" varStatus="loop">
	        		<tr>
	        			<td>${ loop.index + 1 }</td>
	        			<td>${ student.nume }</td>
	        			<td>${ student.prenume }</td>
	        			<td>${ student.adresa }</td>
	        			<td>Edit</td>
	        			<td>X</td>
	        		</tr>
        		</c:forEach>
        	</tbody>
        </table>
    </div>
    <div class="modal">
    	<div class="modal-container">
    		<div class="modal-heading">
    	    	<h3 class="">Add new student</h3>
	    		<button onClick="closeModal()">X</button>
    		</div>
	    	<form class="modal-body" action="" method="POST">
	    		<fieldset class="field-group">
	   				<input type="text" name="nume" placeholder="Nume"/>
	    		</fieldset>
	    		<fieldset class="field-group">
	   				<input type="text" name="prenume" placeholder="Prenume"/>
	    		</fieldset>
	 			<fieldset class="field-group">
	   				<input type="text" name="adresa" placeholder="Adresa"/>
	    		</fieldset>
	    		<fieldset class="field-group">
	    			<input type="submit" placeholder="Inregistreaza student"/>
	    		</fieldset>
	    	</form>
 	    </div>
    </div>
    </jsp:body>
</t:layout>