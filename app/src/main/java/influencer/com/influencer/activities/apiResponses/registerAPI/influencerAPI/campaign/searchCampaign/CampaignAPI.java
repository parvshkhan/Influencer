package influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.searchCampaign;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class CampaignAPI {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Campaign")
    @Expose
    private List<Campaign> campaign = null;

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

    public List<Campaign> getCampaign() {
        return campaign;
    }

    public void setCampaign(List<Campaign> campaign) {
        this.campaign = campaign;
    }

}