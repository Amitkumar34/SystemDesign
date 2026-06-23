package LLD.Logger;

public class LoggerApplication {

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.info("This is info");
        logger.error("This is error");
        logger.debug("This is debug");
    }
}
