#include "Zwierze.h"
#include "BarszczSosnowskiego.h"
#include "Guarana.h"
#include "Czlowiek.h"
#include <typeinfo>

Zwierze::Zwierze(Swiat *swiatOrganizmu, int sila, int initiative, int posX, int posY, int age, bool czyCzeka)
: Organizm(swiatOrganizmu, sila, initiative, posX, posY, age, czyCzeka){
};

bool Zwierze::czyTenSamGatunek(Organizm &entity){
    return (this->getNazwa() == entity.getNazwa());
};

void Zwierze::rozmnazanie(){

    int *newPosition = znajdzKolejneWolnePole();

    if(newPosition[0] == -1 && newPosition[1] == -1){
        std::cout << "Rozmnazanie " << this->getNazwa() << " nieudane, brak dostepnych pol\n";
        return;
    }

    klonowanie(newPosition[0], newPosition[1]);
    delete newPosition;
};

void Zwierze::akcja(){

    //szansa zolwia na ominiecie ruchu
    if(this->getNazwa().compare("Zolw")){
        wiek++;
    }
    int *newPosition = znajdzNowePole();
    
    if(swiatOrganizmu->plansza[newPosition[0]][newPosition[1]] == NULL){
        swiatOrganizmu->plansza[newPosition[0]][newPosition[1]] = this;
        swiatOrganizmu->plansza[koordynaty[0]][koordynaty[1]] = NULL;
        koordynaty[0] = newPosition[0];
        koordynaty[1] = newPosition[1];
    }
    else{
    
        kolizja(swiatOrganizmu->plansza[newPosition[0]][newPosition[1]]);
    }
    delete newPosition;
};

void Zwierze::walka(Organizm* oponent) {
    this->komunikat("[Walka]", oponent);

    if (sila >= oponent->getSila() && typeid(*oponent).hash_code() != typeid(BarszczSosnowskiego).hash_code()) {

        std::cout << "Zwyciestwo: " << this->getNazwa() << "\n";

        if (typeid(*oponent).hash_code() == typeid(Guarana).hash_code()) {
            std::cout << this->getNazwa() << " (" << koordynaty[0] << "," << koordynaty[1] << ") sila zwiekszona\n";
            sila += 3;
        }

        swiatOrganizmu->plansza[koordynaty[0]][koordynaty[1]] = NULL;
        koordynaty[0] = oponent->getX();
        koordynaty[1] = oponent->getY();
        swiatOrganizmu->plansza[koordynaty[0]][koordynaty[1]] = this;

        swiatOrganizmu->podgladOrganizmow->usunOrganizm(oponent);
    }
    else {
        std::cout << "Przegrana: " << this->getNazwa() << "\n";
        swiatOrganizmu->plansza[koordynaty[0]][koordynaty[1]] = NULL;
        swiatOrganizmu->podgladOrganizmow->usunOrganizm(this);
    }
};

void Zwierze::kolizja(Organizm *kolidujacyOrganizm){
    if(czyTenSamGatunek(*kolidujacyOrganizm)){
        
        if (typeid(*kolidujacyOrganizm).hash_code() == typeid(Czlowiek).hash_code()) {
            return;
        }

        this->komunikat("[Rozmnazanie]", kolidujacyOrganizm);
        
        rozmnazanie();
        return;
    }
    else{
        
        if(kolidujacyOrganizm->odbicie(this)){
            this->komunikat("[Odbicie]", kolidujacyOrganizm);
            return;
        }

        this->walka(kolidujacyOrganizm);        
    }
};

Zwierze::~Zwierze(){
};