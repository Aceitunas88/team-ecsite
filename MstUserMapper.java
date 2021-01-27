package jp.co.internous.pancake.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.pancake.model.domain.MstUser;

@Mapper
public interface MstUserMapper {
	
	@Insert("INSERT INTO mst_user ("
			+ "user_name, password, "
			+ "family_name, first_name, family_name_kana, first_name_kana, "
			+ "gender"
			+ ") "
			+ "VALUES ("
			+ "#{userName}, #{password}, "
			+ "#{familyName}, #{firstName}, #{familyNameKana}, #{firstNameKana}, "
			+ "#{gender}"
			+ ")")
	
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(MstUser user);
	
	// MyPageContoroller AuthController DestinationController　ユーザー情報取得
	@Select("SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword(
			@Param("userName") String userName, 
			@Param("password") String password
			);
	
	// UserContoroller　重複確認
	@Select("SELECT count(id) FROM mst_user WHERE user_name = #{userName}")
	int findCountByUserName(
			@Param("userName") String userName
			);
	
	// パスワードの再設定
	@Update("UPDATE mst_user SET password = #{password}, updated_at = now() WHERE user_name = #{userName}")
	int updatePassword(
			@Param("userName") String userName,
			@Param("password") String password
			);

}
