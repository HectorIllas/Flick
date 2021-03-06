
package com.hector.flickapp;

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response type
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    public Q getmRequestValues() {
        return mRequestValues;
    }

    public void setmRequestValues(Q mRequestValues) {
        this.mRequestValues = mRequestValues;
    }

    public UseCaseCallback<P> getmUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setmUseCaseCallback(UseCaseCallback<P> mUseCaseCallback) {
        this.mUseCaseCallback = mUseCaseCallback;
    }

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    /**
     * Data passed to a request
     */
    public interface RequestValues{

    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {

    }

    public interface UseCaseCallback<R>{
        void onSuccess(R response);
        void onError();
    }
}
