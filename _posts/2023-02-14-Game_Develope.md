---
layout: single
title:  "jupyter notebook 변환하기!"
categories: coding
tag: [python, blog, jekyll]
toc: true
author_profile: false
---

<head>
  <style>
    table.dataframe {
      white-space: normal;
      width: 100%;
      height: 240px;
      display: block;
      overflow: auto;
      font-family: Arial, sans-serif;
      font-size: 0.9rem;
      line-height: 20px;
      text-align: center;
      border: 0px !important;
    }

    table.dataframe th {
      text-align: center;
      font-weight: bold;
      padding: 8px;
    }

    table.dataframe td {
      text-align: center;
      padding: 8px;
    }

    table.dataframe tr:hover {
      background: #b8d1f3; 
    }

    .output_prompt {
      overflow: auto;
      font-size: 0.9rem;
      line-height: 1.45;
      border-radius: 0.3rem;
      -webkit-overflow-scrolling: touch;
      padding: 0.8rem;
      margin-top: 0;
      margin-bottom: 15px;
      font: 1rem Consolas, "Liberation Mono", Menlo, Courier, monospace;
      color: $code-text-color;
      border: solid 1px $border-color;
      border-radius: 0.3rem;
      word-break: normal;
      white-space: pre;
    }

  .dataframe tbody tr th:only-of-type {
      vertical-align: middle;
  }

  .dataframe tbody tr th {
      vertical-align: top;
  }

  .dataframe thead th {
      text-align: center !important;
      padding: 8px;
  }

  .page__content p {
      margin: 0 0 0px !important;
  }

  .page__content p > strong {
    font-size: 0.8rem !important;
  }

  </style>
</head>


Game_Develope



2023-02-10



<details>

<summary>

 문제 설명

</summary>



문제

1x1 크기의 정사각형으로 이뤄진 NxM 크기의 직사각형으로 각각의 칸은 육지 또는 바다.



캐릭터는 동서남북 중 한 곳을 바라봄



 



맵의 칸은 각 (A, B)로 나타낼 수 있으며 A는 북쪽으로부터 떨어진 칸의 개수. B는 서쪽으로부터 떨어진 칸의 개수이다. 캐릭터는 상하좌우로 움직일 수 있고, 바다로 되어 있는 공간에는 갈 수 없다. 캐릭터의 움직임을 설정하기 위해 정해 놓은 메뉴얼은 이러하다.



 



1. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향(반시계 방향으로 90도 회전하는 방향)부터 차례대로 갈 곳을 정함



2. 캐릭터의 바로 왼쪽 방향에 아직 가보지 않은 칸이 존재한다면, 왼쪽 방향으로 회전한 다음 왼쪽으로부터 한칸을 전진한다.



왼쪽 방향에 가보지 않은 칸이 없다면, 왼쪽 방향으로 회전만 수행하고 1단계로 돌아간다.



3.만약 네방향 모두 이미 가본 칸이거나 바다로 되어 있는 칸인 경우에는, 바라보는 방향을 유지한 채로 한 칸 뒤로 가고 1단계로 돌아간다. 단, 이때 뒤쪽 방향이 바다인 칸이라 뒤로 갈 수 없는 경우에는 움직임을 멈춘다.



 



위 과정을 반복적으로 수행하면서 캐릭터의 움직임에 이상이 있는지 테스트하려고한다. 메뉴얼에 따라 캐릭터를 이동시킨 뒤에, 캐릭터가 방문한 칸의 수를 출력하는 프로그램을 만드시오.



 



[입력조건]



* 첫째 줄에 맵의 세로 크기 N과 가로 크기 M을 공백으로 구분하여 입력(3<=N, M<=50)



* 둘째 줄에 게임 캐릭터가 있는 칸의 좌표와 바라보는 방향 d 가 각각 서로 공백으로 구분하여 주어짐 방향 d의 값으로는



0 : 북



1 : 동



2 : 남



3 : 서



* 셋째 줄부터 맵이 육지인지 바다인지 정보 주어짐 



육지 : 0



바다 : 1



 



처음 캐릭터가 위치한 칸은 항상 육지



이동을 마친 후 캐릭터가 방문한 칸의 수를 출력한다.



 



[입력예시]



4 4



1 1 0



1 1 1 1



1 0 0 1



1 1 0 1



1 1 1 1



<출력 예시>

3

</details>



이번 문제는 구현에 충실하면 풀 수 있는 문제이다. 하지만 구현하기에 앞서 내가 몰랐던 구현 테크닉들을 배우고 그것에 대한것을 정리 하는것이 더 실력 향상에 도움 될 것 같다.





#### 소스 코드



```python
#n과 m을 공백으로 입력 받기.
n,m = map(int,input().split())
```


```python
#리스트 컴프리핸션을 이용 m,n크기 만큼 배열 초기화
d = [[0]*m for _ in range(n)]
#좌표와 방향을 입력받고 현재 좌표 방문 처리
x,y,direction = map(int, input().split())
d[x][y] = 1
```


```python
#n,m크기의 2차원 배열 입력받기 배열에 append를 써서 1차원 배열을 추가
array = []
for i in range(n) :
    array.append(list(map(int, input().split())))
#북,동,남,서
dx = [-1,0,1,0]
dy = [0,1,0,-1]
```

#### 배열에서 x,y축은 어떻게 될까?

흔히 수학에서 쓰이는 좌표축을 생각한다면 x,y 좌표가 헷갈릴수 있다.  

하지만 컴퓨터 공학에서는 배열을 저장할때 V[x][y] 이렇게 좌표축을 설정하므로 아래의 표 처럼 진행된다.



| x,y | y = 0 | y = 1 | y = 2 |

| :---- | :------ |:------- | :------ |

| x = 0 | v[0][1] | v[0][2] | v[0][3] |

| x = 1 | v[1][1] | v[1][2] | v[1][3] |

| x = 2 | v[2][1] | v[2][2] | v[2][3] |




```python
#방향 바꾸기 함수 방향은 숫자로 표현 된다. 방향 정보는 0 북 1 동 2 남 3 서
def turn_left() :
    global direction
    direction -= -1
    if direction == -1 :
        direction = 3
```


```python
count = 1
turn_time = 0

while(True) :
    turn_left()
    nx = x + dx[direction]
    ny = y + dy[direction]

    if d[nx][ny] == 0 and array[nx][ny] == 0 :
        x = nx
        y = ny
        count += 1
        turn_time = 0
        continue

    else :
        turn_time += 1
    
    if turn_time == 4 :
        nx = x - dx[direction]
        ny = y - dy[direction]
        if array[nx][ny] == 0 :
            x = nx
            y = ny
        else :
            break
        turn_time = 0

print(count)

```


```python
```
