import java.util.Random;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public abstract class Swiat {
    protected int BoardX;
    protected int BoardY;
    private int aktualnaTura = 1;
    private int resetUmiejetnosciCzlowieka;
    private int aktualnaIloscOrganizmow = 0;
    private int maksIloscOrganizmow;
    protected Organizm[][] plansza;    
    protected PodgladOrganizmow podglad = null;
    protected int playerMove = 100;
    protected String moveDisplay = "(...Wprowadz ruch...)";
    private int playerX, playerY;
    protected final int iconWidth = 100;
    protected final int iconHeight = 100;
    protected final int comentatorWidth = 400;
    protected JFrame frame = new JFrame();      
    protected JLabel[][] planszaGraficznie;
    protected JButton[][] przyciskiDodawania;
    protected KomentatorGraphics komentator = null;
    
    protected class KomentatorGraphics extends JPanel {
        private String tekst;
        
        private JTextArea textArea;

        public KomentatorGraphics() {
            super();
            int offset = 0;
            if(frame.getWidth()==BoardX*iconWidth+25 + iconWidth/2 + comentatorWidth) offset = iconWidth/2;
            setBounds(BoardX*iconWidth+offset,0, comentatorWidth, BoardY*iconWidth);
            textArea = new JTextArea(tekst);
            textArea.setEditable(false);
            setLayout(new CardLayout());

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setMargin(new Insets(5, 5, 5, 5));
            textArea.setFocusable(false);
            JScrollPane sp = new JScrollPane(textArea);
            add(sp);
            setFocusable(false);
            frame.add(this);
        }

        public void refresh() {
            String instruction = moveDisplay + "\n\n\n\n\nStrzalki - ruch\n n - save \n m - TARCZA ALZURA \n spacja - zatwierdz ruch/kolejna tura\n\n\n\n\n";
            tekst = instruction + Komentator.getTekst();
            textArea.setText(tekst);
        }
    }

    void setPlayerX(int x) {
        playerX = x;
    }

    void setPlayerY(int y) {
        playerY = y;
    }

    int getPlayerX() {
        return playerX;
    }

    int getPlayerY() {
        return playerY;
    }

    void dodajOrganizm(Organizm organizm) {
        if(aktualnaIloscOrganizmow >= maksIloscOrganizmow) {
            System.out.println("Nie mozna dodac wiecej organizmow");
            return;
        }

        PodgladOrganizmow newEntity = new PodgladOrganizmow();
        newEntity.aktualnyOrganizm = organizm;
        newEntity.next = null;

        PodgladOrganizmow current = podglad;
        if(podglad == null) {
            podglad = newEntity;
            newEntity.next = null;
            aktualnaIloscOrganizmow++;
            return;
        }
        while(current.next != null) {
            if(organizm.getInicjatywa() > current.next.aktualnyOrganizm.getInicjatywa()) {
                break;
            }
            else if(organizm.getInicjatywa() == current.aktualnyOrganizm.getInicjatywa()) {
                if(organizm.getWiek() >= current.aktualnyOrganizm.getWiek()) {
                    break;
                }
            }
            current = current.next;
        }

        newEntity.next = current.next;
        current.next = newEntity;
        aktualnaIloscOrganizmow++;

    }

    void setPlayerMove(int t) {
        this.playerMove = t;
    }

    void usunOrganizm(Organizm organizm) {
        PodgladOrganizmow current = podglad;
        if(aktualnaIloscOrganizmow == 1) podglad = null; //teoretycznie niemozliwe
        if(podglad.aktualnyOrganizm == organizm) {
            podglad = podglad.next;
            aktualnaIloscOrganizmow--;
            return;
        }
        for(int i = 0; i < aktualnaIloscOrganizmow; i++) {
            if(current.next.aktualnyOrganizm == organizm) {
                break;
            }
            if(i == aktualnaIloscOrganizmow - 1) {
                System.out.println("Nie ma takiego organizmu");
                return;
            }
            current = current.next;
        }

        current.next = current.next.next;
        aktualnaIloscOrganizmow--;
    }

    Swiat(int N, int M) {
        BoardX = N;
        BoardY = M;

        System.out.println("Inicjalizacja swiata (" + BoardX +" , " + BoardY + ")");
        Komentator.DodajKomentarz("Inicjalizacja swiata (" + BoardX +" , " + BoardY + ")");

        plansza = new Organizm[BoardX][BoardY];
        for(int i = 0; i < BoardY ; i++) {
            for(int j = 0; j < BoardX; j++) {
                plansza[j][i] = null;
            }
        }

        maksIloscOrganizmow = BoardX*BoardY;

        ustawLosowo(new Czlowiek(this, -1, -1, 20));
        resetUmiejetnosciCzlowieka = 0;        

        ustawLosowo(new Owca(this, -1, -1, 14, true));
        ustawLosowo(new Owca(this, -1, -1, 16, true));
        
        ustawLosowo(new Trawa(this, -1, -1, 5, true));
        
        ustawLosowo(new Mlecz(this, -1, -1, 2, true));    

        ustawLosowo(new Wilk(this, -1, -1, 11, false));
        ustawLosowo(new Wilk(this, -1, -1, 10, false));

        ustawLosowo(new Guarana(this, -1, -1, 4, true));
        ustawLosowo(new Guarana(this, -1, -1, 3, true));
        
        ustawLosowo(new WilczaJagoda(this, -1, -1, 2, true));

        ustawLosowo(new BarszczSosnowskiego(this, -1, -1, 1, true));

        ustawLosowo(new Lis(this, -1, -1, 5, true));
        ustawLosowo(new Lis(this, -1, -1, 9, true));

        ustawLosowo(new Zolw(this, -1, -1, 22, true));
        ustawLosowo(new Zolw(this, -1, -1, 17, true));

        ustawLosowo(new Antylopa(this, -1, -1, 11, true));
        ustawLosowo(new Antylopa(this, -1, -1, 7, true));
        playerMove = 100;
    }

    void zapisz(String fileName) throws IOException {
        try{            
            File file = new File(fileName);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            PodgladOrganizmow current = podglad;
            String toWrite;
            toWrite = "" + BoardX + " " + BoardY + " " + getResetUmiejetnosciCzlowieka() +" "+ aktualnaTura;
            writer.write(toWrite);
            writer.newLine();
            while(current!=null) {
                toWrite = current.aktualnyOrganizm.getNazwa() + " " + current.aktualnyOrganizm.getX() + " " + current.aktualnyOrganizm.getY() + " " + current.aktualnyOrganizm.getWiek() + " " + current.aktualnyOrganizm.getCzyCzeka();
                writer.write(toWrite);
                writer.newLine();
                current = current.next;
            }
            writer.close();
            System.out.println("Zapisano stan gry.");
            moveDisplay = "Zapisano stan gry.";            
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    Swiat(String fileName) throws FileNotFoundException, IOException {
        System.out.println("Zapisany Swiat wczytany");
        Komentator.DodajKomentarz("Zapisany Swiat wczytany");
        aktualnaIloscOrganizmow = 0;
        String word;
        int oldN, oldM, oldAge;
        boolean oldBool;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lineCount = 0;
        String line = reader.readLine();
        playerMove = 100;
        while(line!=null){
            
            String[] elements = line.split(" ");
            if(lineCount == 0) {
                oldN = Integer.parseInt(elements[0]);
                oldM = Integer.parseInt(elements[1]);
                this.ustawResetUmiejetnosciCzlowieka(Integer.parseInt(elements[2]));
                aktualnaTura = Integer.parseInt(elements[3]);
                BoardX = oldN;
                BoardY = oldM;
                plansza = new Organizm[BoardX][BoardY];
                for(int i = 0; i < BoardY ; i++) {
                    for(int j = 0; j < BoardX; j++) {
                        plansza[j][i] = null;
                    }
                }
                maksIloscOrganizmow = BoardX*BoardY;
                lineCount++;
                line = reader.readLine();
                continue;
            }
            
            word = elements[0];
            
            oldN = Integer.parseInt(elements[1]);
            oldM = Integer.parseInt(elements[2]);
            oldAge = Integer.parseInt(elements[3]);
            oldBool = Boolean.parseBoolean(elements[4]);
            
            if(word.equals("Antylopa")){
                ustawNaKoordynatach(new Antylopa(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("WilczaJagoda") ){
                ustawNaKoordynatach(new WilczaJagoda(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("Mlecz")){
                ustawNaKoordynatach(new Mlecz(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("Lis")){
                ustawNaKoordynatach(new Lis(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("Trawa")){
                ustawNaKoordynatach(new Trawa(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("Guarana")){
                ustawNaKoordynatach(new Guarana(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("BarszczSosnowskiego")){
                ustawNaKoordynatach(new BarszczSosnowskiego(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("Czlowiek")){
                ustawNaKoordynatach(new Czlowiek(this, -1, -1, oldAge), oldN, oldM);
                playerX = oldN;
                playerY = oldM;              
            }
            else if(word.equals("Owca")){
                ustawNaKoordynatach(new Owca(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("Zolw")){
                ustawNaKoordynatach(new Zolw(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            else if(word.equals("Wilk")){
                ustawNaKoordynatach(new Wilk(this, -1, -1, oldAge, oldBool), oldN, oldM);
            }
            line = reader.readLine();
            lineCount++;
        }
        reader.close();
    }

    int getBoardX() {
        return BoardX;
    }
    
    int getBoardY() {
        return BoardY;
    }
    
    int getResetUmiejetnosciCzlowieka() {
        return resetUmiejetnosciCzlowieka;
    }

    int getAktualnaTura() {
        return aktualnaTura;
    }

    void ustawResetUmiejetnosciCzlowieka(int i) {
        resetUmiejetnosciCzlowieka = i;
    }

    void ustawAktualnaTure(int tura) {
        aktualnaTura = tura;
    }

    void ustawLosowo(Organizm organizm) {
        int newPosition[] = {0,0};
        Random rand = new Random();
        newPosition[0] = rand.nextInt(getBoardX());
        newPosition[1] = rand.nextInt(getBoardY());
        if(plansza[newPosition[0]][newPosition[1]] == null)
        {
            organizm.setX(newPosition[0]);
            organizm.setY(newPosition[1]);
            plansza[newPosition[0]][newPosition[1]] = organizm;
            dodajOrganizm(organizm);
            System.out.println("Pojawia sie " + organizm.getNazwa());
            Komentator.DodajKomentarz("Pojawia sie " + organizm.getNazwa());
        }
    }

    abstract void rysowanie();

    void ustawNaKoordynatach(Organizm entity, int x, int y) {
        entity.setX(x);
        entity.setY(y);
        plansza[x][y] = entity;
        dodajOrganizm(entity);
    }

    

    void wykonajTure() throws IOException {
        
        System.out.println("Wykonywanie tury nr "+aktualnaTura);
        Komentator.DodajKomentarz("Wykonywanie tury nr "+aktualnaTura);

        if(resetUmiejetnosciCzlowieka>0) {
            resetUmiejetnosciCzlowieka--;
        }

        PodgladOrganizmow current = podglad;
        while(current!=null) {
            if(current.aktualnyOrganizm.czyCzeka) current.aktualnyOrganizm.czyCzeka = false;
            else current.aktualnyOrganizm.akcja();
            current = current.next;
        }
        aktualnaTura++;
        playerMove = 100;        
    }
}
