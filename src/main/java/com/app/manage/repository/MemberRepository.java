package com.app.manage.repository;

import com.app.manage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {

    Optional<Member> findByPhoneNumber(String phoneNumber);
}
