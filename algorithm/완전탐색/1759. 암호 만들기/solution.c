#include <stdio.h>


void qsort(char *data, int start, int end)
{
	if (start >= end) return;

	int pivot = start;
	int i = pivot + 1;
	int j = end;
	char temp;

	while (i <= j)
	{
		while (i <= end&&data[i] <= data[pivot]) i++;
		while (j > start && data[j] >= data[pivot])j--;

		if (i > j)
		{
			temp = data[j]; data[j] = data[pivot]; data[pivot] = temp;
		}
		else
		{
			temp = data[i]; data[i] = data[j]; data[j] = temp;
		}
	}
	qsort(data, start, j - 1);
	qsort(data, j + 1, end);

}


char alp[16];
char queue[16];

int k, c;
int visit[100] = { 0 };
int ja, mo;
void judge(char a)
{
	if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u')
	{
		mo += 1;
	}
	else
	{
		ja += 1;
	}
}

void DFS(int a, int cnt)
{

	if (cnt == k)
	{
		int rear=0, front=0;
		ja = 0; mo = 0;
		for (int i = 0; i < c; i++)
		{
			if (visit[i] == 1)
			{
				judge(alp[i]);
				queue[rear++] = alp[i];
			}
		}
		if (ja < 2 || mo < 1)
		{
			return;
		}
		else
		{
			while (front < rear)
			{
				printf("%c", queue[front++]);
			}
			printf("\n");
		}
		
	}

	for (int i = a; i < c; i++)
	{
		if (visit[i] == 0)
		{
			visit[i] = 1;
			DFS(i, cnt + 1);
			visit[i] = 0;
		}
	}

}

int main()
{

	scanf("%d %d", &k, &c);
	
	for (int i = 0; i < c; i++)
	{
		getchar();
		scanf("%c", &alp[i]);
		
	}
	

	qsort(alp, 0, c-1 );

	DFS(0, 0);
	return 0;
}
