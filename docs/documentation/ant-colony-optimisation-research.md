# Ant Colony Optimisation

## Contents

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


## 2. Improved Ant Colony Optimisation

## Possible Expansion: Pheromones

Army Ants sometimes release different types of pheromones eg to warn other ants of prey (obstacles) ahead.

Madeleine Beekman, University of Sydney, reports that it can be the _quality_ of a pheromone trail as well as the quantity that measures the fitness of a trail. Could mean that an ant trying