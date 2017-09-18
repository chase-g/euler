/*Project Euler Problem 18
Find the maximum total from top to bottom of the triangle below:
*/
var t0 = performance.now();
var triangle = `75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23`
//split it into an array
var triarray = triangle.split('\n');
var tri = triarray;
//split that array into an array of arrays
for(var i = 0; i < tri.length; i++){
  tri[i] = tri[i].split(' ').map(Number);
}
//outer loop over each row, starting at the second to last
for(var i = tri.length - 2; i >= 0; i--){
  //inner loop over each value within the row
  for(var n = 0; n < tri[i].length; n++){
//compare the two values below the selected value (next index up on the outer loop, and +0 and +1 on the inner loop)
//set the selected value to the sum of it and the higher of the two
  tri[i][n] = tri[i][n] + Math.max(tri[i+1][n], tri[i+1][n+1]);
//continue so that each row will compare and choose values below
}
}
//the top value will have been set to the value of the highest value path
console.log("Maximum total: " + tri[0][0]);
var t1 = performance.now();
console.log("Speed: " + String(t1 - t0));
