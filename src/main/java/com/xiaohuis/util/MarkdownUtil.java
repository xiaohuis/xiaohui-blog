package com.xiaohuis.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <p> 用于把博客内容转换为html </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/2 20:49
 */
public class MarkdownUtil {
    public static String markdownToHtmlExtens(String markdown){
        //h标题生成id
        Set<Extension> headingAnchorExtension = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的HTML
        List<Extension> tableExtension= Arrays.asList(TablesExtension.create());
        Parser parser =Parser.builder().extensions(tableExtension).build();
        Node document=parser.parse(markdown);
        HtmlRenderer renderer=HtmlRenderer.builder()
                .extensions(headingAnchorExtension)
                .extensions(tableExtension)
                .build();
        return renderer.render(document);
    }
}
