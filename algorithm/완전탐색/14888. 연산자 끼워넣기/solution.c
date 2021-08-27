#include <stdio.h>

int n, a[13];
int maxi = -1000000001;
int mini = 1000000001;
int x, y, z, w;

void	f(int s, int g){
	if (s == n) {
		if (g > maxi)
			maxi = g;
		if (g < mini)
			mini = g;
	}
	if (x) {
		x--;
		f(s + 1, g + a[s + 1]);
		x++;
	}
	if (y) {
		y--;
		f(s + 1, g - a[s + 1]);
		y++;
	}
	if (z) {
		z--;
		f(s + 1, g * a[s + 1]);
		z++;
	}
	if (w) {
		w--;
		f(s + 1, g / a[s + 1]);
		w++;
	}
}

int main(void) {
	scanf("%d", &n);
	for(int i = 1; i <= n; i++)
		scanf("%d", &a[i]);
	scanf("%d %d %d %d", &x, &y, &z, &w);
	f(1, a[1]);
	printf("%d %d", maxi, mini);
}
