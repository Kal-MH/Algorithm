#include <stdio.h>

int marker[8] = {};
char buffer[32];

void print(int len, int depth, char *end) {
	if (!depth)
	{
		//마지막 숫자를 채우기 위해서 갈 수 있는 곳 순회
		// depth == -1일 때로 조건을 바꾼다면 필요없는 부분이 되겠다.
		for(int i = 0; i < len; i++) {
			if (!marker[i]) {
				end[0] = i + '1';
				end[2] = '\0';
				puts(buffer);
			}
		}
	}
	else {
		for(int i = 0; i < len; i++) {
			if (!marker[i]) {
				marker[i] = 1;
				end[0] = i + '1';
				end[1] = ' ';
				end[2] = '\0';
				print(len, depth - 1, end + 1);
				marker[i] = 0;
			}
		}
	}
}

int main() {
	int n, m;
	scanf("%d %d", &n, &m);
	print(n, m - 1, buffer);
}
