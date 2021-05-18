# MSIAfterBurnerExtension

## Description

  Track your computer's performance while gaming and get snapshot graphs if your FPS dips below a certain value.
  
## Overview

  - Contact the MSI Afterburner remote server and request statistics
  - Parse relevant data 
  - Store data when FPS drops
  - Plot and display graphs

## Compilation 

  Ensure that the XCharts jar file is installed

  `javac -cp .:xchart-3.8.0.jar *.java`
  
  
## Running

  - Ensure MSI Afterburner and the MSI Afterburner remote server are both running
   
  - `java Main`
  
  - Follow the prompts

## Credit 

  Accessing remote server and interval polling: https://github.com/SuperEvenSteven/afterburner
