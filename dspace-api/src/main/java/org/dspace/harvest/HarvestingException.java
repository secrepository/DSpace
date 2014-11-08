package org.dspace.harvest;

/**
 * Exception class specifically assigned to recoverable errors that occur during harvesting. Throughout the harvest process, various exceptions
 * are caught and turned into a HarvestingException. Uncaught exceptions are irrecoverable errors.
 * @author alexey
 */
public class HarvestingException extends Exception{
    public HarvestingException() {
  	        super();
  	    }

    public HarvestingException(String message, Throwable t) {
        super(message, t);
    }

    public HarvestingException(String message) {
        super(message);
    }

    public HarvestingException(Throwable t) {
        super(t);
    }

}
