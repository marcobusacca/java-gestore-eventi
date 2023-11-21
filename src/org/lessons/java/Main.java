package org.lessons.java;

import java.time.LocalDate;

import org.lessons.java.pojo.Evento;

public class Main {

	public static void main(String[] args) {
		
		Evento ev = null;
		
		try {
			
			ev = new Evento("rave party", LocalDate.parse("2024-12-20"), 1);
			
			System.out.println(ev);
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}
}
