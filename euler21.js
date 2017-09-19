var bigarray = [0];
var amicables = [];
//outer loop loops through each number up to 10,000 to find their factors
for(var num = 1; num < 10000; num++){
  numarray = [];
//inner loop tests each number up to 10,000 as a potential factor of outer loop number
for(var i = 1; i <10000; i++){
if(num % i == 0){numarray.push(i)}
}
//adds together the factors
var result = numarray.reduce(function(accumulator, currentValue) {
    return accumulator + currentValue;
});
//stores the sum of the factors in an array
bigarray.push(result - (num));
}
//loops through each number in the array of factor sums
for(var t = 0; t < bigarray.length; t++){
  //bigarray[t] is the factor sum of number t, saved to amic
  var amic = bigarray[t];
  //if the factor sum of number t's factor sum is t
  if(t == bigarray[amic]) {
//checks that it is not a perfect number (where sum of factors = itself)
    if(bigarray[amic] != bigarray[t]){
//if amicable, adds to the array of amicable numbers
      amicables.push(bigarray[amic]);
    }
  }
}
//adds together all amicable numbers and outputs result
var answer = amicables.reduce(function(accumulator, currentValue) {
    return accumulator + currentValue;
});
console.log(answer);
