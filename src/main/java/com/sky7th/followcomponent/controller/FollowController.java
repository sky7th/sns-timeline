package com.sky7th.followcomponent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky7th.followcomponent.core.domain.base.BaseController;
import com.sky7th.followcomponent.core.domain.follow.FollowService;
import com.sky7th.followcomponent.core.domain.follow.FollowerResponse;
import com.sky7th.followcomponent.core.domain.follow.FollowingResponse;

@Controller
public class FollowController extends BaseController {

	private static final int REQUEST_LIMIT = 100;

	@Autowired
	private FollowService followService;

	/**
	 * 내가 팔로잉 한 유저 리스트 가져오기
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}/following")
	@ResponseBody
	public Map<String, Object> getFollowingMap(
		@PathVariable(name = "userId") String userId,
		@RequestParam(name = "start") int start) {
		Map<String, Object> result = new HashMap<>();
		try {
			List<FollowingResponse> followingList = followService.getFollowingList(userId, start, REQUEST_LIMIT);
			Integer lastFollowingId =
				followingList.size() == 0
					? null
					: followingList.get(followingList.size() - 1).getId();
			result = getSuccessResult(result, followingList);
			result = addMapNextStartId(result, lastFollowingId, followingList.size());
			result = addMapIsLast(result, followingList.size());
		} catch (Exception e) {
			result = this.getFailResult(e.getMessage());
		}
		return result;
	}

	/**
	 * 나를 팔로우 한 유저 리스트 가져오기
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}/follower")
	@ResponseBody
	public Map<String, Object> getFollowerMap(
		@PathVariable(name = "userId") String userId,
		@RequestParam(name = "start") int start) {
		Map<String, Object> result = new HashMap<>();
		try {
			List<FollowerResponse> followerList = followService.getFollowerList(userId, start, REQUEST_LIMIT);
			Integer lastFollowedId =
				followerList.size() == 0
					? null
					: followerList.get(followerList.size() - 1).getId();
			result = getSuccessResult(result, followerList);
			result = addMapNextStartId(result, lastFollowedId, followerList.size());
			result = addMapIsLast(result, followerList.size());
		} catch (Exception e) {
			result = this.getFailResult(e.getMessage());
		}
		return result;
	}

	/**
	 * 구독 저장
	 * @param fromUserId
	 * @param toUserId
	 * @return
	 */
	@PostMapping("/follow")
	@ResponseBody
	public Map<String, Object> follow(
		@RequestParam(name = "fromUserId") String fromUserId,
		@RequestParam(name = "toUserId") String toUserId) {
		Map<String, Object> result = new HashMap<>();
		try {
			if (followService.getFollowing(fromUserId, toUserId) != null) {
				throw new Exception("This follow info is already exist.");
			}
			followService.saveFollow(fromUserId, toUserId);
			result = this.getSuccessResult();
		} catch (Exception e) {
			result = this.getFailResult(e.getMessage());
		}
		return result;
	}

	/**
	 * 구독 삭제
	 * @param fromUserId
	 * @param toUserId
	 * @return
	 */
	@DeleteMapping("/follow")
	@ResponseBody
	public Map<String, Object> unFollow(
		@RequestParam(name = "fromUserId") String fromUserId,
		@RequestParam(name = "toUserId") String toUserId) {
		Map<String, Object> result = new HashMap<>();
		try {
			followService.deleteFollow(fromUserId, toUserId);
			result = this.getSuccessResult();
		} catch (Exception e) {
			result = this.getFailResult(e.getMessage());
		}
		return result;
	}

	private Map<String, Object> addMapNextStartId(Map<String, Object> result, Integer lastFollowingId,
		int listSize) {
		if (listSize == 0) {
			result.put("nextStartId", null);
		} else {
			result.put("nextStartId", lastFollowingId + 1);
		}
		return result;
	}

	private Map<String, Object> addMapIsLast(Map<String, Object> result, int listSize) {
		if (listSize == REQUEST_LIMIT) {
			result.put("isLast", false);
		} else {
			result.put("isLast", true);
		}
		return result;
	}

}
