package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    return this.allDrivers.minus(this.trips.map { it.driver }).toSet()
}
/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        this.allPassengers.filter { p->
            this.trips.filter {
                p in it.passengers
            }.count()>=minTrips
        }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
       this.allPassengers.filter {p->
           this.trips.filter { it.driver==driver && it.passengers.contains(p) }.count()>1
}.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        this.allPassengers.filter {p->
            this.trips.filter {
                it.passengers.contains(p) && it.discount!=null
            }.count()>this.trips.filter {
                it.passengers.contains(p) && it.discount==null
            }.count()
        }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return if (this.trips.isEmpty())
        null
    else {
        val longestTrip = this.trips.map { it.duration }.max() ?: 0
        var maxFrequencyIntRange = Pair(IntRange(0, 9), 0)
        for (i in 0..longestTrip step 10) {
            val currIntRange = IntRange(i, i + 9)
            val currRangeFrequency = this.trips.filter { it.duration in currIntRange }.count()
            if (currRangeFrequency > maxFrequencyIntRange.second)
                maxFrequencyIntRange = Pair(currIntRange, currRangeFrequency)
        }
        maxFrequencyIntRange.first
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if(this.trips.isEmpty())
        return false
    else{
        val totalIncomeOfAllDrivers=this.trips.map { it.cost }.sum()
        val eightPercentOfTotal=totalIncomeOfAllDrivers*0.8
        val sortedListOfDriversAndIncome=
                this.trips.groupBy { it.driver }
                .mapValues {(_,trip)-> trip.sumByDouble { it.cost } }
                .toList()
                .sortedByDescending {(_,price)->price }.toMap()
        var noOfDriversForEightPercent=0
        var currentTotalIncome=0.0
        for (i in sortedListOfDriversAndIncome.values){
            noOfDriversForEightPercent++
            currentTotalIncome+=i
            if(currentTotalIncome>=eightPercentOfTotal) break
        }
        return noOfDriversForEightPercent<=allDrivers.size*0.2
    }
}