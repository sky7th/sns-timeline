package com.sky7th.followcomponent.core.domain.follow;

import lombok.Data;

@Data
public class Follow {

	private Integer id;

	private String followerUserId;

	private String followedUserId;

}
