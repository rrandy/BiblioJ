package biblioj



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LivreController {
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	static scaffold = true
	
	Map panier = ["Livre" : "999"] // nom livre, nb exemplaires sélectionnés
	

	def index() {
		params.max = Math.min(params.max ? params.int('max') : 5, 100)

		def livreList = Livre.createCriteria().list (params) {
				if ( params.titreLivre) {
				ilike("titre", "%${params.titreLivre}%")
				}
				if (params.nomAuteur) {
					auteurs { ilike "nom", "%${params.nomAuteur}%" }
				}
				if (params.typeDocument) {
					typeDocument { ilike "intitule", "%${params.typeDocument}%" }
				}
		
		}
		respond livreList, model:[livreInstanceCount: Livre.count()]
	}

	def show(Livre livreInstance) {
		respond livreInstance
	}

	def create() {
		respond new Livre(params)
	}

	@Transactional
	def save(Livre livreInstance) {
		if (livreInstance == null) {
			notFound()
			return
		}

		if (livreInstance.hasErrors()) {
			respond livreInstance.errors, view:'create'
			return
		}

		livreInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'livre.label', default: 'Livre'),
					livreInstance.id
				])
				redirect livreInstance
			}
			'*' { respond livreInstance, [status: CREATED] }
		}
	}

	def edit(Livre livreInstance) {
		respond livreInstance
	}

	@Transactional
	def update(Livre livreInstance) {
		if (livreInstance == null) {
			notFound()
			return
		}

		if (livreInstance.hasErrors()) {
			respond livreInstance.errors, view:'edit'
			return
		}

		livreInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Livre.label', default: 'Livre'),
					livreInstance.id
				])
				redirect livreInstance
			}
			'*'{ respond livreInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Livre livreInstance) {

		if (livreInstance == null) {
			notFound()
			return
		}

		livreInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Livre.label', default: 'Livre'),
					livreInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'livre.label', default: 'Livre'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
