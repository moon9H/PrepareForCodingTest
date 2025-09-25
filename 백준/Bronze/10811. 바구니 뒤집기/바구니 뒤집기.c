#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int basket_num, reverse_rep, first, last, temp;
    int *basket;
    scanf("%d %d",&basket_num,&reverse_rep);
    basket = (int*)malloc(sizeof(int)*(basket_num+1));
    for (int i = 1; i<=basket_num; i++) basket[i] = i;
    for (int i = 0; i<reverse_rep; i++){
        scanf("%d %d",&first,&last);
        for (int j = first; j<last; j++){
            temp = basket[j];
            basket[j] = basket[last];
            basket[last] = temp;
            last--;
        }
    }

    for (int j = 1; j<=basket_num; j++) printf("%d ",basket[j]);
    printf("\n");

    return 0;
}