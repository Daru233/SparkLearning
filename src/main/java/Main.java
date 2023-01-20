import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.codehaus.janino.Java;

public class Main {

    private static final String APPNAME = "SparkLearning";
    private static final String MASTER = "local";
    private static final String PATH_INCEPTION_SCRIPT = "E:\\Development\\InceptionScript.txt";
    private static final String PATH_COURSE_INFO_CSV = "E:\\Development\\Course_info.csv";

    private static final String PATH_TO_DATASET_FOLDER = "E:\\Development\\spark_dataset\\";
    private static final String BASE = PATH_TO_DATASET_FOLDER + "Base.csv";
    private static final String VARIANT1 = PATH_TO_DATASET_FOLDER + "Variant1.csv";
    private static final String VARIANT2 = PATH_TO_DATASET_FOLDER + "Variant2.csv";
    private static final String VARIANT3 = PATH_TO_DATASET_FOLDER + "Variant3.csv";
    private static final String VARIANT4 = PATH_TO_DATASET_FOLDER + "Variant4.csv";
    private static final String VARIANT5 = PATH_TO_DATASET_FOLDER + "Variant5.csv";


    public static void main(String[] args) {

        // CONFIG
        SparkConf conf = new SparkConf()
                .setAppName(APPNAME)
                .setMaster(MASTER);

        JavaSparkContext sc = new JavaSparkContext(conf);

        SparkSession spark = SparkSession
                .builder()
                .appName(APPNAME)
                .master("local[4]")
                .getOrCreate();

        // LOAD MULTIPLE FILES IN TO ONE RDD
        JavaRDD<Row> items = spark.read().csv(BASE, VARIANT1, VARIANT2, VARIANT3, VARIANT4, VARIANT5).toJavaRDD();
        long num_of_rows = items.count();
        printResults(num_of_rows);

        // defines base RDD from external source - in this case a text file
        // This dataset is not loaded in memory or otherwise acted on: I
        // NCEPTION_SCRIPT is a pointer to the file.
//        JavaRDD<String> INCEPTION_SCRIPT = sc.textFile(PATH_INCEPTION_SCRIPT);

        // The second line defines lineLengths as the result of a map transformation
        // lineLengths is not immediately computed, due to laziness
//        JavaRDD<Integer> lineLengths = INCEPTION_SCRIPT.map(s -> s.length());


        // Finally, we run reduce, which is an action.
        // At this point Spark breaks the computation into tasks to run on separate machines
        // each machine runs both its part of the map and a local reduction
        // returning only its answer to the driver program.
//        int totalLength = lineLengths.reduce((a, b) -> a + b);


//        printResults(totalLength);

        // CLOSING
        sc.close();

    }

    private static void printResults(long reducedResult) {

        // 214494 lines
        System.out.println("================= Results =================");
        System.out.println();
        System.out.println(reducedResult);
        System.out.println();
        System.out.println("================= Results =================");
    }


}
