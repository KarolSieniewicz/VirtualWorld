#pragma once

#include <stdio.h>
#include "Zwierze.h"

class Owca : public Zwierze {
    public:
        Owca(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        string getNazwa() override;
        Organizm* klonowanie(int posX, int posY) override;
        ~Owca() override;
};
