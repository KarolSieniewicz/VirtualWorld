#include "BarszczSosnowskiego.h"
#include "Zwierze.h"

BarszczSosnowskiego::BarszczSosnowskiego(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka)
: Roslina(swiatOrganizmu, 10, posX, posY, age, czyCzeka){
};

void BarszczSosnowskiego::rysowanie(){
    printf("&");
};

string BarszczSosnowskiego::getNazwa(){
    return ("BaszczSosnowskiego");
};

void BarszczSosnowskiego::akcja(){

    wiek++;

    int currentPosition[2];
 
    currentPosition[0] = koordynaty[0];
    currentPosition[1] = koordynaty[1];
    

    if((currentPosition[1] != 0) && (swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]-1] != NULL)){

        if(Zwierze *animalOrganism = dynamic_cast<Zwierze*>(swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]-1])){

            std::cout << "Barszcz Sosnowskiego akcja : " << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << "), " << swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]-1]->getNazwa();
            std::cout << " (" << swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]-1]->getX() << "," << swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]-1]->getY() << ").\n";

            swiatOrganizmu->podgladOrganizmow->usunOrganizm(swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]-1]);
            swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]-1] = NULL;
        }
    }

    if((currentPosition[0] != swiatOrganizmu->getBoardX() - 1) && (swiatOrganizmu->plansza[currentPosition[0]+1][currentPosition[1]] != NULL)){        

        if(Zwierze* animalOrganism = dynamic_cast<Zwierze*>(swiatOrganizmu->plansza[currentPosition[0]+1][currentPosition[1]])){
        
            std::cout << "Barszcz Sosnowskiego akcja : " << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << "), " << swiatOrganizmu->plansza[currentPosition[0]+1][currentPosition[1]]->getNazwa();
            std::cout << " (" << swiatOrganizmu->plansza[currentPosition[0]+1][currentPosition[1]]->getX() << "," << swiatOrganizmu->plansza[currentPosition[0]+1][currentPosition[1]]->getY() << ").\n";

            swiatOrganizmu->podgladOrganizmow->usunOrganizm(swiatOrganizmu->plansza[currentPosition[0]+1][currentPosition[1]]);
            swiatOrganizmu->plansza[currentPosition[0]+1][currentPosition[1]] = NULL;
        }
    }

    if((currentPosition[1] != swiatOrganizmu->getBoardY() - 1) && (swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]+1] != NULL)){

        if(Zwierze* animalOrganism = dynamic_cast<Zwierze*>(swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]+1])){

            std::cout << "Barszcz Sosnowskiego akcja : " << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << "), " << swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]+1]->getNazwa();
            std::cout << " (" << swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]+1]->getX() << "," << swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]+1]->getY() << ").\n";

            swiatOrganizmu->podgladOrganizmow->usunOrganizm(swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]+1]);
            swiatOrganizmu->plansza[currentPosition[0]][currentPosition[1]+1] = NULL;
        }
    }

    if((currentPosition[0] != 0) && (swiatOrganizmu->plansza[currentPosition[0]-1][currentPosition[1]] != NULL)){
  
        if(Zwierze* animalOrganism = dynamic_cast<Zwierze*>(swiatOrganizmu->plansza[currentPosition[0]-1][currentPosition[1]])){

            std::cout << "Barszcz Sosnowskiego akcja : " << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << "), " << swiatOrganizmu->plansza[currentPosition[0]-1][currentPosition[1]]->getNazwa();
            std::cout << " (" << swiatOrganizmu->plansza[currentPosition[0]-1][currentPosition[1]]->getX() << "," << swiatOrganizmu->plansza[currentPosition[0]-1][currentPosition[1]]->getY() << ").\n";
 
            swiatOrganizmu->podgladOrganizmow->usunOrganizm(swiatOrganizmu->plansza[currentPosition[0]-1][currentPosition[1]]);
            swiatOrganizmu->plansza[currentPosition[0]-1][currentPosition[1]] = NULL;
        }
    }

    int randomTick = rand() % 100 + 1;
    if(randomTick > 85){
        std::cout << "Nastepuje rozsiew " << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << ").\n";

        int *newPosition =  znajdzKolejneWolnePole();

        if(newPosition[0] == -1 && newPosition[1] == -1){
            printf("Niemozliwy rozsiew, brak dostepnych wolnych pol\n");
            delete newPosition;
            return;
        }
        else{

            klonowanie(newPosition[0], newPosition[1]);
        }
    }
};

Organizm *BarszczSosnowskiego::klonowanie(int posX, int posY){
    return new BarszczSosnowskiego(swiatOrganizmu, posX, posY, 1, true);
};

BarszczSosnowskiego::~BarszczSosnowskiego(){
};