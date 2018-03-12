//
//  main.c
//  First project
//
//  Created by Martin Gábor on 27.10.17.
//  Copyright © 2017 Martin Gábor. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define terminalValue "terVal\0"
#define SPZlength 7


typedef enum bool { false, true} bool;

void v(FILE **fr){
    char name[50], SPZ[SPZlength + 1];
    int date;
    bool type;
    double price;
    
    if ((*fr = fopen("/Users/MocinGejbr/Documents/predaj.txt", "r")) == NULL){
        printf("Neotvoreny subor\n");
        return ;
    }
    
    while (!feof(*fr)){
        
        fgets(name,50,*fr);
        fscanf(*fr ,"%s\n", SPZ);
        fscanf(*fr ,"%d\n", &type);
        fscanf(*fr ,"%lf\n", &price);
        fscanf(*fr ,"%d\n", &date);
        
        printf("meno priezvisko: %s", name);
        printf("SPZ: %s\n", SPZ);
        printf("typ: %d\n", type);
        printf("cena: %.2lf\n", price);
        printf("datum: %d\n\n", date);


    }
    rewind(*fr);
}

void o(FILE **fr){
    if (*fr == NULL) return;
    
    char name[50], SPZ[SPZlength + 1];
    bool type;
    double price;
    int currentDate, fileDate;
    
    scanf("%d", &currentDate);
    
    while (!feof(*fr)){
        
        fgets(name ,50, *fr);
        name[strlen(name) - 1] = '\0';
        fscanf(*fr ,"%s\n", SPZ);
        fscanf(*fr ,"%d\n", &type);
        fscanf(*fr ,"%lf\n", &price);
        fscanf(*fr ,"%d\n", &fileDate);
        
        if ((currentDate - fileDate) >= 10000){
            if (type) printf("%s %s %.2lf\n",name,SPZ,0.015 * price);
            else printf("%s %s %.2lf\n",name,SPZ,0.022 * price);
        }
    }
    
    rewind(*fr);
}

void n(FILE **fr, char ***SPZarray){
    if (*fr == NULL) return;
    
    char temp, tempStr[50];
    int lineCount = 0, inputCount = 0, j = 0;
    
    while ((temp = fgetc(*fr)) != EOF){
        if (temp == '\n'){
            lineCount++;
            if (lineCount == 6){
                inputCount++;
                lineCount = 0;
            }
        }
    }

    rewind(*fr);
    
    if(*SPZarray == NULL){
        *SPZarray = malloc((inputCount + 1) * sizeof(char*));
    }else{
        while(strcmp(*(*SPZarray + j), terminalValue) != 0){
            //printf("%d %s\n", j, *(*SPZarray + j));
            free(*(*SPZarray + j));
            j++;
        }
        free(*SPZarray);
        *SPZarray = (char**) malloc((inputCount + 1) * sizeof(char*));
    }

    
    for (int i = 0; i < inputCount; i++){
        *(*SPZarray + i)  = (char*) malloc(8 * sizeof(char));
        
        fgets(tempStr, 50, *fr);
        fscanf(*fr , "%s\n", *(*SPZarray + i));
        fgets(tempStr, 50, *fr);
        fgets(tempStr, 50, *fr);
        fgets(tempStr, 50, *fr);
        fgets(tempStr, 50, *fr);
    }
    
    *(*SPZarray + inputCount)  = (char*) malloc(8 * sizeof(char));
    *(*SPZarray + inputCount) = terminalValue;
 
    rewind(*fr);
}

void s(char **SPZ){
    if(SPZ == NULL){
        printf("Pole nie je vytvorene\n");
        return;
    }
    
    int i = 0;
    while (SPZ[i] != terminalValue){
        for (int j = 0; j < SPZlength; j++){
            putchar(*(SPZ[i]+j));
            if(j == 1 || j == 4) putchar(' ');
        }
        putchar('\n');
        i++;
    }
}

void p(char **SPZ){
    if(SPZ == NULL){
        printf("Pole nie je vytvorene\n");
        return;
    }
    
    int i = 0;
    bool palindrom = true;
    
    while (SPZ[i] != terminalValue){
        for (int j = 0; j < SPZlength; j++){
            if (SPZ[i][j] != SPZ[i][SPZlength - 1 - j]){
                palindrom = false;
                break;
            }
        }
        
        if (palindrom)
            printf("%c%c\n", SPZ[i][0], SPZ[i][1]);
        
        palindrom = true;
        i++;
    }
}

void z(char **SPZ){
    
    if(SPZ == NULL) return;

    int arraySize = 0, currentNum = 0, maxNum = 0, IDmaxNum = -1, *znum = (int*) malloc(sizeof(int)), k = 0, cnt = 1, l = 0;
    
    bool numFound = false;
    
    char tempI[3], tempJ[3], **zn = (char**) malloc (sizeof(char*));
    
    while(SPZ[arraySize] != terminalValue){
        arraySize++;
    }
    
    *zn = (char*)malloc (3 * sizeof(char));
    *zn = terminalValue;


    for (int i = 0; i < arraySize; i++){
        strncpy(tempI, SPZ[i], 2);
        tempI[2] = '\0';
        for (int j = i; j < arraySize; j++){
            strncpy(tempJ, SPZ[j], 2);
            tempJ[2] = '\0';
            if (!strcmp(tempI, tempJ)){
                currentNum++;
            }
        }
        if (currentNum > maxNum)
        if (currentNum == maxNum){
            while (*(zn + k) != terminalValue){
                if (!strcmp(tempI, *(zn + k))){
                    //*(znum + k) += 1;
                    numFound = true;
                   // printf("%s %d\n",*(zn + k), *(znum + k));
                }
                k++;
            }
            if (!numFound){
                //printf("%s %d\n",*(zn + k), *(znum + k));

                cnt++;
                *(zn + k) = tempI;
                *(znum + k) = currentNum;
                zn =  (char**) realloc (zn, cnt * sizeof(char*));
                *(zn + k + 1) = (char*) malloc (3 * sizeof(char));
                *(zn + k + 1) = terminalValue;
                //printf("%s %d\n",*(zn + k), *(znum + k));

            }
            numFound = false;
            maxNum = currentNum;
            IDmaxNum = i;
        } else if (currentNum > maxNum){
                while (*(zn + l) != terminalValue){
                    free(*(zn + l));
                    free((znum + l));
                    l++;
                }
                free(zn);
            // novy malloc nove pole ak je current vacsi netreba mi ukladat aj ostatne co boli dotrz mensie
            
        }
        
        currentNum = 0;
    }
    for (int i = 0; i < cnt; i ++) {
        if (*(znum + i) == maxNum)
            printf("%s %d\n", *(zn + i), maxNum);
    }
    // printf("%c%c %d\n", SPZ[IDmaxNum][0], SPZ[IDmaxNum][1], maxNum);

}

int main() {
    
    char control, **SPZ = NULL;
    FILE *fr = NULL;
    int i = 0;
    
    
    while ((control = getchar()) != 'k'){
        switch (control){
            case 'v': v(&fr); break;
            case 'o': o(&fr); break;
            case 'n': n(&fr, &SPZ); break;
            case 's': s(SPZ); break;
            case 'p': p(SPZ); break;
            case 'z': z(SPZ); break;
        }
    }
    if (fr != NULL && fclose(fr) == EOF)
        printf("Subor sa nepodarilo zatvorit.");
    
    if (SPZ != NULL){
        while (*(SPZ + i) != terminalValue){
            free(*(SPZ + i));
            i++;
        }
        free(SPZ);
    }
        
    
    return 0;
}
