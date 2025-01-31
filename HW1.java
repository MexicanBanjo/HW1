
/*
 * *** PLACE YOUR NAME / SECTION  HERE ***
 * - Edwin Johnson - Section 001 -
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)  {        // Constructor
                data = d;
                next = null;
            }
        }
        Node head;                // head of Linked-list


        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in teh parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data ) {
            Node new_node = new Node(data);

            new_node.next = null;

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data ) {
                new_node.next = head;
                head = new_node;
            } else {
                // locate the node before the point of insertion
                Node current = this.head;

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }

            return;
        }


        /*
         * Method removeElementsLT() - this method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         *
         * The method will invoke the method removeElements for each element
         * found in the linked-list that is less than thr parameter value passed.
         */
        public void removeElementsLT ( int ltValue ) {
            while (head != null && head.data < ltValue) {
                head = head.next; // Move head to next node.
            }
            Node current = head; // Start from the new head and traverse the list.
            while (current != null && current.next != null) { // If next node = ltValue, skip it by jumping over via next.next.
                if (current.next.data == ltValue) {
                    current.next = current.next.next;
                } else { // Otherwise, move to the next node.
                    current = current.next;
                }
            }
        }


        /*
         * Method removeElement() - this method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         */

        public void removeElement ( int value ) {
            if (head.data == value) { // Check if the head node contains the target.
                head = head.next; // Remove the head via moving the head to the next node.
            }
            Node current = head; // Start traversing from the new head called "current".
            while (current.next != null) { // Iterate through the list while there are more to check.
                if (current.next.data == value) { // If the next node contains the target, skip/bypass it.
                    current.next = current.next.next;
                }
                current = current.next; // Move to the next node.
            }
        }


        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    } // End class LinkedList




    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /*
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignore, so both 'race car' and 'racecar' are plaindromes.
         *
         * The method should utilize the provided Stack class.
         */
        public static boolean isPalindrome(String input) {
            Stack<Character> stack = new Stack<>(); // Create a stack to store characters of the input string.
            input = input.toLowerCase().replaceAll("\\s+", ""); // Convert input to lowercase and remove all whitespace.

            for (char c : input.toCharArray()) { // Push each character of the string onto the stack.
                stack.push(c);
            }

            StringBuilder reversed = new StringBuilder(); // Create stringbuilder to store the reversed input.
            while (!stack.isEmpty()) { // Pop characters from the stack and append them to the string.
                reversed.append(stack.pop());
            }
            // This return should compare the original with the reversed string.
            return input.equals(reversed.toString());
        }


        /*
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So it you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         *
         * The method should utilize the provided Stack class. This method should NOT
         * destroy the passed in stack, meaning when the method returns, the passed in
         * stack should be identical to when this method is passed. One trick as you
         * pop elements off the passed in stack, place them in a temp stack. Then when
         * completed, place them all back in teh original stack.
         */
        public static int findLargestK(Stack<Integer> stack, int k) {
            Integer[] stackArray = new Integer[stack.size()]; // Create an array to store stack elements.
            int index = 0; // Initialize an index to 0.
            Stack<Integer> tempStack = new Stack<>(); // Temporary stack to restore original order after.

            // Traverse stack from top to bottom, storing the elements in stackArray and tempStack.
            while (!stack.isEmpty()) {
                int value = stack.pop();
                stackArray[index++] = value;
                tempStack.push(value);
            }
            int sz = stackArray.length; // Get the length of stackArray and set it to sz.
            int[] temp = new int[sz];
            for (int i = 0; i < sz; i++) { // Reverse stackArray to restore the original order.
                temp[i] = stackArray[sz - i - 1];
            }
            for (int i = 0; i < sz; i++) { // Copy reversed elements back to stackArray.
                stackArray[i] = temp[i];
            }
            while (!tempStack.isEmpty()) { // Restore the original stack.
                stack.push(tempStack.pop());
            }
            int largestIndex = -1; // Set default to -1 if k is not found.
            for (int currentIndex = 0; currentIndex < stackArray.length; currentIndex++) { // Find the last occurence of k.
                if (stackArray[currentIndex] == k) {
                    largestIndex = currentIndex; // Update to the latest index where k is found.
                }
            }
            return largestIndex; // Return the latest index where k was found.
        }

    }  // End class Stacks



    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++)
            a+= Math.random();

        for (int j=0; j < m; j++)
            b+= Math.random();

        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * TODO: return the answer (which option is correct), in the return statement
        */

        // RETURN THE CORRECT OPTION NUMBER LISTED ABOVE
        return 3;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++)
            for ( j = 2; j <= n; j = j*2 )
                k+= n/2;

        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        // RETURN THE CORRECT OPTION LISTED ABOVE
        return 2;
    }

}



