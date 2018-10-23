package influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.activeCampaign;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveCampaign {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ActiveCampaign")
    @Expose
    private List<ActiveCampaign_> activeCampaign = null;

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

    public List<ActiveCampaign_> getActiveCampaign() {
        return activeCampaign;
    }

    public void setActiveCampaign(List<ActiveCampaign_> activeCampaign) {
        this.activeCampaign = activeCampaign;
    }

}
