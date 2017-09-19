var bigarray = [0];
var amicables = [];
for(var num = 1; num < 10000; num++){
  numarray = [];
for(var i = 1; i <10000; i++){
if(num % i == 0){numarray.push(i)}
}
var result = numarray.reduce(function(accumulator, currentValue) {
    return accumulator + currentValue;
});
bigarray.push(result - (num));
}
for(var t = 0; t < bigarray.length; t++){
  var amic = bigarray[t];
  if(t == bigarray[amic]) {

    if(bigarray[amic] != bigarray[t]){
      amicables.push(bigarray[amic]);
    }
  }
}
var answer = amicables.reduce(function(accumulator, currentValue) {
    return accumulator + currentValue;
});
console.log(answer);
