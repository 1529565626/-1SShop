package com.ssk.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录用户实体
 */
public class LoginMan implements GrantedAuthority, UserDetails {
    /**
     * 实体id
     */
    private String id;


    /**
     * 密码
     */
    private String password;

    private Object manMsg;

    private String roleType;

    /**
     * 名称
     */
    private String name;

    private List<String> permissions;

    private boolean enabled = false;

    public Object getManMsg() {
        return manMsg;
    }

    public void setManMsg(Object manMsg) {
        this.manMsg = manMsg;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() { // 帐户是否过期
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() { // 帐户是否被冻结
        return true;
    }

    // 帐户密码是否过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    @JsonIgnore
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roleType));
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return null;
    }

    public LoginMan(String name, String password, String roleType, List<String> permissions, String id , Object manMsg) {
        this.name = name;
        this.password = password;
        this.roleType = roleType;
        this.permissions = permissions;
        this.id = id;
        this.manMsg = manMsg;
    }

    public LoginMan() {

    }

    @Override
    public String toString() {
        return "LoginMan{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", manMsg=" + manMsg +
                ", roleType='" + roleType + '\'' +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                ", enabled=" + enabled +
                '}';
    }
}
