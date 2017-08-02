package com.txw.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.txw.ssm.bean.Employee;
import com.txw.ssm.bean.Msg;
import com.txw.ssm.service.EmployeeService;

/**
 * ����Ա��CRUD����
 * @author KidInWind
 *
 */

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@ResponseBody
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("id") Integer id){
		employeeService.deleteEmp(id);
		return Msg.success();
	}
	
	/**
	 * Ա�����·���
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}", method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee){
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * ����Ա��id��ȡԱ����Ϣ
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public Msg getEmp(@PathVariable("id")Integer id){
		System.out.println("eeee");
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/**
	 * У���û����Ƿ����
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName){
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)){
			return Msg.fail().add("va_msg", "�û���ֻ����2~5λ���Ļ���6~16Ӣ�ĺ����ֵ����");
		}
		boolean b = employeeService.checkUser(empName);
		if(b)
			return Msg.success();
		else
			return Msg.fail().add("va_msg", "�û���������");
	}
	
	/**
	 * Ա������
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult result){
		if(result.hasErrors()){
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("�����ֶΣ�" + fieldError.getField());
				System.out.println("������Ϣ��" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	/**
	 * ���� jackson��
	 * @param pn
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn", defaultValue="1") Integer pn){
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo page = new PageInfo(emps, 5);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * ��ѯԱ������(��ҳ��ѯ)
	 * @return
	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value="pn", defaultValue="1") Integer pn,
//			Model model){
//		PageHelper.startPage(pn, 5);
//		List<Employee> emps = employeeService.getAll();
//		PageInfo page = new PageInfo(emps, 5);
//		model.addAttribute("pageInfo", page);
//		return "list";
//	}
}
