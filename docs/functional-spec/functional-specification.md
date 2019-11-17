# Modelling an Ant Colony

Functional Specification

Kevin Cleary - 16373026

James O'Neill - 16410652

Date:

___

## Table of Contents

1. [Introduction](#1-introduction)
    - [Overview](#11-overview)
    - [Business Context](#12-business-context)
    - [Glossary](#13-glossary)
2. [General Description](#2-general-description)
    - [System Functions](#21-system-functions)
    - [User Characteristics and Objectives](#22-user-characteristics-and-objectives)
    - [Operational Scenarios](#23-operational-scenarios)
    - [Constraints](#24-constraints)
3. [Functional Requirements](#3-functional-requirements)
4. [System Architecture](#4-system-architecture)
5. [High-Level Design](#5-high-level-design)
6. [Preliminary Schedule](#6-preliminary-schedule)
7. [Appendices](#7-appendices)

---

## 1. Introduction

### 1.1 Overview

This project is a study on Machine Learning and Genetic Algorithms through the implementation and simulation of a Virtual Ant Colony. 
We intend to train our virtual ant colony to solve fastest route problems in real time and apply their behaviour to emergency response vehicles facing the same problems.
We are also going to implement various subspecies of ant in our simulations based on differing traits and allow users to alter these traits to suit their desired needs.

Ant Colony Optimisation Algorithms were first implemented by Marco Dorigo (IRIDIA, Brussels) in 1992.
Dorigo used nodes and edges to represent his virtual ants and their environment, using his trained 'ants' to solve a classic Travelling Salesman problem.
Ant Colony Optimisation (ACO) Algorithms are modelled on the behaviour of ants searching for food.
In Nature, ant colonies work as a unit while foraging for food, with each individual ant leaving chemical trails known as pheromones, that can then be smelled by other ants on the route.
The ants work together to find the stronger pheromone paths and over time find the quickest route to and from their desired food source. 
This project will involve studying Stigmergy in nature and how to apply it in the context of Machine Learning.

Our virtual ant colony will be displayed in a GUI environment for users to view their behaviour over time in finding their fastest routes.
Important nodes in this environment will include the Ant Hill (Home), Food Sources (goals) and various obstacles which will cut off paths.
Obstacles will appear by the  user's hand or at random during any simulation, and the ants must adapt to find the shortest route to their goals based on these environmental changes.

As a further study into ant behaviour and biomimiry in modern design, we want to simulate different species of ant colony and have them interact within our GUI environment.
We plan on doing this by having our basic ant class become an interface for our various subspecies and introduce different traits based on ant colonies in the wild e.g size, speed, repopulation rate etc. 
This will allow users to play around with different attributes of the ants to see what factors may improve their ant's optimal route, and will also allow the simulation of two rival colonies trying to access the same resource. 

### 1.2 Business Context

Business Context 1: Emergency Response

Business Context 2: Education

Business Context 3: Autonomous Cars

### 1.3 Glossary

- Travelling Salesman Problem: An optimisation algorithm problem defined by mathematicians W.R. Hamilton and Thomas Kirkman, with a focus on finding the cheapest, shortest or fastest route around a graph of nodes.
- Pheromones: a chemical substance produced and released into the environment by a mammal or insect, affecting the behaviour or physiology of others of its species.
- Stigmergy: a mechanism of indirect coordination in which the trace left by an action in a medium stimulates subsequent actions
- GUI: A graphical user interface, a computer environment that simplifies the user's interaction with the computer by offering visual elements to the user.
- Biomimicry: the design and production of materials, structures and systems that are modelled on biological entities and processes

---

## 2. General Description

This project aims to develop a simulation that mimics the behaviour of 
ants traversing their environment to find food. In nature, ants are 
extremely proficient at finding the shortest path from their anthill to 
the source of food. Through use of chemical trails (pheromones) ants can 
successfully find the shortest path to and from a food source, and in 
turn notify the other ants of this short path using the chemical trails. 

Our project will be broken down into various functional stages:

1. **Simulating Ant Movement:** The first stage will involve developing 
our Graphical User Interface to display the movement of our ants, 
generating the randomised environment that the ants will traverse, and 
developing the basic way the ants will move around that environment. 

2. **Enhancing the Ants' Movement Algorithm:** The second stage will involve mirroring the behaviour of real-life ants, and making our ants as efficient at finding their way from anthill to food source as ants are in the real world. 

3. **Incorporating Environmental Changes:** This is where we will simulate changes to the ants' environment. This will properly test the ants' ability to adapt to changing circumstances, and at this point, we will improve upon the ants' movement algorithm. 

4. **Applying To Real-World Circumstances:** This will involve introducing our trained ants to real-life environments. By having the ants' "anthill" and their "food source" at different ends of a real world environment (ie. changing the environment to mimic Dublin's streets), we can find the shortest path to and from two points. Also, making use of the environmental changes, we can simulate streets being blocked, and a new route being calculated. 

### 2.1 System Functions

- **Start Simulation**

- **Edit environment**

- **Edit ant class**

- **View metrics**

### 2.2 User Characteristics and Objectives

The focus of this project is to apply our trained ants to solve route problems faced by Emergency Response teams, but we want to design our system to be applicable to many different user bases.
As such, our aim is to create a system that is simple at first glance, and while focusing on our assigned problem we want to identify transferable aspects of our system for future growth.

We are not assuming any prior knowledge of genetic algorithms from our userbase, and so want our application to be both simple to use and also to offer descriptions of the theory behind our simulation.
As well as a tool to be used by different services, we want our app to be as educational as possible.
Our GUI will be designed in such a way that users can view our virtual colony at work in real time, as well as clearly being able to visually identify the different components within our simulated environment. 
We also plan to display informative metrics to all users during/after any given simulation for further clarity on the operation of our system. 
This will allow users to pinpoint which attributes have an impact on the colony in any particular environment.

### 2.3 Operational Scenarios

#### Scenario 1: 

**Goal in context**:

**Description**:

1.
2.
3.

### 2.4 Constraints

**Time Constraints**: As we only have a limited amount of time to deliver this system, we are limiting our scope to have one main user base, although we plan to identify where we can expand our system to cater to a wider group.

**Other Constraints**: ??? 

---

## 3. Functional Requirements

#### 3.1 - 

**Description**:

**Criticality**:

**Technical Issues**:

**Dependencies**:

---

## 4. System Architecture

---

## 5. High-Level Design

---

## 6. Preliminary Schedule

---

## 7. Appendices
