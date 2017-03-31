package com.hector.flickapp;

import com.hector.flickapp.util.EspressoIdlingResource;

/**
 * Runs {@link UseCase}s using a {@link UseCaseScheduler}.
 */
public class UseCaseHandler {
    private static UseCaseHandler INSTANCE;
    private final UseCaseScheduler mUseCaseScheduler;

    /**
     *
     * @param mUseCaseScheduler
     */
    public UseCaseHandler(UseCaseScheduler mUseCaseScheduler) {
        this.mUseCaseScheduler = mUseCaseScheduler;
    }

    /**
     *
     * @param useCaseCallback
     * @param <V>
     */
    private <V extends UseCase.ResponseValue> void notifyError(final UseCase.UseCaseCallback<V>
            useCaseCallback){
        mUseCaseScheduler.onError(useCaseCallback);
    }

    /**
     *
     * @param response
     * @param useCaseCallback
     * @param <V>
     */
    public <V extends UseCase.ResponseValue> void notifyResponse(final V response,
            final UseCase.UseCaseCallback<V> useCaseCallback){
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }
    /**
     *
     * @param useCase
     * @param values
     * @param callback
     * @param <T>
     * @param <R>
     */
    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(
            final UseCase<T, R> useCase, T values, UseCase.UseCaseCallback<R> callback) {
        useCase.setmRequestValues(values);
        useCase.setmUseCaseCallback(new UiCallbackWrapper(callback, this));

        // The network request might be handled in a different thread so make sure
        // Espresso knows that the app is busy until the response is handled.
        EspressoIdlingResource.increment();// App is busy until further notice

        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
                // This callback may be called twice, once for the cache and once for loading
                // the data from the server API, so we check before decrementing, otherwise
                // it throws "The counter has been corrupted.!" IllegalStateException.
                if(!EspressoIdlingResource.getIdlingResource().isIdleNow()){
                    //Set app as idle.
                    EspressoIdlingResource.decrement();
                }
            }
        });
    }

    /**
     *
     * @return
     */
    public static UseCaseHandler getInstance() {
        if(INSTANCE == null){
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }

    /**
     *
     * @param <V>
     */
    private static final class UiCallbackWrapper<V extends UseCase.ResponseValue> implements
            UseCase.UseCaseCallback<V> {
        private final UseCase.UseCaseCallback<V> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        /**
         *
         * @param mCallback
         * @param mUseCaseHandler
         */
        public UiCallbackWrapper(UseCase.UseCaseCallback<V> mCallback,
                UseCaseHandler mUseCaseHandler) {
            this.mCallback = mCallback;
            this.mUseCaseHandler = mUseCaseHandler;
        }

        /**
         *
         * @param response
         */
        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        /**
         *
         */
        @Override
        public void onError() {
            mUseCaseHandler.notifyError(mCallback);
        }
    }
}
