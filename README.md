# KronTest
# Task
Locomotives:

The railway network is defined by a set of stations. There are paths between some stations. The length of the paths is given by integers. Bidirectional single-track tracks (trains running along the same path to meet each other).

Train routes are given by the list of stations through which they pass. Train speeds are the same (say, equal to one). Trains start moving at the same time. After reaching the final station the train disappears. The train is a point. Task: For a given network and train configuration, determine if a collision will occur.

What is required to show:

Ability to create a realistic and adequate objective of the object model, possession of OOP concepts.

Requirements for registration:

Programming language (java). The configuration is set in external files. The presence of unit tests is desirable.

# Collisions
- If two trains arrive at one end station at the same time, they will collide. Similarly, if one arrives at the station as the final one, and the other as an intermediate one.

# Files Format
Name pattern: stations*, trains*

## Network
The first line is the number of stations. In each next line we indicate the numbers of the connected stations and the distance between them through a space. Numbering from scratch. Example:
```
5
0 2 1
1 2 5
1 3 5
2 3 3
3 4 2
```

## Trains
The first line is the number of trains. Each next line contains the station numbers separated by a space for the i-th train. Example:
```
2
1 2 0
4 3 2 0
```
