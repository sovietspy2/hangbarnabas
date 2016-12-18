package controllers;

import java.util.List;

import models.Jatekos;
import models.Klub;

import play.data.validation.Validation;
import util.ValidationUtil;
/**
 * Ezzel nagyon sokat szenvedtem amikor mar azt hittem, hogy kesz vagyok.
 * Paraméterekként beadjuk amiket le kell tesztelni és visszakapunk egy listát a hibákról.
 * @author Barnabas mester
 *
 */
public class JatekosCreationControllerValidator {

	public boolean isValidRequest(Validation validation, Long klubId, String engedelyszam, String fizetes, String szerzodesKezdete, String szerzodesVege){	
		
		try  {
				List<Jatekos> jatekosok = Jatekos.findAll();
				for (Jatekos jatekos : jatekosok){
					if (jatekos.engedelyszam == Integer.parseInt(engedelyszam)) validation.addError("engedelyszam", "Ez mar foglalt!"); 
				}
		} catch (Exception ex) {
			validation.addError("engedelyszam", "Szamnak kell lennie az engedelyszamnak!"); 
		}
		
		if (!ValidationUtil.isInteger(fizetes)){
			validation.addError("fizetes", "Csak egesz szam lehet!"); 
		}

		try  {
			Integer teszt = Integer.parseInt(fizetes);
			if (teszt < 150000) {
				validation.addError("fizetes", "Csak 150.000 folott lehet!"); 
			}
		} catch (Exception e) {
			validation.addError("fizetes", "Csak 150.000 folott lehet!"); 
		}
		try {
			Klub klub = Klub.findById(klubId);
		} catch (Exception e){
			validation.addError("klubId", "A kivalasztott klub nem letezik!"); 
		}
		
		if (!szerzodesKezdete.matches("\\d{4}-\\d{2}-\\d{2}"))  { // Ez lehet túl szép de mivel nem date-el dolgoztam inkább ezt választottam.
		     validation.addError("szerzodesKezdete", "Nem jo datum formatum probald igy: yyyy-MM-dd!");
		 }
		if (!szerzodesVege.matches("\\d{4}-\\d{2}-\\d{2}"))  {
		     validation.addError("szerzodesVege", "Nem jo datum formatum probald igy: yyyy-MM-dd!");
		 }
		
		return validation.hasErrors();
	}
}