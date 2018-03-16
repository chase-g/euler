//Project Euler Problem 6
/*
Find the difference between the sum of the squares of
the first one hundred natural numbers and the square of the sum.
*/

function range(start: number, stop: number){
return Array.apply(null, Array(stop - start + 1)).map(function (_, i) {return i + start;})
}

function sumOfSquares(num: number = 1, acc: number = 0): number {
  if(num > 100) { return acc; }
  else { return sumOfSquares(num + 1, acc + Math.pow(num, 2)); }
}

function squareOfSums(num: number = 1, acc: number = 0): number {
  if(num > 100) { return Math.pow(acc, 2); }
  else { return squareOfSums(num + 1, acc + num); }
}

const answer = Math.abs(sumOfSquares() - squareOfSums());
console.log(answer);
