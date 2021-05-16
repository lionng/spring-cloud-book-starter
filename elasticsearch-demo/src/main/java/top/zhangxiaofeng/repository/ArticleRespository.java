package top.zhangxiaofeng.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.zhangxiaofeng.entity.Article;

import java.util.List;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/15
 */
@Repository
public interface ArticleRespository extends CrudRepository<Article,Long> {
    List<Article> findByTitleContaining(String title);
}
