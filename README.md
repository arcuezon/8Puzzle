# 8 Puzzle solver using the A* Search Algorithm

<img src ="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/8puzzle_example.svg/1280px-8puzzle_example.svg.png">
<a href="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/8puzzle_example.svg/1280px-8puzzle_example.svg.png">Image Source</a>
<br><br>

This is an assignment to the <a href = "https://www.coursera.org/learn/algorithms-part1/">Algorithms, Part 1</a> course offered on Coursera 
in collaboration with Princeton University. This implementation includes a Board class and Solver class to represent and solve the puzzles.

This application uses the A* Search Algorithm to solve an NxN sized 8 puzzle. Specifically, the algorithm is implemented using priority queues with the priorities set to be the 
Manhattan Priority Function. The A* algorithm used is Critically Optimized and the Priorities are cached instead of having to recompute priorities with every compare.
