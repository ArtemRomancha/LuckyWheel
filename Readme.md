# Lucky Wheel implementation

## Implementation 
To implement random generating with specified probability we project probabilities on segment from 0 to 100 (We use probability as %).
And random choose number from this segment and select to which situation it related. Situations with greater probability will have longer subsegments and probability to choose them respectively will be greater.  
For example we have next probabilities: `40, 20, 10, 15, 10, 5`. Thence we have segment: 
```
| 5 | 10| 15| 20| 25| 30| 35| 40| 45| 50| 55| 60| 65| 70| 75| 80| 85| 90| 95|100|
|------first situation----------|----second-----|-third-|---fourth--|-fifth-|six|
```       
And randomly choose number from 0 to 100. Find subsegment and select on this situation related to this subsegment. 


Main application offers user to play Lucky Wheel until `q` was entered.

User can enter bets from `0` to `9223372036854775807` (Max LONG Java value). If business need to work with greater bets. then it can be easily changed to BigInteger.
By first iteration was used integer values, but there were int overflow and we received negative values. 

Tests verify that Lucky wheel works with specified probability, generates few thousands bets and calculate frequency of selected multipliers.    

Lucky Wheel was written as separate class and be able to use it from any UI (desktop, web, console).
By default, application uses probabilities from Task, but developer can specify them using appropriate constructor (probability as int or double). 

## Requirements

- Java (version 8+)
- Maven  


## HowTo 

1. Open directory with project 
2. Build project `mvn clean install`
3. Run application `java -jar target/JavaDevTest-1.0-SNAPSHOT.jar`