#include <stdio.h>

int N;
int K;
int ans;

int	word[51][26];
int	used[26];

int	check(int i)
{
	return (i == 0 || i == ('n' - 'a') || i == ('t' - 'a') || i == ('c' - 'a')
			|| i == ('i' - 'a'));
}

void	backtrack(int i, int n)
{
	int cnt, know;

	if (n == 0)
	{
		cnt = 0;
		for(int j = 0; j < N; j++)
		{
			know = 1;
			for(int k = 0; k < 25; k++)
				if (word[j][k] && !used[k])
					know = 0;
			if (know)
				cnt++;
		}
		ans = (ans > cnt) ? ans : cnt;
		return ;
	}
	for(int j = i; j < 25; j++)
	{
		if (check(j))
			continue;
		used[j] = 1;
		backtrack(j + 1, n - 1);
		used[j] = 0;
	}
}

int	main()
{
	int	i, j;
	char	str[16];

	scanf("%d %d", &N, &K);
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
		return (0);
	}
	used[0] = 1;
	used['n' - 'a'] = 1;
	used['i' - 'a'] = 1;
	used['t' - 'a'] = 1;
	used['c' - 'a'] = 1;
	backtrack(0, K - 5);
	printf("%d\n", ans);
	return (0);
}
