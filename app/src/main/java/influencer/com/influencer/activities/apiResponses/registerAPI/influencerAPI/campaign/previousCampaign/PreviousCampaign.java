package influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.previousCampaign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviousCampaign {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("brandid")
    @Expose
    private String brandid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("reward")
    @Expose
    private Integer reward;
    @SerializedName("freeproduct")
    @Expose
    private Boolean freeproduct;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("maxinfu")
    @Expose
    private String maxinfu;
    @SerializedName("genderratio")
    @Expose
    private Integer genderratio;
    @SerializedName("agefrom")
    @Expose
    private Integer agefrom;
    @SerializedName("ageto")
    @Expose
    private Integer ageto;
    @SerializedName("brandname")
    @Expose
    private String brandname;
    @SerializedName("brandlogo")
    @Expose
    private String brandlogo;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("productdetails")
    @Expose
    private String productdetails;
    @SerializedName("createdon")
    @Expose
    private String createdon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Boolean getFreeproduct() {
        return freeproduct;
    }

    public void setFreeproduct(Boolean freeproduct) {
        this.freeproduct = freeproduct;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMaxinfu() {
        return maxinfu;
    }

    public void setMaxinfu(String maxinfu) {
        this.maxinfu = maxinfu;
    }

    public Integer getGenderratio() {
        return genderratio;
    }

    public void setGenderratio(Integer genderratio) {
        this.genderratio = genderratio;
    }

    public Integer getAgefrom() {
        return agefrom;
    }

    public void setAgefrom(Integer agefrom) {
        this.agefrom = agefrom;
    }

    public Integer getAgeto() {
        return ageto;
    }

    public void setAgeto(Integer ageto) {
        this.ageto = ageto;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getBrandlogo() {
        return brandlogo;
    }

    public void setBrandlogo(String brandlogo) {
        this.brandlogo = brandlogo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(String productdetails) {
        this.productdetails = productdetails;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

}

