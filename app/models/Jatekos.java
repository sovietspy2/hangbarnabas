package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name="jatekos")
public class Jatekos extends GenericModel{
	@Id //elsődleges kulcs
	@GeneratedValue //autogenenrált
	@Column(name="jatekos_id") 
	public Long jatekosId;
	
	@Column(name="jatekos_nev")
	public String nev;
	
	@Column(name="szkezdete")
	public String szerzodesKezdete;
	
	@Column(name="szvege")
	public String szerzodesVege;
	
	@Column(name="jatekengedelyszam")
	public Integer engedelyszam;
	
	@Column(name="fizetes")
	public Integer fizetes;
	
	@Column(name="poszt")
	public String poszt;
	
	@ManyToOne
	@JoinColumn(name = "klub_id",referencedColumnName="klub_id")
	public Klub owningKlub;
}
