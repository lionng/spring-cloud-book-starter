package top.zhangxiaofeng.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import top.zhangxiaofeng.entity.Article;
import top.zhangxiaofeng.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/10
 */
@SpringBootTest
public class ArticleMongodbTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 单条数据添加
     */
    @Test
    public void add() {
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setTitle("MongoTemplate 的基本使用");
            article.setAuthor("yinjihuan");
            article.setUrl("http://cxytiandi.com/blog/detail/" + i);
            article.setTags(Arrays.asList("java", "mongodb", "spring"));
            article.setVisitCount(0L);
            article.setAddTime(new Date());
            mongoTemplate.save(article);
        }
    }

    /**
     * 批量添加
     */
    @Test
    public void add2() {
        ArrayList<Article> articles = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setTitle("MongoTemplate 的基本使用");
            article.setAuthor("yinjihuan");
            article.setUrl("http://cxytiandi.com/blog/detail/" + i);
            article.setTags(Arrays.asList("java", "mongodb", "spring"));
            article.setVisitCount(0L);
            article.setAddTime(new Date());
            articles.add(article);
        }
        mongoTemplate.insert(articles, Article.class);
    }

    /**
     * 操作
     */
    @Test
    public void operate() {
        Article article = new Article();
        article.setTitle("MongoTemplate 的基本使用 firely");
        article.setAuthor("firely");
        article.setUrl("https://lionng.github.io/");
        article.setTags(Arrays.asList("java", "mongodb", "spring"));
        article.setVisitCount(0L);
        article.setAddTime(new Date());
        mongoTemplate.save(article);

        Query query = Query.query(Criteria.where("author").is("firely"));
        Update update = Update.update("title", "MongoTemplate 的基本使用 firely").set("visit_count", 10);
        mongoTemplate.updateFirst(query, update, Article.class);

    }

    /**
     * ArticleRepository
     */
    @Test
    public void testArticleRepository() {
        Iterable<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
            System.out.println(article.getId());
        });
    }

    @Test
    public void testArticleRepositorySearch() {
        Iterable<Article> articles = articleRepository.findArticleByAuthor("firefly");
        articles.forEach(article -> {
            System.out.println(article.getId());
        });
    }
}
