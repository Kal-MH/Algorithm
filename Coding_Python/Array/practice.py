# append()메서드로 데이터 추가
my_list = [1, 2, 3];
my_list.append(4);

# +연산자로 데이터 추가
plus_list = [5, 6];
my_list = my_list + plus_list;

# insert()메서드로 데이터 삽입
my_list.insert(2, 9999)

# pop()메서드로 데이터 팝 (데이터의 위치를 지정)
popped_element = my_list.pop(2)
# print(popped_element)
# print(my_list)

# remove()메서드로 데이터 삭제 (데이터 자체를 지정)
my_list.remove(6)

# print(my_list)

# 리스트 컴프리헨션
numbers=[1, 2, 3, 4, 5];
squares = [num**2 for num in numbers]

print(squares)

# 리스트 연관 메서드
fruits = ['grape', 'orange', 'apple', 'banana', 'cherry', 'strawberry', 'apple']
print(len(fruits))
print(fruits.index('cherry'))
print(fruits.count('apple'))

sorted_fruits = sorted(fruits)
print(sorted_fruits)