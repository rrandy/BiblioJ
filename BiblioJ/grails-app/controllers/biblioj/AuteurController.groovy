package biblioj



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AuteurController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Auteur.list(params), model:[auteurInstanceCount: Auteur.count()]
    }

    def show(Auteur auteurInstance) {
        respond auteurInstance
    }

    def create() {
        respond new Auteur(params)
    }

    @Transactional
    def save(Auteur auteurInstance) {
        if (auteurInstance == null) {
            notFound()
            return
        }

        if (auteurInstance.hasErrors()) {
            respond auteurInstance.errors, view:'create'
            return
        }

        auteurInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'auteur.label', default: 'Auteur'), auteurInstance.id])
                redirect auteurInstance
            }
            '*' { respond auteurInstance, [status: CREATED] }
        }
    }

    def edit(Auteur auteurInstance) {
        respond auteurInstance
    }

    @Transactional
    def update(Auteur auteurInstance) {
        if (auteurInstance == null) {
            notFound()
            return
        }

        if (auteurInstance.hasErrors()) {
            respond auteurInstance.errors, view:'edit'
            return
        }

        auteurInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Auteur.label', default: 'Auteur'), auteurInstance.id])
                redirect auteurInstance
            }
            '*'{ respond auteurInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Auteur auteurInstance) {

        if (auteurInstance == null) {
            notFound()
            return
        }

        auteurInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Auteur.label', default: 'Auteur'), auteurInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auteur.label', default: 'Auteur'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
