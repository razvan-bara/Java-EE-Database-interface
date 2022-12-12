<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<h1 class="main-header-title">
		<a href="/">PROIECT3</a>
	</h1>
	<nav>
		<ul class="main-nav">
			<li><a href="/studenti">Studenti</a></li>
			<li><a href="/profesori">Profesori</a></li>
			<li><a href="/functii">Profesor-catedra</a></li>
			<li><a href="proiecte_cercetare">Proiecte cercetare</a></li>
			<li><a href="/catedre">Catedra</a></li>
			<c:if test = '${auth_user.rol == "admin"}'>
				<li><a href="/utilizatori">Utilizatori</a></li>
      		</c:if>
		</ul>
	</nav>
	<form class="profile-link" action="/logout" method="post">
		<span>
			<input type="submit" value="LOGOUT" /> 
		</span>
	</form>
</header>