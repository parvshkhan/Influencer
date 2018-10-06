package influencer.com.influencer.activities.callback;

import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.RegisterAPI;
import retrofit2.Response;



public interface IRegisterCallback {
    void getRegisterResponseSuccess(Response<RegisterAPI>  registerAPIResponse);
    void getRegisterFailure(Throwable  registerAPIResponse);


}




