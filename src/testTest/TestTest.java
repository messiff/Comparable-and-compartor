package testTest;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;


/**
 * 利用两种比较器（Comparable、comparator）来进行比较
 * @author zzf
 *
 */
//定义一个员工类
class Employee implements Comparable{
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
	public MyDate getBirthday() {
		return birthday;
	}
	public void setBirthday(MyDate birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", birthday=" + birthday + "]";
	}
	
	private String name;
	private Integer age;
	private MyDate birthday;
	public Employee(String name, int age, MyDate birthday) {
		super();
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}
	@Override
	public int compareTo(Object o) {
		if (o instanceof Employee) {
			return this.name.compareToIgnoreCase(((Employee)o).name);
		}
		return 0;
	}
	
}
class MyDate{
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "MyDate [month=" + month + ", day=" + day + ", year=" + year + "]";
	}
	
	private Integer month;
	private Integer day;
	private Integer year;
	public MyDate(int month, int day, int year) {
		super();
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
}

public class TestTest {
//	1、方法一：Comparable实现自然排序，根据名字排序
	@Test
	public void test1() {
		TreeSet<Employee> treeSet=new TreeSet<Employee>();
		treeSet.add(new Employee("zhangsan", 31, new MyDate(3, 1, 1990)));
		treeSet.add(new Employee("lisi", 18, new MyDate(5, 1, 2001)));
		treeSet.add(new Employee("wangwu", 23, new MyDate(8,4, 1900)));
		treeSet.add(new Employee("liua", 78, new MyDate(6, 8, 1970)));
		treeSet.add(new Employee("zz", 14, new MyDate(9, 15, 1888)));
		Iterator<Employee> iterator = treeSet.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
//	方法二：comparator自定义比较器来比较 ，根据出生年月日比较
	@Test
	public void test2() {
		TreeSet<Employee> treeSet=new TreeSet<Employee>();
		Comparator<Employee> comparator=new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				int i= o1.getBirthday().getYear().compareTo(o2.getBirthday().getYear());
				if (i==0) {
					int i1=o1.getBirthday().getMonth().compareTo(o2.getBirthday().getMonth());
					if (i1==0) {
						return o1.getBirthday().getDay().compareTo(o2.getBirthday().getDay());
					}
					return i1;
				}	
				return i;
			}
		};
		treeSet.add(new Employee("zhangsan", 31, new MyDate(3, 1, 1990)));
		treeSet.add(new Employee("lisi", 18, new MyDate(5, 1, 2001)));
		treeSet.add(new Employee("wangwu", 23, new MyDate(8,4, 1900)));
		treeSet.add(new Employee("liua", 78, new MyDate(6, 8, 1970)));
		treeSet.add(new Employee("zz", 14, new MyDate(9, 15, 1888)));
		Iterator<Employee> iterator = treeSet.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
		
	
}
