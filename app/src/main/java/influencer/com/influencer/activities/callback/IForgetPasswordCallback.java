package influencer.com.influencer.activities.callback;

import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.ForgetPwdAPI;
import retrofit2.Response;

/**
 * Created by android on 21/8/18.
 */

public interface IForgetPasswordCallback {

    void getRegisterResponseSuccess(Response <ForgetPwdAPI> registerAPIResponse);
    void getRegisterFailure(Throwable registerAPIResponse);

}
