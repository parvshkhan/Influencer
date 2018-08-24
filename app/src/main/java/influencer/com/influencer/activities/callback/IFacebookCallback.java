package influencer.com.influencer.activities.callback;

import android.media.FaceDetector;

import influencer.com.influencer.activities.apiResponses.registerAPI.FacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.ForgetPwdAPI;
import retrofit2.Response;

/**
 * Created by android on 21/8/18.
 */

public interface IFacebookCallback {


    void getFacebookResponseSuccess(Response<FacebookApi> facebookAPIResponse);
    void getFacebookFailure(Throwable facebookAPIResponse);



}
