package kr.co.itcen.jblog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JSONResult addCategory(@RequestBody CategoryVo categoryVo, HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		categoryVo.setBlog_id(userVo.getId());
		Boolean exist = categoryService.add(categoryVo);
		
		return JSONResult.success(exist);
	}
}
