package influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.previousCampaign;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviousCampaignAPI {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("PreviousCampaign")
    @Expose
    private List<PreviousCampaign> previousCampaign = null;

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

    public List<PreviousCampaign> getPreviousCampaign() {
        return previousCampaign;
    }

    public void setPreviousCampaign(List<PreviousCampaign> previousCampaign) {
        this.previousCampaign = previousCampaign;
    }

}
