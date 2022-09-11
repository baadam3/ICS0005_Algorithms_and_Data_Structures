import java.util.*;

public class Answer {

    public static void main(String[] param) {

        // TODO!!! Solutions to small problems
        //   that do not need an independent method!

        // conversion double -> String
        Double decimal = 0.5;
        String fromDecimal = decimal.toString();
        System.out.println("Convert from double to string: " + fromDecimal);
        // conversion String -> int
        String number = "11";
        Integer fromString = Integer.parseInt(number);
        System.out.println("From string to int: " + fromString);
        // "hh:mm:ss"
        Calendar time = Calendar.getInstance();
        System.out.println(time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE) + ":" + time.get(Calendar.SECOND));
        // cos 45 deg
        System.out.println(Math.toRadians(Math.cos(45.0)));
        // table of square roots
        for (int i = 0; i < 101; i++) {
            double root = i;
            System.out.printf("%d root: %f\n", i, Math.sqrt(root));
        }
        // reverse string
        String firstString = "ABcd12";
        String result = reverseCase(firstString);
        System.out.println("\"" + firstString + "\" -> \"" + result + "\"");


        String s = "How  many	 words   here";
        int nw = countWords(s);
        System.out.println(s + "\t" + String.valueOf(nw));

        // pause. COMMENT IT OUT BEFORE JUNIT-TESTING!

        final int LSIZE = 100;
        ArrayList<Integer> randList = new ArrayList<Integer>(LSIZE);
        Random generaator = new Random();
        for (int i = 0; i < LSIZE; i++) {
            randList.add(Integer.valueOf(generaator.nextInt(1000)));
        }

        //time difference
        Calendar currentTime = Calendar.getInstance();
        Integer hour, minute, second, millisecond;
        Integer hour2, minute2, second2, millisecond2;
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);
        second = currentTime.get(Calendar.SECOND);
        millisecond = currentTime.get(Calendar.MILLISECOND);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Calendar currentTime2 = Calendar.getInstance();
        hour2 = currentTime2.get(Calendar.HOUR_OF_DAY);
        minute2 = currentTime2.get(Calendar.MINUTE);
        second2 = currentTime2.get(Calendar.SECOND);
        millisecond2 = currentTime2.get(Calendar.MILLISECOND);
        System.out.printf("Time difference: %d:%d:%d:%d\n", hour2 - hour, minute2 - minute, second2 - second, millisecond2 - millisecond);
        // minimal element
        Integer min = randList.get(0);
        for (int i = 0; i < randList.size(); i++) {
            if (randList.get(i) < min)
                min = randList.get(i);
        }
        System.out.println("The minimum is: " + min);
        // HashMap tasks:
        //    create
        HashMap<String, String> myHashMap = new HashMap<String, String>();
        myHashMap.put("1111", "Algorithms");
        myHashMap.put("1112", "Maths");
        myHashMap.put("1113", "Programming");
        myHashMap.put("1114", "Physics");
        myHashMap.put("1115", "Cyber security");
        //    print all keys
        System.out.println(myHashMap.keySet());
        //    remove a key
        myHashMap.remove("1114");
        //    print all pairs
        for (Map.Entry<String, String> stringStringEntry : myHashMap.entrySet()) {
            System.out.println(stringStringEntry.getKey() + " = " + stringStringEntry);
        }
        System.out.println("Before reverse:  " + randList);
        reverseList(randList);
        System.out.println("After reverse: " + randList);

        System.out.println("Maximum: " + maximum(randList));
    }

    /**
     * Finding the maximal element.
     *
     * @param a Collection of Comparable elements
     * @return maximal element.
     * @throws NoSuchElementException if <code> a </code> is empty.
     */
    static public <T extends Object & Comparable<? super T>>
    T maximum(Collection<? extends T> a)
            throws NoSuchElementException {
        return Collections.max(a); // TODO!!! Your code here
    }

    /**
     * Counting the number of words. Any number of any kind of
     * whitespace symbols between words is allowed.
     *
     * @param text text
     * @return number of words in the text
     */
    public static int countWords(String text) {
        // TODO!!! Your code here
        int wordNumber = 0;
        for (int i = 0; i < text.length() - 1; i++) {
            if (Character.isAlphabetic(text.charAt(i)) && !Character.isAlphabetic(text.charAt(i + 1))) {
                wordNumber++;
            }
            if (i == text.length() - 2 && Character.isAlphabetic(text.charAt(i + 1))) {
                wordNumber++;
            }
        }
        return wordNumber;
    }

    /**
     * Case-reverse. Upper -> lower AND lower -> upper.
     *
     * @param s string
     * @return processed string
     */
    public static String reverseCase(String s) {
        // TODO!!! Your code here
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                result += Character.toUpperCase(s.charAt(i));
            } else {
                result += Character.toLowerCase(s.charAt(i));
            }
        }
        return result;
    }

    /**
     * List reverse. Do not create a new list.
     *
     * @param list list to reverse
     */
    public static <T extends Object> void reverseList(List<T> list)
            throws UnsupportedOperationException {
        // TODO!!! Your code here
       /* int fromStart = 0;
        T helper;
        for (int i = list.size() - 1; i >= 0; i--) {
            helper=list.get(i);
            list.set(i,list.get(fromStart));
            list.set(fromStart,helper);
            fromStart++;
        }*///why is it not working?
        Collections.reverse(list);
    }
}
