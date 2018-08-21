package influencer.com.influencer.activities.callback;

import influencer.com.influencer.activities.apiResponses.registerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.LoginAPI;
import retrofit2.Response;

/**
 * Created by android on 21/8/18.
 */

public interface ILoginCallback {

    void getLoginResponseSuccess(Response<LoginAPI> loginAPIResponse);
    void getRegisterFailure(Throwable loginAPIResponse);



}
