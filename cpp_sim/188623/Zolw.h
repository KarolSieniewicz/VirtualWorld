#pragma once

#include <stdio.h>
#include "Zwierze.h"

class Zolw : public Zwierze {
    public:
        Zolw(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        void akcja() override;
        string getNazwa() override;
        bool odbicie(Organizm *entity) override;
        Organizm *klonowanie(int posX, int posY) override;
        ~Zolw() override;
};
