package com.ikjunweb;

import org.assertj.core.api.Assertions;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MarkdownTest {

    @Test
    public void markdown() {
        String content = "this is *sparta*";
        String ans = "<p>this is <em>sparta</em></p>\n";

        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String result = renderer.render(document);

        assertThat(result).isEqualTo(ans);

        document = parser.parse("this is um");
        System.out.println(renderer.render(document));
    }
}
