# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [1.0.0] - 2020-04-05
### Added
- Ants simulated in Ant Colony Optimisation algorithm. 
- Home and Goal implemented for Ant Colony Optimisation algorithm. 
- Ant Colony Optimisation algorithm expanded, using scent trails of successful ants to influence the direction of the next iteration of ants in the simulation.
- Visual simulation of the Ant Colony Optimisation algorithm. 
- Multiple ants moving at the same time in Ant Colony Optimisation. 
- Obstacle generation added, restricting ant movement in the Ant Colony Optimisation algorithm. 

## [2.0.0] - 2020-06-05
### Added
- Generation of JSON reports of metrics gathered during running of algorithm through scenarios or visual simulation. 
- Breadth first search added to calculate one of the optimal routes before running of the algorithm. 
- Visual simulation status panel to display current status of the visual simulation.
- Visual simulation control panel to allow user to start, stop, skip iteration, pause and resume simulation.
- Variable control panel to allow user to change simulation variables including pheromone importance and distance priority. 
- Obstacle panel to allow user to add, remove and change obstacle positions and dimensions. 
- Visual representation of pheromone value present on each node. 
- Visual representation of optimal route that was calculated using breadth first search. 
- Visual simulation report window to display metrics gathered by simulation. 
- Scenarios: Running the algorithm on a specified environment while systematically changing the number of ants, pheromone importance and distance priority between each running of the algorithm.
- Scenarios user interface to allow user to specify a scenario and run it while generating JSON reports of each running of the specified scenario. 
- User interface to choose between running scenarios or visual simulations. 

### Changed
- Ants turn grey when stopped. 