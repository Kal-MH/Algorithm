# 배열 선언
arr = [0, 0, 0, 0, 0, 0];
arr = [0 for i in range(6)];
arr = [0] * 6;

arr = list(range(6));

# 배열과 차원
arr = [[0, 1, 2], [3, 4, 5], [6, 7, 8]];
print(arr);
print(arr[2][2]);

arr[2][2] = 15;

print(arr[2][2]);

arr = [[1] * 3 for _ in range(3)];

# 배열의 효용성
# 1. 할당할 수 있는 메모리 크기를 확인해라
# 2. 중간에 데이터 삽입이 많은지 확인해라
#  - 배열은 데이터를 삽입하거나 삭제할 때 데이터를 옮겨야 하므로 시간이 많이 걸린다.

# 데이터 추가
my_list = [1, 2, 3, 4, 5]
my_list.append(4)
my_list = my_list + [4, 5];
my_list.insert(2, 999)

# 데이터 삭제

my_list.pop(2); 
my_list.remove(2);

# 리스트 컴프리헨션
numbers=[1, 2, 3, 4, 5];
squares = [num**2 for num in numbers]

print(numbers) # number 값 자체는 변하지 않음
print(squares)

# 리스트 연관 메서드
fruits = ['grape', 'orange', 'apple', 'banana', 'cherry', 'strawberry', 'apple']
print(len(fruits))
print(fruits.index('cherry'))
print(fruits.count('apple'))

sorted_fruits = sorted(fruits)
print(sorted_fruits)