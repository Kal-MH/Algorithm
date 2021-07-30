#include <stdio.h>

//variables
int N, K;
int ans;

int word[51][26];
int used[26];

int	check(int i)
{
	return (i == 0 || i == 'n' - 'a' || i == 't' - 'a'
			|| i == 'i' - 'a' || i == 'c' - 'a');
}

//배운 글자 i에 대한 backtrack, 배워야 할 남은 글자 갯수 n
void	backtrack(int i, int n)
{
	int	cnt, know;

	//1. escape
	if (n == 0)
	{
		cnt = 0;
		for(int j = 0; j < N; j++)
		{
			know = 1;
			for(int k = 0; k < 26; k++)
				if (word[j][k] && !used[k])
					know = 0;
			if (know)
				cnt++;
		}
		ans = (ans > cnt) ? ans : cnt;
		return ;
	}
	for(int j = i; j < 26; j++)
	{
		if (check(j))
			continue;
		//2. check in
		used[j] = 1;
		//3. backtrack
		backtrack(j + 1, n - 1);
		//4. check out
		used[j] = 0;
	}
}

int	main(void)
{
	int	i, j;
	char	str[16];

	//input
	scanf("%d %d", &N, &K);
	for(i = 0; i < N; i++)
	{
		scanf("%s", str);
		j = -1;
		while(str[++j])
			word[i][str[j] - 'a'] = 1;
	}
	if (K < 5)
	{
		printf("0\n");
		return (0);
	}
	//backtrack
	used[0] = 1;
	used['n' - 'a'] = 1;
	used['t' - 'a'] = 1;
	used['i' - 'a'] = 1;
	used['c' - 'a'] = 1;
	backtrack(0, K - 5);
	//output
	printf("%d\n");
	return (0);
}
