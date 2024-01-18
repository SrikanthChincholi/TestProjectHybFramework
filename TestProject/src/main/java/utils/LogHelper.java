package utils;

import org.apache.logging.log4j.*;

public class LogHelper {

	static boolean logger = false;

	static Logger log;

	public static Logger getLogger(Class clazz) {
		if (logger == false) {
			return log = LogManager.getLogger(clazz);
		} else {
			return log;
		}
	}

}
