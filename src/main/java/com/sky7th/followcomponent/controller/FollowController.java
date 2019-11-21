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

	private static final int REQUEST_LIMIT = 2;

	@Autowired
	private FollowService followService;

	/**
	 * 내가 팔로잉 한 유저 리스트 가져오기
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}/following")
	@ResponseBody
	public Map<String, Object> sendFollowingList(
		@PathVariable(name = "userId") String userId,
		@RequestParam(name = "start") int start) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			List<FollowingResponse> followingList = followService.getFollowingList(userId, start, REQUEST_LIMIT);
			if (followingList.size() != REQUEST_LIMIT) {
				result.put("isLast", true);
			} else {
				result.put("isLast", false);
			}
			if (followingList.size() == 0) {
				return getSuccessResult(result, followingList);
			}
			Integer lastFollowingId = followingList.get(followingList.size() - 1).getId();
			result = getSuccessResult(result, lastFollowingId);

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
	public Map<String, Object> sendFollowerList(
		@PathVariable(name = "userId") String userId,
		@RequestParam(name = "start") int start) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			List<FollowerResponse> followerList = followService.getFollowerList(userId, start, REQUEST_LIMIT);
			if (followerList.size() != REQUEST_LIMIT) {
				result.put("isLast", true);
			} else {
				result.put("isLast", false);
			}
			if (followerList.size() == 0) {
				return getSuccessResult(result, followerList);
			}
			Integer lastFollowingId = followerList.get(followerList.size() - 1).getId();
			result = getSuccessResult(result, lastFollowingId);

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
	public Map<String, Object> saveFollow(
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
	public Map<String, Object> deleteFollow(
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

}
