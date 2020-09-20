package io.github.q843705423.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ScoreNode {
    private String name;
    private int score;
    private List<ScoreNode> childList;
    private ScoreNode father;

    public ScoreNode(String name) {
        this.name = name;

    }

    public void addChild(ScoreNode node) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        this.childList.add(node);
        node.setFather(this);

    }

    @Override
    public String toString() {
        return null;
    }
}

