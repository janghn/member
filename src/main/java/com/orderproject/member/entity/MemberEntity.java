package com.orderproject.member.entity;

import com.orderproject.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
  @Id //pk 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
  private Long id;

  @Column(unique = true) //unique 제약조건 추가
  private String memberEmail;

  @Column
  private String memberPassword;

  @Column
  private String memberName;

  public static MemberEntity toMemberEntity(MemberDto memberDto){
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setMemberEmail(memberDto.getMemberEmail());
    memberEntity.setMemberPassword(memberDto.getMemberPassword());
    memberEntity.setMemberName(memberDto.getMemberName());
    return memberEntity;
  }

  public static MemberEntity toUpdateMemberEntity(MemberDto memberDto){
    MemberEntity memberEntity = new MemberEntity();
    memberEntity.setId((memberDto.getId()));
    memberEntity.setMemberEmail(memberDto.getMemberEmail());
    memberEntity.setMemberPassword(memberDto.getMemberPassword());
    memberEntity.setMemberName(memberDto.getMemberName());
    return memberEntity;
  }


}
