package codes;

public class EngineMain {
	public static final int SIZE = 100;
	public static int[] numbers = new int[SIZE];
	public static void main(String[] args) {
		boolean exit = false;
		while (exit == false)
		{
			//Menu
			System.out.println("What would you like to do?"
					+ "\n1. Populate array sequentially"
					+ "\n2. Populate array randomly"
					+ "\n3. Check to see if sorted"
					+ "\n4. Display"
					+ "\n5. Shuffle"
					+ "\n6. Linear search"
					+ "\n7. Bubble sort"
					+ "\n8. Selection sort"
					+ "\n9. Insertion sort"
					+ "\n10. Quick Sort"
					+ "\n11. Radix Sort"
					+ "\n12. Binary Search"
					+ "\n0. Exit");
			int choice = Utility.errorTrapInt(12, 0);
			if(choice == 1)
				Sequencial();
			else if(choice == 2)
				Random();
			else if(choice == 3)
			{
				if(SortCheck() == false)
					System.out.println("Array is not sorted");
				if(SortCheck() == true)
					System.out.println("Array is sorted");
			}
				
			else if(choice == 4)
				Display();
			else if(choice == 5)
				Shuffle();
			else if(choice == 6)
				LinearSearch();
			else if(choice == 7)
				BubbleSort();
			else if(choice == 8)
				SelectionSort();
			else if(choice == 9)
				InsertionSort();
			else if(choice == 10)
				QuickSort(0,SIZE-1);
			else if(choice == 11)
				RadixSort();
			else if (choice ==12)
			{
				System.out.println("Enter number to be searched for");
				int number = Utility.errorTrap();
				binarySearch(0,SIZE - 1, number);
				
			}
			else if(choice == 0)
				exit = true;
		}
		System.out.println("Goodbye");
	}
	
	public static void Sequencial()
	{
		//populate each spot in array in order starting at 1
		for (int x = 0; x < SIZE; x++)
			numbers[x] = x + 1;
	}
	
	public static void Random()
	{
		//populate each spot with a number between 1 and the array size
		for(int x = 0; x < SIZE ; x++)						
			numbers[x] = Utility.rng(SIZE, 1);
	}
	
	public static boolean SortCheck()
	{
		//track index
		int x = 0;
		//track if array is sorted
		boolean sorted = true;
		//once array has been proven sorted, it does not check any more
		while(sorted == true && x < (SIZE - 1))
		{
			//check if the number at the index is greater than the next number
			if(numbers[x] > numbers[x+1])
				sorted = false;
			x++;
		}
		return sorted;
		
	}
	
	public static void Display()
	{
		for(int x = 0; x < SIZE; x++)
		{
			//next line after every 10 numbers
			if((x%10 == 0)&&(x != 0))
			{
				System.out.println();
			}
			System.out.print(numbers[x] + "\t");
		}
		System.out.println();
	}
	
	public static void Shuffle()
	{
		for(int x = 0; x < SIZE; x++)
		{
			//find random index
			int random = Utility.rng(SIZE-1, 0);
			//switch number at index with a random index
			swap(random,x);			
		}
	}
	
	public static void LinearSearch()
	{
		System.out.println("What number would you like to search for?");
		int choice = Utility.errorTrapInt(SIZE, 1);
		//count how many times the number appears
		int counter = 0;
		for(int x = 0; x < SIZE; x++)
		{
			//checks each index for the number
			if(numbers[x] == choice)
				counter++;
		}
		//display result
		if (counter > 0)
			System.out.println("The number appears " + counter + " time(s).");
		else
			System.out.println("The number is not in the array.");
	}
	
	public static void BubbleSort()
	{
		// first number to compare
		for(int x = 0; x < SIZE - 1; x++)
			//second number to compare, starting after first number
			for(int y = x+1; y < SIZE; y++)
				//switch if first is greater than second
				if(numbers[x] > numbers[y])
					swap(x,y);
	}

	public static void SelectionSort()
	{
		for(int x = 0; x < SIZE - 1; x++)
		{
			//tracks lowest, initially the first number being checked
			int lowest = numbers[x];
			//track index of lowest number
			int index = x;
			//check all numbers after to see if it is the lowest
			for(int y = x+1; y < SIZE; y++)
				//change to new lowest number and index
				if(lowest > numbers[y])
				{
					lowest = numbers[y];
					index = y;
				}
			//swap number at x with the lowest
			swap(x, index);
		}	
	}
	
	public static void InsertionSort()
	{
		int[] temp = new int[SIZE];
		temp[0] = numbers[0];
		int placedCounter = 1;
		for(int x = 1; x < SIZE; x++)
		{
			// if it is the lowest number in the temp
			if(numbers[x] < temp[0])
			{
				//shift entire array forward one space
				for(int y = placedCounter; y >0; y--)
					temp[y] = temp[y-1];
				//place as first number
				temp[0] = numbers[x];
			}
			//if it is the highest number in the temp place as last number
			else if(numbers[x] > temp[placedCounter-1])
				temp[placedCounter] = numbers[x];
			//if it goes somewhere in between
			else
			{
				boolean placed = false;
				int y = 1;
				//keep advancing y until the correct spot is found
				while(y < placedCounter && placed == false)
				{
					
					if(numbers[x]>=temp[y-1] && numbers[x]<=temp[y])
					{
						//shift every number that is higher
						for(int z = placedCounter; z >= y; z--)
							temp[z] = temp[z-1];
						//insert number
						temp[y] = numbers[x]; 
						placed = true;
					}
					y++;
				}
			}
			placedCounter++;
		}
		numbers = temp;
	}
	public static void swap(int index1, int index2) 
	{
		// basic swap
		int temp = numbers[index1];          
		numbers[index1] = numbers[index2];  
		numbers[index2] = temp;  
	}
	
	public static void QuickSort(int min, int max)
	{
		//left slider
		int left = min;
		//right slider
		int right = max;
		//pivot is the first number
		int pivot = min;
		while(right > left)
		{
			//move the slider if it does not find a greater number
			while(numbers[left] <= numbers[pivot] && left < pivot)
				left++;
			//move the slider if it does not find a lesser number
			while(numbers[right] > numbers[pivot] && right >pivot)
				right--;
			//change pivot index if it is swapped
			if(left == pivot)
				pivot = right;
			else if(right == pivot)
				pivot = left;
			swap(left, right);
		}
		//left side recursion	
		if(pivot - min > 1)
			QuickSort(min,pivot - 1);
		//right side recursion
		if(max - pivot > 1)
			QuickSort(pivot + 1, max);
		
	}
	
	public static void RadixSort()
	{
		//tracks digit being evaluated
		int at = 1;
		//tracks numbers placed in copy array
		int index = 0;
		while(SortCheck()==false)
		{
			int[] copy = new int[SIZE];
			index = 0;
			//goes through digits in order
			for(int x = 0; x < 10; x++)
				//goes through numbers in array
				for(int y = 0; y < SIZE; y++)
					//checks to see if the digit matches
					if(DigitAt(at,numbers[y]) == x)
					{
						//place in copy array
						copy[index] = numbers[y];
						index++;
					}
			//advance to next digit
			at++;
			numbers = copy;	
		}
	}
	public static int DigitAt(int at, int number)
	{
		for(int x = 1; x < at; x++)
			number = number/10;
		return number%10;
	}
	
	public static void binarySearch(int min, int max, int number)
	{
		//only run if sorted
		if(SortCheck() == true)
		{
			boolean found = false;
			//only run while section is larger than one number
			while(max - min > 1 && found == false)
			{
				int midpoint = (max + min)/2;
				//use smaller half
				if(numbers[midpoint] > number)
					max = midpoint;
				//use larger half
				else if(numbers[midpoint] < number)
					min = midpoint;
				//number is found
				else if(numbers[midpoint] == number)
					found = true;
			}
			//display result
			if(found == true)
				System.out.println("Number is in array");
			else
				System.out.println("Number is not in array");
		}
		//prompt user to sort so binary search can be run
		else
			System.out.println("Array must be sorted for Binary search. Please sort array.");
	}
}
