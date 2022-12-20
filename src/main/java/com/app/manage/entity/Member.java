package com.app.manage.entity;

import com.app.manage.dto.MemberDto;
import com.app.manage.entity.utility.Auth;
import com.app.manage.entity.utility.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member_tbl")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends TimeEntity implements UserDetails {

    @Id
    private String memberId;

    private String password;

    private String name;

    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Auth auth;


    public void modifyInfo(MemberDto modify) {
        this.name = modify.getName();
        this.phoneNumber = modify.getPhoneNumber();
        if (!modify.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(modify.getPassword());
        }
    }

    // 계정의 권한 목록 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(auth.getValue()));
        return roles;
    }

    // 아이디 반환
    @Override
    public String getUsername() {
        return memberId;
    }

    // 비밀번호 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 계정의 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정의 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정의 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
