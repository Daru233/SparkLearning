import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DateRangeVerifier {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final LocalDate START_DATE = LocalDate.of(2000, 2, 8);
    private static final LocalDate END_DATE = LocalDate.of(2024, 2, 8);

    public static void main(String[] args) {

        List<String> list_of_dates = generateDates();

        list_of_dates.stream()
                .map(date -> LocalDate.parse(date, FORMAT))
                .filter(date -> date.isBefore(END_DATE) && date.isAfter(START_DATE))
                .forEach(System.out::println);

    }

    @NotNull
    private static List<String> generateDates() {
        List<String> dates = new ArrayList<>();

        // dates from 8th of feb from year 2008 - 2020
        dates.add("20080208");
        dates.add("20090208");
        dates.add("20100208");
        dates.add("20110208");
        dates.add("20120208");
        dates.add("20130208");
        dates.add("20140208");
        dates.add("20150208");
        dates.add("20160208");
        dates.add("20170208");
        dates.add("20180208");
        dates.add("20190208");
        dates.add("20200208");
        dates.add("20210208");
        dates.add("20220208");
        dates.add("20230208");

        return dates;
    }

}
