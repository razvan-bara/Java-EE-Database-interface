<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="components" tagdir="/WEB-INF/tags/components" %>

<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Proiect3</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?ver=<%= System.currentTimeMillis() %>" />
  </head>
  <body>
  	<components:header />
    <main id="body">
      <jsp:doBody/>
    </main>	
    <components:footer />
  </body>
</html>