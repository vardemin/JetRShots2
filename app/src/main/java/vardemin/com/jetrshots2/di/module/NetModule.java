package vardemin.com.jetrshots2.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vardemin.com.jetrshots2.util.NetworkUtil;

/**
* NetModule provides:
* HttpCache
* Gson
* OkhttpClient
* Retrofit
*/
@Module
public class NetModule {
    @NonNull
    private String BASE_URL;

    private boolean useCache;

    /**
     * Init Net Module
     * @param base_url base REST url
     * @param useCache flag for cache
     */
    public NetModule(@NonNull String base_url, boolean useCache) {
        BASE_URL = base_url;
        this.useCache = useCache;
    }

    /**
     * Provide cache
     * @param context context for getCacheDir
     * @return Cache
     */
    @Provides
    @Singleton
    Cache provideHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    /**
     * Provide GSON
     * @return Gson obj
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
    }

    /**
     * Provide OkHttpClient with interceptors
     * @param cache cache obj
     * @param context context
     * @return ok http client
     */
    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, Context context) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if(useCache) {
            client.cache(cache);
            client.addNetworkInterceptor(chain -> {
                Response originalResponse = chain.proceed(chain.request());
                String cacheControl = originalResponse.header("Cache-Control");

                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + 5000)
                            .build();
                } else {
                    return originalResponse;
                }
            });
            client.addInterceptor(chain -> {
                Request request = chain.request();

                if (!NetworkUtil.isNetworkConnected(context)) {

                    //int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached")
                            .build();
                }

                return chain.proceed(request);
            });
        }
        return client.build();
    }

    /**
     * Provide inited retrofit object
     * @param gson gson obj
     * @param okHttpClient okhttp client
     * @return retrofit instance
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

}