package com.base.BaseProject.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getLoginRes {

    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("TirgEmpCode")
    @Expose
    private String tirgEmpCode;
    @SerializedName("IsActive")
    @Expose
    private String isActive;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("isTrainerPlusUser")
    @Expose
    private String isTrainerPlusUser;
    @SerializedName("UserCourseCompletedStatus")
    @Expose
    private String UserCourseCompletedStatus;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTirgEmpCode() {
        return tirgEmpCode;
    }

    public void setTirgEmpCode(String tirgEmpCode) {
        this.tirgEmpCode = tirgEmpCode;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsTrainerPlusUser() {
        return isTrainerPlusUser;
    }

    public void setIsTrainerPlusUser(String isTrainerPlusUser) {
        this.isTrainerPlusUser = isTrainerPlusUser;
    }

    public String getUserCourseCompletedStatus() {
        return UserCourseCompletedStatus;
    }

    public void setUserCourseCompletedStatus(String userCourseCompletedStatus) {
        UserCourseCompletedStatus = userCourseCompletedStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "userType='" + userType + '\'' +
                ", tirgEmpCode='" + tirgEmpCode + '\'' +
                ", isActive='" + isActive + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isTrainerPlusUser='" + isTrainerPlusUser + '\'' +
                ", UserCourseCompletedStatus='" + UserCourseCompletedStatus + '\'' +
                '}';
    }
}
