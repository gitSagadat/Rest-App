package io.saga.RestApp.util;

/**
 * @author Sagadat Kuandykov
 */
public class UserNotCreatedException  extends RuntimeException{
    public UserNotCreatedException(String msg){
        super(msg);
    }
}
