package com.template.springproject.util.exception;

import io.reactivex.rxjava3.core.*;

import java.util.List;

public class ReactiveApiException {

    public static <T extends Throwable> Single<T> SingleErrorHandler(T ex){
        return Single.error(ErrorHandler(ex));
    }

    public static <T extends Throwable> Observable<T> ObservableErrorHandler(T ex){
        return Observable.error(ErrorHandler(ex));
    }

    public static <T extends Throwable> Completable CompletableErrorHandler(T ex){
        return Completable.error(ErrorHandler(ex));
    }

    public static <T extends Throwable> ApiException ErrorHandler(T ex){
        for (ExceptionTypeHandler type : ExceptionTypeHandler.values()) {
            if (type.getExceptionClass().isInstance(ex)) {
                return ExceptionTypeHandler.buildApiException(type, ex);
            }
        }
        return ExceptionTypeHandler.buildApiException(ex);
    }
}
