#pragma once

#include <stdio.h>
#include "Roslina.h"

class BarszczSosnowskiego : public Roslina {
    public:
        BarszczSosnowskiego(Swiat* swiatOrganizmu, int posX, int posY, int age, bool czyCzeka);
        void rysowanie() override;
        void akcja() override;
        string getNazwa() override;
        Organizm *klonowanie(int posX, int posY) override;
        ~BarszczSosnowskiego() override;
};
