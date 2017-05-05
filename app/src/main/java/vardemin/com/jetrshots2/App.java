package vardemin.com.jetrshots2;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import vardemin.com.jetrshots2.di.component.ApplicationComponent;
import vardemin.com.jetrshots2.di.component.DaggerApplicationComponent;
import vardemin.com.jetrshots2.di.module.ApplicationModule;
import vardemin.com.jetrshots2.di.module.DataModule;
import vardemin.com.jetrshots2.di.module.NetModule;
import vardemin.com.jetrshots2.util.Constant;


public class App extends Application {
    private Realm realm;

    private static ApplicationComponent applicationComponent;

    /**
     * DI Application Component provider
     * @return app component
     */
    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(Constant.BASE_URL, false))
                .dataModule(new DataModule(realm))
                .build();
    }

}
