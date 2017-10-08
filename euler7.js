//Project Euler Problem 7
//"What is the 10,001st prime number?"
//Sieve of Eratosthenes
var t0 = performance.now();
//starting arrays
var notPrime = new Array(150000).fill(false);
notPrime[0] = true;
notPrime[1] = true;
var prime = [];
//loop over every number up to limit
for(var i = 2; i < notPrime.length; i++){
  //if prime:
  if(notPrime[i] == false){
    //loop through multiples of i and make them true
    for(var n = i + i; n < 150000; n += i){
      //add multiples of i to notPrime array if not there
      notPrime[n] = true;
    }
  }
}
for(var i = 0; i < notPrime.length; i++){
  //check whether i is in the notPrime array
  //if not prime, do nothing
  if(notPrime[i] == true){
  } else{
//if prime, add to prime array
    prime.push(i);
    }
  }
  //print 10,001st prime number
console.log("Answer: " + prime[10000]);
var t1 = performance.now();
console.log("Speed: " + String(t1 - t0) + " milliseconds");
