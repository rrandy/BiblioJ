import biblioj.Auteur
import biblioj.Livre
import biblioj.Reservation
import biblioj.TypeDocument

class BootStrap {

	def init = { servletContext ->

		def td = new TypeDocument(intitule:"Livre ado").save()
		def r = new Reservation(code: "testCode",dateReservation: "01/01/01"  ).save()
		def a = new Auteur(nom: "Dutronc",prenom:"jacques").save()
		def l = new Livre(titre:"Hunger games [Texte imprim']", nombreExemplaires: 2,nombreExemplairesDisponibles: 1 ).save()
		
	}
	def destroy = {
	}
}
