import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String APPNAME = "SparkLearning";
    private static final String MASTER = "local";
    private static final String PATH_TO_TEXT_FILE = "E:\\Development\\InceptionScript.txt";

    public static void main(String[] args) {

        // CONFIG
        SparkConf conf = new SparkConf()
                .setAppName(APPNAME)
                .setMaster(MASTER);

        JavaSparkContext sc = new JavaSparkContext(conf);

        // defines base RDD from external source - in this case a text file
        // This dataset is not loaded in memory or otherwise acted on: I
        // NCEPTION_SCRIPT is a pointer to the file.
        JavaRDD<String> INCEPTION_SCRIPT = sc.textFile(PATH_TO_TEXT_FILE);

        // The second line defines lineLengths as the result of a map transformation
        // lineLengths is not immediately computed, due to laziness
        JavaRDD<Integer> lineLengths = INCEPTION_SCRIPT.map(s -> s.length());
        JavaRDD<String> lineContents = INCEPTION_SCRIPT;

        INCEPTION_SCRIPT.foreach(item -> {
            System.out.println("* " + item + " *" + item.length());
        });

        // Finally, we run reduce, which is an action.
        // At this point Spark breaks the computation into tasks to run on separate machines
        // each machine runs both its part of the map and a local reduction
        // returning only its answer to the driver program.
//        int totalLength = lineLengths.reduce((a, b) -> a + b);


//        printResults(totalLength);

        // CLOSING
        sc.close();

    }

    private static void printResults(int reducedResult) {

        // 214494 lines
        System.out.println("================= Results =================");
        System.out.println();
        System.out.println(reducedResult);
        System.out.println();
        System.out.println("================= Results =================");
    }


}
