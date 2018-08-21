package influencer.com.influencer.activities.apiResponses.registerAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPwdAPI {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("Message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}