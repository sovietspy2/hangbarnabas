package controllers;

/**
 * Parameter: jatekosId 
 * Töröli a játékost!
 * Ugyan ugy csinalam mint oran
 */
import models.Jatekos;
import models.Klub;
import play.mvc.Controller;

public class JatekosDeleteController extends Controller{
	public static void deleteJatekos(Long jatekosId){
		String errorMessage = null;
		
		Klub owningKlub = null;
		if (jatekosId != null){
			Jatekos jatekos = Jatekos.findById(jatekosId);
			if (jatekos != null){
				owningKlub = jatekos.owningKlub;
				jatekos.delete();
			} else {
				errorMessage = "A törlendőjátékos nem létezik :(!";
			}
		} else {
			errorMessage = "ures azonosito!";
		}
		
		if (errorMessage != null){
			flash.put("errorMessage", errorMessage);
		}
		
		if (owningKlub == null){
			KlubController.Kezdolap();
		} else {
			KlubController.klub(owningKlub.klubId);
		}
	}
}
