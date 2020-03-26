package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {



		Object[] data = new Object[]{"", "Menu Carnivore", "Menu Marin", "Menu Herbivore", };
		String[] carnivore = new String[]{"Verrine au foie gras","Steak 380g & PdT", "Tartelette chocolat"};
		String[] marin = new String[]{"Trio de la mer", "Saumon & Riz", "Tiramisu Coco" };
		String[] herbivore = new String[]{"Salade de choux","Steak Végétarien & legumes", "Crumble Pomme"};


		// instanciation des objets
		JFrame window = new JFrame("Facturation");
		JPanel panel = new JPanel();
		JButton valide = new JButton("Valider");

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


		panel.setLayout(null);
		validationPanel.setLayout(null);

		valide.setBounds(width/3, 240, 150, 20);
		displayPrice.setBounds(0, 0, 150, 20);
		liste.setBounds(width/4, 10, 200, 25);
		displayText.setBounds(100, 40, 480, 20);
		displayList.setBounds(150, 75, 150, 100);

		verification.setBounds(width/3, height/3, width, 20);

		confirmation.setBounds(width/3, 240, 100, 20);
		retour.setBounds(250, 240, 100, 20);


		panel.add(valide);
		panel.add(displayPrice);
		panel.add(liste);
		panel.add(displayText);
		panel.add(displayList);

		validationPanel.add(confirmation);
		validationPanel.add(verification);
		validationPanel.add(retour);



		window.setSize(width,height);

		valide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setContentPane(validationPanel);
				window.invalidate();
				window.validate();
			}
		});
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setContentPane(panel);
				window.invalidate();
				window.validate();
			}
		});

		confirmation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String menuChoice = liste.getSelectedItem().toString();
					LocalDate myDate = LocalDate.now();
					// Create file
					FileWriter fstream = new FileWriter(System.currentTimeMillis() + "_ticket"+ myDate + ".txt");
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("le : " + myDate + "\n" + "Vous avez choisi : " + menuChoice );
					//Close the output stream
					out.close();
				}catch (Exception ex){//Catch exception if any
					System.err.println("Error: " + ex.getMessage());
				}

				verification.setText("<html> <h2 color='green'>Votre choix est confirmé !</h2> </html>");

			}
		});


		liste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				e.getSource();
				String text = (String) liste.getSelectedItem();

				if( text != ""){
					String saveListe = "";

					displayText.setText("<html><h1>" + text + "</h1></html>");
					switch (text){

						case "Menu Carnivore":
							for(int i = 0; i< carnivore.length; i++){

								saveListe += carnivore[i] + "\r\n";
							}
							displayList.setText(saveListe);
							verification.setText("Vous avez choisi le menu Carnivore ?");
							break;
						case "Menu Herbivore":
							for(int i = 0; i< herbivore.length; i++){

								saveListe += herbivore[i] + "\r\n";


							}
							displayList.setText(saveListe);
							verification.setText("Vous avez choisi le menu Herbivore ?");
							break;
						case "Menu Marin":
							for(int i = 0; i< marin.length; i++){

								saveListe += marin[i] + "\r\n";


							}
							displayList.setText(saveListe);
							verification.setText("Vous avez choisi le menu Marin ?");

							break;


					}

				}
			}
		});


		// affichage est visible
		window.setContentPane(panel);
		window.setVisible(true);







    }

}
