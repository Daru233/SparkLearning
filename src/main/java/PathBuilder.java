import com.amazonaws.thirdparty.joda.time.DateTime;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PathBuilder {

    private static final LocalDate START_DATE = LocalDate.of(2023, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 1, 7);
    private static final String PATH_TO_S3_FLE = "s3a://spark-learning-datasets/spark_dataset/";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static void main(String[] args) {

        List<String> paths = new ArrayList<>();

        for (LocalDate date = START_DATE; date.isBefore(END_DATE); date = date.plusDays(1)) {
            paths.add(PATH_TO_S3_FLE + date.format(FORMAT) + ".gz");
        }

    }

}
