package influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.invitedCampaign;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvitedCampaignAPI {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("InvitedCampaign")
    @Expose
    private List<InvitedCampaign> invitedCampaign = null;

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

    public List<InvitedCampaign> getInvitedCampaign() {
        return invitedCampaign;
    }

    public void setInvitedCampaign(List<InvitedCampaign> invitedCampaign) {
        this.invitedCampaign = invitedCampaign;
    }

}