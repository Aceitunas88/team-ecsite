package jp.co.internous.pancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.pancake.model.mapper.MstUserMapper;
import jp.co.internous.pancake.model.session.LoginSession;
import jp.co.internous.pancake.model.domain.MstUser;
import jp.co.internous.pancake.model.form.UserForm;

@Controller
@RequestMapping("/pancake/user")
public class UserController {
	
	// ログインしているユーザー情報(名前、ふりがな、性別、ユーザー名、パスワード)の取得
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("loginSession",loginSession);
		
		return "register_user";
	}
	
	// 重複確認
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestParam String userName) {		
		int count = userMapper.findCountByUserName(userName);
		return count > 0;
	}
	
	// ユーザー登録情報をインサート
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		
		int count = userMapper.insert(user);
		
		return count > 0;
	}
	
}
