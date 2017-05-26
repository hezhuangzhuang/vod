package com.zxwl.vod.net.transformer;

import com.zxwl.vod.bean.NewDataList;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 预处理异常信息
 */
public class ListDefaultTransformer<T> implements Observable.Transformer<NewDataList<T>, List<T>> {
    @Override
    public Observable<List<T>> call(Observable<NewDataList<T>> httpResponseObservable) {
        return httpResponseObservable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .compose(ListErrorTransformer.<T>getInstance())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
