package influencer.com.influencer.activities.application;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.steelkiwi.instagramhelper.InstagramHelper;

public class MyApplication extends Application {
    public static final String CLIENT_ID     = "3e37e143dd1448ec9ddf5c0bea087abc";

    public static final String REDIRECT_URL = "https://www.influencernetwork.be/";



    private static InstagramHelper instagramHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext()).build();
        instagramHelper = new InstagramHelper.Builder()

                .withClientId(CLIENT_ID)

                .withRedirectUrl(REDIRECT_URL)

                .withScope("likes+comments")

                .build();

    }



    public static InstagramHelper getInstagramHelper() {

        return instagramHelper;

    }
}
