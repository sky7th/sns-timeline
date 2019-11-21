package com.sky7th.followcomponent.core.domain.follow;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sky7th.followcomponent.core.domain.base.BaseService;
import com.sky7th.followcomponent.core.jooq.domain.Tables;
import com.sky7th.followcomponent.core.jooq.domain.tables.Follows;

@Service
public class FollowService extends BaseService {

	private Follows follows = Tables.FOLLOWS;

	public List<FollowerResponse> getFollowerList(String userId) {
		return jooq.select(
			follows.ID,
			follows.FOLLOWER_ID)
			.from(follows)
			.where(follows.FOLLOWED_ID.eq(userId))
			.fetchInto(FollowerResponse.class);
	}

	public void saveFollow(String fromUserId, String toUserId) {
		jooq.insertInto(follows, follows.FOLLOWER_ID, follows.FOLLOWED_ID)
			.values(fromUserId, toUserId)
			.execute();
	}

}
