/**
 * Project Euler
 * Problem 33
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 * March 24, 2018
 */
function checkEq(numer: number, denom: number): number {
  function remove(array, index) { return array.slice(0, index).concat(array.slice(index + 1, array.length));}
  const fraction: number = numer / denom;
  const strNum: string = String(numer);
  const strDem: string = String(denom);
  if(strNum[1] == strDem[1]) return -1;
  else {
    function opt(n, d, seg1, seg2){
      if(String(n)[seg1] == String(d)[seg2]){
        let nx = remove(n, seg1);
        let dx = remove(d, seg2);
        return Number(nx) / Number(dx);
      } else return -1;
    }
      const oneone = opt(strNum, strDem, 0, 0)
      const twotwo = opt(strNum, strDem, 1, 1)
      const onetwo = opt(strNum, strDem, 0, 1)
      const twoone = opt(strNum, strDem, 1, 0)
      const group: Array<number> = [oneone,twotwo,onetwo,twoone];
      for(let i of group) {
      if(Number(i) == fraction) {return i}
    }
    return -1
  }
}

function twoDigitFractions() {
  function gcd(x: number, y: number): number {
    let mx = Math.max(x,y);
    let mn = Math.min(x,y);
    for(let i = (mx / 2) + 1; i > 1; i--){
      if(mx % i == 0 && mn % i == 0) return i;
    }
    return 1;
  }
  let ans: Array<Array<number>> = [];
  for(let i = 10; i < 100; i++) {
    for(let n = 10; n < i; n++) {
       let t = checkEq(n,i);
       if(t != -1){
         ans.push([n,i]);
       }
    }
  }
  let totNum = 1;
  let totDem = 1;
  for(let e of ans){
    totNum *= e[0];
    totDem *= e[1];
  }
  const theGCD = gcd(totNum, totDem);
  return totDem / theGCD;
}
console.log(twoDigitFractions());
