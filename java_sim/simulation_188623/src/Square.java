import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Square extends Swiat implements ActionListener,KeyListener{

    private ImageIcon pustePoleIcon = new ImageIcon("zdjecia/empty.png");
    


    void initGraphics(){
        frame.setSize(this.getBoardX()*iconWidth+25 + comentatorWidth,this.getBoardY()*iconHeight+50);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.setTitle("Square Mode");
        frame.setIconImage(new ImageIcon("zdjecia/miniature.png").getImage()); 
        
        planszaGraficznie = new JLabel[this.getBoardX()][this.getBoardY()];
        przyciskiDodawania = new JButton[this.getBoardX()][this.getBoardY()];
        komentator = new KomentatorGraphics();

        //inicjalizacja graficznej reprezentacji planszy
        for(int i = 0, y = 0; i < this.getBoardY();i++, y += iconHeight) {
            for(int j = 0, x = 0; j < this.getBoardX();j++, x += iconWidth) {
                planszaGraficznie[j][i] = new JLabel();
                przyciskiDodawania[j][i] = new JButton();
                przyciskiDodawania[j][i].setOpaque(false);
                przyciskiDodawania[j][i].setContentAreaFilled(false);
                przyciskiDodawania[j][i].setBorderPainted(false); 
                przyciskiDodawania[j][i].addActionListener(this);
                przyciskiDodawania[j][i].setFocusable(false);
                planszaGraficznie[j][i].setBounds(x, y, iconWidth, iconHeight);              
                przyciskiDodawania[j][i].setBounds(x,y,iconWidth,iconHeight);
                frame.add(przyciskiDodawania[j][i]);
                frame.add(planszaGraficznie[j][i]);
            }
        }
    }

    
    Square(int BoardX, int BoardY) {
        super(BoardX, BoardY);

        initGraphics();
        this.rysowanie();

    }

    void rysowanie() {
        for(int i = 0; i<this.getBoardY();i++) {
            for(int j = 0; j <this.getBoardX();j++) {
                if(this.plansza[j][i] == null) planszaGraficznie[j][i].setIcon(pustePoleIcon);
                else planszaGraficznie[j][i].setIcon(this.plansza[j][i].getIkonka());
            }
        }
        komentator.refresh();
    }

    Square(String fileName) throws FileNotFoundException, IOException {
        super(fileName);
        this.initGraphics();
        this.rysowanie();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < this.getBoardY(); i++) {
            for(int j = 0; j < this.getBoardX(); j++) {
                if(e.getSource() == przyciskiDodawania[j][i] && plansza[j][i] == null) {
                    String toAdd = JOptionPane.showInputDialog(frame,"Podaj zwierze do dodania", "Antylopa");
                    if(toAdd != null) {
                        if(toAdd.equals("Antylopa")){
                            ustawNaKoordynatach(new Antylopa(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("WilczaJagoda") ){
                            ustawNaKoordynatach(new WilczaJagoda(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("Mlecz")){
                            ustawNaKoordynatach(new Mlecz(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("Lis")){
                            ustawNaKoordynatach(new Lis(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("Trawa")){
                            ustawNaKoordynatach(new Trawa(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("Guarana")){
                            ustawNaKoordynatach(new Guarana(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("BarszczSosnowskiego")){
                            ustawNaKoordynatach(new BarszczSosnowskiego(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("Owca")){
                            ustawNaKoordynatach(new Owca(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("Zolw")){
                            ustawNaKoordynatach(new Zolw(this, -1, -1, 1, true), j, i);
                        }
                        else if(toAdd.equals("Wilk")){
                            ustawNaKoordynatach(new Wilk(this, -1, -1, 1, true), j, i);
                        }
                    }
                }
                this.rysowanie();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyCode() == KeyEvent.VK_SPACE) {
            try {
                Komentator.reset();
                this.wykonajTure();
                this.rysowanie();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if(k.getKeyCode() == KeyEvent.VK_UP) {
            this.setPlayerMove(0);
            System.out.println("Aktualny ruch: gora");
            moveDisplay = "Aktualny ruch: gora";
            komentator.refresh();
        }
        if(k.getKeyCode() == KeyEvent.VK_DOWN) {
            this.setPlayerMove(1);
            System.out.println("Aktualny ruch: dol");
            moveDisplay = "Aktualny ruch: dol";
            komentator.refresh();
        }
        if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.setPlayerMove(2);
            System.out.println("Aktualny ruch: prawo");
            moveDisplay = "Aktualny ruch: prawo";
            komentator.refresh();
        }
        if(k.getKeyCode() == KeyEvent.VK_LEFT) {
            this.setPlayerMove(3);
            System.out.println("Aktualny ruch: lewo");
            moveDisplay = "Aktualny ruch: lewo";
            komentator.refresh();
        }
        if(k.getKeyCode() == KeyEvent.VK_M) {
            this.setPlayerMove(8);
            System.out.println("Aktualny ruch: TARCZA ALZURA");
            moveDisplay = "Aktualny ruch: TARCZA ALZURA";
            komentator.refresh();
        }
        if(k.getKeyCode() == KeyEvent.VK_N) {
            try {
                this.zapisz("zapis.txt");             
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            komentator.refresh();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
