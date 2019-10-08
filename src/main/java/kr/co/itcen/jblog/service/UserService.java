package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	// 회원가입
	public void join(UserVo vo) {
		userDao.insert(vo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.DefaultBlogSetting(vo.getId());
		
		// 회원가입시 자동으로 블로그 생성
		blogDao.createBlog(blogVo);
		
	}
	
	// 접속한 사용자 id, password 가져오기
	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}
	

	//id중복검사
	public Boolean existUser(String id) {
		return userDao.get(id) != null;
	}

}
