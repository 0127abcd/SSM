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
 * 处理员工CRUD请求
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
	 * 员工更新方法
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
	 * 根据员工id获取员工信息
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
	 * 校验用户名是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName){
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)){
			return Msg.fail().add("va_msg", "用户名只能是2~5位中文或者6~16英文和数字的组合");
		}
		boolean b = employeeService.checkUser(empName);
		if(b)
			return Msg.success();
		else
			return Msg.fail().add("va_msg", "用户名不可用");
	}
	
	/**
	 * 员工保存
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee, BindingResult result){
		if(result.hasErrors()){
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误字段：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	/**
	 * 导入 jackson包
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
	 * 查询员工数据(分页查询)
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
