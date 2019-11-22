package com.sky7th.followcomponent;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky7th.followcomponent.core.domain.follow.Follow;
import com.sky7th.followcomponent.core.domain.follow.FollowService;

public class FollowServiceTest extends BaseTest {

	@Autowired
	private FollowService followService;

	@Test
	public void test() {
		try {
			Follow followerList = followService.getFollowing("ggg", "aaa");
			try {
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(followerList);
				System.out.println(jsonInString);
			} catch (JsonProcessingException e) {
				System.out.println("failed to convert object to json");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
