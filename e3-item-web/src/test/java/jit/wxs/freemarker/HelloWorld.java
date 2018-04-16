package jit.wxs.freemarker;

/**
 * @author jitwxs
 * @date 2018/4/16 9:41
 */
public class HelloWorld {
//    @Test
//    public void firstDemo() throws Exception {
//        //1、创建Configuration对象，指定编码集和模板文件夹
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        configuration.setDefaultEncoding("utf-8");
//        configuration.setDirectoryForTemplateLoading(new File("D:/ftl"));
//        //2、创建Template对象，指定模板文件
//        Template template = configuration.getTemplate("test.ftl");
//        //3、准备数据，Map或POJO类型，推荐Map
//        Map<String,Object> data = new HashMap<>();
//        data.put("test", "第一个Freemarker例子");
//        //4、创建Writer对象，指定输出文件
//        Writer writer = new FileWriter("D:/ftl_out/test.txt");
//        //5、生成模板
//        template.process(data,writer);
//    }

//    @Test
//    public void listDemo() throws Exception {
//        //1、创建Configuration对象，指定编码集和模板文件夹
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        configuration.setDefaultEncoding("utf-8");
//        configuration.setDirectoryForTemplateLoading(new File("D:/ftl"));
//        //2、创建Template对象，指定模板文件
//        Template template = configuration.getTemplate("student_list.ftl");
//        //3、准备数据
//        List<Student> list = new ArrayList<>();
//        list.add(new Student("jitwxs", 20));
//        list.add(new Student("zhangsan", 25));
//        list.add(new Student("lisi", 12));
//        Map<String,Object> data = new HashMap<>();
//        data.put("studentList", list);
//        //4、创建Writer对象，指定输出文件
//        Writer writer = new FileWriter("D:/ftl_out/student_list.txt");
//        //5、生成模板
//        template.process(data,writer);
//    }

//    @Test
//    public void dateDemo() throws Exception {
//        //1、创建Configuration对象，指定编码集和模板文件夹
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        configuration.setDefaultEncoding("utf-8");
//        configuration.setDirectoryForTemplateLoading(new File("D:/ftl"));
//        //2、创建Template对象，指定模板文件
//        Template template = configuration.getTemplate("date.ftl");
//        //3、准备数据
//        Map<String,Object> data = new HashMap<>();
//        data.put("date", new Date());
//        //4、创建Writer对象，指定输出文件
//        Writer writer = new FileWriter("D:/ftl_out/date.txt");
//        //5、生成模板
//        template.process(data,writer);
//    }

//    @Test
//    public void nullDemo() throws Exception {
//        //1、创建Configuration对象，指定编码集和模板文件夹
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        configuration.setDefaultEncoding("utf-8");
//        configuration.setDirectoryForTemplateLoading(new File("D:/ftl"));
//        //2、创建Template对象，指定模板文件
//        Template template = configuration.getTemplate("null.ftl");
//        //3、准备数据
//        Map<String,Object> data = new HashMap<>();
//        data.put("value",null);
//        //4、创建Writer对象，指定输出文件
//        Writer writer = new FileWriter("D:/ftl_out/null.txt");
//        //5、生成模板
//        template.process(data,writer);
//    }
}

//class Student {
//    private String name;
//    private Integer age;
//
//    public Student(String name, Integer age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//}