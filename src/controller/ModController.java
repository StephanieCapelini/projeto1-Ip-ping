package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ModController {
	public void IP (String So) {
		if (So.contains("Windows")) {
			try {
				Process p=Runtime.getRuntime().exec("ipconfig");
				InputStream fluxo= p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer= new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null) {
					if (linha.contains("Adaptador")) {
						String adap= linha;
						linha= buffer.readLine();
						try {
							while (!linha.contains("Adaptador")) {
								if (linha.contains("IPv4")) {
									String [] Ip= linha.split(":");
									System.out.println(adap+" "+Ip[1]);
									linha= buffer.readLine();
								} 
								else {
								linha= buffer.readLine();
								}
							}
						}
						catch(java.lang.NullPointerException e) {
							linha= buffer.readLine();
						}
					}
					linha= buffer.readLine();	
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if (So.contains("Linux")) {
			try {
				Process p=Runtime.getRuntime().exec("ifconfig");
				InputStream fluxo= p.getInputStream();
				InputStreamReader leitor= new InputStreamReader(fluxo);
				BufferedReader buffer= new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null) {
					if (linha.contains("flags")) {
						String [] adap= linha.split(":");
						linha= buffer.readLine();
						try {
							while (!linha.contains("flags")) {
								if (linha.contains("inet")) {
									String [] Ip= linha.split("netmask");
									String [] inet=Ip[0].split("inet");
									System.out.println(adap[0]+" "+Ip[1]);
								}
								linha= buffer.readLine();
							}
						}
						catch(java.lang.NullPointerException e) {
							linha= buffer.readLine();
						}
					}
					linha= buffer.readLine();	
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			}
			catch(IOException e) {
				e.printStackTrace();
		}
	}
	}
	public void ConsultaPing (String So) {
		if (So.contains("Windows")) {
			String in= ("ping -4 -n 10 www.uol.com.br");
			try {
				Process p=Runtime.getRuntime().exec(in);
				InputStream fluxo= p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer= new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null) {
					if(linha.contains("dia")) {
						String[] term= linha.split(",");
						String [] med= term[2].split("=");
						System.out.print(in+": media = "+ med[1]);
						linha= buffer.readLine();
					}
					else {
						linha=buffer.readLine();
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (So.contains("Linux")) {
			String in= ("ping -4 -c 10 www.uol.com.br");
			try {
				Process p=Runtime.getRuntime().exec(in);
				InputStream fluxo= p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer= new BufferedReader(leitor);
				String linha= buffer.readLine();
				while (linha!= null) {
					if(linha.contains("avg")) {
						String[] term= linha.split("=");
						String [] med= term[1].split("/");
						System.out.print(in+": media = "+ med[1]);
						linha= buffer.readLine();
					}
					else {
						linha=buffer.readLine();
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
