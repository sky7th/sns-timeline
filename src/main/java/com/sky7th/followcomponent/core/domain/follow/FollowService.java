package com.sky7th.followcomponent.core.domain.follow;

import java.util.List;

import org.jooq.types.UInteger;
import org.springframework.stereotype.Service;

import com.sky7th.followcomponent.core.domain.base.BaseService;
import com.sky7th.followcomponent.core.jooq.domain.Tables;
import com.sky7th.followcomponent.core.jooq.domain.tables.Following;

@Service
public class FollowService extends BaseService {

	private Following following = Tables.FOLLOWING;

	public List<FollowerResponse> getFollowerList(String userId, Integer start, int followerLimit) {
		return jooq.select(
			following.ID,
			following.FOLLOWER_USER_ID)
			.from(following)
			.where(following.FOLLOWED_USER_ID.eq(userId)
				.and(following.ID.gt(UInteger.valueOf(start))))
			.limit(followerLimit)
			.fetchInto(FollowerResponse.class);
	}

	public List<FollowingResponse> getFollowingList(String userId, Integer start, Integer end) {
		return jooq.select(
			following.ID,
			following.FOLLOWED_USER_ID)
			.from(following)
			.where(following.FOLLOWER_USER_ID.eq(userId)
				.and(following.ID.between(UInteger.valueOf(start), UInteger.valueOf(end))))
			.fetchInto(FollowingResponse.class);
	}

	public void saveFollow(String fromUserId, String toUserId) {
		jooq.insertInto(following, following.FOLLOWER_USER_ID, following.FOLLOWED_USER_ID)
			.values(fromUserId, toUserId)
			.execute();
	}

	public void deleteFollow(String fromUserId, String toUserId) {
		jooq.delete(following)
			.where(following.FOLLOWER_USER_ID.eq(fromUserId)
				.and(following.FOLLOWED_USER_ID.eq(toUserId)))
			.execute();
	}

}
