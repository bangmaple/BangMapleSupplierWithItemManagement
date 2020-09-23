/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.dtos;

import java.io.Serializable;

/**
 *
 * @author bangmaple
 */
public class UsersDTO implements Serializable {
    private String username;
    private String fullname;
    private String password;
    private boolean activated;

    public UsersDTO() {
    }

    public UsersDTO(String username, String fullname, String password, boolean activated) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.activated = activated;
    }
    public UsersDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UsersDTO{username=").append(username);
        sb.append(", fullname=").append(fullname);
        sb.append(", password=").append(password);
        sb.append(", activated=").append(activated);
        sb.append('}');
        return sb.toString();
    }




}
