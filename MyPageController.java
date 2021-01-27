package jp.co.internous.pancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.pancake.model.mapper.MstUserMapper;
import jp.co.internous.pancake.model.session.LoginSession;
import jp.co.internous.pancake.model.domain.MstUser;

@Controller
@RequestMapping("/pancake/mypage")
public class MyPageController {
	
	// ログインしているユーザー情報(名前、ふりがな、性別、ユーザー名、パスワード)の取得
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	
	// マイページに遷移するメソッド
	@RequestMapping("/")
	public String index(Model m) {
		
		MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		
		m.addAttribute("user", user);
		m.addAttribute("loginSession", loginSession);
		
		
		return "my_page";
	}

}
