package cn.edu.shiep.model;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.shiep.dao.ILoginUserDao;
import cn.edu.shiep.entity.LoginUser;
import cn.edu.shiep.james.DigestUtil;
import cn.edu.shiep.utils.ResultType;
import cn.edu.shiep.utils.SystemParam;

@Service("loginService")
public class LoginService {

	@Resource(name="loginUserDaoImpl")
	private ILoginUserDao dao;
	
	public Map<String,Object> validateUser(String name,String pass,Map<String,Object> map){
		
		if(SystemParam.isDebug){
			System.out.println("LoginService:������֤");
		}
		
		//�����ݿ���ȡ���û���½����
		name = name.split("@")[0];
		LoginUser user = dao.getUserByName(name);
		
		//���ܴ����password
		try {
			pass = DigestUtil.digestString(pass, "SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if(SystemParam.isDebug && user!=null){
			System.out.println("LoginService:ȡ���û� "+user.toString());
		}
		
		if(user==null){
			if(SystemParam.isDebug){
				System.out.println("LoginService:δ�ҵ��û�");
			}
			map.put("result", ResultType.FAILURE);
			return map;
		}else if(user.getPwdHash().equals(pass)){
			if(SystemParam.isDebug){
				System.out.println("LoginService:��ȷ��½");
			}
			map.put("islogin", "1");
			map.put("loginname", name.split("@")[0]);
			//map.put("userid", user.getUserid());
			
			map.put("result", ResultType.SUCCESS);
			return map;
		}else{
			if(SystemParam.isDebug){
				System.out.println("LoginService:�������");
			}
			map.put("result", ResultType.FAILURE);
			return map;
		}
		
	}

	public void setDao(ILoginUserDao dao) {
		this.dao = dao;
	}

}
