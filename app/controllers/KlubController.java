package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Jatekos;
import models.Klub;
import models.KlubAdat;
import play.mvc.Controller;

public class KlubController extends Controller{
	public static void Kezdolap(){
		
		List<Klub> klubok = Klub.findAll(); 
		List<KlubAdat> adat = new ArrayList<KlubAdat>();
		
		// Atrakok minden adatot ami kelleni fog a kezdolapon egy masik listaba
		for (Klub k: klubok){
			KlubAdat a = new KlubAdat();
			a.klubId = k.klubId; //FONTOS
			a.nev = k.nev;
			a.varos = k.varos;
			a.jatekosokSzama = k.jatekosok.size(); // jatekosok megszamolasa
			
			Integer osszeg = 0;
			
			for (Jatekos jatekos: k.jatekosok)	{ //fizetesek szamolgatasa
				osszeg = osszeg + jatekos.fizetes;
			}
			a.fizetesek = osszeg;
			adat.add(a);
		}
		
		renderArgs.put("adat",adat);
		render("@Application.Kezdolap");
	}
	/// DELETE
	public static void deleteKlub(Long klubId){
		String errorMessage = null;
		if (klubId != null){
			Klub klub = Klub.findById(klubId);
			if (klub != null){
				if (klub != null){
					if (klub.jatekosok != null) {
						for (Jatekos jatekos : klub.jatekosok){
							jatekos.delete();
						}
					}
					if (klub != null) klub.delete();
					KlubController.Kezdolap();
				}
			} else {
				errorMessage = "A törlendo klub nem letezik!";
			}
		} else {
			errorMessage = "Ures azonosito!";
		}
		
		if (errorMessage != null){
			flash.put("errorMessage", errorMessage);
		}
		KlubController.Kezdolap();
	}
	// KLUB RESZLETEK
	public static void klub(Long klubId){
		Klub klub = null;
		
		if (klubId != null){
			klub = Klub.findById(klubId);
		}
		
		if (klub == null){
			KlubController.Kezdolap();
		} else {
			//
			Integer csatarokSzama = 0;
			Integer kapusokSzama = 0;
			Integer kozeppalyasokSzama = 0;
			Integer vedokSzama = 0;
			
			List<Jatekos> jatekosok = new ArrayList<Jatekos>();
			
			for (Jatekos jatekos: klub.jatekosok) {
				jatekosok.add(jatekos);
			}
			
			for (Jatekos j: jatekosok) {
				if (j.poszt.equals("kapus")) kapusokSzama++;
				if (j.poszt.equals("csatar")) csatarokSzama++;
				if (j.poszt.equals("kozeppalyas")) kozeppalyasokSzama++;
				if (j.poszt.equals("vedo")) vedokSzama++;
			}
			
			renderArgs.put("klub", klub); // A klubok listaja 
			
			// Az ertekek, hogy ki tudjam irni a hibauzenetet ha kell
			renderArgs.put("csatarokSzama", csatarokSzama);
			renderArgs.put("kapusokSzama", kapusokSzama);
			renderArgs.put("kozeppalyasokSzama", kozeppalyasokSzama);
			renderArgs.put("vedokSzama", vedokSzama);
			render("@Application.klub");
		}
	}
}
