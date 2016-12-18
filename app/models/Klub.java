package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

@Entity
@Table(name="klub")
public class Klub extends GenericModel{
	
	@Required
	@Id //elsődleges kulcs
	@GeneratedValue(strategy=GenerationType.IDENTITY)//autogenenrált
	@Column(name="klub_id") 
	public Long klubId;
	
	@Required
	@OneToMany(mappedBy="owningKlub")
	public List<Jatekos> jatekosok;
	
	@Required
	@Column(name = "klub_nev")
	public String nev;
	
	@Required
	@Column(name = "ev")
	public Integer ev;
	
	@Required
	@Column(name = "orszag")
	public String orszag;
	
	@Required
	@Column(name = "varos")
	public String varos;
	
	@Required
	@Column(name = "megye")
	public String megye;
}
