
<%@page import="biblioj.TypeDocument"%>
<%@ page import="biblioj.Livre"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'livre.label', default: 'Livre')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-livre" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="list-livre" class="content scaffold-list" role="main">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		<fieldset class="form">
			<g:form action="index" method="GET">
				<label for="typeDocument">Type de document :</label>

				<g:select name="typeDocument"
					from="${TypeDocument.list()*.intitule}"
					value="${params.typeDocument}" />

				<%--        <div class="fieldcontain">--%>
				<label for="titreLivre">Titre du livre :</label>
				<g:textField name="titreLivre" value="${params.titreLivre}" />

				<label for="nomAuteur">Nom de l'auteur :</label>
				<g:textField name="nomAuteur" value="${params.nomAuteur}" />

				<g:actionSubmit value="Rechercher" action="index" />

				<%--        </div>--%>
			</g:form>
		</fieldset>



		<table>
			<thead>
				<tr>

					<g:sortableColumn property="titre"
						title="${message(code: 'livre.titre.label', default: 'Titre')}" />

					<th><g:message code="livre.auteurs.label" default="Auteurs" /></th>

					<th><g:message code="livre.typeDocument.label"
							default="Type Document" /></th>

					<g:sortableColumn property="nombreExemplairesDisponibles"
						title="${message(code: 'livre.nombreExemplairesDisponibles.label', default: 'Nb Exemp Dispos')}" />

					<%--					<g:sortableColumn property="nombreExemplaires"--%>
					<%--						title="${message(code: 'livre.nombreExemplaires.label', default: 'Nb Exemp')}" />--%>

				</tr>
			</thead>
			<tbody>
				<g:each in="${livreInstanceList}" status="i" var="livreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${livreInstance.id}">
								${fieldValue(bean: livreInstance, field: "titre")}
							</g:link></td>

						<td>
							${fieldValue(bean: livreInstance.auteurs, field: "nom")}
						</td>

						<td>
							${fieldValue(bean: livreInstance.typeDocument, field: "intitule")}
						</td>

						<td>
							${fieldValue(bean: livreInstance, field: "nombreExemplairesDisponibles")}
						</td>

						<%--						<td>--%>
						<%--							${fieldValue(bean: livreInstance, field: "nombreExemplaires")}--%>
						<%--						</td>--%>
						<td>

							<fieldset class="form">
								<g:form action="index" method="GET">
									<g:select name="${'test'+i}"
										value="${fieldValue(bean: livreInstance, field: 'titre')}"
										noSelection="${['null':'Select a number...']}" from="${1..6}" />

									<g:actionSubmit value="Ajouter" action="index" />

								</g:form>
							</fieldset>
						</td>


					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${livreInstanceCount ?: 0}" />
		</div>
	</div>
</body>
</html>
