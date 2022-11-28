# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format

## New Implementation
* Added JaCoCo maven dependancy to track code coverage with junit testing. After new requirements were implemented , unused code removed, new tests added code coverage is now 95%.
* Added json maven dependancy to address json print output 
* Fixed logic for returning the correct fee for movie tickets

## Steps to view the code coverage reports
* Please pull the feature branch 
* Run mvn clean test
* The JaCoCo code coverage report will be generated at target/site/jacoco/* 

## Note
JaCoCo is an actively developed line coverage tool that is used to measure the number of lines in code that are tested