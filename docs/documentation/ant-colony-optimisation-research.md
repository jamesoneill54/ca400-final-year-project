# Ant Colony Optimisation

## Contents

make use of the following

http://www.scholarpedia.org/article/Ant_colony_optimization

https://towardsdatascience.com/swarm-intelligence-inside-the-ant-colony-9ffbce22a736

## Swarm Intelligence

The discipline that deals with natural and artificial systems composed of many individuals that coordinate using decentralized control and self organizaion. Focuses on the collective behaviours that result from the local interactions of the individuals with each other with their environment. As well as ants, termites, schools of fish, flocks of birds, herds of land animals and some human artifacts eg multi-robot systems.

- **Natural** SI research studies biological systems whereas **Artificial** SI focuses on human artifacts
- **Scientific** stream models swarm intelligence systems and to single out and understand the mechanisms that allow a system as a whole to behave in a coordinated way as a result of local individal-individual and individual-environment interactions. Goal of **Engineering** stream is to exploit the understanding developed by the scientific stream in order to design systems able to solve problems of practical relevance. This project is a Natrual study in an Engineering stream.

Typical SI system has the following properties:

- Composed of many individuals
- Individuals are relatively homogeneous (they are either all identical or belong to a few typologies)
- Interactions among the individuals are based on simple behavioural rules that exploit only local information hat the individuals exchange directly or via the environment (stigmergy)
- Overall behaviour of the system results from the interactions of individuals with each other and with their environment - group behaviour self organizes.

## 1.

Marco Dorigo, IRIDIA

Ant Colony Optimisation is a population-based metaheuristic that can be used to find approximate solutions to difficult optimization problems.

In ACO, a set of software agents called artificial ants search for good solutions to a given optimization problem. To apply ACO, the optimization problem is transformed into the problem of finding the best path on a weighted graph. The artificial ants (hereafter ants) incrementally build solutions by moving on the graph. The solution construction process is stochastic and is biased by a pheromone model, that is, a set of parameters associated with graph components (either nodes or edges) whose values are modified at runtime by the ants.

Construction Graph: the virtual space in which the ants travel

The ants make probabilistic decisions based on pheromone concentrations at each point in the construction matrix.

The typical problem tackled by an ACO is the Tavelling Salesman Problem. An example of a Combinatorial Optimisation Problem (COP).

Dorigo defines the COP as a triplet _S_, _Ω_, _f_ where:

- _S_ is a **search space** defined over a finite set of discrete decision variables
    - a set of discrete variable _X<sub>i</sub>_, _i_ = 1, ..., _n_ with values _v_<sup>j</sup><sub>i</sub> ∈ _D_<sub>i</sub> = {v<sup>1</sup><sub>i</sub>, ..., v<sub>i</sub><sup>|Di|</sup>}, is given
    - Elements of _S_ are full assignments; in which each variable _X_<sub>i</sub> has a value v<sup>j</sup><sub>i</sub> assigned from its domain _D_<sub>i</sub>.
- _Ω_ is a **set of constraints** among  the variables
    - Set _S<sub>Ω</sub>_ is given by the elements of _S_ that satisfy all constraints in the set _Ω_
- _f_ : _S_ → _R_<sup>+</sup><sub>0</sub> is an **objective function to be minimised**.
    - as maximising over _f_ is the same as minimising by _-f_, any COP can be considered a minimization problem

A solution s* ∈ _S<sub>Ω</sub>_ is called the **global optimum** if and _only if_:

- _f_(s∗) ≤ _f_(s) ∀s ∈ S<sub>Ω</sub> 

Set of _all globally optimum solutions_ denoted by _S*<sub>Ω</sub>_ ⊆ _S<sub>Ω</sub>_. Solving a COP requires at least one s* ∈ _S*<sub>Ω</sub>_.

### The ACO Metaheuristic

In ACO, artificial ants build a solution to a COP by traversing a _fully connected construction graph_.

- Each instantiated decision variable _X<sub>i</sub> = v<sup>j</sup><sub>i</sub> is called a _solution component_, denoted by _c<sub>ij</sub>_. Set of all possible solution components is denoted by _C_.
- The construction graph _G<sub>C</sub>(V,E)_ is defined by associating the components C either with the set of Vertices _V_ or the set of edges _E_.
- A pheromone trail value τ<sub>ij</sub> is associated with each solution component. Allow the probability distribution of different components of the solution to be modelled. Pheromone values are used and updated by the ACO algorithm during the search
- ants move from vertex to vertex across edges of the construction graph exploiting info provided by the numerous pheremone trails, as well as depositing their own.  Δτ, or the amount of pheromone deposited depend on the quality of the solution found

The metaheuristic:

    Set parameters, initialise pheromone trails
    SCHEDULE_ACTIVITIES
        ConstructAntSolutions
        DaemonActions {optional}
        UpdatePheromones
    END_SCHEDULE_ACIVITIES



The metaheuristic consists of an **initialisation** phase and three **activity** phases executed repeatedly as a schedule of activities. These scheduled activities aren't specified in any given order

1. ConstructAntSolutions
    - A group of _m_ ants construct solutions from elements of a finite set of available components _C_ = {_c<sub>ij</sub>_}, _i_ = 1, ..., _n_, _j_ = 2, ..., |_D<sub>i</sub>_|. A solution construction starts with an empty solution s<sup>p</sup> = ∅. At each construction step, current partial solution s<sup>p</sup> is extended by adding a feasible solution component from the set of feasible neighbours N(s<sup>p</sup>) ⊆ C.
    - The choice of a solution component from N(s<sup>p</sup>) is done probabilistically at each construction step. Below is Dorigo's method where,
        - τ<sub>ij</sub> is the pheremone value associated with c<sub>ij</sub>
        - η<sub>ij</sub> is the heuristic value associated with c<sub>ij</sub>
        - α and β are positive real parameter whose values determine the _relative importance_ of pheremone vs heuristic info

    ![Dorigo's equation for the ConstructAntSolutions action](./res/construct-ant-solutions-dorigo.png)

2. DaemonActions
    - problem specific/centralised actions that cannot be performed by a single ant
    - The most used daemon action consists in the application of local search to the constructed solutions: the locally optimized solutions are then used to decide which pheromone values to update.

3. PheremoneUpdate
    - the aim is to increase pheromone values associated with good solutions and to decrease pheromone values associated with bad solutions
    - A common approach is to _decrease all pheromone levels_ using pheromone **evaporation** and then _increasing pheromone levels associated with good solutions_ by using **_S_<sub>upd</sub>**

    ![PheromoneUpdate action](./res/update-pheromones.png)

    - Where _S_<sub>upd</sub> is a set of solutions used for the update, ρ ∈ (0,1] is a parameter for the **evaporation rate** and F:S → R<sup>+</sup><sub>0</sub> is a function such that:

            f(s) < f(s′) ⇒ F(s) ≥ F(s′), ∀s ≠ s′ ∈ S .

    - F(.) is commonly referred to as the **fitness function**
    - Pheromone evaporation implements a useful form of _forgetting_, favouring new areas in the search space
    - Different systems eg AntColonySystem (Dorigo & Gambardella) or MaxMinAntSystem (Stützle & Hoos)
    - There exist different specifications of S<sub>upd</sub>, usually a subset of S<sub>iter</sub> ∪ { S<sub>bs</sub> }
        - S<sub>iter</sub> is the set of solutions constructed from the current iteration of the algorithm
        - S<sub>bs</sub> is the best solution found since the first iteration AKA _best so far_
    - Example 1: the AS Update System
        
    ![AS Update System](./res/as-update-system.png)

    - Example 2: the Iteration-Best (IB) Update System

    ![IB Update System](./res/ib-update-system.png)

    - The IB Update rule contains a much stronger bias towards good solutions than the AS update rule does. This increases the speed of finding a solution but also increases the probability of _premature convergence_.
    - An even stronger bias can be found in the BS update rule, where S<sub>upd</sub> is set to {S<sub>bs</sub>}
    - In practice it is best to use_variations_ of the IB or BS rules which avoid premature convergence to acheive better results than an AS update rule.

## 2. Existing ACO Implementations

### 1. Ant System (Dorigo et. al, 1991-1996)

The first Ant Colony Optimisation Algorithm to be proposed in literature. Pheromone values are updated for _all_ of the ants that have completed the tour. Solution components c<sub>ij</sub> are the edges of the graph and the pheromone update for τ<sub>ij</sub> (the pheromone joining nodes i and j) is as follows

![Pheomone Update for AS](./res/as-pheromone-update.png)

- ρ ∈ (0,1] is the evaporation rate of pheromone trails
- _m_ is the number of ants
- Δτ<sup>k</sup><sub>ij</sub> is the quantity of pheromones laid on edge (i, j) by ant k
    - ![Pheromone Quantity](./res/as-pheromone-quantity.png)
    - where _L<sub>k</sub>_ is the tour length of the _k_-th ant 
- When constructing their solutions, the ants traverse the construction graph making a probabilistic decision at each vertex. The **transition probability** of the k-th ant moving between nodes i and j is given by:
    - ![Transition Probability](./res/as-transition-probability.png)
    - N(s<sup>p</sup><sub>k</sub>) is the set of components that do not yet belong to the partial solution s<sup>p</sup><sub>k</sub> of ant _k_
    - α and β are parameters that control the relative importance of the pheromone versus the heuristic information η<sub>ij</sub> = 1/d<sub>ij</sub>, where d<sub>ij</sub> is the length of the component c<sub>ij</sub> (ie of edge (i,j))

### 2. Ant Colony System (Dorigo & Gambardella, 1997)

First major improvement over the original Ant System. First innovations made in a system known as _Ant-Q_, by the same authors.

 New additions include the form of the decision rule used by ants during the construction process. Ants in ACS use the _psuedorandom porportional_ rule: the probability that an ant moves from node _i_ to _j_ depends on a random variable _q_ uniformly distributed over [0,1], and a parameter q<sub>0</sub>. If q <= q<sub>0</sub> then, among the feasible components, the component to maximise the product of τ<sub>il</sub>η<sup>β</sup><sub>il<sub> is chosen. Otherwise the same equation as AS is used.

This is a **greedy rule** which favours exploitation of the pheromone information, and must be counterbalanced with the introduction of a diversifying component, the _local pheromone update_. Local pheromone update is performed by all ants after each construction step. Each ant only applies it to the last edge traversed

- ![Local Pheromone Update](./res/acs-local-pheromone-update.png)
- Where φ ∈ (0,1] is the _pheromone decay coefficient_ and τ<sub>0</sub> is the initial pheromone value.

The main goal of LPU is to diversify the search performed by subsequent ants during one iteration. Decreasing the pheromone concentrations on traversed edges in an iteration encourages other following ants to choose different edges and form different solutions. Local Pheromone Update also means the minimum values of the pheromone are limited.

An _offline pheromone update_ is perfomed at the end of the construction process, as in AS. This is performed only by the best ant -  only the edges visited by the best ant are updated according to the following equation

- ![Offline Pheromone Update](./res/acs-offline-pheromone-update.png) 
- if the best ant used edge (i,j) in its tour, then Δτ<sup>best</sup><sub>ij</sub> = 1/L<sub>best</sub>
    - L<sub>best</sub> can be either the length of the iteration best or that of the best-so-far
- otherwise, Δτ<sup>best</sup><sub>ij</sub> = 0

### 3. MAXMIN Ant System (MMAS) (Stützle & Hoos, 2000)

Differs from AS in that

- Only the best ant adds pheromone trails
- The minimum and maximum values of the pheromone are _explicitly_ limited (AS and ACS are implicitly limited)

The pheromone update equation takes the form

- ![Pheromone Update Equation](./res/mm-pheromone-update-rule.png)
- if the best ant used edge (i,j) in its tour, then Δτ<sup>best</sup><sub>ij</sub> = 1/L<sub>best</sub>
    - L<sub>best</sub> can be either the length of the iteration best or that of the best-so-far
- Pheromone values are _constrained_ between τ<sub>min</sub> and τ<sub>max</sub> by verifying that each pheromone value after being updated by the ant is within these constraints
- τ<sub>ij</sub> is set to τ<sub>max</sub> _if_ τ<sub>ij</sub> > τ<sub>max</sub> and is set to τ<sub>min</sub> if τ<sub>ij</sub> < τ<sub>min</sub>
- As in AS but contrary to ACS, the pheromone update rule applies to all edges at the end of an iteration rather than those traversed by the best ants.
- τ<sub>min</sub> is usually experimentally chosen (Stutzle & Hoos have developed a theory on how to define this value analytically)
- τ<sub>max</sub> can be calculated analytically _if_ the optimum ant tour length is known
    - for TSP, optimum tour length would be 1 / (ρ ⋅ L*) , where L∗ is the length of the optimal tour.
    - L* can be substitued with L<sub>bs</sub> if L* not known

The algorithm sets the initial values of its trails to L<sub>max</sub> and stops when no improvement can be seen for a given number of iterations

### 4. 

## 3. Improved Ant Colony Optimisation

## Possible Expansion: Pheromones

Army Ants sometimes release different types of pheromones eg to warn other ants of prey (obstacles) ahead.

Madeleine Beekman, University of Sydney, reports that it can be the _quality_ of a pheromone trail as well as the quantity that measures the fitness of a trail. Could mean that an ant trying. Possibly have two values associated with the pheromones at a given node; one for the quantity at that node and one for the combined quality of the present pheromones..

### Differing Ant Roles within the Colony

Some ant colonies delegate between different tasks depending on the number of ants already attending to said task. For example, a forager ant that discovers that there are very few active nest-maintaining ants will task-switch and attend to the nest instead. Defense, larvae care and attack are other possible tasks an ant can pick up, and while ACOs are solely based on foraging when traversing a construction graph, we could introduce new operations based on task switching. If we have two rival AI colonies in the one environment then we could have ants task switch between regular searches and attack/defense of the other colonies. This could be implemented within the ant class to release a different "style" of pheromone depending on their current task as well as a sense to monitor how much of each kind of pheromone exists around them. If there is a really low level of a certain style of pheromone in the vicinity, the ant would have a 0-1 chance of switching to that task.

## Application to Problem

Most implementations of Ant Colony Optimisation Algorithms have been used to solve specifically the Travelling Salesman Problem, a combinatorial problem in which the shortest route visiting every node in a construction graph is calculated. The problem we wish to tackle using our trained ants differs in a few ways. 

- Our problem is, once again, a shortest route problem. However, we do not wish to visit every node in the graph but rather visit a few **goal nodes** (thought of as food sources) before returning to the starting point (the anthill/home).
- As well as goal nodes being present in the construction matrix we also wish to include **obstruction nodes** which should not be included in any working solutions. The ants should find the shortest path to the goal nodes while simultaneously avoiding obstruction nodes.
    - In the context of nature, ants can release both positive and negative-indicitave pheromones. In the context of our algorithm, this indicates the need for the algorithm to decrease the pheromone values on paths including obstruction nodes within the pheromone update rule. This could also mean keeping pheromone values of known obstruction nodes at 0.
- As an extension to the problem above, we wish to add new obstructions in the middle of a run. This will be to test for how quickly the virtual ants can adapt to a change in the environment. Again once a new obstacle is introduced the best initial approach is to lower the pheromone values of the node and possibly those adjacent to it, leaving new ants trying to reconnect to the paths of higher pheromone levels.

A proposed new algorithm would be as follows:

    Set parameters, initialise pheromone trails (taking obstuctions in space into account)
    SCHEDULE_ACTIVITIES
        ConstructAntSolutions
        DaemonActions {optional}
            - determine which ants are close to a food source
            - determine which ants are close to an obstruction
            - determine which task type of ant are low in number //optional
        UpdatePheromones
            - positive for those en route to food source/home
            - negative for those whose path includes an obstuction
            - pheromone type update for task switching ants //optional
    END_SCHEDULE_ACIVITIES