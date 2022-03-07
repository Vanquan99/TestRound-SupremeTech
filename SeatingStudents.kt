# Android Internship 2022 - Test Round - SupremeTech

/*
Challenge
Have the function SeatingStudents(arr) read the array of integers stored in arr which will be in the 
following format: [K, r1, r2, r3, ...] where K represents the number of desks in a classroom, 
and the rest of the integers in the array will be in sorted order and will represent the desks
 that are already occupied. All of the desks will be arranged in 2 columns, 
 where desk #1 is at the top left, desk #2 is at the top right, desk #3 is below #1, desk #4 is below #2, etc. 
 Your program should return the number of ways 2 students can be seated next to each other. 
 This means 1 student is on the left and 1 student on the right, or 1 student is directly above or below the other student. 
For example: if arr is [12, 2, 6, 7, 11] then this classrooms looks like the following diagram: 
 1   (2)
 3    4
 5   (6)
(7)   8
 9    10
(11)  12
 
Based on above arrangement of occupied desks, there are a total of 6 ways ([1,3][3,4][3,5][8,10][9,10][10,12]) to seat 2 new students next to each other. The combinations are: [1, 3], [3, 4], [3, 5], [8, 10], [9, 10], [10, 12]. So for this input your program should return 6. K will range from 2 to 24 and will always be an even number. After K, the number of occupied desks in the array can range from 0 to K. 
Sample Test Cases
Input:6, 4
Output:4
Input:8, 1, 8
Output:6
*/ 
       fun SeatingStudents(arr: Array<Int>): Int {
    if(arr.isEmpty() || arr[0] % 2 != 0)
        throw Exception("invalid argument") //optional

    val totalRows = arr[0] / 2
    var totalCombinations = (totalRows - 1) * 2 + totalRows
    if(arr.size == 1)
        return  totalCombinations

    for(i in 1 until arr.size) {
        if(arr[i] < 1 || i > 1 && arr[i] <= arr[i-1])
            throw Exception("invalid value in argument") // optional
      
        val isNeighbors = arr[i] % 2 == 0 && i > 1 && arr[i] - arr[i-1] == 1
        val isFirstOrLast = arr[i] in 1 .. 2 || arr[i] in arr[0]-1 .. arr[0]
        val isParallel = (i > 1 && arr[i] - arr[i-1] == 2) || (i > 2 && arr[i] - arr[i-2] == 2)

        if(isFirstOrLast && (isNeighbors || isParallel))
            totalCombinations--
        else if(isFirstOrLast || isNeighbors || isParallel)
            totalCombinations -= 2
        else
            totalCombinations -= 3
    }
    
    return totalCombinations
}

