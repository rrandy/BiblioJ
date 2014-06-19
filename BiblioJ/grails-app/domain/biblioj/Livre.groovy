package biblioj

class Livre {
	
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	TypeDocument typeDocument
	
	static hasMany = [
		reservations: Reservation,
		auteurs: Auteur
   ]
	
	static belongsTo = [
		Reservation
	]
	
	static constraints = {
		titre(blank:false)
		nombreExemplaires(blank:false)
		nombreExemplairesDisponibles(blank:false)
	}
}
