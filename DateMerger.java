package DateMergerAssignment;

import java.time.LocalDate;
import java.util.*;

/**
 * Represents class which performs date merger operation on given dates
 */

public class DateMerger {

    /**
    Represents method to merge date ranges
    @param inputDateRanges : list of given input date ranges
     */
    private static List<DateRange> mergeDateRange(List<DateRange> inputDateRanges) {
        Set<DateRange> datesMerged = new HashSet<>();
        inputDateRanges.sort(DateRange.COMPARE_DATES);

        datesMerged.add(inputDateRanges.get(0));
        for (int index = 1; index < inputDateRanges.size(); index++) {
            DateRange current = inputDateRanges.get(index);
            List<DateRange> toBeAdded = new ArrayList<>();
            boolean rangeMerged = false;
            for (DateRange mergedRange : datesMerged) {
                DateRange merged = getMergedDates(mergedRange, current);
                if (merged == null) {
                    toBeAdded.add(current);
                }
                else {
                    mergedRange.setFinalDate(merged.getFinalDate());
                    mergedRange.setInitialDate(merged.getInitialDate());
                    rangeMerged = true;
                    break;
                }
            }
            if (!rangeMerged) {
                datesMerged.addAll(toBeAdded);
            }
            toBeAdded.clear();
        }
        List<DateRange> finalDatesMerged = new ArrayList<>(datesMerged);
        finalDatesMerged.sort(DateRange.COMPARE_DATES);
        return finalDatesMerged;
    }

    /**
     * Represents method to check if two dates overlap or not
     * @param mergedRange : initial date to compare
     * @param current : next date to compare
     * @return if dates overlap then list else null
     */
    private static DateRange getMergedDates(DateRange mergedRange, DateRange current) {
        if (mergedRange.getInitialDate().isAfter(current.getFinalDate()) || mergedRange.getFinalDate().isBefore(current.getInitialDate())) {
            return null;
        }
        else {
            return new DateRange(mergedRange.getInitialDate().isBefore(current.getInitialDate()) ? mergedRange.getInitialDate() : current.getInitialDate(),
                    mergedRange.getFinalDate().isAfter(current.getFinalDate()) ? mergedRange.getFinalDate() : current.getFinalDate());
        }
    }

    public static void main(String[] args) throws Exception {
        List<DateRange> inputDateRanges = new ArrayList<>();
        inputDateRanges.add(new DateRange(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 30)));
        inputDateRanges.add(new DateRange(LocalDate.of(2020, 1, 15), LocalDate.of(2020, 2, 15)));
        inputDateRanges.add(new DateRange(LocalDate.of(2020, 3, 10), LocalDate.of(2020, 4, 15)));
        inputDateRanges.add(new DateRange(LocalDate.of(2020, 4, 10), LocalDate.of(2020, 5, 15)));

        System.out.println("\n*********** Input Date ranges ****************");
        inputDateRanges.forEach(dateRange -> System.out.println(dateRange.getInitialDate() + " - " + dateRange.getFinalDate()));

        List<DateRange> mergeDates = mergeDateRange(inputDateRanges);

        System.out.println("\n*********** Merged Date Output ****************");
        mergeDates.forEach(dateRange -> System.out.println(dateRange.getInitialDate() + " - " + dateRange.getFinalDate()));
    }
}
