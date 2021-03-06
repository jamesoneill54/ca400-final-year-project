# School of Computing &mdash; Year 4 Project Proposal Form

## SECTION A

|                     |                         |
|---------------------|-------------------------|
|Project Title:       | Modelling An Ant Colony |
|Student 1 Name:      | Kevin Thomas Cleary     |
|Student 1 ID:        | 16373026                |
|Student 2 Name:      | James Edward O'Neill    |
|Student 2 ID:        | 16410652                |
|Project Supervisor:  | Alistair Sutherland     |

## SECTION B

### Introduction

> Describe the general area covered by the project.

This project will aim to properly simulate colonies and individual ants using Artificial Intelligence, and simulate their evolution from generation to generation in the Darwinist style. We will then use our trained ants to solve real world problems such as emergency vehicle response following natural disasters. 

Ants in a colony have different jobs and goals within the colony, such as collecting food, defending the colony, looking after the young, removing rubbish etc. Each of these jobs must be distributed equally among the ants, no matter if some ants die or more ants are born. This project will simulate ants performing their jobs for the upkeep of the colony. 

This project will also, on a smaller scale, aim to outline the importance of [biomimicry][biomimicry-link] in modern design. 

### Outline

> Outline the proposed project.

The project will aim to initially follow a singular ant colony visually, either by 2D or 3D movable graphical interface. The ants will start out with randomly assigned roles, but will switch roles depending on the needs of the colony. They will do this throughout the simulation if some ants die or more are born. All worker ants will initially search for food, and upon finding food will bring it back to the colony and return for more. The ants will use scent trails to signal to other ants their job, and ants will follow the most used path to food, just like real ants. 

Ants in this simulation will change jobs the same way real ants do; by the scent trails of the other ants they pass. For example, if an ant starts detecting fewer worker ants to soldier ants, it will take note of this. If the ant gets to a point where there are a critical low number of worker ants that it has encountered, then it itself will change to a worker ant. 

The simulation will be designed in such a way that with different environmental criteria, the simulation will adapt and will never be the same as a previous iteration. 

We would like to extend this idea by introducing multiple species of ant colonies with different, customizable attributes to simulate the constant warring between ant colonies in any given environment. This would ideally allow each colony to evolve and mutate over time to try and tackle problems set out by both rival colonies and their native environment. We plan to have certain 'locked' attributes inherent in each colony; for example, the speed of one colony could increase over generations whereas an ant's size could be static throughout. This would again be modelled on the real world, where rival colonies across the world have adapted to each other in order to fight for their territory.

Time permitting, we would also like to extend this project to include simulations of natural disasters, and emergency response vehicle's reaction to it. In the case of typhoons, hurricanes, or tsunamis, regular access routes are blocked off, so emergency vehicles must find other ways to get to those in need. We can apply the algorithms used by our trained ants to these emergency vehicles. 

### Background

> Where did the ideas come from?

This idea was originally selected from Alastair Sutherland's list of proposed projects, but we hope to extend and expand upon the basis for this ant colony project as described above. We really want to explore Artificial Intelligence and study machine learning through the lens of [biomimicry][biomimicry-link]. 

### Achievements

> What functions will the project provide? Who will the users be?

This project could be used for educational purposes, both from an artificial intelligence point of view and to outline the importance of biomimicry in technology. The customisation aspect of our simulation will also boost engagement with users and prompt deep exploration into what factors can have a big impact on colonies such as these.

The way ants find their way to and from food sources is particularly interesting, as they have no central control, no hierarchical structure in the colony, yet they still perform so efficiently. This can be very useful for designing technology, as it requires no dedicated central "mind" or "leader", negating the risk of a single point of failure if used in technology. 

There are a lot of real world optimisation applications of this project. Specifically, users could be those designing delivery routes, public transport routes or automated emergency responses. This stems from the way ants will always find the shortest path to a destination.  

### Justification

> Why/when/where/how will it be useful?

Lots of companies spend millions on research and development, yet never look at nature, which has had essentially billions of years of research and development by way of evolution. We think that by simulating ants, we can get emphasise the importance of biomimicry in Artificial Intelligence.  

The simulation of ant colony behaviour in the past has been useful for research surrounding traffic control, self-building robots and drone swarms. The inclusion of simulated environmental changes and rival colonies in this particular project can build upon traffic control etc. by being used to analyse/improve emergency response to natural disasters and aggressive invasions in the real world. A emergency vehicle's ability to traverse the site of a natural disaster can be the difference between life and death for people trapped in the wreckage. 

### Programming language(s)

> List the proposed language(s) to be used.

- Java will be used to create classes for the ants, colonies, environment etc.
- Java will also be used to display our simulation using a Java Visual Library.

### Programming tools / Tech stack

> Describe the compiler, database, web server, etc., and any other software tools you plan to use.

- [ELKStack's ElasticSearch, Kibana & Logstash][elkstack-link] to display logging metrics.
- [Grafana][grafana-link] to visually display metrics.
- JUnit for unit testing. 
- Java Swing/AWT for building our GUI and simulation. 
- GitlabCI for Continuous Integration. 
- [Trello][trello-link] for Scrum and Agile development. 

### Hardware

> Describe any non-standard hardware components which will be required.

- No non-standard hardware components as of this proposal document.

### Learning Challenges

> List the main new things (technologies, languages, tools, etc) that you will have to learn.

- Neither of us have had any past experience with AI outside of this course. We will be delving a lot further into this field for this project and so will have to study various approaches.
- We have both had past experience programming in Java, but we intend to use Java to also display our 2D/3D display of our simulation. This is a new application of Java programming we will need to learn when undertaking this project. We will most likely use [Java Swing][java-swing-link]/[Java AWT][java-awt-link] in order to make a graphical user interface that the user can interact with. 

### Breakdown of work

Over the course of the project we will be using a [Trello][trello-link] board to report, assign and review our work and progress. We will also be assigning each other our Git merge requests. 

![Our Trello Agile board setup](./res/trello-agile-board.png)

**Note:** The following list is only comprised of tasks that we can see immediately. The tasks that are assigned for student 1 and student 2 are subject to change, depending on the needs of the project. For example, with further research we may find that one task should be split up more and divided between the two students instead of being handled by only one of us. 

#### Student 1

> *Kevin's Task Breakdown*

- Integration testing pipeline. 
- Back-end boilerplate (Basic classes and interfaces). 
- GUI ant simulation integration. 
- Training ants epic. 

#### Student 2

> *James' Task Breakdown*

- Logging metrics. 
- GUI Boilerplate. 
- Back-end class development. 
- Simulation design epic. 

### References
- [Boston Globe](https://www.bostonglobe.com/ideas/2018/11/09/what-complex-technology-can-learn-from-simple-ants/nRUJgi2duvaS0tg6Nk7WcL/story.html)
- [Java Swing](https://www.guru99.com/java-swing-gui.html)
- [Java AWT](https://www.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI.html)

[biomimicry-link]: https://biomimicry.org/what-is-biomimicry/
[java-swing-link]: https://www.guru99.com/java-swing-gui.html
[java-awt-link]: https://www.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI.html
[trello-link]: https://trello.com
[elkstack-link]: https://www.elastic.co/what-is/elk-stack
[grafana-link]: https://grafana.com/