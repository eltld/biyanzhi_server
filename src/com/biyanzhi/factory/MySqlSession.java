package com.biyanzhi.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.biyanzhi.bean.PictureImage;
import com.biyanzhi.dao.PictureImageDao;

public class MySqlSession {
	/**
	 * ���MyBatis SqlSessionFactory
	 * SqlSessionFactory���𴴽�SqlSession��һ�������ɹ����Ϳ�����SqlSessionʵ����ִ��ӳ�����
	 * ��commit��rollback��close�ȷ�����
	 * 
	 * @return
	 */
	public static SqlSessionFactory sessionFactory = null;

	static {
		String resource = "configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void main(String[] args) {
		List<PictureImage> lists = new ArrayList<PictureImage>();
		for (int i = 0; i < 5; i++) {
			PictureImage image = new PictureImage();
			image.setImage_url("aa");
			lists.add(image);
		}
		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		PictureImageDao dao = sqlSession.getMapper(PictureImageDao.class);
		int id = dao.insertPictureImage(lists);
		sqlSession.commit();
		System.out.println(id);
	}
}
