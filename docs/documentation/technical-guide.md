# Modelling an Ant Colony

Technical Guide

Kevin Cleary - 16373026

James O'Neill - 16410652

Date: 

___

## Table of Contents

1. [Motivation](#1-motivation)
2. [Research](#2-research)
    - [Swarm Intelligence](#21-swarm-intelligence)
    - [Ant Colony Optimisation Algorithm Overview](#22-ant-colony-optimisation-algorithm-overview)
    - [Ant Colony Optimisation Algorithm Metaheuristic](#23-ant-colony-optimisation-algorithm-metaheuristic)
        - [ConstrucAntSolutions](#231-constructantsolutions)
        - [DaemonActions](#232-daemonactions)
        - [PheromoneUpdate](#233-pheromoneupdate)
    - [History of popular ACO Algorithms](#24-history-of-popular-aco-algorithms)
        - [Ant System](#241-ant-system)
        - [Ant Colony System](#242-ant-colony-system)
        - [MAXMIN Ant System](#243-maxmin-ant-system)
    - [Adaptions to Ant Colony Optimisation Algorithm](#25-adaptions-to-ant-colony-optimisation-algorithm)
3. [Design](#3-design)
4. [Implementation](#4-implementation)
5. [Sample Code](#5-sample-code)
6. [Problems Solved](#6-problems-solved)
7. [Results](#7-results)
8. [Future Work](#8-future-work)
9. [Acknowledgements](#9-acknowledgements)

___

## 1. Motivation

Our work on this project was inspired by previous explorations into genetic algorithms and the use of biomimicry in technology. 

In the past, Ant Colony Optimisation (ACO) algorithms have been utilised to solve defined shortest route problems, most notably the Travelling Salesman problem. This has been relayed and reflected in real world applications such as the design of traffic systems. We wanted to adapt this use case and explore the capabilities of an Ant Colony Optimisation in an environment where not every destination had to be visited, but rather find the shortest path between two locations. This would require a deep understanding of previous ACO algorithms to see where changes could be made to optimise a new alorithm specific to this environmnet.

We were motivated by a real life ant colony's ability to use pheromones to find their food sources, and adapt their routes quickly when an obstacle would block their original route. We explored how to make our alorithm 'recalibrate' and create a new shortest path if an obstacle blocked the initial path.

This project allowed us to gain a deeper understanding of the process behind developing a genetic optimisation algorithm -  a field we had an interest in. We put into practice the use of a defined test case where we knew what the optimum length was in order to test the quality of our algorithm. We researched previous probabilistic equations that such algorithms use to learn and choose paths to their benefit, what constants should be used in such equations and the values of these constants.

___

## 2. Research

### 2.1 Swarm Intelligence

Ant Colony Optimisation Algorithms examplify **Swarm Intelligence** (SI).
> Swarm Intelligence: The discipline that deals with natural and artificial systems composed of _many individuals_ that coordinate using _decentralized control_ and _self organizaion_. 

Swarm intelligence focuses on the collective behaviours that result from the local interactions of all the individuals within a system with each other and their environment. In this case we focus on the behaviour of individual ants within a colony as they search for food. 

A typical SI system has the following properties:

- Composed of _many individuals_
- Individuals are relatively _homogeneous_ (they are either all identical or belong to a few typologies)
- Interactions among the individuals are based on _simple behavioural rules_ that exploit _only local information_ that the individuals exchange directly or via the environment (**stigmergy**)
- Overall behaviour of the system results from the interactions of individuals with each other and with their environment - group behaviour _self organizes_.

Swarm intelligence can be split up into **Natural** and **Artificial** research:

- Natural SI research studies biological systems; as well as ants, the behaviour of schools of fish, flocks of birds, termite colonies and herds of land animals are all examples of Natural Swarm Intelligence. 
- Artificial SI focuses on human artifacts eg multi-robot systems.

Swarm intelligence is also divided into two streams; **Scientific** and **Engineering**:

- Scientific stream involves modelling swarm intelligence systems to single out and understand the mechanisms that allow a system as a whole to behave in a coordinated way as a result of local interactions.
- Goal of Engineering stream is to exploit the understanding developed by the scientific stream in order to design systems able to solve problems of practical relevance.

This project is in a **Natural** field, and is based around **both streams**; we are studying the effect of the local individual-individual and individual-environment interactions of our simulated ants on the behaviour on our colony system as a whole, while also applying our resulting algorithm to a real-world route recalibration application.  

### 2.2 Ant Colony Optimisation Algorithm Overview

> Ant Colony Optimisation is a **population-based metaheuristic** that can be used to find approximate solutions to difficult optimization problems.

In ACO, a set of software agents called **artificial ants** search for good solutions to a given optimization problem. To apply ACO, the optimization problem is typically transformed into the problem of _finding the best path on a weighted graph_. The artificial ants (referred to henceforth as 'ants') incrementally build solutions by moving on the graph. The solution construction process is **stochastic** and is biased by a **pheromone model**, that is, a set of parameters associated with graph components (either nodes or edges) whose values are modified at runtime by the ants.

The **Construction Graph**, or Construction Matrix, is the virtual space in which the ants travel. The ants make probabilistic decisions based on pheromone concentrations at each point in the construction matrix.

An ACO is usually used to tackle a **Combinatorial Optimisation Problem** (COP). Marco Dorigo (IRIDIA 1997) defines the COP as a triplet _S_, _Ω_, _f_ where:

- _S_ is a **search space** defined over a finite set of discrete decision variables
    - a set of discrete variable _X<sub>i</sub>_, _i_ = 1, ..., _n_ with values _v_<sup>j</sup><sub>i</sub> ∈ _D_<sub>i</sub> = {v<sup>1</sup><sub>i</sub>, ..., v<sub>i</sub><sup>|Di|</sup>}, is given
    - Elements of _S_ are full assignments; in which each variable _X_<sub>i</sub> has a value v<sup>j</sup><sub>i</sub> assigned from its domain _D_<sub>i</sub>.
- _Ω_ is a **set of constraints** among  the variables
    - Set _S<sub>Ω</sub>_ is given by the elements of _S_ that satisfy all constraints in the set _Ω_
- _f_ : _S_ → _R_<sup>+</sup><sub>0</sub> is an **objective function to be minimised**.
    - as maximising over _f_ is the same as minimising by _-f_, any COP can be considered a minimization problem

A solution s* ∈ _S<sub>Ω</sub>_ is called the **global optimum** if and _only if_:

- _f_(s∗) ≤ _f_(s) ∀s ∈ S<sub>Ω</sub> 

Set of _all globally optimum solutions_ denoted by _S*<sub>Ω</sub>_ ⊆ _S<sub>Ω</sub>_. Solving a COP requires at least one _s*_ ∈ _S*<sub>Ω</sub>_.

The **Travelling Salesman Problem** (TSP) is a well known example of a COP.

### 2.3 Ant Colony Optimisation Algorithm Metaheuristic

> In ACO, artificial ants build a solution to a Combinatorial Optimisation Problem by traversing a _fully connected construction graph_.

- Each instantiated decision variable _X<sub>i</sub>_ = _v<sup>j</sup><sub>i</sub>_ is called a **solution component**, denoted by _c<sub>ij</sub>_. Set of all possible solution components is denoted by _C_.
- The **construction graph** _G<sub>C</sub>(V,E)_ is defined by associating the components C either with the set of vertices _V_ or the set of edges _E_.
- A **pheromone trail value** τ<sub>ij</sub> is associated with each solution component. Allows the probability distribution of different components of the solution to be modelled. Pheromone values are used and updated by the ACO algorithm during the search.
- Ants typically move from vertex to vertex across edges of the construction graph exploiting info provided by the numerous **pheremone trails**, as well as depositing their own.  Δτ, or the **amount of pheromones** deposited depend on the quality of the solution found.

The metaheuristic for an ACO algorithm is as follows:

    Set parameters, initialise pheromone trails

    SCHEDULE_ACTIVITIES
        ConstructAntSolutions
        DaemonActions {optional}
        UpdatePheromones
    END_SCHEDULE_ACIVITIES



The metaheuristic consists of an **initialisation** phase and three **activity** phases executed repeatedly as a schedule of activities. These scheduled activities aren't specified in any given order.

#### 2.3.1 ConstructAntSolutions
- A group of _m_ ants construct solutions from elements of a finite set of available components _C_ = {_c<sub>ij</sub>_}, _i_ = 1, ..., _n_, _j_ = 2, ..., |_D<sub>i</sub>_|. 
    - A solution construction starts with an empty solution s<sup>p</sup> = ∅. 
    - At each construction step, current partial solution s<sup>p</sup> is extended by adding a feasible solution component from the set of feasible neighbours N(s<sup>p</sup>) ⊆ C.
- The choice of a solution component from N(s<sup>p</sup>) is done **probabilistically** at each construction step. Below is Dorigo's method where,
    - τ<sub>ij</sub> is the pheremone value associated with c<sub>ij</sub>
    - η<sub>ij</sub> is the heuristic value associated with c<sub>ij</sub>
    - α and β are positive real parameter whose values determine the _relative importance_ of pheremone vs heuristic info

![Dorigo's equation for the ConstructAntSolutions action](./res/construct-ant-solutions-dorigo.PNG)

#### 2.3.2 DaemonActions
- Refers to any problem specific/centralised actions that _cannot be performed by a single ant_.
- The most used daemon action consists in the application of **local search** to the constructed solutions: the locally optimized solutions are then used to decide which pheromone values to update.

#### 2.3.3 PheromoneUpdate
- The aim is to increase pheromone values associated with good solutions and to decrease pheromone values associated with bad solutions
- A common approach is to _decrease all pheromone levels_ using pheromone **evaporation** and then _increasing pheromone levels associated with good solutions_ by using **_S_<sub>upd</sub>**

- ![PheromoneUpdate action](./res/update-pheromones.PNG)

    - Where _S_<sub>upd</sub> is a set of solutions used for the update, ρ ∈ (0,1] is a parameter for the **evaporation rate** and F:S → R<sup>+</sup><sub>0</sub> is a function such that:

        ``f(s) < f(s′) ⇒ F(s) ≥ F(s′), ∀s ≠ s′ ∈ S`` 

    - F(.) is commonly referred to as the **fitness function**
- Pheromone evaporation implements a useful form of _forgetting_, favouring new areas in the search space
- This is where different systems tend to branch off from each other eg AntColonySystem (Dorigo & Gambardella) or MaxMinAntSystem (Stützle & Hoos)
- There exist different specifications of S<sub>upd</sub>, usually a subset of S<sub>iter</sub> ∪ { S<sub>bs</sub> }
    - S<sub>iter</sub> is the set of solutions constructed from the current iteration of the algorithm
    - S<sub>bs</sub> is the best solution found since the first iteration AKA _best so far_
- Example 1: the **Ant System** Update System
    
    - ![AS Update System](./res/as-update-system.PNG)

- Example 2: the **Iteration-Best** (IB) Update System

    - ![IB Update System](./res/ib-update-system.PNG)

- The IB Update rule contains a much stronger bias towards good solutions than the AS update rule does. This increases the speed of finding a solution but also increases the probability of _premature convergence_.
- An even stronger bias can be found in the **Best-So-Far** (BS) update rule, where S<sub>upd</sub> is set to { S<sub>bs</sub> }
- In practice it is best to use _variations_ of the IB or BS rules which avoid premature convergence to acheive better results than an AS update rule.

### 2.4 History of popular ACO Algorithms

#### 2.4.1 Ant System

Dorigo et. al, 1991-1996

> The Ant System (AS) has virtual ants construct solutions on a construction graph and updates pheromones between components on each iteration.

The first Ant Colony Optimisation Algorithm to be proposed in literature. Pheromone values are updated for _all_ of the ants that have completed the tour. Solution components c<sub>ij</sub> are the edges of the graph and the pheromone update for τ<sub>ij</sub> (the pheromone joining nodes i and j) is as follows

- ![Pheomone Update for AS](./res/as-pheromone-update.PNG)

    - ρ ∈ (0,1] is the evaporation rate of pheromone trails
    - _m_ is the number of ants
    - Δτ<sup>k</sup><sub>ij</sub> is the quantity of pheromones laid on edge (i, j) by ant k
        - ![Pheromone Quantity](./res/as-pheromone-quantity.PNG)
        - where _L<sub>k</sub>_ is the tour length of the _k_-th ant 

When constructing their solutions, the ants traverse the construction graph making a probabilistic decision at each vertex. The **transition probability** of the k-th ant moving between nodes i and j is given by:

- ![Transition Probability](./res/as-transition-probability.PNG)
    - N(s<sup>p</sup><sub>k</sub>) is the set of components that do not yet belong to the partial solution s<sup>p</sup><sub>k</sub> of ant _k_
    - α and β are parameters that control the relative importance of the pheromone versus the heuristic information η<sub>ij</sub> = 1/d<sub>ij</sub>, where d<sub>ij</sub> is the length of the component c<sub>ij</sub> (ie of edge (i,j))

#### 2.4.2 Ant Colony System 

Dorigo & Gambardella, 1997

> The Ant Colony System (ACS) introduces new random factor _q_ and a local pheromone update rule to diversify solution construction.

First major improvement over the original Ant System. First innovations made in a system known as _Ant-Q_, by the same authors.

 New additions include the form of the **decision rule** used by ants during the construction process. Ants in ACS use the **psuedorandom porportional** rule: the probability that an ant moves from node _i_ to _j_ depends on a random variable _q_ uniformly distributed over [0,1], and a parameter _q<sub>0</sub>_. If q <= q<sub>0</sub> then, among the feasible components, the component to maximise the product of τ<sub>il</sub>η<sup>β</sup><sub>il</sub> is chosen. Otherwise the same equation as AS is used.

This is a **greedy rule** which favours exploitation of the pheromone information, and must be counterbalanced with the introduction of a diversifying component, the **Local Pheromone Update** (LPU). Local pheromone update is performed by all ants after each construction step. Each ant only applies it to the _last edge traversed_.

- ![Local Pheromone Update](./res/acs-local-pheromone-update.PNG)
    - Where φ ∈ (0,1] is the _pheromone decay coefficient_ and τ<sub>0</sub> is the initial pheromone value.

The main goal of LPU is to _diversify_ the search performed by subsequent ants during one iteration. Decreasing the pheromone concentrations on traversed edges in an iteration encourages other following ants to choose different edges and form different solutions. Local Pheromone Update also means the minimum values of the pheromone are limited.

An **offline pheromone update** is perfomed at the end of the construction process, as in AS. This is performed _only by the best ant_ -  only the edges visited by the best ant are updated according to the following equation

- ![Offline Pheromone Update](./res/acs-offline-pheromone-update.PNG) 
    - if the best ant used edge (i,j) in its tour, then Δτ<sup>best</sup><sub>ij</sub> = 1/L<sub>best</sub>
    - L<sub>best</sub> can be either the length of the iteration best or that of the best-so-far
    - otherwise, Δτ<sup>best</sup><sub>ij</sub> = 0

#### 2.4.3 MAXMIN Ant System 

Stützle & Hoos, 2000

> In MAXMIN ant system (MMAS), _only the best ant adds pheromone trails_, and the minimum and maximum values of the pheromone are _explicitly_ limited (AS and ACS are implicitly limited)

The pheromone update equation takes the form

- ![Pheromone Update Equation](./res/mm-pheromone-update-rule.PNG)
- if the best ant used edge (i,j) in its tour, then Δτ<sup>best</sup><sub>ij</sub> = 1/L<sub>best</sub>
    - L<sub>best</sub> can be either the length of the iteration best or that of the best-so-far
- Pheromone values are _constrained_ between τ<sub>min</sub> and τ<sub>max</sub> by verifying that each pheromone value after being updated by the ant is within these constraints
- τ<sub>ij</sub> is set to τ<sub>max</sub> _if_ τ<sub>ij</sub> > τ<sub>max</sub> and is set to τ<sub>min</sub> if τ<sub>ij</sub> < τ<sub>min</sub>
- As in AS but contrary to ACS, the pheromone update rule applies to _all edges_ at the end of an iteration rather than those traversed by the best ants.
- τ<sub>min</sub> is usually _experimentally chosen_ (Stutzle & Hoos have developed a theory on how to define this value analytically)
- τ<sub>max</sub> can be calculated analytically _if_ the optimum ant tour length is known
    - for TSP, optimum tour length would be 1 / (ρ ⋅ _L*_) , where _L*_ is the length of the optimal tour.
    - _L*_ can be substitued with _L<sub>bs</sub>_ if _L*_ not known

The algorithm sets the initial values of its trails to L<sub>max</sub> and stops when no improvement can be seen for a given number of iterations.

### 2.5 Adaptions to Ant Colony Optimisation Algorithm

The problem we are looking to solve with our system differs from the COPs tackled by the systems described above in various ways. As such, our design and implementation need to differ slightly from these systems based on the requirements of our problem.

The problem we wish to solve is as follows:

> Develop a system modelled on the behaviour of an ant colony to find the shortest route from the Home Node (an 'anthill') to a specified Goal Node (a 'food source') in a matrix, using pheromone value as a determining factor of an ants' trail.

Using the Travelling Salesman Problem (TSP) as a reference for a typical Combinatorial Optimisation Problem, we can identify the following differences:

- In a TSP, the objective is to find the shortest path around an **entire matrix** of differently weighted nodes, attempting to find the shortest path visiting every possible node and returning home. 
    - Our own problem does not require that every node in the matrix is visited, but rather that every ant begins at the same node and should end at the same, sperate node.  
- Following on from this, the solution set of an Ant System solving a TSP comprises of a **list of edges** between pairs of nodes within the construction matrix, and the shortest path is the list with the lowest _sum of values_. 
    - For the purpose of our own problem and also our visual simulation, our solution set will comprise of a **list of nodes**, each equidistant from each of its neighbouring nodes. The shortest path will be the list with the _smallest number of elements_.
- When selecting a new node in all previous ACO systems, ants use a distance heuristic which grows in prominence the closer a solution is to construction. This is based on the number of nodes in the matrix left to be visited. 
    - As our problem does not specify this number of nodes, we have to redefine this distance heuristic. We have decided to base our distance heuristic on the distance from the current ants position from the home node. Our probabilitic function favours nodes further away from the home node.

___

## 3. Design

___

## 4. Implementation

___

## 5. Sample Code

___

## 6. Problems Solved

___

## 7. Results

___

## 8. Future Work

___

## 9. Acknowledgements