# mancala
Code for versions of the classic board game Mancala.

SUMMARY:
I made this project to practice concepts I learned in my OOD class.  
I've played Mancala at home since I was a kid, and this game in particular had a few special attributes making it great to write:
-I knew how to play it 
-Easy to represent visually, no need to create my own animations
-The game itself has a discrete set of moves and strategies, meaning I could create a CPU opponent without any learning capabilities

In short, I felt a game that was simple to learn, but hard to master, was a great starting point for a personal project. 

NAVIGATION:
This was designed with MVC architecture, allowing for new components to be added in the future.  
Classes and interfaces should be grouped together by Model, View, and Controller. 
The strategy pattern is the CPU opponent, which is kind of part of the Controller, since the model doesn't care who is playing the game.
