package Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.imageio.IIOException;
import java.io.*;

public class LoggerUtility {

    private static final String suiteLogsPath = "target/logs/suite/";
    private static final String regressionSuiteLogsPath = "target/logs/";
    private static final Logger logger = LogManager.getLogger();

    public static synchronized void errorLog(String errorName){
        logger.info(Thread.currentThread().getName() + ": " + getCallInfo() + errorName);
    }

    public static void mergeFilesIntoOne(){
        File dir = new File(suiteLogsPath);
        String[] fileNames = dir.list();

        PrintWriter pW = null;

        try {
            pW = new PrintWriter(regressionSuiteLogsPath + "regressionLogs.log");

            for (String fileName : fileNames) {
                File f = new File(dir, fileName);
                BufferedReader bR = new BufferedReader(new FileReader(f));
                pW.println("Content of file " + fileName);
                String line = bR.readLine();
                while (line != null) {
                    pW.println(line);
                    line = bR.readLine();
                }

                pW.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }

    }

    public static synchronized void startTestCase(String testName){
        ThreadContext.put("threadName", testName);
        logger.info("------------- execution started ------------- " + testName );
    }

    public static synchronized void endTestCase(String testName){
        ThreadContext.put("threadName", testName);
        logger.info("-------------  execution ended ------------- " + testName );
    }

    public static synchronized void infoTest(String stepName){
        logger.info(Thread.currentThread().getName() + ": " + getCallInfo() + " " + stepName);
    }

    public static synchronized String getCallInfo(){
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();

        return className + " " + methodName;
    }


}
