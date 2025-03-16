/**
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.toint.jdy4j.core.model;

import cn.toint.jdy4j.core.annotation.JdyTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.annotation.AnnotationUtil;

import java.io.Serializable;

/**
 * 简道云表单基类, 自定义表单请继承本类
 *
 * @author Toint
 * @date 2024/10/20
 */
@Data
public class BaseJdyTable implements Serializable {
    /**
     * 应用Id
     */
    @JsonProperty("appId")
    private String appId;

    /**
     * 表单Id
     */
    @JsonProperty("entryId")
    private String entryId;

    /**
     * 数据ID
     */
    @JsonProperty("_id")
    private String dataId;

    /**
     * 扩展字段
     */
    @JsonProperty("ext")
    private String ext;

    /**
     * 提交时间
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * 修改时间
     */
    @JsonProperty("updateTime")
    private String updateTime;

    /**
     * 提交人
     */
    @JsonProperty("creator")
    private JdyUser creator;

    /**
     * 修改人
     */
    @JsonProperty("updater")
    private JdyUser updater;

    /**
     * 删除人
     */
    @JsonProperty("deleter")
    private JdyUser deleter;

    /**
     * 流程状态
     * 该字段仅流程表单支持
     * 2:流程手动结束
     * 1:表示流程流转完成
     * 0:表示流程进行中
     */
    @JsonProperty("flowState")
    private Integer flowState;

    public BaseJdyTable() {
        this.init();
    }

    public BaseJdyTable(final String appId, final String entryId) {
        if (StringUtils.isAnyEmpty(appId, entryId)) {
            this.init();
            return;
        }

        this.appId = appId;
        this.entryId = entryId;
    }

    private void init() {
        final JdyTable jdyTable = AnnotationUtil.getAnnotation(this.getClass(), JdyTable.class);
        if (jdyTable != null) {
            this.appId = jdyTable.appId();
            this.entryId = jdyTable.entryId();
        }
    }
}
