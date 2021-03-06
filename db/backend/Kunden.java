package backend;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datentypen.Person;

/**
 * Die Klasse stellt alle relevanten Information zum Kunden bereit.
 * @author Johann Muenchhagen
 * @version 1.0
 * @see Person
 */
public class Kunden extends Person{
	/**
	 * Diesse Methode Konstruiert die Klasse Kunden.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see initieren
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public Kunden()throws ClassNotFoundException, SQLException {
		initieren();
	}
	/**
	 * Diese Methode überprüft ob die Tabelle kunden in der Datenbank existiert.
	 * Wenn dies nicht der Fall ist, wird die Tabelle kunden neu angelegt.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private void initieren()throws ClassNotFoundException, SQLException{
		/*
		 * initiere die Tabelle Kunden
		 */
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:kundenDB.db");//stelle verbindung zur DB her
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);
		//erstelle eine Tabelle Kunden wenn diese nicht bereits existiert
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS kunden(kd_id INTEGER PRIMARY KEY, vorname TEXT NOT NULL, nachname TEXT NOT NULL,geburtstag TEXT NOT NULL, telefon TEXT,email TEXT,interessent BOOLEAN, aktiv BOOLEAN)");
		connection.close();
	}
	/**
	 * Diese Methode erstellt einen Datenbankeintrag, in der Kundentabelle.
	 * @param vorname Der Vorname des Kunden als String
	 * @param nachname	Der Nachname des Kunden als String
	 * @param geburtstag	Das Geburtsdatum des Kunden als String
	 * @param telefon	Die Telefonnummer des Kunden als String
	 * @param email Die E-mail des Kunden als String
	 * @param interessent Den Interessentenstatus als Integer. 1 = True, 0 = False.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean set_db_value_for_kd(String vorname, String nachname, String geburtstag, String telefon, String email,int interessent )throws ClassNotFoundException, SQLException{
		/*
		 * speicher den Vornamen, Nachnamen und Geburtstag in die Tabelle Kunden
		 * alle Werte als String übergeben
		 */try {
				Connection connection = null;//setze Connection auf null
				connection = DriverManager.getConnection("jdbc:sqlite:kundenDB.db");//stelle verbindung zur DB her
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30);
				statement.executeUpdate("INSERT INTO kunden (vorname,nachname, geburtstag,telefon,email,interessent,aktiv) VALUES ('"+vorname+"','"+nachname+"','"+geburtstag+"','"+telefon+"','"+email+"','"+interessent+"',1)");
				connection.close();
				return true;
		 }catch(Exception e) {
			 return false;
		 }
	}
	/**
	 * Diese Methode  lädt alle Kunden in eine ArrayList vom Typ Person
	 * @param anweisung Ein SQL-Befehl
	 * @return Eine ArrayList vom Typ Person
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private ArrayList<Person> get_all_values(String anweisung) throws ClassNotFoundException, SQLException{
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:kundenDB.db");
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);
		//System.out.println(anweisung);//Kontrollausgabe
		ResultSet rs = statement.executeQuery(anweisung);
		ArrayList<Person> daten = new ArrayList<Person>();
		while(rs.next()) {
			Person p = new Person();
			p.setId(rs.getInt(1));
			p.setVorname(rs.getString(2));
			p.setNachname(rs.getString(3));
			p.setGeburtsdatum(rs.getString(4));
			p.setTelefon(rs.getString(5));
			p.setEmail(rs.getString(6));
			p.setInteressent(rs.getBoolean(7));
			p.setAktiv(rs.getBoolean(8));
			daten.add(p);
		}
		connection.close();
		return daten;	
	}
	/**
	 * Diese Methode ändert ein Attribut innerhalb der Datenbank
	 * @param anweisung Ein SQL-Befehl
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_db_value_for_costumer(String anweisung)throws ClassNotFoundException, SQLException{
		try {
			Connection connection = null;//setze Connection auf null
			connection = DriverManager.getConnection("jdbc:sqlite:kundenDB.db");//stelle verbindung zur DB her
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			//Speicher die Kundendaten in der Tabelle
			statement.executeUpdate(anweisung);
			connection.close();
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	/**
	 * Diese Methode lädt alle Kundendaten, bei unbekannter Kunden-ID.
	 * @param vorname Der Vorname des Kunden als String
	 * @param nachname Der Nachname des Kunden als String
	 * @param geburtstag Das Geburtsdatum des Kunden als String
	 * @return Die Kunden-ID als Integer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private int get_kd_id(String vorname, String nachname, String geburtstag)throws ClassNotFoundException, SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:kundenDB.db");
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);
		//System.out.println(anweisung);//Kontrollausgabe
		ResultSet rs = statement.executeQuery("SELECT kd_id,telefon,email FROM kunden WHERE vorname = '"+vorname+"' AND nachname = '"+nachname+"' AND geburtstag = '"+geburtstag+"'");
		while(rs.next()) {
			this.setId(rs.getInt(1));
			this.setTelefon(rs.getString(2));
			this.setEmail(rs.getString(3));
		}
		connection.close();
		this.setVorname(vorname);
		this.setNachname(nachname);
		this.setGeburtsdatum(geburtstag);
		return this.getId();
	}
	/**
	 * Diese Methode lädt alle Kundendaten bei bekannter Kunden-ID
	 * @param id Die Kunden-ID als Integer.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean get_kd_daten(int id)throws ClassNotFoundException, SQLException{
		try {
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:sqlite:kundenDB.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			//System.out.println(anweisung);//Kontrollausgabe
			ResultSet rs = statement.executeQuery("SELECT vorname,nachname,geburtstag,telefon,email,interessent,aktiv FROM kunden WHERE kd_id = '" + id+"'");
			while(rs.next()) {
				this.setId(id);
				this.setVorname(rs.getString(1));
				this.setNachname(rs.getString(2));
				this.setGeburtsdatum(rs.getString(3));
				this.setTelefon(rs.getString(4));
				this.setEmail(rs.getString(5));
				this.setInteressent(rs.getBoolean(6));
				this.setAktiv(rs.getBoolean(7));
			}
			connection.close();
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	/**
	 * Diese Methode gibt den Vornamen des Kunden zurück.
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private String get_kd_vorname() throws ClassNotFoundException, SQLException{
		return this.getVorname();
	}
	/**
	 * Diese Methode gibt den Nachnamen des Kunden zurück.
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private String get_kd_nachname() throws ClassNotFoundException, SQLException{
		return this.getNachname();
	}
	/**
	 * Diese Methode gibt das Geburtsdatum des Kunden zurück.
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private String get_kd_geburtstag() throws ClassNotFoundException, SQLException{
		return this.getGeburtsdatum();
	}
	/**
	 * Diese Methode gibt die Telefonnummer des Kunden zurück.
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private String get_kd_telefon() throws ClassNotFoundException, SQLException{
		return this.getTelefon();
	}
	/**
	 * Diese Methode gibt die E-mail des Kunden zurück.
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private String get_kd_email() throws ClassNotFoundException, SQLException{
		return this.getEmail();
	}
	/**
	 * Diese Methode gibt den Interessentenstatus des Kunden zurück.
	 * @return Boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean get_kd_interessent()throws ClassNotFoundException, SQLException{
		return this.isInteressent();
	}
	/**
	 * Diese Methode gibt den Aktivstatus des Kunden zurück.
	 * @return Boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean get_kd_aktiv()throws ClassNotFoundException, SQLException{
		return this.isAktiv();
	}
	/**
	 * Diese Methode ändert den Vornamen des Kunden in der Datenbank
	 * @param id Die Kunden-ID als Integer.
	 * @param vorname Den neuen Vornamen des Kunden als String.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_kd_vorname(int id,String vorname) throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("UPDATE kunden SET vorname = '"+vorname+"' WHERE kd_id = '"+id+"'");
	}
	/**
	 * Diese Methode ändert den Nachnamen des Kunden in der Datenbank
	 * @param id Die Kunden-ID als Integer.
	 * @param nachname Den neuen Nachnamen des Kunden als String
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_kd_nachname(int id,String nachname) throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("UPDATE kunden SET nachname = '"+nachname+"' WHERE kd_id = '"+id+"'");
	}
	/**
	 * Diese Methode ändert das Geburtsdatum des Kunden in der Datenbank
	 * @param id Die Kunden-ID als Integer
	 * @param geburtstag Das neue Geburtsdatum des Kunden als String.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_kd_geburtstag(int id,String geburtstag)throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("UPDATE kunden SET geburtstag = '"+geburtstag+"' WHERE kd_id = '"+id+"'");
	}
	/**
	 * Diese Methode ändert die Telefonnummer des Kunden in der Datenbank
	 * @param id Die Kunden-ID als Integer
	 * @param telefon Die neue Telefonnummer des Kunden als String
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_kd_telefon(int id,String telefon)throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("UPDATE kunden SET telefon = '"+telefon+"' WHERE kd_id = '"+id+"'");
	}
	/**
	 * Diese Methode ändert die E-mail Adresse des Kunden in der Datenbank
	 * @param id Die Kunden-ID als Integer
	 * @param email Die neue E-mail Adresse des Kunden als String
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_kd_email(int id,String email)throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("UPDATE kunden SET email = '"+email+"' WHERE kd_id = '"+id+"'");
	}
	/**
	 * Diese Methode ändert den Interessentenstatus des Kunden in der Datenbank
	 * @param id Die Kunden-ID als Integer
	 * @param interessent Den neuen Interessentenstatus als Integer. 1 = True, 0 = False
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_kd_interessent(int id,int interessent)throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("UPDATE kunden SET interessent = '"+interessent+"' WHERE kd_id = '"+id+"'");
	}
	/**
	 * Diese Methode ändert den Aktivstatus des Kunden in der Datenbank
	 * @param id Die Kunden-ID als Integer
	 * @param aktiv Den neuen Aktivstatus des Kunden als Integer. 1 = True, 0 = False
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean change_kd_aktiv(int id,int aktiv)throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("UPDATE kunden SET aktiv = '"+aktiv+"' WHERE kd_id = '"+id+"'");
	}
	/**
	 * Diese Methode gibt alle Kunden in einer ArrayList vom Typ Person zurück
	 * @return ArrayList vom Typ Person
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_all_values
	 * @see Person
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private ArrayList<Person> get_kd_all() throws ClassNotFoundException, SQLException{
		return get_all_values("SELECT * FROM kunden");
	}
	/**
	 * Diese Methode löscht eine Kunden aus der Datenbank
	 * @param kunden_id Die Kunden-ID des zu löschenden Kunden
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_db_value_for_costumer
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	private boolean delete_kd(int kunden_id)throws ClassNotFoundException, SQLException{
		return change_db_value_for_costumer("DELETE FROM kunden WHERE kd_id = '"+kunden_id+"'");
	}
	
	/**
	 * Diese Methode lädt alle Kundendaten.
	 * <p>Immer als erstes nutzen, wenn die Kundennummer bekannt ist</p>
	 * @param kunden_nummer Die Kundennummer als Integer
	 * @return Boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_daten
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean lade_kunden_daten(int kunden_nummer)throws ClassNotFoundException, SQLException{
		return get_kd_daten(kunden_nummer);
	}
	/**
	 * Diese Methode erstellt einen Kunden.
	 * <p> Der Kunde wird Standardmäßig aktiv gesetzt</p>
	 * @param vorname Den Vornamen des Kunden als String.
	 * @param nachname Den Nachnamen des Kunden als String.
	 * @param geburtstag Das Geburtsdatum als String.
	 * @param telefon Die Telefonnummer als String. Falls unbekannt, leeren String " ".
	 * @param email Die Email als String. Falls unbekannt, leeren String " ".
	 * @param interessent Den Interessentenstatus des Kunden als Integer. 0 = False, 1 = True.
	 * @return Boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see set_db_value_for_kd
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean set_db_value(String vorname, String nachname, String geburtstag, String telefon,String email,int interessent)throws ClassNotFoundException, SQLException {
		return set_db_value_for_kd(vorname,nachname,geburtstag,telefon,email,interessent);
	}
	/**
	 * Diese Methode gibt die Kunden-ID zurück.
	 * <p> Alle Parameter müssen so geschrieben werden, wie Sie in der Datenbank stehen. Ansonsten gibt es keine Ausgabe.</p>
	 * @param vorname Den Vornamen des Kunden als String.
	 * @param nachname Den Nachnamen des Kunden als String.
	 * @param geburtstag Das Geburtsdatum des Kunden als String.
	 * @return Die Kunden-ID als Integer.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_id
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public int get_id(String vorname, String nachname, String geburtstag)throws ClassNotFoundException, SQLException {
		return get_kd_id(vorname,nachname, geburtstag);
	}
	/**
	 * Diese Methode gibt den Vornamen des Kunden zurück.
	 * <p> Zuerst die Methode lade_kunden_daten() ausführen, wenn die ID bekannt ist und die Methode nicht schon mal ausgeführt wurde</p>
	 * <p> Bei unbekannter ID die Funktion lade_kunden_daten()ignorieren</p>
	 * @return Den Vornamen als String.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_vorname
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public String get_vorname() throws ClassNotFoundException, SQLException{
		return get_kd_vorname();
	}
	/**
	 * Diese Methode gibt den Nachnamen des Kunden zurück.
	 * <p> Zuerst die Methode lade_kunden_daten() ausführen, wenn die ID bekannt ist und die Methode nicht schon mal ausgeführt wurde</p>
	 * <p> Bei unbekannter ID die Funktion lade_kunden_daten()ignorieren</p>
	 * @return Den Nachnamen als String.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_nachname
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public String get_nachname() throws ClassNotFoundException, SQLException{
		return get_kd_nachname();
	}
	/**
	 * Diese Methode gibt das Geburtsdatum des Kunden zurück.
	 * <p> Zuerst die Methode lade_kunden_daten() ausführen, wenn die ID bekannt ist und die Methode nicht schon mal ausgeführt wurde</p>
	 * <p> Bei unbekannter ID die Funktion lade_kunden_daten()ignorieren</p>
	 * @return Das Geburtsdatum als String.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_geburtstag
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public String get_geburtstag() throws ClassNotFoundException, SQLException{
		return get_kd_geburtstag();
	}
	/**
	 * Diese Methode gibt die Telefonnummer des Kunden zurück.
	 * <p> Zuerst die Methode lade_kunden_daten() ausführen, wenn die ID bekannt ist und die Methode nicht schon mal ausgeführt wurde</p>
	 * <p> Bei unbekannter ID die Funktion lade_kunden_daten()ignorieren</p>
	 * @return Die Telefonnummer als String.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_telefon
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public String get_telefon() throws ClassNotFoundException, SQLException{
		return get_kd_telefon();
	}
	/**
	 * Diese Methode gibt die Email des Kunden zurück.
	 * <p> Zuerst die Methode lade_kunden_daten() ausführen, wenn die ID bekannt ist und die Methode nicht schon mal ausgeführt wurde</p>
	 * <p> Bei unbekannter ID die Funktion lade_kunden_daten()ignorieren</p>
	 * @return Die Email als String.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_email
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public String get_email()throws ClassNotFoundException, SQLException{
		return get_kd_email();
	}
	/**
	 * Diese Methode gibt zurück ob der Kunde ein Interessent ist.
	 * <p> Zuerst die Methode lade_kunden_daten() ausführen, wenn die ID bekannt ist und die Methode nicht schon mal ausgeführt wurde</p>
	 * <p> Bei unbekannter ID die Funktion lade_kunden_daten()ignorieren</p>
	 * @return Den Interessentenstatus als Boolean.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_interessent
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean get_interessent()throws ClassNotFoundException, SQLException{
		return get_kd_interessent();
	}
	/**
	 * Diese Methode gibt den Status des Kunden zurück.
	 * <p> Ist dieser noch Kunde oder nicht</p>
	 * <p> Zuerst die Methode lade_kunden_daten() ausführen, wenn die ID bekannt ist und die Methode nicht schon mal ausgeführt wurde</p>
	 * <p> Bei unbekannter ID die Funktion lade_kunden_daten()ignorieren</p>
	 * @return Den Status als Boolean.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_aktiv
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean get_aktiv()throws ClassNotFoundException, SQLException{
		return get_kd_aktiv();
	}
	/**
	 * Diese Methode ändert den Vornamen des Kunden.
	 * @param id Die Kunden-ID als Integer.
	 * @param vorname Den Vornamen als String.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_kd_vorname
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean change_vorname(int id, String vorname) throws ClassNotFoundException, SQLException{
		return change_kd_vorname(id,vorname);
	}
	/**
	 * Diese Methode ändert den Nachnamen des Kunden.
	 * @param id Die Kunden-ID als Integer.
	 * @param nachname Den Nachnamen als String.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_kd_nachname
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean change_nachname(int id, String nachname)throws ClassNotFoundException, SQLException{
		return change_kd_nachname(id,nachname);
	}
	/**
	 * Diese Methode ändert das Geburtsdatum des Kunden.
	 * @param id Die Kunden-ID als Integer.
	 * @param geburtstag Das Geburtsdatum als String.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_kd_geburtstag
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean change_geburtstag(int id, String geburtstag) throws ClassNotFoundException, SQLException{
		return change_kd_geburtstag(id,geburtstag);
	}
	/**
	 * Diese Methode ändert die Telefonnummer des Kunden.
	 * @param id Die Kunden-ID als Integer.
	 * @param telefon Die Telefonnummer als String.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_kd_telefon
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean change_telefon(int id, String telefon) throws ClassNotFoundException, SQLException{
		return change_kd_telefon(id,telefon);
	}
	/**
	 * Diese Methode ändert die Email des Kunden.
	 * @param id Die Kunden-ID als Integer.
	 * @param email Die Email als String.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_kd_email
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean change_email(int id, String email) throws ClassNotFoundException, SQLException{
		return change_kd_email(id,email);
	}
	/**
	 * Diese Methode ändert den Interessentenstatus des Kunden.
	 * @param id Die Kunden-ID als Integer.
	 * @param interessent Den Interessentenstatus als Integer. 0 = False, 1 = True.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_kd_interessent
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean change_interessent(int id, int interessent) throws ClassNotFoundException, SQLException{
		return change_kd_interessent(id,interessent);
	}
	/**
	 * Diese Methode ändert den Status des Kunden, ob dieser noch Kunde ist oder nicht.
	 * @param id Die Kunden-ID als Integer.
	 * @param aktiv Den Status als Integer. 0 = False, 1 = True.
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see change_kd_aktiv
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public void change_aktiv(int id, int aktiv) throws ClassNotFoundException, SQLException{
		change_kd_aktiv(id,aktiv);
	}
	/**
	 * Diese Methode gibt alle Kunden aus.
	 * <p> Bei dieser Methode müssen die Kunden nicht vorher geladen werden</p>
	 * @return ArrayList vom Typ Person
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see get_kd_all
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public ArrayList<Person> get_all() throws ClassNotFoundException, SQLException{
		return get_kd_all();
	}
	/**
	 * Löscht einen Kunden
	 * @param kunden_id als Integer
	 * @return Boolean als Funktionsindikator
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see delete_kd
	 * @see ClassNotFoundException
	 * @see SQLException
	 */
	public boolean delete(int kunden_id)throws ClassNotFoundException, SQLException{
		return delete_kd(kunden_id);
	}
}
