package biblioj

class Reservation {
	String code
	String dateReservation
	
	static hasMany = [
		livres: Livre
	]

	static constraints = {
		code(blank:false)
		dateReservation(blank:false)
	}
}
