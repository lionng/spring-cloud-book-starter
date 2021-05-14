package top.zhangxiaofeng.entity.generatedvalue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author zhangxiaofeng
 * @Date 2021/5/13
 */
@Document(collection = "sequence")
public class SequenceId {

    @Id
    private String id;

    /**
     * 自增ID值
     */
    @Field("seq_id")
    private long seqId;

    /**
     * 集合名称
     */
    @Field("coll_name")
    private String collName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeqId() {
        return seqId;
    }

    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }
}
