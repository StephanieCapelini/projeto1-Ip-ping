package view;

import javax.swing.JOptionPane;

import controller.ModController;

public class Menu {

	public static void main(String[] args) {
		
		ModController mod=new ModController();
		String So= System.getProperty("os.name");
		int X= 0;
		while (X==0) {
			X= Integer.parseInt(JOptionPane.showInputDialog("escolha uma opcao"
					+"\n1-Ip"
					+ "\n2-Ping"));
		}
		if (X==1) {
			mod.IP(So);
		}
		else if (X==2) {
			mod.ConsultaPing(So);
		}
	}

}
