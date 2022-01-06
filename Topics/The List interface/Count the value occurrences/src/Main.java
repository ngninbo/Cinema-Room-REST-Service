import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        // implement the method
        int numOccurrenceList1 = 0;
        int numOccurrenceList2 = 0;

        for (Integer integer : list1) {
            if (integer.equals(elem)) {
                numOccurrenceList1++;
            }
        }

        for (Integer value : list2) {
            if (value.equals(elem)) {
                numOccurrenceList2++;
            }
        }

        return numOccurrenceList1 == numOccurrenceList2;
    }
}