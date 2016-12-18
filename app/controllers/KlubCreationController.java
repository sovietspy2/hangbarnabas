package controllers;

import java.util.Calendar;
import java.util.List;

import java.sql.Date;
import java.text.SimpleDateFormat;

import models.Klub;

import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;
import util.ValidationUtil;

public class KlubCreationController extends Controller{
	
	public static void createKlubForm(){
		render("@Application.createKlub");
	}
	
	public static void createKlub(@Required(message = "A név kötelező!") String nev, // Minden string lesz a validacio miatt
			@Required(message = "Az alapitas eve kotelezo!")String ev,
			@Required(message = "Az varos kotelezo!")String orszag,
			@Required(message = "A megye kotelezo!")String megye,
			@Required(message = "A varos kotelezo!")String varos){
		
		Klub klub = Klub.find(" nev = ?", nev).first();
		if (klub != null){
			validation.addError("nev", "Ilyen néven már létezik már klub!");
		}
		if (!ValidationUtil.isInteger(ev)){
			validation.addError("ev", "Az evszamnak egesz szamnak kell lennie!"); 
		}
		
		try {
			Integer teszt = Integer.parseInt(ev);
			if (teszt > 2016 || teszt < 1862)
			{
				validation.addError("ev", "Nem megfelelo evszam!");
			}
		} catch (Exception e){
			validation.addError("ev", "Az evszamnak egesz szamnak kell lennie!"); // Biztos ami biztos
		}
		
				
		if (validation.hasErrors()){ //
			params.flash(); // add http parameters to the flash scope
			render("@Application.createKlub");
		} else {
			// Ezt vegul nem hasznaltam
			/*Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String formatted = format1.format(cal.getTime());*/
			
			klub = new Klub();
			klub.nev = nev;
			klub.ev = Integer.parseInt(ev);
			klub.orszag = orszag;
			klub.megye = megye;
			klub.varos = varos;
			
			klub.save();
			
			KlubController.Kezdolap();
		}
	}
}
