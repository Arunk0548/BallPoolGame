#To Run
~~~
javac BallPoolGameRunner.java
java BallPoolGameRunner

Enter initial position of ball :
3
4
Enter position of point on cushion:
0
0
Enter speed of ball (in cm/s).(Between 0 and 1000 cm/s):
1
Enter travelling time (in seconds):
15
final position of the ball: (6,8)
~~~

# Problem Statement
Problem Statement A game of pool is played on a frictionless rectangular table. Imagine that the table is represented by the coordinates (0,0), (0, 142), (284, 142) and (284,0) where the coordinate axes are measured in centimetres. A player places a ball anywhere on the table (but not on the cushions) and hits the ball to a point on one of the cushions. The ball travels at a constant velocity rebounding off the cushions. Write a program that accepts as input from the user, the initial position of the ball, the position of the point at one of the cushions at which the ball makes its first impact, the time the ball travels around the table, and as output, the final position of the ball. 
#Assumptions:
● All collisions with the cushions are perfectly elastic, and there is no spin imparted on the ball i.e. angle of incidence = angle of reflection. 
● The ball is a point object 
● The table is pocketless 
● The cushions have no “thickness” and are represented by the boundaries of the rectangle shown in the diagram above. Note that the diagram is just for visualization purposes only! 
● The maximum speed at which the ball can travel is at 1000 cm/s 
● If the ball hits the corner, it rebounds “straight back”

#Here is how the input should look:
Enter initial position: (3,4)
Enter position of point on cushion: (0,0)
Enter speed of ball (in cm/s). (Between 0 and 1000 cm/s) : 1
Enter travelling time (in seconds): 15

#Here is the output:
Final position of ball: (6,8)
