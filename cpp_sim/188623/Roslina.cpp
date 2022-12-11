#include "Roslina.h"

Roslina::Roslina(Swiat *swiatOrganizmu, int sila, int posX, int posY, int age, bool czyCzeka)
: Organizm(swiatOrganizmu, sila, 0, posX, posY, age, czyCzeka){
};

void Roslina::akcja(){
    
    
    if(this->getNazwa().compare("Mlecz")){
        wiek++;
    }
    
    int randomTick = rand() % 100 + 1;
    if(randomTick > 85){
        std::cout << "Nastepuje rozsiew " << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << ").\n";
        
        int *newPosition = znajdzKolejneWolnePole();

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

Roslina::~Roslina(){

};