package com.txw.ssm.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.txw.ssm.bean.Department;
import com.txw.ssm.bean.Employee;
import com.txw.ssm.dao.DepartmentMapper;
import com.txw.ssm.dao.EmployeeMapper;

/**
 * ���� dao��
 * @author KidInWind
 * �Ƽ� Spring ����Ŀʹ�� Spring �ĵ�Ԫ���ԣ������Զ�ע�����
 * 1������ SpringTestģ��
 * 2��@ContextConfigurationָ��Spring�����ļ���λ��
 * 3��ֱ�� autowired
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * ���� DepartmentMapper
	 */
	@Test
	public void testCRUD(){
		System.out.println(departmentMapper);
//		departmentMapper.insertSelective(new Department(null, "������"));
//		departmentMapper.insertSelective(new Department(null, "���԰�"));
		
//		employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@qq.com", 1));
		
//		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//		for(int i = 0; i < 1000; i++){
//			String uid = UUID.randomUUID().toString().substring(0,5) + i;
//			mapper.insertSelective(new Employee(null, uid, "M", uid + "@qq.com", 1));
//		}
//		System.out.println("�������");
	}
	
}
