import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void maquina(String variavel_alvo,List<String> memoria,List<String>variaveis,List<String>regras) {
		String analisado;
		String esquerda,direita;
		boolean memoria_mudou;
		memoria_mudou = true;
		while(memoria_mudou) {
			memoria_mudou = false;
			if(memoria.size() == variaveis.size()) {
				System.out.println("A variável alvo " +variavel_alvo+ " é verdadeira");
				System.out.println(memoria);
			}
			else {
				for(int x = 0;x<regras.size();x++) {
					analisado = regras.get(x);
					variavel_alvo = Character.toString(analisado.charAt(analisado.indexOf(">")+1));
					for(int w = 0;w<analisado.length();w++) {
						for(int y = 0;y<variaveis.size();y++) {
							if(analisado.contains(variaveis.get(y))) {
								if(memoria.contains(variaveis.get(y)) && !analisado.contains("*")) {
									if(!memoria.contains(variavel_alvo)) {
										memoria.add(variavel_alvo);
										memoria_mudou = true;
									}
								}
								else if(analisado.contains("*") && analisado.contains(">"+variavel_alvo)) {
									esquerda = Character.toString(analisado.charAt(analisado.indexOf("*")-1));
									direita = Character.toString(analisado.charAt(analisado.indexOf("*")+1));
									if(memoria.contains(esquerda) && memoria.contains(direita)) {
										if(!memoria.contains(variavel_alvo)) {
											memoria.add(variavel_alvo);
											memoria_mudou = true;
										}
									}
								}
								else if(analisado.contains("|")) {
									for(int z = 0;z<analisado.length();z++) {
										if(memoria.contains(analisado.charAt(z))) {
											if(!memoria.contains(variavel_alvo)) {
												memoria.add(variavel_alvo);
												memoria_mudou = true;
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
		int quantidade;
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
		//variaveis.remove(variavel_alvo);
		
		maquina(variavel_alvo,memoria,variaveis,regras);
		user_input.close();
		}
}