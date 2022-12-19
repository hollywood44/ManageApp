package com.app.manage.dto;

import com.app.manage.entity.utility.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String memberId;
    private String password;
    private String name;
    private String phoneNumber;
    private Auth auth;

}
