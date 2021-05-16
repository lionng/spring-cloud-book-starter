package top.zhangxiaofeng.elasticsearch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import top.zhangxiaofeng.entity.Article;

import java.util.List;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/16
 */
@SpringBootTest
public class ElasticSearchTemplateTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void queryByTitle() {
        String param = "实";
        SearchHits<Article> title = elasticsearchRestTemplate.search(new CriteriaQuery(Criteria.where("title").contains(param)), Article.class);
        System.out.println("title = " + title);
        List<SearchHit<Article>> searchHits = title.getSearchHits();
        for (SearchHit<Article> searchHit : searchHits) {
            System.out.println(searchHit.getContent());
        }
    }
}
