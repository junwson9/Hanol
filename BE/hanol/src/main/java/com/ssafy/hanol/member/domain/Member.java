package com.ssafy.hanol.member.domain;

import com.ssafy.hanol.common.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_oauth_member",
                        columnNames = {"oauth_provider","oauth_id"}
                )
        }
)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "oauth_provider")
    private OauthProvider oauthProvider;

    @Embedded
    private OauthId oauthId = new OauthId();

    @Column(nullable = false)
    private Role role;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Builder
    public Member(String name, String profileImage, OauthProvider oauthProvider, OauthId oauthId,
                  Role role) {
        this.name = name;
        this.profileImage = profileImage;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.role = role;
    }

    public void updateLastLoginDate() {
        this.lastLoginDate = LocalDateTime.now();
    }
}
