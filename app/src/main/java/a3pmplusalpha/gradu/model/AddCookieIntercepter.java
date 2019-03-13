package a3pmplusalpha.gradu.model;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import a3pmplusalpha.gradu.model.repository.Local.preference.GraduPreference;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookieIntercepter implements Interceptor {
    Context context;

    public AddCookieIntercepter(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        Set<String> prefs = GraduPreference.getSharedPreferences(context)
                .getPref(GraduPreference.PREF_NAME_COOKIE, new HashSet<String>());

        for (String cookie: prefs) {
            Log.d("ACI", "Set Cookies" + cookie);
            builder.addHeader("Cookie", cookie);
        }

        return chain.proceed(builder.build());
    }
}
