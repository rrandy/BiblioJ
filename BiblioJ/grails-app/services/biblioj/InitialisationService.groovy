package biblioj

import grails.transaction.Transactional

@Transactional
class InitialisationService {
	def initialiseDonneesReferenciel() {
		def livre = new Livre(titre:"UnLivre", nombreExemplaires : 2, nombreExemplairesDisponibles:1)
		livre.save()
	}
    def serviceMethod() {

    }
}
