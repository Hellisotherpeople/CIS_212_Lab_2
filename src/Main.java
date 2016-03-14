import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        int cont1 = 1;
        int cont2 = 1;
        int length = 0;
        double density = 0;
        /* The main loop where the user enters values */
        while (cont1 == 1)
        {
            try
            {
                System.out.println("Enter an Array Length");
                Scanner leninput = new Scanner(System.in);
                length = leninput.nextInt();
                cont1 = 0;
            } catch (Exception e)
            {
                System.out
                        .println("Please enter an integer value for the Array Length!");
                cont1 = 1;
            }
        }
        while (cont2 == 1)
        {
            try
            {
                System.out
                        .println("Enter an Array Density value between 0.0 and 1.0");
                Scanner densinput = new Scanner(System.in);
                density = densinput.nextDouble();
                cont2 = 0;
            } catch (Exception ex)
            {
                System.out
                        .println("Please enter a valid number between 0.0 and 1.0");
                cont2 = 1;

            }
            int[] darray = randomarray(length, density);
            ArrayList<int[]> sarray = randomarraylist(length, density);

            // randomarray(length,density);// < - This is number 2 of the
            // project
            maxarray(darray); // < - Test run for the maximum value of the array
                              // function, this is method #4
            // randomarraylist(length,density); //< - Test run for the number 3
            // of the project, the sparse array
            maxarraylist(sarray);
        }
    }

    public static int[] randomarray(int length, double density)
    {
        long time_start = System.nanoTime();
        int[] densearray;
        densearray = new int[length];// The array that's created from the length
                                     // we pass
        Random random1 = new Random();// The random number created for the
                                      // density
        Random random2 = new Random();// The random number object created for
                                      // populating the array
        for (int i = 0; i < densearray.length; i++)// Iterates through each
                                                   // value in the array
        {
            int b = random2.nextInt(999999) + 1;// Gives values between 1 and 1
                                                // million... feels a little
                                                // hacky
            double a = random1.nextDouble();// The "actual" random that the
                                            // density is compared to
            if (density >= a)
            {
                densearray[i] = b;
            }

            // System.out.println(densearray[i]);// <- This is how I tested it
            // to show that it worked
        }
        long time_end = System.nanoTime();
        System.out
                .println("The time that it took to create a random array is  "
                        + (time_end - time_start));
        return densearray;
    }

    public static ArrayList<int[]> randomarraylist(int length, double density)
    {
        long time_start = System.nanoTime();
        ArrayList<int[]> randomarraylist = new ArrayList<int[]>();
        Random random3 = new Random(); // The random number created for the
                                       // density
        Random random4 = new Random(); // The random number created for adding
                                       // to the array list
        for (int i = 0; i < length; i++)
        {
            int arrayvalue = random4.nextInt(999999) + 1;
            double c = random3.nextDouble();// The random used for density
            if (density >= c)
            {
                int[] sparsearray;
                sparsearray = new int[2];
                sparsearray[0] = i;
                sparsearray[1] = arrayvalue;
                randomarraylist.add(sparsearray);
                // System.out.println(Arrays.toString(randomarraylist.get(randomarraylist.size()
                // - 1)));// - The code I used to test that the arraylist works
                // and stuff is added to it
            }
        }
        long time_end = System.nanoTime();
        System.out
                .println("The time that it took to create a random array list is "
                        + (time_end - time_start));
        return randomarraylist;
    }

    public static void maxarray(int[] array)
    {
        long time_start = System.nanoTime();
        int max = array[0];// This will hold the current max value, initially
                           // just the value of the first incremented array
                           // value
        int max_index = 0;

        for (int i = 1; i < array.length; i++)
        {
            if (array[i] > max)// Compare the array value with the current max
            {
                max = array[i];// Replace current max with new max
                max_index = i;
            }
        }
        System.out.println("The index of the max is:  " + max_index
                + "  And the maximum value in the array is:  " + max);
        long time_end = System.nanoTime();
        System.out
                .println("The time it took to find the maximum of this array is  "
                        + (time_end - time_start));
    }

    public static void maxarraylist(ArrayList<int[]> arraylist)
    {
        long time_start = System.nanoTime();
        /* Make sure array actually has stuff in it */
        if (arraylist.size() <= 0)
        {
            System.out.println("There's nothing in the array :c");
            return;
        }

        int[] cur_value = arraylist.get(0);
        int max_val = cur_value[1];
        int max_index = cur_value[0];

        for (int i = 1; i < arraylist.size(); i++)
        {
            cur_value = arraylist.get(i);
            if (cur_value[1] > max_val)
            {
                max_val = cur_value[1];
                max_index = cur_value[0];
            }
        }

        System.out
                .println("The maximum value is:  " + max_val
                        + "   And the index where this maximum value is:  "
                        + max_index);
        long time_end = System.nanoTime();
        System.out
                .println("The time it took to find the maximum of this array list is  "
                        + (time_end - time_start));
    }
}

/*
 * Time stuff: Results for array length of 10000, density value of 0.5 Random
 * Array: 2318732, Random Array List: 1709327, Maximum value Array: 180897,
 * Maximum value ArrayList: 559797 Interesting to note that at smaller array
 * lengths, most of the time the array is actually SLOWER than the array list...
 * hopefully this isn't me being a poor programmer Results for array length
 * 100000, density value of 0.5 Random Array: 8485054, Random Array List:
 * 10776416, max array: 1532707, max array list: 3193709 At least for results
 * above 100000, the array has a roughly 20% benefit in speed, and finding the
 * max of the array happens twice as quickly
 * 
 * Results for array length of 1000000, density value of 0.8 The time that it
 * took to create a random array is 38685019 The time that it took to create a
 * random array list is 53690064 The time it took to find the maximum of this
 * array is 4093918 The time it took to find the maximum of this array list is
 * 12067499 Still more gains over the random array list for the random array...
 * and the max of the array is now 3x faster.
 */
