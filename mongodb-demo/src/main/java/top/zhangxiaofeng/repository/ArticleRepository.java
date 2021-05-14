package top.zhangxiaofeng.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import top.zhangxiaofeng.entity.Article;

import java.util.List;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/13
 */
@Repository("ArticleRepositor")
public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
    /**
     * findArticleByAuthor
     * @param author
     * @return
     */
    public List<Article> findArticleByAuthor(String author);

}
