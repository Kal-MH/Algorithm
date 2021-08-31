#include <stdio.h>

int N, M;
int a[9];

void dfs(int cnt, int st)
{
	int i;
	if (cnt == M)
	{
		for(int i = 0; i < M; i++)
			printf("%d ", a[i]);
		printf("\n");
		return ;
	}
	for(i = st; i <= N; i++)
	{
		a[cnt] = i;
		dfs(cnt + 1, i + 1);
	}
} 

int main() {
	scanf("%d %d", &N, &M);
	dfs(0, 1);
}
