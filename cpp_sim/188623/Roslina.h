#pragma once

#include <stdio.h>
#include <stdlib.h>
#include "Organizm.h"

class Roslina : public Organizm {
    public:
        Roslina(Swiat* swiatOrganizmu, int sila, int pos_x, int pos_y, int age, bool czyCzeka);
        void akcja() override;
        ~Roslina();
};
