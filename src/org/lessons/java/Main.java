package org.lessons.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import org.lessons.java.pojo.Concerto;
import org.lessons.java.pojo.Evento;
import org.lessons.java.pojo.ProgrammEventi;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		// CHIEDO ALL'UTENTE IL TITOLO DEL "ProgrammEventi"
		System.out.print("Inserisci il titolo della lista degli eventi: ");
		String titoloProgrammEventi = in.nextLine();
		
		// CREO L'ISTANZA DELLA CLASSE "ProgrammEventi"
		ProgrammEventi p = new ProgrammEventi(titoloProgrammEventi);
		
		System.out.print("\n");
		
		while (true) {
			
			// CHIEDO IL TITOLO DELL'EVENTO ALL'UTENTE
			System.out.print("Inserisci il titolo dell'evento: ");
			String titolo = in.nextLine();
			
			// CHIEDO LA DATA DELL'EVENTO ALL'UTENTE
			System.out.print("Inserisci la data dell'evento (yyyy-mm-dd): ");
			String data = in.nextLine();
			
			// CHIEDO IL NUMERO DI POSTI TOTALI ALL'UTENTE
			System.out.print("Inserisci il numero di posti totali: ");
			String strPostiTotali = in.nextLine();
			int postiTotali = Integer.valueOf(strPostiTotali);
			
			// CHIEDO IL TIPO DI EVENTO ALL'UTENTE
			System.out.print("Scegli il tipo di evento: (evento | concerto) ");
			String typeChoice = in.nextLine().toLowerCase();
			
			// DICHIARO L'ISTANZA DELLA CLASSE EVENTO
			Evento ev = null;
			
			if (typeChoice.equals("concerto")) {
				
				// CHIEDO L'ORARIO DEL CONCERTO
				System.out.print("Inserisci l'orario del concerto (hh:mm): ");
				String orario = in.nextLine();
				
				// CHIEDO IL PREZZO DEL CONCERTO
				System.out.print("Inserisci il prezzo del concerto (00.00): ");
				String strPrezzo = in.nextLine();
				BigDecimal prezzo = new BigDecimal(strPrezzo);
				
				try {
					
					// CREO L'ISTANZA DELLA CLASSE CONCERTO
					ev = new Concerto(titolo, LocalDate.parse(data), postiTotali, LocalTime.parse(orario), prezzo);
					
				} catch (Exception e) {
					
					System.out.println("Errore: " + e.getMessage());
					in.close();
					return;
				}
				
			} else {
				
				try {
					
					// CREO L'ISTANZA DELLA CLASSE EVENTO
					ev = new Evento(titolo, LocalDate.parse(data), postiTotali);
					
				} catch (Exception e) {
					
					System.out.println("Errore: " + e.getMessage());
					in.close();
					return;
				}
			}
			
			
			System.out.println("\n" + ev);
			
			
			try {
				
				// CHIEDO ALL'UTENTE SE VUOLE EFFETTUARE DELLE PRENOTAZIONI
				System.out.print("\nVuoi effettuare delle prenotazioni? (si | no): ");
				String choice = in.nextLine().toLowerCase();
				
				// SE L'UTENTE HA INSERITO "SI", ESEGUO IL BLOCCO DI CODICE
				if (choice.equals("si")) {
					
					// CHIEDO ALL'UTENTE QUANTE PRENOTAZIONI VUOLE EFFETTUARE
					System.out.print("Quante prenotazioni vuoi effettuare? ");
					String StrCounterPrenotazioni = in.nextLine();
					int counterPrenotazioni = Integer.valueOf(StrCounterPrenotazioni);
					
					// EFFETTUO LE PRENOTAZIONI RICHIESTE DALL'UTENTE
					for (int i = 1; i <= counterPrenotazioni; i++) {
						
						ev.prenota();
						System.out.println("Prenotazione " + i + " effettuata");
					}
				}
				
			} catch (Exception e) {
				
				System.out.println("Errore: " + e.getMessage());
			}
			
			
			// STAMPO POSTI PRENOTATI E POSTI DISPONIBILI
			System.out.println("\nPosti prenotati: " + ev.getPostiPrenotati() + "/" + ev.getPostiTotali());
			System.out.println("Posti disponibili: " + (ev.getPostiTotali() - ev.getPostiPrenotati()) + "/" + ev.getPostiTotali());
			
			
			try {
				// CHIEDO ALL'UTENTE SE VUOLE DISDIRE DELLE PRENOTAZIONI
				System.out.print("\nVuoi disdire delle prenotazioni? (si | no): ");
				String choice = in.nextLine().toLowerCase();
				
				// SE L'UTENTE HA INSERITO "SI", ESEGUO IL BLOCCO DI CODICE
				if (choice.equals("si")) {
					
					// CHIEDO ALL'UTENTE QUANTE PRENOTAZIONI VUOLE DISDIRE
					System.out.print("Quante prenotazioni vuoi disdire? ");
					String StrCounterPrenotazioni = in.nextLine();
					int counterPrenotazioni = Integer.valueOf(StrCounterPrenotazioni);
					
					// DISDICO LE PRENOTAZIONI RICHIESTE DALL'UTENTE
					for (int i = 1; i <= counterPrenotazioni; i++) {
						
						ev.disdici();
						System.out.println("Prenotazione " + i + " eliminata");
					}
				}
				
			} catch (Exception e) {
				
				System.out.println("Errore: " + e.getMessage());
			}
			
			
			// STAMPO POSTI PRENOTATI E POSTI DISPONIBILI
			System.out.println("\nPosti prenotati: " + ev.getPostiPrenotati() + "/" + ev.getPostiTotali());
			System.out.println("Posti disponibili: " + (ev.getPostiTotali() - ev.getPostiPrenotati()) + "/" + ev.getPostiTotali());
			
			// AGGIUNGO L'EVENTO CREATO ALLA LISTA EVENTI DELLA CLASSE "ProgrammEventi"
			p.aggiungiEvento(ev);
			
			// CHIEDO ALL'UTENTE SE VUOLE INSERIRE UN ALTRO EVENTO
			System.out.print("\nVuoi inserire un altro evento? (si | no): ");
			String finalChoiche = in.nextLine().toLowerCase();
			
			
			// SE L'UTENTE HA SCELTO "NO", INTERROMPO IL CICLO WHILE
			if (finalChoiche.equals("no")) {
				
				// CHIEDO ALL'UTENTE SE VUOLE ELIMINARE LA LISTA
				System.out.print("Vuoi eliminare la lista eventi? (si | no): ");
				String deleteChoice = in.nextLine().toLowerCase();
				
				if (deleteChoice.equals("si")) {
					
					// SVUOTO LA LISTA
					p.svuotaEventi();
					
					System.out.println("Lista: '" + p.getTitolo() + "' azzerata");
					
					in.close();
					return;
				}
				
				// CHIEDO ALL'UTENTE SE VUOLE FILTRARE LA LISTA PER DATA
				System.out.print("Vuoi filtrare la lista per data? (si | no): ");
				String filtringChoice = in.nextLine().toLowerCase();
				
				if (filtringChoice.equals("si")) {
					
					// CHIEDO ALL'UTENTE LA DATA CON CUI FILTRARE LA LISTA
					System.out.print("Inserisci una data (yyyy-mm-dd): ");
					String dataFiltraggio = in.nextLine();
					LocalDate d = LocalDate.parse(dataFiltraggio);
					
					System.out.println("\n" + p.visualizzaProgramma(p.getEventiFiltrati(d)));
					
					in.close();
					return;
				}
				break;
			}
		}
		
		in.close();
		
		// STAMPO LA LISTA DEGLI EVENTI TRAMITE LA FUNZIONE "visualizzaProgramma" di ProgrammEventi
		System.out.println("\n" + p.visualizzaProgramma(p.getEventi()));
		System.out.println("\nTotale numero eventi: " + p.getEventiSize());
	}
}
