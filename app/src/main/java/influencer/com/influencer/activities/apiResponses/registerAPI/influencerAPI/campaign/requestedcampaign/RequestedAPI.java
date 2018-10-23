package influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.requestedcampaign;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestedAPI {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("RequestedCampaign")
    @Expose
    private List<RequestedCampaign> requestedCampaign = null;

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

    public List<RequestedCampaign> getRequestedCampaign() {
        return requestedCampaign;
    }

    public void setRequestedCampaign(List<RequestedCampaign> requestedCampaign) {
        this.requestedCampaign = requestedCampaign;
    }

}
