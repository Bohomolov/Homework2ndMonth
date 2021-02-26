package log4jhw.blogic;

import log4jhw.exception.MyException;
import org.apache.log4j.Logger;

import java.util.Random;

public class RandomValue {
    private final Random random = new Random();
    private final static Logger logger = Logger.getLogger(RandomValue.class);



    public int getRandomValue() {

        int randomValue = random.nextInt(11);
        if (randomValue <= 5) {
            try {
                throw new MyException("Сгенерированное число – " + randomValue);
            } catch (MyException e) {
                logger.error(e);
            }
        } else {
            logger.info("Приложение успешно запущено");
        }
        return randomValue;
    }
}