package influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashBoardApi {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("CampaignDetails")
    @Expose
    private List<Object> campaignDetails = null;
    @SerializedName("PlanDetail")
    @Expose
    private PlanDetail planDetail;
    @SerializedName("UserDetail")
    @Expose
    private UserDetail userDetail;
    @SerializedName("Leaderboard")
    @Expose
    private List<Leaderboard> leaderboard = null;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Object> getCampaignDetails() {
        return campaignDetails;
    }

    public void setCampaignDetails(List<Object> campaignDetails) {
        this.campaignDetails = campaignDetails;
    }

    public PlanDetail getPlanDetail() {
        return planDetail;
    }

    public void setPlanDetail(PlanDetail planDetail) {
        this.planDetail = planDetail;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Leaderboard> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<Leaderboard> leaderboard) {
        this.leaderboard = leaderboard;
    }

}