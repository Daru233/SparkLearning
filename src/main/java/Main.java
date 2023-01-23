import org.apache.spark.sql.SparkSession;
import java.util.Arrays;


public class Main {

    private static final String APPNAME = "SparkLearning";
    private static final String MASTER = "local[6]";

    private static final String PATH_TO_S3_FLE = "s3a://spark-learning-datasets/spark_dataset/*";
    private static final String AWS_ACCESS_KEY = System.getenv("aws_access_key");
    private static final String AWS_SECRET_KEY = System.getenv("aws_secret_key");

    public static void main(String[] args) {

        // config
        SparkSession spark = SparkSession
                .builder()
                .appName(APPNAME)
                .master(MASTER)
                .config("spark.jars.packages", "org.apache.hadoop:hadoop-aws:3.3.4")
                .getOrCreate();

        // setting keys for aws
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.access.key", AWS_ACCESS_KEY);
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.secret.key", AWS_SECRET_KEY);
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.endpoint", "s3.amazonaws.com");

        // TODO glob pattern for paths

        // read file names only, the contents
        String[] filesNames = spark.read().format("csv").load(PATH_TO_S3_FLE).inputFiles();

        // create stream to
        Arrays.stream(filesNames).forEach(item -> {
            System.out.println(item);
        });

    }

    private static void printResults(long reducedResult) {
        System.out.println("================= Results =================");
        System.out.println();
        System.out.println(reducedResult);
        System.out.println();
        System.out.println("================= Results =================");
    }

}
