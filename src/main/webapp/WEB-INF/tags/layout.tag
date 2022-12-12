<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="components" tagdir="/WEB-INF/tags/components" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Proiect3</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?ver=<%= System.currentTimeMillis() %>" />
    <script src="${pageContext.request.contextPath}/js/script.js?ver=<%= System.currentTimeMillis() %>" defer></script>
  </head>
  <body>
  	<components:header />
 	 <div class="notification error">
    	<c:out value="${sessionScope.error}" />
		<c:remove var="error" scope="session" />
    </div>
    <div class="notification success">
    	<c:out value="${sessionScope.success}" />
		<c:remove var="success" scope="session" />
    </div>
     <div class="notification error">
    	${ error }
    </div>
    <main id="body">
      <jsp:doBody/>
    </main>	
  </body>
</html>