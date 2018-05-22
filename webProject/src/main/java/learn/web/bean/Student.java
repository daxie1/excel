package learn.web.bean;

import java.io.Serializable;
import java.util.Date;

import common.util.Excel;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	@Excel(name="学号",order=1)
    private String identify;
	@Excel(name="姓名",order=2)
    private String name;
	@Excel(name="年龄",order=3)
    private Integer age;
	@Excel(name="入学日期",order=4)
    private Date startDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}