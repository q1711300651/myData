package testLog;


import org.apache.log4j.Logger;

/**
 * Created by ww on 17/3/11.
 */
public class Log4jDemo {
    public static  void  main(String[]args){
        Logger logger=Logger.getLogger(Log4jDemo.class.getName());
        logger.debug("debug-demo");
        logger.info("info");
        System.out.println("a");
    }
}
