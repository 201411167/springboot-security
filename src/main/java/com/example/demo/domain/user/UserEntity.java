package com.example.demo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Document
public class UserEntity implements UserDetails{
    @Id
    private String id;
    private String email;
    private String password;
    private String authority;

    @Builder
    public UserEntity(String id, String email, String password, String authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정이 가지고 있는 권한 목록을 리턴한다.
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(authority));
        return auth;
    }

    public String getPassword(){
        // 계정의 비밀번호를 리턴한다.
        return password;
    }

    public String getEmail(){
        // 계정의 이메일을 리턴한다.
        return email;
    }

    public String getAuthority(){
        return authority;
    }

    public void setAuthority(String authority){
        this.authority = authority;
    }

    @Override
    public String getUsername() {
        // 계정의 이름을 리턴한다.
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정의 만료 여부 리턴
        // (true : 만료되지 않음)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정의 잠김 여부 리턴
        // (true : 잠겨있지 않음)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호의 만료 여부 리턴
        // (true : 만료되지 않음)
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
