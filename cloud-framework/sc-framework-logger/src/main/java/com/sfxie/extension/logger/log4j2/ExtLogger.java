package com.sfxie.extension.logger.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

/**
 * Extended Logger interface with convenience methods for
 * the BUSINESS, BUSINESSERROR and CONTROLLER custom log levels.
 */
public final class ExtLogger extends ExtendedLoggerWrapper {
    private static final long serialVersionUID = 955844600578378L;
    private final ExtendedLoggerWrapper logger;

    private static final String FQCN = ExtLogger.class.getName();
    private static final Level BUSINESS = Level.forName("BUSINESS", 350);
    private static final Level BUSINESSERROR = Level.forName("BUSINESSERROR", 351);
    private static final Level CONTROLLER = Level.forName("CONTROLLER", 352);

    private ExtLogger(final Logger logger) {
        super((AbstractLogger) logger, logger.getName(), logger.getMessageFactory());
        this.logger = this;
    }

    /**
     * Returns a custom Logger with the name of the calling class.
     * 
     * @return The custom Logger for the calling class.
     */
    public static ExtLogger create() {
        final Logger wrapped = LogManager.getLogger();
        return new ExtLogger(wrapped);
    }

    /**
     * Returns a custom Logger using the fully qualified name of the Class as
     * the Logger name.
     * 
     * @param loggerName The Class whose name should be used as the Logger name.
     *            If null it will default to the calling class.
     * @return The custom Logger.
     */
    public static ExtLogger create(final Class<?> loggerName) {
        final Logger wrapped = LogManager.getLogger(loggerName);
        return new ExtLogger(wrapped);
    }

    /**
     * Returns a custom Logger using the fully qualified name of the Class as
     * the Logger name.
     * 
     * @param loggerName The Class whose name should be used as the Logger name.
     *            If null it will default to the calling class.
     * @param messageFactory The message factory is used only when creating a
     *            logger, subsequent use does not change the logger but will log
     *            a warning if mismatched.
     * @return The custom Logger.
     */
    public static ExtLogger create(final Class<?> loggerName, final MessageFactory factory) {
        final Logger wrapped = LogManager.getLogger(loggerName, factory);
        return new ExtLogger(wrapped);
    }

    /**
     * Returns a custom Logger using the fully qualified class name of the value
     * as the Logger name.
     * 
     * @param value The value whose class name should be used as the Logger
     *            name. If null the name of the calling class will be used as
     *            the logger name.
     * @return The custom Logger.
     */
    public static ExtLogger create(final Object value) {
        final Logger wrapped = LogManager.getLogger(value);
        return new ExtLogger(wrapped);
    }

    /**
     * Returns a custom Logger using the fully qualified class name of the value
     * as the Logger name.
     * 
     * @param value The value whose class name should be used as the Logger
     *            name. If null the name of the calling class will be used as
     *            the logger name.
     * @param messageFactory The message factory is used only when creating a
     *            logger, subsequent use does not change the logger but will log
     *            a warning if mismatched.
     * @return The custom Logger.
     */
    public static ExtLogger create(final Object value, final MessageFactory factory) {
        final Logger wrapped = LogManager.getLogger(value, factory);
        return new ExtLogger(wrapped);
    }

    /**
     * Returns a custom Logger with the specified name.
     * 
     * @param name The logger name. If null the name of the calling class will
     *            be used.
     * @return The custom Logger.
     */
    public static ExtLogger create(final String name) {
        final Logger wrapped = LogManager.getLogger(name);
        return new ExtLogger(wrapped);
    }

    /**
     * Returns a custom Logger with the specified name.
     * 
     * @param name The logger name. If null the name of the calling class will
     *            be used.
     * @param messageFactory The message factory is used only when creating a
     *            logger, subsequent use does not change the logger but will log
     *            a warning if mismatched.
     * @return The custom Logger.
     */
    public static ExtLogger create(final String name, final MessageFactory factory) {
        final Logger wrapped = LogManager.getLogger(name, factory);
        return new ExtLogger(wrapped);
    }

    /**
     * Logs a message with the specific Marker at the {@code BUSINESS} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     */
    public void business(final Marker marker, final Message msg) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, msg, (Throwable) null);
    }

    /**
     * Logs a message with the specific Marker at the {@code BUSINESS} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void business(final Marker marker, final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, msg, t);
    }

    /**
     * Logs a message object with the {@code BUSINESS} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void business(final Marker marker, final Object message) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code BUSINESS} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void business(final Marker marker, final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, message, t);
    }

    /**
     * Logs a message object with the {@code BUSINESS} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void business(final Marker marker, final String message) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code BUSINESS} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     * @see #getMessageFactory()
     */
    public void business(final Marker marker, final String message, final Object... params) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, message, params);
    }

    /**
     * Logs a message at the {@code BUSINESS} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void business(final Marker marker, final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, message, t);
    }

    /**
     * Logs the specified Message at the {@code BUSINESS} level.
     * 
     * @param msg the message string to be logged
     */
    public void business(final Message msg) {
        logger.logIfEnabled(FQCN, BUSINESS, null, msg, (Throwable) null);
    }

    /**
     * Logs the specified Message at the {@code BUSINESS} level.
     * 
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void business(final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, null, msg, t);
    }

    /**
     * Logs a message object with the {@code BUSINESS} level.
     * 
     * @param message the message object to log.
     */
    public void business(final Object message) {
        logger.logIfEnabled(FQCN, BUSINESS, null, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code BUSINESS} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void business(final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, null, message, t);
    }

    /**
     * Logs a message object with the {@code BUSINESS} level.
     * 
     * @param message the message object to log.
     */
    public void business(final String message) {
        logger.logIfEnabled(FQCN, BUSINESS, null, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code BUSINESS} level.
     * 
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     * @see #getMessageFactory()
     */
    public void business(final String message, final Object... params) {
        logger.logIfEnabled(FQCN, BUSINESS, null, message, params);
    }

    /**
     * Logs a message at the {@code BUSINESS} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void business(final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, null, message, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the {@code BUSINESS}level.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void business(final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESS, null, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESS}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
    public void business(final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, null, msgSupplier, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code BUSINESS} level with the specified Marker.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void business(final Marker marker, final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is the
     * {@code BUSINESS} level.
     *
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void business(final Marker marker, final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, message, paramSuppliers);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESS}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t A Throwable or null.
     * @since 2.4
     */
    public void business(final Marker marker, final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, msgSupplier, t);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is
     * the {@code BUSINESS} level.
     *
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void business(final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, BUSINESS, null, message, paramSuppliers);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code BUSINESS} level with the specified Marker. The {@code MessageSupplier} may or may
     * not use the {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
    public void business(final Marker marker, final MessageSupplier msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESS}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t A Throwable or null.
     * @since 2.4
     */
    public void business(final Marker marker, final MessageSupplier msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, marker, msgSupplier, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code BUSINESS} level. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
    public void business(final MessageSupplier msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESS, null, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESS}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     * The {@code MessageSupplier} may or may not use the {@link MessageFactory} to construct the
     * {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
    public void business(final MessageSupplier msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESS, null, msgSupplier, t);
    }

    /**
     * Logs a message with the specific Marker at the {@code BUSINESSERROR} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     */
    public void businesserror(final Marker marker, final Message msg) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, msg, (Throwable) null);
    }

    /**
     * Logs a message with the specific Marker at the {@code BUSINESSERROR} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void businesserror(final Marker marker, final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, msg, t);
    }

    /**
     * Logs a message object with the {@code BUSINESSERROR} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void businesserror(final Marker marker, final Object message) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code BUSINESSERROR} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void businesserror(final Marker marker, final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, message, t);
    }

    /**
     * Logs a message object with the {@code BUSINESSERROR} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void businesserror(final Marker marker, final String message) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code BUSINESSERROR} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     * @see #getMessageFactory()
     */
    public void businesserror(final Marker marker, final String message, final Object... params) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, message, params);
    }

    /**
     * Logs a message at the {@code BUSINESSERROR} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void businesserror(final Marker marker, final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, message, t);
    }

    /**
     * Logs the specified Message at the {@code BUSINESSERROR} level.
     * 
     * @param msg the message string to be logged
     */
    public void businesserror(final Message msg) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, msg, (Throwable) null);
    }

    /**
     * Logs the specified Message at the {@code BUSINESSERROR} level.
     * 
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void businesserror(final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, msg, t);
    }

    /**
     * Logs a message object with the {@code BUSINESSERROR} level.
     * 
     * @param message the message object to log.
     */
    public void businesserror(final Object message) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code BUSINESSERROR} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void businesserror(final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, message, t);
    }

    /**
     * Logs a message object with the {@code BUSINESSERROR} level.
     * 
     * @param message the message object to log.
     */
    public void businesserror(final String message) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code BUSINESSERROR} level.
     * 
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     * @see #getMessageFactory()
     */
    public void businesserror(final String message, final Object... params) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, message, params);
    }

    /**
     * Logs a message at the {@code BUSINESSERROR} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void businesserror(final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, message, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the {@code BUSINESSERROR}level.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void businesserror(final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESSERROR}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
    public void businesserror(final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, msgSupplier, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code BUSINESSERROR} level with the specified Marker.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void businesserror(final Marker marker, final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is the
     * {@code BUSINESSERROR} level.
     *
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void businesserror(final Marker marker, final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, message, paramSuppliers);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESSERROR}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t A Throwable or null.
     * @since 2.4
     */
    public void businesserror(final Marker marker, final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, msgSupplier, t);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is
     * the {@code BUSINESSERROR} level.
     *
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void businesserror(final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, message, paramSuppliers);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code BUSINESSERROR} level with the specified Marker. The {@code MessageSupplier} may or may
     * not use the {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
    public void businesserror(final Marker marker, final MessageSupplier msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESSERROR}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t A Throwable or null.
     * @since 2.4
     */
    public void businesserror(final Marker marker, final MessageSupplier msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, marker, msgSupplier, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code BUSINESSERROR} level. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
    public void businesserror(final MessageSupplier msgSupplier) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code BUSINESSERROR}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     * The {@code MessageSupplier} may or may not use the {@link MessageFactory} to construct the
     * {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
    public void businesserror(final MessageSupplier msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, BUSINESSERROR, null, msgSupplier, t);
    }

    /**
     * Logs a message with the specific Marker at the {@code CONTROLLER} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     */
    public void controller(final Marker marker, final Message msg) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, msg, (Throwable) null);
    }

    /**
     * Logs a message with the specific Marker at the {@code CONTROLLER} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void controller(final Marker marker, final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, msg, t);
    }

    /**
     * Logs a message object with the {@code CONTROLLER} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void controller(final Marker marker, final Object message) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code CONTROLLER} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void controller(final Marker marker, final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, message, t);
    }

    /**
     * Logs a message object with the {@code CONTROLLER} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message object to log.
     */
    public void controller(final Marker marker, final String message) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code CONTROLLER} level.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     * @see #getMessageFactory()
     */
    public void controller(final Marker marker, final String message, final Object... params) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, message, params);
    }

    /**
     * Logs a message at the {@code CONTROLLER} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param marker the marker data specific to this log statement
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void controller(final Marker marker, final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, message, t);
    }

    /**
     * Logs the specified Message at the {@code CONTROLLER} level.
     * 
     * @param msg the message string to be logged
     */
    public void controller(final Message msg) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, msg, (Throwable) null);
    }

    /**
     * Logs the specified Message at the {@code CONTROLLER} level.
     * 
     * @param msg the message string to be logged
     * @param t A Throwable or null.
     */
    public void controller(final Message msg, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, msg, t);
    }

    /**
     * Logs a message object with the {@code CONTROLLER} level.
     * 
     * @param message the message object to log.
     */
    public void controller(final Object message) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, message, (Throwable) null);
    }

    /**
     * Logs a message at the {@code CONTROLLER} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void controller(final Object message, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, message, t);
    }

    /**
     * Logs a message object with the {@code CONTROLLER} level.
     * 
     * @param message the message object to log.
     */
    public void controller(final String message) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, message, (Throwable) null);
    }

    /**
     * Logs a message with parameters at the {@code CONTROLLER} level.
     * 
     * @param message the message to log; the format depends on the message factory.
     * @param params parameters to the message.
     * @see #getMessageFactory()
     */
    public void controller(final String message, final Object... params) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, message, params);
    }

    /**
     * Logs a message at the {@code CONTROLLER} level including the stack trace of
     * the {@link Throwable} {@code t} passed as parameter.
     * 
     * @param message the message to log.
     * @param t the exception to log, including its stack trace.
     */
    public void controller(final String message, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, message, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the {@code CONTROLLER}level.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void controller(final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code CONTROLLER}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     *
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
    public void controller(final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, msgSupplier, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code CONTROLLER} level with the specified Marker.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @since 2.4
     */
    public void controller(final Marker marker, final Supplier<?> msgSupplier) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is the
     * {@code CONTROLLER} level.
     *
     * @param marker the marker data specific to this log statement
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void controller(final Marker marker, final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, message, paramSuppliers);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code CONTROLLER}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message;
     *            the format depends on the message factory.
     * @param t A Throwable or null.
     * @since 2.4
     */
    public void controller(final Marker marker, final Supplier<?> msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, msgSupplier, t);
    }

    /**
     * Logs a message with parameters which are only to be constructed if the logging level is
     * the {@code CONTROLLER} level.
     *
     * @param message the message to log; the format depends on the message factory.
     * @param paramSuppliers An array of functions, which when called, produce the desired log message parameters.
     * @since 2.4
     */
    public void controller(final String message, final Supplier<?>... paramSuppliers) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, message, paramSuppliers);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code CONTROLLER} level with the specified Marker. The {@code MessageSupplier} may or may
     * not use the {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
    public void controller(final Marker marker, final MessageSupplier msgSupplier) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code CONTROLLER}
     * level) with the specified Marker and including the stack trace of the {@link Throwable}
     * <code>t</code> passed as parameter. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param marker the marker data specific to this log statement
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t A Throwable or null.
     * @since 2.4
     */
    public void controller(final Marker marker, final MessageSupplier msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, marker, msgSupplier, t);
    }

    /**
     * Logs a message which is only to be constructed if the logging level is the
     * {@code CONTROLLER} level. The {@code MessageSupplier} may or may not use the
     * {@link MessageFactory} to construct the {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @since 2.4
     */
    public void controller(final MessageSupplier msgSupplier) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, msgSupplier, (Throwable) null);
    }

    /**
     * Logs a message (only to be constructed if the logging level is the {@code CONTROLLER}
     * level) including the stack trace of the {@link Throwable} <code>t</code> passed as parameter.
     * The {@code MessageSupplier} may or may not use the {@link MessageFactory} to construct the
     * {@code Message}.
     *
     * @param msgSupplier A function, which when called, produces the desired log message.
     * @param t the exception to log, including its stack trace.
     * @since 2.4
     */
    public void controller(final MessageSupplier msgSupplier, final Throwable t) {
        logger.logIfEnabled(FQCN, CONTROLLER, null, msgSupplier, t);
    }
}

