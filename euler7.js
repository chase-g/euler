//Project Euler Problem 7
//"What is the 10,001st prime number?"
//Sieve of Eratosthenes
var t0 = performance.now();
//starting arrays
var notPrime = [0,1];
var prime = [];
function sieve(first, num){
  //loop over every number up to limit
  for(var i = first; i < num + 1; i++){
    //check whether i is already in notPrime array
    if(notPrime.includes(i)){
      //already in notPrime so do nothing
    } else{
//if not in notPrime:
//if not already in prime, add to prime (avoid redundance on recursion)
      if(!prime.includes(i)){
      prime.push(i);
      console.log(i);
    }
      //loop through multiples of i
      for(var n = i + i; n < num + 200000; n += i){
        //add multiples of i to notPrime array if not there
        if(!notPrime.includes(n)){
        notPrime.push(n);
      }
      }
    }}}
//Call recursively to reach prime #10,001
function recur(begin, end){
  if(prime.length < 10001){
    //call sieve with parameters
    sieve(begin, end);
    //recur with next 100
    recur(begin + 100, end + 100);
  } else{
    //output value when reached on recursive call
    console.log("Answer: " + String(prime[10000]));
    return prime[10000];
  }
}
recur(0, 100);
var t1 = performance.now();
console.log("Speed: " + String(t1 - t0) + " milliseconds");
