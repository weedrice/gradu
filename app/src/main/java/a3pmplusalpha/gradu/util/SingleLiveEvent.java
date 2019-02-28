package a3pmplusalpha.gradu.util;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class SingleLiveEvent extends MutableLiveData {
    private AtomicBoolean mPending = new AtomicBoolean(false);
    private static final String TAG = "SingleLiveEvent";

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer observer) {
        if(hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, new Observer() {
            @Override
            public void onChanged(Object o) {
                if(mPending.compareAndSet(true, false)) {
                    observer.onChanged(o);
                }
            }
        });
    }

    @MainThread
    @Override
    public void setValue(Object value) {
        mPending.set(true);
        super.setValue(value);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
