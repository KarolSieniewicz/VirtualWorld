import java.util.Random;

public class Roslina extends Organizm {
    Roslina(Swiat swiatOrganizmu, int sila, int x_pos, int y_pos, int wiek, boolean czyCzeka) {
        super(swiatOrganizmu, sila, 0, x_pos, y_pos, wiek, czyCzeka);
    }

    @Override
    String getNazwa() {
        return null;
    }

    @Override
    char getZnaczek() {
        return '_';
    }

    @Override
    void akcja() {
        if(this.getNazwa().equals("Mlecz")){
            wiek++;
        }

        Random rand = new Random();
        int randomTick = rand.nextInt(101);
        if(randomTick > 85) {
            System.out.println("Nastepuje rozsiew " + this.getNazwa() + " (" + koordynaty[0] + "," + koordynaty[1]+ ").");
            Komentator.DodajKomentarz("Nastepuje rozsiew " + this.getNazwa() + " (" + koordynaty[0] + "," + koordynaty[1]+ ").");
            int newPosition[] = znajdzKolejneWolnePole();

            if(newPosition[0] == -1 && newPosition[1] == -1){
                System.out.println("Niemozliwy rozsiew"+this.getNazwa()+ "brak dostepnych wolnych pol");
                Komentator.DodajKomentarz("Niemozliwy rozsiew"+this.getNazwa()+ "brak dostepnych wolnych pol");
                return;
            }
            else{
                klonowanie(newPosition[0], newPosition[1]);
            }
        }
    }

    @Override
    Organizm klonowanie(int posX, int posY) {
        // 
        return null;
    } 
}
