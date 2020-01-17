package com.example.demo.domain.user;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public String addUser(UserEntity user){
        // 비밀번호를 암호화하여 회원 가입시키는 절차
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(id).get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        String authority = userEntity.getAuthority();
        if(!authorities.contains(authority)){
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return userEntity.getPassword();
            }

            @Override
            public String getUsername() {
                return userEntity.getId();
            }

            public String getEmail(){
                return userEntity.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    public String deleteUser(String id){
        Optional<UserEntity> user = userRepository.findById(id);
        String userId="";
        if(user.isPresent()) {
            userId = user.get().getId();
            userRepository.delete(user.get());
        }
        return userId;
    }
}
