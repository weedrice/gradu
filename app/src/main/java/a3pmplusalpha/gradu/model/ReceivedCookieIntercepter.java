package a3pmplusalpha.gradu.model;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import a3pmplusalpha.gradu.model.repository.Local.preference.GraduPreference;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookieIntercepter implements Interceptor {
    Context context;

    public ReceivedCookieIntercepter(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if(!response.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for(String header: response.headers("Set-Cookie")) {
                Log.d("RCI", "Add header"+header);
                cookies.add(header);
            }

            GraduPreference.getSharedPreferences(context)
                    .putPref(GraduPreference.PREF_NAME_COOKIE, cookies);
        }

        return response;
    }
}
