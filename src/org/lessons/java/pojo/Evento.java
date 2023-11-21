package org.lessons.java.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

	private String titolo;
	private LocalDate data;
	private int postiTotali;
	private int postiPrenotati;
	
	
	public Evento(String titolo, LocalDate data, int postiTotali) throws Exception {
		
		setTitolo(titolo);
		setData(data);
		setPostiTotali(postiTotali);
		setPostiPrenotati(0);
	}
	
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) throws Exception {
		
		if (data.isBefore(LocalDate.now())) {
			throw new Exception("La data inserita è già passata");
		}
		
		this.data = data;
	}
	public int getPostiTotali() {
		return postiTotali;
	}
	private void setPostiTotali(int postiTotali) throws Exception {
		
		if (postiTotali <= 0) {
			throw new Exception("Il numero di posti totali deve essere almeno 1");
		}
		
		this.postiTotali = postiTotali;
	}
	public int getPostiPrenotati() {
		return postiPrenotati;
	}
	private void setPostiPrenotati(int postiPrenotati) {
		this.postiPrenotati = postiPrenotati;
	}
	
	
	public void prenota() throws Exception {
		
		if (getData().isBefore(LocalDate.now())) {
			
			throw new Exception("Questo evento è terminato");
		}
		if (getPostiTotali() == getPostiPrenotati()) {
			
			throw new Exception("Questo evento è SOLD OUT");
		}
		
		setPostiPrenotati(getPostiPrenotati() + 1);
	}
	public void disdici() throws Exception {
		
		if (getData().isBefore(LocalDate.now())) {
			
			throw new Exception("Questo evento è terminato");
		}
		if (getPostiPrenotati() == 0) {
			
			throw new Exception("Non ci sono prenotazioni per questo evento");
		}
		
		setPostiPrenotati(getPostiPrenotati() - 1);
	}
	
	private String getDataFormattata(LocalDate data) {
		
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String dataFormattata = data.format(formatoData);
		
		return dataFormattata;
	}
	
	@Override
	public String toString() {
		
		return getDataFormattata(getData()) + " - " + getTitolo();
	}
}
