package web.service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import web.model.UserWeb;

@Service
public class MemberStore {

	private static List<UserWeb> store = new LinkedList<>();
	

	public List<UserWeb> getMembersList() {
		AtomicInteger serialId = new AtomicInteger(1);
		return store.stream()
			.map(userWeb -> new UserWeb(userWeb.id(), serialId.getAndIncrement() + "", userWeb.username()))
			.toList();
	}
	
	public List<UserWeb> filterMemberListByUser(List<UserWeb> memberList, UserWeb userWeb) {
		return memberList.stream()
				.filter(filterUserWeb -> filterUserWeb.id() != userWeb.id())
				.map(sendUserWeb -> new UserWeb(null, sendUserWeb.serialId(), sendUserWeb.username()))
				.toList();
	}
	
	public UserWeb getMember(String id) {
		return store.get(Integer.valueOf(id) - 1);
	}
	
	public void addMember(UserWeb member) {
		store.add(member);
	}
	
	public void removeMember(UserWeb member) {
		store.remove(member);
	}
}
