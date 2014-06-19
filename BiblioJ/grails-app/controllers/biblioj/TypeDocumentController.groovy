package biblioj



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TypeDocumentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TypeDocument.list(params), model:[typeDocumentInstanceCount: TypeDocument.count()]
    }

    def show(TypeDocument typeDocumentInstance) {
        respond typeDocumentInstance
    }

    def create() {
        respond new TypeDocument(params)
    }

    @Transactional
    def save(TypeDocument typeDocumentInstance) {
        if (typeDocumentInstance == null) {
            notFound()
            return
        }

        if (typeDocumentInstance.hasErrors()) {
            respond typeDocumentInstance.errors, view:'create'
            return
        }

        typeDocumentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'typeDocument.label', default: 'TypeDocument'), typeDocumentInstance.id])
                redirect typeDocumentInstance
            }
            '*' { respond typeDocumentInstance, [status: CREATED] }
        }
    }

    def edit(TypeDocument typeDocumentInstance) {
        respond typeDocumentInstance
    }

    @Transactional
    def update(TypeDocument typeDocumentInstance) {
        if (typeDocumentInstance == null) {
            notFound()
            return
        }

        if (typeDocumentInstance.hasErrors()) {
            respond typeDocumentInstance.errors, view:'edit'
            return
        }

        typeDocumentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TypeDocument.label', default: 'TypeDocument'), typeDocumentInstance.id])
                redirect typeDocumentInstance
            }
            '*'{ respond typeDocumentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TypeDocument typeDocumentInstance) {

        if (typeDocumentInstance == null) {
            notFound()
            return
        }

        typeDocumentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TypeDocument.label', default: 'TypeDocument'), typeDocumentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'typeDocument.label', default: 'TypeDocument'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
