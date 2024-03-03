package view;

import controller.ProcessosController;

public class Principal {
	public static void main (String[] args) {
		ProcessosController proc = new ProcessosController();
		
		String os = proc.os();
		System.out.println("O sistema operacional é " + os);
				
		if (os.contains("Windows")) {
			String ipconfig = "ipconfig";
			proc.callIpconfig(ipconfig, os);
			String process = "ping -4 -n 10 www.google.com.br";
			proc.callProcess(process, os);
		} else {
			String ipconfig = "ifconfig";
			proc.callIpconfig(ipconfig, os);
			String process = "ping -4 -c 10 www.google.com.br";
			proc.callProcess(process, os);
		}
	}
}