//Project Euler Problem 10
//"Find the sum of all the primes below two million."
//Sieve of Eratosthenes
function sieve(peak: number) {
var notPrime: Array<boolean> = new Array(peak);
notPrime[0] = true;
notPrime[1] = true;
var total = 0;
//loop over every number up to limit
for(var i = 2; i < notPrime.length; i++){
  //check whether i is already in notPrime array
  if(notPrime[i] != true){
//if prime:
    total += i;
    //loop through multiples of i and make them true
    for(var n = i + i; n < peak; n += i){
      //add multiples of i to notPrime array if not there
      notPrime[n] = true;
    }
  }
}
console.log(total);
}
sieve(2000000);
