//입력이 그렇게 크지 않다.브루트 포스, 완전탐색, 모든 케이스에 대해서 조사해서 정답을 도출
//백트래킹, DFS를 활용한 예
#include <bits/stdc++.h>

using namespace std;

int N, K, ans;
bool word[51][26], used[26];
//word = 'abc' -> word[0][0] = true, word[0][1] = true, word[0][3] = false;

bool check(int i) {
	return i == 0 || i == 'n' - 'a' || i == 't' - 'a' || i == 'c' - 'a' || i == 'i' - 'a';
}

//i : 알파벳, n : 남은 갯수
void backtrack(int i, int n) {
	if (n == 0)
	{
		int cnt = 0;
		for (int j = 0; j < N; j++) {
			bool know = true;
			for (int k = 0; k < 25; k++) {
				if (word[j][k] && !used[k]) //단어를 이루는 문자와 내가 알고 있는 문자가 모두 참이어야 한다.
					know = false;
			}
			if (know) ++cnt;
		}
		ans = max(ans, cnt); //각각의 글자 조합으로 체크했을 때 읽을 수 있는 단어 수 최대를 구하는 거기 때문에
		return;
	}
	for (int j = i; j < 26; j++) {
		if (check(j))
			continue;
		used[j] = true;
		backtrack(j + 1, n - 1);
		used[j] = false;
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < s.length(); j++)
			word[i][s[j] - 'a'] = true;
	}
	if (K < 5)
	{
		cout << 0 << endl;
		return;
	}
	used[0] = true;
	used['n' - 'a'] = true;
	used['t' - 'a'] = true;
	used['i' - 'a'] = true;
	used['c' - 'a'] = true;
	backtrack(0, K - 5);
	cout << ans << endl;
}
