package com.sky7th.followcomponent.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky7th.followcomponent.core.domain.base.BaseController;
import com.sky7th.followcomponent.core.domain.follow.FollowService;

@Controller
@RequestMapping("/follow")
public class FollowController extends BaseController {

	@Autowired
	private FollowService followService;

	/**
	 * 내가 팔로잉 한 유저 리스트 가져오기
	 * @param userId
	 * @return
	 */
	@GetMapping("/followingList/{userId}")
	@ResponseBody
	public Map<String, Object> sendFollowingList(@PathVariable(name = "userId") String userId) {
		Map<String, Object> result = new HashMap<>();

		return result;
	}

	/**
	 * 나를 팔로우 한 유저 리스트 가져오기
	 * @param userId
	 * @return
	 */
	@GetMapping("/followerList/{userId}")
	@ResponseBody
	public Map<String, Object> sendFollowerList(@PathVariable(name = "userId") String userId) {
		Map<String, Object> result = new HashMap<>();

		return result;
	}

	/**
	 * 구독 저장
	 * @param fromUserId
	 * @param toUserId
	 * @return
	 */
	@PostMapping("/{fromUserId}/{toUserId}")
	@ResponseBody
	public Map<String, Object> saveFollow(
		@PathVariable(name = "fromUserId") String fromUserId,
		@PathVariable(name = "toUserId") String toUserId) {
		Map<String, Object> result = new HashMap<>();

		return result;
	}

	/**
	 * 구독 삭제
	 * @param fromUserId
	 * @param toUserId
	 * @return
	 */
	@DeleteMapping("/{fromUserId}/{toUserId}")
	@ResponseBody
	public Map<String, Object> deleteFollow(
		@PathVariable(name = "fromUserId") String fromUserId,
		@PathVariable(name = "toUserId") String toUserId) {
		Map<String, Object> result = new HashMap<>();

		return result;
	}

}
