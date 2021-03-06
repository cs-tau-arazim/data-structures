package test1;


import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ExTester {

	public static final int SIZE = 2048;

	public static int[] sortInts(int[] arr) {
		int[] sortedArr = new int[arr.length];
		for (int j = 0; j < arr.length; j++) {
			sortedArr[j] = arr[j];
		}
		Arrays.sort(sortedArr);
		return sortedArr;
	}

	public static boolean arraysIdentical(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int j = 0; j < arr1.length; j++) {
			if (arr1[j] != arr2[j]) {
				return false;
			}
		}
		return true;
	}

	public static int[] stringToInt(String[] arr) {
		int[] arr2 = new int[arr.length];
		for (int i = 0 ; i < arr2.length ; i++) {
			arr2[i] = Integer.parseInt(arr[i]);
		}
		return arr2;
	}

	public static int intValue(String str) {
		if (str == null || str == "")
			return -1;
		else
			return Integer.parseInt(str);
	}

	public static boolean checkEmpty(RBTree rbTree, MyTree myTree) {
		return rbTree.empty() == myTree.empty();
	}

	public static boolean checkSize(RBTree rbTree, MyTree myTree) {
		return rbTree.size() == myTree.size();
	}
	

	public static boolean checkMin(RBTree rbTree, MyTree myTree) {
		return intValue(rbTree.min()) == myTree.min();
	}

	public static boolean checkMax(RBTree rbTree, MyTree myTree) {
		return intValue(rbTree.max()) == myTree.max();
	}

	public static boolean checkKeysArray(RBTree rbTree, MyTree myTree) {
		return arraysIdentical(rbTree.keysToArray(),
							   sortInts(myTree.array()));
	}

	public static boolean checkValuesArray(RBTree rbTree, MyTree myTree) {
		return arraysIdentical(stringToInt(rbTree.valuesToArray()),
							   sortInts(myTree.array()));
	}

	public static boolean checkSearch(RBTree rbTree, MyTree myTree) {
		for (int i = 0; i < SIZE; i++) {
			if ((intValue(rbTree.search(i)) == i) != myTree.contains(i))
				return false;
		}
		return true;
	}
	
	public static boolean checkRank(RBTree rbTree, MyTree myTree) {
		for (int i = 0; i < SIZE; i++) {

			if (rbTree.rank(i) != myTree.rank(i)){
				System.out.println(rbTree.rank(i) );
				System.out.println( "--" + myTree.rank(i));				
				return false;

			}
		}
		return true;
	}
	
	public static boolean rankEdgeCases() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		insert(rbTree, myTree, keys);
		
		int min_key = myTree.min();
		int max_key = myTree.max();
		
		// check k larger then keys

		
		if (rbTree.rank(max_key+100) != myTree.rank(max_key+100)) {
			System.out.println(rbTree.rank(max_key+100) );
			System.out.println( "--" + myTree.rank(max_key+100));
			return false;
		}
		
		// check k smaller then keys

		
		if (rbTree.rank(min_key-1) != myTree.rank(min_key-1)){
			System.out.println(rbTree.rank(min_key-1) );
			System.out.println( "--" + myTree.rank(min_key-1));			
			return false;

		}
		return true;
	}
	
	public static boolean checkAll(RBTree rbTree, MyTree myTree) {
		return (checkEmpty(rbTree, myTree) &&
				checkSize(rbTree, myTree) &&
				checkMin(rbTree, myTree) &&
				checkMax(rbTree, myTree)  &&
				checkKeysArray(rbTree, myTree) &&
				checkValuesArray(rbTree, myTree));
	}

	public static void insert(RBTree rbTree, MyTree myTree, int[] keys) {
		for (int j = 0; j < keys.length; j++) {
			rbTree.insert(keys[j],(""+keys[j]));
			myTree.insert(keys[j]);
		}
	}

	public static int[] generateKeys() {
		int[] arr = new int[SIZE];
	    for (int i = 0; i < SIZE; i++) {
	        arr[i] = i;
	    }
	    Collections.shuffle(Arrays.asList(arr), new Random(539996358));

	    // mid -> min_max sort
	    int tmp[] = Arrays.copyOf(arr, SIZE/4);
	   	Arrays.sort(tmp);
	   	for (int i = 0; i < tmp.length/2; i++) {
	   		arr[2*i] = tmp[tmp.length/2-1-i];
	   		arr[2*i+1] = tmp[tmp.length/2+i];
	   	}

	   	// max -> min sort
	    Arrays.sort(arr, SIZE/4, SIZE/2);
	    for (int i = 0; i < SIZE/8; i++) {
	    	int swapped = arr[SIZE/4+i];
	    	arr[SIZE/4+i] = arr[SIZE/2-1-i];
	    	arr[SIZE/2-1-i] = swapped;
	    }

	    // min -> max sort
	    Arrays.sort(arr, SIZE/2, 3*SIZE/4);

	    return arr;
	}

	public static boolean emptyTreeTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();

		if (!checkAll(rbTree, myTree))
			return false;

		rbTree.insert(1, "1");
		rbTree.delete(1);

		return checkAll(rbTree, myTree);
	}

	public static boolean insertAndSearchTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		for (int j = 0; j < keys.length; j++) {
			rbTree.insert(keys[j],(""+keys[j]));
			myTree.insert(keys[j]);
			if (!checkSearch(rbTree, myTree))
				return false;
		}
		return true;
	}
	
	public static boolean insertAndRankTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		insert(rbTree, myTree, keys);
		if (!checkRank(rbTree, myTree))
			return false;
		
		return true;
	}

	public static boolean deleteAndSearchTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		insert(rbTree, myTree, keys);
		for (int j = 0; j < keys.length; j++) {
			rbTree.delete(keys[j]);
			myTree.delete(keys[j]);
			if (!checkSearch(rbTree, myTree))
				return false;
		}
		return true;
	}

	public static boolean insertAndMinMaxTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		for (int j = 0; j < keys.length; j++) {
			rbTree.insert(keys[j],(""+keys[j]));
			myTree.insert(keys[j]);
			if (!checkMin(rbTree, myTree) || !checkMax(rbTree, myTree))
				return false;
		}
		return true;
	}

	public static boolean deleteMinMaxTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		insert(rbTree, myTree, keys);
		for (int j = 0; j < keys.length; j++) {
			rbTree.delete(keys[j]);
			myTree.delete(keys[j]);
			if (!checkMin(rbTree, myTree) || !checkMax(rbTree, myTree))
				return false;
		}
		return true;
	}

	public static boolean insertAndSizeEmptyTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		for (int j = 0; j < keys.length; j++) {
			rbTree.insert(keys[j],(""+keys[j]));
			myTree.insert(keys[j]);
			if (!checkSize(rbTree, myTree) || !checkEmpty(rbTree, myTree))
				return false;
		}
		return true;
	}

	public static boolean insertAndArraysTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		for (int j = 0; j < keys.length; j++) {
			rbTree.insert(keys[j],(""+keys[j]));
			myTree.insert(keys[j]);
			if (!checkKeysArray(rbTree, myTree))
			{
				System.out.println(rbTree.keysToArray().length);
				System.out.println(myTree.size());
				return false;
			}
			if (!checkValuesArray(rbTree, myTree))
				return false;
		}
		return true;
	}

	public static boolean deleteAndArraysTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		insert(rbTree, myTree, keys);
		for (int j = 0; j < keys.length; j++) {
			rbTree.delete(keys[j]);
			myTree.delete(keys[j]);
			if (!checkKeysArray(rbTree, myTree))
				return false;
			if (!checkValuesArray(rbTree, myTree))
				return false;
		}
		return true;
	}

	public static boolean doubleInsertTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		insert(rbTree, myTree, keys);
		for (int j = 0; j < keys.length; j++) {
			if (rbTree.insert(keys[j],""+(-1)) != -1)
				return false;
			if (!checkSize(rbTree, myTree))
				return false;
		}
		return checkValuesArray(rbTree, myTree);
	}

	public static boolean doubleDeleteTest() {
		RBTree rbTree = new RBTree();
		MyTree myTree = new MyTree();
		int[] keys = generateKeys();
		for (int j = 0; j < keys.length; j++) {
			if (rbTree.delete(keys[j]) != -1)
				return false;
			rbTree.insert(keys[j],(""+keys[j]));
			myTree.insert(keys[j]);
			if (!checkSize(rbTree, myTree))
				return false;
		}
		return checkValuesArray(rbTree, myTree);
	}

	public static boolean checkTreeIntegrity()
	{
		RBTree rbTree = new RBTree();
		boolean isValid = true;
		int[] keys = generateKeys();
		for(int i=0;i<keys.length;i++)
		{
			rbTree.insert(keys[i], "s:"+Integer.toString(keys[i]));
			isValid = isValid && RBInternalTester.isTreeValid(rbTree);
		}
		for(int i=0;i<keys.length;i=i+2)
		{
			rbTree.delete(keys[i]);
			isValid = isValid && RBInternalTester.isTreeValid(rbTree);
		}
		for(int i=0;i<keys.length;i=i+4)
		{
			rbTree.insert(keys[i], "s:"+Integer.toString(keys[i]));
			isValid = isValid && RBInternalTester.isTreeValid(rbTree);
		}
		for(int i=0;i<keys.length;i=i+8)
		{
			rbTree.delete(keys[i]);
			isValid = isValid && RBInternalTester.isTreeValid(rbTree);
		}
		return isValid;
	}
	
	public static int parseArgs(String[] args) {
		int test_num;

		if (args.length != 1)
			return -1;

		try {
			test_num = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			return -1;
		}

		if (test_num < 0 || test_num > 9)
			return -1;

		return test_num;
	}

	public static void main(String[] args) {
		for(int test_num =0;test_num < 13;test_num++)
		{

			TestRun test_runner = new TestRun(test_num);
			Thread test_thread = new Thread(test_runner);
			test_thread.start();
			try {
				test_thread.join(50000);
				if (test_thread.isAlive())
					System.out.println("Timeout on Test " + test_num);
			}
			catch (Exception e) {
				System.out.println("Exception on Test " + test_num + " : " + e);
			}
			System.out.println("Result #" + test_num + ": " + test_runner.success);
		}
	}


}

