#Union-Find / Maze Generation
This was the third individual project for CS3345(Intro to Data Structures and Algorithms) that implements a union-find data structure that uses smart union by size and path compression algorithms. It would use the union find algorithm on the input to attempt to link two vertices together, if they were able to be connected. The find function would search for the leader of the subgraph and use path compression along its search. It would also generate a valid Torus Maze, which is an undirected graph drawn on the surface of a torus(donut).

The input was restricted to the spec, and was not change as of recently so it will work with the specfied commands (see below) or else it will most likely not work or break.

```
Commands (see the sample input and output that follows later)

n       // output your FILENAME, i.e. I output IVPAP3.java
d 10    // create a UnionFind data structure with elements 0,1,2...9
u 7 6   // Call union(7,6), output the root value and the size of the
        // resulting tree. See the sample output below
      
f 7     // Call find(7), output the root index. Keep track
        // of the total path length required in all find operations
      
p       // output the array elements in your UnionFind data structure,
        // space separated on one line
      
s       // output statistics as described below
m 3 30  // create a new UnionFind class with elements 0,1,..,(2^3)*(2^3)-1,
        // generate a Torus Maze as described below and print it
e       // last command of the data
```
An example of how the find function works:
```
Input Data File |  What happens in find() etc.
----------------------------------------------
n               |
d 10            |
u 1 2           |  find(1), path length = 1
                |  find(2), path length = 1
u 3 4           |  find(3), path length = 1
                |  find(4), path length = 1
u 5 6           |  find(5), path length = 1
                |  find(6), path length = 1
u 7 8           |  find(7), path length = 1
                |  find(8), path length = 1
u 2 8           |  find(2), path length = 2
                |  find(8), path length = 2
u 6 1           |  find(6), path length = 2
                |  find(1), path length = 1
p               |  p command: -1 -6 1 -2 3 1 5 1 7 -1
f 3             |  find(3),path length = 1
f 6             |  find(6),path length = 3
f 8             |  find(8),path length = 3
f 2             |  find(2),path length = 2
f 1             |  find(1),path length = 1
f 6             |  find(6),path length = 2
f 8             |  find(8),path length = 2
p               |  p command: -1 -6 1 -2 3 1 1 1 1 -1
s               |  total calls to nd = 19, total path length = 29
e               |  Number of disjoint sets remaining = 4
                |  Mean path length of all nd operations = 1.53
```

An example of expected input/output:
```
Sample Input | Sample Output
----------------------------
n            | JAEVP3.java
d 10         | 7 2
u 7 6        | 3 2
u 3 1        | 0 2
u 0 2        | 0 2
u 0 2        | 3 3
u 1 4        | 7 3
u 5 6        | 8 2
u 8 9        | 3 5
u 2 3        | 7 5
u 6 8        | 7
f 7          | 7
f 5          | 3
f 2          | 7
f 6          | 3 
f 1          | 7
f 5          | 7
f 9          | 3
f 0          | 3 10
u 1 9        | 3
f 8          | 3
f 9          | 3
f 7          | 3 3 3 -10 3 7 7 3 3 3
p            | Number of sets remaining = 1
s            | Mean path length in find = 1.68
m 2 2        | 1 3 1
s            | 1 2 2
e            | 1 14 2
             | 1 15 2
             | 3 5 7 8 1 1 2
             | 1 6 2
             | 1 10 1
             | 1 11 1
             | 0
             | 1 10 2
             | 1 14 2
             | 0
             | 2 13 15 2 2
             | 1 14 1
             | 0
             | 0
             | Number of sets remaining = 1
             | Mean path length in find = 1.67
```
