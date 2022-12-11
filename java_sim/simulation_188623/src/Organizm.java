import java.util.Random;
import javax.swing.*;

public abstract class Organizm {
   public enum kierunki {
       GORA,
       PRAWO,
       DOL,
       LEWO
   }

   protected Swiat swiatOrganizmu;
   protected int sila;
   protected int inicjatywa;
   protected int koordynaty[] = {-1,-1};
   protected int wiek;
   ImageIcon ikonka;
   ImageIcon hexIkonka;
   boolean czyCzeka;
   Organizm(Swiat swiatOrganizmu, int sila, int inicjatywa, int x_pos, int y_pos, int wiek, boolean czyCzeka)
   {
        this.swiatOrganizmu = swiatOrganizmu;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.wiek = wiek;
        this.czyCzeka = czyCzeka;
        koordynaty[0] = x_pos;
        koordynaty[1] = y_pos;

        if(x_pos == -1 && y_pos == -1) return;
        swiatOrganizmu.plansza[x_pos][y_pos] = this;
        swiatOrganizmu.dodajOrganizm(this);

        System.out.println("Pojawia sie "+ this.getNazwa());
        Komentator.DodajKomentarz("Pojawia sie "+ this.getNazwa());
   }

   boolean inBounds(int x, int y) {
    if(x >= this.swiatOrganizmu.getBoardX()) return false;
    if(y >= this.swiatOrganizmu.getBoardY()) return false;
    if(x<0) return false;
    if(y<0) return false;
    return true;
    }

    ImageIcon getIkonka() {
        return ikonka;
    }

    ImageIcon getHexIkonka() {
        return hexIkonka;
    }

   void komunikat(String wydarzenie, Organizm kolidujacyOrganizm) {
        Komentator.DodajKomentarz(wydarzenie+" - "+this.getNazwa()+" ("+ koordynaty[0]+ ","+ koordynaty[1]+"),"+kolidujacyOrganizm.getNazwa()+" ("+kolidujacyOrganizm.getX()+","+kolidujacyOrganizm.getY()+").");
        System.out.println(wydarzenie+" - "+this.getNazwa()+" ("+ koordynaty[0]+ ","+ koordynaty[1]+"),");
        System.out.println(kolidujacyOrganizm.getNazwa()+" ("+kolidujacyOrganizm.getX()+","+kolidujacyOrganizm.getY()+").");
   }

   int[] znajdzNowePole(){
       int returnPosition[] = new int[2];
       returnPosition[0] = koordynaty[0];
       returnPosition[1] = koordynaty[1];

       int field = 0;
       boolean validMove;
       int tryCounter = 0;
       do{
           validMove = false;    
           Random rand = new Random();
           field = rand.nextInt(6);
           switch(field)
           {
               case 0: //GORA
                if(returnPosition[1] == 0){
                    validMove = true;
                }
                else returnPosition[1]--;
               break;
               case 1: //PRAWO
                if(returnPosition[0] >= swiatOrganizmu.getBoardX()-1){
                    validMove = true;
                }
                else returnPosition[0]++;
               break;
               case 2: //DOL
                if(returnPosition[1] >= swiatOrganizmu.getBoardY()-1){
                    validMove = true;
                }
                else returnPosition[1]++;
               break;
               case 3: //LEWO
                if(returnPosition[0] == 0){
                    validMove = true;
                }
                else returnPosition[0]--;
               break;

               case 4:
               {

                if(!inBounds(returnPosition[0]-1, returnPosition[1]-1)){
                    validMove = true;
                }
                else if(this.swiatOrganizmu instanceof Hex){                     
                    returnPosition[0]--;
                    returnPosition[1]--;                                        
                }

               }                
               break;

               case 5:
               {
                if(!inBounds(returnPosition[0]+1, returnPosition[1]+1)){
                    validMove = true;
                }
                else if(this.swiatOrganizmu instanceof Hex){
                    returnPosition[0]++;
                    returnPosition[1]++;
                }
               }
               break;
                
           }
           tryCounter++;
       } while(validMove && tryCounter<10);
       if(validMove) return koordynaty;
       return returnPosition;
   }

   int[] znajdzKolejneWolnePole(){
    int returnPosition[] = {0,0};
    returnPosition[0] = koordynaty[0];
    returnPosition[1] = koordynaty[1];

    // GORA
    if((returnPosition[1] != 0) && (swiatOrganizmu.plansza[returnPosition[0]][returnPosition[1]-1] == null)){
        returnPosition[1]--;
        return returnPosition;
    }
    // PRAWO
    else if((returnPosition[0] != swiatOrganizmu.getBoardX() - 1) && (swiatOrganizmu.plansza[returnPosition[0]+1][returnPosition[1]] == null)){
        returnPosition[0]++;
        return returnPosition;
    }
    // DOL
    else if((returnPosition[1] != swiatOrganizmu.getBoardY() - 1) && (swiatOrganizmu.plansza[returnPosition[0]][returnPosition[1]+1] == null)){
        returnPosition[1]++;
        return returnPosition;
    }
    // LEWO
    else if((returnPosition[0] != 0) && (swiatOrganizmu.plansza[returnPosition[0]-1][returnPosition[1]] == null)){
        returnPosition[0]--;
        return returnPosition;
    }
    else if(inBounds(returnPosition[0]-1, returnPosition[1]-1) && this.swiatOrganizmu instanceof Hex) {
        returnPosition[0]--;
        returnPosition[1]--;
    }
    else if(inBounds(returnPosition[0]+1, returnPosition[1]+1) && this.swiatOrganizmu instanceof Hex) {
        returnPosition[0]++;
        returnPosition[1]++;
    }
    
    returnPosition[0] = -1;
    returnPosition[1] = -1;
    return returnPosition;
   }

   // metody z implementacja w zwierzeciu
   abstract String getNazwa();
   abstract char getZnaczek();
   abstract void akcja();
   abstract Organizm klonowanie(int posX, int posY);
   
   int getSila() {
       return sila;
   }

   int getInicjatywa()
   {
       return inicjatywa;
   }

   void setX(int x)
   {
       koordynaty[0] = x;
   }

   void setY(int y)
   {
       koordynaty[1] = y;
   }

   int getX()
   {
       return koordynaty[0];
   }

   int getY()
   {
       return koordynaty[1];
   }

   int getWiek()
   {
       return wiek;
   }

   boolean getCzyCzeka()
   {
       return czyCzeka;
   }

   boolean odbicie(Organizm organizm)
   {
        return false;
   }
}
