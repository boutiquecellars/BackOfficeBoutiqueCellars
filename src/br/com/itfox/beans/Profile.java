/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.beans;

/**
 *
 * @author belchiorpalma
 */
public class Profile {
    private int profileId;
    private String profile;
    private String permission;

    public Profile(int profileId, String profile, String permission) {
        this.profileId = profileId;
        this.profile = profile;
        this.permission=permission;
    }
    public Profile(){}

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Profile{" + "profileId=" + profileId + ", profile=" + profile + ", permission=" + permission + '}';
    }

    
    
    
}
