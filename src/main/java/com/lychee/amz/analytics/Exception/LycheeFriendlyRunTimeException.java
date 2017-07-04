package com.lychee.amz.analytics.Exception;

/**
 *
 * this exception is only for testing purpose.
 *
 */
public class LycheeFriendlyRunTimeException extends LycheeRunTimeException implements ILycheeFriendlyException {
    public LycheeFriendlyRunTimeException (){
        super(403, "Forbidden","user did not has permission to execute the action");
    }

}
