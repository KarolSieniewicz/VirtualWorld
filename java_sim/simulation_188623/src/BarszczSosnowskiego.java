import javax.swing.ImageIcon;

public class BarszczSosnowskiego extends Roslina{

    BarszczSosnowskiego(Swiat swiatOrganizmu, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, 10, x_pos, y_pos, wiek, czyCzeka);
        this.ikonka = new ImageIcon("zdjecia/barszczsosnowskiego.png");
        this.hexIkonka = new ImageIcon("zdjecia/barszczsosnowskiegoHex.png");
    }

    @Override
    char getZnaczek() {
        return '2'; 
    }

    String getNazwa() {
        return ("BarszczSosnowskiego");
    }

    void akcja() {
        wiek++;
        int currentPosition[] = {koordynaty[0],koordynaty[1]};

        //MIEJSCE NA ZABIJANIE
        for(int i = -1;i<=1;i++) {
            for(int j = -1;j<=1;j++) {
                if(inBounds(currentPosition[0]+i, currentPosition[1]+j) && (swiatOrganizmu.plansza[currentPosition[0]+i][currentPosition[1]+j] != null)){
                    if(swiatOrganizmu.plansza[currentPosition[0]+i][currentPosition[1]+j] instanceof Zwierze){                    
                        System.out.println("Barszcz Sosnowskiego zabija "+swiatOrganizmu.plansza[currentPosition[0]+i][currentPosition[1]+j].getNazwa()+" ("+(currentPosition[0]+i)+" , "+(currentPosition[1]+j)+").");
                        Komentator.DodajKomentarz("Barszcz Sosnowskiego zabija "+swiatOrganizmu.plansza[currentPosition[0]+i][currentPosition[1]+j].getNazwa()+" ("+(currentPosition[0]+i)+" , "+(currentPosition[1]+j)+").");
                        swiatOrganizmu.usunOrganizm(swiatOrganizmu.plansza[currentPosition[0]+i][currentPosition[1]+j]);                    
                        swiatOrganizmu.plansza[currentPosition[0]+i][currentPosition[1]+j] = null;
                    }
                }
            }
            
        }

        if(this.swiatOrganizmu instanceof Hex) {
            if(inBounds(currentPosition[0] + 1, currentPosition[1] + 1) && (swiatOrganizmu.plansza[currentPosition[0]+1][currentPosition[1]+1] != null && swiatOrganizmu.plansza[currentPosition[0]+1][currentPosition[1]+1] instanceof Zwierze)) {
                System.out.println("Barszcz Sosnowskiego zabija "+swiatOrganizmu.plansza[currentPosition[0]+1][currentPosition[1]+1].getNazwa()+" ("+(currentPosition[0]+1)+" , "+(currentPosition[1]+1)+").");
                Komentator.DodajKomentarz("Barszcz Sosnowskiego zabija "+swiatOrganizmu.plansza[currentPosition[0]+1][currentPosition[1]+1].getNazwa()+" ("+(currentPosition[0]+1)+" , "+(currentPosition[1]+1)+").");
                swiatOrganizmu.usunOrganizm(swiatOrganizmu.plansza[currentPosition[0]+1][currentPosition[1]+1]);                    
                swiatOrganizmu.plansza[currentPosition[0]+1][currentPosition[1]+1] = null;
            }
            if(inBounds(currentPosition[0] - 1, currentPosition[1] - 1) && (swiatOrganizmu.plansza[currentPosition[0]-1][currentPosition[1]-1] != null && swiatOrganizmu.plansza[currentPosition[0]-1][currentPosition[1]-1] instanceof Zwierze)) {
                System.out.println("Barszcz Sosnowskiego zabija "+swiatOrganizmu.plansza[currentPosition[0]-1][currentPosition[1]-1].getNazwa()+" ("+(currentPosition[0]-1)+" , "+(currentPosition[1]-1)+").");
                Komentator.DodajKomentarz("Barszcz Sosnowskiego zabija "+swiatOrganizmu.plansza[currentPosition[0]-1][currentPosition[1]-1].getNazwa()+" ("+(currentPosition[0]-1)+" , "+(currentPosition[1]-1)+").");
                swiatOrganizmu.usunOrganizm(swiatOrganizmu.plansza[currentPosition[0]-1][currentPosition[1]-1]);                    
                swiatOrganizmu.plansza[currentPosition[0]-1][currentPosition[1]-1] = null;
            }
        }

        super.akcja();
    }
    
    @Override
    Organizm klonowanie(int posX, int posY) {
        return new BarszczSosnowskiego(swiatOrganizmu, posX, posY, 1, true);
    }

}
