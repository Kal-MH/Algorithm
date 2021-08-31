#include <stdio.h>

char str[16];

void func(int n, int m, int idx, int num)
{
	if (idx >= m * 2 - 1) {
		printf("%s\n", str);
		return ;
	}

	//갈 수 있는 곳의 시작점을 바로 전 숫자로 지정 -> 오름차순을 위해서
	for(int i = num; i <= n; i++) {
		str[idx] = i + '0';
		func(n, m, idx + 2, i);
	}
}

int main(void) {
	int i, n, m;

	scanf("%d %d", &n, &m);
	for(int i = 1; i < m * 2 - 1; i += 2)
		str[i] = ' ';
	str[m * 2 - 1] = '\0';
	func(n, m, 0, 1);
	return (0);
}
