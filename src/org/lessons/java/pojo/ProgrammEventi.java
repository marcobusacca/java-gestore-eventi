package org.lessons.java.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProgrammEventi {

	private String titolo;
	private List<Evento> eventi;
	
	
	public ProgrammEventi(String titolo) {
		setTitolo(titolo);
		setEventi(new ArrayList<>());
	}
	
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public List<Evento> getEventi() {
		return eventi;
	}
	public void setEventi(List<Evento> eventi) {
		this.eventi = eventi;
	}
	
	
	public void aggiungiEvento(Evento evento) {
		getEventi().add(evento);
    }
	public void svuotaEventi() {
		getEventi().clear();
	}
	public int getEventiSize() {
		return getEventi().size();
	}
	public List<Evento> getEventiFiltrati(LocalDate data){
		
		List<Evento> filteredEventi = new ArrayList<>();
		
		for (Evento evento : getEventi()) {
			
			if (evento.getData().isEqual(data)) {
				
				filteredEventi.add(evento);
			}
		}
		
		return filteredEventi;
	}
	public String visualizzaProgramma(List<Evento> eventi) {

		eventi.sort(Comparator.comparing(evento -> evento.getData()));
		
		String str = getTitolo() + "\n---------------------------\n";
		
		for (Evento evento : eventi) {
			
			String d = evento.getDataFormattata();
			
			String t = evento.getTitolo();
			
			str = str + d + " - " + t + "\n";
		}
		
		return str;
	}
}
