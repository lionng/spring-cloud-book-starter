package top.zhangxiaofeng.entity.generatedvalue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/13
 *
 * 自增序列生成逻辑
 */
@Component
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {

    Logger logger =  LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if (source != null){
            ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    if (field.isAnnotationPresent(GeneratedValue.class)){
                        // 设置自增ID field.set(source,
                        field.set(source, getNextId(source.getClass().getSimpleName()));
                    }
                 }
            });
        }
    }

    /**
     * 获取下一个自增ID
     * @param collName 集合名称
     * @return
     */
    public Long getNextId(String collName){
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SequenceId SeqId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);
        logger.info("SaveMongoEventListener getNextId SeqId.getSeqId:{}",SeqId.getSeqId());
        return SeqId.getSeqId();
    }
}
