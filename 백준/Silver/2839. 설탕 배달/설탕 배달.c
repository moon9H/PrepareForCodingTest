#include <stdio.h>

int main(void)
{
    int cnt_3 = 10000, cnt_5 = 0, weight, cnt = 10000;
    scanf("%d",&weight);
    if (weight % 3 == 0)
        cnt_3 = weight / 3;
    if (weight % 5 == 0)
    {
        cnt_5 = weight / 5;
        printf("%d\n",cnt_5);
        return 0;
    }
    for (int i = 1; i <= weight / 3; i++)
    {
        for (int j = 1; j <= weight / 5; j++)
        {
            if (3*i + 5*j == weight)
            {
                cnt  = i+j;
                printf("%d\n",cnt);
                return 0;
            }
        }
    }
    if (cnt_3 == 10000 && cnt == 10000)
        printf("-1\n");
    else
        printf("%d\n",cnt_3);

    return 0;
}