//Project Euler Problem 6
/*
Find the difference between the sum of the squares of
the first one hundred natural numbers and the square of the sum.
*/
var t0 = performance.now();
var total = 0;
var squaresSum = 0;
function makeSumSquares(value){
  //square the parameter
  toAdd = value * value;
  squaresSum += toAdd;
}
function runningTotal(value){
  //add to running total
  total = total + value;
}
for(var i = 0; i < 101; i++){
  //recursively call function to square each number
  makeSumSquares(i);
  //call function which adds to running total
  runningTotal(i);
  //if on last call square total
  if(i == 100){
    squaredTotal = total ** 2;
    //add together and output answer
    answer = squaredTotal - squaresSum;
    console.log("Answer: " + String(answer));
  }
}
var t1 = performance.now();
console.log("Speed: " + String(t1 - t0) + " milliseconds");
