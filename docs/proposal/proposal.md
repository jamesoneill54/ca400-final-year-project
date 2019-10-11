# School of Computing &mdash; Year 4 Project Proposal Form

> Edit (then commit and push) this document to complete your proposal form.
> Make use of figures / diagrams where appropriate.
>
> Do not rename this file.

## SECTION A

|                     |                         |
|---------------------|-------------------------|
|Project Title:       | Modelling an ant colony |
|Student 1 Name:      | Kevin Thomas Cleary     |
|Student 1 ID:        | 16373026                |
|Student 2 Name:      | James Edward O'Neill    |
|Student 2 ID:        | 16410652                |
|Project Supervisor:  | xxxxxx                  |

> Ensure that the Supervisor formally agrees to supervise your project; this is only recognised once the
> Supervisor assigns herself/himself via the project Dashboard.
>
> Project proposals without an assigned
> Supervisor will not be accepted for presentation to the Approval Panel.

## SECTION B

> Guidance: This document is expected to be approximately 3 pages in length, but it can exceed this page limit.
> It is also permissible to carry forward content from this proposal to your later documents (e.g. functional
> specification) as appropriate.
>
> Your proposal must include *at least* the following sections.


### Introduction

> Describe the general area covered by the project.

Ants in a colony have different jobs and goals within the colony, such as collecting food, defending the colony, looking after the young, removing rubbish etc. Each of these jobs must be distributed equally among the ants, no matter if some ants die or more ants are born. This project will simulate ants performing their jobs for the upkeep of the colony. 

This project will aim to properly simulate colonies and individual ants using Artificial Intelligence, and simulate their evolution from generation to generation in the Darwinist style. 

This project will also, on a smaller scale, aim to outline the importance of [biomimicry](biomimicry-link) in modern design. 

### Outline

> Outline the proposed project.

The project will aim to initially follow a singular ant colony visually, either by 2D or 3D movable graphical interface. The ants will start out with randomly assigned roles, but will switch roles depending on the needs of the colony. They will do this throughout the simulation if some ants die or more are born. All worker ants will initially search for food, and upon finding food will bring it back to the colony and return for more. The ants will use scent trails to signal to other ants their job, and ants will follow the most used path to food, just like real ants. 

Ants in this simulation will change jobs the same way real ants do; by the scent trails of the other ants they pass. For example, if an ant starts detecting fewer worker ants to soldier ants, it will take note of this. If the ant gets to a point where there are a critical low number of worker ants that it has encountered, then it itself will change to a worker ant. 

The simulation will be designed in such a way that with different environmental criteria, the simulation will adapt and will never be the same as a previous iteration. 

We would like to extend this idea by introducing multiple species of ant colonies with different, customizable attributes to simulate the constant warring between ant colonies in any given environment. This would ideally allow each colony to evolve and mutate over time to try and tackle problems set out by both rival colonies and their native environment. We plan to have certain 'locked' attributes inherent in each colony; for example, the speed of one colony could increase over generations whereas an ant's size could be static throughout. This would again be modelled on the real world, where rival colonies across the world have adapted to each other in order to fight for their territory.

### Background

> Where did the ideas come from?

This idea was originally selected from Alastair Sutherland's list of proposed projects, but we hope to extend and expand upon the basis for this ant colony project as described above. We really want to explore Artificial Intelligence and study the behaviour of Neural Networks through the lens of [biomimicry](biomimicry-link). 

### Achievements

> What functions will the project provide? Who will the users be?

This project could be used for educational purposes, both from an artificial intelligence point of view and to outline the importance of biomimicry in technology. The customisation aspect of our simulation will also boost engagement with users and prompt deep exploration into what factors can have a big impact on colonies such as these.

The way ants find their way to and from food sources is particularly interesting, as they have no central control, no hierarchical structure in the colony, yet they still perform so efficiently. They innately know what to do and how to do it in order to benefit the colony as a whole. This can be very useful for designing technology, as it requires no dedicated central "mind" or "leader", negating the risk of a single point of failure if used in technology. 

Also, the way ants move from anthill to food source is of particular interest. They will always find they shortest path to a destination by use of pheromones. This behaviour would be very useful for everyday use (ie. for delivery services, emergency services or public transport) in order to find the shortest path to and from a destination. 

### Justification

> Why/when/where/how will it be useful?

Lots of companies spend millions on research and development, yet never look at nature, which has had essentially billions of years of research and development by way of evolution. We think that by simulating ants, we can get emphasise the importance of biomimicry in Artificial Intelligence.  

### Programming language(s)

> List the proposed language(s) to be used.

- Java will be used to create classes for the ants, colonies, environment etc
- Java will also be used to display our simulation

### Programming tools / Tech stack

> Describe the compiler, database, web server, etc., and any other software tools you plan to use.

- ELKStack's ElasticSearch & Kibana to display logging metrics
- Grafana to visually display metrics
- GitlabCI for Continuous Integration

### Hardware

> Describe any non-standard hardware components which will be required.

- No non-standard hardware components as of this proposal document

### Learning Challenges

> List the main new things (technologies, languages, tools, etc) that you will have to learn.

- Neither of us have had any past experience with AI outside of this course. We will be delving a lot further into this field for this project and so will have to study various approaches
- We have both had past experience programming in Java, but we intend to use Java to also display our 2D/3D display of our simulation. This is a new application of Java programming we will need to learn when undertaking this project.

### Breakdown of work

> Clearly identify who will undertake which parts of the project.
>
> It must be clear from the explanation of this breakdown of work both that each student is responsible for
> separate, clearly-defined tasks, and that those responsibilities substantially cover all of the work required
> for the project.

#### Student 1

> *Student 1 should complete this section.*

#### Student 2

> *Student 2 should complete this section.*

### References
[Boston Globe](https://www.bostonglobe.com/ideas/2018/11/09/what-complex-technology-can-learn-from-simple-ants/nRUJgi2duvaS0tg6Nk7WcL/story.html)

[biomimicry-link]: https://biomimicry.org/what-is-biomimicry/