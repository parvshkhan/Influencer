package influencer.com.influencer.activities.callback;

import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.FacebookApi;
import retrofit2.Response;

/**
 * Created by android on 21/8/18.
 */

public interface IFacebookCallback {


    void getFacebookResponseSuccess(Response<FacebookApi> facebookAPIResponse);
    void getFacebookFailure(Throwable facebookAPIResponse);



}
