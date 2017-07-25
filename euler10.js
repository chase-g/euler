//Project Euler Problem 10
//"Find the sum of all the primes below two million."
//Sieve of Eratosthenes
var t0 = performance.now();
//starting arrays
var notPrime = new Array(2000000).fill(false);
notPrime[0] = true;
notPrime[1] = true;
total = 0;
//loop over every number up to limit
for(var i = 2; i < notPrime.length; i++){
  //check whether i is already in notPrime array
  if(notPrime[i] == true){
    //already in notPrime so do nothing
  } else{
//if prime:
    total += i;
    //loop through multiples of i and make them true
    for(var n = i + i; n < 2000000; n += i){
      //add multiples of i to notPrime array if not there
      notPrime[n] = true;
    }
  }
}
console.log(total);
var t1 = performance.now();
console.log("Speed: " + String(t1 - t0) + " milliseconds");
