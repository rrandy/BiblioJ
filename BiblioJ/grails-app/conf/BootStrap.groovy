import biblioj.Auteur
import biblioj.Livre
import biblioj.Reservation
import biblioj.TypeDocument

class BootStrap {

	def init = { servletContext ->

		try {
			def td = new TypeDocument(intitule:"Livre ado").save(flush: true)
			def r = new Reservation(code: "testCode",dateReservation: "01/01/01"  ).save(flush: true)
			def a = new Auteur(nom: "Dutronc",prenom:"jacques").save(flush: true)
			def l = new Livre(titre:"Hunger games [Texte imprim']", nombreExemplaires: 2,nombreExemplairesDisponibles: 1 , typeDocument : td).save(flush: true)
		} catch (org.springframework.dao.OptimisticLockingFailureException e) {
			e.printStackTrace()
		}
	}
	def destroy = {
	}
}
