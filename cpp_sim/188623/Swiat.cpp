#include "Swiat.h"
#include "Organizm.h"

#include "Zwierze.h"
#include "Czlowiek.h"
#include "Owca.h"
#include "Wilk.h"
#include "Lis.h"
#include "Zolw.h"
#include "Antylopa.h"

#include "Roslina.h"
#include "Trawa.h"
#include "Mlecz.h"
#include "Guarana.h"
#include "WilczaJagoda.h"
#include "BarszczSosnowskiego.h"

#include <typeinfo>

Organizmy::Organizmy(int maxSize) : maksIlosc(maxSize){

    podglad = new podgladOrganizmu;
    podglad->aktualnyOrganizm = NULL;
    podglad->next = NULL;
};

int Organizmy::getMaksIlosc(){
    return maksIlosc;
};

int Organizmy::getAktualnaIlosc(){
    return aktualnaIlosc;
};

void Organizmy::dodajOrganizm(Organizm *entity){

    if(aktualnaIlosc >= maksIlosc){
        printf("Nie mozna dodac wiecej organizmow\n");
        return;
    }
    

    podgladOrganizmu *newEntity = new podgladOrganizmu;
    newEntity->aktualnyOrganizm = entity;
    newEntity->next = NULL;
    
    podgladOrganizmu *current = podglad;
    

    while(current->next != NULL){
        if (current->next->aktualnyOrganizm->getNazwa() != "Czlowiek") {
            if (entity->getInicjatywa() > current->next->aktualnyOrganizm->getInicjatywa()) {

                break;
            }

            else if (entity->getInicjatywa() == current->next->aktualnyOrganizm->getInicjatywa()) {

                if (entity->getWiek() >= current->next->aktualnyOrganizm->getWiek()) {

                    break;
                }
            }
        }
        
        current = current->next;
    }

    newEntity->next = current->next;
    current->next = newEntity;

    aktualnaIlosc++;
};

void Organizmy::usunOrganizm(Organizm *entity){

    podgladOrganizmu *current = podglad;
    for(int i = 0; i < aktualnaIlosc; i++){
        if(current->next->aktualnyOrganizm == entity){
            break;
        }
        if(i == aktualnaIlosc - 1){
            printf("Nie ma takiego organizmu\n");
            return;
        }
        current = current->next;
    }
    

    current->next = current->next->next;
    delete entity;
    aktualnaIlosc--;
};

Organizmy::~Organizmy(){
};

void Swiat::ustawLosowo(Organizm *entity){


    int newPosition[2];
    newPosition[0] = rand() % getBoardX();
    newPosition[1] = rand() % getBoardY();
    
    if(plansza[newPosition[0]][newPosition[1]] == NULL){

        entity->setX(newPosition[0]);
        entity->setY(newPosition[1]);
        plansza[newPosition[0]][newPosition[1]] = entity;
        podgladOrganizmow->dodajOrganizm(entity);
    }
    else{

        delete entity;
    }
};

void Swiat::ustawNaKoordynatach(Organizm *entity, int axisN, int axisM){

    entity->setX(axisN);
    entity->setY(axisM);
    plansza[axisN][axisM] = entity;
    podgladOrganizmow->dodajOrganizm(entity);
}

Swiat::Swiat(int N, int M) : BoardX(N), BoardY(M) {
    
    printf("Inicjalizacja swiata(%d,%d)\n",N,M);
    srand(time(NULL));


    plansza = new Organizm **[N];
    for(int i = 0; i < N; i++){
        plansza[i] = new Organizm *[M];
    }
    

    for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
            plansza[i][j] = NULL;
        }
    }


    podgladOrganizmow = new Organizmy(N*M);
    

    ustawLosowo(new Czlowiek(this, -1, -1, 19));
    resetUmiejetnosciCzlowieka = 0;
    

    czyGraAktywna = true;
        
    ustawLosowo(new Owca(this, -1, -1, 14, true));
    ustawLosowo(new Owca(this, -1, -1, 16, true));
    
    ustawLosowo(new Trawa(this, -1, -1, 5, true));
    
    ustawLosowo(new Mlecz(this, -1, -1, 2, true));    

    ustawLosowo(new Wilk(this, -1, -1, 11, false));
    ustawLosowo(new Wilk(this, -1, -1, 10, false));

    ustawLosowo(new Guarana(this, -1, -1, 4, true));
    ustawLosowo(new Guarana(this, -1, -1, 3, true));
    
    ustawLosowo(new WilczaJagoda(this, -1, -1, 2, true));

    ustawLosowo(new BarszczSosnowskiego(this, -1, -1, 1, true));

    ustawLosowo(new Lis(this, -1, -1, 5, true));
    ustawLosowo(new Lis(this, -1, -1, 9, true));

    ustawLosowo(new Zolw(this, -1, -1, 22, true));
    ustawLosowo(new Zolw(this, -1, -1, 17, true));

    ustawLosowo(new Antylopa(this, -1, -1, 11, true));
    ustawLosowo(new Antylopa(this, -1, -1, 7, true));
    
    getchar();
    getchar();

};

Swiat::Swiat(int N, int M, std::ifstream &loadFile) : BoardX(N), BoardY(M) {
    
    printf("Zapisany Swiat koordynatach %i,%i wczytany\n",N,M);
    srand(time(NULL));


    plansza = new Organizm **[N];
    for(int i = 0; i < N; i++){
        plansza[i] = new Organizm *[M];
    }
    

    for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
            plansza[i][j] = NULL;
        }
    }


    podgladOrganizmow = new Organizmy(N*M);
    

    czyGraAktywna = true;

    Organizm *newEntity;
    std::string word;
    int oldN, oldM, oldAge, oldBool;
    while(loadFile >> word){
        loadFile >> oldN >> oldM >> oldAge >> oldBool;
        if(!word.compare("Antylopa")){
            ustawNaKoordynatach(new Antylopa(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("WilczaJagoda")){
            ustawNaKoordynatach(new WilczaJagoda(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("Mlecz")){
            ustawNaKoordynatach(new Mlecz(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("Lis")){
            ustawNaKoordynatach(new Lis(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("Trawa")){
            ustawNaKoordynatach(new Trawa(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("Guarana")){
            ustawNaKoordynatach(new Guarana(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("BarszczSosnowskiego")){
            ustawNaKoordynatach(new BarszczSosnowskiego(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("Czlowiek")){
            ustawNaKoordynatach(new Czlowiek(this, -1, -1, oldAge), oldN, oldM);
        }
        else if(!word.compare("Owca")){
            ustawNaKoordynatach(new Owca(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("Zolw")){
            ustawNaKoordynatach(new Zolw(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
        else if(!word.compare("Wilk")){
            ustawNaKoordynatach(new Wilk(this, -1, -1, oldAge, oldBool), oldN, oldM);
        }
    }
    getchar();
    getchar();
};

int Swiat::getBoardX(){
    return BoardX;
};

int Swiat::getBoardY(){
    return BoardY;
};

int Swiat::getAktualnaTura(){
    return aktualnaTura;
}

int Swiat::getResetUmiejetnosciCzlowieka(){
    return resetUmiejetnosciCzlowieka;
}

void Swiat::ustawResetUmiejetnosciCzlowieka(int i){
    resetUmiejetnosciCzlowieka = i;
}

bool Swiat::getCzyGraAktywna(){
    return czyGraAktywna;
}

void Swiat::zmienStanGry(){
    if(czyGraAktywna){
        czyGraAktywna = 0;
    }
    else{
        czyGraAktywna = 1;
    }
}

void Swiat::ustawAktualnaTure(int turn){
    this->aktualnaTura = turn;
}

void Swiat::rysujSwiat(){
    printf("Rysowanie swiata\n");
    
    int i, j, k = 0;
    printf("Tura %i\n ", aktualnaTura);
    printf("\n");
    for (i = -1; i < BoardX+1; i++) printf("-");
    printf("\n");
    for(i = 0; i < BoardY; i++){
        for(j = -1; j < BoardX+1; j++){
            if (j == -1 || j == BoardX)
            {
                printf("|");
            }
            else
            {
                if (plansza[j][i] != NULL) {
                    if (typeid(*plansza[j][i]).hash_code() == typeid(Czlowiek).hash_code()) {
                        plansza[j][i]->rysowanie();
                    }
                    else {
                        plansza[j][i]->rysowanie();
                    }
                }
                else {
                    printf(" ");
                }
            }            
        }
        printf("\n");
    }    
    for (i = -1; i < BoardX+1; i++) printf("-");
    printf("\n\nLegenda :\nA(Antylopa)]\nJ(Wilcza Jagoda)\nL(Lis)\n#(Trawa)\nG(Guarana)\n&(Barszcz Sosnowskiego)\n$(Czlowiek)\nO(Owca)\nM(Mlecz)\nZ(Zolw)\nW(Wilk)\n\n\n");
};

void Swiat::wykonajTure(){
    printf("Wykonywanie tury nr %d\n", aktualnaTura);
    
    podgladOrganizmu *current = podgladOrganizmow->podglad->next;
    if(resetUmiejetnosciCzlowieka > 0){
        resetUmiejetnosciCzlowieka--;
    }

    while(current != NULL) {

        if(current->aktualnyOrganizm->czyCzeka == true){
            current->aktualnyOrganizm->czyCzeka = false;
        }
        else{
            current->aktualnyOrganizm->akcja();
        }
        current = current->next;
    }
    getchar();
    aktualnaTura++;
};

void Swiat::zapiszSwiat(){
    std::ofstream fileToSave;
    fileToSave.open("zapis.txt", std::ios::out);
    if(!fileToSave){
        printf("Nie mozna otworzyc pliku. Gra nie zostala zapisana.\n");
        return;
    }
    else{
        fileToSave << aktualnaTura << " " << resetUmiejetnosciCzlowieka << "\n";
        fileToSave << BoardX << " " << BoardY << "\n";
        podgladOrganizmu *current = podgladOrganizmow->podglad->next;
        while(current != NULL){
            fileToSave << current->aktualnyOrganizm->getNazwa() << " " << current->aktualnyOrganizm->getX() << " " << current->aktualnyOrganizm->getY() << " " << current->aktualnyOrganizm->getWiek() << " " << current->aktualnyOrganizm->getCzyCzeka() << "\n";
            current = current->next;
        }
        
        printf("Zapisano stan gry.\n");
        fileToSave.close();
    }
};

Swiat::~Swiat(){
    
};