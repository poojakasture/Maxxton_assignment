package DateMergerAssignment;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Represents DateRage class as a model for DateMerger operation
 */
public class DateRange {
    private LocalDate initialDate;

    private LocalDate finalDate;

    public DateRange(LocalDate initialDate, LocalDate finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public static final Comparator<DateRange> COMPARE_DATES = (DateRange o1, DateRange o2) -> {
        if (o1.getInitialDate() != null && o2.getInitialDate() != null) {
            if (o1.getInitialDate().isBefore(o2.getInitialDate())) {
                return -1;
            }
            else {
                return o1.getInitialDate().isAfter(o2.getInitialDate()) ? 1 : 0;
            }
        }
        else if (o1.getInitialDate() != null && o2.getInitialDate() == null) {
            return -1;
        }
        else if (o1.getInitialDate() == null && o2.getInitialDate() != null) {
            return 1;
        }
        else {
            return 0;
        }
    };
}
