import java.awt.event.*;

import java.io.IOException;

import javax.swing.*;



public class Startup implements ActionListener{
        
    //do obslugi gry
    int BoardX;
    int BoardY;

    //wymiary
    int buttonWidth = 400;
    int buttonHeight = 100;
    int mainFrameWidth = 500;
    int mainFrameHeight = 500;
    int offset = 100;


    //obiekty Swing
    JFrame mainFrame = new JFrame();
    JButton squareMode = new JButton();
    JButton hexMode = new JButton();
    JButton loadSquareGame = new JButton();
    JButton loadHexGame = new JButton();
    
        
    Startup() {
        squareMode.setVisible(true);
        squareMode.setText("Square Mode");
        squareMode.setBounds(mainFrameWidth/2 - buttonWidth/2, 0, buttonWidth, buttonHeight);
        squareMode.addActionListener(this);
        
        hexMode.setVisible(true);
        hexMode.setText("Hex Mode");
        hexMode.addActionListener(this);
        hexMode.setBounds(mainFrameWidth/2 - buttonWidth/2, buttonHeight, buttonWidth, buttonHeight);
        
        loadSquareGame.setVisible(true);
        loadSquareGame.setText("Load Square Game");
        loadSquareGame.addActionListener(this);  
        loadSquareGame.setBounds(mainFrameWidth/2 - buttonWidth/2, 2*buttonHeight, buttonWidth, buttonHeight);

        loadHexGame.setVisible(true);
        loadHexGame.setText("Load Hex Game");
        loadHexGame.addActionListener(this);  
        loadHexGame.setBounds(mainFrameWidth/2 - buttonWidth/2, 3*buttonHeight, buttonWidth, buttonHeight);
        
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(mainFrameWidth,mainFrameHeight);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.add(squareMode);
        mainFrame.add(hexMode);
        mainFrame.add(loadSquareGame);
        mainFrame.add(loadHexGame);
        mainFrame.setTitle("Java Simulation 188623");
        mainFrame.setIconImage(new ImageIcon("zdjecia/miniature.png").getImage()); 
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == squareMode) {
            Komentator.reset();
            BoardX = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,
                    "Podaj szerokosc swiata", "10"));
            BoardY = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,
                    "Podaj wysokosc swiata", "10"));
            Square swiat = new Square(BoardX,BoardY);
            swiat.rysowanie();
        }

        if(e.getSource() == hexMode) {
            Komentator.reset();
            BoardX = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,
                    "Podaj szerokosc swiata", "10"));
            BoardY = Integer.parseInt(JOptionPane.showInputDialog(mainFrame,
                    "Podaj wysokosc swiata", "10"));
            Hex swiat = new Hex(BoardX,BoardY);
            swiat.rysowanie();
        }
        
        if(e.getSource() == loadSquareGame) {
            Komentator.reset();
            Square swiat;
            try {
                swiat = new Square("zapis.txt");
                swiat.rysowanie();
            } catch (IOException e1) {                
                e1.printStackTrace();
            }
            
        }

        if(e.getSource() == loadHexGame) {
            Komentator.reset();
            Hex swiat;
            try {
                swiat = new Hex("zapis.txt");
                swiat.rysowanie();
            } catch (IOException e1) {                
                e1.printStackTrace();
            }
            
        }
    }
}
