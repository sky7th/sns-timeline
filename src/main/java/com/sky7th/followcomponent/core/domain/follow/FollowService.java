package com.sky7th.followcomponent.core.domain.follow;

import java.util.List;

import org.jooq.types.UInteger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky7th.followcomponent.core.domain.base.BaseService;
import com.sky7th.followcomponent.core.jooq.domain.Tables;
import com.sky7th.followcomponent.core.jooq.domain.tables.Followed;
import com.sky7th.followcomponent.core.jooq.domain.tables.Following;

@Service
public class FollowService extends BaseService {

	private Following following = Tables.FOLLOWING;
	private Followed followed = Tables.FOLLOWED;

	public List<FollowerResponse> getFollowerList(String userId, Integer start, int requestLimit) {
		return jooq.select(
			followed.ID,
			followed.FOLLOWER_USER_ID)
			.from(followed)
			.where(followed.FOLLOWED_USER_ID.eq(userId)
				.and(followed.ID.gt(UInteger.valueOf(start))))
			.limit(requestLimit)
			.fetchInto(FollowerResponse.class);
	}

	public List<FollowingResponse> getFollowingList(String userId, Integer start, int requestLimit) {
		return jooq.select(
			following.ID,
			following.FOLLOWED_USER_ID)
			.from(following)
			.where(following.FOLLOWER_USER_ID.eq(userId)
				.and(following.ID.gt(UInteger.valueOf(start))))
			.limit(requestLimit)
			.fetchInto(FollowingResponse.class);
	}

	@Transactional
	public void saveFollow(String fromUserId, String toUserId) {
		jooq.insertInto(following, following.FOLLOWER_USER_ID, following.FOLLOWED_USER_ID)
			.values(fromUserId, toUserId)
			.execute();

		jooq.insertInto(followed, followed.FOLLOWED_USER_ID, followed.FOLLOWER_USER_ID)
			.values(toUserId, fromUserId)
			.execute();
	}

	@Transactional
	public void deleteFollow(String fromUserId, String toUserId) {
		jooq.delete(following)
			.where(following.FOLLOWER_USER_ID.eq(fromUserId)
				.and(following.FOLLOWED_USER_ID.eq(toUserId)))
			.execute();

		jooq.delete(followed)
			.where(followed.FOLLOWED_USER_ID.eq(toUserId)
				.and(followed.FOLLOWER_USER_ID.eq(fromUserId)))
			.execute();
	}

}
