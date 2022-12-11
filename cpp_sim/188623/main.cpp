#include "Swiat.h"

int main() {
    system("cls");
    printf("Karol Sieniewicz 188623\n");
    printf("Manual:\n\n");
    printf("1) Ruch:\n");
    printf("    w - gora, s - dol, a - lewo, d - prawo\n");
    printf("2) Akcje gracza:\n");
    printf("    r - umiejetnosc specjalna (w ramach ruchu), x - zapisz gre (po ruchu), q - zamknij gre (w ramach ruchu)\n\n\n");
    printf("Wybierz opcje gry:\n");
    printf("1 - Nowa gra\n");
    printf("2 - Wczytaj gre\n");
    printf("3 - Wyjdz\n");
    int gameMode, BoardX, BoardY, playerInput;
    std::cin >> gameMode;

    if (gameMode == 1) {
        printf("Wpisz X i Y planszy: ");
        std::cin >> BoardX >> BoardY;
        Swiat newWorld(BoardX, BoardY);
        while (newWorld.getCzyGraAktywna()) {
            system("cls");
            newWorld.rysujSwiat();
            newWorld.wykonajTure();
            playerInput = getchar();
            if (playerInput == 120) {
                newWorld.zapiszSwiat();
                getchar();
                getchar();
            }
        }
        system("cls");
        newWorld.rysujSwiat();
    }
    else if (gameMode == 2) {
        std::ifstream loadFile;
        loadFile.open("zapis.txt", std::ios::in);
        if (!loadFile) {
            printf("Nie udalo sie zaladowac pliku.\n");
            exit(1);
        }
        int oldTurn, oldCooldown;
        loadFile >> oldTurn >> oldCooldown;
        loadFile >> BoardX >> BoardY;
        Swiat savedWorld(BoardX, BoardY, loadFile);

        savedWorld.ustawAktualnaTure(oldTurn);
        savedWorld.ustawResetUmiejetnosciCzlowieka(oldCooldown);

        while (savedWorld.getCzyGraAktywna()) {
            system("cls");
            savedWorld.rysujSwiat();
            savedWorld.wykonajTure();
            playerInput = getchar();
            if (playerInput == 120) {
                savedWorld.zapiszSwiat();
                getchar();
                getchar();
            }
        }
        system("cls");
        savedWorld.rysujSwiat();
    }
    else {
        exit(1);
    }

    printf("GAME OVER\n");
    system("pause");
    return 0;
};