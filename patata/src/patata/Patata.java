package patata;

import java.util.Scanner;

public class Patata {
static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//RECOGER NOTAS Y COMPROBACIÓN
		System.out.println("Nota práctica: ");
		double notaPractica = compruebaNota(Double.parseDouble(s.nextLine()));
		
		System.out.println("Nota teórica: ");
		double notaTeorico = compruebaNota(Double.parseDouble(s.nextLine()));
		

		System.out.println("Nota problemas: ");
		double notaProblemas = compruebaNota(Double.parseDouble(s.nextLine()));
		
		
		//CALCULAR NOTA FINAL
		double notaFinal = (notaPractica*0.10 + notaProblemas*0.50 + notaTeorico*0.40)/3;
		if(notaFinal >=5) {
			System.out.println("muy bien campeón!");
		}else {
			System.out.println("try again");
		}
		
	}
	public static double compruebaNota(double nota) {
		while(nota<0 || nota > 10) {
			System.out.println("Introduce una nota válida:");
			nota = Double.parseDouble(s.nextLine());
		}
		return nota;
	}
	

}
