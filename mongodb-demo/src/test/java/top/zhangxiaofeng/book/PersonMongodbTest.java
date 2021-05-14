package top.zhangxiaofeng.book;

import com.mongodb.client.ListIndexesIterable;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import top.zhangxiaofeng.entity.Person;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/10
 */
@SpringBootTest
public class PersonMongodbTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void getCollection() {
        Person person = new Person();
        person.setName("zhang");
        person.setAge(20);
        person.setCity("beijing");
        person.setRegion("changping");
        mongoTemplate.save(person);


        ListIndexesIterable<Document> list = mongoTemplate.getCollection("person_info").listIndexes();
        for (Document document : list) {
            System.out.println(document.toJson());
        }
    }
}
