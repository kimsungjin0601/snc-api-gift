package com.snc.gift.common.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class CustomLombokPlugin extends PluginAdapter  {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");

        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        return true;
    }

//    @Override
//    public boolean modelFieldGenerated(Field field,
//                                       TopLevelClass topLevelClass,
//                                       org.mybatis.generator.api.IntrospectedColumn introspectedColumn,
//                                       org.mybatis.generator.api.IntrospectedTable introspectedTable,
//                                       ModelClassType modelClassType) {
//        String comment = introspectedColumn.getRemarks();
//        if (comment != null && !comment.isEmpty()) {
//            field.addAnnotation("@Schema(description=\"" + comment + "\")");
//            topLevelClass.addImportedType("io.swagger.v3.oas.annotations.media.Schema");
//        }
//        return true;
//    }
}
