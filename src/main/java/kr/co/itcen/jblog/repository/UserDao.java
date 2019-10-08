package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
		
//	//회원정보 수정에 가져와야될 get
//	public UserVo get(long no) {
//		return sqlSession.selectOne("user.getByNo", no);
//	}

	//로그인 하기위해 필요한 정보를 가져오는 get
	public UserVo get(UserVo vo) {
		UserVo result = sqlSession.selectOne("user.getByIdAndPassword1", vo);
		return result;
	}
	public UserVo get(String id) {
		UserVo result = sqlSession.selectOne("user.getById", id);
		return result;
	}
	public UserVo get(String id, String password) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("password",password);

		UserVo result = sqlSession.selectOne("user.getByIdAndPassword2", map);
		return result;		
	}

	//회원가입을 하기위한 insert
	public Boolean insert(UserVo vo) throws UserDaoException { 
		int count =sqlSession.insert("user.insert",vo);
		return count == 1;		
	}

	//회원정보 수정 update
	public Boolean update(UserVo vo) {
		int count = sqlSession.update("user.update",vo);
		return count == 1;
	}
	


}
