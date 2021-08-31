#include <stdio.h>

char str[16];

void func(int n, int m, int idx, int num)
{
	//목적지인가
	if (idx >= m * 2 - 1) {
		printf("%s\n", str);
		return ;
	}
	//갈 수 있는 곳 순회
	for(int i = 1; i <= n; i++ {
		str[idx] = i + '0';
		func(n, m, idx + 2, i + 1);
	}
}

int main(void) {
	int i, n, m;

	scanf("%d %d", &n, &m);
	//최대 7자리가 되는 char배열에서 공백 미리 채워넣기
	for(int i = 1; i < m * 2 - 1; i += 2)
		str[i] = ' ';
	//문자열 마지막 자리 널 문자 삽입
	str[m * 2 - 1] = 0;
	func(n, m, 0, 1);
	
	return (0);
}
