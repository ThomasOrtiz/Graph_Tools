# Graph Tools

## Graph File Expectation
All graphs (input and output) will be used from a graph/ folder inside the root project directory.

```
(vertex id) (xpos) (ypox)

...

$

(v1) (v2) (weight)

...
```

A valid example file is: 
```
a 0 0

b 2 0

c 1 3

d 5 0

e 3 3

f 1 6

g 5 4

$

a b 1.3

a c 1.1

b d 1.5

b e 1.7

c f 1.6

c e 1.5

e g 1.4
```

## CreateRandomSimpleDiGraph.java
This program creates a new random simple di-graph
 
It guarantees no self-loops and guarantees that every vertex can reach every other vertex.
 
## DupEdges
Given a file it will duplicate the edges in reverse order and appends the new edges onto the given file.

This means, if you have an edge a-->b it will make b-->a

## RenameVertices
This program will rename vertices id from letters to indices or vice versa.
 
It does so by using intToLetters() or lettersToInts().
 
Replace these method calls in renameVertices.java based on how you want your graph renamed.

## AddEdgeWeights.java

AddEdgeWeights expects a file input of the below format. Notice, that differently from the valid example file above, there are no edge weights in the edge section. This utility calculates the edge distances and adds them to match the format above.

```
(vertex id) (xpos) (ypox)

...

$

(v1) (v2)

...
```

