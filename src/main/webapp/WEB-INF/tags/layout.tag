<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="components" tagdir="/WEB-INF/tags/components" %>

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
    <main id="body">
      <jsp:doBody/>
    </main>	
  </body>
</html>