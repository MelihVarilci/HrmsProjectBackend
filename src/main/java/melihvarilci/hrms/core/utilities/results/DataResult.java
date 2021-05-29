package melihvarilci.hrms.core.utilities.results;

public class DataResult<T> extends Result{
    private T data;
    public DataResult(T data, boolean isSuccess, String message) {
        super(isSuccess, message);
        this.data = data;
    }
    public DataResult(T data, boolean isSuccess) {
        super(isSuccess);
        this.data = data;
    }
    public T getData(){
        return this.data;
    }
}
