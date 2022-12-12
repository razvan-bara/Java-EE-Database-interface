<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
	    <div class="container">
		    <h2>
		    	Salut <c:out value="${ sessionScope.auth_user.nume_complet }" /> cu rolul <c:out value="${ sessionScope.auth_user.rol }" />
			</h2>
	    </div>
    </jsp:body>
</t:layout>