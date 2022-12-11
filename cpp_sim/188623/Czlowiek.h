#pragma once

#include <stdio.h>
#include "Zwierze.h"

class Czlowiek : public Zwierze {
    private:
        bool tarczaAlzura = 0;
    public:
        Czlowiek(Swiat* swiatOrganizmu, int posX, int posY, int age);
        void rysowanie() override;
        void akcja() override;
        string getNazwa() override;
        void specjalnaUmiejetnosc();
        void dezaktywujTarcze();
        bool odbicie(Organizm* atakujacy) override;
        Organizm *klonowanie(int posX, int posY) override;
        ~Czlowiek();
};
