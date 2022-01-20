## 문제

상근이는 보드 게임 "Boggle"을 엄청나게 좋아한다. Boggle은 글자가 쓰여 있는 주사위로 이루어진 **4×4 크기의 그리드**에서 최대한 많은 단어를 찾는 게임이다.

```cpp
맵이 주어졌다면 DFS를 생각해보고, 조이스틱을 생각하자
-> 고슴도치 탈출 문제
-> mx[] = {-1, 1, 0, 0} / my[] = {0, 0, -1, 1}
```

상근이는 한 번도 부인을 Boggle로 이겨본 적이 없다. 이렇게 질 때마다 상근이는 쓰레기 버리기, 설거지와 같은 일을 해야 한다. 이제 상근이는 프로그램을 작성해서 부인을 이겨보려고 한다.

Boggle에서 단어는 인접한 글자(가로, 세로, 대각선)를 이용해서 만들 수 있다. 하지만, 한 주사위는 단어에 한 번만 사용할 수 있다. 단어는 게임 사전에 등재되어 있는 단어만 올바른 단어이다.

**1글자, 2글자로 이루어진 단어는 0점, 3글자, 4글자는 1점, 5글자는 2점, 6글자는 3점, 7글자는 5점, 8글자는 11점이다.** 점수는 자신이 찾은 단어에 해당하는 점수의 총 합이다.

```java
해당 정보를 배열에 저장해두고 문자열 길이로 찾을 수 있게 해보자.
ex) arr[length - 1] = 2;
```

단어 사전에 등재되어 있는 단어의 목록과 Boggle 게임 보드가 주어졌을 때, **얻을 수 있는 최대 점수, 가장 긴 단어, 찾은 단어의 수**를 구하는 프로그램을 작성하시오.

```cpp
TrieNode
- 단어를 트리로 저장하자.
- 문자열을 빠르게 검색할 수 있는 자료구조
```

## 입력

첫째 줄에 단어 사전에 들어있는 단어의 수 w가 주어진다. (1 < w < 300,000) 다음 w개 줄에는 단어가 주어진다. 단어는 최대 8글자이고, 알파벳 대문자로만 이루어져 있다. 단어 사전에 대한 정보가 모두 주어진 이후에는 빈 줄이 하나 주어진다.

그 다음에는 Boggle 보드의 개수 b가 주어진다. (1 < b < 30) 모든 Boggle은 알파벳 대문자로 이루어져 있고, 4줄에 걸쳐 주어진다. 각 Boggle의 사이에는 빈 줄이 하나  있다.

## 출력

각각의 Boggle에 대해, 얻을 수 있는 최대 점수, 가장 긴 단어, 찾은 단어의 개수를 출력한다. 한 Boggle에서 같은 단어를 여러 번 찾은 경우에는 한 번만 찾은 것으로 센다. 가장 긴 단어가 여러 개인 경우에는 **사전 순으로 앞서는 것을 출력**한다. 각 Boggle에 대해서 찾을 수 있는 단어가 적어도 한 개인 경우만 입력으로 주어진다.

## 예제 입력 1

```
5
ICPC
ACM
CONTEST
GCPC
PROGRAMM

3
ACMA
APCA
TOGI
NEST

PCMM
RXAI
ORCN
GPCG

ICPC
GCPC
ICPC
GCPC

```

## 예제 출력 1

```
8 CONTEST 4
14 PROGRAMM 4
2 GCPC 2
```

## 풀이

### solution

```java
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
  static int[] mx = {-1, 1, 0, 0, -1, 1, -1, 1};
  static int[] my = {0, 0, -1, 1, -1, -1, 1, 1};
  static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

  static int W, N;
  static char[][] map;
  static boolean[][] visited;
  static String answer;
  static int sum;
  static int count;
  static StringBuilder sb = new StringBuilder();
  static TrieNode root = new TrieNode();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    W = Integer.parseInt(br.readLine());

    for (int i = 0; i < W; i++) {
      insert(br.readLine());
    }

    br.readLine();
    N = Integer.parseInt(br.readLine());
    StringBuilder resultSb = new StringBuilder();
    for (int n = 0; n < N; n++) {
      map = new char[4][4];
      visited = new boolean[4][4];
      answer = "";
      sum = 0;
      count = 0;
      sb = new StringBuilder();

      for (int i = 0; i < 4; i++) {
        String in = br.readLine();
        for (int k = 0; k < 4; k++) {
          map[i][k] = in.charAt(k);
        }
      }

      for (int y = 0; y < 4; y++) {
        for (int x = 0; x < 4; x++) {
          if (root.hasChild(map[y][x])) {
            search(y, x, 1, root.getChild(map[y][x]));
          }
        }
      }
      resultSb.append(sum);
      resultSb.append(" ");
      resultSb.append(answer);
      resultSb.append(" ");
      resultSb.append(count);
      resultSb.append("\n");
      root.clearHit();
      br.readLine();
    }
    System.out.println(resultSb.toString());

  }

  static void search(int y, int x, int length, TrieNode node) {
    // 1. 체크인
    visited[y][x] = true;
    sb.append(map[y][x]);

    // 2. 목적지에 도달하였는가?
    if (node.isEnd && node.isHit == false) {
			//해당 단어는 찾았다는 것을 표시, 중복을 허락하지 않기 위해서
      node.isHit = true;
      sum += score[length];
      count++;
      String foundWord = sb.toString();
      if (compare(answer, foundWord) > 0) {
        answer = foundWord;
			//한 단어를 찾고 끝나는 것이 아니라 여러 단어를 최대한 많이 찾는 것이 목적이기 때문에
			//여기서 바로 break를 통해 종료하는 것이 아니라 
			//다시 한 발짝 전으로 돌아가서 새로운 단어를 탐색하게 된다.
      }
    }
    // 3. 연결된 곳을 순회
    for (int i = 0; i < 8; i++) {
      int ty = y + my[i];
      int tx = x + mx[i];
      // 4. 가능한가? map경계, 방문하지는 않았는지, node가 해당 자식을 가지고 있는지
      if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
        if (visited[ty][tx] == false && node.hasChild(map[ty][tx])) {
          // 5. 간다
          search(ty, tx, length + 1, node.getChild(map[ty][tx]));
        }
      }
    }
    // 6. 체크아웃
    visited[y][x] = false;
    sb.deleteCharAt(length - 1);
  }

  static int compare(String arg0, String arg1) {
    int result = Integer.compare(arg1.length(), arg0.length());
    if (result == 0) {
      return arg0.compareTo(arg1);
    } else {
      return result;
    }
  }

  static void insert(String word) {
    TrieNode current = root;
    for (int i = 0; i < word.length(); i++) {
      int wordIndex = word.charAt(i) - 'A';
      if (current.children[wordIndex] == null) {
        current.children[wordIndex] = new TrieNode();
      }
      current = current.children[wordIndex];
    }
    current.isEnd = true;
  }

  static boolean containsNode(String word) {
    TrieNode current = root;
    for (int i = 0; i < word.length(); i++) {
      int wordIndex = word.charAt(i) - 'A';
      if (current.children[wordIndex] == null) {
        return false;
      }
      current = current.children[wordIndex];
    }
    return true;
  }
}

class TrieNode {
  TrieNode[] children = new TrieNode[26];
  boolean isEnd;
  boolean isHit;

  void clearHit() {
    isHit = false;
    for (int i = 0; i < children.length; i++) {
      if (children[i] != null) {
        children[i].clearHit();
      }
    }
  }

  boolean hasChild(char c) {
    return children[c - 'A'] != null;
  }

  TrieNode getChild(char c) {
    return children[c - 'A'];
  }

  public String toString() {
    String str = "";
    for (int i = 0; i < children.length; i++) {
      if (children[i] != null) {
        str += (char) ('A' + i) + (children[i].isEnd ? "end" : "") + "{" + children[i].toString()
            + "}";
      }
    }
    return str;
  }
}
```

### 주석

```cpp
package algorithm;

import java.util.*;
import java.io.*;

public class Main {
	//variables;
	static int[] mx = {-1, 1, 0, 0, -1, 1, -1, 1};
	static int[] my = {0, 0, -1, 1, 1, 1, -1, -1};
	static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
	
	static int W, N;
	static char[][] map;
	static boolean[][] visited;
	static String answer;
	static int sum;
	static int count;
	static StringBuilder sb;
	static TrieNode root = new TrieNode();
			
	public static void main(String args[]) throws Exception{
		//input
		System.setIn(new FileInputStream("src/algorithm/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//Tree 초기화
		W = Integer.parseInt(br.readLine());
		for(int i = 0; i < W; i++) {
			insert(br.readLine());
		}
		
		br.readLine();
		N = Integer.parseInt(br.readLine());
		StringBuilder resultSb = new StringBuilder();
		for(int n = 0; n < N; n++) {
			//boggle 맵 초기화
			map = new char[4][4];
			visited = new boolean[4][4];
			answer = "";
			sum = 0;
			count = 0;
			sb = new StringBuilder();
			
			for(int i = 0; i < 4; i++) {
				String in = br.readLine();
				for(int j = 0; j < 4; j++) {
					map[i][j] = in.charAt(j);
				}
			}
			
			//run
			for(int y = 0; y < 4; y++) {
				for(int x = 0; x < 4; x++) {
					if (root.hasChild(map[y][x])) {
						search(y, x, 1, root.getChild(map[y][x]));
					}
				}
			}
			
			//output
			resultSb.append(sum);
		    resultSb.append(" ");
		    resultSb.append(answer);
		    resultSb.append(" ");
		    resultSb.append(count);
		    resultSb.append("\n");
		    root.clearHit();
		    br.readLine();
		}
		System.out.println(resultSb.toString());
	}
	
	static void search(int y, int x, int length, TrieNode node) {
		//1. check in
		visited[y][x] = true;
		sb.append(map[y][x]);
		//2. destination
		if (node.isEnd && node.isHit == false) {
			node.isHit = true;
			sum += score[length];
			count++;
			String foundAnswer = sb.toString();
			if (compare(answer, foundAnswer) > 0)
				answer = foundAnswer;
			//한 단어를 찾고 끝나는 것이 아니라 여러 단어를 최대한 많이 찾는 것이 목적이기 때문에
			//여기서 바로 break를 통해 종료하는 것이 아니라 
			//다시 한 발짝 전으로 돌아가서 새로운 단어를 탐색하게 된다.
		}
		//3. 갈 수 있는 곳 순회
		for(int i = 0; i < 8; i++) {
			int ty = y + my[i];
			int tx = x + mx[i];
			//4. 갈 수 있는가
			if ((0 <= ty && ty < 4) && (0 <= tx && tx < 4)) {
				if (visited[ty][tx] == false && node.hasChild(map[ty][tx])) {
					//5. 간다
					search(ty, tx, length + 1, node.getChild(map[ty][tx]));
				}
			}
		}
		//6. check out
		visited[y][x] = false;
		sb.deleteCharAt(length - 1);
	}
	
	static int compare(String args1, String args2) {
		//양수일 때 foundAnswer가 새로운 답이 되도록 하기 위해서 넣는 순서를 바꿔준다,
		int result = Integer.compare(args2.length(), args1.length());
		//문자열 길이가 같다면 사전순으로 정렬
		//음수 : args1이 args2보다 사전순으로 더 앞선다.
		//양수 : args2가 더 앞선다.
		if (result == 0)
			return args1.compareTo(args2);
		return result;
	}
	
	static void insert(String word) {
		TrieNode current = root;
		for(int i = 0; i < word.length(); i++) {
			int wordIndex = word.charAt(i) - 'A';
			if (current.children[wordIndex] == null) {
				current.children[wordIndex] = new TrieNode();
			}
			current = current.children[wordIndex];
		}
		current.isEnd = true;
	}
}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean isEnd;
	boolean isHit;
	
	void clearHit() {
		isHit = false;
		for(int i = 0; i < children.length; i++) {
			if (children[i] != null) {
				children[i].clearHit();
			}
		}
	}
	
	boolean hasChild(char c) {
		return children[c - 'A'] != null;
	}
	
	TrieNode getChild(char c) {
		return children[c - 'A'];
	}
}
```
