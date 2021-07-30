/*
 * 1. 'antic' 포함 ->K의 값이 최소 5개
 * 2.  입력값이 작음 -> 완전 탐색 가능
 * */

#include <stdio.h>

// variables
int N, K;
int ans;

int word[51][26];
int used[26];

int 	check(int i)
{
	return (i == 0 || i == ('n' - 'a') || i == ('t' - 'a') ||
			i == ('i' - 'a') || i == ('c' - 'a'));
}

void	backtrack(int i, int n)
{	
	int	cnt, know;

	// escape condition
	if (n == 0)
	{
		cnt = 0;
		//남극 모든 단어에 대해서 배운글자로 익힐 수 있는 지 판단.
		fot(int j = 0; j < N; j++)
		{
			know = 1;
			for(int k = 0; k < 26; k++)
			{
				if (word[j][k] && !used[k])
					know = 0;
			}
			if (know)
				cnt++;
		}
		ans = (ans > cnt) ? ans : cnt;
		return ;
	}
	//나머지 글자에 대해서 익히는 백트래킹
	for(int j = i; j < 26; j++)
	{
		if (check(j))
			continue;
		// check in
		used[j] = 1;
		// backtrack
		// 다음 글자를 배운다.
		// 하나 배웠으니 n - 1개의 글자가 남았다.
		backtrack(j + 1, n - 1);
		// check out
		used[j] = 0;
	}
}

int	main(void)
{
	int	i, j;
	char	str[16];
	//1. input
	scanf("%d %d\n", &N, &K);
	for(i = 0;i < N; i++)
	{
		scanf("%s", str);
		j = -1;
		while (str[++j])
			word[i][str[j] - 'a'] = 1;
	}
	if (K < 5)
	{
		printf("0\n");
		return ;
	}
	//2. backtrack
	used[0] = 1;
	used['n' - 'a'] = 1;
	used['t' - 'a'] = 1;
	used['i' - 'a'] = 1;
	used['c' - 'a'] = 1;
	//시작점, 남은 횟수
	backtrack(0, K - 5);
	printf("%d\n", ans);
	return (0);
}
