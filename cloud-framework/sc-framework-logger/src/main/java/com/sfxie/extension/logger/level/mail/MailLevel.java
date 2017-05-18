package com.sfxie.extension.logger.level.mail;


import org.apache.log4j.Level;

/**
 * @project MRMAutoloc
 * @author sunnylocus
 * @vresion 1.0 2009-7-22
 * @description 自定义级别REMIND，该级别用来发送提醒邮件,级别要比INFO低
 */
public class MailLevel extends Level {
	
	private static final long serialVersionUID = 1L;

	static private final int MAIL_INFO_INT = Level.INFO_INT + 13;
	static private final int MAIL_FATAL_INT = Level.FATAL_INT + 11;

	private static String MAILINFO_STR = "MAILINFO";
	private static String MAILERROR_STR = "MAILERROR";

	/**	INFO级别邮件	*/
	public static final MailLevel MAILINFO = new MailLevel(MAIL_INFO_INT, MAILINFO_STR,1);
	/**	FATAL级别邮件	*/
	public static final MailLevel MAILERROR = new MailLevel(MAIL_FATAL_INT, MAILERROR_STR,2);

	protected MailLevel(int level, String strLevel, int syslogEquiv) {
		super(level, strLevel, syslogEquiv);
	}

	/**
	 * Convert the string passed as argument to a level. If the conversion
	 * fails, then this method returns {@link #REMIND}.
	 */
	public static Level toLevel(String sArg) {
		return (Level) toLevel(sArg, MailLevel.MAILINFO);
	}

	public static Level toLevel(String sArg, Level defaultValue) {
		if (sArg == null) {
			return defaultValue;
		}
		String stringVal = sArg.toUpperCase();

		if (stringVal.equals(MAILINFO_STR)) {
			return MailLevel.MAILINFO;
		} else if (stringVal.equals(MAILERROR_STR)) {
			return MailLevel.MAILERROR;
		}

		return Level.toLevel(sArg, (Level) defaultValue);
	}
	public static Level toLevel(int i) throws IllegalArgumentException {
		switch (i) {
		case MAIL_INFO_INT:
			return MailLevel.MAILINFO;
		case MAIL_FATAL_INT:
			return MailLevel.MAILERROR;
		}
		return Level.toLevel(i);
	}

}
