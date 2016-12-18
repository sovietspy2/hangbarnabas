package controllers;

import java.util.ArrayList;
import java.util.List;


import models.Jatekos;
import models.Klub;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.GenericModel;
import play.mvc.Before;
import play.mvc.Controller;
import util.ValidationUtil;

public class JatekosCreationController extends Controller{
	@Before
	public static void preparePage(){
		
		List<Klub> klubok = Klub.findAll();
		renderArgs.put("klubok", klubok); // Egy listaban tarolom es kuldom majd tovabb
		
		List<String> posztok = new ArrayList<String>();
			posztok.add("kapus");
			posztok.add("vedo");
			posztok.add("kozeppalyas");
			posztok.add("csatar");
			renderArgs.put("posztok", posztok);	
	}

	public static void createJatekosForm(){
		
		List<Klub> klubok = (List<Klub>) renderArgs.get("klubok"); //ez biztos meglesz, hiszen a preparePage metódus @Before annotációval lefut a controllerünk előtt!
		if (klubok.size() == 0) {
			flash.put("errorMessage", "Nincsenek klubok!");
			KlubController.Kezdolap();
		} else {
			render("@Application.createJatekos");
		}
	}
	
	public static void createJatekos(		@Required(message = "FATAL ERROR!") Long klubId,  // Minden más string ... 
											@Required(message = "Az nev kötelező!") String nev,
											@Required(message = "A szerzodes kezdete kötelező!") String szerzodesKezdete,
											@Required(message = "A szerzodes vege kötelező!") String szerzodesVege,
											@Required(message = "Az engedelyszam kitöltése kötelező!") String engedelyszam,
											@Required(message = "Az fizetes kitoltese kötelező!") String fizetes,
											@Required(message = "Az poszt kitoltese kötelező!") String poszt									
									){
		
		JatekosCreationControllerValidator validator = new JatekosCreationControllerValidator();
		boolean isValid = validator.isValidRequest(validation,klubId,engedelyszam,fizetes,szerzodesKezdete,szerzodesVege);
				
		if (isValid){ //
			/*params.flash(); // add http parameters to the flash scope Nem mukodott a debug az asztali gepemen
			System.out.println("VALIDATION HIBA ======== .....");
			System.out.println(klubId);
			System.out.println(nev);
			System.out.println(szerzodesKezdete);
			System.out.println(szerzodesVege);
			System.out.println(engedelyszam);
			System.out.println(fizetes);
			System.out.println(poszt);
			System.out.println("VALIDATION HIBA ======== ..................");*/
			render("@Application.createJatekos");
			
		} else {
			Jatekos jatekos = new Jatekos();
			jatekos.nev = nev;
			jatekos.owningKlub = Klub.findById(klubId); 
			jatekos.szerzodesKezdete = szerzodesKezdete;
			jatekos.szerzodesVege = szerzodesVege;
			jatekos.engedelyszam = Integer.parseInt(engedelyszam); // castolom
			jatekos.fizetes = Integer.parseInt(fizetes); // castolom
			jatekos.poszt = poszt;
		
			jatekos.save();
			KlubController.klub(klubId); // Visszamegyunk a klubhoz a sikeres addolas utan
		}
		
	}
}
