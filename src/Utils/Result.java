/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author Nicholas.Sixbury
 */
public class Result {
    public enum ResultType {
        Ok,
        Err
    }//end enum ResultType
    
    private Exception exception = null;

    private ResultType state = ResultType.Ok;
    
    public boolean isOk() {
        return state == ResultType.Ok;
    }//end isOk()
    
    public boolean isErr() {
        return state == ResultType.Err;
    }//end isErr()

    public Exception getError() {
        return exception;
    }//end getError()

    /**
     * Initializes the Result as not an error.
     */
    public Result() {
        state = ResultType.Ok;
        exception = null;
    }//end Ok constructor

    /**
     * Initializes the Result as an error.
     * @param e The exception that we're calling an error
     */
    public Result(Exception e) {
        state = ResultType.Err;
        exception = e;
    }//end Err constructor
}
