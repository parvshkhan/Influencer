package influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("usertype")
    @Expose
    private String usertype;
    @SerializedName("currentplan")
    @Expose
    private Integer currentplan;
    @SerializedName("nextplan")
    @Expose
    private Integer nextplan;
    @SerializedName("validity")
    @Expose
    private String validity;
    @SerializedName("createdon")
    @Expose
    private String createdon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Integer getCurrentplan() {
        return currentplan;
    }

    public void setCurrentplan(Integer currentplan) {
        this.currentplan = currentplan;
    }

    public Integer getNextplan() {
        return nextplan;
    }

    public void setNextplan(Integer nextplan) {
        this.nextplan = nextplan;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

}