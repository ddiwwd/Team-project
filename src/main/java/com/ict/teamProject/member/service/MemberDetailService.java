package com.ict.teamProject.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.teamProject.member.service.impl.MemberMapper;
import com.ict.teamProject.member.service.MemberDto;

@Service
public class MemberDetailService {

	@Autowired
	private MemberMapper mapper;

    public boolean findbyUserPassword(Map map) {
    	String pwd = mapper.findbyUserPassword(map);
    	
    	if(pwd!=null) {
    	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	    boolean passwordMatch = passwordEncoder.matches(map.get("pwd").toString(), pwd);
    	    return passwordMatch;
    	}
        return false;
    }

	public MemberDto findByUsername(String id) {
	    System.out.println("ID: " + id); // id 출력
	    MemberDto userEntity = mapper.findByUsername(id);
	    if (userEntity != null) {
	        System.out.println("User found: " + userEntity.getId()); 
	    } else {
	        System.out.println("User not found");
	    }
	    return userEntity;
	}
    
    
	public Map<String, Object> getInfo(String id) {
		return mapper.getInfo(id);
	}

	public boolean isPreviousUser(String id) {
		int ismember = mapper.isPreviousUser(id);
		return ismember > 0;
	}

	public void joinSocialMember(MemberDto dto) {
		BCryptPasswordEncoder bcy = new BCryptPasswordEncoder();
		String pwd = bcy.encode("소셜로그인");
		dto.setPwd(pwd);
		mapper.joinSocialMember(dto);
	}

	public MemberDto findByMemberInfo(String id) {
		return mapper.findByMemberInfo(id);
	}
}
