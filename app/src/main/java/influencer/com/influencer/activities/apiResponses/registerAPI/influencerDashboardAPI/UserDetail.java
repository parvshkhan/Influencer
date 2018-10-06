package influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age_from")
    @Expose
    private String ageFrom;
    @SerializedName("age_to")
    @Expose
    private String ageTo;
    @SerializedName("genderratio")
    @Expose
    private Integer genderratio;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("postalcode")
    @Expose
    private String postalcode;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("bankAccount")
    @Expose
    private String bankAccount;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("Interest")
    @Expose
    private String interest;
    @SerializedName("aboutme")
    @Expose
    private String aboutme;
    @SerializedName("insta_link")
    @Expose
    private String instaLink;
    @SerializedName("fb_link")
    @Expose
    private String fbLink;
    @SerializedName("instaprofilepic")
    @Expose
    private String instaprofilepic;
    @SerializedName("fbprofilepic")
    @Expose
    private String fbprofilepic;
    @SerializedName("fbname")
    @Expose
    private String fbname;
    @SerializedName("instaname")
    @Expose
    private String instaname;
    @SerializedName("approved")
    @Expose
    private Boolean approved;
    @SerializedName("rejected")
    @Expose
    private Boolean rejected;
    @SerializedName("molliecustid")
    @Expose
    private String molliecustid;
    @SerializedName("createdon")
    @Expose
    private String createdon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(String ageFrom) {
        this.ageFrom = ageFrom;
    }

    public String getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }

    public Integer getGenderratio() {
        return genderratio;
    }

    public void setGenderratio(Integer genderratio) {
        this.genderratio = genderratio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getInstaLink() {
        return instaLink;
    }

    public void setInstaLink(String instaLink) {
        this.instaLink = instaLink;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public String getInstaprofilepic() {
        return instaprofilepic;
    }

    public void setInstaprofilepic(String instaprofilepic) {
        this.instaprofilepic = instaprofilepic;
    }

    public String getFbprofilepic() {
        return fbprofilepic;
    }

    public void setFbprofilepic(String fbprofilepic) {
        this.fbprofilepic = fbprofilepic;
    }

    public String getFbname() {
        return fbname;
    }

    public void setFbname(String fbname) {
        this.fbname = fbname;
    }

    public String getInstaname() {
        return instaname;
    }

    public void setInstaname(String instaname) {
        this.instaname = instaname;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    public String getMolliecustid() {
        return molliecustid;
    }

    public void setMolliecustid(String molliecustid) {
        this.molliecustid = molliecustid;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

}

