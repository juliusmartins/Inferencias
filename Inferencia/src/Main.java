import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void maquina(String variavel_alvo,List<String> memoria,List<String>variaveis,List<String>regras,int alvo_na_memoria) {
		String analisado;
		String esquerda,direita;
		while(alvo_na_memoria==0) {
			if(memoria.contains(variavel_alvo)) {
				System.out.println("A variável alvo " +variavel_alvo+ " é verdadeira");
				System.out.println(memoria);
				alvo_na_memoria=1;
			}
			else {
				for(int x = 0;x<regras.size();x++) {
					analisado = regras.get(x);
					if(analisado.contains(">"+variavel_alvo)) {
						for(int w = 0;w<analisado.length();w++) {
							for(int y = 0;y<variaveis.size();y++) {
								if(analisado.contains(variaveis.get(y))) {
								//	System.out.println("aqui2");
									if(memoria.contains(variaveis.get(y)) && !analisado.contains("*")) {
										memoria.add(variavel_alvo);
									}
									else if(analisado.contains("*") && analisado.contains(">"+variavel_alvo)) {
										esquerda = Character.toString(analisado.charAt(analisado.indexOf("*")-1));
										direita = Character.toString(analisado.charAt(analisado.indexOf("*")+1));
										if(memoria.contains(esquerda) && memoria.contains(direita)) {
											memoria.add(variavel_alvo);
										}
										else {
										//	System.out.println("aqui");
											maquina(esquerda,memoria,variaveis,regras,alvo_na_memoria);
											maquina(direita,memoria,variaveis,regras,alvo_na_memoria);
										}
									}
									else if(analisado.contains("|")) {
										for(int z = 0;z<analisado.length();z++) {
											if(memoria.contains(analisado.charAt(z))) {
												memoria.add(variavel_alvo);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		int quantidade,alvo_na_memoria;
		String variavel_alvo;
		List<String> memoria = new ArrayList<String>();
		List<String> variaveis = new ArrayList<String>();
		List<String> regras = new ArrayList<String>();
		Scanner user_input = new Scanner( System.in );
		
		System.out.println("Quantas variáveis?");
		quantidade = user_input.nextInt();
		for(int x = 0;x<quantidade;x++) {
			variaveis.add(user_input.next());
		}
		
		System.out.println("Quantas regras?");
		quantidade = user_input.nextInt();
		for(int x = 0;x<quantidade;x++) {
			regras.add(user_input.next());
		}
		
		System.out.println("Quantas variáveis na memória inicialmente?");
		quantidade = user_input.nextInt();
		for(int x = 0;x<quantidade;x++) {
			memoria.add(user_input.next());
		}
		
		System.out.println("Qual variável deve ser respondida?");
		variavel_alvo = user_input.next();
		variaveis.remove(variavel_alvo);
		
		alvo_na_memoria=0;
		
		maquina(variavel_alvo,memoria,variaveis,regras,alvo_na_memoria);
		user_input.close();
		}
}
//StrCharAt.java
//String a = "A quick bronze fox leapt a lazy bovine";
//for (int i=0; i < a.length(  ); i++)
// System.out.println("Char " + i + " is " + a.charAt(i));