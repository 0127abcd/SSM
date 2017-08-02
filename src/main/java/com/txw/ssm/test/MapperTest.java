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
 * 测试 dao层
 * @author KidInWind
 * 推荐 Spring 的项目使用 Spring 的单元测试，可以自动注入组件
 * 1、导入 SpringTest模块
 * 2、@ContextConfiguration指定Spring配置文件的位置
 * 3、直接 autowired
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
	 * 测试 DepartmentMapper
	 */
	@Test
	public void testCRUD(){
		System.out.println(departmentMapper);
//		departmentMapper.insertSelective(new Department(null, "开发部"));
//		departmentMapper.insertSelective(new Department(null, "测试版"));
		
//		employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@qq.com", 1));
		
//		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//		for(int i = 0; i < 1000; i++){
//			String uid = UUID.randomUUID().toString().substring(0,5) + i;
//			mapper.insertSelective(new Employee(null, uid, "M", uid + "@qq.com", 1));
//		}
//		System.out.println("批量完成");
	}
	
}
