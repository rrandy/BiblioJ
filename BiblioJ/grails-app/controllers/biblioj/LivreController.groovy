package biblioj



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LivreController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Livre.list(params), model:[livreInstanceCount: Livre.count()]
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
                flash.message = message(code: 'default.created.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Livre.label', default: 'Livre'), livreInstance.id])
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
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Livre.label', default: 'Livre'), livreInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
