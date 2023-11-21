package org.lessons.java.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concerto extends Evento {
	
	private LocalTime ora;
	private BigDecimal prezzo;
	
	
	public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, BigDecimal prezzo) throws Exception {
		
		super(titolo, data, postiTotali);
		setOra(ora);
		setPrezzo(prezzo);
	}
	
	
	public LocalTime getOra() {
		return ora;
	}
	public void setOra(LocalTime ora) {
		this.ora = ora;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) throws Exception {
		
		if (prezzo.compareTo(BigDecimal.ZERO) < 0) {
			throw new Exception("il prezzo non può essere negativo");
		}
		
		this.prezzo = prezzo;
	}
	
	
	private String getDataOraFormattata() {
		
		return getDataFormattata() + " " + getOra().format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	private String getPrezzoFormattato() {
		
		return String.format(Locale.ITALY, "%.2f€", getPrezzo());
	}
	
	
	@Override
	public String toString() {
		
		return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato() + " - " + getPostiPrenotati() + "/" + getPostiTotali();
	}
}
