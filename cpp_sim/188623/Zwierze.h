#pragma once

#include <iostream>
#include <string>
#include "Organizm.h"


class Zwierze : public Organizm {
    public:
        Zwierze(Swiat *currentWorld, int sila, int initiative, int pos_x, int pos_y, int age, bool newborn);
        bool czyTenSamGatunek(Organizm &entity);
        void rozmnazanie();
        std::string getNazwa() override = 0;         
        void rysowanie() override = 0;                  
        void akcja() override;
        void walka(Organizm* oponent);
        virtual void kolizja(Organizm *kolidujacyOrganizm);
        ~Zwierze();
};