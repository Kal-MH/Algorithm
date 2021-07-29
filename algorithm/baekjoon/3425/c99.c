#include <stdio.h>

#define NUM 1
#define POP 2
#define INV 3
#define DUP 4
#define SWP 5
#define ADD 6
#define SUB 7
#define MUL 8
#define DIV 9
#define MOD 10

#define MAX_C 100000
#define MAX_N 10000
#define MAX_S 1000

#define ABS_MAX 1000000000
#define ABS_MIN -1000000000

int command[MAX_C][2] = { 0 };
int program[MAX_N] = { 0 };
int stack[MAX_S] = { 0 };
char in[4];
int top;

void	init_stack()
{
	top = -1;
}

void	push(int num)
{
	if (top + 1 == MAX_S)
		return;
	stack[++top] = num;
}

int	pop()
{
	int	tmp;

	tmp = stack[top--];
	return (tmp);
}

int	main()
{
	int n, i, j, k, end, quit, tmp1, tmp2, abs, error;
	double res;

	quit = 0;
	while (1)
	{
		i = 0;
		end = 0;
		while (1)
		{
			scanf("%s", in);
			switch (in[0])
			{
			case 'N':
				scanf("%d", &command[i][1]);
				command[i++][0] = NUM;
				break;
			case 'P':
				command[i++][0] = POP;
				break;
			case 'I':
				command[i++][0] = INV;
				break;
			case 'D':
				if (in[1] == 'U')
					command[i++][0] = DUP;
				else
					command[i++][0] = DIV;
				break;
			case 'S':
				if (in[1] == 'W')
					command[i++][0] = SWP;
				else
					command[i++][0] = SUB;
				break;
			case 'A':
				command[i++][0] = ADD;
				break;
			case 'M':
				if (in[1] == 'O')
					command[i++][0] = MOD;
				else
					command[i++][0] = MUL;
				break;
			case 'E':
				end = 1; break;
			case 'Q':
				quit = 1; break;
			}
			if (end || quit) break;
		}
		if (quit)
			break;

		//input value
		scanf("%d", &n);
		for (j = 0; j < n; j++)
			scanf("%d", program + j);
		//run
		for (j = 0; j < n; j++)
		{
			init_stack();
			push(program[j]);
			error = 0;
			for (k = 0; k < i; k++)
			{
				abs = 0;
				switch (command[k][0])
				{
				case NUM:
					push(command[k][1]);
					break;
				case POP:
					if (top >= 0)
						pop();
					else
						error = 1;
					break;
				case INV:
					if (top >= 0)
					{
						tmp1 = pop();
						push(-tmp1);
					}
					else
						error = 1;
					break;
				case DUP:
					if (top >= 0)
					{
						tmp1 = pop();
						push(tmp1);
						push(tmp1);
					}
					else
						error = 1;
					break;
				case SWP:
					if (top >= 1)
					{
						tmp1 = pop();
						tmp2 = pop();
						push(tmp1);
						push(tmp2);
					}
					else
						error = 1;
					break;
				case ADD:
					if (top >= 1)
					{
						tmp1 = pop();
						tmp2 = pop();
						res = tmp2 + tmp1;
						if (res > ABS_MAX || res < ABS_MIN)
							error = 1;
						else
							push(res);
					}
					else
						error = 1;
					break;
				case SUB:
					if (top >= 1)
					{
						tmp1 = pop();
						tmp2 = pop();
						res = tmp2 - tmp1;
						if (res > ABS_MAX || res < ABS_MIN)
							error = 1;
						else
							push(res);
					}
					else
						error = 1;
					break;
				case MUL:
					if (top >= 1)
					{
						tmp1 = pop();
						tmp2 = pop();
						res = (double)tmp2 * tmp1;
						if (res > ABS_MAX || res < ABS_MIN)
							error = 1;
						else
							push(res);
					}
					else
						error = 1;
					break;
				case DIV:
					if (top >= 1)
					{
						tmp1 = pop();
						tmp2 = pop();
						if (tmp1 == 0)
						{
							error = 1;
							break;
						}
						if (tmp1 < 0)
						{
							tmp1 = -tmp1;
							abs++;
						}
						else if (tmp2 < 0)
						{
							tmp2 = -tmp2;
							abs++;
						}
						res = tmp2 / tmp1;
						if (abs == 1)
							res = -res;
						if (res > ABS_MAX || res < ABS_MIN)
							error = 1;
						else
							push(res);
					}
					else
						error = 1;
					break;
				case MOD:
					if (top >= 1)
					{
						tmp1 = pop();
						tmp2 = pop();
						if (tmp1 == 0)
						{
							error = 1;
							break;
						}
						if (tmp2 < 0)
						{
							tmp2 = -tmp2;
							abs++;
						}
						res = tmp2 % tmp1;
						if (abs == 1)
							res = -res;
						if (res > ABS_MAX || res < ABS_MIN)
							error = 1;
						else
							push(res);
					}
					else
						error = 1;
					break;
				}
				if (error)
					break;
			}
			if (error || top != 0)
				printf("ERROR\n");
			else
				printf("%d\n", pop());
		}
		printf("\n");
	}
}
