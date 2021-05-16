package top.zhangxiaofeng.elasticsearch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhangxiaofeng.entity.Article;
import top.zhangxiaofeng.repository.ArticleRespository;

import java.util.List;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/15
 */
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private ArticleRespository articleRespository;

    @Test
    public void testAdd() {
        Article article = new Article();
        article.setId(1);
        article.setSid("dak219dksd");
        article.setTitle("Java 如何实现重围");
        article.setUrl("https://www.baidu.com/");
        article.setContent("Java 垃圾回收");
        articleRespository.save(article);
    }

    @Test
    public void testQuery() {
        List<Article> list = articleRespository.findByTitleContaining("java");
        for (Article article : list) {
            System.out.println(article.getTitle());
        }
    }
}
