package com.sky7th.followcomponent.core.domain.follow;

import java.util.List;

import org.jooq.types.UInteger;
import org.springframework.stereotype.Service;

import com.sky7th.followcomponent.core.domain.base.BaseService;
import com.sky7th.followcomponent.core.jooq.domain.Tables;
import com.sky7th.followcomponent.core.jooq.domain.tables.Follows;

@Service
public class FollowService extends BaseService {

	private Follows follows = Tables.FOLLOWS;

	public List<FollowerResponse> getFollowerList(String userId, Integer start, Integer end) {
		return jooq.select(
			follows.ID,
			follows.FOLLOWER_ID)
			.from(follows)
			.where(follows.FOLLOWED_ID.eq(userId)
				.and(follows.ID.between(UInteger.valueOf(start), UInteger.valueOf(end))))
			.fetchInto(FollowerResponse.class);
	}

	public List<FollowingResponse> getFollowingList(String userId, Integer start, Integer end) {
		return jooq.select(
			follows.ID,
			follows.FOLLOWED_ID)
			.from(follows)
			.where(follows.FOLLOWER_ID.eq(userId)
				.and(follows.ID.between(UInteger.valueOf(start), UInteger.valueOf(end))))
			.fetchInto(FollowingResponse.class);
	}

	public void saveFollow(String fromUserId, String toUserId) {
		jooq.insertInto(follows, follows.FOLLOWER_ID, follows.FOLLOWED_ID)
			.values(fromUserId, toUserId)
			.execute();
	}

	public void deleteFollow(String fromUserId, String toUserId) {
		jooq.delete(follows)
			.where(follows.FOLLOWER_ID.eq(fromUserId)
				.and(follows.FOLLOWED_ID.eq(toUserId)))
			.execute();
	}

}
