package top.zhangxiaofeng.book;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import top.zhangxiaofeng.entity.generatedvalue.SaveMongoEventListener;
import top.zhangxiaofeng.entity.generatedvalue.Student;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/13
 */
@SpringBootTest
public class StudentMongodbTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        Student student = new Student();
        student.setName("zhang");
        mongoTemplate.save(student);
        logger.info("StudentMongodbTest test student:{}", student);

        Student student1 = new Student();
        student1.setName("zhang1");
        mongoTemplate.save(student1);
        logger.info("StudentMongodbTest test student1:{}", student1);
    }
}
