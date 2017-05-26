package com.zxwl.vod.net.transformer;

import com.zxwl.vod.net.response.HttpResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 预处理异常信息
 */
public class DefaultTransformer<T> implements Observable.Transformer<HttpResponse<T>, T> {
    @Override
    public Observable<T> call(Observable<HttpResponse<T>> httpResponseObservable) {
        return httpResponseObservable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .compose(ErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
