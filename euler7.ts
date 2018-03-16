//Project Euler Problem 7
//"What is the 10,001st prime number?"
//Sieve of Eratosthenes
let t0: number = performance.now();
//starting arrays
let notPrime: Array<boolean> = new Array(150000);
notPrime[0] = true;
notPrime[1] = true;
const prime: Array<number> = [];
//loop over every number up to limit
for(let i = 2; i < notPrime.length; i++){
  //check whether i is already in notPrime array
  if(notPrime[i] == true){
    //already in notPrime so do nothing
  } else{
//if prime:
      prime.push(i);
    //loop through multiples of i and make them true
    for(let n = i + i; n < 150000; n += i){
      //add multiples of i to notPrime array if not there
      notPrime[n] = true;
    }
  }
}
  //print 10,001st prime number
console.log("Answer: " + prime[10000]);
let t1 = performance.now();
console.log("Speed: " + String(t1 - t0) + " milliseconds");
