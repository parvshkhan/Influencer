package influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginAPI {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("InfluencerID")
    @Expose
    private String influencerID;
    @SerializedName("Picture")
    @Expose
    private String picture;
    @SerializedName("Name")
    @Expose
    private String name;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfluencerID() {
        return influencerID;
    }

    public void setInfluencerID(String influencerID) {
        this.influencerID = influencerID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}