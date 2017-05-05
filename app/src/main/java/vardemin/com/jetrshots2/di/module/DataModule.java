package vardemin.com.jetrshots2.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;
import vardemin.com.jetrshots2.data.local.ILocalDataRepository;
import vardemin.com.jetrshots2.data.local.LocalDataRepository;
import vardemin.com.jetrshots2.data.remote.IRemoteDataRepository;
import vardemin.com.jetrshots2.data.remote.RemoteDataRepository;

@Module
public class DataModule {
    private Realm realm;
    public DataModule(Realm realm) {
        this.realm = realm;
    }
    @Provides
    @Singleton
    public ILocalDataRepository provideLocalDataRepository(Context context) {
        return new LocalDataRepository(context, realm);
    }
    @Provides
    @Singleton
    public IRemoteDataRepository provideRemoteDataRepository(Retrofit retrofit) {
        return new RemoteDataRepository(retrofit);
    }
}
