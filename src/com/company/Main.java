package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Timer;


public class Main {

    public static void main(String[] args) {
		// Creation des donnée a affichés
		Object[] data = new Object[]{"", "Menu Carnivore", "Menu Marin", "Menu Herbivore", };
		String[] carnivore = new String[]{"Verrine au foie gras","Steak 380g & PdT sauce chasseur", "Tartelette chocolat"};
		String[] marin = new String[]{"Trio de la mer (saint jacques, moules, saumon fumé) ", "Saumon & Riz sauce bernaise", "Tiramisu Coco" };
		String[] herbivore = new String[]{"Salade de choux","Steak Végétarien & Julienne de 3 legumes", "Crumble Pomme Fruits Rouges"};


		// instanciation des objets
		JFrame window = new JFrame("Facturation");
		JPanel panel = new JPanel();
		JButton valide = new JButton("Valider");

		Timer timer = new Timer();

		JLabel displayPrice = new JLabel();
		JLabel displayText = new JLabel("<html><h1>Bienvenue</h1></html>");
		JTextArea displayList = new JTextArea();
		displayList.setEditable(false);
		JComboBox<String> liste = new JComboBox(data);

		JPanel validationPanel = new JPanel();
		JLabel verification = new JLabel();

		JButton confirmation = new JButton("Confirmer");
		JButton retour = new JButton("Retour");


		// variables
		int width = 450;
		int height = 300;
		// Configuration des panel et de la fenetre
		window.setResizable(false);
		panel.setLayout(null);
		panel.setBackground(Color.cyan);
		validationPanel.setLayout(null);
		validationPanel.setBackground(Color.cyan);

	// Taille et emplacement des differents elements de l'application
		valide.setBounds(width/3, 240, 150, 20);
		liste.setBounds(width/4, 10, 200, 25);
		displayText.setBounds(100, 40, 480, 20);
		displayList.setBounds(10, 75, 420, 150);
		displayList.setVisible(false);

		verification.setBounds(width/4, height/3, width, 20);

		confirmation.setBounds(width/3, 240, 100, 20);
		retour.setBounds(250, 240, 100, 20);

	// Ajout des elements aux panels
		panel.add(valide);
		panel.add(displayPrice);
		panel.add(liste);
		panel.add(displayText);
		panel.add(displayList);

		validationPanel.add(confirmation);
		validationPanel.add(verification);
		validationPanel.add(retour);

// Definition de la taille de la fenetre
		window.setSize(width,height);

	// Valider son menu
		valide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = (String) liste.getSelectedItem();

				if( text != "") {

					window.setContentPane(validationPanel);
					window.invalidate();
					window.validate();
				}else{
					displayText.setText("<html><h2 color='red'>Veuillez selectionner un menu !</h2></html>");
				}
			}
		});
		// Bouton permettant un retour en arriere pour changer de menu
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setContentPane(panel);
				window.invalidate();
				window.validate();
			}
		});
// Confirmation du choix du menu et ticket avec choix au format txt est généré
		confirmation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String menuChoice = liste.getSelectedItem().toString();
					LocalDate myDate = LocalDate.now();
					// Create file
					FileWriter fstream = new FileWriter(System.currentTimeMillis() + "_ticket"+ myDate + ".txt");
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("le : " + myDate + "\n" + " Votre commande est bien réservé \n"+ "Vous avez choisi : " + menuChoice + "\n" + " Merci d'avoir utilisé nos services" );
					//Close the output stream
					out.close();
				}catch (Exception ex){//Catch exception if any
					System.err.println("Erreur: " + ex.getMessage());
				}
				confirmation.setVisible(false);
				retour.setVisible(false);

				verification.setText("<html> <h2 color='green'>Votre choix est confirmé !</h2> </html>");

				new java.util.Timer().schedule(
						new java.util.TimerTask() {
							@Override
							public void run() {
								window.dispose();
							}
						},
						3000
				);

			}
		});

// Affichage des menu au changement dans la liste
		liste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				e.getSource();
				String text = (String) liste.getSelectedItem();

				if( text != ""){
					String saveListe = "";
					displayList.setVisible(true);
					displayText.setText("<html><h1>" + text + "</h1></html>");
					switch (text){

						case "Menu Carnivore":
							for(int i = 0; i< carnivore.length; i++){

								saveListe += carnivore[i] + "\r\n";
							}
							displayList.setText(saveListe);
							verification.setText("<html><h3 color='red'>Vous avez choisi le menu Carnivore ?</h3> </html>");
							break;
						case "Menu Herbivore":
							for(int i = 0; i< herbivore.length; i++){

								saveListe += herbivore[i] + "\r\n";


							}
							displayList.setText(saveListe);
							verification.setText("<html><h3 color='red'>Vous avez choisi le menu Herbivore ?</h3> </html>");
							break;
						case "Menu Marin":
							for(int i = 0; i< marin.length; i++){

								saveListe += marin[i] + "\r\n";


							}
							displayList.setText(saveListe);
							verification.setText("<html><h3 color='red'>Vous avez choisi le menu Marin ?</h3> </html>");

							break;


					}

				}
			}
		});
		// affichage de la fenetre est visible
		window.setContentPane(panel);
		window.setVisible(true);


    }

}
