package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	
	public ProcessosController() {
		super();
	}
	
	public String os() {
		String os = System.getProperty("os.name");
		return os;	
	}
	
	public void callIpconfig(String ipconfig, String os) {
		try {
			Process p = Runtime.getRuntime().exec(ipconfig);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine().concat(ipconfig);
			StringBuffer bufferr = new StringBuffer();
			
			while (linha != null) {
				bufferr.append(linha);
				bufferr.append(" ");
				bufferr.append("\n");			
				linha = buffer.readLine();
			}
			String contSplit = null;
			if (os.contains("Windows")) {
				contSplit = "Adaptador";
			} else if(os.contains("Linux")) {
				contSplit = "Ether";
			}
			String[] split = bufferr.toString().split(contSplit);
			String controle = null;

			if (os.contains("Windows")) {
				controle = "IPv4";
			} else if(os.contains("Linux")) {
				controle = "inet";
			}
			for (int i = 0; i < split.length; i++) {
				if (split[i].contains(controle)) {
					System.out.println("Adaptdaor" + split[i]);
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public void callProcess(String process, String os) {
		
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream(); 
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer1 = new BufferedReader(leitor);
			String linha = buffer1.readLine();
			
			if (os.contains("Windows")) {
				while (linha != null) {
					if (linha.contains("dia")) {
						String [] split = linha.split(",");
						for (int i = 1; i < split.length; i++) {
							if (split[i].contains("dia")) {
								System.out.println("Ping" + split[i]);
							}
						}
					}
					linha = buffer1.readLine();
				}
			} else if(os.contains("Linux")) {
				while (linha != null) {
					String [] split = linha.split("rtt");
					for (int i = 1; i < split.length; i++) {
						if (split[i].contains("avg")) {
							System.out.println("Ping mínimo, médio, maior e mdev" + split[i]);
						}
					}
					linha = buffer1.readLine();
				}
			}
			buffer1.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}