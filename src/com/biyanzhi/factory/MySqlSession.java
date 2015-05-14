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
		List<PictureImage> imageLists = new ArrayList<PictureImage>();
		for (int i = 0; i < 5; i++) {
			PictureImage img = new PictureImage();
			img.setImage_url("http://www.baidu.com");
			img.setPicture_id(2);
			imageLists.add(img);
		}

		SqlSession sqlSession = MySqlSession.getSessionFactory().openSession();
		PictureImageDao dao = sqlSession.getMapper(PictureImageDao.class);
		int id = dao.insertPictureImage(imageLists);
		System.out.println(id);

	}
}
