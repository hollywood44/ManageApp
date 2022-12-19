package com.app.manage.entity.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Auth {
    Admin("ROLE_Admin"),
    Member("ROLE_Member"),
    ProductManager("ROLE_ProductManager"),
    MemberManager("ROLE_MemberManager");

    private String value;
}
