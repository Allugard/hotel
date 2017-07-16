package ua.allugard.hotel.util.exceptions;

/**
 * Created by allugard on 11.07.17.
 */
public class DuplicateLoginException extends DaoException {

    public DuplicateLoginException(){
        super();
    }

    public DuplicateLoginException(String msg){
        super(msg);
    }

}
