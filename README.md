# Auburn_university-Algorithms
Algorithms Projects for Algorithms I and Algorithms II
The objective of this programming assignment is to design and implement in Java the naïve sorting
program presented in the lecture to sort a list of numbers. We are interested in exploring the relationship between the time complexity and the “real time”. For this exploration, you will collect the execution time T(n) as a function of n and plot T(n)/n, T(n)/n2, and T(n)/n3 on the same graph. Finally, discuss your results.
Program to implement
collectData()
Generate an array G of HUGE length L (as huge as your language allows) with random values for n = 5,000 to L (with step 1,000)
Data Analysis
copy in Array A n first values from Array G
Start timing // We time the sorting of Array A of length n for j = 1 to n-1 //Sort_Array(A)
for i = (j + 1) to n if (A[i] < A[j])
// swap A[i] and A[j] buffer = A[j]
A[j] = A[i]
A[i] = buffer
Store the value n and the values T(n)/n, T(n)/n2, and T(n)/n3 in a file F where T(n) is the execution time.
Use any plotting software (e.g., Excel) to plot the values T(n)/n, T(n)/n2, and T(n)/n3 in File F as a function of n (on the same graph). File F is the file produced by the program you implemented. Discuss your results based on the plot. (Hint: is T(n) closer to K. n, K.n2, or K. n3.log2(n) where K is a constant?)
