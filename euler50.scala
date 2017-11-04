
object euler50 {
  def main(args: Array[String]): Unit = {
    
    def sieve() = {  // Sieve of Erastosthenes 
      val notPrime = new Array[Boolean](9999999)  // Create array of booleans, each initially false
      notPrime(0) = true
      notPrime(1) = true
      for(i <- 2 until notPrime.length){  // For each index in the array of booleans
      // set all the multiples of each prime number to true
      if(notPrime(i) == false) {
      for(n <- i until notPrime.length by i){
        if(n != i){
          notPrime(n) = true
            }
          }
        }
      }
    notPrime
   }
    
    val notPrime = sieve()
    
    val prime = new Array[Int](1000000)
    
    def makePrimes(item: Int, next: Int): Unit = {
      //for(i <- 0 to notPrime.length) if(notPrime(i) == false)
      if(item == notPrime.length) print("")
      else if(notPrime(item) == false) {
        prime(next) = item
        makePrimes(item + 1, next + 1)
      } 
      else makePrimes(item + 1, next)
    } 
    makePrimes(0,0)
    
    def primeAdd(sliceStart: Int, sliceAdd: Int, record: Int, recordHolder: Int): List[Int] = {
      val amt = prime.slice(sliceStart, sliceStart + sliceAdd).reduceLeft(_ + _)
      // length exceeded, record existing record
      //println(recordHolder)
      if(amt >= 1000000) return List(record, recordHolder)
      // amt isn't prime, continue with a longer sequence
      else if(notPrime(amt) == true) primeAdd(sliceStart, sliceAdd + 1, record, recordHolder) 
      // amt is a prime made of a longer sequence than the standing record
      else primeAdd(sliceStart, sliceAdd + 1, sliceAdd, amt)
    }
    
    def primeIterate(current: Int, record: Int, recordHolder: Int): Int = {
      val next = primeAdd(current, record, 1, 0)
      if(current > 10000) return recordHolder
      else if(next(0) > record) primeIterate(current + 1, next(0), next(1))
      else primeIterate(current + 1, record, recordHolder)
    }
    
    println("Answer: " + primeIterate(0, 1, 0)) 
    
  }
}